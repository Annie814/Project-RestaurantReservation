package ui;

import model.DayReservation;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

// This class is to perform the save data function of in the gui.
public class SaveListener implements ActionListener {
    public static final String JSON_STORE = "./data/dayreservation.json";
    private DayReservation dayReservation;
    private JsonWriter jsonWriter;
    private JButton button;

    //EFFECTS: instantiate a save listener to perform the action of save dayreservation to jason file
    public SaveListener(DayReservation reservation, JButton button) {
        this.button = button;
        this.dayReservation = reservation;
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    @Override
    //EFFECTS: perform the action of the button save
    public void actionPerformed(ActionEvent e) {
        saveDayReservation();
    }

    // EFFECTS: saves the workroom to file
    private void saveDayReservation() {
        try {
            jsonWriter.open();
            jsonWriter.write(dayReservation);
            jsonWriter.close();

            JOptionPane.showMessageDialog(null,
                    "Saved reservations on" + dayReservation.getdate() + " to " + JSON_STORE,
                    "Saved", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Saved reservations on" + dayReservation.getdate() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);

            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
