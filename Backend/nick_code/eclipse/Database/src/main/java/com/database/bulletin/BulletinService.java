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
		List<Pin> bulletin = new ArrayList<>();
		bulletinRepository.findAll().forEach(bulletin::add);
		return bulletin; 
	}
	
	public void addPin(Pin bulletin)
	{
		bulletinRepository.save(bulletin); 
	}
}