package com.database.roomList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.database.rooms.Rooms;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * This class implements the roomList object. Each room will only have 
 * one room list. 
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Entity
@Table(name="roomLists")
public class RoomList 
{
	/**
	 * A unique Id which is automatically generated for each roomList.
	 */
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	/**
	 * Holds the room Id this list belongs to. 
	 */
	@ManyToOne(targetEntity=com.database.rooms.Rooms.class)
	@JoinColumn(name="room_id", foreignKey = @ForeignKey(name="id"))
	private Rooms room; 

	
	/**
	 * Holds the title of this list. 
	 */
	@Column(name="title")
	private String title;
	
	/**
	 * Holds the description of this list. 
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * Default Constructor
	 */
	public RoomList()
	{}
	
	/**
	 * Constructor which sets the below fields.
	 * 
	 * @param roomId
	 * @param title
	 */
	public RoomList(Rooms room, String title, String description)
	{
		this.room = room;  
		this.title = title; 
		this.description = description; 
	}
	
	/**
	 * Gets the roomList Id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
	}
	
	/**
	 * Gets the room Id. 
	 * 
	 * @return
	 */
	public Rooms getRoom()
	{
		return room;
	}
	
	/**
	 * Gets the roomList title. 
	 * 
	 * @return
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Gets the roomLists description.
	 * 
	 * @return
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * Sets the roomList's Id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
	}
	
	/**
	 * Sets the room Id. 
	 * 
	 * @param roomId
	 */
	public void setRoom(Rooms room)
	{
		this.room = room;
	}
	
	/**
	 * Sets the roomList's title. 
	 * 
	 * @param title
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * Sets the roomList's description. 
	 * 
	 * @param description
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
//	/**
//	 * Gets all tasks belonging to this list. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getTasks()
//	{
//		return tasks; 
//	}
	
//	/**
//	 * Adds one task to this list. 
//	 * 
//	 * @param task
//	 */
//	public void setTask(Long task)
//	{
//		this.tasks.add(task);
//	}
	
	/**
	 * Checks if two roomLists are the same. 
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof RoomList))
			return false; 
		RoomList roomList = (RoomList) o;

		return this.id == roomList.id && this.title.equals(roomList.title) 
				&& this.room == roomList.room && this.description.equals(roomList.description);
				//&& this.tasks.equals(roomList.tasks); 
	}


}
