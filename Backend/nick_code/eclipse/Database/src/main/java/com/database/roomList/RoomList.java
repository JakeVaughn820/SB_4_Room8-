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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name="roomLists")
public class RoomList 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id; 
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_list_id", foreignKey = @ForeignKey(name="room_list_id"))
	private String roomId; 
	
	@Column(name="contents")
	private String contents; 
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.roomList.tasks.Tasks.class)
	@JoinColumn(name="list_task_id", foreignKey = @ForeignKey(name="list_task_id"))
	private String taskId;
	
	/**
	 * Constructor
	 * 
	 * @param roomId
	 * @param contents
	 */
	public RoomList(String roomId, String contents)
	{
		this.roomId = roomId;  
		this.contents = contents; 
	}
	
	/**
	 * Handlers
	 */
	public String getId()
	{
		return id; 
	}
	
	public String getRoomId()
	{
		return roomId;
	}
	
	public String getTaskId()
	{
		return taskId; 
	}
	
	public void setId(String id)
	{
		this.id = id; 
	}
	
	public void setRoomId(String roomId)
	{
		this.roomId = roomId;
	}
	
	public void setTaskId(String taskId)
	{
		this.taskId = taskId; 
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof RoomList))
			return false; 
		RoomList roomList = (RoomList) o;
		return this.id == roomList.id && this.contents == roomList.contents && this.roomId == roomList.roomId && this.taskId == roomList.taskId; 
	}

}
