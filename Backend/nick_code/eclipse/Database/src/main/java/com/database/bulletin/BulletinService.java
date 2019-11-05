package com.database.bulletin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the bulletin repository. 
 * 
 * @author Nickolas Mitchell
 */
@Service
public class BulletinService
{
	/**
	 * Holds the bulletin repository object. 
	 */
	@Autowired
	private BulletinRepository bulletinRepository;
	
	/**
	 * Gets all bulletins within the database. 
	 * 
	 * @return
	 */
	public List<Bulletin> getBulletin()
	{
		return bulletinRepository.findAll();
	}
	
	/**
	 * Adds one bulletin to the database. 
	 * 
	 * @param bulletin
	 * @return
	 */
	public Bulletin addBulletin(Bulletin bulletin)
	{
		return bulletinRepository.save(bulletin); 
	}
	
	/**
	 * Gets the number of bulletins in the database. 
	 * 
	 * @return
	 */
    public Long count() {

        return bulletinRepository.count();
    }

    /**
     * Deletes a bulletin from the database. Throws IllegalArgumentException
     * if the bulletin does not exist. 
     * 
     * @param bulletinId
     * @return
     * @throws IllegalArgumentException
     */
    public boolean deleteById(Long bulletinId) throws IllegalArgumentException 
    {
    	bulletinRepository.deleteById(bulletinId);
    	return true; 
    }
}
