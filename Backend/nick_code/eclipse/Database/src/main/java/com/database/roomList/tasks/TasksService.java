package com.database.roomList.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksService 
{
	@Autowired
	private TasksRepository taskRepository; 
	
	public List<Tasks> getTask() 
	{
		return taskRepository.findAll();
	}
	
	public Tasks addTask(Tasks Task)
	{
		return taskRepository.save(Task);
	}
	
    public Long count() 
    {
        return taskRepository.count();
    }

    public boolean deleteById(String taskId) throws IllegalArgumentException
    {
    	taskRepository.deleteById(taskId);
        return true; 
    }

}
