package ui;

import model.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is to perform the view reservation in a table function in the gui.
public class ViewListener implements ActionListener {
    private JButton button;
    private boolean alreadyEnabled = false;
    private UIBottomPane uiBottomPane;
    //    private ReservationTable res;
    private BookingTable bookingTable;

    //EFFECTS: instantiate a view listener to perform the action of view all customer bookings in dayreservation
    public ViewListener(UIBottomPane uiBottomPane, BookingTable bookingTable, JButton button) {
        this.button = button;
        this.uiBottomPane = uiBottomPane;
        this.bookingTable = bookingTable;

    }

    //Required by ActionListener.
    // Effect: Perform the AcctionEvent e of ViewListener. Update the displayed booking table on gui.
    public void actionPerformed(ActionEvent e) {
        bookingTable.updateTable();

        //___________________________For testing__________________________________
        doViewBooking();
    }


    // EFFECTS: conducts viewing a booking of the list of today's reservations.
    private void doViewBooking() {
        System.out.print("--------Reservation List------- ");
        printDayBooking();
    }

    // EFFECTS: print out all the customers booked on the day, if there is no customers, print none.
    public void printDayBooking() {

        if (uiBottomPane.dayReservation.numberBooking() > 0) {
            System.out.print("\n\nHere are the customer info On" + " " + uiBottomPane.dayReservation.getdate() + ":");
            for (Customer next : uiBottomPane.dayReservation.convertArray()) {
                printCustomerinfo(next);
            }
        } else {
            System.out.print("On " + uiBottomPane.dayReservation.getdate() + " there is ");
            System.out.println(" No reservation recorded.");
        }
    }

    // EFFECTS: prints out the name, phone number and party size of this customer on the console
    public void printCustomerinfo(Customer c) {
        System.out.println("\nTime: " + c.getBookingTime() + ", " + " Name: " + c.customername + ","
                + " Phone#: " + c.getPhonenum() + ","
                + " Party size: " + c.getPartysize());
    }
}

