package com.database.lists;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomListService 
{
	@Autowired
	private RoomListRepository listRepository; 
	
	public List<RoomList> getLists() 
	{
		List<RoomList> roomList = new ArrayList<>();
		listRepository.findAll().forEach(roomList::add);
		return roomList;
		
	}
	
	public void addList(RoomList roomList)
	{
		listRepository.save(roomList); 
	}
}