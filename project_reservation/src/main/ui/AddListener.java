package ui;

import exception.AddCustomerInvalidException;
import model.Customer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is to perform the add function of in the gui.
public class AddListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private UITopPane uiTopPane;

    //EFFECTS: instantiate a add listener to perform the action of save customer booking in dayreservation
    public AddListener(UITopPane uiTopPane, JButton button) {
        this.button = button;
        this.uiTopPane = uiTopPane;
    }

    //Required by ActionListener.
    // Effect: Perform the AcctionEvent e of AddListener.Add the input info from user to the Booking table
    //         catch the AddCustomerInvalidException and NumberFormatException when the input form is invalid.
    public void actionPerformed(ActionEvent e) {

        try {
            String enteredName = uiTopPane.nameInput.getText().toLowerCase(); // read the entered name
            String enteredNumber = uiTopPane.phoneInput.getText().toLowerCase();// read the entered number
            double enteredtime = Double.parseDouble(uiTopPane.timeInput.getText());// read the entered time
            double validTime = checkEnteredTime(enteredtime);
            int enteredparty = Integer.parseInt(uiTopPane.partysizeInput.getText()); // read the entered party
            Customer customer = new Customer(enteredName, enteredNumber, validTime, enteredparty);
            uiTopPane.dayReservation.addCustomertoDay(customer);
            JOptionPane.showMessageDialog(null, "Reservation is recorded!",
                    "Recorded", JOptionPane.INFORMATION_MESSAGE);
        } catch (AddCustomerInvalidException addE) {
            popUp1();
        } catch (NumberFormatException numE) {
            popUp2();
        }
    }

    // EFFECTS: create the invalid input name and phone number pop-up window.
    private void popUp2() {
        JOptionPane.showMessageDialog(null,
                "Invalid input info. Double check the customer booking time and party size."
                        + "\n\t Booking time should be in the format --.-- (for example: 17.00)"
                        + "\n\t Party size should be numbers (for example: 6) ",
                "Add Customer Failed.", JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: create the invalid input time and party size pop-up window.
    private void popUp1() {
        JOptionPane.showMessageDialog(null,
                "Invalid input info. Double check the customer name and phone number. "
                        + "\n\t Customer name should be in alphabets (for example: Elisa)"
                        + "\n\t Phone number should be in numbers (for example: 5875998765) ",
                "Add Customer Failed.", JOptionPane.ERROR_MESSAGE);
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

    // Effect: enable the button
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

