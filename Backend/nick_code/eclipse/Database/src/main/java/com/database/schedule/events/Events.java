package com.database.schedule.events;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class provides the implementation for the Events entity. 
 * The schedule entity has a OneToMany relationship with events. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="events")
public class Events 
{
	/**
	 * A unique id which is generated for each event. 
	 */
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Holds the contents of the event. 
	 */
	@Column(name="contents")
	private String contents;
	
	/**
	 * Assigns each event to a userId. A user can have multiple events.  
	 */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=com.database.user.User.class)
    @JoinColumn(name="user_event_id", referencedColumnName = "id")
	private List<Long> users; 
	
	/**
	 * Holds the schedule this event was created in. 
	 */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=com.database.schedule.Schedule.class)
    @JoinColumn(name="schedule_id", referencedColumnName = "id")
	private Long schedule; 
	
	/**
	 * Default constructor. 
	 */
	public Events()
	{	
	}
	
	/**
	 * Constructor which sets the below parameters. 
	 * 
	 * @param contents
	 * @param userId
	 */
	public Events(String contents, Long userId, Long schedule)
	{ 
		this.contents = contents;
		users.add(userId); 
		this.schedule = schedule; 
	}
	
	/**
	 * Returns the event Id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
	}
	
	/**
	 * Returns the contents of the event. 
	 * 
	 * @return
	 */
	public String getContents()
	{
		return contents; 
	}
	
	/**
	 * Returns the user id associated with this event. 
	 * 
	 * @return
	 */
	public List<Long> getUsers()
	{
		return users;
	}
	
	/**
	 * Sets the event id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
	}
	
	/**
	 * Sets the events contents. 
	 * 
	 * @param contents
	 */
	public void setContents(String contents)
	{
		this.contents = contents; 
	}
	
	/**
	 * Sets the user associated with this event. 
	 * 
	 * @param userId
	 */
	public void setUsers(Long users)
	{
		this.users.add(users); 
	}
	
	/**
	 * Returns "User: 'userId' Contents: 'contents' " 
	 */
	@Override 
	public String toString() 
	{ 
		String temp = "";
		for(Long i : users)
		{
			temp += "User: " + users.get(Math.toIntExact(i)) + " Contents: " + contents;
			temp += "\n";
		}
        return temp; 
    } 
	
	/**
	 * Returns the schedule this event is associated with. 
	 * 
	 * @return
	 */
	public Long getSchedule()
	{
		return schedule; 
	}
	
	/**
	 * Adds this event to a schedule. 
	 * 
	 * @param schedule
	 */
	public void setSchedule(Long schedule)
	{
		this.schedule = schedule; 
	}
	
	/**
	 * Checks if two events are the same. 
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Events))
			return false; 
		Events event = (Events) o;
		return this.id == event.id && this.contents.equals(event.contents) && this.users.equals(event.users); 
	}

}
