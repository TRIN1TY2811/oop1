package schoolsystem3.StudentInsideData.Grades;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import schoolsystem3.StudentsData.Student1;

public class Student1Grades extends JFrame implements ActionListener {

    private JTextField txtSearch;
    private JButton btnSearch, btnBack;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JButton btnOOP, btnInteg, btnCP, btnNet, btnOS;

    // ===== OOP =====
    private JTextField[] oopID         = new JTextField[10];
    private JTextField[] oopName       = new JTextField[10];
    private JTextField[] oopQuiz       = new JTextField[10];
    private JTextField[] oopProject    = new JTextField[10];
    private JTextField[] oopExam       = new JTextField[10];
    private JTextField[] oopFinalGrade = new JTextField[10];

    // ===== INTEG =====
    private JTextField[] integID         = new JTextField[10];
    private JTextField[] integName       = new JTextField[10];
    private JTextField[] integQuiz       = new JTextField[10];
    private JTextField[] integProject    = new JTextField[10];
    private JTextField[] integExam       = new JTextField[10];
    private JTextField[] integFinalGrade = new JTextField[10];

    // ===== CP =====
    private JTextField[] cpID         = new JTextField[10];
    private JTextField[] cpName       = new JTextField[10];
    private JTextField[] cpQuiz       = new JTextField[10];
    private JTextField[] cpProject    = new JTextField[10];
    private JTextField[] cpExam       = new JTextField[10];
    private JTextField[] cpFinalGrade = new JTextField[10];

    // ===== NET =====
    private JTextField[] netID         = new JTextField[10];
    private JTextField[] netName       = new JTextField[10];
    private JTextField[] netQuiz       = new JTextField[10];
    private JTextField[] netProject    = new JTextField[10];
    private JTextField[] netExam       = new JTextField[10];
    private JTextField[] netFinalGrade = new JTextField[10];

    // ===== OS =====
    private JTextField[] osID         = new JTextField[10];
    private JTextField[] osName       = new JTextField[10];
    private JTextField[] osQuiz       = new JTextField[10];
    private JTextField[] osProject    = new JTextField[10];
    private JTextField[] osExam       = new JTextField[10];
    private JTextField[] osFinalGrade = new JTextField[10];

    public Student1Grades() {

        setTitle("Student Grades Viewer");
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // ===== SEARCH BAR =====
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

        // ===== SIDE MENU =====
        JPanel side = new JPanel();
        side.setLayout(null);
        side.setBounds(0, 0, 180, 700);
        side.setBackground(Color.LIGHT_GRAY);
        add(side);

        btnOOP   = createButton("OOP", 120);
        btnInteg = createButton("Integrative", 170);
        btnCP    = createButton("Programming", 220);
        btnNet   = createButton("Network", 270);
        btnOS    = createButton("OS", 320);

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

        // ===== MAIN PANEL =====
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBounds(200, 80, 760, 550);
        add(mainPanel);

        // Each panel method fills its own class-level arrays directly
        mainPanel.add(createOOPPanel(),   "OOP");
        mainPanel.add(createIntegPanel(), "INTEG");
        mainPanel.add(createCPPanel(),    "CP");
        mainPanel.add(createNetPanel(),   "NET");
        mainPanel.add(createOSPanel(),    "OS");

        // ===== BACK BUTTON =====
        btnBack = new JButton("Back");
        btnBack.setBounds(820, 610, 150, 40);
        btnBack.addActionListener(this);
        add(btnBack);

        setVisible(true);
    }

    // ================= PANEL BUILDERS =================
    // Each method assigns directly into the class-level arrays so loadTable can update them

    private JPanel createOOPPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        addTitle(panel, "OOP Grades");
        addHeaders(panel);
        for (int i = 0; i < 10; i++) {
            int y = 100 + (i * 35);
            oopID[i]         = createField(panel, 20,  y, 80);
            oopName[i]       = createField(panel, 120, y, 170);
            oopQuiz[i]       = createField(panel, 300, y, 80);
            oopProject[i]    = createField(panel, 390, y, 80);
            oopExam[i]       = createField(panel, 480, y, 80);
            oopFinalGrade[i] = createField(panel, 570, y, 120);
        }
        return panel;
    }

    private JPanel createIntegPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        addTitle(panel, "Integrative Grades");
        addHeaders(panel);
        for (int i = 0; i < 10; i++) {
            int y = 100 + (i * 35);
            integID[i]         = createField(panel, 20,  y, 80);
            integName[i]       = createField(panel, 120, y, 170);
            integQuiz[i]       = createField(panel, 300, y, 80);
            integProject[i]    = createField(panel, 390, y, 80);
            integExam[i]       = createField(panel, 480, y, 80);
            integFinalGrade[i] = createField(panel, 570, y, 120);
        }
        return panel;
    }

    private JPanel createCPPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        addTitle(panel, "Computer Programming Grades");
        addHeaders(panel);
        for (int i = 0; i < 10; i++) {
            int y = 100 + (i * 35);
            cpID[i]         = createField(panel, 20,  y, 80);
            cpName[i]       = createField(panel, 120, y, 170);
            cpQuiz[i]       = createField(panel, 300, y, 80);
            cpProject[i]    = createField(panel, 390, y, 80);
            cpExam[i]       = createField(panel, 480, y, 80);
            cpFinalGrade[i] = createField(panel, 570, y, 120);
        }
        return panel;
    }

    private JPanel createNetPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        addTitle(panel, "Network Administration Grades");
        addHeaders(panel);
        for (int i = 0; i < 10; i++) {
            int y = 100 + (i * 35);
            netID[i]         = createField(panel, 20,  y, 80);
            netName[i]       = createField(panel, 120, y, 170);
            netQuiz[i]       = createField(panel, 300, y, 80);
            netProject[i]    = createField(panel, 390, y, 80);
            netExam[i]       = createField(panel, 480, y, 80);
            netFinalGrade[i] = createField(panel, 570, y, 120);
        }
        return panel;
    }

    private JPanel createOSPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        addTitle(panel, "Operating System Grades");
        addHeaders(panel);
        for (int i = 0; i < 10; i++) {
            int y = 100 + (i * 35);
            osID[i]         = createField(panel, 20,  y, 80);
            osName[i]       = createField(panel, 120, y, 170);
            osQuiz[i]       = createField(panel, 300, y, 80);
            osProject[i]    = createField(panel, 390, y, 80);
            osExam[i]       = createField(panel, 480, y, 80);
            osFinalGrade[i] = createField(panel, 570, y, 120);
        }
        return panel;
    }

    // ================= SHARED HELPERS =================

    private void addTitle(JPanel panel, String text) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);
    }

    private void addHeaders(JPanel panel) {
        String[] labels = {"ID", "Name", "Quiz", "Project", "Exam", "Final Grade"};
        int[]    xPos   = { 20,   120,    300,    390,       480,    570 };
        for (int i = 0; i < labels.length; i++) {
            JLabel h = new JLabel(labels[i]);
            h.setBounds(xPos[i], 60, 80, 25);
            h.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(h);
        }
    }

    private JTextField createField(JPanel panel, int x, int y, int width) {
        JTextField t = new JTextField();
        t.setBounds(x, y, width, 25);
        t.setEditable(false);
        panel.add(t);
        return t;
    }

    private JButton createButton(String text, int y) {
        JButton b = new JButton(text);
        b.setBounds(10, y, 160, 35);
        return b;
    }

    // ================= SEARCH ALL TABLES =================
    private void searchAll() {
        loadTable("oopgrades",   oopID,   oopName,   oopQuiz,   oopProject,   oopExam,   oopFinalGrade);
        loadTable("integgrades", integID, integName, integQuiz, integProject, integExam, integFinalGrade);
        loadTable("cpgrades",    cpID,    cpName,    cpQuiz,    cpProject,    cpExam,    cpFinalGrade);
        loadTable("netgrades",   netID,   netName,   netQuiz,   netProject,   netExam,   netFinalGrade);
        loadTable("osgrades",    osID,    osName,    osQuiz,    osProject,    osExam,    osFinalGrade);
    }

    // ================= REUSABLE LOADER =================
    private void loadTable(String table,
                           JTextField[] id,
                           JTextField[] name,
                           JTextField[] quiz,
                           JTextField[] project,
                           JTextField[] exam,
                           JTextField[] finalGrade) {

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

            // Clear rows first
            for (int i = 0; i < 10; i++) {
                id[i].setText("");
                name[i].setText("");
                quiz[i].setText("");
                project[i].setText("");
                exam[i].setText("");
                finalGrade[i].setText("");
            }

            int i = 0;

            while (rs.next() && i < 10) {
                id[i].setText(rs.getString("ID"));
                name[i].setText(rs.getString("Name"));
                quiz[i].setText(rs.getString("Quiz"));
                project[i].setText(rs.getString("Project"));
                exam[i].setText(rs.getString("Exam"));
                finalGrade[i].setText(rs.getString("FinalGrade"));
                i++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================= ACTIONS =================
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSearch) {
            searchAll();
        }
        else if (e.getSource() == btnOOP)   cardLayout.show(mainPanel, "OOP");
        else if (e.getSource() == btnInteg) cardLayout.show(mainPanel, "INTEG");
        else if (e.getSource() == btnCP)    cardLayout.show(mainPanel, "CP");
        else if (e.getSource() == btnNet)   cardLayout.show(mainPanel, "NET");
        else if (e.getSource() == btnOS)    cardLayout.show(mainPanel, "OS");

        else if (e.getSource() == btnBack) {
            new Student1().setVisible(true);
            dispose();
        }
    }
}