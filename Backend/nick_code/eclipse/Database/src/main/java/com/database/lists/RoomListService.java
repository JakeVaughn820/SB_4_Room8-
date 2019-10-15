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
		return listRepository.findAll();
	}
	
	public void addList(RoomList roomList)
	{
		listRepository.save(roomList); 
	}
	
    public Long count() {

        return listRepository.count();
    }

    public void deleteById(String userId) {

    	listRepository.deleteById(userId);;
    }
}