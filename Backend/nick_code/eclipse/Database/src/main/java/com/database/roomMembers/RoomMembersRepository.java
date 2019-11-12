package com.database.roomMembers;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import com.database.rooms.Rooms;

/**
 * This interface holds the roomMembers repository. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface RoomMembersRepository extends JpaRepository<RoomMembers, Long> 
{	
	@Query(value = "select u from RoomMembers u where u.user_id in :user_id", nativeQuery = true)
	List<Rooms> findRoomsByUserId(@Param("user_id") Long userId);
	
//	@Query("select i from RoomMembers i where i.id = ?1")
//	List<Rooms> getRooms(); 
}
