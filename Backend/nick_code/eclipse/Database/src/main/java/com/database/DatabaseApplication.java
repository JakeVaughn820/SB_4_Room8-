package com.database;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.database.bulletin.*;
//import com.database.bulletin.pins.*;
import com.database.roomList.*;
import com.database.roomMembers.*;
import com.database.rooms.*;
import com.database.user.*;

/**
 * This class contains all of our endpoints for communication. 
 * 
 * @author Thane, Nick
 *
 */
@SpringBootApplication
public class DatabaseApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
		
	  @RestController
	  class GreetingController implements ErrorController {
			
//		  @Autowired private RoomListService roomListService;
//		  @Autowired private BulletinService bulletinService;
		  @Autowired private RoomsService roomService;
		  @Autowired private UserService userService;
		  @Autowired private ErrorAttributes errorAttributes;
		  @Autowired private RoomMembersService roomMembersService;
		  
		  /**
		   * Takes in a room Id and returns the roomList for that room.  
		   * 
		   * @param room
		   * @return
		   */
		  @GetMapping("/list/{room}")
		  public String getRoomList(@PathVariable String room) {
//			  List<RoomList> roomLists = roomListService.getLists();
			  String ret = "{\"RoomLists\":[";
//			  if(roomLists.isEmpty())
//				  ret += " ";
//			  for (RoomList temp : roomLists) {
//				ret += temp.toString() + ",";
//			  	}
		  	  ret = ret.substring(0, ret.length()-1) + "]}";
		      return ret;
		  }
		  
		  /**
		   * Creates a new roomList in the provided room. 
		   * 
		   * @param item
		   * @param room
		   * @return
		   */
		  @PostMapping(path = "/list/{room}", consumes = "application/json", produces = "application/json")
		  public String addRoomList(@RequestBody String item, @PathVariable String room) {
			  JSONObject body = new JSONObject(item);
			  Long intRoom = Long.parseLong(room);
			  String Title = body.getString("Title");
			  String Description = body.getString("Description");
//			  roomListService.addRoomList(new RoomList(intRoom, Title, Description));
			  return "{\"Response\":\"Success\"}";
		  }
		  
		  /**
		   * Takes in a room Id and returns the bulletin for that room. 
		   * 
		   * @param room
		   * @return
		   */
		  @GetMapping("/bulletin/{room}")
		  public String getBulletin(@PathVariable String room) {
	//		  List<Pin> pins = bulletinService.getPins();
			  String ret = "{\"BulletinBoard\":[";
			//  if(pins.isEmpty())
				  ret += " ";
	//		  for (Pin temp : pins) {
	//			ret += temp.toString() + ",";
	//		  	}
		  	  ret = ret.substring(0, ret.length()-1) + "]}";
		      return ret;
		  }
		  
		  /**
		   * Creates a bulletin for the corresponding room. 
		   * 
		   * @param item
		   * @param room
		   * @return
		   */
		  @PostMapping(path = "/bulletin/{room}", consumes = "application/json", produces = "application/json")
		  public String addToBulletin(@RequestBody String item, @PathVariable String room) {
			  JSONObject body = new JSONObject(item);
			  //String User = body.getString("User");
			  String Contents = body.getString("Contents");
			  //String User = item.substring(9, item.indexOf('\"', 9));
			  //String Contents = item.substring(User.length()+23, item.indexOf('\"', User.length()+23));
	//		  bulletinService.addPin(new Pin(Contents));
			  return "{\"Response\":\"Success\"}";
		  }
		  
		  /**
		   * Get all rooms a particular user is in
		   * @param user
		   * @return
		   */
		  @GetMapping("/room/{user}")
		  public String getRooms(@PathVariable String user) {
			  List<Rooms> rooms = roomMembersService.findRoomsByUserId(Long.parseLong(user));
			  String ret = "{\"Rooms\":[";
			  if(rooms.isEmpty())
				  ret += " ";
			  for (Rooms temp : rooms) {
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Id\":\"" + temp.getId() + "\"},";
			  	}
		  	  ret = ret.substring(0, ret.length()-1) + "]}";
		      return ret;
		  }
		  
		  /**
		   * This method creates a room therefore assigning which ever user created this room 
		   * is now the owner of the room 
		   * 
		   * @param item
		   * @param user
		   * @return
		   */
		  @PostMapping(path = "/room/{user}", consumes = "application/json", produces = "application/json")
		  public void addRoom(@RequestBody String item, @PathVariable String user)
		  {
			  JSONObject body = new JSONObject(item);
			  String Title = body.getString("Title");
			  Rooms toAdd = new Rooms(Title);
			  //TODO confirm that a user is actually a user, then make a new room
			  roomService.addRoom(toAdd);
			  Long roomsId = toAdd.getId();
			  System.out.println("==============================");
			  System.out.println(roomsId);
			  Long userId = (Long) Long.valueOf(user);
			  System.out.println("==============================");
			  System.out.println(userId);
			  RoomMembers adding = new RoomMembers(userService.findById(Long.valueOf("1")), toAdd, "Owner");
			  //RoomMembers adding = new RoomMembers(userId, roomsId, "Owner");
			  System.out.println("Funzies");
			  System.out.println("RoomId: " + adding.getRoomId() + " Id: " + adding.getId() + " UserId: " + adding.getUserId());
			  System.out.println("==============AFTER RoomMembers adding = new RoomMembers(userId, roomsId, \"Owner\");================");
			  roomMembersService.addRoomMembers(adding);
			  System.out.println("=============AFTER roomMembersService.addRoomMembers(adding);================");

			  ///adding.setUserRole("Owner");
			  //return "{\"Response\":\"Success\"}";
		  }
		  
		  /**
		   * This method deletes a room
		   * This method checks if the user requesting 
		   * to delete the room is a Owner since only 
		   * the owner can delete rooms.
		   * 
		   * @param item
		   * @param user
		   * @return
		   */
		  @PostMapping(path = "/room/delete/{user}", consumes = "application/json", produces = "application/json")
		  public String deleteRoom(@RequestBody String item, @PathVariable String user)
		  {
			  JSONObject body = new JSONObject(item);
			  long roomId = Long.parseLong((String) body.get("RoomID"));
			  long userId = Long.parseLong(user);
			  
			  //TODO
			  //If user has owner role in the room they have selected to delete, delete that room
			  //RoomMembers roleCheck = 
					  //roomMembersService.getRoomByRoomId(roomId, userId);
			  return null;
		  }
		  
		  /**
		   * User login, sends back whether the user exists and if the login was successful
		   * or not. 
		   * 
		   * @param item
		   * @return
		   */
		  @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
		  public String attemptLogin(@RequestBody String item) {
			  //TODO Add login system, actually do something to Log in, instead of returning 'match' or 'no match'
			  JSONObject body = new JSONObject(item);
			  String Email = body.getString("Email");
			  String Password = body.getString("Password");
			  List<User> userList = userService.getUsers();
			  for(User user : userList) {
				  if(user.getEmail().equals(Email)) {
					  if(user.getPassword().equals(Password)) {
						  System.out.println("{\"Response\":\"Success\", \"Username\":\"" + user.getName() + "\",\"UserId\":\"" + user.getId() + "\"}");
						  return "{\"Response\":\"Success\", \"Username\":\"" + user.getName() + "\",\"UserId\":\"" + user.getId() + "\"}";
					  }
					  else
						  return "{\"Response\":\"Incorrect Password\"}";
				  }
			  }
			  return "{\"Response\":\"User Does Not Exist\"}";
		  }
		  
		  /**
		   * User registration, registers a user to the database and sends back a success. 
		   * 
		   * @param item
		   * @return
		   */
		  @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
		  public String createUser(@RequestBody String item) {
			  JSONObject body = new JSONObject(item);
			  String Name = body.getString("Name");
			  String Email = body.getString("Email");
			  String Password = body.getString("Password");
			  List<User> userList = userService.getUsers();
			  for(User user : userList) {
				  if(user.getName().equals(Name))
					  return "{\"Response\":\"Name Already In Use\"}";
				  if(user.getEmail().equals(Email))
					  return "{\"Response\":\"Email Already In Use\"}";
			  }
			  userService.addUser(new User(Name, Email, Password));
			  return "{\"Response\":\"Success\"}";
		  }
		  
		  @Override
		  public String getErrorPath() {
		      return "/error";
		  }
		
		  @RequestMapping("/error")
		  public String error(HttpServletRequest servletRequest, Model model) {
		      Map<String, Object> attrs = errorAttributes.getErrorAttributes((WebRequest) new ServletRequestAttributes(servletRequest), false);
		      model.addAttribute("attrs", attrs);
		      return "error: "+attrs.toString();
		  }
	  }
}
