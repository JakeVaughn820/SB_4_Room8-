package com.database.roomList.tasks;

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

import com.database.roomList.RoomList;

/**
 * This class implements the tasks entity. Each roomList can have 
 * multiple tasks. 
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Entity
@Table(name="tasks")
public class Tasks 
{
	/**
	 * A unique Id which is automatically generated for each task.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Holds the contents of this task.
	 */
	@Column(name = "contents")
	private String contents;

	/**
	 * Holds the roomList this task was created in.
	 */
	@ManyToOne(targetEntity = com.database.roomList.RoomList.class)
	@JoinColumn(name = "list_id", referencedColumnName = "id")
	private RoomList list;

//	/**
//	 * SubTask Id's associated with this Task.
//	 */
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "subtask_id", referencedColumnName = "id")
//	private List<Long> subtasks;

	/**
	 * Default Constructor
	 */
	public Tasks() {
	}

	/**
	 * Constructor
	 * 
	 * @param list
	 * @param contents
	 */
	public Tasks(String contents, RoomList list)
	{
		this.contents = contents;
		this.list = list;
	}

	/**
	 * Gets the task Id.
	 * 
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	/**
	 * Gets the task's contents.
	 * 
	 * @return
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * Gets list Id associated with this task.
	 * 
	 * @return
	 */
	public RoomList getRoomList() {
		return list;
	}

	/**
	 * Sets the task Id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the task's contents.
	 * 
	 * @param contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}


	/**
	 * Sets the List Id.
	 * 
	 * @param listId
	 */
	public void setList(RoomList list) {
		this.list = list;
	}

//	/**
//	 * Gets all subtasks for this task.
//	 * 
//	 * @return
//	 */
//	public List<Long> getSubTasks() {
//		return subtasks;
//	}
//
//	/**
//	 * Adds one subtask to this task.
//	 * 
//	 * @param subtask
//	 */
//	public void setSubTask(Long subtask) {
//		this.subtasks.add(subtask);
//	}
	/**
	 * Checks if two tasks are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Tasks))
			return false;
		Tasks task = (Tasks) o;
		return this.id == task.id && this.contents.equals(task.contents)
				&& this.list == task.list; 
				//&& this.subtasks.equals(task.subtasks);
	}
}