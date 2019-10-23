package com.database.roomList.tasks;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Tasks 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="contents")
	private String contents;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.user.User.class)
	@JoinColumn(name="task_user_id", foreignKey = @ForeignKey(name = "task_user_id"))
	private int userId;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.roomList.RoomList.class)
	@JoinColumn(name="task_list_id", foreignKey = @ForeignKey(name = "task_list_id"))
	private int listId;
	
	/**
	 * Constructor
	 * 
	 * @param contents
	 * @param userId
	 */
	public Tasks(String contents, int userId, int listId)
	{ 
		this.contents = contents;
		this.userId = userId;
		this.listId = listId; 
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
	
	public int getUserId()
	{
		return userId;
	}
	
<<<<<<< HEAD
	public void setId(int id)
=======
	public int getListId()
	{
		return listId; 
	}
	public void setId(String id)
>>>>>>> nick_dev2
	{
		this.id = id; 
	}
	
	public void setContents(String contents)
	{
		this.contents = contents; 
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId; 
	}
	
	public void setListId(int listId)
	{
		this.listId = listId; 
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
		return this.id == task.id && this.contents == task.contents && this.userId == task.userId && this.listId == task.listId; 
	}
}