package com.database.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	@Autowired
	private UserRepository listRepository; 
	
	public List<User> getUsers() 
	{
		return listRepository.findAll();
	}
	
	public void addUser(User user)
	{
		listRepository.save(user); 
	}
}