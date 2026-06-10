package schoolsystem3.StudentInsideData.Attendance;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import schoolsystem3.Homepage;
import schoolsystem3.StudentsData.Student1;

public class Student1Attendance extends JFrame implements ActionListener {

    private JTextField txtSearch;
    private JButton btnSearch, btnBack;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JButton btnOOP, btnInteg, btnCP, btnNet, btnOS;

    // OOP 
    private JTextField[] oopID = new JTextField[10];
    private JTextField[] oopName = new JTextField[10];
    private JTextField[][] oopWeek = new JTextField[10][5];

    //INTEG 
    private JTextField[] integID = new JTextField[10];
    private JTextField[] integName = new JTextField[10];
    private JTextField[][] integWeek = new JTextField[10][5];

    //  CP 
    private JTextField[] cpID = new JTextField[10];
    private JTextField[] cpName = new JTextField[10];
    private JTextField[][] cpWeek = new JTextField[10][5];

    //  NET 
    private JTextField[] netID = new JTextField[10];
    private JTextField[] netName = new JTextField[10];
    private JTextField[][] netWeek = new JTextField[10][5];

    //  OS 
    private JTextField[] osID = new JTextField[10];
    private JTextField[] osName = new JTextField[10];
    private JTextField[][] osWeek = new JTextField[10][5];

    public Student1Attendance() {

        setTitle("Student Attendance Viewer");
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        //  SEARCH BAR 
        JLabel lbl = new JLabel("Enter Student ID or Name:");
        lbl.setBounds(200, 20, 200, 30);
        add(lbl);

        txtSearch = new JTextField();
        txtSearch.setBounds(380, 20, 200, 30);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(600, 20, 100, 30);
        btnSearch.addActionListener(this);
        add(btnSearch);

        //  SIDE MENU 
        JPanel side = new JPanel();
        side.setLayout(null);
        side.setBounds(0, 0, 180, 700);
        side.setBackground(Color.LIGHT_GRAY);
        add(side);

        btnOOP = createButton("OOP", 120);
        btnInteg = createButton("Integrative", 170);
        btnCP = createButton("Programming", 220);
        btnNet = createButton("Network", 270);
        btnOS = createButton("OS", 320);

        side.add(btnOOP);
        side.add(btnInteg);
        side.add(btnCP);
        side.add(btnNet);
        side.add(btnOS);

        btnOOP.addActionListener(this);
        btnInteg.addActionListener(this);
        btnCP.addActionListener(this);
        btnNet.addActionListener(this);
        btnOS.addActionListener(this);

        //  MAIN PANEL 
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBounds(200, 80, 760, 550);
        add(mainPanel);

        mainPanel.add(createPanel("OOP", oopID, oopName, oopWeek), "OOP");
        mainPanel.add(createPanel("INTEG", integID, integName, integWeek), "INTEG");
        mainPanel.add(createPanel("CP", cpID, cpName, cpWeek), "CP");
        mainPanel.add(createPanel("NET", netID, netName, netWeek), "NET");
        mainPanel.add(createPanel("OS", osID, osName, osWeek), "OS");

        // BACK BUTTON
        btnBack = new JButton("Back");
        btnBack.setBounds(820, 610, 150, 40);
        btnBack.addActionListener(this);
        add(btnBack);

        setVisible(true);
    }

    //  PANEL CREATOR 
    private JPanel createPanel(String titleText,
                               JTextField[] id,
                               JTextField[] name,
                               JTextField[][] week) {

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel(titleText + " Attendance", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);

        JLabel[] headers = {
            new JLabel("ID"),
            new JLabel("Name"),
            new JLabel("W1"),
            new JLabel("W2"),
            new JLabel("W3"),
            new JLabel("W4"),
            new JLabel("W5")
        };

        int[] x = {50, 150, 250, 320, 390, 460, 530};

        for (int i = 0; i < headers.length; i++) {
            headers[i].setBounds(x[i], 60, 70, 25);
            panel.add(headers[i]);
        }

        int yStart = 100;

        for (int i = 0; i < 10; i++) {

            int y = yStart + (i * 35);

            id[i] = createField(panel, 50, y);
            name[i] = createField(panel, 150, y);

            for (int j = 0; j < 5; j++) {
                week[i][j] = createField(panel, 250 + (j * 70), y);
            }
        }

        return panel;
    }

    private JTextField createField(JPanel panel, int x, int y) {
        JTextField t = new JTextField();
        t.setBounds(x, y, 60, 25);
        t.setEditable(false);
        panel.add(t);
        return t;
    }

    private JButton createButton(String text, int y) {
        JButton b = new JButton(text);
        b.setBounds(10, y, 160, 35);
        return b;
    }

    //  SEARCH ALL TABLES 
    private void searchAll() {

        loadTable("oopattendance", oopID, oopName, oopWeek);
        loadTable("integattendance", integID, integName, integWeek);
        loadTable("cpattendance", cpID, cpName, cpWeek);
        loadTable("netattendance", netID, netName, netWeek);
        loadTable("osattendance", osID, osName, osWeek);
    }

    //  REUSABLE LOADER 
    private void loadTable(String table,
                           JTextField[] id,
                           JTextField[] name,
                           JTextField[][] week) {

        String key = txtSearch.getText().trim();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            PreparedStatement pst;

            if (key.isEmpty()) {
                pst = conn.prepareStatement("SELECT * FROM " + table);
            } else {
                pst = conn.prepareStatement(
                    "SELECT * FROM " + table + " WHERE ID=? OR Name=?");
                pst.setString(1, key);
                pst.setString(2, key);
            }

            ResultSet rs = pst.executeQuery();

            // clear
            for (int i = 0; i < 10; i++) {
                id[i].setText("");
                name[i].setText("");
                for (int j = 0; j < 5; j++) {
                    week[i][j].setText("");
                }
            }

            int i = 0;

            while (rs.next() && i < 10) {

                id[i].setText(rs.getString("ID"));
                name[i].setText(rs.getString("Name"));

                week[i][0].setText(rs.getString("Week1"));
                week[i][1].setText(rs.getString("Week2"));
                week[i][2].setText(rs.getString("Week3"));
                week[i][3].setText(rs.getString("Week4"));
                week[i][4].setText(rs.getString("Week5"));

                i++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //  ACTIONS 
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSearch) {
            searchAll();
        }

        else if (e.getSource() == btnOOP) cardLayout.show(mainPanel, "OOP");
        else if (e.getSource() == btnInteg) cardLayout.show(mainPanel, "INTEG");
        else if (e.getSource() == btnCP) cardLayout.show(mainPanel, "CP");
        else if (e.getSource() == btnNet) cardLayout.show(mainPanel, "NET");
        else if (e.getSource() == btnOS) cardLayout.show(mainPanel, "OS");

        else if (e.getSource() == btnBack) {
            Student1 st = new Student1();
            st.setVisible(true);
            dispose();
        }
    }
}

