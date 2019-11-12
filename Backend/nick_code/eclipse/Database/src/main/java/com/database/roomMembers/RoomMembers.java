package com.database.roomMembers;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * This class implements the roomMembers entity. The roomMembers entity is simply 
 * an extra table to handle the manytomany relationship between rooms and users. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="room_members")
public class RoomMembers 
{
	/**
	 * A unique Id which is automatically generated for each roomMember. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	public Long id; 
	
	/**
	 * UserId relationship
	 */
    @OneToOne(fetch=FetchType.EAGER, targetEntity=com.database.user.User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
	public Long userId;
    
    /**
     * RoomId relationship
     */
    @OneToOne(fetch=FetchType.EAGER, targetEntity=com.database.rooms.Rooms.class)
    @JoinColumn(name="room_id", referencedColumnName = "id")
	public Long roomId;
	
	/**
	 * Holds the user role. A user can be an "Owner" Roommate" or "Viewer"
	 */
	@Column(name="user_role")
	public String userRole; 
	
	/**
	 * Default Constructor. 
	 */
	public RoomMembers()
	{
		
	}

	/**
	 * Constructor which sets the below fields. 
	 * 
	 * @param userId
	 * @param roomId
	 */
	public RoomMembers(Long userId, Long roomId, String userRole)
	{
		this.userId = userId;
		this.roomId = roomId; 
		this.userRole = userRole; 
	}
	
	/**
	 * Sets the roomMembers Id.
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
	}
	
	/**
	 * Sets the users Id. 
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId)
	{
		this.userId = userId; 
	}
	
	/**
	 * Sets the rooms Id. 
	 * 
	 * @param roomId
	 */
	public void setRoomId(Long roomId)
	{
		this.roomId = roomId; 
	}
	
	/**
	 * Gets the roomMembers Id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return this.id;
	}
	
	/**
	 * Gets the users Id.
	 * 
	 * @return
	 */
	public Long getUserId()
	{
		return this.userId; 
	}
	
	/**
	 * Gets the rooms Id. 
	 * 
	 * @return
	 */
	public Long getRoomId()
	{
		return this.roomId; 
	}
	
	/**
	 * Gets the users role. 
	 * 
	 * @return
	 */
	public String getUserRole()
	{
		return this.userRole; 
	}
	
	/**
	 * Sets the users role. 
	 * 
	 * @param userRole
	 */
	public void setUserRole(String userRole)
	{
		if(!userRole.equals("Owner") || !userRole.equals("Roommate"))
			this.userRole = "Viewer";
		else
			this.userRole = userRole; 
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof RoomMembers))
			return false; 
		RoomMembers RoomMembers = (RoomMembers) o;
		return this.id == RoomMembers.id && this.userId == RoomMembers.userId && this.roomId == RoomMembers.roomId 
				&& this.userRole.equals(RoomMembers.userRole);
	}
}
