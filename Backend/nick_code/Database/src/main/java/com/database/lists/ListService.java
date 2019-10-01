package com.database.lists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService 
{
	@Autowired
	private ListRepository listRepository; 
	
	public List<UserLists> getLists() 
	{
		return listRepository.findAll();
	}
	
	public void addList(UserLists userList)
	{
		listRepository.save(userList); 
	}
	
	


}