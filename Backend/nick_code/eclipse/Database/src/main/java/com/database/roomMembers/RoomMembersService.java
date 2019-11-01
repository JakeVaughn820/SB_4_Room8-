package com.database.roomMembers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.rooms.Rooms;
import com.database.rooms.RoomsRepository;
import com.database.user.User;
import com.database.user.UserRepository;


@Service
public class RoomMembersService 
{
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	@Autowired private RoomsRepository roomsRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	public List<RoomMembers> getRoomMembers()
	{
		return roomMembersRepository.findAll();
	}
	
	public List<User> getUsersByUserId(Integer userId)
	{
		List <RoomMembers> temp = roomMembersRepository.findAll();
		List <User> toReturn = new ArrayList<User>();
		
		for(RoomMembers i : temp) 
		{
			if(i.getUserId() == userId)
			{
				toReturn.add(userRepository.getUserById(i.getUserId())); 
			}
		}
		return toReturn;
	}
	
	public List<Rooms> getRoomsByRoomId(Integer roomId)
	{
		List <RoomMembers> temp = roomMembersRepository.findAll();
		List <Rooms> toReturn = new ArrayList<Rooms>();
		
		for(RoomMembers i : temp) 
		{
			if(i.getRoomId() == roomId)
			{
				toReturn.add(roomsRepository.getRoomById(i.getRoomId())); 
			}
		}
		return toReturn;
	}
	
	public List<Rooms> getRoomsByUserId(Integer userId)
	{
		List <RoomMembers> temp = roomMembersRepository.findAll();
		List <Rooms> toReturn = new ArrayList<Rooms>();
		
		for(RoomMembers i : temp) 
		{
			if(i.getUserId() == userId)
			{
				toReturn.add(roomsRepository.getRoomById(i.getRoomId())); 
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
