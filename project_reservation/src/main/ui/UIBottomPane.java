package ui;

import model.DayReservation;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UIBottomPane extends JPanel implements ListSelectionListener {

    private JPanel bottomPanel;    // container panel for the bottom
    protected JScrollPane scrollPane; // makes the text scrollable
    private JButton bottonView;
    private JButton bottonSave;
    //    private JButton bottonLoad;
    public DayReservation dayReservation;


    //    public ReservationTable reservationTable;
    public BookingTable bookingTable;
    public JTable jtable;

    // EFFECTS: instantiates the Bottom Panel of the SplitPanel
    public UIBottomPane(DayReservation dayReservation) {
        super();
//        testlist = new DefaultListModel();
//        testlist.addElement("Jane Doe");
//        testlist.addElement("John Smith");
//        testlist.addElement("Kathy Green");

        //bottomPanel

        bottomPanel = new JPanel();
        bottomPanel.setSize(new Dimension(300, 300));
        bottomPanel.setBackground(new Color(227, 227, 101));
        bottomPanel.setVisible(true);
        // in our bottom panel we want the label area and the input components
        bottonView = new JButton("update list");
        bottonSave = new JButton("Save");
        //set layout and put components in the panel.
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

//        reservationlist = new JList(dayReservation); //data has type Object[]
//        reservationlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        reservationlist.setSelectedIndex(0);
//        reservationlist.addListSelectionListener(this);
//        reservationlist.setVisibleRowCount(5);
//        scrollPane = new JScrollPane(reservationlist);

//        reservationTable = new ReservationTable(dayReservation);
//        jtable = new JTable(reservationTable);
//        jtable.setRowHeight(10);
//        scrollPane = new JScrollPane(jtable);
//        scrollPane.setPreferredSize(new Dimension(550, 200));

        bookingTable = new BookingTable(dayReservation);

        jtable = new JTable(bookingTable);
        scrollPane = new JScrollPane(jtable);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        add(bottomPanel, CENTER_ALIGNMENT);
        bottomPanel.add(bottonView);
        bottomPanel.add(bottonSave);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        //set up
        this.dayReservation = dayReservation;
        buttonSetUp(dayReservation);

    }

    //EFFECTS: Set up action and command for all the buttons in bottom panel
    private void buttonSetUp(DayReservation dayReservation) {
        bottonView.setActionCommand("view");
        bottonSave.setActionCommand("save");
        bottonView.addActionListener(new ViewListener(this, bookingTable, bottonView));
        bottonSave.setActionCommand("save");
        bottonSave.addActionListener(new SaveListener(dayReservation, bottonView));

    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}


