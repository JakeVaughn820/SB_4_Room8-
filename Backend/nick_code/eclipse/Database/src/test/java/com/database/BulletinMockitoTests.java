package com.database;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.database.bulletin.Bulletin;
import com.database.bulletin.BulletinRepository;
import com.database.bulletin.BulletinService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BulletinMockitoTests 
{
	@Autowired
	private BulletinService bulletinService;
	@MockBean
	private BulletinRepository bulletinRepository;

//	@Test
//	public void getUsersTest() {
//		when(bulletinRepository.findAll()).thenReturn(Stream.of(new Bulletin("Room1", "Pin1"),
//				new Bulletin("Room2", "Pin2")).collect(Collectors.toList()));
//		assertEquals(2, bulletinService.getBulletin().size());
//	}
//
//	@Test
//	public void addUsersTest() {
//		Bulletin newBulletin = new Bulletin("Room3", "Pin3");
//		when(bulletinRepository.save(newBulletin)).thenReturn(newBulletin);
//		assertEquals(newBulletin, bulletinService.addBulletin(newBulletin));
//	}

}
