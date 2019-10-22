package com.database.rooms;

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
	
	public Rooms addRoom(Rooms room)
	{
		roomsRepository.save(room);
		return room; 
	} 
	
    public Long count() {

        return roomsRepository.count();
    }
	
	public void deleteById(String roomId)
	{
		roomsRepository.deleteById(roomId);
	}

}
