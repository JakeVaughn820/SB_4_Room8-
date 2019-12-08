package com.database.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Rooms controller for all rooms endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 */
@Controller
public class RoomsController {
	@Autowired
	private RoomsRepository roomsRepository;
	@Autowired
	private RoomsService roomService;

}
