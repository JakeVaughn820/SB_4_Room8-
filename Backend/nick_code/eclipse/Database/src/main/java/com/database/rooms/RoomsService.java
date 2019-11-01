package com.database.rooms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomsService 
{
	@Autowired
	private RoomsRepository roomsRepository; 
	
	public List<Rooms> getRooms()
	{
		return roomsRepository.findAll();
	}
	
	public Rooms getRoomById(Integer roomId)
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
	
	public Rooms addRoom(Rooms room)
	{
		roomsRepository.save(room);
		return room; 
	} 
	
    public Long count() {

        return roomsRepository.count();
    }
	
	public boolean deleteById(String roomId) throws IllegalArgumentException
	{
		roomsRepository.deleteById(roomId);
		return true;
	}

}
