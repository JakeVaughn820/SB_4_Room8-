package com.database.roomList.tasks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.roomList.RoomList;

/**
 * This interface holds the task repository. 
 * 
 * @author Thane Storley, Nickolas Mitchell
 */
@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long>
{
	@Query(value = "SELECT * FROM tasks WHERE list_id = ?1", nativeQuery = true)
	List<Tasks> findTasksByListId(@Param("list_id") Long listId);
}
