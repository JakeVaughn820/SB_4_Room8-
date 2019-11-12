package com.database.roomMembers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.rooms.Rooms;
//import com.database.rooms.RoomsRepository;


/**
 * This class implements the roomMembers repository. 
 * 
 * @author Nickolas Mitchell
 */
@Service
public class RoomMembersService 
{
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	//@Autowired private RoomsRepository roomsRepository;
	
	/**
	 * Returns all roomMembers objects. 
	 * 
	 * @return
	 */
	public List<RoomMembers> getRoomMembers()
	{
		return roomMembersRepository.findAll();
	}
	
	/**
	 * Adds a roomMembers object to the database. 
	 * 
	 * @param roomMembers
	 * @return
	 */
	public RoomMembers addRoomMembers(RoomMembers roomMembers)
	{
		roomMembersRepository.save(roomMembers); 
		return roomMembers;
	}
	

	public List<Rooms> findRoomsByUserId(Long userId)
	{
		return roomMembersRepository.findRoomsByUserId(userId); 
	}
	
//	List<Rooms> getRooms()
	{
		
	}

	
	/**
	 * Deletes a roomMembers object from the database.
	 * 
	 * @param roomMembersId
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deleteById(Long roomMembersId)
	{
		roomMembersRepository.deleteById(roomMembersId);
	}
}
