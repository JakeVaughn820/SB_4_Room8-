package com.database.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
//	/**
//	 * Holds room Id's for every room this user is in. 
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> rooms;
//	
//	/**
//	 * Holds event Id's for all events this user has created.
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> events;
//	
//	/**
//	 * Holds pin Id's for all pins this user has created on the bulletin board. 
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> pins; 
//	
//	/**
//	 * Holds list Id's for all lists this user has created. 
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> lists; 
//	
//	/**
//	 * Holds task Id's for all tasks this user has created for a list. 
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> tasks;
//	
//	/**
//	 * Holds subTask Id's for all subTasks this user has created for a task. 
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	private List<Long> subTasks; 
	
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
	
//	/**
//	 * Returns all of the rooms a user is in. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getRooms()
//	{
//		return rooms; 
//	}
//	
//	/**
//	 * Add user to multiple rooms.
//	 * 
//	 * @param rooms
//	 */
//	public void addUserToRooms(List<Long> rooms)
//	{
//		for(Long i : rooms)
//		{
//			rooms.add(i);
//		}
//	}
//	
//	/**
//	 * Add user to a single room.
//	 * 
//	 * @param room
//	 */
//	public void addUserToARoom(Long room)
//	{
//		rooms.add(room); 
//	}
//	
//	/**
//	 * Remove user from a room 
//	 * 
//	 * @param room
//	 */
//	public void removeUserFromRoom(Long room)
//	{
//		rooms.remove(room); 
//	}
//	
//	/**
//	 * Returns all events this user has created. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getEvents()
//	{
//		return events; 
//	}
//	
//	/**
//	 * Adds multiple events a user has created. 
//	 * 
//	 * @param events
//	 */
//	public void addEvents(List<Long> events)
//	{
//		for(Long i : events)
//		{
//			this.events.add(i);
//		}
//	}
//
//	/** 
//	 * Adds a single event a user has created. 
//	 * 
//	 * @param event
//	 */
//	public void addEvent(Long event)
//	{
//		this.events.add(event);
//	}
//	
//	/**
//	 * Returns all pins this user has created. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getPins()
//	{
//		return pins; 
//	}
//	
//	/**
//	 * Adds multiple pins a user has created. 
//	 * 
//	 * @param pins
//	 */
//	public void addPins(List<Long> pins)
//	{
//		for(Long i : pins)
//		{
//			this.pins.add(i);
//		}
//	}
//	
//	/** 
//	 * Adds a single pin a user has created. 
//	 * 
//	 * @param pin
//	 */
//	public void addPin(Long pin)
//	{
//		this.pins.add(pin);
//	}
//	
//	/**
//	 * Returns all lists this user has created. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getLists()
//	{
//		return lists; 
//	}
//	
//	/**
//	 * Adds multiple lists a user has created. 
//	 * 
//	 * @param lists
//	 */
//	public void addLists(List<Long> lists)
//	{
//		for(Long i : lists)
//		{
//			this.lists.add(i);
//		}
//	}
//	
//	/** 
//	 * Adds a single list a user has created. 
//	 * 
//	 * @param list
//	 */
//	public void addList(Long list)
//	{
//		this.lists.add(list);
//	}
//	
//	/**
//	 * Returns all tasks this user has created. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getTasks()
//	{
//		return tasks; 
//	}
//	
//	/**
//	 * Adds multiple tasks a user has created. 
//	 * 
//	 * @param tasks
//	 */
//	public void addTasks(List<Long> tasks)
//	{
//		for(Long i : tasks)
//		{
//			this.tasks.add(i);
//		}
//	}
//	
//	/** 
//	 * Adds a single task a user has created. 
//	 * 
//	 * @param task
//	 */
//	public void addTask(Long task)
//	{
//		this.tasks.add(task);
//	}
//	
//	/**
//	 * Returns all subTasks this user has created. 
//	 * 
//	 * @return
//	 */
//	public List<Long> getSubTasks()
//	{
//		return subTasks; 
//	}
//	
//	/**
//	 * Adds multiple subTasks a user has created. 
//	 * 
//	 * @param tasks
//	 */
//	public void addSubTasks(List<Long> subTasks)
//	{
//		for(Long i : subTasks)
//		{
//			this.subTasks.add(i);
//		}
//	}
//	
//	/** 
//	 * Adds a single task a user has created. 
//	 * 
//	 * @param task
//	 */
//	public void addSubTasks(Long subTasks)
//	{
//		this.tasks.add(subTasks);
//	}	
	
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
		
		/**
		 * && this.rooms.equals(user.rooms)
				&& this.events.equals(user.events)
				&& this.pins.equals(user.pins)
				&& this.lists.equals(user.lists)
				&& this.tasks.equals(user.tasks)
				&& this.subTasks.equals(user.subTasks)
		 */
	}

}
