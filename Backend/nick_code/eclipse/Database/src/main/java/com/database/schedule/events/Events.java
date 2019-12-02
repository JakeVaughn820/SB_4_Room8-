package com.database.schedule.events;

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
 * This class provides the implementation for the Events entity. The schedule
 * entity has a OneToMany relationship with events.
 * 
 * @author Nickolas Mitchell
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

	/**
	 * Holds the contents of the event.
	 */
	@Column(name = "contents")
	private String contents;

	/**
	 * Assigns each event to a user. A user can have multiple events.
	 */
	@Column(name = "user_event_id")
	private String username;

	/**
	 * Holds the schedule this event was created in.
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.database.schedule.Schedule.class)
	@JoinColumn(name = "schedule_id", referencedColumnName = "id")
	private Long schedule;

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
	public Events(String contents, String username, Long schedule) {
		this.contents = contents;
		this.username = username;
		this.schedule = schedule;
	}

	/**
	 * Returns the event Id.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the contents of the event.
	 * 
	 * @return
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * Returns the user id associated with this event.
	 * 
	 * @return
	 */
	public String getUsers() {
		return username;
	}

	/**
	 * Sets the event id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the events contents.
	 * 
	 * @param contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * Sets the user associated with this event.
	 * 
	 * @param userId
	 */
	public void setUsername(String username) {
		this.username = username;
	}

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
	}

	/**
	 * Adds this event to a schedule.
	 * 
	 * @param schedule
	 */
	public void setSchedule(Long schedule) {
		this.schedule = schedule;
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
		return this.id == event.id && this.contents.equals(event.contents) && this.username.equals(event.username);
	}

}
