package com.database.roomMembers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.rooms.Rooms;
import com.database.rooms.RoomsRepository;


@Service
public class RoomMembersService 
{
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	@Autowired private RoomsRepository roomsRepository;
	
	public List<RoomMembers> getRoomMembers()
	{
		return roomMembersRepository.findAll();
	}
	
	public List<Rooms> getRoomsByUsersId(int userId)
	{
		List <Rooms> temp = roomsRepository.findAll();
		List <Rooms> toReturn = new ArrayList<Rooms>();
		
		for(Rooms i : temp) {
			if(i.getId() == userId)
				toReturn.add(i);
		}
		return toReturn;
	}
	
	public RoomMembers addRoomMembers(RoomMembers roomMembers)
	{
		roomMembersRepository.save(roomMembers); 
		return roomMembers;
	} 
	
	public boolean deleteById(String roomMembersId) throws IllegalArgumentException
	{
		roomMembersRepository.deleteById(roomMembersId);
		return true;
	}
}
