package com.database;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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

		@Autowired
		private RoomListService roomListService;
		@Autowired
		private RoomsService roomService;
		@Autowired
		private UserService userService;
		@Autowired
		private ErrorAttributes errorAttributes;
		@Autowired
		private RoomMembersService roomMembersService;

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
			ret = ret.substring(0, ret.length() - 1) + "]}";
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
			String Title = body.getString("Title");
			String Description = body.getString("Description");
//			  roomListService.addRoomList(new RoomList(intRoom, Title, Description));
			return "{\"Response\":\"Success\"}";
		}

		/**
		 * Get all rooms a particular user is in
		 * 
		 * @param user
		 * @return
		 */
		@GetMapping("/getrooms/{user}")
		public String getRooms(@PathVariable String user) {
			List<Rooms> rooms = roomMembersService.findRoomsByUserId(Long.valueOf(user));
			String ret = "{\"Rooms\":[";
			if (rooms.isEmpty()) {
				ret += " ";
			}
			for (Rooms temp : rooms) {
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]}";
			return ret;
		}

		/**
		 * This method creates a room therefore assigning which ever user created this
		 * room is now the owner of the room
		 * 
		 * @param item
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/room/{user}", consumes = "application/json", produces = "application/json")
		public void addRoom(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			String Title = body.getString("Title");
			Rooms toAdd = new Rooms(Title);
			// System.out.println("Created Room object " + toAdd);
			roomService.addRoom(toAdd);
			// System.out.println("Added " + toAdd + " to the database");
			// Long roomsId = toAdd.getId();
			// System.out.println("Get room id " + roomsId);
			// Long userId = Long.valueOf(user);
			// System.out.println("Get user id " + userId);

			Optional<User> admin = userService.findById(Long.valueOf(user));
			User adminTemp = null;
			Optional<Rooms> room = roomService.findById(toAdd.getId());
			Rooms roomTemp = null;

			try {
				adminTemp = admin.get();
			} catch (NoSuchElementException e) {
				System.out.println("No such user exists");
				return;
			}
			try {
				roomTemp = room.get();
			} catch (NoSuchElementException e) {
				System.out.println("No such room exits");
			}

//			Long userId = adminTemp.getId();
//			Long roomId = roomTemp.getId(); 

			RoomMembers roomMember = new RoomMembers(adminTemp, roomTemp, "OWNER");

			System.out.println("Create room members object " + roomMember);
			roomMembersService.addRoomMembers(roomMember);
			System.out.println("Add room members object to datababse" + roomMember);
		}

		/**
		 * This method deletes a room This method checks if the user requesting to
		 * delete the room is a Owner since only the owner can delete rooms.
		 * 
		 * @param item
		 * @return
		 */
//		  @GetMapping(path = "/room/delete/{user}")
//		  public String deleteRoom(@PathVariable Integer roomId, @PathVariable Integer userId)
//		  {
//			  RoomMembers roleCheck = roomMembersService.getRoomByRoomId(roomId, userId);
//			  if()
//		  }

		/**
		 * User login, sends back whether the user exists and if the login was
		 * successful or not.
		 * 
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
		public String attemptLogin(@RequestBody String item) {
			// TODO Add login system, actually do something to Log in, instead of returning
			// 'match' or 'no match'
			JSONObject body = new JSONObject(item);
			String Email = body.getString("Email");
			String Password = body.getString("Password");
			List<User> userList = userService.getUsers();
			for (User user : userList) {
				if (user.getEmail().equals(Email)) {
					if (user.getPassword().equals(Password)) {
						System.out.println("{\"Response\":\"Success\", \"Username\":\"" + user.getName()
								+ "\",\"UserId\":\"" + user.getId() + "\"}");
						return "{\"Response\":\"Success\", \"Username\":\"" + user.getName() + "\",\"UserId\":\""
								+ user.getId() + "\"}";
					} else
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
			for (User user : userList) {
				if (user.getName().equals(Name))
					return "{\"Response\":\"Name Already In Use\"}";
				if (user.getEmail().equals(Email))
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
			Map<String, Object> attrs = errorAttributes
					.getErrorAttributes((WebRequest) new ServletRequestAttributes(servletRequest), false);
			model.addAttribute("attrs", attrs);
			return "error: " + attrs.toString();
		}
	}
}
