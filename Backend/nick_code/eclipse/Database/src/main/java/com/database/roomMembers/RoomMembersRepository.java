package com.database.roomMembers;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import com.database.rooms.Rooms;
import com.database.user.User;

/**
 * This interface holds the roomMembers repository. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface RoomMembersRepository extends JpaRepository<RoomMembers, Long> 
{	//"SELECT * FROM RoomMembers WHERE user_id = ?"
	//select * from RoomMembers u where u.user_id in :user_id
	
	
	@Query(value = "SELECT * FROM room_members WHERE user_id = ?1", nativeQuery = true)
	List<RoomMembers> findRoomMembersByUserId(@Param("user_id") Long userId);
	
	@Query(value = "SELECT * FROM room_members WHERE room_id = ?1", nativeQuery = true)
	List<RoomMembers> findRoomMembersByRoomId(@Param("room_id") Long roomId);
	
//	@Query("select i from RoomMembers i where i.id = ?1")
//	List<Rooms> getRooms(); 
}
