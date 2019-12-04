package com.database.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the repository for the uses entity.
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Query which finds a User with the provided userId.
	 */
	@Query("select i from User i where i.id = ?1")
	Optional<User> findById(Long userId);
}
