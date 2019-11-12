package com.database.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the repository for the schedule entity. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>
{
	
}