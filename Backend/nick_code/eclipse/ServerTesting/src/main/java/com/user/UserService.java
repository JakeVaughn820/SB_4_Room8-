package com.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the users repository. 
 * 
 * @author Nickolas Mitchell
 */
@Service
public class UserService 
{
	/**
	 * The user repository object. 
	 */
	@Autowired
	private UserRepository userRepository; 
	
	/**
	 * Returns all users within the database. 
	 * 
	 * @return
	 */
	public List<User> getUsers() 
	{
		return userRepository.findAll();
	}
	
	/**
	 * Returns a specific user by searching for their id. If the 
	 * user is not found a null user object is returned.  
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(Long userId)
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
	
	/**
	 * Adds a user to the database. 
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user)
	{
		userRepository.save(user);
		return user; 
	}
	
	/**
	 * Returns number of users within the database. 
	 * 
	 * @return
	 */
    public Long count() {

        return userRepository.count();
    }

    /**
     * Deletes user from the database. Throws IllegalArgumentException if
     * the user does not exist.
     * 
     * @param userId
     * @return
     * @throws IllegalArgumentException
     */
    public boolean deleteById(Long userId) throws IllegalArgumentException
    {
        userRepository.deleteById(userId);
        return true; 
    }
}