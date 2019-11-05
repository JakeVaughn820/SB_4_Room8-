package com.database.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the schedule repository.
 * 
 * @author Nickolas Mitchell
 */
@Service
public class ScheduleService 
{
	/**
	 * Holds the schedule repository. 
	 */
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	/**
	 * Returns all schedules within the database.
	 * 
	 * @return
	 */
	public List<Schedule> getSchedule()
	{
		return scheduleRepository.findAll();
	}
	
	/**
	 * Adds a schedule to the database. 
	 * 
	 * @param schedule
	 * @return
	 */
	public Schedule addSchedule(Schedule schedule)
	{
		return scheduleRepository.save(schedule); 
	}
	
	/**
	 * Returns the number of schedules in the database.
	 * 
	 * @return
	 */
    public Long count() {

        return scheduleRepository.count();
    }

    /**
     * Deletes a schedule from the database. Throws IllegalArgumentException 
     * if the schedule does not exist. 
     * 
     * @param scheduleId
     * @return
     * @throws IllegalArgumentException
     */
    public boolean deleteById(Long scheduleId) throws IllegalArgumentException  
    {
    	scheduleRepository.deleteById(scheduleId);
    	return true; 
    }

}
