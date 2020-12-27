package model;

import exception.AddCustomerInvalidException;
import exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DayReservationTest {
    private DayReservation date1130;
    private LocalDate date = LocalDate.of( 2020 , 11 , 30 );

    @BeforeEach
    //set up a day reservation for Nov.30th
    public void setUp() {
        date1130 = new DayReservation(date);
    }


    @Test
    public void testaddCustomertoDay1() {
        // first customer tim
        Customer tim = new Customer("Tim","13256357",19.00,4);
        try {
            date1130.addCustomertoDay(tim);
        } catch (AddCustomerInvalidException e) {
            fail("expect to have added tim in the reservation");
        }
        assertEquals(1, date1130.numberBooking());
        assertTrue(date1130.checkContained(tim));
        Customer checktim = (date1130.customerIndex(0));
        assertEquals("Tim", checktim.getCustomername());

        // 2nd customer will
        Customer will = new Customer("Will","122342347",12.30,3);
        try {
            date1130.addCustomertoDay(will);
        } catch (AddCustomerInvalidException e) {
            fail("expect to have added will in the reservation");
        }
        assertEquals(2, date1130.numberBooking());
        assertTrue(date1130.checkContained(will));
        Customer checkwill = (date1130.customerIndex(1));
        assertEquals("Will", checkwill.getCustomername());

    }
    @Test
    public void testaddCustomertoDay2() {
        // first customer tim
        Customer tim = new Customer("1231","13256357",19.00,4);
        try {
            date1130.addCustomertoDay(tim);
            fail("expect have AddCustomerInvalidException thrown");
        } catch (AddCustomerInvalidException e) {
            // expected
        }

        // 2nd customer will
        Customer will = new Customer("!@#","adasdc",12.30,3);
        try {
            date1130.addCustomertoDay(will);
            fail("expect have AddCustomerInvalidException thrown");
        } catch (AddCustomerInvalidException e) {
            //expected
        }

        // 3rd customer tom

        Customer tom = new Customer("Tom","@#$%^%%$",19.00,4);
        try {
            date1130.addCustomertoDay(tom);
            fail("expect have AddCustomerInvalidException thrown");
        } catch (AddCustomerInvalidException e) {
           // expected
        }
    }

    // note: the next three methods are there to support test1, addCustomerToDay.
    // note: this is to check the size of dayreservation.
    @Test
    public void testcheckNumBookg() {
        //0 booking
        assertEquals(0,date1130.numberBooking());

        Customer tim = new Customer("Tim","13256357",19.00,4);
        date1130.addCustomertoDay(tim);
        assertEquals(1,date1130.numberBooking());
        //2 booking

        Customer andy1 = new Customer("Andy","32323232",18.00,5);
        date1130.addCustomertoDay(andy1);
        assertEquals(2,date1130.numberBooking());
    }

    // note: this is to check whether the customer is added to the correct position in the list dayreservation.
    @Test
    public void testcustomerIndex() {
        Customer tim = new Customer("Tim","13256357",19.00,4);
        Customer andy1 = new Customer("Andy","32323232",18.00,5);
        Customer will1 = new Customer("Will","98765432",19.30,3);
        date1130.addCustomertoDay(tim);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(will1);

        Customer gettim = date1130.customerIndex(0);
        Customer getandy1 = date1130.customerIndex(1);
        Customer getwill1 = date1130.customerIndex(2);

        boolean checktim = gettim.equals(tim);
        boolean checkandy1 = getandy1.equals(andy1);
        boolean checkwill1 = getwill1.equals(will1);

        assertTrue(checktim);
        assertTrue(checkandy1);
        assertTrue(checkwill1);


    }

    // note: this is to check if the dayreservation list contains a customer.
    @Test
    public void testcheckContained() {
        Customer tim = new Customer("Tim","13256357",19.00,4);
        Customer andy1 = new Customer("Andy","32323232",18.00,5);
        date1130.addCustomertoDay(tim);
        date1130.addCustomertoDay(andy1);
        //true cases
        assertTrue(date1130.checkContained(tim));
        assertTrue(date1130.checkContained(andy1));
        //false cases
        Customer will = new Customer("Will","10000057",19.00,4);
        Customer john = new Customer("John","32398787",18.00,4);

        assertFalse(date1130.checkContained(will));
        assertFalse(date1130.checkContained(john));


    }

    @Test
    public void testchangeBookTime1() {
        Customer tim = new Customer("Tim", "13256357", 19.00, 5);
        Customer andy1 = new Customer("Andy", "32323232", 18.00, 2);
        date1130.addCustomertoDay(tim);
        date1130.addCustomertoDay(andy1);

        try {
            date1130.changeBookTime(tim, 11.00);
            assertEquals(11.00, tim.getBookingTime());
        } catch (CustomerNotFoundException e) {
            fail("expect to have tim's reservation.");
        }
        try {
            date1130.changeBookTime(andy1, 10.00);
            assertEquals(10.00, andy1.getBookingTime());
        } catch (CustomerNotFoundException e) {
            fail("expect to have andy's reservation.");
        }
    }

    @Test
    public void testchangeBookTime2() {
        Customer tim = new Customer("Tim", "13256357", 19.00, 5);
        Customer will = new Customer("Will", "10000057", 19.00, 4);
        Customer will2 = new Customer("Wil", "10000057", 19.00, 4);
        Customer john = new Customer("John", "32398787", 18.00, 4);
        Customer jonh2 = new Customer("John", "32323232", 18.00, 2);
        date1130.addCustomertoDay(will);
        date1130.addCustomertoDay(john);

        try {
            date1130.changeBookTime(tim, 11.00);
            fail("expect to not have tim's reservation.");
        } catch (CustomerNotFoundException e) {
            //expected
        }

        try {
            date1130.changeBookTime(jonh2, 10.00);
            fail("expect to not have jonh's reservation.");

        } catch (CustomerNotFoundException e) {
            //expected
        }

        try {
            date1130.changeBookTime(will2, 10.00);
            fail("expect to not have will's reservation.");

        } catch (CustomerNotFoundException e) {
            //expected
        }

        assertFalse(tim.getBookingTime() == 11.00);
        assertFalse(john.getBookingTime() == 10.00);
        assertFalse(will.getBookingTime() == 10.00);
    }

    @Test
    public void testverifyBooking1() {
        //create customers, some of them has same name and booking time but with distinct phone number.
        //same name, different time
        Customer tim1 = new Customer("Tim", "13256357", 10.30, 2);
        Customer tim2 = new Customer("Tim", "12345678", 10.00, 2);
        //same name same time
        Customer will1 = new Customer("Will", "98765432", 19.30, 3);
        Customer will2 = new Customer("Will", "10101010", 19.30, 4);
        //different name same time
        Customer andy1 = new Customer("Andy", "32323232", 18.00, 3);
        Customer peter1 = new Customer("Peter", "66666666", 18.00, 5);
        //different name different time
        Customer tom1 = new Customer("Tom", "09090909", 9.20, 4);
        Customer jim1 = new Customer("Jim", "87687687", 9.20, 5);

        //add them to the day reservation
        date1130.addCustomertoDay(tim1);
        date1130.addCustomertoDay(tim2);
        date1130.addCustomertoDay(will1);
        date1130.addCustomertoDay(will2);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(peter1);
        date1130.addCustomertoDay(tom1);
        date1130.addCustomertoDay(jim1);

        //check if verification will process successfully

        //exception should NOT be thrown cases:

        try {
            date1130.verifyBooking("Tim", "13256357");
            assertTrue(date1130.verifyBooking("Tim", "13256357"));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Tim's reservation.");
        }
        try {
            date1130.verifyBooking("Will", "98765432");
            assertTrue(date1130.verifyBooking("Will", "98765432"));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Will's reservation.");
        }
        try {
            date1130.verifyBooking("Andy", "32323232");
            assertTrue(date1130.verifyBooking("Andy", "32323232"));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Andy's reservation.");
        }
        try {
            date1130.verifyBooking("Peter", "66666666");
            assertTrue(date1130.verifyBooking("Peter", "66666666"));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Peter's reservation.");
        }
        try {
            date1130.verifyBooking("Jim", "87687687");
            assertTrue(date1130.verifyBooking("Jim", "87687687"));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Jim's reservation.");
        }
    }

    @Test
    public void testverifyBooking2() {
        //create customers, some of them has same name and booking time but with distinct phone number.
        //same name, different time
        Customer tim1 = new Customer("Tim", "13256357", 10.30, 2);
        Customer tim2 = new Customer("Tim", "12345678", 10.00, 2);
        //same name same time
        Customer will1 = new Customer("Will", "98765432", 19.30, 3);
        Customer will2 = new Customer("Will", "10101010", 19.30, 4);
        //different name same time
        Customer andy1 = new Customer("Andy", "32323232", 18.00, 3);
        Customer peter1 = new Customer("Peter", "66666666", 18.00, 5);
        //different name different time
        Customer tom1 = new Customer("Tom", "09090909", 9.20, 4);
        Customer jim1 = new Customer("Jim", "87687687", 9.20, 5);

        //add them to the day reservation
        date1130.addCustomertoDay(tim1);
        date1130.addCustomertoDay(tim2);
        date1130.addCustomertoDay(will1);
        date1130.addCustomertoDay(will2);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(peter1);
        date1130.addCustomertoDay(tom1);
        date1130.addCustomertoDay(jim1);

        //check if verification will process successfully

        //exception should be thrown cases:

        try {
            date1130.verifyBooking("Marry", "13256357");
            fail("expect to not have Mary's reservation.");
        } catch (CustomerNotFoundException e) {
        }

        try {
            date1130.verifyBooking("Elisa", "88888888");
            fail("expect to not have Elisa's reservation.");
        } catch (CustomerNotFoundException e) {
        }

        try {
            date1130.verifyBooking("Tim", "1322222227");
            fail("expect to not have this reservation.");
        } catch (CustomerNotFoundException e) {
        }

        try {
            date1130.verifyBooking("Andy", "1111132");
            fail("expect to not have this reservation.");
        } catch (CustomerNotFoundException e) {
        }

    }

    @Test
    public void testgetVerifyCustomer1() {
        //create customers, some of them has same name and booking time but with distinct phone number.
        //same name, different time
        Customer tim1 = new Customer("Tim", "13256357", 10.30, 2);
        Customer tim2 = new Customer("Tim", "12345678", 10.00, 2);
        //same name same time
        Customer will1 = new Customer("Will", "98765432", 19.30, 3);
        Customer will2 = new Customer("Will", "10101010", 19.30, 4);
        //different name same time
        Customer andy1 = new Customer("Andy", "32323232", 18.00, 3);
        Customer peter1 = new Customer("Peter", "66666666", 18.00, 5);
        //different name different time
        Customer tom1 = new Customer("Tom", "09090909", 9.20, 4);
        Customer jim1 = new Customer("Jim", "87687687", 9.20, 5);

        //add them to the day reservation
        date1130.addCustomertoDay(tim1);
        date1130.addCustomertoDay(tim2);
        date1130.addCustomertoDay(will1);
        date1130.addCustomertoDay(will2);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(peter1);
        date1130.addCustomertoDay(tom1);
        date1130.addCustomertoDay(jim1);

        //check if verification will process successfully

        //exception should NOT be thrown cases:

        try {
            date1130.getVerifyCustomer("Tim", "13256357");
            assertTrue(tim1.equals(date1130.getVerifyCustomer("Tim", "13256357")));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Tim's reservation.");
        }
        try {
            date1130.getVerifyCustomer("Will", "98765432");
            assertTrue(will1.equals(date1130.getVerifyCustomer("Will", "98765432")));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Will's reservation.");
        }
        try {
            date1130.getVerifyCustomer("Andy", "32323232");
            assertTrue(andy1.equals(date1130.getVerifyCustomer("Andy", "32323232")));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Andy's reservation.");
        }
        try {
            date1130.getVerifyCustomer("Peter", "66666666");
            assertTrue(peter1.equals(date1130.getVerifyCustomer("Peter", "66666666")));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Peter's reservation.");
        }
        try {
            date1130.getVerifyCustomer("Jim", "87687687");
            assertTrue(jim1.equals(date1130.getVerifyCustomer("Jim", "87687687")));
        } catch (CustomerNotFoundException e) {
            fail("expect to have Jim's reservation.");
        }
    }

    //public Customer getVerifyCustomer(String verifyname, String verifynumber) throws CustomerNotFoundException{
    //        for (Customer next : dayreservation) {
    //            if (next.getPhonenum().equals(verifynumber)
    //                    && next.getCustomername().equals(verifyname)) {
    //                return next;
    //            }
    //        }
    //        throw new CustomerNotFoundException("Customer: " + verifyname + " has no reservation.");
    //    }

    @Test
    public void testgetVerifyCustomer2() {
        //create customers, some of them has same name and booking time but with distinct phone number.
        //same name, different time
        Customer tim1 = new Customer("Tim", "13256357", 10.30, 2);
        Customer tim2 = new Customer("Tim", "12345678", 10.00, 2);
        //same name same time
        Customer will1 = new Customer("Will", "98765432", 19.30, 3);
        Customer will2 = new Customer("Will", "10101010", 19.30, 4);
        //different name same time
        Customer andy1 = new Customer("Andy", "32323232", 18.00, 3);
        Customer peter1 = new Customer("Peter", "66666666", 18.00, 5);
        //different name different time
        Customer tom1 = new Customer("Tom", "09090909", 9.20, 4);
        Customer jim1 = new Customer("Jim", "87687687", 9.20, 5);

        //add them to the day reservation
        date1130.addCustomertoDay(tim1);
        date1130.addCustomertoDay(tim2);
        date1130.addCustomertoDay(will1);
        date1130.addCustomertoDay(will2);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(peter1);
        date1130.addCustomertoDay(tom1);
        date1130.addCustomertoDay(jim1);

        //check if verification will process successfully

        //exception should be thrown cases:

        try {
            date1130.getVerifyCustomer("Marry", "13256357");
            fail("expect to not have Mary's reservation.");
        } catch (CustomerNotFoundException e) {
        }
        try {
            date1130.getVerifyCustomer("Elisa", "88888888");
            fail("expect to not have Elisa's reservation.");
        } catch (CustomerNotFoundException e) {
        }
    }

    @Test
    public void testgetVerifyTime() {
        //create customers, some of them has same name and booking time but with distinct phone number.
        //same name, different time
        Customer tim1 = new Customer("Tim", "13256357", 10.30, 5);
        Customer tim2 = new Customer("Tim", "12345678", 10.00, 1);
        //same name same time
        Customer will1 = new Customer("Will", "98765432", 19.30, 2);
        Customer will2 = new Customer("Will", "10101010", 19.30, 4);
        //different name same time
        Customer andy1 = new Customer("Andy", "32323232", 18.00, 3);
        Customer peter1 = new Customer("Peter", "66666666", 18.00, 2);
        //different name different time
        Customer tom1 = new Customer("Tom", "09090909", 9.20, 3);
        Customer jim1 = new Customer("Jim", "87687687", 9.20, 4);

        //add them to the day reservation
        date1130.addCustomertoDay(tim1);
        date1130.addCustomertoDay(tim2);
        date1130.addCustomertoDay(will1);
        date1130.addCustomertoDay(will2);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(peter1);
        date1130.addCustomertoDay(tom1);
        date1130.addCustomertoDay(jim1);

        //check if verification will process successfully
        assertEquals(10.30,date1130.getVerifyTime("Tim","13256357"));
        assertEquals(10.00,date1130.getVerifyTime("Tim","12345678"));
        assertEquals(19.30,date1130.getVerifyTime("Will","98765432"));
        assertEquals(18.00,date1130.getVerifyTime("Andy","32323232"));
        assertEquals(18.00,date1130.getVerifyTime("Peter","66666666"));
        assertEquals(9.20,date1130.getVerifyTime("Jim","87687687"));

        //false cases:
        assertEquals(0.00,date1130.getVerifyTime("Marry","13256357"));
        assertEquals(0.00,date1130.getVerifyTime("Elisa","88888888"));
    }

    @Test
    public void testgetdate(){
        LocalDate theDate = date1130.getdate();
        assertEquals(date, theDate);
    }

    @Test
    public void testconvertArray(){
        //create some customers and add them to date1130.
        //same name, different time
        Customer tim1 = new Customer("Tim","13256357",10.30,2);
        Customer tim2 = new Customer("Tim","12345678",10.00,2);
        //same name same time
        Customer will1 = new Customer("Will","98765432",19.30,3);
        Customer will2 = new Customer("Will","10101010",19.30,4);
        //different name same time
        Customer andy1 = new Customer("Andy","32323232",18.00,5);
        Customer peter1 = new Customer("Peter","66666666",18.00,5);
        //different name different time
        Customer tom1 = new Customer("Tom","09090909",9.20,4);
        Customer jim1 = new Customer("Jim","87687687",9.20,3);

        date1130.addCustomertoDay(tim1);
        date1130.addCustomertoDay(tim2);
        date1130.addCustomertoDay(will1);
        date1130.addCustomertoDay(will2);
        date1130.addCustomertoDay(andy1);
        date1130.addCustomertoDay(peter1);
        date1130.addCustomertoDay(tom1);
        date1130.addCustomertoDay(jim1);

        //convert them to a list of customers
        ArrayList<Customer> convertedList = date1130.convertArray();
        //actual list of customers
        ArrayList<Customer> actualList = new ArrayList<>();
        actualList.add(tim1);
        actualList.add(tim2);
        actualList.add(will1);
        actualList.add(will2);
        actualList.add(andy1);
        actualList.add(peter1);
        actualList.add(tom1);
        actualList.add(jim1);
        //check if the converted list is equal to the actual list of customers.
        boolean check = convertedList.equals(actualList);
        assertTrue(check);

    }
}