package edu.iastate.room8;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;



public class ExampleMockitoTest { //will test
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    ScheduleActivity sch = new ScheduleActivity();
    DateParser test = Mockito.mock(DateParser.class);

//    @Before
//    public void setup(){
//        String temp = "10/10/2019";
//        when(DateParser.parseDate(10, 10, 2019)).thenReturn(temp);
//    }

    @Test
    public void jsonParseTest_returnsTrue() {
        String temp = "10/17/2019";
        when(test.parseDate(10, 10, 2019)).thenReturn(temp);
        String returned = sch.callDateParser(10, 10, 2019);
        assertEquals("10/17/2019", returned);
    }
}
