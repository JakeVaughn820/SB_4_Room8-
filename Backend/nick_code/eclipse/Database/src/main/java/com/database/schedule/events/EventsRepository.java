package com.database.schedule.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface holds the repository for the events entity. 
 * 
 * @author Nickolas Mitchell
 */
@Repository
public interface EventsRepository extends JpaRepository<Events, Long>
{

}
