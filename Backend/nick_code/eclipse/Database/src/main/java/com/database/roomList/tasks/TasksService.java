//package com.database.roomList.tasks;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * This class implements the task repository. 
// * 
// * @author Nickolas Mitchell
// */
//@Service
//public class TasksService 
//{
//	/**
//	 * Holds the task repository object. 
//	 */
//	@Autowired
//	private TasksRepository taskRepository; 
//	
//	/**
//	 * Gets all tasks in the database. 
//	 * 
//	 * @return
//	 */
//	public List<Tasks> getTask() 
//	{
//		return taskRepository.findAll();
//	}
//	
//	/**
//	 * Adds a task to the database. 
//	 * 
//	 * @param Task
//	 * @return
//	 */
//	public Tasks addTask(Tasks Task)
//	{
//		return taskRepository.save(Task);
//	}
//	
//	/**
//	 * Gets number of tasks within the database. 
//	 * 
//	 * @return
//	 */
//    public Long count() 
//    {
//        return taskRepository.count();
//    }
//
//    /**
//     * Deletes a task from the database. Throws IllegalArgumentException 
//     * if the task does not exist. 
//     * 
//     * @param taskId
//     * @return
//     * @throws IllegalArgumentException
//     */
//    public boolean deleteById(Long taskId) throws IllegalArgumentException
//    {
//    	taskRepository.deleteById(taskId);
//        return true; 
//    }
//
//}
