package com.database.roomMembers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * RoomMembers controller for all room members endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 */
@Controller
public class RoomMembersController {
	@Autowired
	private RoomMembersRepository roomMembersRepository;
	@Autowired
	private RoomMembersService roomMembersService;

}
