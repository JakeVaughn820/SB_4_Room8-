package com.database.tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Task")
public class Task 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="Title")
	private String Title;
	
	@Column(name="")
	private String ListContents;  
	
	public Task(String name, String contents)
	{ 
		Title = name; 
		ListContents = contents; 
	}
	
	//Id handlers
	public int getId()
	{
		return Id; 
	}
	
	public void setId(int Id)
	{
		this.Id = Id; 
	}
	
	
	//Name handlers
	public String getListName()
	{
		return Title; 
	}
	
	public void setListName(String name)
	{
		this.Title = name; 
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
