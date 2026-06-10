package schoolsystem3.TeachersFunctions;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TeacherGrades extends JFrame implements ActionListener {

    private JButton btnback, btnOOP, btnInteg, btnCP, btnNetAd, btnOS;
    private JButton btnSubmit, btnSubmit2, btnSubmit3, btnSubmit4, btnSubmit5;
    private JButton btnUpdate, btnUpdate2, btnUpdate3, btnUpdate4, btnUpdate5;
    private JButton btnDelete, btnDelete2, btnDelete3, btnDelete4, btnDelete5;
    private JButton btnSearch, btnSearch2, btnSearch3, btnSearch4, btnSearch5; 
    private JButton btncomp, btncomp2, btncomp3, btncomp4, btncomp5;
    private JPanel pnlSideBar, pnlMain;
    private CardLayout cardLayout;
    
    // Student ID Arrays
    private JTextField[] oopID = new JTextField[10];
    private JTextField[] integID = new JTextField[10];
    private JTextField[] cpID = new JTextField[10];
    private JTextField[] netID = new JTextField[10];
    private JTextField[] osID = new JTextField[10];

    // OOP
    private JTextField[] oopName = new JTextField[10];
    private JTextField[][] oopWeek = new JTextField[10][5];
    
    // INTEG
    private JTextField[] integName = new JTextField[10];
    private JTextField[][] integWeek = new JTextField[10][5];

    // CP
    private JTextField[] cpName = new JTextField[10];
    private JTextField[][] cpWeek = new JTextField[10][5];
    
    // NET
    private JTextField[] netName = new JTextField[10];
    private JTextField[][] netWeek = new JTextField[10][5];

    // OS
    private JTextField[] osName = new JTextField[10];
    private JTextField[][] osWeek = new JTextField[10][5];

    private String currentTab = "OOP"; 

    public TeacherGrades() {

        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // ================= SIDEBAR =================
        pnlSideBar = new JPanel();
        pnlSideBar.setLayout(null);
        pnlSideBar.setBounds(0, 0, 180, 750);
        pnlSideBar.setBackground(Color.decode("#c0c0c0"));
        add(pnlSideBar);

        JLabel lblMenu = new JLabel("Subject");
        lblMenu.setBounds(20, 40, 150, 30);
        pnlSideBar.add(lblMenu);

        btnOOP = createSideButton("OOP", 120);
        btnInteg = createSideButton("Integrative", 170);
        btnCP = createSideButton("Programming", 220);
        btnNetAd = createSideButton("Network", 270);
        btnOS = createSideButton("Operating Sys", 320);

        pnlSideBar.add(btnOOP);
        pnlSideBar.add(btnInteg);
        pnlSideBar.add(btnCP);
        pnlSideBar.add(btnNetAd);
        pnlSideBar.add(btnOS);

        // ================= MAIN PANEL =================
        cardLayout = new CardLayout();
        pnlMain = new JPanel(cardLayout);
        pnlMain.setBounds(200, 0, 800, 550);

        pnlMain.add(createOOPPanel(), "OOP");
        pnlMain.add(createIntegPanel(), "INTEG");
        pnlMain.add(createCompPanel(), "CP");
        pnlMain.add(createNetAdPanel(), "NET");
        pnlMain.add(createOSPanel(), "OS");

        add(pnlMain);

        // ================= BUTTONS =================
        btnback = new JButton("Back");
        btnback.setBounds(650, 620, 100, 30);
        add(btnback);

        // ================= ACTIONS =================
        btnOOP.addActionListener(this);
        btnInteg.addActionListener(this);
        btnCP.addActionListener(this);
        btnNetAd.addActionListener(this);
        btnOS.addActionListener(this);
        btnback.addActionListener(this);

        cardLayout.show(pnlMain, "OOP");      
    }

    // ================= OOP PANEL =================
    private JPanel createOOPPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("OOP Grades", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title);

        panel.add(createLabel("Student ID", 19, 100));
        panel.add(createLabel("Name", 112, 100));
        panel.add(createLabel("Quiz", 203, 100));
        panel.add(createLabel("Exam", 281, 100));
        panel.add(createLabel("Project", 361, 100));
        panel.add(createLabel("Final Grade", 445, 100));   
        int yStart = 120;

        for (int i = 0; i < 10; i++) {
            int y = yStart + (i * 35);
            oopID[i] = addField(panel, "", 20, y, 90, 30);
            oopName[i] = addField(panel, "", 120, y, 90, 30);
            oopWeek[i][0] = addField(panel, "", 220, y, 70, 30);
            oopWeek[i][1] = addField(panel, "", 300, y, 70, 30);
            oopWeek[i][2] = addField(panel, "", 380, y, 70, 30);
            oopWeek[i][3] = addField(panel, "", 460, y, 70, 30);
            oopWeek[i][3].setEditable(false);
        }

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(75, 500, 120, 30);
        panel.add(btnSubmit);
        btnSubmit.addActionListener(this);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(205, 500, 120, 30);
        panel.add(btnUpdate);
        btnUpdate.addActionListener(this);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(335, 500, 120, 30);
        panel.add(btnDelete);
        btnDelete.addActionListener(this);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(465, 500, 120, 30);
        panel.add(btnSearch);
        btnSearch.addActionListener(this);
        
        btncomp = new JButton("compute");
        btncomp.setBounds(595, 500, 100, 30);
        panel.add(btncomp);
        btncomp.addActionListener(this);

        return panel;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setBounds(x, y, 100, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        return lbl;
    }

    // ================= INTEG PANEL =================
    private JPanel createIntegPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Integrative Grades", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title);

        panel.add(createLabel("Student ID", 19, 100));
        panel.add(createLabel("Name", 112, 100));
        panel.add(createLabel("Quiz", 203, 100));
        panel.add(createLabel("Exam", 281, 100));
        panel.add(createLabel("Project", 361, 100));
        panel.add(createLabel("Final Grade", 445, 100));
        int yStart = 120;

        for (int i = 0; i < 10; i++) {
            int y = yStart + (i * 35);
            integID[i] = addField(panel, "", 20, y, 90, 30);
            integName[i] = addField(panel, "", 120, y, 90, 30);
            integWeek[i][0] = addField(panel, "", 220, y, 70, 30);
            integWeek[i][1] = addField(panel, "", 300, y, 70, 30);
            integWeek[i][2] = addField(panel, "", 380, y, 70, 30);
            integWeek[i][3] = addField(panel, "", 460, y, 70, 30);
            integWeek[i][3].setEditable(false);
        }

        btnSubmit2 = new JButton("Submit");
        btnSubmit2.setBounds(75, 500, 120, 30);
        panel.add(btnSubmit2);
        btnSubmit2.addActionListener(this);
        
        btnUpdate2 = new JButton("Update");
        btnUpdate2.setBounds(205, 500, 120, 30);
        panel.add(btnUpdate2);
        btnUpdate2.addActionListener(this);

        btnDelete2 = new JButton("Delete");
        btnDelete2.setBounds(335, 500, 120, 30);
        panel.add(btnDelete2);
        btnDelete2.addActionListener(this);

        btnSearch2 = new JButton("Search");
        btnSearch2.setBounds(465, 500, 120, 30);
        panel.add(btnSearch2);
        btnSearch2.addActionListener(this);
        
        btncomp2 = new JButton("compute");
        btncomp2.setBounds(595, 500, 100, 30);
        panel.add(btncomp2);
        btncomp2.addActionListener(this);

        return panel;
    }

    // ================= CP PANEL =================
    private JPanel createCompPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Computer Programming Grades", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title);

        panel.add(createLabel("Student ID", 19, 100));
        panel.add(createLabel("Name", 112, 100));
        panel.add(createLabel("Quiz", 203, 100));
        panel.add(createLabel("Exam", 281, 100));
        panel.add(createLabel("Project", 361, 100));
        panel.add(createLabel("Final Grade", 445, 100));
        int yStart = 120;

        for (int i = 0; i < 10; i++) {
            int y = yStart + (i * 35);
            cpID[i] = addField(panel, "", 20, y, 90, 30);
            cpName[i] = addField(panel, "", 120, y, 90, 30);
            cpWeek[i][0] = addField(panel, "", 220, y, 70, 30);
            cpWeek[i][1] = addField(panel, "", 300, y, 70, 30);
            cpWeek[i][2] = addField(panel, "", 380, y, 70, 30);
            cpWeek[i][3] = addField(panel, "", 460, y, 70, 30);
            cpWeek[i][3].setEditable(false);
        }

        btnSubmit3 = new JButton("Submit");
        btnSubmit3.setBounds(75, 500, 120, 30);
        panel.add(btnSubmit3);
        btnSubmit3.addActionListener(this);
        
        btnUpdate3 = new JButton("Update");
        btnUpdate3.setBounds(205, 500, 120, 30);
        panel.add(btnUpdate3);
        btnUpdate3.addActionListener(this);

        btnDelete3 = new JButton("Delete");
        btnDelete3.setBounds(335, 500, 120, 30);
        panel.add(btnDelete3);
        btnDelete3.addActionListener(this);

        btnSearch3 = new JButton("Search");
        btnSearch3.setBounds(465, 500, 120, 30);
        panel.add(btnSearch3);
        btnSearch3.addActionListener(this);
        
        btncomp3 = new JButton("compute");
        btncomp3.setBounds(595, 500, 100, 30);
        panel.add(btncomp3);
        btncomp3.addActionListener(this);

        return panel;
    }

    // ================= NET PANEL =================
    private JPanel createNetAdPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Network Administration Grades", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title);

        panel.add(createLabel("Student ID", 19, 100));
        panel.add(createLabel("Name", 112, 100));
        panel.add(createLabel("Quiz", 203, 100));
        panel.add(createLabel("Exam", 281, 100));
        panel.add(createLabel("Project", 361, 100));
        panel.add(createLabel("Final Grade", 445, 100));
        int yStart = 120;

        for (int i = 0; i < 10; i++) {
            int y = yStart + (i * 35);
            netID[i] = addField(panel, "", 20, y, 90, 30);
            netName[i] = addField(panel, "", 120, y, 90, 30);
            netWeek[i][0] = addField(panel, "", 220, y, 70, 30);
            netWeek[i][1] = addField(panel, "", 300, y, 70, 30);
            netWeek[i][2] = addField(panel, "", 380, y, 70, 30);
            netWeek[i][3] = addField(panel, "", 460, y, 70, 30);
            netWeek[i][3].setEditable(false);
        }

        btnSubmit4 = new JButton("Submit");
        btnSubmit4.setBounds(75, 500, 120, 30);
        panel.add(btnSubmit4);
        btnSubmit4.addActionListener(this);
        
        btnUpdate4 = new JButton("Update");
        btnUpdate4.setBounds(205, 500, 120, 30);
        panel.add(btnUpdate4);
        btnUpdate4.addActionListener(this);

        btnDelete4 = new JButton("Delete");
        btnDelete4.setBounds(335, 500, 120, 30);
        panel.add(btnDelete4);
        btnDelete4.addActionListener(this);

        btnSearch4 = new JButton("Search");
        btnSearch4.setBounds(465, 500, 120, 30);
        panel.add(btnSearch4);
        btnSearch4.addActionListener(this);
        
        btncomp4 = new JButton("compute");
        btncomp4.setBounds(595, 500, 100, 30);
        panel.add(btncomp4);
        btncomp4.addActionListener(this);

        return panel;
    }

    // ================= OS PANEL =================
    private JPanel createOSPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Operating System Grades", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title);

        panel.add(createLabel("Student ID", 19, 100));
        panel.add(createLabel("Name", 112, 100));
        panel.add(createLabel("Quiz", 203, 100));
        panel.add(createLabel("Exam", 281, 100));
        panel.add(createLabel("Project", 361, 100));
        panel.add(createLabel("Final Grade", 445, 100));
        
        int yStart = 120;

        for (int i = 0; i < 10; i++) {
            int y = yStart + (i * 35);
            osID[i] = addField(panel, "", 20, y, 90, 30);
            osName[i] = addField(panel, "", 120, y, 90, 30);
            osWeek[i][0] = addField(panel, "", 220, y, 70, 30);
            osWeek[i][1] = addField(panel, "", 300, y, 70, 30);
            osWeek[i][2] = addField(panel, "", 380, y, 70, 30);
            osWeek[i][3] = addField(panel, "", 460, y, 70, 30);
            osWeek[i][3].setEditable(false);
        }

        btnSubmit5 = new JButton("Submit");
        btnSubmit5.setBounds(75, 500, 120, 30);
        panel.add(btnSubmit5);
        btnSubmit5.addActionListener(this);
        
        btnUpdate5 = new JButton("Update");
        btnUpdate5.setBounds(205, 500, 120, 30);
        panel.add(btnUpdate5);
        btnUpdate5.addActionListener(this);

        btnDelete5 = new JButton("Delete");
        btnDelete5.setBounds(335, 500, 120, 30);
        panel.add(btnDelete5);
        btnDelete5.addActionListener(this);

        btnSearch5 = new JButton("Search");
        btnSearch5.setBounds(465, 500, 120, 30);
        panel.add(btnSearch5);
        btnSearch5.addActionListener(this);
        
        btncomp5 = new JButton("compute");
        btncomp5.setBounds(595, 500, 100, 30);
        panel.add(btncomp5);
        btncomp5.addActionListener(this);

        return panel;
    }

    // Helper to check if student ID already exists in specific table
    private boolean studentExists(Connection conn, String tableName, String id) throws SQLException {
        String checkSql = "SELECT 1 FROM " + tableName + " WHERE student_id = ?";
        try (PreparedStatement checkPst = conn.prepareStatement(checkSql)) {
            checkPst.setString(1, id);
            try (ResultSet rs = checkPst.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    private void performcomputation(JTextField[][] weekfields) {
    for (int i = 0; i < 10; i++) {
        try {
            if (weekfields[i][0].getText().trim().isEmpty()) continue;
            double q = Double.parseDouble(weekfields[i][0].getText());
            double e = Double.parseDouble(weekfields[i][1].getText());
            double p = Double.parseDouble(weekfields[i][2].getText());
            double finalgrade = (q + e + p) / 3.0;
            weekfields[i][3].setText(String.format("%.2f", finalgrade));
        } catch (Exception ex) { /* skips non-numeric input */ }
    }
}

    // ================= ACTIONS =================
    @Override
    public void actionPerformed(ActionEvent e) {
        
         if (e.getSource() == btncomp) { performcomputation(oopWeek); }
else if (e.getSource() == btncomp2) { performcomputation(integWeek); }
else if (e.getSource() == btncomp3) { performcomputation(cpWeek); }
else if (e.getSource() == btncomp4) { performcomputation(netWeek); }
else if (e.getSource() == btncomp5) { performcomputation(osWeek); } {

        if (e.getSource() == btnback) {
            setVisible(false);
            new TeacherDashB().setVisible(true);
        }
        else if (e.getSource() == btnOOP) {
            currentTab = "OOP";
            cardLayout.show(pnlMain, "OOP");
        }
        else if (e.getSource() == btnInteg) {
            currentTab = "INTEG";
            cardLayout.show(pnlMain, "INTEG");
        }
        else if (e.getSource() == btnCP) {
            currentTab = "CP";
            cardLayout.show(pnlMain, "CP");
        }
        else if (e.getSource() == btnNetAd) {
            currentTab = "NET";
            cardLayout.show(pnlMain, "NET");
        }
        else if (e.getSource() == btnOS) {
            currentTab = "OS";
            cardLayout.show(pnlMain, "OS");
        }
        
        // ================= RETRIEVE ALL DATA =================
        else if (e.getSource() == btnSearch || e.getSource() == btnSearch2 || e.getSource() == btnSearch3 || e.getSource() == btnSearch4 || e.getSource() == btnSearch5) {
            executeDatabaseSearch();
        }

        // ================= DIALOG INPUT DELETE =================
        else if (e.getSource() == btnDelete || e.getSource() == btnDelete2 || e.getSource() == btnDelete3 || e.getSource() == btnDelete4 || e.getSource() == btnDelete5) {
            String deleteID = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");
            if (deleteID != null && !deleteID.trim().isEmpty()) {
                executeDatabaseDelete(deleteID.trim());
            }
        }

        // ================= SUBMIT OOP =================
       // ================= SUBMIT OOP =================
else if (e.getSource() == btnSubmit) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
        String sql = "INSERT INTO oopgrades (student_id, name, quiz, exam, project, final_grade) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        int savedRows = 0;
        
        if (savedRows > 0) {
    JOptionPane.showMessageDialog(this, savedRows + " new OOP Grade(s) saved!");
    for (int i = 0; i < 10; i++) {
        oopID[i].setText(""); oopName[i].setText("");
        for (int j = 0; j < 4; j++) oopWeek[i][j].setText("");
    }
}
        

        for (int i = 0; i < 10; i++) {
            String id = oopID[i].getText().trim();
            String name = oopName[i].getText().trim();
            
            if (id.isEmpty() && name.isEmpty()) continue;
            if (studentExists(conn, "oopgrades", id)) continue;
            
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, oopWeek[i][0].getText());
            pst.setString(4, oopWeek[i][1].getText());
            pst.setString(5, oopWeek[i][2].getText());
            pst.setString(6, oopWeek[i][3].getText());
            pst.executeUpdate();
            savedRows++;
        }
        
        // --- CHANGE START ---
        // Only refresh the search if we actually saved something new
        if (savedRows > 0) {
            JOptionPane.showMessageDialog(this, savedRows + " new OOP Grade(s) saved safely!");
           // executeDatabaseSearch(); 
        } else {
            JOptionPane.showMessageDialog(this, "No new data to save.");
        }
        // --- CHANGE END ---

        pst.close(); conn.close();
    } catch (Exception ex) { 
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "OOP Error: " + ex.getMessage());
    }
}
        
       
      // ================= SUBMIT INTEG =================
    else if (e.getSource() == btnSubmit2) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
        String sql = "INSERT INTO integgrades (student_id, name, quiz, exam, project, final_grade) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        int savedRows = 0;

        for (int i = 0; i < 10; i++) {
            String id = integID[i].getText().trim();
            String name = integName[i].getText().trim();
            
            if (id.isEmpty() && name.isEmpty()) continue;
            if (studentExists(conn, "integgrades", id)) continue;
            
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, integWeek[i][0].getText());
            pst.setString(4, integWeek[i][1].getText());
            pst.setString(5, integWeek[i][2].getText());
            pst.setString(6, integWeek[i][3].getText());
            pst.executeUpdate();
            savedRows++;
        }
        
        // --- THIS IS THE FIX ---
        if (savedRows > 0) {
            JOptionPane.showMessageDialog(this, savedRows + " new Integrative Grade(s) saved successfully!");
           // executeDatabaseSearch(); // Only refreshes if data was actually saved
        } else {
            JOptionPane.showMessageDialog(this, "No new data to save.");
        }
        // -----------------------

        pst.close(); 
        conn.close();
    } catch (Exception ex) { 
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Integrative Error: " + ex.getMessage());
    }
}
        
        // ================= SUBMIT CP =================
else if (e.getSource() == btnSubmit3) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
        String sql = "INSERT INTO cpgrades (student_id, name, quiz, exam, project, final_grade) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        int savedRows = 0;

        for (int i = 0; i < 10; i++) {
            String id = cpID[i].getText().trim();
            String name = cpName[i].getText().trim();
            
            if (id.isEmpty() && name.isEmpty()) continue;
            if (studentExists(conn, "cpgrades", id)) continue;
            
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, cpWeek[i][0].getText());
            pst.setString(4, cpWeek[i][1].getText());
            pst.setString(5, cpWeek[i][2].getText());
            pst.setString(6, cpWeek[i][3].getText());
            pst.executeUpdate();
            savedRows++;
        }
        
        // --- THIS IS THE FIX ---
        if (savedRows > 0) {
            JOptionPane.showMessageDialog(this, savedRows + " new CP Grade(s) saved successfully!");
           // executeDatabaseSearch(); // Only refreshes if data was actually saved
        } else {
            JOptionPane.showMessageDialog(this, "No new data to save.");
        }
        // -----------------------

        pst.close(); 
        conn.close();
    } catch (Exception ex) { 
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "CP Error: " + ex.getMessage());
    }
}

        // ================= SUBMIT NET =================
    else if (e.getSource() == btnSubmit4) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
        String sql = "INSERT INTO netgrades (student_id, name, quiz, exam, project, final_grade) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        int savedRows = 0;

        for (int i = 0; i < 10; i++) {
            String id = netID[i].getText().trim();
            String name = netName[i].getText().trim();
            
            if (id.isEmpty() && name.isEmpty()) continue;
            if (studentExists(conn, "netgrades", id)) continue;
            
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, netWeek[i][0].getText());
            pst.setString(4, netWeek[i][1].getText());
            pst.setString(5, netWeek[i][2].getText());
            pst.setString(6, netWeek[i][3].getText());
            pst.executeUpdate();
            savedRows++;
        }
        
        
        
        // --- THIS IS THE FIX ---
        if (savedRows > 0) {
            JOptionPane.showMessageDialog(this, savedRows + " new Network Grade(s) saved successfully!");
           // executeDatabaseSearch(); // Only refreshes if data was actually saved
        } else {
            JOptionPane.showMessageDialog(this, "No new data to save.");
        }
        // -----------------------

        pst.close(); 
        conn.close();
    } catch (Exception ex) { 
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Network Error: " + ex.getMessage());
    }
}
    else if (e.getSource() == btnSubmit5) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
        // Changed to osgrades table
        String sql = "INSERT INTO osgrades (student_id, name, quiz, exam, project, final_grade) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        int savedRows = 0;

        for (int i = 0; i < 10; i++) {
            // Using os arrays
            String id = osID[i].getText().trim();
            String name = osName[i].getText().trim();
            
            if (id.isEmpty() && name.isEmpty()) continue;
            if (studentExists(conn, "osgrades", id)) continue;
            
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, osWeek[i][0].getText());
            pst.setString(4, osWeek[i][1].getText());
            pst.setString(5, osWeek[i][2].getText());
            pst.setString(6, osWeek[i][3].getText());
            pst.executeUpdate();
            savedRows++;
        }
        
        // --- FIX APPLIED ---
        if (savedRows > 0) {
            JOptionPane.showMessageDialog(this, savedRows + " new OS Grade(s) saved successfully!");
           // executeDatabaseSearch(); // Only refreshes if data was actually saved
        } else {
            JOptionPane.showMessageDialog(this, "No new data to save.");
        }
        // -----------------------

        pst.close(); 
        conn.close();
    } catch (Exception ex) { 
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "OS Error: " + ex.getMessage());
    }
}

        // ================= UPDATE OOP =================
        else if (e.getSource() == btnUpdate) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE oopgrades SET name=?, quiz=?, exam=?, project=?, final_grade=? WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;

                for (int i = 0; i < 10; i++) {
                    if (oopID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, oopName[i].getText());
                    pst.setString(2, oopWeek[i][0].getText());
                    pst.setString(3, oopWeek[i][1].getText());
                    pst.setString(4, oopWeek[i][2].getText());
                    pst.setString(5, oopWeek[i][3].getText());
                    pst.setString(6, oopID[i].getText());
                    updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close();
                 // Refresh window
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        // ================= UPDATE INTEG =================
        else if (e.getSource() == btnUpdate2) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE integgrades SET name=?, quiz=?, exam=?, project=?, final_grade=? WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;

                for (int i = 0; i < 10; i++) {
                    if (integID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, integName[i].getText());
                    pst.setString(2, integWeek[i][0].getText());
                    pst.setString(3, integWeek[i][1].getText());
                    pst.setString(4, integWeek[i][2].getText());
                    pst.setString(5, integWeek[i][3].getText());
                    pst.setString(6, integID[i].getText());
                    updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close();
               executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        // ================= UPDATE CP =================
        else if (e.getSource() == btnUpdate3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE cpgrades SET name=?, quiz=?, exam=?, project=?, final_grade=? WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;

                for (int i = 0; i < 10; i++) {
                    if (cpID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, cpName[i].getText());
                    pst.setString(2, cpWeek[i][0].getText());
                    pst.setString(3, cpWeek[i][1].getText());
                    pst.setString(4, cpWeek[i][2].getText());
                    pst.setString(5, cpWeek[i][3].getText());
                    pst.setString(6, cpID[i].getText());
                    updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close();
                executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        // ================= UPDATE NET =================
        else if (e.getSource() == btnUpdate4) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE netgrades SET name=?, quiz=?, exam=?, project=?, final_grade=? WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;

                for (int i = 0; i < 10; i++) {
                    if (netID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, netName[i].getText());
                    pst.setString(2, netWeek[i][0].getText());
                    pst.setString(3, netWeek[i][1].getText());
                    pst.setString(4, netWeek[i][2].getText());
                    pst.setString(5, netWeek[i][3].getText());
                    pst.setString(6, netID[i].getText());
                    updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close();
                executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        // ================= UPDATE OS =================
        else if (e.getSource() == btnUpdate5) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE osgrades SET name=?, quiz=?, exam=?, project=?, final_grade=? WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;

                for (int i = 0; i < 10; i++) {
                    if (osID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, osName[i].getText());
                    pst.setString(2, osWeek[i][0].getText());
                    pst.setString(3, osWeek[i][1].getText());
                    pst.setString(4, osWeek[i][2].getText());
                    pst.setString(5, osWeek[i][3].getText());
                    pst.setString(6, osID[i].getText());
                    updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close();
                executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
    }
    // ================= EXECUTE DATABASE RETRIEVAL =================
    private void executeDatabaseSearch() {
        String sql = "";
        if (currentTab.equals("OOP")) sql = "SELECT * FROM oopgrades LIMIT 10";
        else if (currentTab.equals("INTEG")) sql = "SELECT * FROM integgrades LIMIT 10";
        else if (currentTab.equals("CP")) sql = "SELECT * FROM cpgrades LIMIT 10";
        else if (currentTab.equals("NET")) sql = "SELECT * FROM netgrades LIMIT 10";
        else if (currentTab.equals("OS")) sql = "SELECT * FROM osgrades LIMIT 10";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            ResultSet rs = pst.executeQuery();

            JTextField[] idArr = currentTab.equals("OOP") ? oopID : currentTab.equals("INTEG") ? integID : currentTab.equals("CP") ? cpID : currentTab.equals("NET") ? netID : osID;
            JTextField[] nameArr = currentTab.equals("OOP") ? oopName : currentTab.equals("INTEG") ? integName : currentTab.equals("CP") ? cpName : currentTab.equals("NET") ? netName : osName;
            JTextField[][] weekArr = currentTab.equals("OOP") ? oopWeek : currentTab.equals("INTEG") ? integWeek : currentTab.equals("CP") ? cpWeek : currentTab.equals("NET") ? netWeek : osWeek;
            
            // Wipe UI fields completely clear first
            for (int r = 0; r < 10; r++) {
                idArr[r].setText("");
                nameArr[r].setText("");
                for (int c = 0; c < 4; c++) { 
                    weekArr[r][c].setText("");
                }
            }

            int rowCounter = 0;
                while (rs.next() && rowCounter < 10) {
                idArr[rowCounter].setText(rs.getString("student_id"));
                nameArr[rowCounter].setText(rs.getString("name"));
                weekArr[rowCounter][0].setText(rs.getString("quiz"));
                weekArr[rowCounter][1].setText(rs.getString("exam"));
                weekArr[rowCounter][2].setText(rs.getString("project"));
                weekArr[rowCounter][3].setText(rs.getString("final_grade"));
                
                rowCounter++;
            }
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(this, "Retrieval Error: " + ex.getMessage());
        }
    }

    // ================= EXECUTE DATABASE DELETION WITH GUI REFRESH =================
    private void executeDatabaseDelete(String targetID) {
        String sql = "";
        if (currentTab.equals("OOP")) sql = "DELETE FROM oopgrades WHERE student_id = ?";
        else if (currentTab.equals("INTEG")) sql = "DELETE FROM integgrades WHERE student_id = ?";
        else if (currentTab.equals("CP")) sql = "DELETE FROM cpgrades WHERE student_id = ?";
        else if (currentTab.equals("NET")) sql = "DELETE FROM netgrades WHERE student_id = ?";
        else if (currentTab.equals("OS")) sql = "DELETE FROM osgrades WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, targetID);
            int status = pst.executeUpdate();

            if (status > 0) {
                JOptionPane.showMessageDialog(this, "Student ID (" + targetID + ") cleared successfully!");
                executeDatabaseSearch(); 
            } else {
                JOptionPane.showMessageDialog(this, "No entry identified with ID: " + targetID);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private JTextField addField(JPanel panel, String text, int x, int y, int w, int h) {
        JTextField txt = new JTextField(text);
        txt.setBounds(x, y, w, h);
        panel.add(txt);
        return txt;
    }

    private JButton createSideButton(String text, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(10, y, 160, 35);
        return btn;
    }   
}



