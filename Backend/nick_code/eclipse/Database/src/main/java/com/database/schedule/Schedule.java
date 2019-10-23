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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id; 
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name="room_schedule_id", foreignKey = @ForeignKey(name="room_schedule_id"))
	private String roomId; 
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = com.database.schedule.events.Events.class)
	@JoinColumn(name="event_id", foreignKey = @ForeignKey(name="event_id"))
	private String eventId;
	
	/**
	 * @JoinTable(name = "lawyer_cscd", joinColumns = {
    @JoinColumn(name = "country_code", referencedColumnName = "country_code") }, inverseJoinColumns = {
    @JoinColumn(name = "lawyer_batch_no", referencedColumnName = "lawyer_batch_no") })
	 */
	
	/**
	 * Constructor
	 * @param roomId
	 * @param eventId
	 */
	public Schedule(String roomId, String eventId)
	{
		this.roomId = roomId; 
		this.eventId = eventId; 
	}
	
	/**
	 * Handlers
	 */
	public String getId()
	{
		return id; 
	}
	
	public String getRoomId()
	{
		return roomId; 
	}
	
	public String getEventId()
	{
		return eventId; 
	}
	
	public void setId(String id)
	{
		this.id = id; 
	}
	
	public void setRoomId(String roomId)
	{
		this.roomId = roomId;
	}
	
	public void setEventId(String eventId)
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
