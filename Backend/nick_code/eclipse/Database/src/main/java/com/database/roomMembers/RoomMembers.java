package com.database.roomMembers;


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
@Table(name="room_members")
public class RoomMembers 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id; 
	
	@ManyToOne(targetEntity = com.database.user.User.class)
	@JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "user_id"))
	private Integer userId;
	
	@ManyToOne(targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_id", foreignKey = @ForeignKey(name = "room_id"))
	private Integer roomId;
	
	public RoomMembers(Integer userId, Integer roomId)
	{
		this.userId = userId;
		this.roomId = roomId; 
	}
	
	public void setId(Integer id)
	{
		this.id = id; 
	}
	
	public void setUserId(Integer userId)
	{
		this.userId = userId; 
	}
	
	public void setRoomId(Integer roomId)
	{
		this.roomId = roomId; 
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public Integer getUserId()
	{
		return this.userId; 
	}
	
	public Integer getRoomId()
	{
		return this.roomId; 
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof RoomMembers))
			return false; 
		RoomMembers RoomMembers = (RoomMembers) o;
		return this.id == RoomMembers.id && this.userId == RoomMembers.userId && this.roomId == RoomMembers.roomId;
	}
}
