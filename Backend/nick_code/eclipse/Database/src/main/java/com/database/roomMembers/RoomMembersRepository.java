package com.database.roomMembers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the roomMembers repository. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface RoomMembersRepository extends JpaRepository<RoomMembers, Long> 
{
	

}
