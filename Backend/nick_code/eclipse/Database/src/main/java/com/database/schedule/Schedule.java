package com.database.schedule;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class implements the schedule entity. Schedule has a OneToOne relationship with Rooms. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="schedule")
public class Schedule 
{
	/**
	 * A unique id which is automatically set for each Schedule.
	 */
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	/**
	 * Room Id associated with this schedule.
	 */
	@OneToOne(fetch=FetchType.LAZY, targetEntity=com.database.rooms.Rooms.class)
    @JoinColumn(name="room_schedule_id", referencedColumnName = "id")
	private Long roomId; 
	
//	/**
//	 * Event Id's associated with this schedule.
//	 */
//	@OneToMany(fetch=FetchType.LAZY)
//    @JoinColumn(name="event_schedu_id", referencedColumnName = "id")
//	private List<Long> events;
	
	/**
	 * Default Constructor. 
	 */
	public Schedule()
	{
		
	}
	/**
	 * Constructor which sets the below parameters.
	 * 
	 * @param roomId
	 */
	public Schedule(Long roomId)
	{
		this.roomId = roomId; 
	}
	
	/**
	 * Returns the schedule id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
	}
	
	/**
	 * Returns the room id.
	 * 
	 * @return
	 */
	public Long getRoomId()
	{
		return roomId; 
	}
	
	/**
//	 * Returns the event id's.  
//	 * 
//	 * @return
//	 */
//	public List<Long> getEventId()
//	{
//		return events; 
//	}
	
	/**
	 * Sets the schedule id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
	}
	
	/**
	 * Sets the room id. 
	 * 
	 * @param roomId
	 */
	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}
	
//	public void setManyEventIds(List<Long> eventIds)
//	{
//		for(Long i: eventIds)
//		{
//			events.add(i);
//		}
//	}
	
//	/**
//	 * Sets the event id. 
//	 * 
//	 * @param eventId
//	 */
//	public void setEventId(Long eventId)
//	{
//		events.add(eventId); 
//	}
	
	/**
	 * Checks if two schedules are the same. 
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Schedule))
			return false; 
		Schedule schedule = (Schedule) o;
		return this.id == schedule.id && this.roomId == schedule.roomId;
				//&& this.events.equals(schedule.events);
	}
}
