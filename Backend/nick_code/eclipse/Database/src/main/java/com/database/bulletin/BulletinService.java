package com.database.bulletin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulletinService implements IBulletinService
{
	@Autowired
	private BulletinRepository bulletinRepository;
	
	public List<Bulletin> findAll()
	{
		return (List<Bulletin>) bulletinRepository.findAll(); 
	}
	
	public void addBullentin(Bulletin bulletin)
	{
		bulletinRepository.save(bulletin); 
	}
}