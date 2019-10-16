package com.database.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Users")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="Password")
	private String Password;  
	
	
	public User() {

	}
	
	public User(String name, String email, String pswd){ 
		this.Name = name; 
		this.Email = email;
		this.Password = pswd; 
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

}
