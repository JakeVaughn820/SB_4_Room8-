package edu.iastate.room8;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

public class PaulMockitoTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void simpleJUNITTest() {
        assertEquals(1, 1);
    } //Makes sure class works as expected

    @Test
    public void scheduleTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        ScheduleActivity test = mock(ScheduleActivity.class);
        JsonParser jsonParser = new JsonParser();
        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String day = "12";
        String month = "14";
        String year = "1998";
        String date = "12/14/1998";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        when(test.callDateParser()).thenReturn(date);
    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        Assert.assertEquals(date, test.callDateParser());
    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        //when(test.getResponse(usernameCorrect,passwordCorrect)).thenReturn(response);

        //Assert.assertEquals(testLogInSuccess.tryLogin(usernameCorrect,passwordCorrect,test),response.getBoolean("loginSuccess"));
    }
}
