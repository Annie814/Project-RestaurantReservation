package persistence;

import model.DayReservation;
import model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

// note: the design of Json ReaderTest and WriterTest structure is referred to
// JsonSerialzation Demo.
public class JsonTest {
    protected void checkCustomer(String name, String phnum, double time, int partysize, Customer c) {
        assertEquals(name, c.getCustomername());
        assertEquals(phnum, c.getPhonenum());
        assertEquals(time, c.getBookingTime());
        assertEquals(partysize, c.getPartysize());

    }
}
