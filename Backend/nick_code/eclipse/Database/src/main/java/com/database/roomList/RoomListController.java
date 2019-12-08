package com.database.roomList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * RoomList controller for all roomlist endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 *
 */
@Controller
public class RoomListController {

	@Autowired
	private RoomListRepository roomListRepository;
	@Autowired
	private RoomListService roomListService;

}
