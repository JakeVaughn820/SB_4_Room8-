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
	
	public Rooms getRoomById(int id)
	{
		List<Rooms> temp = roomsRepository.findAll();
		Rooms toReturn = new Rooms("fakeNews");
		for(int i = 0; i < getRooms().size(); i++)
		{
			if(temp.get(i).getId() == id)
			{
				toReturn = temp.get(i); 
			}
		}
		return toReturn; 
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
