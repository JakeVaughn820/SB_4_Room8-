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

    JSONObject jsonRequest;
    JSONObject jsonReturn;
    BulletinActivity test = Mockito.mock(BulletinActivity.class);

    @Before
    public void setup(){

    }

    @Test
    public void jsonParseTest_returnsTrue() throws JSONException{

        String User = "Paul";
        String Contents = "IsCool";

        JSONObject response = new JSONObject();

        response.put("User",User);
        response.put("Contents", Contents);

        Mockito.doCallRealMethod().when(test).jsonParse();
        test.jsonParse();
        Assert.assertEquals(User,response.getString("User"));
    }
}
