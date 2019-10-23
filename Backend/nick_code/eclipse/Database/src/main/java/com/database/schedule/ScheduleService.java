package com.database.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService 
{
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public List<Schedule> getBulletin()
	{
		return scheduleRepository.findAll();
	}
	
	public Schedule addBulletin(Schedule schedule)
	{
		return scheduleRepository.save(schedule); 
	}
	
    public Long count() {

        return scheduleRepository.count();
    }

    public boolean deleteById(String userId) throws IllegalArgumentException  
    {
    	scheduleRepository.deleteById(userId);
    	return true; 
    }

}
