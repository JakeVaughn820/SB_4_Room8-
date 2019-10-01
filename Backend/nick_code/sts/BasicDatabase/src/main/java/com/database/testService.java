package com.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testService 
{
	
	@Autowired
	private testRepository testRepository;
	
	public List<testList> getList() 
	{
		return testRepository.findAll();
	}

}
