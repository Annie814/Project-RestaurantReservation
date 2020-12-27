package persistence;

import model.Customer;
import model.DayReservation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DayReservation res = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRes.json");
        try {
            DayReservation res = reader.read();
            assertTrue(LocalDate.of(2020, 10, 30).isEqual(res.getdate()));
            assertEquals(0, res.numberBooking());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRes.json");
        try {
            DayReservation res = reader.read();

            assertTrue(LocalDate.of(2020, 10, 27).isEqual(res.getdate()));
            List<Customer> customerList = res.convertArray();
            assertEquals(2, customerList.size());
            checkCustomer("Marry", "5349870987", 11.30, 4, customerList.get(0));
            checkCustomer("Tim", "12345678", 10.00, 2, customerList.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}