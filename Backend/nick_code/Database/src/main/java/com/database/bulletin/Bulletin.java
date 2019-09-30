package com.database.bulletin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Bulletin")
public class Bulletin 
{
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
