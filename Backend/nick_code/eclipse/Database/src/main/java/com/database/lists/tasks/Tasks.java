package com.database.lists.tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Tasks 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="task_user_id")
	private String userId; 
	
	/**
	 * Constructor
	 * 
	 * @param contents
	 * @param userId
	 */
	public Tasks(String contents, String userId)
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
		if(!(o instanceof Tasks))
			return false; 
		Tasks task = (Tasks) o;
		return this.id == task.id && this.contents == task.contents && this.userId == task.userId; 
	}
}