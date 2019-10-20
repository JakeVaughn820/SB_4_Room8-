package com.database.bulletin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulletinService
{
	@Autowired
	private BulletinRepository bulletinRepository;
	
	public Iterable<Bulletin> fetchAll() throws Exception
	{
		return bulletinRepository.findAll(); 
	}
	
	public List<String> findContentsByRoomId(String roomId)
	{
		return bulletinRepository.findContentsByRoomId(roomId);
	}
	
	
	
//	@Query(value="select first_name, last_name from Users u where u.user_id =:userId", nativeQuery=true)
//	List<Object[]> getUserFullNameById(@Param("userId") String userId);
//	
//	
//	public List<Pin> getPins()
//	{
//		return bulletinRepository.findAll();
//	}
//	
//	public void addPin(Pin bulletin)
//	{
//		bulletinRepository.save(bulletin); 
//	}
//	
//    public Long count() {
//
//        return bulletinRepository.count();
//    }
//
//    public void deleteById(String userId) {
//
//    	bulletinRepository.deleteById(userId);;
//    }
}