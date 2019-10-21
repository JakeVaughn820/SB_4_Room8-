package com.database.bulletin;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@Column(name="bulletin_contents")
	private String bulletinContents;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_bulletin_id", foreignKey = @ForeignKey(name = "room_bulletin_id"))
	private String roomBulletinId;
	
	/**
	 * Bulletin Constructor 
	 */
	public Bulletin(String id, String bulletinContents, String roomBulletinId)
	{
		if(id.length() <= 49)
			this.id = id;
		if(bulletinContents.length() <= 98)
			this.bulletinContents = bulletinContents;
		if(roomBulletinId.length() <= 49)
			this.roomBulletinId = roomBulletinId; 
	}
	
	public void setId(String id)
	{
		if(id.length() <= 49)
			this.id = id; 
	}
	
	public void setBulletinContents(String bulletinContents)
	{
		if (bulletinContents.length() <= 98)
			this.bulletinContents = bulletinContents;
	}
	
	public void setRoomBulletinId(String roomBulletinId)
	{
		if (roomBulletinId.length() <= 49)
			this.roomBulletinId = roomBulletinId;
	}
	
	public String getBulletinId()
	{
		return id;
	}
	
	public String getBulletinContents()
	{
		return bulletinContents; 
	}
	
	public String getRoomId()
	{	
		return roomBulletinId;
	}
	
	
}
