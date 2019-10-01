package com.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TestTable")
public class testList 
{
	@Id
	private String name; 
	
	
	public String getlist()
	{
		return name; 
	}
	
	public void addList(String name)
	{
		this.name = name; 
	}
}
