package com.database.roomList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

@Entity
@Table(name="roomLists")
public class RoomList 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_list_id", foreignKey = @ForeignKey(name="room_list_id"))
	private int roomId; 
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	/**
	 * Constructor
	 * 
	 * @param roomId
	 * @param title
	 */
	public RoomList(int roomId, String title, String description)
	{
		this.roomId = roomId;  
		this.title = title; 
		this.description = description; 
	}
	
	/**
	 * Handlers
	 */
	public int getId()
	{
		return id; 
	}
	
	public int getRoomId()
	{
		return roomId;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setId(int id)
	{
		this.id = id; 
	}
	
	public void setRoomId(int roomId)
	{
		this.roomId = roomId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof RoomList))
			return false; 
		RoomList roomList = (RoomList) o;

		return this.id == roomList.id && this.title == roomList.title && this.roomId == roomList.roomId; 
	}


}
