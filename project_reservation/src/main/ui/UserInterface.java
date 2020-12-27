package ui;

import model.DayReservation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


// the implementation for split panel is referred to
// https://stackoverflow.com/questions/15694107/how-to-layout-multiple-panels-on-a-jframe-java

public class UserInterface extends JFrame {

    private JSplitPane splitPane;  // split the window in top and bottom
    private UITopPane topPanel;       // container panel for the top
    private UIBottomPane bottomPanel;    // container panel for the bottom
    private LocalDate date;
    public DayReservation dayReservation;
    private LoadListener loadListener;

    // EFFECTS: instantiates the gui of this program
    public UserInterface() {

        init();

        splitPane = new JSplitPane();
        topPanel = new UITopPane(dayReservation);
        bottomPanel = new UIBottomPane(dayReservation);


        setPreferredSize(new Dimension(600, 600));

        Image img2 = Toolkit.getDefaultToolkit().getImage("./src/main/ui/bkg.jpg");


        getContentPane().setLayout(new GridLayout());



        // configure splitPane:
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window verticaly
        splitPane.setDividerLocation(300);
        // the initial position of the divider is 200 (our window is 400 pixels high)

        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(bottomPanel);            // and at the bottom we want our "bottomPanel"
        getContentPane().add(splitPane);


        pack();
    }

    // EFFECTS: initializes dayreservation
    private void init() {
        date = LocalDate.of(2021, 1, 1);
        dayReservation = new DayReservation(date);
        loadListener = new LoadListener(dayReservation);
        this.dayReservation = loadListener.getLoadedRes();

    }
}