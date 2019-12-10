package com.database.user;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller for all user endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	
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
}
