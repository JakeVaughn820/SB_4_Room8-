package com.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the repository for the rooms entity. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> 
{

	Room getRoomById(Long long1);

}
