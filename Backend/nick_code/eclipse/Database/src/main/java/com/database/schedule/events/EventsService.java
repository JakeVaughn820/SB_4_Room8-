package com.database.schedule.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class EventsService 
{
	@Autowired
	private EventsRepository eventsRepository; 
	
	public List<Events> getEvents() 
	{
		return eventsRepository.findAll();
	}
	
	public Events addEvent(Events event)
	{
		return eventsRepository.save(event);
	}
	
    public Long count() 
    {

        return eventsRepository.count();
    }

    public boolean deleteById(String eventId) throws IllegalArgumentException
    {
    	eventsRepository.deleteById(eventId);
        return true; 
    }

}
