package model;


import exception.AddCustomerInvalidException;
import exception.CustomerNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;

// This class instantiates an arraylist that adds all the customers booked on that day.
//note: in this class Booking means the same with Reservation, they are interchangeable.


public class DayReservation implements Writable {
    private ArrayList<Customer> dayreservation;
    private LocalDate ld;


    //EFFECT: Create a reservation list on a date, includes customers booked on that day
    public DayReservation(LocalDate d) {
        dayreservation = new ArrayList();
        ld = d;
    }


    // MODIFIES: this
    // EFFECTS: add new customer in this timeslot, throw AddCustomerInvalidException when the customer name and
    //          phone number is not valid,
    public void addCustomertoDay(Customer c) throws AddCustomerInvalidException {
        if (c.getCustomername().matches("[a-zA-Z]+") && c.getPhonenum().matches("\\d+")) {
            dayreservation.add(c);
        } else {
            throw new AddCustomerInvalidException("Invalid input info, Add Customer Failed.");
        }
    }


    // MODIFIES: this and Customer
    // EFFECTS: if customer is in the dayreservation list, changes the time which customer booked,
    // and return the new booking time
    // else throw CustomerNotFoundException
    public void changeBookTime(Customer customer, double newtime) throws CustomerNotFoundException {
        String verifyname = customer.getCustomername();
        String verifynumber = customer.getPhonenum();
        boolean verified = verifyBooking(verifyname, verifynumber);
        if (verified) {
            customer.setBookedTime(newtime);
        }
    }


    //below are getters for DayreservationTest

    // EFFECTS: return the number of bookings at this time.
    public int numberBooking() {
        return dayreservation.size();
    }


    // EFFECTS: if customer is in the dayreservation list given the customer name and phone number,
    // return true if the customer with the name and phone number is found at the day,
    // else throws CustomerNotFoundException.
    public boolean verifyBooking(String verifyname, String verifynumber) throws CustomerNotFoundException {
        for (Customer next : dayreservation) {
            if (next.getPhonenum().equals(verifynumber)
                    && next.getCustomername().equals(verifyname)) {
                return true;
            }
        }
        throw new CustomerNotFoundException("Customer: " + verifyname + " has no reservation.");
    }


    // EFFECTS: if customer is in the dayreservation list given the customer name and phone number,
    // given the customer name and phone number,
    // return true if the customer with the name and phone number is found at the day,
    // false if not.
    public Customer getVerifyCustomer(String verifyname, String verifynumber) throws CustomerNotFoundException {
        for (Customer next : dayreservation) {
            if (next.getPhonenum().equals(verifynumber)
                    && next.getCustomername().equals(verifyname)) {
                return next;
            }
        }
        throw new CustomerNotFoundException("Customer: " + verifyname + " has no reservation.");
    }


    // EFFECTS: return the time of a booking
    public double getVerifyTime(String verifyname, String verifynumber) {
        double find = 0;
        for (Customer next : dayreservation) {
            if (next.getPhonenum().equals(verifynumber)
                    && next.getCustomername().equals(verifyname)) {
                find = next.getBookingTime();
            }
        }
        return find;
    }

    // EFFECTS: return the list of customers in this day reservation
    public ArrayList<Customer> convertArray() {
        return dayreservation;
    }


    // EFFECTS: return the date of this day reservation

    public LocalDate getdate() {
        return ld;
    }


    // EFFECTS: return the customer in the index n of the list
    // note: used for testing
    public Customer customerIndex(int n) {
        return dayreservation.get(n);
    }

    // REQUIRES: c is non-null
    // EFFECTS: check if the customer is in the dayreservation.
    // Return true is the customer c is found in dayreservation, false if not.
    // note: used for testing
    public boolean checkContained(Customer c) {
        if (dayreservation.contains(c)) {
            return true;
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", ld);
        json.put("customers", customerToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray customerToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Customer c : dayreservation) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
