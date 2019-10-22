package edu.iastate.room8;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;

import edu.iastate.room8.utils.SessionManager;

import static org.mockito.Mockito.*;

public class SessionsTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void createSessionTest() throws JSONException {
        SessionManager session = mock(SessionManager.class);

        session.createSession("Joe", "Joe@email.com", "34");

        HashMap<String, String> user = new HashMap<>();
        user.put("NAME", "Joe");
        user.put("EMAIL", "Joe@email.com");
        user.put("ID", "34");
        user.put("ROOM", null);

        when(session.getUserDetail()).thenReturn(user);

        Assert.assertEquals(user.get("EMAIL"), session.getUserDetail().get("NAME"));
        Assert.assertEquals(user.get("NAME"), session.getUserDetail().get("EMAIL"));
        Assert.assertEquals(user.get("ID"), session.getUserDetail().get("ID"));
        Assert.assertEquals(user.get("ROOM"), session.getUserDetail().get("ROOM"));
    }
}

