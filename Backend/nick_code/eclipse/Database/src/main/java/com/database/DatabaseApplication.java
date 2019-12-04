package com.database;

import java.util.ArrayList;
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
import com.database.roomList.tasks.subtasks.*;
import com.database.roomMembers.*;
import com.database.rooms.*;
import com.database.schedule.events.*;
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
		@Autowired
		private EventsService eventService;

		/**
		 * Get Events.
		 * 
		 * Gets all of the events of a provided room.
		 * 
		 * @param room
		 * @param user
		 * @return
		 */
		@GetMapping("/getevent/{room}/{user}/")
		public String getEvent(@PathVariable String room, @PathVariable String user) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);

			List<Events> events = new ArrayList<Events>();
			String response = "";

			if (roomMembersService.findRoomMemberByIds(userId, roomId) != null) {
				events = eventService.findEventsByRoomId(roomId);
				response = "\"Response\":\"Success\"";
			} else
				response = "\"Response\":\"No such RoomMembers object for given parameters\"";

			String ret = "{\"Events\":[";
			if (events.isEmpty()) {
				ret += " ";
			}
			for (Events temp : events) {
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Description\":\"" + temp.getDescription()
						+ "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Create Event.
		 * 
		 * This method takes in the roomId of the room for the room list to be created
		 * and also takes in the userId of the user creating the room. This method is
		 * called whenever a user on the frontend selects the create room option. A
		 * successful response is sent to the frontend if the user is a member of the
		 * room. If the user is not a member of the room then the room list is not
		 * created and a response of "No such RoomMembers object for given parameters"
		 * is sent to the frontend. The frontend is handling user permissions for this
		 * endpoint.
		 * 
		 * @param item
		 * @param room
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/addevent/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String addEvent(@PathVariable("room") String room, @PathVariable("user") String user,
				@RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			JSONObject body = new JSONObject(item);
			String Title = body.getString("Title");
			String Description = body.getString("Description");
			String StartTime = body.getString("StartTime");
			String EndTime = body.getString("EndTime");
			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if (isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have the permissions to take this action\"}";

			Optional<Rooms> tempRoom = roomService.findById(roomId);
			Rooms eventRoom = null;
			Optional<User> tempUser = userService.findById(userId);
			User eventUser = null;

			try {
				eventRoom = tempRoom.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such user exists\"}";
			}

			try {
				eventUser = tempUser.get();
			} catch (NoSuchElementException e) {
				return "{\"Response\":\"No such user exists\"}";
			}

			if (StartTime.equals(null) && EndTime.equals(null))
				eventService.addEvent(new Events(eventRoom, Title, Description, eventUser));
			else
				eventService.addEvent(new Events(eventRoom, Title, Description, StartTime, EndTime, eventUser));
			return "{\"Response\":\"Success\"}";
		}

		/**
		 * Delete Event.
		 * 
		 * Deletes a provided event.
		 * 
		 * @param room
		 * @param user
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/deleteevent/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String deleteEvent(@PathVariable("room") String room, @PathVariable("user") String user,
				@RequestBody String item) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(room);
			Long listId = Long.valueOf(body.getString("listId"));

			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if (isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have permission to complete this action\"}";
			else {
				List<Tasks> temp = taskService.findTasksByListId(listId);
				for (Tasks x : temp) {
					List<SubTasks> temp2 = subTaskService.findSubTasksByTaskId(x.getId());
					for (SubTasks y : temp2) {
						subTaskService.deleteById(y.getId());
					}
					taskService.deleteById(x.getId());
				}
				roomListService.deleteById(listId);
				return "{\"Response\":\"Success\"}";
			}
		}

		/**
		 * Get Subtasks.
		 * 
		 * Returns all subtasks for a particular room.
		 * 
		 * @param list
		 * @param task
		 * @return
		 */
		@GetMapping("/getsubtasks/{list}/{task}/")
		public String getSubTasks(@PathVariable String list, @PathVariable String task) {
			Long listId = Long.valueOf(list);
			Long taskId = Long.valueOf(task);

			List<SubTasks> subTasks = new ArrayList<SubTasks>();
			String response = "";

			if (taskService.findTasksByListId(listId).isEmpty())
				response = "\"Response\":\"No such Task for given parameters\"";
			else {
				subTasks = subTaskService.findSubTasksByTaskId(taskId);
				response = "\"Response\":\"Success\"";
			}

			String ret = "{\"SubTaskList\":[";
			if (subTasks.isEmpty()) {
				ret += " ";
			}
			for (SubTasks temp : subTasks) {
				ret += "{\"Contents\":\"" + temp.getContents() + "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Create Subtask.
		 * 
		 * Creates a subtask for the provided room.
		 * 
		 * @param room
		 * @param task
		 * @param user
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/addsubtask/{room}/{task}/{user}", consumes = "application/json", produces = "application/json")
		public String addSubTask(@PathVariable("room") String room, @PathVariable("task") String task,
				@PathVariable("user") String user, @RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			Long taskId = Long.valueOf(task);

			JSONObject body = new JSONObject(item);
			String Contents = body.getString("Contents");

			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if (isAdmin.getUserRole().equals("VIEWER"))
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
		 * Delete Subtask.
		 * 
		 * Deletes a provided subtask for a provided room.
		 * 
		 * @param room
		 * @param user
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/deletesubtask/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String deleteSubTask(@PathVariable("room") String room, @PathVariable("user") String user,
				@RequestBody String item) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(room);
			Long subTaskId = Long.valueOf(body.getString("subTaskId"));

			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if (isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have permission to complete this action\"}";
			else {
				subTaskService.deleteById(subTaskId);
				return "{\"Response\":\"Success\"}";
			}
		}

		/**
		 * Get Tasks.
		 * 
		 * Gets all of the tasks for a provided room.
		 * 
		 * @param room
		 * @param list
		 * @return
		 */
		@GetMapping("/gettasks/{room}/{list}/")
		public String gettasks(@PathVariable String room, @PathVariable String list) {
			Long roomId = Long.valueOf(room);
			Long listId = Long.valueOf(list);

			List<Tasks> tasks = new ArrayList<Tasks>();
			String response = "";

			if (roomListService.findListsByRoomId(roomId).isEmpty())
				response = "\"Response\":\"No such List for given parameters\"";
			else {
				tasks = taskService.findTasksByListId(listId);
				response = "\"Response\":\"Success\"";
			}

			String ret = "{\"TaskList\":[";
			if (tasks.isEmpty()) {
				ret += " ";
			}
			for (Tasks temp : tasks) {
				ret += "{\"Contents\":\"" + temp.getContents() + "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Create Task.
		 * 
		 * Creates a task for a provided room.
		 * 
		 * @param room
		 * @param list
		 * @param user
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/addtask/{room}/{list}/{user}", consumes = "application/json", produces = "application/json")
		public String addTask(@PathVariable("room") String room, @PathVariable("list") String list,
				@PathVariable("user") String user, @RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			Long listId = Long.valueOf(list);
			JSONObject body = new JSONObject(item);
			String Contents = body.getString("Contents");

			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if (isAdmin.getUserRole().equals("VIEWER"))
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
		 * Delete Task.
		 * 
		 * Deletes a provided task.
		 * 
		 * @param room
		 * @param user
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/deletetask/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String deleteTask(@PathVariable("room") String room, @PathVariable("user") String user,
				@RequestBody String item) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(room);
			Long taskId = Long.valueOf(body.getString("taskId"));

			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if (isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have permission to complete this action\"}";
			else {
				List<SubTasks> temp = subTaskService.findSubTasksByTaskId(taskId);
				for (SubTasks x : temp) {
					subTaskService.deleteById(x.getId());
				}
				taskService.deleteById(taskId);
				return "{\"Response\":\"Success\"}";
			}
		}

		/**
		 * Get Lists.
		 * 
		 * Gets all of the lists of a provided room.
		 * 
		 * @param room
		 * @param user
		 * @return
		 */
		@GetMapping("/getlists/{room}/{user}/")
		public String getRoomList(@PathVariable String room, @PathVariable String user) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);

			List<RoomList> lists = new ArrayList<RoomList>();
			String response = "";

			if (roomMembersService.findRoomMemberByIds(userId, roomId) != null) {
				lists = roomListService.findListsByRoomId(roomId);
				response = "\"Response\":\"Success\"";
			} else
				response = "\"Response\":\"No such RoomMembers object for given parameters\"";

			String ret = "{\"RoomLists\":[";
			if (lists.isEmpty()) {
				ret += " ";
			}
			for (RoomList temp : lists) {
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Description\":\"" + temp.getDescription()
						+ "\",\"Id\":\"" + temp.getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Create RoomList.
		 * 
		 * This method takes in the roomId of the room for the room list to be created
		 * and also takes in the userId of the user creating the room. This method is
		 * called whenever a user on the frontend selects the create room option. A
		 * successful response is sent to the frontend if the user is a member of the
		 * room. If the user is not a member of the room then the room list is not
		 * created and a response of "No such RoomMembers object for given parameters"
		 * is sent to the frontend. The frontend is handling user permissions for this
		 * endpoint.
		 * 
		 * @param item
		 * @param room
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/addlist/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String addRoomList(@PathVariable("room") String room, @PathVariable("user") String user,
				@RequestBody String item) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);
			JSONObject body = new JSONObject(item);
			String Title = body.getString("Title");
			String Description = body.getString("Description");
			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null) {
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			}
			if (isAdmin.getUserRole().equals("VIEWER"))
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
		 * Delete List.
		 * 
		 * Deletes a provided list.
		 * 
		 * @param room
		 * @param user
		 * @param item
		 * @return
		 */
		@PostMapping(path = "/deletelist/{room}/{user}", consumes = "application/json", produces = "application/json")
		public String deletelist(@PathVariable("room") String room, @PathVariable("user") String user,
				@RequestBody String item) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(room);
			Long listId = Long.valueOf(body.getString("listId"));

			RoomMembers isAdmin = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isAdmin == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if (isAdmin.getUserRole().equals("VIEWER"))
				return "{\"Response\":\"User does not have permission to complete this action\"}";
			else {
				List<Tasks> temp = taskService.findTasksByListId(listId);
				for (Tasks x : temp) {
					List<SubTasks> temp2 = subTaskService.findSubTasksByTaskId(x.getId());
					for (SubTasks y : temp2) {
						subTaskService.deleteById(y.getId());
					}
					taskService.deleteById(x.getId());
				}
				roomListService.deleteById(listId);
				return "{\"Response\":\"Success\"}";
			}
		}

		/**
		 * Get Rooms.
		 * 
		 * This method returns all of the rooms a particular user is in.
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
				ret += "{\"Title\":\"" + temp.getTitle() + "\",\"Id\":\"" + temp.getId() + "\",\"Role\":\""
						+ roomMembersService.findRoomMemberByIds(userId, temp.getId()).getUserRole() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]}";
			return ret;
		}

		/**
		 * Create Room.
		 * 
		 * This method creates a room therefore assigning which ever user created this
		 * room is now the owner of the room. If the room does not exist a response of
		 * "No such room exists" is returned or if a user does not exist a response of
		 * "No such user exists" is returned. Otherwise a success response is returned.
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
		 * Get RoomMembers.
		 * 
		 * Returns all rooms a user is a member of.
		 * 
		 * @param room
		 * @param user
		 * @return
		 */
		@GetMapping("/getroommembers/{room}/{user}/")
		public String getRoomMembers(@PathVariable String room, @PathVariable String user) {
			Long roomId = Long.valueOf(room);
			Long userId = Long.valueOf(user);

			List<RoomMembers> roomMembers = new ArrayList<RoomMembers>();
			String response = "";

			RoomMembers isOwner = roomMembersService.findRoomMemberByIds(userId, roomId);

			if (isOwner == null)
				response = "\"Response\":\"No such RoomMembers object for given parameters\"";
			else if (isOwner.getUserRole().equals("OWNER")) {
				roomMembers = roomMembersService.findRoomMembersByRoomId(roomId);
				response = "\"Response\":\"Success\"";
			} else
				response = "\"Response\":\"Current user does  not have permission to access this information\"";

			String ret = "{\"Users\":[";
			if (roomMembers.isEmpty()) {
				ret += " ";
			}
			for (RoomMembers temp : roomMembers) {
				ret += "{\"Name\":\"" + temp.getUser().getName() + "\",\"Email\":\"" + temp.getUser().getEmail()
						+ "\",\"Role\":\"" + temp.getUserRole() + "\",\"UserId\":\"" + temp.getUser().getId() + "\"},";
			}
			ret = ret.substring(0, ret.length() - 1) + "]," + response + "}";
			return ret;
		}

		/**
		 * Kick User From Room.
		 * 
		 * This method kicks a particular user from a room. Only the OWNER can kick a
		 * user out of a room. If the user trying to kick another user from the room is
		 * not an OWNER, a response of "User is not an OWNER" is returned. If the user
		 * to be kicked from the room does not exist then a response of "No such
		 * RoomMembers object for given parameters" is returned. Otherwise a success
		 * response is returned.
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
			if (toDel == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters (room and user being kicked)\"}";
			else if (isOwner == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters (room and current user)\"}";
			else if (!(isOwner.getRoom().equals(toDel.getRoom()))) // not in the same room
				return "{\"Response\":\"Current user is not in the same room as the user being kicked\"}";
			if (isOwner.getUserRole().equals("OWNER")) {
				roomMembersService.deleteById(toDel.getId());
				return "{\"Response\":\"Success\"}";
			} else
				return "{\"Response\":\"User is not an OWNER\"}";
		}

		/**
		 * Set User Role.
		 * 
		 * Sets the user role. The user role can only be set by the OWNER of the room.
		 * 
		 * @param item
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/room/setrole/{user}")
		public String setRole(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			String RoomId = body.getString("RoomId");
			String setUser = body.getString("UserId");
			String role = body.getString("Role");
			role = role.toUpperCase();

			RoomMembers toSet = roomMembersService.findRoomMemberByIds(Long.valueOf(setUser), Long.valueOf(RoomId));
			RoomMembers isOwner = roomMembersService.findRoomMemberByIds(Long.valueOf(user), Long.valueOf(RoomId));
			if (toSet == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters (room and user being set)\"}";
			else if (isOwner == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters (room and current user)\"}";
			else if (!(isOwner.getRoom().equals(toSet.getRoom()))) // not in the same room
				return "{\"Response\":\"Current user is not in the same room as the user being set\"}";
			else if (isOwner.getUserRole().equals("OWNER")) {
				if (role.equals("VIEWER") || role.equals("ROOMMATE")) {
					toSet.setUserRole(role);
					return "{\"Response\":\"Success\"}";
				} else
					return "{\"Response\":\"Invalid Role\"}";
			} else
				return "{\"Response\":\"User is not an OWNER\"}";
		}

		/**
		 * Join Room.
		 * 
		 * This method allows a user to join a room. If the room does not exist a
		 * response of "No such room exists" is returned and if the room the user
		 * requested to join does not exist a response of "No such room exists" is
		 * returned. Otherwise a success response is returned and the viewer's
		 * permissions are automatically set to VIEWER. The frontend allows for the
		 * OWNER to give a VIEWER more permission at their discretion.
		 * 
		 * @param item
		 * @param user
		 * @return
		 */
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

		/**
		 * Delete Room.
		 * 
		 * This method deletes a the requested room. Only ADMIN's can delete rooms. If
		 * the user is not an ADMIN a response of "User is not an OWNER" is returned. If
		 * the room does not exist a response of "No such RoomMembers object for given
		 * parameters" is returned. Otherwise a success response is returned and the
		 * room is removed from the database and all users in said room will no longer
		 * have accesss to that room. Also, all of the roomMembers objects with the
		 * target roomId will also be removed from the database.
		 * 
		 * @param item
		 * @param user
		 * @return
		 */
		@PostMapping(path = "/room/delete/{user}")
		public String deleteRoom(@RequestBody String item, @PathVariable String user) {
			JSONObject body = new JSONObject(item);
			Long userId = Long.valueOf(user);
			Long roomId = Long.valueOf(body.getString("RoomId"));

			RoomMembers isOwner = roomMembersService.findRoomMemberByIds(userId, roomId);
			if (isOwner == null)
				return "{\"Response\":\"No such RoomMembers object for given parameters\"}";
			if (isOwner.getUserRole().equals("OWNER")) {
				roomService.deleteById(roomId);
				List<RoomMembers> temp = roomMembersService.findRoomMembersByRoomId(roomId);
				for (RoomMembers x : temp) {
					roomMembersService.deleteById(x.getId());
				}
				return "{\"Response\":\"Success\"}";
			} else
				return "{\"Response\":\"User is not an OWNER\"}";
		}

		/**
		 * Login.
		 * 
		 * This method checks if the user is inside of the database and the correct
		 * password is provided. If not then a response of "Incorrect Password" is
		 * returned. If the user is within the database and has entered the correct
		 * password then a success response is returned.
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
		 * Registration.
		 * 
		 * This method adds a user to the database. If the entered email or name is
		 * already within the database, then a response of "Email Already In Use" or
		 * "Name Already In Use" respectively. Otherwise a success response is returned
		 * and the user is added to the database.
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
