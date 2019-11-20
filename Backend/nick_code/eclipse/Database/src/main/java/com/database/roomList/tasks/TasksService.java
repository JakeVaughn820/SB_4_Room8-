package com.database.roomList.tasks;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.database.roomList.RoomList;
import com.database.rooms.RoomsService;

||||||| merged common ancestors
import com.database.roomList.RoomList;

=======
>>>>>>> 99ebb0f2cd0038b69d08ce9f48315fde1fc32181
/**
 * This class implements the task repository.
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Service
public class TasksService {
	/**
	 * Holds the task repository object.
	 */
	@Autowired
<<<<<<< HEAD
	private TasksRepository taskRepository; 
	
	private final Logger logger = LoggerFactory.getLogger(RoomsService.class);
	
||||||| merged common ancestors
	private TasksRepository taskRepository; 
	
=======
	private TasksRepository taskRepository;

>>>>>>> 99ebb0f2cd0038b69d08ce9f48315fde1fc32181
	/**
	 * Gets all tasks in the database.
	 * 
	 * @return
	 */
	public List<Tasks> getTask() {
		return taskRepository.findAll();
	}
<<<<<<< HEAD
	
	public Optional<Tasks> findById(Long taskId)
	{
		Optional<Tasks> task = null;
		try {
			task = taskRepository.findById(taskId);
			if (task.equals(null))
				throw new NullPointerException();
		} catch (NullPointerException e) {
			logger.info("User does not exist");
		}
		return task;
	}
	
||||||| merged common ancestors
	
=======

>>>>>>> 99ebb0f2cd0038b69d08ce9f48315fde1fc32181
	/**
	 * Adds a task to the database.
	 * 
	 * @param Task
	 * @return
	 */
	public Tasks addTask(Tasks Task) {
		return taskRepository.save(Task);
	}

	/**
	 * Gets number of tasks within the database.
	 * 
	 * @return
	 */
	public Long count() {
		return taskRepository.count();
	}

	/**
	 * Returns all tasks for a given list.
	 * 
	 * @param listId
	 * @return
	 */
	public List<Tasks> findTasksByListId(Long listId) {
		return taskRepository.findTasksByListId(listId);
	}

	/**
	 * Deletes a task from the database. Throws IllegalArgumentException if the task
	 * does not exist.
	 * 
	 * @param taskId
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean deleteById(Long taskId) throws IllegalArgumentException {
		taskRepository.deleteById(taskId);
		return true;
	}

}
