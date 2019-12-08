package com.database.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * User controller for all user endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 */
@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

}
