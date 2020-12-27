package model;

import com.sun.corba.se.impl.encoding.CDROutputObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer marry;

    @BeforeEach
    public void setUp() {
        marry = new Customer("Marry", "5349870987", 11.30,4);
    }

    @Test
    public void testgetCustomername() {
        assertEquals("Marry", marry.getCustomername());
    }

    @Test
    public void testgetPhonenum() {assertEquals("5349870987", marry.getPhonenum());
    }

    @Test
    public void testgetBookingTime() {
        assertEquals(11.30, marry.getBookingTime());
    }

    @Test
    public void testgetPartysize() {
        assertEquals(4, marry.getPartysize());
    }

    @Test
    public void testsetBookedTime() {
        marry.setBookedTime(14.00);
        assertEquals(14.00, marry.getBookingTime());
    }

    @Test
    public void testsetPartysize() {
        marry.setPartysize(3);
        assertEquals(3, marry.getPartysize());
    }

}