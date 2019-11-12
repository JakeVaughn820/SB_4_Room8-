package com.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.room.Room;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * This class provides the implementation for the users entity.
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="users")
public class User 
{
	/**
	 * A unique id which is generated for each user. 
	 */
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Name of the user. 
	 */
	@Column(name="name")
	private String Name;
	
	/**
	 * Email of the user. 
	 */
	@Column(name="email")
	private String Email;
	
	/**
	 * Password of the user. 
	 */
	@Column(name="password")
	private String Password;
	
	/**
	 * How many rooms the user is currently in. 
	 */
	@Column(name="in_room")
	private int In_Room;
	
	/**
	 * Current many to many test. 
	 */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "room_members", 
      joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<Room> rooms;
	
//	@ManyToMany(cascade ={ CascadeType.ALL })
//	@JoinTable(name = "room_members", joinColumns =
//	{ @JoinColumn(name = "user_id") }, inverseJoinColumns =
//	{ @JoinColumn(name = "room_id") })
//	List<Room> joinedRooms;

	/**
	 * Default constructor 
	 */
	public User()
	{
	}
	
	/**
	 * A more useful constructor which sets the below parameters. 
	 * 
	 * @param name
	 * @param email
	 * @param pswd
	 */
	public User(String name, String email, String pswd)
	{ 
		this.Name = name; 
		this.Email = email;
		this.Password = pswd; 
		this.In_Room = 0; 
	}
	
	/**
	 * Returns the users id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
	}
	
	/**
	 * Sets the users id. 
	 * 
	 * @param Id
	 */
	public void setId(Long Id)
	{
		this.id = Id; 
	}
	
	/**
	 * Returns the users name.
	 * 
	 * @return
	 */
	public String getName()
	{
		return Name; 
	}
	
	/**
	 * Sets the users name. 
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.Name = name; 
	}
	
	/**
	 * Returns the users email. 
	 * 
	 * @return
	 */
	public String getEmail() 
	{
		return Email;
	}
	
	/**
	 * Sets the users email. 
	 * 
	 * @param email
	 */
	public void setEmail(String email) 
	{
		this.Email = email;
	}
	
	/**
	 * Returns the users password. 
	 * 
	 * @return
	 */
	public String getPassword()
	{
		return Password; 
	}
	
	/**
	 * Sets the users password. 
	 * 
	 * @param pswd
	 */
	public void setPassword(String pswd)
	{
		this.Password = pswd; 
	}
	
	/**
	 * Returns the number of rooms a user is in.  
	 * 
	 * @return
	 */
	public int getInRoom()
	{
		return In_Room; 
	}
	
	/**
	 * Sets the number of rooms a user is in. 
	 * 
	 * @param num
	 */
	public void setInRoom(int num)
	{
		this.In_Room = num;
	}
	
	/**
	 * Increments the number of rooms a user is in by 1. 
	 */
	public void addedUserToRoom()
	{
		In_Room ++; 
	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}	
	
	/**
	 * Checks if two users are the same. 
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof User))
			return false; 
		User user = (User) o;
		return this.id == user.id && this.Name.equals(user.Name) && this.Email.equals(user.Email) 
				&& this.Password.equals(user.Password) && this.In_Room == user.In_Room;
	}

}
