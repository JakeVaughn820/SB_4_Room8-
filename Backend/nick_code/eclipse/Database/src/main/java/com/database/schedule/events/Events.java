package com.database.schedule.events;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.database.rooms.Rooms;
import com.database.user.User;

/**
 * This class provides the implementation for the Events entity. 
 * The schedule entity has a OneToMany relationship with events. 
 * 
 * @author Thane Storley, Nickolas Mitchell
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
	
	@Column(name="title")
	private String title;

	/**
	 * Holds the contents of the event. 
	 */
	@Column(name="description")
	private String description;
	
	@Column(name="starttime")
	private String starttime;
	
	@Column(name="endtime")
	private String endtime;
	
	/**
	 * Assigns each event to a userId. A user can have multiple events.  
	 */
	@ManyToOne(targetEntity=com.database.user.User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	/**
	 * Many to One relationship with Rooms. 
	 */
	@ManyToOne(targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "id"))
	private Rooms room;
	
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
	public Events(Rooms room, String title, String description, String start, String end, User user)
	{ 
		this.room = room;
		this.title = title;
		this.description = description;
		this.starttime = start;
		this.endtime = end;
		this.user = user;
		this.room = room; 
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setUsers(User user) {
		this.user = user;
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
		return this.id == event.id && this.description.equals(event.description) && this.users.equals(event.users); 
	}

}
