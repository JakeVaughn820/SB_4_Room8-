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
	private int id; 
	
	@Column(name="room_name")
	private String roomName;
	
	public Rooms(String roomName)
	{
		this.roomName = roomName; 
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setRoomName(String roomName)
	{
		this.roomName = roomName; 
	}
	
	public int getId()
	{
		return this.id; 
	}
	
	public String getRoomName()
	{
		return this.roomName; 
	}
	
	@Override 
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Rooms))
			return false; 
		Rooms room = (Rooms) o;
		return this.id == room.id && this.roomName == room.roomName;
	}

}
