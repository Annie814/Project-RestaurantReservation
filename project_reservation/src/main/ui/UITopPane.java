package ui;

import model.DayReservation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UITopPane extends JPanel {

    protected JPanel topPanel;       // container panel for the top
    private JLabel labelDate;
    private JPanel inputPanel1;      // under the text a container for all the input elements
    private JPanel inputPanel2;      // under the text a container for all the input elements
    private JPanel inputPanel3;      // under the text a container for all the input elements
    private JPanel inputPanel4;      // under the text a container for all the input elements
    private JPanel buttonPanel;
    protected JTextField nameInput;   // a textField for the text the user inputs
    protected JTextField phoneInput;
    protected JTextField timeInput;
    protected JTextField partysizeInput;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton bottonAdd;
    private JButton bottonVerify;
    private JButton bottonChange;
    protected DayReservation dayReservation;

    // EFFECTS: instantiates the Top Panel of the SplitPanel
    public UITopPane(DayReservation reservation) {
        super();
        // instantiate top panel components:  1 label field, 4 input panels, horizontal arranged 3 bottons.
        topPanel = new JPanel();
        topPanel.setVisible(true);
        topPanel.setPreferredSize(new Dimension(600, 300));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(227, 227, 101));
        ShowImage image = new ShowImage("./src/main/ui/bkg.jpg");
        topPanel.add(image);

        this.dayReservation = reservation;
        greetingPanel();
        inputInfoPanels();
        bottonPanel();
        //Lay out from top to down.
        setTopPanelLayout();
        //setup action listener for bottons
        setupBottonListener();

        add(topPanel, CENTER_ALIGNMENT);

    }


    private void setTopPanelLayout() {
        topPanel.add(labelDate);
        topPanel.add(inputPanel1);
        topPanel.add(inputPanel2);
        topPanel.add(inputPanel3);
        topPanel.add(inputPanel4);
        topPanel.add(buttonPanel);
    }

    //EFFECTS: Set up action and command for all the buttons in top panel
    private void setupBottonListener() {
        bottonAdd.setActionCommand("add");
        bottonAdd.addActionListener(new AddListener(this, bottonAdd));
        bottonVerify.setActionCommand("change");
        bottonVerify.addActionListener(new VerifyListener(this, bottonVerify));
        bottonChange.setActionCommand("change");
        bottonChange.addActionListener(new ChangeListener(this, bottonChange));
    }

    //EFFECTS: instantiates the greeting panel as a label on the top.
    private void greetingPanel() {
        labelDate = new JLabel("\nToday is " + dayReservation.getdate()
                + " How can I assist you for today's reservation");

    }

    //EFFECTS: instantiates the panel for collecting all the inputs from users.
    private void inputInfoPanels() {
        inputPanel1 = new JPanel();
        inputPanel1.setBackground(new Color(227, 227, 101));
        inputPanel2 = new JPanel();
        inputPanel2.setBackground(new Color(227, 227, 101));
        inputPanel3 = new JPanel();
        inputPanel3.setBackground(new Color(227, 227, 101));
        inputPanel4 = new JPanel();
        inputPanel4.setBackground(new Color(227, 227, 101));

        label1 = new JLabel("Customer Name");
        label2 = new JLabel("Phone #");
        label3 = new JLabel("Booking Time");
        label4 = new JLabel("Party Size");
        nameInput = new JTextField(150);    // first the input field where the user can type his text
        phoneInput = new JTextField(150);
        timeInput = new JTextField(150);
        partysizeInput = new JTextField(150);

        inputPanel1();
        inputPanel2();
        inputPanel3();
        inputPanel4();
    }


    //EFFECTS: instantiates all the buttons in top panel.
    private void bottonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(227, 227, 101));

        bottonAdd = new JButton("Add");
        bottonVerify = new JButton("Verify");
        bottonChange = new JButton("Change");

        //Lay out the buttons from left to right.
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(bottonAdd);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(bottonVerify);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(bottonChange);
    }


    //EFFECTS: instantiates input panel for party size
    private void inputPanel4() {
        inputPanel4.setMaximumSize(new Dimension(300, 20));
        inputPanel4.setLayout(new BoxLayout(inputPanel4, BoxLayout.X_AXIS));
        inputPanel4.add(label4);            // left will be label
        inputPanel4.add(partysizeInput);        // right will be the textField
    }

    //EFFECTS: instantiates input panel for booking time
    private void inputPanel3() {
        inputPanel3.setMaximumSize(new Dimension(300, 20));
        inputPanel3.setLayout(new BoxLayout(inputPanel3, BoxLayout.X_AXIS));
        inputPanel3.add(label3);            // left will be label
        inputPanel3.add(timeInput);        // right will be the textField
    }

    //EFFECTS: instantiates input panel for phone num
    private void inputPanel2() {
        inputPanel2.setMaximumSize(new Dimension(300, 20));
        inputPanel2.setLayout(new BoxLayout(inputPanel2, BoxLayout.X_AXIS)); // arrange the content horizontally
        inputPanel2.add(label2);            // left will be label
        inputPanel2.add(phoneInput);        // right will be the textField
    }

    //EFFECTS: instantiates input panel for customer name
    private void inputPanel1() {
        inputPanel1.setMaximumSize(new Dimension(300, 20));
        inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
        inputPanel1.add(label1);            // left will be label
        inputPanel1.add(nameInput);        // right will be the textField
    }

    //EFFECTS: show the image
    public class ShowImage extends Panel {
        private static final long serialVersionUID = 1L;
        private BufferedImage image;

        public ShowImage(String filename) {
            try {
                image = ImageIO.read(new File(filename));
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }


        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
    }
}

