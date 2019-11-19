package com.database.roomList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the roomLists repository.  
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface RoomListRepository extends JpaRepository<RoomList, Long> 
{
	
	
}
