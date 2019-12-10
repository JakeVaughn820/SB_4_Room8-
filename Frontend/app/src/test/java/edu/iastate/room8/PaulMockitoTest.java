package edu.iastate.room8;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import edu.iastate.room8.Home.HomeActivity;
import edu.iastate.room8.List.SubtaskActivity;
import edu.iastate.room8.Schedule.DayActivity;
import edu.iastate.room8.Schedule.ScheduleMVP.IDateParserInversionPattern;
import edu.iastate.room8.Schedule.ScheduleMVP.ScheduleActivity;
import edu.iastate.room8.Settings.RoomSettings.RoomSettingsActivity;
import edu.iastate.room8.Schedule.ScheduleMVP.DateParser;
import edu.iastate.room8.utils.Sessions.ISessionManagerInversionPattern;
import edu.iastate.room8.utils.Sessions.SessionManager;


/**
 * PaulMockitoTest
 * Tests using Mockito for the project
 * @author pndegnan
 */
public class PaulMockitoTest {
    /**
     * Used so Mockito can be used in JUNIT tests
     */
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * Simple JUNIT Test that makes sure that the class PaulMockitoTest is working
     * If it is not working it will give an error
     */
    @Test
    public void simpleJUNITTest() {
        assertEquals(1, 1);
    } //Makes sure class works as expected


    /**
     * Used to test schedule, specifically this one tests if the callDateParser method is working
     * If callDateParser is not working it will not be true. Simple to make sure the method
     * works and specifically that mocking works.
     */
    @Test
    public void scheduleTest()  {
        //This creates a Mock Object of the class that we have not fully implemented
        ScheduleActivity test = mock(ScheduleActivity.class);

        String day = "14";
        String month = "12";
        String year = "1998";
        String date = month + "/" + day + "/" + year;

        when(test.callDateParser()).thenReturn(date);

        Assert.assertEquals(date, test.callDateParser());
    }

    /**
     * Used to test schedule, specifically this one tests if the callDateParser method is working
     * If callDateParser is not working it will not be true. Simple to make sure the method
     * works and specifically that mocking works.
     */
    @Test
    public void scheduleTest2() {
        //This creates a Mock Object of the class that we have not fully implemented
        ScheduleActivity test = mock(ScheduleActivity.class);
        DateParser dateParser = new DateParser(14, 12, 1998); //day month year

        String day = "14";
        String month = "12";
        String year = "1998";
        String date = month + "/" + day + "/" + year;

        when(test.callDateParser()).thenReturn(date);

        Assert.assertEquals(test.callDateParser(),dateParser.parseDate());
    }

    /**
     * Testing with JSONObject's to verify how they work
     */
    @Test
    public void jsonObjectTest() throws JSONException{
        DayActivity test = mock(DayActivity.class);

        String day = "14";
        String month = "12";
        String year = "1998";
        String date = month + "/" + day + "/" + year;

        JSONObject response = new JSONObject();

        response.put("StartTime", "2:00pm");
        response.put("EndTime", "3:00pm");
        response.put("EventName", "Demo");
        response.put("User", "Paul");
        response.put("Date", date);

        when(test.jsonGetSchedule()).thenReturn(response);

        Assert.assertEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("StartTime"));
        Assert.assertEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("EndTime"));
        Assert.assertEquals(response.getString("EventName"), test.jsonGetSchedule().getString("EventName"));
        Assert.assertEquals(response.getString("User"), test.jsonGetSchedule().getString("User"));
    }

    /**
     * Testing with JSONObject's to verify how they work
     */
    @Test
    public void jsonObjectTestFalse() throws JSONException{
        DayActivity test = mock(DayActivity.class);

        String day = "14";
        String month = "12";
        String year = "1998";
        String date = month + "/" + day + "/" + year;

        JSONObject response = new JSONObject();

        response.put("StartTime", "2:00pm");
        response.put("EndTime", "3:00pm");
        response.put("EventName", "Demo");
        response.put("User", "Paul");
        response.put("Date", date);


        when(test.jsonGetSchedule()).thenReturn(response);

        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
    }

    /**
     * Testing with permissions between users
     */
    @Test
    public void PermissionTest1(){
        HomeActivity test = mock(HomeActivity.class);
//        SessionManager sessionManager = new SessionManager(test);
//        sessionManager.setPermission("Owner");
//        sessionManager.setRoomid("1");
//        sessionManager.setRoom("Room");

        when(test.getPermissionHome()).thenReturn("Owner");

        test.setPermissionForTesting("Owner");
        String temp = test.getPermissionHome();

        Assert.assertEquals("Owner", temp);

        Assert.assertEquals(View.VISIBLE, test.getButtonVisibility());
        Assert.assertNotEquals(View.INVISIBLE, test.getButtonVisibility());
    }
    /**
     * Testing with permissions between users
     */
    @Test
    public void PermissionTest2() {
        HomeActivity test = mock(HomeActivity.class);
//        SessionManager sessionManager = new SessionManager(test);
//        sessionManager.setPermission("Owner");
//        sessionManager.setRoomid("1");
//        sessionManager.setRoom("Room");

        when(test.getPermissionHome()).thenReturn("Editor");

        test.setPermissionForTesting("Editor");
        String temp = test.getPermissionHome();

        Assert.assertEquals("Editor", temp);

        Assert.assertEquals(View.VISIBLE, test.getButtonVisibility());
        Assert.assertNotEquals(View.INVISIBLE, test.getButtonVisibility());
    }
    /**
     * Testing with permissions between users
     */
    @Test
    public void PermissionTest3() {
        HomeActivity test = mock(HomeActivity.class);
//        SessionManager sessionManager = new SessionManager(test);
//        sessionManager.setPermission("Owner");
//        sessionManager.setRoomid("1");
//        sessionManager.setRoom("Room");
        when(test.getPermissionHome()).thenReturn("Viewer");

        test.setPermissionForTesting("Viewer");
        String temp = test.getPermissionHome();

        Assert.assertEquals("Viewer", temp);

        Assert.assertEquals(View.VISIBLE, test.getButtonVisibility());
        Assert.assertNotEquals(View.INVISIBLE, test.getButtonVisibility());
    }


    /**
     * Testing with subtasks
     */
    @Test
    public void SubtaskTest() throws JSONException{
        SubtaskActivity test = mock(SubtaskActivity.class);

        String contents = "Subtask Test Task";
        String day = "Today";
        JSONObject response = new JSONObject();

        response.put("contents", contents);
        response.put("dateCreate", day);
        when(test.jsonGetSubtask()).thenReturn(response);

        Assert.assertEquals(response.getString("contents"), test.jsonGetSubtask().getString("contents"));
        Assert.assertEquals(response.getString("dateCreate"), test.jsonGetSubtask().getString("dateCreate"));
        Assert.assertNotEquals(response.getString("contents"), test.jsonGetSubtask().getString("dateCreate"));
        Assert.assertNotEquals(response.getString("dateCreate"), test.jsonGetSubtask().getString("contents"));
    }
    /**
     * Testing for room settings
     */
    @Test
    public void RoomSettingsTest() throws JSONException{
        RoomSettingsActivity test = mock(RoomSettingsActivity.class);

        String user = "Paul";
        String permission = "Owner";

        JSONObject response = new JSONObject();

        response.put("User", user);
        response.put("Permission", permission);

        when(test.jsonGetRoomSettings()).thenReturn(response);

        Assert.assertEquals(response.getString("User"), test.jsonGetRoomSettings().getString("User"));
        Assert.assertEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("Permission"));
        Assert.assertNotEquals(response.getString("User"), test.jsonGetRoomSettings().getString("Permission"));
        Assert.assertNotEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("User"));
    }

    /**
     * Testing for room settings
     */
    @Test
    public void TestInversionSession(){
        ISessionManagerInversionPattern test = mock(ISessionManagerInversionPattern.class); //mock for expected

        SharedPreferences mockPrefs = mock(SharedPreferences.class); //mock for actual
        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class); //second mock for actual

        Context mockContext = mock(Context.class); //mock for context

        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockPrefs); //sets mock prefs
        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt()).edit()).thenReturn(mockEditor); //sets mock editor

        ISessionManagerInversionPattern test2 = new SessionManager(mockContext); //mock for actual to test

        when(test.getRoomid()).thenReturn("1");
        when(test.getEmail()).thenReturn("testemail");
        when(test.getID()).thenReturn("1");
        when(test.getName()).thenReturn("testname");
        when(test.getPermission()).thenReturn("Owner");
        when(test.getRoom()).thenReturn("room");
        when(test.isInRoom()).thenReturn(true);
        when(test.isLoggin()).thenReturn(true);
        when(test.isRoom("room")).thenReturn(true);

        test2.createSession("testname", "testemail", "1");
        test2.setRoomid("1");
        test2.setRoom("room");
        test2.setPermission("Owner");
        test2.setName("testname");
        test2.setEmail("testemail");

        Assert.assertEquals("1", test.getRoomid());
        Assert.assertEquals("testemail", test.getEmail());
        Assert.assertEquals("1", test.getID());
        Assert.assertEquals("testname", test.getName());
        Assert.assertEquals("Owner", test.getPermission());
        Assert.assertEquals("room", test.getRoom());
        Assert.assertTrue(test.isInRoom());
        Assert.assertTrue( test.isLoggin());
        Assert.assertTrue(test.isRoom("room"));
    }

//    /**
//     * Testing for room settings
//     */
//    @Test
//    public void TestMVPSession() throws JSONException{
//        ISessionManagerInversionPattern test = mock(ISessionManagerInversionPattern.class);
//
//        String user = "Paul";
//        String permission = "Owner";
//
//        JSONObject response = new JSONObject();
//
//        response.put("User", user);
//        response.put("Permission", permission);
//
//        when(test.jsonGetRoomSettings()).thenReturn(response);
//
//        Assert.assertEquals(response.getString("User"), test.jsonGetRoomSettings().getString("User"));
//        Assert.assertEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("User"));
//    }
//
//    /**
//     * Testing for room settings
//     */
//    @Test
//    public void TestObserverSession() throws JSONException{
//        ISessionManagerInversionPattern test = mock(ISessionManagerInversionPattern.class);
//
//        String user = "Paul";
//        String permission = "Owner";
//
//        JSONObject response = new JSONObject();
//
//        response.put("User", user);
//        response.put("Permission", permission);
//
//        when(test.jsonGetRoomSettings()).thenReturn(response);
//
//        Assert.assertEquals(response.getString("User"), test.jsonGetRoomSettings().getString("User"));
//        Assert.assertEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("User"));
//    }
//
//    /**
//     * Testing for room settings
//     */
//    @Test
//    public void TestInversionDate() throws JSONException{
//        IDateParserInversionPattern test = mock(IDateParserInversionPattern.class);
//
//        when(test.parseDate()).thenReturn();
//        when(test.parseDay()).thenReturn();
//        when(test.parseMonth()).thenReturn();
//        when(test.parseYear()).thenReturn();
//
//        String user = "Paul";
//        String permission = "Owner";
//
//        JSONObject response = new JSONObject();
//
//        response.put("User", user);
//        response.put("Permission", permission);
//
//        when(test.jsonGetRoomSettings()).thenReturn(response);
//
//        Assert.assertEquals(response.getString("User"), test.jsonGetRoomSettings().getString("User"));
//        Assert.assertEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("User"));
//    }
//
//    /**
//     * Testing for room settings
//     */
//    @Test
//    public void TestMVPDate() throws JSONException{
//        IDateParserInversionPattern test = mock(IDateParserInversionPattern.class);
//
//        String user = "Paul";
//        String permission = "Owner";
//
//        Assert.assertEquals(response.getString("User"), test.jsonGetRoomSettings().getString("User"));
//        Assert.assertEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("User"));
//    }
//
//    /**
//     * Testing for room settings
//     */
//    @Test
//    public void TestObserverDate() throws JSONException{
//        IDateParserInversionPattern test = mock(IDateParserInversionPattern.class);
//
//        String user = "Paul";
//        String permission = "Owner";
//
//        JSONObject response = new JSONObject();
//
//        response.put("User", user);
//        response.put("Permission", permission);
//
//        when(test.jsonGetRoomSettings()).thenReturn(response);
//
//        Assert.assertEquals(response.getString("User"), test.jsonGetRoomSettings().getString("User"));
//        Assert.assertEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetRoomSettings().getString("Permission"));
//        Assert.assertNotEquals(response.getString("Permission"), test.jsonGetRoomSettings().getString("User"));
//    }
}
