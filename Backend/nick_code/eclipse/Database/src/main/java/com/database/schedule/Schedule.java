package com.database.schedule;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_schedule_id", foreignKey = @ForeignKey(name="room_schedule_id"))
	private Integer roomId; 
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.schedule.events.Events.class)
	@JoinColumn(name="event_id", foreignKey = @ForeignKey(name="event_id"))
	private Integer eventId;
	
	/**
	 * Constructor
	 * @param roomId
	 * @param eventId
	 */
	public Schedule(Integer roomId, Integer eventId)
	{
		this.roomId = roomId; 
		this.eventId = eventId; 
	}
	
	/**
	 * Handlers
	 */
	public Integer getId()
	{
		return id; 
	}
	
	public Integer getRoomId()
	{
		return roomId; 
	}
	
	public Integer getEventId()
	{
		return eventId; 
	}
	
	public void setId(Integer id)
	{
		this.id = id; 
	}
	
	public void setRoomId(Integer roomId)
	{
		this.roomId = roomId;
	}
	
	public void setEventId(Integer eventId)
	{
		this.eventId = eventId;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Schedule))
			return false; 
		Schedule schedule = (Schedule) o;
		return this.id == schedule.id && this.roomId == schedule.roomId && this.eventId == schedule.eventId;
	}
}
