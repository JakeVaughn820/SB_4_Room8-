package com.database.bulletin.pins;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pin")
public class Pin 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="pin_user_id")
	private String userId; 
	
	/**
	 * Constructor
	 * 
	 * @param contents
	 * @param userId
	 */
	public Pin(String contents, String userId)
	{ 
		this.contents = contents;
		this.userId = userId; 
	}
	
	/**
	 * Handlers
	 * 
	 */
	public String getId()
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
	
	public void setId(String id)
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
		if(!(o instanceof Pin))
			return false; 
		Pin pin = (Pin) o;
		return this.id == pin.id && this.contents == pin.contents && this.userId == pin.userId; 
	}
}