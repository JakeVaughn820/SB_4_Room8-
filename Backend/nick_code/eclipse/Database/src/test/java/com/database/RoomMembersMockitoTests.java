//package com.database;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.database.roomMembers.RoomMembers;
//import com.database.roomMembers.RoomMembersRepository;
//import com.database.roomMembers.RoomMembersService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RoomMembersMockitoTests  
//{
//	@Autowired
//	private RoomMembersService roomMemberService; 
//	
//	@MockBean
//	private RoomMembersRepository roomMembersRepository;
//	
//	@Test
//	public void getRoomMembersTest() 
//	{
//		when(roomMembersRepository.findAll()).thenReturn(Stream.of(new RoomMembers(1, 1), 
//				new RoomMembers(2, 2)).collect(Collectors.toList()));
//		assertEquals(2, roomMemberService.getRoomMembers().size());
//	}
//	
//	@Test
//	public void addRoomMemberTest()
//	{
//		RoomMembers newRoomMember = new RoomMembers(3, 3);
//		when(roomMembersRepository.save(newRoomMember)).thenReturn(newRoomMember); 
//		assertEquals(newRoomMember, roomMemberService.addRoomMembers(newRoomMember));
//	}
//}
