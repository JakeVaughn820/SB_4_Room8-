package com.database.roomList.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Task Controller for all task endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 */
@Controller
public class TasksController {

	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private TasksService taskService;

}
