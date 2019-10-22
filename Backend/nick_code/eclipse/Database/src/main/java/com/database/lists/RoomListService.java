package com.database.lists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomListService 
{
	@Autowired
	private RoomListRepository roomListRepository; 
	
	public List<RoomList> getPins() 
	{
		return roomListRepository.findAll();
	}
	
	public RoomList addPin(RoomList roomList)
	{
		return roomListRepository.save(roomList);
	}
	
    public Long count() 
    {

        return roomListRepository.count();
    }

    public boolean deleteById(String pinId) throws IllegalArgumentException
    {
    	roomListRepository.deleteById(pinId);
        return true; 
    }
}