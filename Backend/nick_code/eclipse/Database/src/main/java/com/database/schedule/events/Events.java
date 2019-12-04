package com.database.schedule.events;

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
 * This class provides the implementation for the Events entity. The schedule
 * entity has a OneToMany relationship with events.
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Entity
@Table(name = "events")
public class Events {
	/**
	 * A unique id which is generated for each event.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
<<<<<<< HEAD
	
	@Column(name="title")
	private String title;

||||||| merged common ancestors
	
=======

>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	/**
	 * Holds the contents of the event.
	 */
<<<<<<< HEAD
	@Column(name="description")
	private String description;
	
	@Column(name="starttime")
	private String starttime;
	
	@Column(name="endtime")
	private String endtime;
	
||||||| merged common ancestors
	@Column(name="contents")
	private String contents;
	
=======
	@Column(name = "contents")
	private String contents;

>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	/**
	 * Assigns each event to a user. A user can have multiple events.
	 */
<<<<<<< HEAD
	@ManyToOne(targetEntity=com.database.user.User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
||||||| merged common ancestors
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=com.database.user.User.class)
    @JoinColumn(name="user_event_id", referencedColumnName = "id")
	private List<Long> users; 
	
=======
	@Column(name = "user_event_id")
	private String username;

>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	/**
<<<<<<< HEAD
	 * Many to One relationship with Rooms. 
||||||| merged common ancestors
	 * Holds the schedule this event was created in. 
=======
	 * Holds the schedule this event was created in.
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	 */
<<<<<<< HEAD
	@ManyToOne(targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "id"))
	private Rooms room;
	
||||||| merged common ancestors
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=com.database.schedule.Schedule.class)
    @JoinColumn(name="schedule_id", referencedColumnName = "id")
	private Long schedule; 
	
=======
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.database.schedule.Schedule.class)
	@JoinColumn(name = "schedule_id", referencedColumnName = "id")
	private Long schedule;

>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	/**
	 * Default constructor.
	 */
	public Events() {
	}

	/**
	 * Constructor which sets the below parameters.
	 * 
	 * @param contents
	 * @param userId
	 */
<<<<<<< HEAD
	public Events(Rooms room, String title, String description, String start, String end, User user)
	{ 
		this.room = room;
		this.title = title;
		this.description = description;
		this.starttime = start;
		this.endtime = end;
		this.user = user;
		this.room = room; 
||||||| merged common ancestors
	public Events(String contents, Long userId, Long schedule)
	{ 
		this.contents = contents;
		users.add(userId); 
		this.schedule = schedule; 
=======
	public Events(String contents, String username, Long schedule) {
		this.contents = contents;
		this.username = username;
		this.schedule = schedule;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public String getTitle() {
		return title;
||||||| merged common ancestors
	
	/**
	 * Returns the event Id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
=======

	/**
	 * Returns the event Id.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public void setTitle(String title) {
		this.title = title;
||||||| merged common ancestors
	
	/**
	 * Returns the contents of the event. 
	 * 
	 * @return
	 */
	public String getContents()
	{
		return contents; 
=======

	/**
	 * Returns the contents of the event.
	 * 
	 * @return
	 */
	public String getContents() {
		return contents;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public String getDescription() {
		return description;
||||||| merged common ancestors
	
	/**
	 * Returns the user id associated with this event. 
	 * 
	 * @return
	 */
	public List<Long> getUsers()
	{
		return users;
=======

	/**
	 * Returns the user id associated with this event.
	 * 
	 * @return
	 */
	public String getUsers() {
		return username;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public void setDescription(String description) {
		this.description = description;
||||||| merged common ancestors
	
	/**
	 * Sets the event id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
=======

	/**
	 * Sets the event id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public String getStarttime() {
		return starttime;
||||||| merged common ancestors
	
	/**
	 * Sets the events contents. 
	 * 
	 * @param contents
	 */
	public void setContents(String contents)
	{
		this.contents = contents; 
=======

	/**
	 * Sets the events contents.
	 * 
	 * @param contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public void setStarttime(String starttime) {
		this.starttime = starttime;
||||||| merged common ancestors
	
	/**
	 * Sets the user associated with this event. 
	 * 
	 * @param userId
	 */
	public void setUsers(Long users)
	{
		this.users.add(users); 
=======

	/**
	 * Sets the user associated with this event.
	 * 
	 * @param userId
	 */
	public void setUsername(String username) {
		this.username = username;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public String getEndtime() {
		return endtime;
||||||| merged common ancestors
	
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
=======

	/**
	 * Returns "User: 'userId' Contents: 'contents' "
	 */
	@Override
	public String toString() {
		return "User: " + username + "Event: " + contents;
	}

	/**
	 * Returns the schedule this event is associated with.
	 * 
	 * @return
	 */
	public Long getSchedule() {
		return schedule;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}
<<<<<<< HEAD

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setUsers(User user) {
		this.user = user;
||||||| merged common ancestors
	
	/**
	 * Adds this event to a schedule. 
	 * 
	 * @param schedule
	 */
	public void setSchedule(Long schedule)
	{
		this.schedule = schedule; 
=======

	/**
	 * Adds this event to a schedule.
	 * 
	 * @param schedule
	 */
	public void setSchedule(Long schedule) {
		this.schedule = schedule;
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}

	/**
	 * Checks if two events are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Events))
			return false;
		Events event = (Events) o;
<<<<<<< HEAD
		return this.id == event.id && this.description.equals(event.description) && this.users.equals(event.users); 
||||||| merged common ancestors
		return this.id == event.id && this.contents.equals(event.contents) && this.users.equals(event.users); 
=======
		return this.id == event.id && this.contents.equals(event.contents) && this.username.equals(event.username);
>>>>>>> a6d72dc80042abe3efed18a34dfee825808fbbcc
	}

}
