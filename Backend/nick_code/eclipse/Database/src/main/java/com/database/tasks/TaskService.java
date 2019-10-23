package com.database.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService 
{
	@Autowired
	private TaskRepository taskRepository; 
	
	public List<Task> getTasks() 
	{
		return taskRepository.findAll();
	}
	
	public void addTask(Task task)
	{
		taskRepository.save(task); 
	}
	
    public Long count() {

        return taskRepository.count();
    }

    public boolean deleteById(String taskId) throws IllegalArgumentException
    {
    	taskRepository.deleteById(taskId);
    	return true; 
    }
}