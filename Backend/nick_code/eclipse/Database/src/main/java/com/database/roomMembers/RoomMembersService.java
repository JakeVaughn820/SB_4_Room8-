package com.database.roomMembers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.rooms.Rooms;
import com.database.rooms.RoomsRepository;
import com.database.user.User;
import com.database.user.UserRepository;

/**
 * This class implements the roomMembers repository. 
 * 
 * @author Nickolas Mitchell
 */
@Service
public class RoomMembersService 
{
	/**
	 * Holds the roomMembers repository object. 
	 */
	@Autowired
	private RoomMembersRepository roomMembersRepository; 
	
	/**
	 * Holds the rooms repository object. 
	 */
	@Autowired private RoomsRepository roomsRepository;
	
	/**
	 * Holds the user repository object. 
	 */
	@Autowired
	private UserRepository userRepository; 
	
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
	 * Returns users 
	 * 
	 * @param userId
	 * @return
	 */
	public List<User> getUsersByUserId(Long userId)
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
	
	/**
	 * Returns the room based on roomId. There should only be one 
	 * relevant room. 
	 * 
	 * @param roomId
	 * @return
	 */
	public Rooms getRoomByRoomId(Long roomId)
	{
		List <RoomMembers> temp = roomMembersRepository.findAll();
		Rooms toReturn = new Rooms();
		
		for(RoomMembers i : temp) 
		{
			if(i.getRoomId() == roomId)
			{
				toReturn = (roomsRepository.getRoomById(i.getRoomId())); 
			}
		}
		return toReturn;
	}
	
//	public RoomMembers getRoomMember(Integer roomId, Integer userId)
//	{
//		List <RoomMembers> temp = roomMembersRepository.findAll();
//		RoomMembers toReturn = new RoomMembers(); 
//		for(RoomMembers i : temp)
//		{
//			if(i.getRoomId(roomId).equals(i.getUserId(userId)))
//			{
//				toReturn = roomMembersRepository.get
//			}
//		}
//	}
	
	/**
	 * Returns all rooms a particular user is in 
	 * using the users id. 
	 * 
	 * @param userId
	 * @return
	 */
	public List<Rooms> getRoomsByUserId(Long userId)
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
	
	/**
	 * Adds a roomMembers object. 
	 * 
	 * @param roomMembers
	 * @return
	 */
	public RoomMembers addRoomMembers(RoomMembers roomMembers)
	{
		roomMembersRepository.save(roomMembers); 
		return roomMembers;
	} 
	
	/**
	 * Deletes a roomMembers object from the database. Throws IllegalArgumentException 
	 * if the roomMembers object does not exist. 
	 * 
	 * @param roomMembersId
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean deleteById(Long roomMembersId) throws IllegalArgumentException
	{
		roomMembersRepository.deleteById(roomMembersId);
		return true;
	}
}
