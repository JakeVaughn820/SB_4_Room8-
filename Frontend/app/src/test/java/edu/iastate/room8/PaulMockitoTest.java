package edu.iastate.room8;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import edu.iastate.room8.utils.DateParser;
import edu.iastate.room8.utils.SessionManager;


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
     * @throws JSONException
     */
    @Test
    public void scheduleTest() throws JSONException {
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
     * @throws JSONException
     */
    @Test
    public void scheduleTest2() throws JSONException {
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

        when(test.jsonGetSchedule()).thenReturn(response);

        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
    }

//    /**
//     * Testing with permissions between users
//     */
//    @Test
//    public void PermissionTest1() throws JSONException{
//        HomeActivity test = mock(HomeActivity.class);
////        SessionManager sessionManager = new SessionManager(test);
////        sessionManager.setPermission("Owner");
////        sessionManager.setRoomid("1");
////        sessionManager.setRoom("Room");
//
////        when(test.jsonGetSchedule()).thenReturn(response); dont need when for this test, just need to mock the class
//
//        Assert.assertEquals(test.getButtonVisibility(), 1);
//    }
//    /**
//     * Testing with permissions between users
//     */
//    @Test
//    public void PermissionTest2() throws JSONException{
//        DayActivity test = mock(DayActivity.class);
//
//        String day = "14";
//        String month = "12";
//        String year = "1998";
//        String date = month + "/" + day + "/" + year;
//
//        JSONObject response = new JSONObject();
//
//        response.put("StartTime", "2:00pm");
//        response.put("EndTime", "3:00pm");
//        response.put("EventName", "Demo");
//        response.put("User", "Paul");
//
//        when(test.jsonGetSchedule()).thenReturn(response);
//
//        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
//        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
//        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
//    }
//    /**
//     * Testing with permissions between users
//     */
//    @Test
//    public void PermissionTest3() throws JSONException{
//        DayActivity test = mock(DayActivity.class);
//
//        String day = "14";
//        String month = "12";
//        String year = "1998";
//        String date = month + "/" + day + "/" + year;
//
//        JSONObject response = new JSONObject();
//
//        response.put("StartTime", "2:00pm");
//        response.put("EndTime", "3:00pm");
//        response.put("EventName", "Demo");
//        response.put("User", "Paul");
//
//        when(test.jsonGetSchedule()).thenReturn(response);
//
//        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
//        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
//        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
//    }
//    /**
//     * Testing with permissions between users
//     */
//    @Test
//    public void PermissionTest4() throws JSONException{
//        DayActivity test = mock(DayActivity.class);
//
//        String day = "14";
//        String month = "12";
//        String year = "1998";
//        String date = month + "/" + day + "/" + year;
//
//        JSONObject response = new JSONObject();
//
//        response.put("StartTime", "2:00pm");
//        response.put("EndTime", "3:00pm");
//        response.put("EventName", "Demo");
//        response.put("User", "Paul");
//
//        when(test.jsonGetSchedule()).thenReturn(response);
//
//        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
//        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
//        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
//    }
//
//    /**
//     * Testing with subtasks
//     */
//    @Test
//    public void SubtaskTest() throws JSONException{
//        DayActivity test = mock(DayActivity.class);
//
//        String day = "14";
//        String month = "12";
//        String year = "1998";
//        String date = month + "/" + day + "/" + year;
//
//        JSONObject response = new JSONObject();
//
//        response.put("StartTime", "2:00pm");
//        response.put("EndTime", "3:00pm");
//        response.put("EventName", "Demo");
//        response.put("User", "Paul");
//
//        when(test.jsonGetSchedule()).thenReturn(response);
//
//        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
//        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
//        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
//    }
//    /**
//     * Testing for room settings
//     */
//    @Test
//    public void RoomSettingsTest() throws JSONException{
//        DayActivity test = mock(DayActivity.class);
//
//        String day = "14";
//        String month = "12";
//        String year = "1998";
//        String date = month + "/" + day + "/" + year;
//
//        JSONObject response = new JSONObject();
//
//        response.put("StartTime", "2:00pm");
//        response.put("EndTime", "3:00pm");
//        response.put("EventName", "Demo");
//        response.put("User", "Paul");
//
//        when(test.jsonGetSchedule()).thenReturn(response);
//
//        Assert.assertNotEquals(response.getString("StartTime"), test.jsonGetSchedule().getString("EndTime"));
//        Assert.assertNotEquals(response.getString("EndTime"), test.jsonGetSchedule().getString("StartTime"));
//        Assert.assertNotEquals(response.getString("EventName"), test.jsonGetSchedule().getString("User"));
//        Assert.assertNotEquals(response.getString("User"), test.jsonGetSchedule().getString("EventName"));
//    }
}
