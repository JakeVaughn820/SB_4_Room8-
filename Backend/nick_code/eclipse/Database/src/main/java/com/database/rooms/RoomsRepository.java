package com.database.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the repository for the rooms entity. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> 
{

	Rooms getRoomById(Long long1);

}
