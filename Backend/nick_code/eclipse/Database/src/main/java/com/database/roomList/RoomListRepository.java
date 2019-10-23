package com.database.roomList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomListRepository extends JpaRepository<RoomList, String> 
{
	
	
}
