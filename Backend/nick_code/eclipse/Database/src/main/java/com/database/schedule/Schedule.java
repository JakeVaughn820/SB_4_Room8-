package com.database.schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.database.rooms.Rooms;

/**
 * This class implements the schedule entity. Schedule has a OneToOne
 * relationship with Rooms.
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name = "schedule")
public class Schedule {
	/**
	 * A unique id which is automatically set for each Schedule.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Room Id associated with this schedule.
	 */
	@OneToOne(targetEntity = com.database.rooms.Rooms.class)
	@JoinColumn(name = "room_schedule", foreignKey = @ForeignKey(name = "fk_schedule_room"))
	private Rooms room;

	/**
	 * Default Constructor.
	 */
	public Schedule() {

	}

	/**
	 * Constructor which sets the below parameters.
	 * 
	 * @param roomId
	 */
	public Schedule(Rooms room) {
		this.room = room;
	}

	/**
	 * Returns the schedule id.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the room id.
	 * 
	 * @return
	 */
	public Rooms getRoom() {
		return room;
	}

	/**
	 * Sets the schedule id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the room id.
	 * 
	 * @param roomId
	 */
	public void setRoomId(Rooms room) {
		this.room = room;
	}

	/**
	 * Checks if two schedules are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Schedule))
			return false;
		Schedule schedule = (Schedule) o;
		return this.id == schedule.id && this.room.equals(schedule.room);
	}
}
