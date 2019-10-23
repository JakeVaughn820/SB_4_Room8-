package com.database.roomMembers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMembersService 
{
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	public List<RoomMembers> getRoomMembers()
	{
		return roomMembersRepository.findAll();
	}
	
	public List<RoomMembers> getRoomsByUserId(int userId)
	{
		List<RoomMembers> roomMembers = roomMembersRepository.findAll();
		List<RoomMembers> toReturn = new ArrayList<RoomMembers>(); 
		for(int i = 0; i < roomMembers.size(); i++)
		{
			if(roomMembers.get(i).getUserId() == userId)
				toReturn.add(roomMembers.get(i)); 
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
