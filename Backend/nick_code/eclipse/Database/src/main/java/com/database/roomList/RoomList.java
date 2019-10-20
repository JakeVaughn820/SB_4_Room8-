package com.database.roomList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import com.database.tasks.*;

@Entity
@Table(name="Lists")
public class RoomList 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	
	@Column(name="Title")
	private String Title;	
	
	@Column(name="Description")
	private String Description;
	
	public RoomList() {
		
	}
	
	public RoomList(String name, String contents){ 
		Title = name; 
		Description = contents; 
	}
	
	public String getId(){
		return Id; 
	}
	
	public void setId(String id){
		Id = id; 
	}
		
	public String getTitle(){
		return Title; 
	}
	
	public void setTitle(String name){
		Title = name; 
	}
	
	public String getDescription(){
		return Description; 
	}
	
	public void setListContents(String contents){
		Description = contents; 
	}

    @Override
    public String toString() {
        String ret = "{\"Title\":\"" + Title + "\",\"Description\":\"" + Description + "\"}";
        return ret;
    }
}
