package com.room;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.user.User;


/**
 * This class implements the rooms entity. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="rooms")
public class Room 
{
	/**
	 * A unique Id which is automatically generated for each room. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Long id; 
	
	/**
	 * Title of the room. 
	 */
	@Column(name="title", nullable = false)
	public String title;
	
	@ManyToMany(mappedBy = "rooms", cascade=CascadeType.REMOVE)
    private List<User> users;

	/**
	 * Default Constructor.
	 */
	public Room()
	{
		
	}
	
	/**
	 * Constructor which sets the room title. 
	 * 
	 * @param title
	 */
	public Room(String title)
	{
		this.title = title;
	}
	
	/**
	 * Sets the room Id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * Sets the room name. 
	 * 
	 * @param roomName
	 */
	public void setRoomName(String roomName)
	{
		this.title = roomName; 
	}
	
	/**
	 * Returns the room Id.
	 * 
	 * @return
	 */
	public Long getId()
	{
		return this.id; 
	}
	
	/**
	 * Returns the rooms name. 
	 * 
	 * @return
	 */
	public String getTitle()
	{
		return this.title; 
	}
	
	/**
	 * Sets the users in this room. 
	 * 
	 * @param users
	 */
	public void setUsers(List<Long> users)
	{
		for(Long i : users)
		{
			users.add(i); 
		}
	}
	
	/**
	 * Checks if two rooms are equal. 
	 */
	@Override 
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Room))
			return false; 
		Room room = (Room) o;
		return this.id == room.id && this.title.equals(room.title);
	}

}
