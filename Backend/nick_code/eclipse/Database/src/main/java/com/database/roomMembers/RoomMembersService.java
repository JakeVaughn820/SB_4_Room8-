package com.database.roomMembers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMembersService 
{
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	public List<RoomMembers> getRooms()
	{
		return roomMembersRepository.findAll();
	}
	
	public void addRoom(RoomMembers roomMembers)
	{
		roomMembersRepository.save(roomMembers); 
	} 
	
	public void deleteById(String roomMembersId)
	{
		roomMembersRepository.deleteById(roomMembersId);
	}
}
