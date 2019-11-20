package com.database.user;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.database.roomMembers.RoomMembers;

import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This class provides the implementation for the users entity.
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Entity
@Table(name = "users")
public class User {
	/**
	 * A unique id which is generated for each user.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Name of the user.
	 */
	@Column(name = "name")
	private String Name;

	/**
	 * Email of the user.
	 */
	@Column(name = "email")
	private String Email;

	/**
	 * Password of the user.
	 */
	@Column(name = "password")
	private String Password;
	
	/**
	 * Room members stuff
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<RoomMembers> roomMembers; 

	/**
	 * Default constructor
	 */
	public User() {
	}

	/**
	 * A more useful constructor which sets the below parameters.
	 * 
	 * @param name
	 * @param email
	 * @param pswd
	 */
	public User(String name, String email, String pswd) {
		this.Name = name;
		this.Email = email;
		this.Password = pswd;
	}

	/**
	 * Returns the users id.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the users id.
	 * 
	 * @param Id
	 */
	public void setId(Long Id) {
		this.id = Id;
	}

	/**
	 * Returns the users name.
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the users name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.Name = name;
	}

	/**
	 * Returns the users email.
	 * 
	 * @return
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Sets the users email.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.Email = email;
	}

	/**
	 * Returns the users password.
	 * 
	 * @return
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * Sets the users password.
	 * 
	 * @param pswd
	 */
	public void setPassword(String pswd) {
		this.Password = pswd;
	}
	
	public Set<RoomMembers> getRoomMembers() {
		return roomMembers;
	}

	public void setRoomMembers(Set<RoomMembers> roomMembers) {
		this.roomMembers = roomMembers;
	}

	/**
	 * Checks if two users are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return this.id == user.id && this.Name.equals(user.Name) && this.Email.equals(user.Email)
				&& this.Password.equals(user.Password);
	}

}
