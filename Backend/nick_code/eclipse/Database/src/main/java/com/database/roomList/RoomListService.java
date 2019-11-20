package com.database.roomList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the roomList repository. 
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Service
public class RoomListService 
{
	/**
	 * Holds the roomList repository. 
	 */
	@Autowired
	private RoomListRepository roomListRepository; 
	
	/**
	 * Gets all roomLists in the database.
	 * 
	 * @return
	 */
	public List<RoomList> getRoomList() 
	{
		return roomListRepository.findAll();
	}
	
	/**
	 * Adds a roomList to the database. 
	 * 
	 * @param roomList
	 * @return
	 */
	public RoomList addRoomList(RoomList roomList)
	{
		return roomListRepository.save(roomList);
	}
	
	/**
	 * Gets number of roomLists in database.
	 * 
	 * @return
	 */
    public Long count() 
    {
        return roomListRepository.count();
    }

    /**
     * Deletes a roomList from the database. Throws IllegalArgumentException
     * if the roomList does not exist. 
     * 
     * @param roomList
     * @return
     * @throws IllegalArgumentException
     */
    public boolean deleteById(Long roomList) throws IllegalArgumentException
    {
    	roomListRepository.deleteById(roomList);
        return true; 
    }

	public List<RoomList> findListByRoomId(Long roomId) {
		return roomListRepository.findListByRoomId(roomId);
	}
}