package com.database.bulletin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinRepository extends JpaRepository<Bulletin, String> 
{
	List<String> findContentsByRoomId(String roomId); 
	
	void addBulletinContent(String bulletinContent);

}
