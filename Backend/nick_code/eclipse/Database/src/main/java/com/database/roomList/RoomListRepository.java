package com.database.roomList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.roomMembers.RoomMembers;

/**
 * This interface holds the roomLists repository.  
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Repository
public interface RoomListRepository extends JpaRepository<RoomList, Long> 
{
	@Query(value = "SELECT * FROM room_lists WHERE room_id = ?1", nativeQuery = true)
	List<RoomList> findRoomMembersByUserId(@Param("room_id") Long roomId);
	
}
