package com.database;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.test.context.junit4.SpringRunner;

import com.database.bulletin.BulletinRepository;
import com.database.bulletin.BulletinService;
import com.database.lists.RoomListRepository;
import com.database.lists.RoomListService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoTestApplicationTests 
{
	private RoomListService roomListService;
	private RoomListRepository roomListRepository; 
	
	private BulletinService bulletinService;
	private BulletinRepository bulletinRepository; 
	
	
	//private UserService userService;
	private UserRepository userRepository
	
	private ErrorAttributes errorAttributes;
	
	

}
