package com.database.roomMembers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the roomMembers repository. 
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Repository
public interface RoomMembersRepository extends JpaRepository<RoomMembers, Long> 
{		
	/**
	 * Query which returns all roomMembers with a given userId. 
	 * 
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT * FROM room_members WHERE user_id = ?1", nativeQuery = true)
	List<RoomMembers> findRoomMembersByUserId(@Param("user_id") Long userId);
	
	/**
	 * Query which returns all roomMembers with a given roomId. 
	 * 
	 * @param roomId
	 * @return
	 */
	@Query(value = "SELECT * FROM room_members WHERE room_id = ?1", nativeQuery = true)
	List<RoomMembers> findRoomMembersByRoomId(@Param("room_id") Long roomId);
}
