//package model;
//
//import java.util.HashMap;
//import java.util.Map;
//
// ****** This class is unavailable currently, but will be needed in future development.******

//
//public class Reservation {
//    private DayReservation onetimeslot;
//    Map<Double, DayReservation> reservation;
//
//    public Reservation(double t, Customer c) {
//
//        reservation = new HashMap<>();
//        reservation.put(t, onetimeslot);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: if there is no Timeslot (list of Customers) associated with this time,
//    //           put the time in to the map and set up an empty Time slot and add
//    //           the customer,
//    //           else add the customer in to the list of customers in the Timeslot(Value),
//    //           along with the time (key).
//    //
//    public void addReservation(double time, Customer customer) {
//        DayReservation exist = reservation.get(time);
//        if (exist.numberBooking() == 0) {
//            DayReservation empty = new DayReservation(time);
//            addemptyslot(time, empty);
//        } else {
//            fillSlot(time, exist, customer);
//
//        }
//
//
//    }
//
//
//    // EFFECT: put time with an empty timeslot in to the map
//    public void addemptyslot(double t, DayReservation dayReservation) {
//
//        reservation.put(t, dayReservation);
//    }
//
//    // EFFECT: put time and corresponding timeslot into the map
//    public void fillSlot(double t, DayReservation dayReservation, Customer customer) {
//        DayReservation addcustomer = reservation.get(t);
//        dayReservation.addCustomertoDay(customer);
//    }
//}
