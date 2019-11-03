package com.database.rooms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the rooms repository. 
 * 
 * @author Nickolas Mitchell
 */
@Service
public class RoomsService 
{
	/**
	 * Holds the rooms repository object. 
	 */
	@Autowired
	private RoomsRepository roomsRepository; 
	
	/**
	 * Returns all of the rooms within the database. 
	 * 
	 * @return
	 */
	public List<Rooms> getRooms()
	{
		return roomsRepository.findAll();
	}
	
	/**
	 * Returns a room given its Id. Returns null if 
	 * room doesn't exist. 
	 * 
	 * @param roomId
	 * @return
	 */
	public Rooms getRoomById(Long roomId)
	{
		List<Rooms> temp = new ArrayList<Rooms>();
		Rooms toReturn = null; 
		for(Rooms i : temp)
		{
			if(i.getId().equals(roomId))
			{
				toReturn = i; 
				break; 
			}
		}
		return toReturn; 
	}
	
	/**
	 * Adds a room to the database. 
	 * 
	 * @param room
	 * @return
	 */
	public Rooms addRoom(Rooms room)
	{
		roomsRepository.save(room);
		return room; 
	} 
	
	/**
	 * Returns the number of rooms within the database. 
	 * 
	 * @return
	 */
    public Long count() {

        return roomsRepository.count();
    }
	
    /**
     * Deletes room from database. Throws IllegalArgumentException 
     * if room does not exist. 
     * 
     * @param roomId
     * @return
     * @throws IllegalArgumentException
     */
	public boolean deleteById(Long roomId) throws IllegalArgumentException
	{
		roomsRepository.deleteById(roomId);
		return true;
	}

}
