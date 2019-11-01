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
	public Integer id; 
	
	@Column(name="title")
	public String title;
	
	public Rooms()
	{
		
	}
	
	public Rooms(String roomName)
	{
		this.title = roomName; 
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void setRoomName(String roomName)
	{
		this.title = roomName; 
	}
	
	public Integer getId()
	{
		return this.id; 
	}
	
	public String getTitle()
	{
		return this.title; 
	}
	
	@Override 
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Rooms))
			return false; 
		Rooms room = (Rooms) o;
		return this.id == room.id && this.title.equals(room.title);
	}

}
