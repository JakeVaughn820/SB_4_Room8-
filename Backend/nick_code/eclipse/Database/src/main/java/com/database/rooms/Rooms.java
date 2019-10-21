package com.database.rooms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Rooms 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id; 
	
	@Column(name="room_name")
	private String roomName;
	
	public Rooms(String id, String roomName)
	{
		if (id.length() <= 49)
			this.id = id;
		if (roomName.length() <= 49)
			this.roomName = roomName; 
	}
	
	public void setId(String id)
	{
		if (id.length() <= 49)
			this.id = id;
	}
	
	public void setRoomName(String roomName)
	{
		if (roomName.length() <= 49)
			this.roomName = roomName; 
	}
	
	public String getId()
	{
		return this.id; 
	}
	
	public String getRoomName()
	{
		return this.roomName; 
	}

}
