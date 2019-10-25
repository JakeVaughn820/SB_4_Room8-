package com.database.roomMembers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.database.rooms.Rooms;

@Service
public class RoomMembersService 
{
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	public List<RoomMembers> getRoomMembers()
	{
		return roomMembersRepository.findAll();
	}
	
	public List<Integer> getRoomsByUsersId(int userId)
	{
		List <RoomMembers> temp = roomMembersRepository.findAll();
		List <Integer> toReturn = new ArrayList<Integer>();
		for(int i = 0; i < ((RoomMembersService) temp).getRoomMembers().size(); i++)
		{
			if(temp.get(i).getUserId() == userId)
			{
				toReturn.add(temp.get(i).getUserId());
			}
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
