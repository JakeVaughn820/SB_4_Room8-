package com.database.lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="UserLists")
public class UserLists 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	
	@Column(name="Name")
	private String ListName;
	
	@Column(name="Contents of List")
	private String ListContents;  
	
	public UserLists(String name, String contents)
	{ 
		this.ListName = name; 
		this.ListContents = contents; 
	}
	
	//Id handlers
	public String getId()
	{
		return Id; 
	}
	
	public void setId(String Id)
	{
		this.Id = Id; 
	}
	
	
	//Name handlers
	public String getListName()
	{
		return ListName; 
	}
	
	public void setListName(String name)
	{
		this.ListName = name; 
	}
	
	
	//Content handlers
	public String getListContents()
	{
		return ListContents; 
	}
	
	public void setListContents(String contents)
	{
		this.ListContents = contents; 
	}

}
