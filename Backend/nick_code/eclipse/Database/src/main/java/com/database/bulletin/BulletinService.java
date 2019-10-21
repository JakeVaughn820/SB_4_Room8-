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
	
	public List<Pin> getPins()
	{
		return bulletinRepository.findAll();
	}
	
	public void addPin(Pin bulletin)
	{
		bulletinRepository.save(bulletin); 
	}
	
    public Long count() {

        return bulletinRepository.count();
    }

    public void deleteById(String userId) {

    	bulletinRepository.deleteById(userId);;
    }
}
