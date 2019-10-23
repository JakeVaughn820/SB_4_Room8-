package com.database.schedule.events;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="events")
public class Events 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="events_user_id")
	private String userId; 
	
	/**
	 * Constructor
	 * 
	 * @param contents
	 * @param userId
	 */
	public Events(String contents, String userId)
	{ 
		this.contents = contents;
		this.userId = userId; 
	}
	
	/**
	 * Handlers
	 * 
	 */
	public int getId()
	{
		return id; 
	}
	
	public String getContents()
	{
		return contents; 
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setId(int id)
	{
		this.id = id; 
	}
	
	public void setContents(String contents)
	{
		this.contents = contents; 
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId; 
	}
	
	@Override 
	public String toString() 
	{ 
        return String.format("User: " + userId + "Contents: " + contents); 
    } 
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Events))
			return false; 
		Events event = (Events) o;
		return this.id == event.id && this.contents == event.contents && this.userId == event.userId; 
	}

}
