package com.database;

import java.util.ArrayList;
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
import com.database.roomList.tasks.*;
import com.database.roomList.tasks.subtasks.SubTasks;
import com.database.roomList.tasks.subtasks.SubTasksService;
import com.database.roomMembers.*;
import com.database.rooms.*;
import com.database.user.*;

/**
 * This class contains all of our endpoints for communication.
 * 
 * @author Thane Storley, Nick
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
		@Autowired
		private TasksService taskService;
		@Autowired
		private SubTasksService subTaskService;

		/**
		 * Takes in a room Id and returns the roomList for that room.
		 * 
		 * @param room
		 * @return
		 */
		@GetMapping("/getsubtasks/{list}/{task}/")
		public String getSubTasks(@PathVariable String list, @PathVariable String task) {
			Long listId = Long.valueOf(list);
			Long taskId = Long.valueOf(task);

			List<SubTasks> subTasks = new ArrayList<SubTasks>();
			String response = "";
			
			if(taskService.findTasksByListId(listId).isEmpty())
				response =  "\"Response\":\"No such Task for given parameters\"";
			else {
				subTasks = subTaskService.findSubTasksByTaskId(taskId);
				response = "\"Response\":\"Success\"";
			}
				
			String ret = "{\"SubTaskList\":[";
			if (subTasks.isEmpty()) {
				ret += " ";
			}
			for (SubTasks temp : subTasks) {
				ret += "{\"Contents\":\"" + temp.getContents() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Creates a new list in the provided room.
		 * 
		 * @param item
		 * @param room
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/addsubtask/{room}/{task}/{user}", consumes = "application/json", produces = "application/json")
		public String addSubTask(@PathVariable("room") String room, @PathVariable("task") String task, @PathVariable("user") String user, @RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			Long taskId = Long.valueOf(task);
			
			JSONObject body = new JSONObject(item);
			String Contents = body.getString("Contents");
			
			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if(isAdmin==null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if(isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have the permissions to take this action\"}";

			Optional<Tasks> toAdd = taskService.findById(taskId);
			Tasks toAddTemp = null;
			
			try {
				toAddTemp = toAdd.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such Task exists\"}";
			}
			
			subTaskService.addSubTask(new SubTasks(Contents, toAddTemp));
			return "{\"Response\":\"Success\"}";
			
		}
		
		/**
		 * Takes in a room Id and returns the roomList for that room.
		 * 
		 * @param room
		 * @return
		 */
		@GetMapping("/gettasks/{room}/{list}/")
		public String gettasks(@PathVariable String room, @PathVariable String list) {
			Long roomId = Long.valueOf(room);
			Long listId = Long.valueOf(list);

			List<Tasks> tasks = new ArrayList<Tasks>();
			String response = "";
			
			if(roomListService.findListsByRoomId(roomId).isEmpty())
				response =  "\"Response\":\"No such List for given parameters\"";
			else {
				tasks = taskService.findTasksByListId(listId);
				response = "\"Response\":\"Success\"";
			}
				
			String ret = "{\"TaskList\":[";
			if (tasks.isEmpty()) {
				ret += " ";
			}
			for (Tasks temp : tasks) {
				ret += "{\"Contents\":\"" + temp.getContents()  + "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Creates a new list in the provided room.
		 * 
		 * @param item
		 * @param room
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/addtask/{room}/{list}/{user}", consumes = "application/json", produces = "application/json")
		public String addTask(@PathVariable("room") String room, @PathVariable("list") String list, @PathVariable("user") String user, @RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			Long listId = Long.valueOf(list);
			JSONObject body = new JSONObject(item);
			String Contents = body.getString("Contents");
			
			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if(isAdmin==null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if(isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have the permissions to take this action\"}";
			
			Optional<RoomList> toAdd = roomListService.findById(listId);
			RoomList toAddTemp = null;
			
			try {
				toAddTemp = toAdd.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such List exists\"}";
			}
			
			taskService.addTask(new Tasks(Contents, toAddTemp));
			return "{\"Response\":\"Success\"}";
			
		}
		
		
		
		/**
		 * Takes in a room Id and returns the roomList for that room.
		 * 
		 * @param room
		 * @return
		 */
		@GetMapping("/getlists/{room}/{user}/")
		public String getRoomList(@PathVariable String room, @PathVariable String user) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			
			List<RoomList> lists = new ArrayList<RoomList>();
			String response = "";
			
			if(roomMembersService.findRoomMemberByIds(userId, roomId)!=null) {
				lists = roomListService.findListsByRoomId(roomId);
				response = "\"Response\":\"Success\"";
			}
			else
				response =  "\"Response\":\"No such RoomMembers object for given parameters\"";

			String ret = "{\"RoomLists\":[";
			if (lists.isEmpty()) {
				ret += " ";
			}
			for (RoomList temp : lists) {
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Description\":\"" + temp.getDescription() + "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Creates a new list in the provided room.
		 * 
		 * @param item
		 * @param room
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/addlist/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String addRoomList(@PathVariable("room") String room, @PathVariable("user") String user, @RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			JSONObject body = new JSONObject(item);
			String Title = body.getString("Title");
			String Description = body.getString("Description");
			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if(isAdmin==null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if(isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have the permissions to take this action\"}";
			
			Optional<Rooms> toAdd = roomService.findById(roomId);
			Rooms toAddTemp = null;
			
			try {
				toAddTemp = toAdd.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such user exists\"}";
			}
			
			roomListService.addRoomList(new RoomList(toAddTemp, Title, Description));
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
			Long userId = Long.valueOf(user);
			List<Rooms> rooms = roomMembersService.findRoomsByUserId(userId);
			String ret = "{\"Rooms\":[";
			if (rooms.isEmpty()) {
				ret += " ";
			}
			for (Rooms temp : rooms) {
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Id\":\"" + temp.getId() + "\",\"Role\":\"" + roomMembersService.findRoomMemberByIds(userId, temp.getId()).getUserRole() + "\"},";
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
		public String addRoom(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			String Title = body.getString("Title");
			Rooms toAdd = new Rooms(Title);
			roomService.addRoom(toAdd);

			Optional<User> admin = userService.findById(Long.valueOf(user));
			User adminTemp = null;
			Optional<Rooms> room = roomService.findById(toAdd.getId());
			Rooms roomTemp = null;

			try {
				adminTemp = admin.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such user exists\"}";
			}
			try {
				roomTemp = room.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such room exists\"}";
			}
			
			RoomMembers roomMember = new RoomMembers(adminTemp, roomTemp, "OWNER");

			System.out.println("Create room members object " + roomMember);
			roomMembersService.addRoomMembers(roomMember);
			System.out.println("Add room members object to datababse" + roomMember);
			return "{\"Response\":\"Success\"}";
		}

		/**
		 * This method deletes a room This method checks if the user requesting to
		 * delete the room is a Owner since only the owner can delete rooms.
		 * 
		 * @param item
		 * @return
		 */
		  @PostMapping(path = "/room/kick/{user}")
		  public String kickUser(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			String RoomId = body.getString("RoomId");
			String toKick = body.getString("UserId");
			
			RoomMembers toDel = roomMembersService.findRoomMemberByIds(Long.valueOf(toKick), Long.valueOf(RoomId));
			RoomMembers isOwner = roomMembersService.findRoomMemberByIds(Long.valueOf(user), Long.valueOf(RoomId));
			if(toDel==null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if(isOwner.getUserRole().equals("OWNER")) {
				roomMembersService.deleteById(toDel.getId());
				return "{\"Response\":\"Success\"}";
			}
			else
				return "{\"Response\":\"User is not an OWNER\"}";
		  }

		@PostMapping(path = "/room/join/{user}", consumes = "application/json", produces = "application/json")
		public String joinRoom(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(body.getString("RoomId"));
			
			Optional<User> admin = userService.findById(userId);
			User adminTemp = null;
			Optional<Rooms> room = roomService.findById(roomId);
			Rooms roomTemp = null;

			try {
				adminTemp = admin.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such user exists\"}";
			}
			try {
				roomTemp = room.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such room exists\"}";
			}
			
			RoomMembers roomMember = new RoomMembers(adminTemp, roomTemp, "VIEWER");
			
			System.out.println("Create room members object " + roomMember);
			roomMembersService.addRoomMembers(roomMember);
			System.out.println("Add room members object to datababse" + roomMember);
			return "{\"Response\":\"Success\"}";
		}
		
		@PostMapping(path = "/room/delete/{user}")
		  public String deleteRoom(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(body.getString("RoomId"));
			
			RoomMembers isOwner = roomMembersService.findRoomMemberByIds(userId, roomId);
			if(isOwner==null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if(isOwner.getUserRole().equals("OWNER")) {
				roomService.deleteById(roomId);
				List<RoomMembers> temp = roomMembersService.findRoomMembersByRoomId(roomId);
				for(RoomMembers x : temp) {
					roomMembersService.deleteById(x.getId());
				}
				return "{\"Response\":\"Success\"}";
			}
			else
				return "{\"Response\":\"User is not an OWNER\"}";
		  }
		
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
