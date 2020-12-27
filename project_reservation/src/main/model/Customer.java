package model;

// the class instantiates a single customer with their full name, phone number，the time they will com。
// and the total number of people coming(include this customer),

import persistence.Writable;
import org.json.JSONObject;

public class Customer implements Writable {
    public String customername;
    private String phonenum;
    private double bookedTime;
    private int partysize;

    // REQUIRES: name and phone have a non-zero length and its first letter should be capitalized.
    // 23 >= time >= 8  and time can be booked every ten minutes of a day, size >0
    // EFFECTS: Customer has a profile set up with its name, phone number and the time they will come
    // and the party size

    public Customer(String name, String phone, double time, int size) {
        customername = name;
        phonenum = phone;
        bookedTime = time;
        partysize = size;
    }

    //EFFECTS: return the customer name

    public String getCustomername() {
        return this.customername;
    }


    //EFFECTS: return the customer phone number

    public String getPhonenum() {
        return this.phonenum;
    }


    // EFFECTS: return the booking time for a customer.
    public double getBookingTime() {
        return bookedTime;
    }

    // EFFECTS: return the party size for number of people that will come
    public int getPartysize() {
        return partysize;
    }


    // MODIFIES: this
    // EFFECTS: set the time for customer booking
    public void setBookedTime(double time) {
        bookedTime = time;
    }

    // REQUIRES: num is an integer greater than 0
    // MODIFIES: this
    // EFFECTS: set the party size for number of people that will come
    public void setPartysize(int num) {
        partysize = num;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("customername", customername);
        json.put("phonenum", phonenum);
        json.put("bookedtime", bookedTime);
        json.put("partysize", partysize);
        return json;
    }
}


// note: the following is not necessary for this phase, but is there, in case later development
    /*// EFFECTS: set the name for customer booking

    public void setCustomername(String name) {
        customername = name;
    }

    // EFFECTS: set the name for customer booking
    public void setPhonenum(String number) {
        phonenum = number;
    }

    */


