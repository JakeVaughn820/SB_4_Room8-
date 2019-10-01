package com.database.bulletin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RoomBulletin")
public class Bulletin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String BulletinId; 
	
	@Column(name="Contents")
	private String BullentinContents; 
	
	public String getBullentinContents()
	{
		return BullentinContents; 
	}
	
	public String updateBullentin(String content)
	{
		BullentinContents = content; 
		return content; 
	}
}
