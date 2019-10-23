package com.database.user;

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