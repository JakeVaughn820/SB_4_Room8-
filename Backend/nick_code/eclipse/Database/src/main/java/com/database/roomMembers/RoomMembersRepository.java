package com.database.roomMembers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMembersRepository extends JpaRepository<RoomMembers, String> 
{
	

}
