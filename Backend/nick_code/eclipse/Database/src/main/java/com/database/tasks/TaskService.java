package com.database.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService 
{
	@Autowired
	private TaskRepository listRepository; 
	
	public List<Task> getLists() 
	{
		return listRepository.findAll();
	}
	
	public void addList(Task userList)
	{
		listRepository.save(userList); 
	}
}