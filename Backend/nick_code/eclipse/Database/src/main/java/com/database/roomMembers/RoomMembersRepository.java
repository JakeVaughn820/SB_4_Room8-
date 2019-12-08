package com.database.roomMembers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import com.database.user.User;

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
	
	/**
	 * Query which updates the user role of a provided user. 
	 * 
	 * @param userRole
	 * @param userId
	 * @return
	 */
	@Transactional
	@Modifying
	@Query(value = "update room_members rm set rm.user_role = ?1 where rm.user_id = ?2", nativeQuery = true)
	void updateUserRole(@Param("user_role") String userRole, @Param("user_id") Long userId);
}
