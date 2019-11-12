package com.database.rooms;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class implements the rooms entity. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="rooms")
public class Rooms 
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
	@Column(name="title")
	public String title;

	/**
	 * Default Constructor.
	 */
	public Rooms()
	{
		
	}
	
	/**
	 * Constructor which sets the room title. 
	 * 
	 * @param title
	 */
	public Rooms(String title)
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
	 * Checks if two rooms are equal. 
	 */
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
