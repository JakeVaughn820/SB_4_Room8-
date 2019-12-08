package com.database.roomList.tasks.subtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Subtask controller for all subtask endpoints.
 * 
 * @author Nickolas Mitchell
 */
@Controller
public class SubTasksController {
	@Autowired
	private SubTasksRepository subtasksRepository; 
	@Autowired
	private SubTasksService subTaskService;

	

}
