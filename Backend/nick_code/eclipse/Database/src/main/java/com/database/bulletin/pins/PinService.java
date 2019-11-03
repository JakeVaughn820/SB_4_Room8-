package com.database.bulletin.pins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the pin repository. 
 * 
 * @author Nickolas Mitchell
 */
@Service
public class PinService 
{
	/**
	 * Holds the pin repository object. 
	 */
	@Autowired
	private PinRepository pinRepository; 
	
	/**
	 * Gets all pins within the database. 
	 * 
	 * @return
	 */
	public List<Pin> getPins() 
	{
		return pinRepository.findAll();
	}
	
	/**
	 * Adds a pin to the database. 
	 * 
	 * @param pin
	 * @return
	 */
	public Pin addPin(Pin pin)
	{
		return pinRepository.save(pin);
	}
	
	/**
	 * Gets the number of pins within the database. 
	 * 
	 * @return
	 */
    public Long count() 
    {

        return pinRepository.count();
    }

    /**
     * Deletes a pin from the database. Throws IllegalArgumentException 
     * if the pin does not exist. 
     * 
     * @param pinId
     * @return
     * @throws IllegalArgumentException
     */
    public boolean deleteById(Long pinId) throws IllegalArgumentException
    {
    	pinRepository.deleteById(pinId);
        return true; 
    }
}
