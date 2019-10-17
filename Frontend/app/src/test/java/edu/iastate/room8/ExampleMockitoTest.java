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

    JSONObject json;
    BulletinActivity test = Mockito.mock(BulletinActivity.class);

    @Before
    public void setup(){
        json = new JSONObject();
    }

    @Test
    public void jsonParseTest_returnsTrue() throws JSONException{
        String User = "Paul";
        String Content = "Jake";
        json.put("User", User);
        json.put("Content", Content);
        //when((test.jsonParse()).thenReturn(json.getString(User)));

    }
}
