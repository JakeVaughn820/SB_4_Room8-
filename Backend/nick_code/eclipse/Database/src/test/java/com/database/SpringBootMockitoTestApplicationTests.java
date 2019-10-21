package com.database;

import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.database.bulletin.BulletinService;
import com.database.lists.RoomListService;
import com.database.tasks.TaskService;
import com.database.user.UserService;

//@RunWith(MockitoJUnitRunner.class)
public class SpringBootMockitoTestApplicationTests 
{
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	BulletinService bulletinService; 
	
	@Mock
	RoomListService roomListService; 
	
	@Mock
	TaskService taskService;
	
	@Mock
	UserService userService;
	
	@Test
	public void test1()
	{
		
	}
	

}
