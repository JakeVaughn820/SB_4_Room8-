package com.database.bulletin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulletinService
{
	@Autowired
	private BulletinRepository bulletinRepository;
	
	public List<Bulletin> getBulletin()
	{
		return bulletinRepository.findAll();
	}
	
	public Bulletin addBulletin(Bulletin bulletin)
	{
		return bulletinRepository.save(bulletin); 
	}
	
    public Long count() {

        return bulletinRepository.count();
    }

    public boolean deleteById(String userId) throws IllegalArgumentException 
    {
    	bulletinRepository.deleteById(userId);
    	return true; 
    }
}
