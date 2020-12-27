package persistence;

import model.Customer;
import model.DayReservation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            DayReservation wr = new DayReservation(LocalDate.of(2020, 10, 25));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            DayReservation res = new DayReservation(LocalDate.of(2020, 10, 26));
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRes.json");
            writer.open();
            writer.write(res);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRes.json");
            res = reader.read();
            assertEquals(LocalDate.of(2020, 10, 26), res.getdate());
            assertEquals(0, res.numberBooking());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            DayReservation res = new DayReservation(LocalDate.of(2020, 10, 27));
            res.addCustomertoDay(new Customer("Marry", "5349870987", 11.30, 4));
            res.addCustomertoDay(new Customer("Tim", "12345678", 10.00, 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRes.json");
            writer.open();
            writer.write(res);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRes.json");
            res = reader.read();
            assertEquals(LocalDate.of(2020, 10, 27), res.getdate());
            List<Customer> customerList = res.convertArray();
            assertEquals(2, customerList.size());
            checkCustomer("Marry", "5349870987", 11.30, 4, customerList.get(0));
            checkCustomer("Tim", "12345678", 10.00, 2, customerList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}