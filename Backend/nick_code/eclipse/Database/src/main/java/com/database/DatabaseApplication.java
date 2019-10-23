package com.database;

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
import com.database.bulletin.pins.Pin;
import com.database.lists.*;
import com.database.user.*;

@SpringBootApplication
public class DatabaseApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
		
	  @RestController
	  class GreetingController implements ErrorController {
			
		  @Autowired private RoomListService roomListService;
		  @Autowired private BulletinService bulletinService;
		  @Autowired private UserService userService;
		  @Autowired private ErrorAttributes errorAttributes;
		 
		  @RequestMapping("/hello/{name}")
		  String hello(@PathVariable String name) {
		      return "Hello, " + name + "!";
		  }
		  
		  @GetMapping("/list")
		  public String getRoomList() {
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
		  
		  @PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
		  public String addRoomList(@RequestBody String item) {
//			  JSONObject body = new JSONObject(item);
//			  String Title = body.getString("Title");
//			  String Description = body.getString("Description");
			  //String Description = item.substring(16, item.indexOf('\"', 16));
			  //String Title = item.substring(Description.length()+27, item.indexOf('\"', Description.length()+27));
//			  roomListService.addList(new RoomList(Title, Description));
			  return "200 OK";
		  }
		  
		  @GetMapping("/bulletin")
		  public String getBulletin() {
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
		  
		  @PostMapping(path = "/bulletin/{room}", consumes = "application/json", produces = "application/json")
		  public String addToBulletin(@RequestBody String item, @PathVariable String room) {
			  JSONObject body = new JSONObject(item);
			  //String User = body.getString("User");
			  String Contents = body.getString("Contents");
			  //String User = item.substring(9, item.indexOf('\"', 9));
			  //String Contents = item.substring(User.length()+23, item.indexOf('\"', User.length()+23));
	//		  bulletinService.addPin(new Pin(Contents));
			  return "200 OK";
		  }
		  
		  @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
		  public String attemptLogin(@RequestBody String item) {
			  //TODO Add login system, actually do something to Log in, instead of returning 'match' or 'no match'
			  JSONObject body = new JSONObject(item);
			  String Email = body.getString("Email");
			  String Password = body.getString("Password");
			  List<User> userList = userService.getUsers();
			  for(User user : userList) {
				  if(user.getEmail().equals(Email)) {
					  if(user.getPassword().equals(Password))
						  return "Success";
					  else
						  return "Incorrect Password";
				  }
			  }
			  return "User does not exist";
		  }
		  
		  @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
		  public String createUser(@RequestBody String item) {
			  JSONObject body = new JSONObject(item);
			  String Name = body.getString("Name");
			  String Email = body.getString("Email");
			  String Password = body.getString("Password");
			  //int In_Room = body.getInt("In_Room"); 
			  List<User> userList = userService.getUsers();
			  for(User user : userList) {
				  if(user.getName().equals(Name))
					  return "Name already in use";
				  if(user.getEmail().equals(Email))
					  return "Email already in use";
			  }
			  userService.addUser(new User(Name, Email, Password));
			  return "Success";
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
