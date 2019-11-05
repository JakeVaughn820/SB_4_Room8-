package com.database.bulletin;

import java.util.List;

import javax.persistence.CascadeType;
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

//import com.database.rooms.Rooms;

/**
 * This class implements the bulletin entity. A bulletin is made up 
 * of a table of pins. Each room only has one bulletin.  
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="bulletin")
public class Bulletin 
{
	/**
	 * A unique Id which is automatically generated for each bulletin. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id; 
	
	/**
	 * Room Id associated with this bulletin.
	 */
	@OneToOne(fetch=FetchType.LAZY, targetEntity=com.database.rooms.Rooms.class)
    @JoinColumn(name="bulletinroomid", referencedColumnName = "id")
	private Long roomId; 
	
//	/**
//	 * Pin Id's associated with this schedule.
//	 */
//	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name="bulletinpinid", referencedColumnName = "id")
//	private List<Long> pins;
	
	/**
	 * Default Constructor
	 */
	public Bulletin() {}
	
	/**
	 * Constructor which sets the roomId. 
	 * 
	 * @param roomId
	 */
	public Bulletin(Long roomId)
	{
		this.roomId = roomId; 
	}
	
	/**
	 * Gets the bulletin Id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
	}
	
	/**
	 * Gets the room Id. 
	 * @return
	 */
	public Long getRoomId()
	{
		return roomId;
	}
	
//	/**
//	 * Gets the pin's Id
//	 * 
//	 * @return
//	 */
//	public List<Long> getPinId() 
//	{
//		return pins; 
//	}
	
	/**
	 * Sets the bulletin Id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
	}
	
	/**
	 * Sets the room Id. 
	 * 
	 * @param roomId
	 */
	public void setRoomId(Long roomId)
	{
		this.roomId = roomId; 
	}
	
//	/**
//	 * Sets one pin Id. 
//	 * 
//	 * @param pinId
//	 */
//	public void setPinId(Long pinId)
//	{
//		this.pins.add(pinId); 
//	}
	
	/**
	 * Checks if two bulletin objects are the same. 
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Bulletin))
			return false; 
		Bulletin bulletin = (Bulletin) o;
		return this.id == bulletin.id && this.roomId == bulletin.roomId; 
				//&& this.pins.equals(bulletin.pins);
	}	
}
