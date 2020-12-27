package ui;

import model.Customer;
import model.DayReservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

// This class is the booking table which will appear on the gui bottom panel to show the day reservation list.
public class BookingTable extends DefaultTableModel {
    private DayReservation dayReservation;


    public static final String[] COLUMN_NAMES = {"Customer Name",
            "Phone#",
            "Time",
            "PartySize"};

    private Object[][] data = {
            {"example", "57463746273", "9.00", "5"},
    };

    // EFFECTS: construct the BookingTable and load the dayreservation.
    public BookingTable(DayReservation dayReservation) {
        super(COLUMN_NAMES, 0);
        this.dayReservation = dayReservation;
    }

    // EFFECTS: update the booking table
    public void updateTable() {
        // for testing
//        JOptionPane.showMessageDialog(null,
//                " No reservation recorded.", "NOTE", JOptionPane.INFORMATION_MESSAGE);

        if (dayReservation.numberBooking() > 0) {
            removeAllRows();
            for (Customer next : dayReservation.convertArray()) {
                printCustomerinfo(next);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    " No reservation recorded.", "NOTE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // EFFECTS: prints out the name, phone number and party size of this customer
    public void printCustomerinfo(Customer c) {
        Vector<Object> rowVector = new Vector<>();
        rowVector.add(c.getCustomername());
        rowVector.add(c.getPhonenum());
        rowVector.add(c.getBookingTime());
        rowVector.add(c.getPartysize());
        super.addRow(rowVector);
    }

    // EFFECTS: remove all the rows
    private void removeAllRows() {
        while (getRowCount() > 0) {
            removeRow(0);
        }
    }
}
