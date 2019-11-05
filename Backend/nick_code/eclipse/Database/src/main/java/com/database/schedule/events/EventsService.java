package com.database.schedule.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class implements the events repository. 
 * 
 * @author Nickolas Mitchell
 */
public class EventsService 
{
	/**
	 * Holds the events database object. 
	 */
	@Autowired
	private EventsRepository eventsRepository; 
	
	/**
	 * Returns all events within the database. 
	 * 
	 * @return
	 */
	public List<Events> getEvents() 
	{
		return eventsRepository.findAll();
	}
	
	/**
	 * Adds an event to the database. 
	 * 
	 * @param event
	 * @return
	 */
	public Events addEvent(Events event)
	{
		return eventsRepository.save(event);
	}
	
	/**
	 * Returns the number of events within the database. 
	 * 
	 * @return
	 */
    public Long count() 
    {

        return eventsRepository.count();
    }

    /**
     * Deletes an event from the database. Throws IllegalArgumentException
     * if the event does not exist. 
     * 
     * @param eventId
     * @return
     * @throws IllegalArgumentException
     */
    public boolean deleteById(Long eventId) throws IllegalArgumentException
    {
    	eventsRepository.deleteById(eventId);
        return true; 
    }

}
