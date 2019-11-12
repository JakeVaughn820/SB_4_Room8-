package com.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the repository for the uses entity. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{

	User getUserById(Long long1);
	
	
}
