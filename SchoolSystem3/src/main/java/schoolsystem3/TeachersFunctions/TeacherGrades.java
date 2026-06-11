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

    private JTextField[] oopID    = new JTextField[10];
    private JTextField[] integID  = new JTextField[10];
    private JTextField[] cpID     = new JTextField[10];
    private JTextField[] netID    = new JTextField[10];
    private JTextField[] osID     = new JTextField[10];

    private JTextField[] oopName   = new JTextField[10];
    private JTextField[][] oopWeek = new JTextField[10][4];

    private JTextField[] integName   = new JTextField[10];
    private JTextField[][] integWeek = new JTextField[10][4];

    private JTextField[] cpName   = new JTextField[10];
    private JTextField[][] cpWeek = new JTextField[10][4];

    private JTextField[] netName   = new JTextField[10];
    private JTextField[][] netWeek = new JTextField[10][4];

    private JTextField[] osName   = new JTextField[10];
    private JTextField[][] osWeek = new JTextField[10][4];

    private String currentTab = "OOP";

    public TeacherGrades() {

        setSize(1000, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Background 
        JPanel background = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(192, 0, 11, 12));
                g2.fillRect(185, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(null);
        setContentPane(background);

        // Sidebar 
        pnlSideBar = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(255, 255, 255, 18));
                g2.fillOval(-40, 480, 280, 280);
                g2.fillOval(10, -70, 210, 210);
            }
        };
        pnlSideBar.setBounds(0, 0, 185, 720);
        pnlSideBar.setOpaque(false);
        background.add(pnlSideBar);

        //  Sidebar: Logo & title 
        JLabel lblSystem = new JLabel("PUP", SwingConstants.CENTER);
        lblSystem.setBounds(10, 28, 165, 30);
        lblSystem.setFont(new Font("Arial", Font.BOLD, 22));
        lblSystem.setForeground(Color.WHITE);
        pnlSideBar.add(lblSystem);

        JLabel lblSubSys = new JLabel("School System", SwingConstants.CENTER);
        lblSubSys.setBounds(10, 52, 165, 20);
        lblSubSys.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSubSys.setForeground(new Color(255, 210, 210));
        pnlSideBar.add(lblSubSys);

        JSeparator sideDiv = new JSeparator();
        sideDiv.setBounds(20, 80, 145, 1);
        sideDiv.setForeground(new Color(255, 255, 255, 60));
        pnlSideBar.add(sideDiv);

        JLabel lblMenu = new JLabel("SUBJECTS", SwingConstants.CENTER);
        lblMenu.setBounds(10, 90, 165, 24);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 10));
        lblMenu.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblMenu);

        //  Sidebar: Subject buttons 
        btnOOP   = createSideButton("OOP",           122);
        btnInteg = createSideButton("Integrative",   168);
        btnCP    = createSideButton("Programming",   214);
        btnNetAd = createSideButton("Network",       260);
        btnOS    = createSideButton("Operating Sys", 306);

        pnlSideBar.add(btnOOP);
        pnlSideBar.add(btnInteg);
        pnlSideBar.add(btnCP);
        pnlSideBar.add(btnNetAd);
        pnlSideBar.add(btnOS);

        JSeparator sideFootDiv = new JSeparator();
        sideFootDiv.setBounds(20, 660, 145, 1);
        sideFootDiv.setForeground(new Color(255, 255, 255, 40));
        pnlSideBar.add(sideFootDiv);

        JLabel lblFooter = new JLabel("© 2025 PUP", SwingConstants.CENTER);
        lblFooter.setBounds(10, 668, 165, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblFooter);

        //  Main card panel 
        cardLayout = new CardLayout();
        pnlMain = new JPanel(cardLayout);
        pnlMain.setBounds(185, 0, 815, 660);
        pnlMain.setOpaque(false);
        background.add(pnlMain);

        pnlMain.add(createOOPPanel(),     "OOP");
        pnlMain.add(createIntegPanel(),   "INTEG");
        pnlMain.add(createCompPanel(),    "CP");
        pnlMain.add(createNetAdPanel(),   "NET");
        pnlMain.add(createOSPanel(),      "OS");

        //  Back button 
        btnback = new JButton("← Back");
        styleActionButton(btnback, false);
        btnback.setBounds(760, 535, 120, 36);
        background.add(btnback);

        //  Action listeners 
        btnOOP.addActionListener(this);
        btnInteg.addActionListener(this);
        btnCP.addActionListener(this);
        btnNetAd.addActionListener(this);
        btnOS.addActionListener(this);
        btnback.addActionListener(this);

        cardLayout.show(pnlMain, "OOP");
    }

    //  Shared panel builder 
    private JPanel buildSubjectPanel(String titleText,
                                     JTextField[] idArr,
                                     JTextField[] nameArr,
                                     JTextField[][] weekArr,
                                     JButton submit, JButton update,
                                     JButton delete, JButton search,
                                     JButton compute) {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);

        // Header bar
        JPanel header = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setBounds(0, 0, 815, 58);
        header.setOpaque(false);
        panel.add(header);

        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        title.setBounds(0, 0, 815, 58);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title);

        // Column headers
        String[] colLabels = {"Student ID", "Name", "Quiz", "Exam", "Project", "Final Grade"};
        int[]    colX      = { 15,           110,    230,    318,    406,       494 };
        int[]    colW      = { 90,            110,    83,     83,     83,        90  };

        JPanel colHeaderBg = new JPanel(null);
        colHeaderBg.setBounds(0, 58, 815, 30);
        colHeaderBg.setBackground(new Color(255, 240, 240));
        panel.add(colHeaderBg);

        for (int c = 0; c < colLabels.length; c++) {
            JLabel h = new JLabel(colLabels[c], SwingConstants.CENTER);
            h.setBounds(colX[c], 58, colW[c], 30);
            h.setFont(new Font("Arial", Font.BOLD, 11));
            h.setForeground(Color.decode("#C0000B"));
            panel.add(h);
        }

        JSeparator headerLine = new JSeparator();
        headerLine.setBounds(10, 89, 790, 1);
        headerLine.setForeground(new Color(192, 0, 11, 80));
        panel.add(headerLine);

        // Data rows
        for (int i = 0; i < 10; i++) {
            int y = 95 + (i * 34);
            Color rowBg = (i % 2 == 0) ? Color.WHITE : new Color(255, 247, 247);
            idArr[i]      = addStyledField(panel, colX[0], y, colW[0], rowBg, true);
            nameArr[i]    = addStyledField(panel, colX[1], y, colW[1], rowBg, true);
            weekArr[i][0] = addStyledField(panel, colX[2], y, colW[2], rowBg, true);
            weekArr[i][1] = addStyledField(panel, colX[3], y, colW[3], rowBg, true);
            weekArr[i][2] = addStyledField(panel, colX[4], y, colW[4], rowBg, true);
            weekArr[i][3] = addStyledField(panel, colX[5], y, colW[5], rowBg, false); // Final Grade: read-only
        }

        JSeparator footerLine = new JSeparator();
        footerLine.setBounds(10, 440, 790, 1);
        footerLine.setForeground(new Color(192, 0, 11, 80));
        panel.add(footerLine);

        // Action buttons
        int[]    btnX    = { 20, 150, 280, 410, 540 };
        JButton[] btns   = { submit, update, delete, search, compute };
        String[]  labels = { "Submit", "Update", "Delete", "Search", "Compute" };

        for (int b = 0; b < btns.length; b++) {
            btns[b].setBounds(btnX[b], 452, 120, 36);
            btns[b].setText(labels[b]);
            styleActionButton(btns[b], b == 0);
            panel.add(btns[b]);
            btns[b].addActionListener(this);
        }

        return panel;
    }

    private JTextField addStyledField(JPanel panel, int x, int y, int w, Color bg, boolean editable) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, 28);
        tf.setFont(new Font("Arial", Font.PLAIN, 11));
        tf.setBackground(editable ? bg : new Color(245, 245, 245));
        tf.setForeground(Color.decode("#1a1a1a"));
        tf.setEditable(editable);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 200, 200), 1),
            BorderFactory.createEmptyBorder(2, 4, 2, 4)
        ));
        panel.add(tf);
        return tf;
    }

    private JPanel createOOPPanel() {
        btnSubmit = new JButton(); btnUpdate = new JButton();
        btnDelete = new JButton(); btnSearch = new JButton(); btncomp = new JButton();
        return buildSubjectPanel("OOP Grades", oopID, oopName, oopWeek,
            btnSubmit, btnUpdate, btnDelete, btnSearch, btncomp);
    }

    private JPanel createIntegPanel() {
        btnSubmit2 = new JButton(); btnUpdate2 = new JButton();
        btnDelete2 = new JButton(); btnSearch2 = new JButton(); btncomp2 = new JButton();
        return buildSubjectPanel("Integrative Grades", integID, integName, integWeek,
            btnSubmit2, btnUpdate2, btnDelete2, btnSearch2, btncomp2);
    }

    private JPanel createCompPanel() {
        btnSubmit3 = new JButton(); btnUpdate3 = new JButton();
        btnDelete3 = new JButton(); btnSearch3 = new JButton(); btncomp3 = new JButton();
        return buildSubjectPanel("Computer Programming Grades", cpID, cpName, cpWeek,
            btnSubmit3, btnUpdate3, btnDelete3, btnSearch3, btncomp3);
    }

    private JPanel createNetAdPanel() {
        btnSubmit4 = new JButton(); btnUpdate4 = new JButton();
        btnDelete4 = new JButton(); btnSearch4 = new JButton(); btncomp4 = new JButton();
        return buildSubjectPanel("Network Administration Grades", netID, netName, netWeek,
            btnSubmit4, btnUpdate4, btnDelete4, btnSearch4, btncomp4);
    }

    private JPanel createOSPanel() {
        btnSubmit5 = new JButton(); btnUpdate5 = new JButton();
        btnDelete5 = new JButton(); btnSearch5 = new JButton(); btncomp5 = new JButton();
        return buildSubjectPanel("Operating System Grades", osID, osName, osWeek,
            btnSubmit5, btnUpdate5, btnDelete5, btnSearch5, btncomp5);
    }

    private JButton createSideButton(String text, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(18, y, 150, 36);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 70), 1));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setOpaque(true);
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(160, 0, 9));
                btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 70), 1));
            }
        });
        return btn;
    }

    private void styleActionButton(JButton btn, boolean isPrimary) {
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (isPrimary) {
            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.decode("#C0000B"));
            btn.setOpaque(true);
            btn.setBorderPainted(false);
        } else {
            btn.setForeground(Color.decode("#C0000B"));
            btn.setBackground(Color.WHITE);
            btn.setOpaque(true);
            btn.setBorder(BorderFactory.createLineBorder(Color.decode("#C0000B"), 2));
        }
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isPrimary) {
                    btn.setBackground(Color.decode("#A80009"));
                } else {
                    btn.setBackground(Color.decode("#C0000B"));
                    btn.setForeground(Color.WHITE);
                    btn.setBorder(BorderFactory.createLineBorder(Color.decode("#C0000B"), 2));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (isPrimary) {
                    btn.setBackground(Color.decode("#C0000B"));
                } else {
                    btn.setBackground(Color.WHITE);
                    btn.setForeground(Color.decode("#C0000B"));
                    btn.setBorder(BorderFactory.createLineBorder(Color.decode("#C0000B"), 2));
                }
            }
        });
    }

    private void performComputation(JTextField[][] weekFields) {
        for (int i = 0; i < 10; i++) {
            try {
                if (weekFields[i][0].getText().trim().isEmpty()) continue;
                double q = Double.parseDouble(weekFields[i][0].getText().trim());
                double ex = Double.parseDouble(weekFields[i][1].getText().trim());
                double p = Double.parseDouble(weekFields[i][2].getText().trim());
                double finalGrade = (q + ex + p) / 3.0;
                weekFields[i][3].setText(String.format("%.2f", finalGrade));
            } catch (Exception e) { /* skip non-numeric */ }
        }
    }

    private boolean studentExists(Connection conn, String tableName, String id) throws SQLException {
        String checkSql = "SELECT 1 FROM " + tableName + " WHERE student_id = ?";
        try (PreparedStatement checkPst = conn.prepareStatement(checkSql)) {
            checkPst.setString(1, id);
            try (ResultSet rs = checkPst.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void submitGrades(JTextField[] idArr, JTextField[] nameArr,
                               JTextField[][] weekArr, String tableName, String label) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
            String sql = "INSERT INTO " + tableName +
                " (student_id, name, quiz, exam, project, final_grade) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            int savedRows = 0;
            for (int i = 0; i < 10; i++) {
                String id   = idArr[i].getText().trim();
                String name = nameArr[i].getText().trim();
                if (id.isEmpty() && name.isEmpty()) continue;
                if (studentExists(conn, tableName, id)) continue;
                pst.setString(1, id);
                pst.setString(2, name);
                pst.setString(3, weekArr[i][0].getText());
                pst.setString(4, weekArr[i][1].getText());
                pst.setString(5, weekArr[i][2].getText());
                pst.setString(6, weekArr[i][3].getText());
                pst.executeUpdate();
                savedRows++;
            }
            JOptionPane.showMessageDialog(this,
                savedRows > 0 ? savedRows + " new " + label + " Grade(s) saved successfully!"
                              : "No new data to save.");
            pst.close(); conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, label + " Error: " + ex.getMessage());
        }
    }

    private void updateGrades(JTextField[] idArr, JTextField[] nameArr,
                               JTextField[][] weekArr, String tableName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
            String sql = "UPDATE " + tableName +
                " SET name=?, quiz=?, exam=?, project=?, final_grade=? WHERE student_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            int updatedCount = 0;
            for (int i = 0; i < 10; i++) {
                if (idArr[i].getText().trim().isEmpty()) continue;
                pst.setString(1, nameArr[i].getText());
                pst.setString(2, weekArr[i][0].getText());
                pst.setString(3, weekArr[i][1].getText());
                pst.setString(4, weekArr[i][2].getText());
                pst.setString(5, weekArr[i][3].getText());
                pst.setString(6, idArr[i].getText());
                updatedCount += pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
            pst.close(); conn.close();
            executeDatabaseSearch();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //  Compute buttons 
        if      (e.getSource() == btncomp)  { performComputation(oopWeek); }
        else if (e.getSource() == btncomp2) { performComputation(integWeek); }
        else if (e.getSource() == btncomp3) { performComputation(cpWeek); }
        else if (e.getSource() == btncomp4) { performComputation(netWeek); }
        else if (e.getSource() == btncomp5) { performComputation(osWeek); }

        //  Navigation 
        else if (e.getSource() == btnback) {
            setVisible(false);
            new TeacherDashB().setVisible(true);
        }
        else if (e.getSource() == btnOOP)   { currentTab = "OOP";   cardLayout.show(pnlMain, "OOP"); }
        else if (e.getSource() == btnInteg) { currentTab = "INTEG"; cardLayout.show(pnlMain, "INTEG"); }
        else if (e.getSource() == btnCP)    { currentTab = "CP";    cardLayout.show(pnlMain, "CP"); }
        else if (e.getSource() == btnNetAd) { currentTab = "NET";   cardLayout.show(pnlMain, "NET"); }
        else if (e.getSource() == btnOS)    { currentTab = "OS";    cardLayout.show(pnlMain, "OS"); }

        //  Search 
        else if (e.getSource() == btnSearch  || e.getSource() == btnSearch2 ||
                 e.getSource() == btnSearch3 || e.getSource() == btnSearch4 ||
                 e.getSource() == btnSearch5) {
            executeDatabaseSearch();
        }

        //  Delete 
        else if (e.getSource() == btnDelete  || e.getSource() == btnDelete2 ||
                 e.getSource() == btnDelete3 || e.getSource() == btnDelete4 ||
                 e.getSource() == btnDelete5) {
            String deleteID = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");
            if (deleteID != null && !deleteID.trim().isEmpty())
                executeDatabaseDelete(deleteID.trim());
        }

        //  Submit 
        else if (e.getSource() == btnSubmit)  { submitGrades(oopID,   oopName,   oopWeek,   "oopgrades",   "OOP"); }
        else if (e.getSource() == btnSubmit2) { submitGrades(integID, integName, integWeek, "integgrades", "Integrative"); }
        else if (e.getSource() == btnSubmit3) { submitGrades(cpID,    cpName,    cpWeek,    "cpgrades",    "CP"); }
        else if (e.getSource() == btnSubmit4) { submitGrades(netID,   netName,   netWeek,   "netgrades",   "Network"); }
        else if (e.getSource() == btnSubmit5) { submitGrades(osID,    osName,    osWeek,    "osgrades",    "OS"); }

        //  Update 
        else if (e.getSource() == btnUpdate)  { updateGrades(oopID,   oopName,   oopWeek,   "oopgrades"); }
        else if (e.getSource() == btnUpdate2) { updateGrades(integID, integName, integWeek, "integgrades"); }
        else if (e.getSource() == btnUpdate3) { updateGrades(cpID,    cpName,    cpWeek,    "cpgrades"); }
        else if (e.getSource() == btnUpdate4) { updateGrades(netID,   netName,   netWeek,   "netgrades"); }
        else if (e.getSource() == btnUpdate5) { updateGrades(osID,    osName,    osWeek,    "osgrades"); }
    }

    private void executeDatabaseSearch() {
        String sql = "";
        if      (currentTab.equals("OOP"))   sql = "SELECT * FROM oopgrades LIMIT 10";
        else if (currentTab.equals("INTEG")) sql = "SELECT * FROM integgrades LIMIT 10";
        else if (currentTab.equals("CP"))    sql = "SELECT * FROM cpgrades LIMIT 10";
        else if (currentTab.equals("NET"))   sql = "SELECT * FROM netgrades LIMIT 10";
        else if (currentTab.equals("OS"))    sql = "SELECT * FROM osgrades LIMIT 10";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
             PreparedStatement pst = conn.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();

            JTextField[] idArr   = currentTab.equals("OOP") ? oopID   : currentTab.equals("INTEG") ? integID   : currentTab.equals("CP") ? cpID   : currentTab.equals("NET") ? netID   : osID;
            JTextField[] nameArr = currentTab.equals("OOP") ? oopName : currentTab.equals("INTEG") ? integName : currentTab.equals("CP") ? cpName : currentTab.equals("NET") ? netName : osName;
            JTextField[][] weekArr = currentTab.equals("OOP") ? oopWeek : currentTab.equals("INTEG") ? integWeek : currentTab.equals("CP") ? cpWeek : currentTab.equals("NET") ? netWeek : osWeek;

            for (int r = 0; r < 10; r++) {
                idArr[r].setText(""); nameArr[r].setText("");
                for (int c = 0; c < 4; c++) weekArr[r][c].setText("");
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

    private void executeDatabaseDelete(String targetID) {
        String sql = "";
        if      (currentTab.equals("OOP"))   sql = "DELETE FROM oopgrades WHERE student_id = ?";
        else if (currentTab.equals("INTEG")) sql = "DELETE FROM integgrades WHERE student_id = ?";
        else if (currentTab.equals("CP"))    sql = "DELETE FROM cpgrades WHERE student_id = ?";
        else if (currentTab.equals("NET"))   sql = "DELETE FROM netgrades WHERE student_id = ?";
        else if (currentTab.equals("OS"))    sql = "DELETE FROM osgrades WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, targetID);
            int status = pst.executeUpdate();
            if (status > 0) {
                JOptionPane.showMessageDialog(this, "Student ID (" + targetID + ") deleted successfully!");
                executeDatabaseSearch();
            } else {
                JOptionPane.showMessageDialog(this, "No entry found with ID: " + targetID);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}