package com.database.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository; 
	
	public List<User> getUsers() 
	{
		return userRepository.findAll();
	}
	
	public User getUserById(Integer userId)
	{
		List<User> temp = new ArrayList<User>();
		User toReturn = null; 
		for(User i : temp)
		{
			if(i.getId().equals(userId))
			{
				toReturn = i; 
				break; 
			}
		}
		return toReturn;
	}
	
	public User addUser(User user)
	{
		userRepository.save(user);
		return user; 
	}
	
    public Long count() {

        return userRepository.count();
    }

    public boolean deleteById(String userId) throws IllegalArgumentException
    {
        userRepository.deleteById(userId);
        return true; 
    }
}