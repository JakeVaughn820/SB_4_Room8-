package com.database.roomList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomListService 
{
	@Autowired
	private RoomListRepository roomListRepository; 
	
	public List<RoomList> getRoomList() 
	{
		return roomListRepository.findAll();
	}
	
	public RoomList addRoomList(RoomList roomList)
	{
		return roomListRepository.save(roomList);
	}
	
    public Long count() 
    {
        return roomListRepository.count();
    }

    public boolean deleteById(String roomList) throws IllegalArgumentException
    {
    	roomListRepository.deleteById(roomList);
        return true; 
    }
}