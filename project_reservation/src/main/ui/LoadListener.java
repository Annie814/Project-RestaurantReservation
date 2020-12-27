package ui;

import model.DayReservation;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// This class is to perform the load data function of in the gui.
public class LoadListener implements ActionListener {
    private static final String JSON_STORE = "./project_reservation/data/dayreservation.json";
    public DayReservation dayReservation;
    private JsonReader jsonReader;

    //EFFECTS: instantiate a load listener to perform the action of load dayreservation to jason file
    public LoadListener(DayReservation reservation) {
        int result = JOptionPane.showConfirmDialog(null,
                "Would you like to load the data from" + JSON_STORE + " ?"
                        +
                        "\n\nNote for all staffs: "
                        + "\nIt is important to load the files, "
                        +
                        "so that you can access all the customers info on this date",
                "Load data", JOptionPane.YES_NO_OPTION);
        this.dayReservation = reservation;
        if (result == JOptionPane.YES_OPTION) {
            jsonReader = new JsonReader(JSON_STORE);
            loadDayReservation();
        }
//        } else {
//            String inputDate = JOptionPane.showInputDialog("Enter the the new date:"); //popup enternewtime.
//            this.dayReservation = Double.parseDouble(inputTime);// read the entered time
//        }
    }

    @Override
    //EFFECTS: perform the action of load the data from the file
    public void actionPerformed(ActionEvent e) {
        loadDayReservation();
    }

    //EFFECTS: return the dayreservation in the data file
    public DayReservation getLoadedRes() {
        return dayReservation;
    }

    // MODIFIES: this
    // EFFECTS: loads dayreservation from file
    private void loadDayReservation() {
        try {
            dayReservation = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded reservations on" + dayReservation.getdate() + " from " + JSON_STORE,
                    "Loaded", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Loaded reservations on " + dayReservation.getdate() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);

            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
