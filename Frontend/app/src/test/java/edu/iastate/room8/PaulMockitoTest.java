package edu.iastate.room8;

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
import org.mockito.runners.MockitoJUnitRunner;

public class PaulMockitoTest {
    @Test
    public void test1() {
        assertEquals(1, 1);
    }

    @Test
    public void doesThisClassSuck(){
        assertEquals("True", "True");
    }
}
