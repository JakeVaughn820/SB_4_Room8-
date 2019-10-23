package edu.iastate.room8;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import android.content.Context;

import java.util.HashMap;

import edu.iastate.room8.utils.SessionManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SessionsTest {

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    Context context;
    SessionManager sessionManager;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void before() throws Exception {
        this.sharedPrefs = Mockito.mock(SharedPreferences.class);
        this.context = Mockito.mock(Context.class);
        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs);

    }


    @Test
    public void createSessionTest() throws JSONException {
        sessionManager = new SessionManager(context);
        Mockito.when(sharedPrefs.getString(anyString(), anyString())).thenReturn("foobar");


        assertEquals("Joe", sessionManager.getUserDetail().get("NAME"));

    }
}

