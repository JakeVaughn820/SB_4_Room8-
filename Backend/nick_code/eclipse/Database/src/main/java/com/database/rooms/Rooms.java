package com.database.rooms;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
//	/**
//	 * Holds user Id's for every user in this room. 
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> users;

//	/**
//	 * Holds this rooms Schedule Id.
//	 */
//	@OneToOne(fetch = FetchType.LAZY)
//	private Long schedule;

//	/**
//	 * Holds this rooms Bulletin Id.
//	 */
	//@OneToOne(fetch = FetchType.LAZY)
	//private Long bulletin;
	
//	/**
//	 * Holds this rooms List Id.
//	 */
//	@OneToOne(fetch = FetchType.LAZY)
//	private Long list;

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

//	/**
//	 * Adds one user to this room. 
//	 * 
//	 * @param user
//	 */
//	public void setUser(Long user)
//	{
//		users.add(user); 
//	}
	
//	/**
//	 * Returns the users in this room.  
//	 * 
//	 * @return
//	 */
//	public List<Long> getUsers()
//	{
//		return users; 
//	}
	
//	/**
//	 * Returns the schedule associated with this room. 
//	 * 
//	 * @return
//	 */
//	public Long getSchedule()
//	{
//		return schedule;
//	}
//	
//	/**
//	 * Adds a schedule to this room. 
//	 * 
//	 * @param schedule
//	 */
//	public void setSchedule(Long schedule)
//	{
//		this.schedule = schedule; 
//	}
	
//	/**
//	 * Returns the bulletin associated with this room.
//	 * 
//	 * @return
//	 */
//	public Long getBulletin()
//	{
//		return bulletin;
//	}
	
//	/**
//	 * Adds a bulletin to this room. 
//	 * 
//	 * @param bulletin
//	 */
//	public void setBulletin(Long bulletin)
//	{
//		this.bulletin = bulletin;
//	}
	
//	/**
//	 * Returns the list associated with this room. 
//	 * 
//	 * @return
//	 */
//	public Long getList()
//	{
//		return list; 
//	}
//	
//	/**
//	 * Adds a list to this room. 
//	 * 
//	 * @param list
//	 */
//	public void setList(Long list)
//	{
//		this.list = list; 
//	}
	
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
			//	&& this.schedule == room.schedule
			//	 && this.list == room.list;
			//&& this.bulletin == room.bulletin
	}

}
