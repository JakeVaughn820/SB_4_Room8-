package com.database.roomMembers;

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
	
	public RoomMembers addRoomMembers(RoomMembers roomMembers)
	{
		roomMembersRepository.save(roomMembers); 
		return roomMembers;
	} 
	
	public void deleteById(String roomMembersId)
	{
		roomMembersRepository.deleteById(roomMembersId);
	}
}
