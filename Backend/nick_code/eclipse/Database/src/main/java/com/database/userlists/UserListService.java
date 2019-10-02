package com.database.userlists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserListService 
{
	@Autowired
	private UserListRepository listRepository; 
	
	public List<UserLists> getLists() 
	{
		return listRepository.findAll();
	}
	
	public void addList(UserLists userList)
	{
		listRepository.save(userList); 
	}
}