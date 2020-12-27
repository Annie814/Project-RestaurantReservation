package ui;


import exception.CustomerNotFoundException;
import model.Customer;
import model.DayReservation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is to perform the verify customer's reservation function in the gui.
public class VerifyListener implements ActionListener, DocumentListener {
    private JButton button;
    private boolean alreadyEnabled = false;
    private UITopPane uiTopPane;

    //EFFECTS: instantiate a verify listener to perform the action of verify customer booking in dayreservation
    public VerifyListener(UITopPane uiTopPane, JButton button) {
        this.button = button;
        this.uiTopPane = uiTopPane;

    }

    //Required by ActionListener.
    // Effect: Perform the ActionEvent e of VerifyListener. Catch CustomerNotFoundException exception
    //         if the customer is not found in the current reservation list.

    public void actionPerformed(ActionEvent e) {
        String enteredName = uiTopPane.nameInput.getText().toLowerCase(); // read the entered name
        String enteredNumber = uiTopPane.phoneInput.getText().toLowerCase();// read the entered number

        try {
            uiTopPane.dayReservation.verifyBooking(enteredName, enteredNumber);
            JOptionPane.showMessageDialog(null,
                    "Customer, " + enteredName + " has a reservation at "
                            + uiTopPane.dayReservation.getVerifyTime(enteredName, enteredNumber) + "0.",
                    "Has reservation", JOptionPane.INFORMATION_MESSAGE);
        } catch (CustomerNotFoundException exception) {
            JOptionPane.showMessageDialog(null,
                    "This Customer does not have a reservation",
                    "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }


    //Required by DocumentListener.
    // Effect: insert any Updates
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    //Required by DocumentListener.
    // Effect: remove any Updates
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }

    //Required by DocumentListener.
    // Effect: change any Updates
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // Effect: enable the button
    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }

    // Effect: if the text field is not empty (length greater than 0), return true, else return false
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }
}
