package com.database.schedule.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class EventsService 
{
	@Autowired
	private EventsRepository eventsRepository; 
	
	public List<Events> getPins() 
	{
		return eventsRepository.findAll();
	}
	
	public Events addPin(Events pin)
	{
		return eventsRepository.save(pin);
	}
	
    public Long count() 
    {

        return eventsRepository.count();
    }

    public boolean deleteById(String pinId) throws IllegalArgumentException
    {
    	eventsRepository.deleteById(pinId);
        return true; 
    }

}
