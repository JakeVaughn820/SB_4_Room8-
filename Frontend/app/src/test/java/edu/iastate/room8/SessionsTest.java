package edu.iastate.room8;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import android.content.Context;

import java.util.HashMap;

import edu.iastate.room8.utils.SessionManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionsTest {
    @Mock
    Context mockContext;
    @Mock
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;

    private SessionManager sessionManager;

    @Before
    public void before() throws Exception {

        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockPrefs);
        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt()).edit()).thenReturn(mockEditor);

        sessionManager = new SessionManager(mockContext);
    }

    @Test
    public void getUserDetailTest() {
        Mockito.when(mockPrefs.getString("NAME", null)).thenReturn("Joe");
        Mockito.when(mockPrefs.getString("EMAIL", null)).thenReturn("Joe@email.com");
        Mockito.when(mockPrefs.getString("ID", null)).thenReturn("34");
        Mockito.when(mockPrefs.getString("ROOM", null)).thenReturn("8");

        assertEquals("Joe", sessionManager.getUserDetail().get("NAME"));
        assertEquals("Joe@email.com", sessionManager.getUserDetail().get("EMAIL"));
        assertEquals("34", sessionManager.getUserDetail().get("ID"));
        assertEquals("8", sessionManager.getUserDetail().get("ROOM"));

    }

    @Test
    public void createSessionTest() {
        sessionManager.createSession("Jack", "Jack@email.com", "35");

        assertEquals("Jack", sessionManager.getUserDetail().get("NAME"));
        assertEquals("Jack@email.com", sessionManager.getUserDetail().get("EMAIL"));
        assertEquals("35", sessionManager.getUserDetail().get("ID"));
        assertEquals(null, sessionManager.getUserDetail().get("ROOM"));
    }
}

