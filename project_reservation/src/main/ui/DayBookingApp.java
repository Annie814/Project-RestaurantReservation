package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import exception.CustomerNotFoundException;
import model.Customer;
import model.DayReservation;
import persistence.JsonReader;
import persistence.JsonWriter;

// note: the design of ui structure is referred to TellerApp.
// DayBooking(Reservation) teller application
public class DayBookingApp {
    private static final String JSON_STORE = "./data/dayreservation.json";
    private LocalDate date;
    private DayReservation dayReservation;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the app
    public DayBookingApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes dayreservation
    private void init() {
        date = LocalDate.of(2020, 10, 22);
        dayReservation = new DayReservation(date);
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nToday is " + dayReservation.getdate()
                + ". How can I assist you for today's reservation?");
        System.out.println("\tadd -> add a reservation");
        System.out.println("\tview -> view the reservations");
        System.out.println("\tverify -> verify if a customer has a reservation");
        System.out.println("\tchange -> change the time of a customer's reservation");
        System.out.println("\ts -> save reservations of the day to file");
        System.out.println("\tl -> load reservations of the day from file");
        System.out.println("\tq -> quit the system");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            doAddBooking();
        } else if (command.equals("view")) {
            doViewBooking();
        } else if (command.equals("verify")) {
            doVerifyBooking();
        } else if (command.equals("change")) {
            doChangeBookTime();
        } else if (command.equals("s")) {
            saveDayReservation();
        } else if (command.equals("l")) {
            loadDayReservation();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts adding a booking to today's reservation
    // note: customer duplication will be dealt later.
    private void doAddBooking() {
        Scanner inputname = new Scanner(System.in);
        System.out.print("Please enter customer's first name (for example: Elisa)");
        String enteredName = inputname.nextLine().toLowerCase(); // read the entered name

        Scanner inputnumber = new Scanner(System.in);
        System.out.print("\nPlease enter customer's phone number (for example: 5875998765)");
        String enteredNumber = inputnumber.nextLine().toLowerCase(); // read the entered number

        Scanner inputtime = new Scanner(System.in);
        System.out.print("Please enter the booking time (for example: 17.00)");
        System.out.print("\nNote： Customer booking time at the hour or half past, is preferred.");
        double enteredtime = inputtime.nextDouble(); // read the entered time
        double validTime = checkEnteredTime(enteredtime);

        Scanner inputparty = new Scanner(System.in);
        System.out.print("\nPlease enter the party size (number of people coming), (for example: 3)");
        int enteredparty = inputparty.nextInt(); // read the entered party

        Customer customer = new Customer(enteredName, enteredNumber, validTime, enteredparty);
        dayReservation.addCustomertoDay(customer);

        System.out.print("Reservation is recorded!");
    }


    // EFFECTS: conducts viewing a booking of the list of today's reservations.
    private void doViewBooking() {
        System.out.print("--------Reservation List------- ");
        printDayBooking();
    }

    // EFFECTS: conducts verifying a booking of a customer.
    private void doVerifyBooking() {
        Scanner inputname = new Scanner(System.in);
        System.out.print("Enter Customer's first name (for example: Elisa)");
        String enteredName = inputname.nextLine().toLowerCase(); // read the entered name

        Scanner inputnumber = new Scanner(System.in);
        System.out.print("Enter Customer's phone number (for example: 5875998765)");
        String enteredNumber = inputnumber.nextLine().toLowerCase(); // read the entered number

        try {
            dayReservation.verifyBooking(enteredName, enteredNumber);
            System.out.println("Customer, " + enteredName + " has a reservation at "
                    + dayReservation.getVerifyTime(enteredName, enteredNumber) + "0.");
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a change of the booking time
    private void doChangeBookTime() {
        Scanner inputname = new Scanner(System.in);
        System.out.print("Enter Customer's first name (for example: Elisa)");
        String enteredName = inputname.nextLine().toLowerCase(); // read the entered name
        Scanner inputnumber = new Scanner(System.in);
        System.out.print("Enter Customer's phone number (for example: 5875998765)");
        String enteredNumber = inputnumber.nextLine().toLowerCase(); // read the entered number


        try {
            Customer theCustomer = dayReservation.getVerifyCustomer(enteredName, enteredNumber);
            Scanner inputtime = new Scanner(System.in);
            System.out.print("Please enter the new booking time (for example: 17.00)");
            System.out.print("\nNote： Customer booking time at the hour or half past, is preferred.");
            double enteredtime = inputtime.nextDouble(); // read the entered time
            double validTime = checkEnteredTime(enteredtime);
            double oldtime = theCustomer.getBookingTime();

            dayReservation.changeBookTime(theCustomer, validTime);

            System.out.println("We are changing the Customer, " + theCustomer.getCustomername()
                    + "'s boGoking time from " + oldtime + " to " + validTime);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }
    }


    // EFFECTS: print out all the customers booked on the day, if there is no customers, print none.
    public void printDayBooking() {

        if (dayReservation.numberBooking() > 0) {
            System.out.print("\n\nHere are the customer info On" + " " + date + ":");
            for (Customer next : dayReservation.convertArray()) {
                printCustomerinfo(next);
            }
        } else {
            System.out.print("On " + date + " there is ");
            System.out.println(" No reservation recorded.");
        }
    }

    // EFFECTS: prints out the name, phone number and party size of this customer on the console
    // note: can be refactored in customer class.
    public void printCustomerinfo(Customer c) {
        System.out.println("\nTime: " + c.getBookingTime() + ", " + " Name: " + c.customername + ","
                + " Phone#: " + c.getPhonenum() + ","
                + " Party size: " + c.getPartysize());
    }

    // EFFECTS: check if the reservation time is valid.
    private double checkEnteredTime(double time) {
        while (8 > time || time > 23) {
            System.out.print("\nThis booking time is not valid.");
            System.out.print("\nReservation failed.");
            System.out.print("\nEnter the booking time again: \n");

            Scanner inputtime = new Scanner(System.in);
            time = inputtime.nextDouble();
        }
        return time;

    }

    // EFFECTS: saves the workroom to file
    private void saveDayReservation() {
        try {
            jsonWriter.open();
            jsonWriter.write(dayReservation);
            jsonWriter.close();
            System.out.println("Saved reservations on" + dayReservation.getdate() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadDayReservation() {
        try {
            dayReservation = jsonReader.read();
            System.out.println("Loaded reservations on" + dayReservation.getdate() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



