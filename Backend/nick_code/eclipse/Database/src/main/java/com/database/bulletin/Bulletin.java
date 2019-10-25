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
	private Integer id; 
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_bulletin_id", foreignKey = @ForeignKey(name = "room_bulletin_id"))
	private Integer roomBulletinId;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="pin_id", foreignKey = @ForeignKey(name = "pin_id"))
	private Integer pinId;
	
	/**
	 * Bulletin Constructor 
	 */
	public Bulletin(Integer roomBulletinId, Integer pinId)
	{
		this.roomBulletinId = roomBulletinId;
		this.pinId = pinId; 
	}
	
	/**
	 * Handlers
	 */
	public Integer getBulletinId()
	{
		return id; 
	}
	
	public Integer getRoomId()
	{
		return roomBulletinId;
	}
	
	public Integer getPinId() 
	{
		return pinId; 
	}
	
	public void setBulletinId(Integer id)
	{
		this.id = id; 
	}
	
	public void setRoomId(Integer roomId)
	{
		this.roomBulletinId = roomId; 
	}
	
	public void setPinId(Integer pinId)
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
