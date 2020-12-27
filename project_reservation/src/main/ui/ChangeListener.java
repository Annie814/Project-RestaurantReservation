package ui;

import exception.CustomerNotFoundException;
import model.Customer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is to perform the change function of in the gui.
public class ChangeListener implements ActionListener, DocumentListener {

    private boolean alreadyEnabled = false;
    private JButton button;
    private UITopPane uiTopPane;

    //EFFECTS: instantiate a change listener to perform the action of change customer booking in dayreservation
    public ChangeListener(UITopPane uiTopPane, JButton button) {
        this.button = button;
        this.uiTopPane = uiTopPane;

    }

    //Required by ActionListener.
    // EFFECTS: Perform the ActionEvent e of ChangeListener. Catch CustomerNotFoundException exception
    //         if the customer is not found in the current reservation list.

    public void actionPerformed(ActionEvent e) {
        String enteredName = uiTopPane.nameInput.getText().toLowerCase(); // read the entered name
        String enteredNumber = uiTopPane.phoneInput.getText().toLowerCase();// read the entered number

        try {
            Customer theCustomer = uiTopPane.dayReservation.getVerifyCustomer(enteredName, enteredNumber);
            String inputTime = JOptionPane.showInputDialog("Please enter the new booking time."); //popup enternewtime.

            double enteredtime = Double.parseDouble(inputTime);// read the entered time
            double validTime = checkEnteredTime(enteredtime);
            double oldtime = theCustomer.getBookingTime();

            uiTopPane.dayReservation.changeBookTime(theCustomer, validTime);

            JOptionPane.showMessageDialog(null,
                    "We are changing the Customer, " + theCustomer.getCustomername()
                            + "'s booking time from " + oldtime + " to " + validTime,
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (CustomerNotFoundException exception) {
            JOptionPane.showMessageDialog(null,
                    "Cannot Change the Booking Time. This Customer is not found in the reservations.",
                    "Alert", JOptionPane.ERROR_MESSAGE);
        }

    }


    //Required by DocumentListener.
    // EFFECTS: Insert any Updates
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    //Required by DocumentListener.
    // EFFECTS: remove any Updates
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }

    //Required by DocumentListener.
    // EFFECTS: change any Updates
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // EFFECTS: enable the button
    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }

    // EFFECTS: if the text field is not empty (length greater than 0), return true, else return false
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }

    // EFFECTS: check if the reservation time is valid.
    private double checkEnteredTime(double time) {
        while (8 > time || time > 23) {
            String inputTime = JOptionPane.showInputDialog(
                    "This booking time is not valid. Reservation failed."
                            + "\n Enter the booking time again:"); //popup enternewtime.
            time = Double.parseDouble(inputTime);// read the entered time
        }
        return time;

    }
}