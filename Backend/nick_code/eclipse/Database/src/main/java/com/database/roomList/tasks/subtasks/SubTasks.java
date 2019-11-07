package com.database.roomList.tasks.subtasks;

import java.util.List;

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
 * This class implements the subtasks entity. Each task can have 
 * multiple subtasks. 
 * 
 * @author Nickolas Mitchell
 */
@Entity
@Table(name="subtasks")
public class SubTasks 
{
	/**
	 * A unique Id which is automatically generated for each subtask.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Holds the contents of this subtask.
	 */
	@Column(name = "contents")
	private String contents;

	/**
	 * Assigns each subtask to a user. A user can have multiple tasks.
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.database.user.User.class)
	@JoinColumn(name = "user_subtask_id", referencedColumnName = "id")
	private List<Long> users;

	/**
	 * Holds the task this task was created in.
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.database.roomList.tasks.Tasks.class)
	@JoinColumn(name = "task_id", referencedColumnName = "id")
	private Long task;

	/**
	 * Default Constructor
	 */
	public SubTasks() {
	}

	/**
	 * Constructor which sets the below parameters. 
	 * 
	 * @param task
	 * @param contents
	 * @param users
	 */
	public SubTasks(String contents, Long users, Long task) 
	{
		this.contents = contents;
		this.users.add(users);
		this.task = task;
	}

	/**
	 * Gets the subtask Id.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the subtask's contents.
	 * 
	 * @return
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * Gets the users associated with this subtask.
	 * 
	 * @return
	 */
	public List<Long> getUser() {
		return users;
	}

	/**
	 * Gets task Id associated with this subtask.
	 * 
	 * @return
	 */
	public Long getListId() {
		return task;
	}

	/**
	 * Sets the subtask Id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the subtask's contents.
	 * 
	 * @param contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * Sets one user Id.
	 *
	 * @param userId
	 */
	public void setUserId(Long userId) {
		users.add(userId);
	}

	/**
	 * Sets the task Id.
	 * 
	 * @param task
	 */
	public void setListId(Long task) {
		this.task = task;
	}

	/**
	 * Returns: "User: " ~~~~ "Contents: " ~~~~~
	 */
	@Override
	public String toString() {
		String temp = "";
		for (Long i : users) {
			temp += "User: " + users.get(Math.toIntExact(i)) + "Contents: " + contents;
			temp += "\n";
		}
		return temp;
	}

	/**
	 * Checks if two subtasks are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof SubTasks))
			return false;
		SubTasks subtask = (SubTasks) o;
		return this.id == subtask.id && this.contents.equals(subtask.contents) && this.users.equals(subtask.users)
				&& this.task == subtask.task;
	}


}
