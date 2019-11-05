package com.database.bulletin.pins;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class implements the pin entity. Multiple pins can relate to 
 * one bulletin. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="pin")
public class Pin 
{
	/**
	 * A unique Id which is automatically generated for each pin. 
	 */
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Holds the contents of this pin. 
	 */
	@Column(name="contents")
	private String contents;
	
	/**
	 * Assigns each pin to a userId. A user can have multiple pins.  
	 */
	@OneToMany(fetch=FetchType.LAZY, targetEntity=com.database.user.User.class)
    @JoinColumn(name="pin_user_id", referencedColumnName = "id")
	private List<Long> users; 
	
	/**
	 * Holds the bulletin this event was created in. 
	 */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=com.database.bulletin.Bulletin.class)
    @JoinColumn(name="pin_bulletin_id", referencedColumnName = "id")
	private Long bulletin; 
	
	/**
	 * Default Constructor. 
	 */
	public Pin() {}
	
	/**
	 * Constructor which sets the parameters below. 
	 * 
	 * @param contents
	 * @param bulletin
	 * @param userId
	 */
	public Pin(String contents, Long user, Long bulletin)
	{ 
		this.contents = contents;
		this.bulletin = bulletin;
		this.users.add(user);
	}
	
	/**
	 * Gets the pin Id. 
	 * 
	 * @return
	 */
	public Long getId()
	{
		return id; 
	}
	
	/**
	 * Gets the pin's contents. 
	 * 
	 * @return
	 */
	public String getContents()
	{
		return contents; 
	}
	
	/**
	 * Gets the user's Id. 
	 * 
	 * @return
	 */
	public List<Long> getUserId()
	{
		return users;
	}
	
	/**
	 * Sets the pin Id. 
	 * 
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id; 
	}
	
	/**
	 * Sets the pin's contents. 
	 * 
	 * @param contents
	 */
	public void setContents(String contents)
	{
		this.contents = contents; 
	}
	
	/**
	 * Adds one user to this pin. 
	 * 
	 * @param userId
	 */
	public void setUserId(Long user)
	{
		this.users.add(user);  
	}
	
	/**
	 * Gets the bulletin related to this pin. 
	 * 
	 * @return
	 */
	public Long getBulletin()
	{
		return bulletin; 
	}
	
	/**
	 * Sets this pin to a bulletin. 
	 * 
	 * @param bulletin
	 */
	public void setBulletin(Long bulletin)
	{
		this.bulletin = bulletin; 
	}
	
	/**
	 * Returns "User: " ~~~~~ "Contents: " ~~~~~
	 * 
	 */
	@Override 
	public String toString() 
	{ 
		String temp = "";
		for(Long i : users)
		{
			temp += "User: " + users.get(Math.toIntExact(i)) + "Contents: " + contents;
			temp += "\n";
		}
        return temp; 
    } 
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof Pin))
			return false; 
		Pin pin = (Pin) o;
		return this.id == pin.id && this.contents.equals(pin.contents) && this.users.equals(pin.users) 
				&& this.bulletin == pin.bulletin; 
	}
}