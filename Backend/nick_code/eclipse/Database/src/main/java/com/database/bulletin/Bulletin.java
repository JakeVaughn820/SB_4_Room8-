package com.database.bulletin;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bulletin")
public class Bulletin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id; 
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_bulletin_id", foreignKey = @ForeignKey(name = "room_bulletin_id"))
	private String roomBulletinId;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="pin_id", foreignKey = @ForeignKey(name = "pin_id"))
	private String pinId;
	
	/**
	 * Bulletin Constructor 
	 */
	public Bulletin(String roomBulletinId, String pinId)
	{
		this.roomBulletinId = roomBulletinId;
		this.pinId = pinId; 
	}
	
	/**
	 * Handlers
	 */
	public String getBulletinId()
	{
		return id; 
	}
	
	public String getRoomId()
	{
		return roomBulletinId;
	}
	
	public String getPinId() 
	{
		return pinId; 
	}
	
	public void setBulletinId(String id)
	{
		this.id = id; 
	}
	
	public void setRoomId(String roomId)
	{
		this.roomBulletinId = roomId; 
	}
	
	public void setPinId(String pinId)
	{
		this.pinId = pinId; 
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Bulletin))
			return false; 
		Bulletin Bulletin = (Bulletin) o;
		return this.id == Bulletin.id && this.roomBulletinId == Bulletin.roomBulletinId && this.pinId == Bulletin.pinId;
	}	
}
