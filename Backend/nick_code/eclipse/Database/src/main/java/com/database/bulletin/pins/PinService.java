package com.database.bulletin.pins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinService 
{
	@Autowired
	private PinRepository pinRepository; 
	
	public List<Pin> getPins() 
	{
		return pinRepository.findAll();
	}
	
	public Pin addPin(Pin pin)
	{
		return pinRepository.save(pin);
	}
	
    public Long count() 
    {

        return pinRepository.count();
    }

    public boolean deleteById(String pinId) throws IllegalArgumentException
    {
    	pinRepository.deleteById(pinId);
        return true; 
    }
}
