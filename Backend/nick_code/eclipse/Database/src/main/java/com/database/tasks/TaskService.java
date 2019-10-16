package com.database.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService 
{
	@Autowired
	private TaskRepository taskRepository; 
	
	public List<Task> getLists() 
	{
		return taskRepository.findAll();
	}
	
	public void addList(Task userList)
	{
		taskRepository.save(userList); 
	}
	
    public Long count() {

        return taskRepository.count();
    }

    public void deleteById(String userId) {

    	taskRepository.deleteById(userId);;
    }
}