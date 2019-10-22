package com.database.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="users")
public class User 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="email")
	private String Email;
	
	@Column(name="password")
	private String Password;
	
	@Column(name="in_room")
	private int In_Room;
	
	
	public User() {

	}
	
	public User(String name, String email, String pswd)
	{ 
		this.Name = name; 
		this.Email = email;
		this.Password = pswd; 
		this.In_Room = 0; 
	}
	
	//Id handlers
	public String getId(){
		return Id; 
	}
	
	public void setId(String Id){
		this.Id = Id; 
	}
	
	//Name handlers
	public String getName(){
		return Name; 
	}
	
	public void setName(String name){
		this.Name = name; 
	}
	
	//Email handlers
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		this.Email = email;
	}
	
	//Password handlers
	public String getPassword(){
		return Password; 
	}
	
	public void setPassword(String pswd){
		this.Password = pswd; 
	}
	
	//In_Room handlers
	public int getInRoom(){
		return In_Room; 
	}
	
	public void setInRoom(String in_room){
		this.Password = in_room; 
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof User))
			return false; 
		User user = (User) o;
		return this.Id == user.Id && this.Name == user.Name && this.Email == user.Email && this.Password == user.Password && this.In_Room == user.In_Room;
	}

}
