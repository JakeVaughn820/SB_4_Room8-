package com.database.bulletin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Bulletin")
public class Pin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	
	@Column(name="User")
	private String User;	
	
	@Column(name="Contents")
	private String Contents;
	
	public Pin() {
		
	}
	
	public Pin(String name, String contents){ 
		User = name; 
		Contents = contents; 
	}
	
	public String getId(){
		return Id; 
	}
	
	public void setId(String id){
		Id = id; 
	}
		
	public String getUser(){
		return User; 
	}
	
	public void setUser(String name){
		User = name; 
	}
	
	public String getContents(){
		return Contents; 
	}
	
	public void setContents(String contents){
		Contents = contents; 
	}

    @Override
    public String toString() {
        String ret = "{User:" + User + ",Contents:" + Contents + "}";
        return ret;
    }
}
