package com.database.schedule.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Events controller for all events endpoints.
 * 
 * @author Nickolas Mitchell, Thane Storley
 */
@Controller
public class EventsController {
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	private EventsService eventService;

}
