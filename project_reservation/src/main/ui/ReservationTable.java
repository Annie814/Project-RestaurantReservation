//package ui;
//
//import model.Customer;
//import model.DayReservation;
//
//import javax.swing.*;
//import javax.swing.event.EventListenerList;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;
//
//public class ReservationTable extends AbstractTableModel {
//    private boolean DEBUG = false;
//    private DayReservation dayReservation;
//
//
//    private String[] columnNames = {"Customer Name",
//            "Phone#",
//            "Time",
//            "PartySize"};
//
//    private Object[][] data = {
//            {"example", "57463746273", "9.00", "5"},
//    };
//    protected EventListenerList;
//
//
//    public ReservationTable(DayReservation dayReservation) {
//        super();
//        this.dayReservation = dayReservation;
//    }
//
//
//    // the code above is refered to Java Table Demo:
////    private String[] columnNames = {"First Name",
////            "Last Name",
////            "Sport",
////            "# of Years",
////            "Vegetarian"};
////    private Object[][] data = {
////            {"Kathy", "Smith",
////                    "Snowboarding", new Integer(5), new Boolean(false)},
////            {"John", "Doe",
////                    "Rowing", new Integer(3), new Boolean(true)},
////            {"Sue", "Black",
////                    "Knitting", new Integer(2), new Boolean(false)},
////            {"Jane", "White",
////                    "Speed reading", new Integer(20), new Boolean(true)},
////            {"Joe", "Brown",
////                    "Pool", new Integer(10), new Boolean(false)}
////    };
//
//
//    public void updateTable() {
//        // for testing
////        JOptionPane.showMessageDialog(null,
////                " No reservation recorded.", "NOTE", JOptionPane.INFORMATION_MESSAGE);
//
//        if (dayReservation.numberBooking() > 0) {
////            int row = 0;
//            for (Customer next : dayReservation.convertArray()) {
//                printCustomerinfo(next);
////                row++;
//
//            }
//        } else {
//            JOptionPane.showMessageDialog(null,
//                    " No reservation recorded.", "NOTE", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//
//    // EFFECTS: prints out the name, phone number and party size of this customer on the console
//    // note: can be refactored in customer class.
//    public void printCustomerinfo(Customer c) {
//        Object[][] thisdata = {
//                {c.getCustomername(), c.getPhonenum(), c.getBookingTime(), c.getPartysize()}
//        };
//
//        add(data);
//        fireTableRowsInserted(list.size() - 1, list.size() - 1);
//
////        Object[][] setdata = {
////                {"", "", "", ""}
////        };
////        addRow(); //!!! dont know why its not showing.
////        fireTableRowsUpdated(0,getRowCount());
////        setValueAt(c.getCustomername(), row,0);
////        setValueAt(c.getPhonenum(), row,1);
////        setValueAt(c.getBookingTime(), row,2);
////        setValueAt(c.getPartysize(), row,3);
//
//    }
//
//
//    public int getColumnCount() {
//        return columnNames.length;
//    }
//
//    public int getRowCount() {
//        return data.length;
//    }
//
//    public String getColumnName(int col) {
//        return columnNames[col];
//    }
//
//    public Object getValueAt(int row, int col) {
//        return data[row][col];
//    }
//
//
//    /*
//     * JTable uses this method to determine the default renderer/
//     * editor for each cell.  If we didn't implement this method,
//     * then the last column would contain text ("true"/"false"),
//     * rather than a check box.
//     */
//    public Class getColumnClass(int c) {
//        return getValueAt(0, c).getClass();
//    }
//
//    /*
//     * Don't need to implement this method unless your table's
//     * editable.
//     */
//    public boolean isCellEditable(int row, int col) {
//        //Note that the data/cell address is constant,
//        //no matter where the cell appears onscreen.
//        if (col < 2) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    /*
//     * Don't need to implement this method unless your table's
//     * data can change.
//     */
//    public void setValueAt(Object value, int row, int col) {
//        if (DEBUG) {
//            System.out.println("Setting value at " + row + "," + col
//                    + " to " + value
//                    + " (an instance of "
//                    + value.getClass() + ")");
//        }
//
//        data[row][col] = value;
//        fireTableCellUpdated(row, col);
//
//        if (DEBUG) {
//            System.out.println("New value of data:");
//            printDebugData();
//        }
//    }
//
//    private void printDebugData() {
//        int numRows = getRowCount();
//        int numCols = getColumnCount();
//
//        for (int i = 0; i < numRows; i++) {
//            System.out.print("    row " + i + ":");
//            for (int j = 0; j < numCols; j++) {
//                System.out.print("  " + data[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");
//    }
//}
