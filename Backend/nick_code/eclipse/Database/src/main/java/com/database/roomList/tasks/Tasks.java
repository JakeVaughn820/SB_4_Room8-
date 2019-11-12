//package com.database.roomList.tasks;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
///**
// * This class implements the tasks entity. Each roomList can have 
// * multiple tasks. 
// * 
// * @author Nickolas Mitchell
// */
//@Entity
//@Table(name="tasks")
//public class Tasks 
//{
//	/**
//	 * A unique Id which is automatically generated for each task.
//	 */
//	@Id
//	@Column(name = "id", nullable = false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	/**
//	 * Holds the contents of this task.
//	 */
//	@Column(name = "contents")
//	private String contents;
//
//	/**
//	 * Assigns each task to a user. A user can have multiple tasks.
//	 */
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.database.user.User.class)
//	@JoinColumn(name = "user_task_id", referencedColumnName = "id")
//	private List<Long> users;
//
//	/**
//	 * Holds the roomList this task was created in.
//	 */
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.database.roomList.RoomList.class)
//	@JoinColumn(name = "list_id", referencedColumnName = "id")
//	private Long list;
//
////	/**
////	 * SubTask Id's associated with this Task.
////	 */
////	@OneToMany(fetch = FetchType.LAZY)
////	@JoinColumn(name = "subtask_id", referencedColumnName = "id")
////	private List<Long> subtasks;
//
//	/**
//	 * Default Constructor
//	 */
//	public Tasks() {
//	}
//
//	/**
//	 * Constructor
//	 * 
//	 * @param list
//	 * @param contents
//	 * @param uses
//	 */
//	public Tasks(String contents, Long users, Long list)
//	{
//		this.contents = contents;
//		this.users.add(users);
//		this.list = list;
//	}
//
//	/**
//	 * Gets the task Id.
//	 * 
//	 * @return
//	 */
//	public Long getId() 
//	{
//		return id;
//	}
//
//	/**
//	 * Gets the task's contents.
//	 * 
//	 * @return
//	 */
//	public String getContents() {
//		return contents;
//	}
//
//	/**
//	 * Gets the users associated with this task.
//	 * 
//	 * @return
//	 */
//	public List<Long> getUser() {
//		return users;
//	}
//
//	/**
//	 * Gets list Id associated with this task.
//	 * 
//	 * @return
//	 */
//	public Long getListId() {
//		return list;
//	}
//
//	/**
//	 * Sets the task Id.
//	 * 
//	 * @param id
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	/**
//	 * Sets the task's contents.
//	 * 
//	 * @param contents
//	 */
//	public void setContents(String contents) {
//		this.contents = contents;
//	}
//
//	/**
//	 * Sets one user Id.
//	 *
//	 * @param userId
//	 */
//	public void setUserId(Long userId) {
//		users.add(userId);
//	}
//
//	/**
//	 * Sets the List Id.
//	 * 
//	 * @param listId
//	 */
//	public void setListId(Long listId) {
//		this.list = listId;
//	}
//
////	/**
////	 * Gets all subtasks for this task.
////	 * 
////	 * @return
////	 */
////	public List<Long> getSubTasks() {
////		return subtasks;
////	}
////
////	/**
////	 * Adds one subtask to this task.
////	 * 
////	 * @param subtask
////	 */
////	public void setSubTask(Long subtask) {
////		this.subtasks.add(subtask);
////	}
//
//	/**
//	 * Returns: "User: " ~~~~ "Contents: " ~~~~~
//	 */
//	@Override
//	public String toString() {
//		String temp = "";
//		for (Long i : users) {
//			temp += "User: " + users.get(Math.toIntExact(i)) + "Contents: " + contents;
//			temp += "\n";
//		}
//		return temp;
//	}
//
//	/**
//	 * Checks if two tasks are the same.
//	 */
//	@Override
//	public boolean equals(Object o) {
//		if (o == this)
//			return true;
//		if (!(o instanceof Tasks))
//			return false;
//		Tasks task = (Tasks) o;
//		return this.id == task.id && this.contents.equals(task.contents) && this.users.equals(task.users)
//				&& this.list == task.list; 
//				//&& this.subtasks.equals(task.subtasks);
//	}
//}