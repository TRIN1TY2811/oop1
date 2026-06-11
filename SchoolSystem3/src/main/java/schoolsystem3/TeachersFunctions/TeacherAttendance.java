package schoolsystem3.TeachersFunctions;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TeacherAttendance extends JFrame implements ActionListener {

    private JButton btnback, btnOOP, btnInteg, btnCP, btnNetAd, btnOS;
    private JButton btnSubmit, btnSubmit2, btnSubmit3, btnSubmit4, btnSubmit5;
    private JButton btnUpdate, btnUpdate2, btnUpdate3, btnUpdate4, btnUpdate5;
    private JButton btnDelete, btnDelete2, btnDelete3, btnDelete4, btnDelete5;
    private JButton btnSearch, btnSearch2, btnSearch3, btnSearch4, btnSearch5;
    private JPanel pnlSideBar, pnlMain;
    private CardLayout cardLayout;

    private JTextField[] oopID    = new JTextField[10];
    private JTextField[] integID  = new JTextField[10];
    private JTextField[] cpID     = new JTextField[10];
    private JTextField[] netID    = new JTextField[10];
    private JTextField[] osID     = new JTextField[10];

    private JTextField[] oopName   = new JTextField[10];
    private JTextField[][] oopWeek = new JTextField[10][5];

    private JTextField[] integName   = new JTextField[10];
    private JTextField[][] integWeek = new JTextField[10][5];

    private JTextField[] cpName   = new JTextField[10];
    private JTextField[][] cpWeek = new JTextField[10][5];

    private JTextField[] netName   = new JTextField[10];
    private JTextField[][] netWeek = new JTextField[10][5];

    private JTextField[] osName   = new JTextField[10];
    private JTextField[][] osWeek = new JTextField[10][5];

    private String currentTab = "OOP";

    public TeacherAttendance() {

        setSize(1000, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel background = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(192, 0, 11, 12));
                g2.fillRect(190, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(null);
        setContentPane(background);

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

        btnOOP   = createSideButton("OOP", 122);
        btnInteg = createSideButton("Integrative", 168);
        btnCP    = createSideButton("Programming", 214);
        btnNetAd = createSideButton("Network", 260);
        btnOS    = createSideButton("Operating Sys", 306);

        pnlSideBar.add(btnOOP);
        pnlSideBar.add(btnInteg);
        pnlSideBar.add(btnCP);
        pnlSideBar.add(btnNetAd);
        pnlSideBar.add(btnOS);

        JSeparator sideFootDiv = new JSeparator();
        sideFootDiv.setBounds(20, 630, 145, 1);
        sideFootDiv.setForeground(new Color(255, 255, 255, 40));
        pnlSideBar.add(sideFootDiv);

        JLabel lblFooter = new JLabel("© 2025 PUP", SwingConstants.CENTER);
        lblFooter.setBounds(10, 638, 165, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblFooter);

        cardLayout = new CardLayout();
        pnlMain = new JPanel(cardLayout);
        pnlMain.setBounds(185, 0, 815, 660);
        pnlMain.setOpaque(false);
        background.add(pnlMain);

        pnlMain.add(createOOPPanel(),   "OOP");
        pnlMain.add(createIntegPanel(), "INTEG");
        pnlMain.add(createCompPanel(),  "CP");
        pnlMain.add(createNetAdPanel(), "NET");
        pnlMain.add(createOSPanel(),    "OS");

        btnback = new JButton("← Back");
        styleActionButton(btnback, false);
        btnback.setBounds(760, 535, 120, 36);
        background.add(btnback);

        btnOOP.addActionListener(this);
        btnInteg.addActionListener(this);
        btnCP.addActionListener(this);
        btnNetAd.addActionListener(this);
        btnOS.addActionListener(this);
        btnback.addActionListener(this);

        cardLayout.show(pnlMain, "OOP");
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

    private JPanel buildSubjectPanel(String titleText,
                                     JTextField[] idArr,
                                     JTextField[] nameArr,
                                     JTextField[][] weekArr,
                                     JButton submit, JButton update,
                                     JButton delete, JButton search) {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);

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

        String[] colLabels = {"Student ID", "Name", "Week 1", "Week 2", "Week 3", "Week 4", "Week 5"};
        int[]    colX      = { 15,           110,    215,      292,      369,      446,      523 };
        int[]    colW      = { 90,            95,     72,       72,       72,       72,       72  };

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

        for (int i = 0; i < 10; i++) {
            int y = 95 + (i * 34);
            Color rowBg = (i % 2 == 0) ? Color.WHITE : new Color(255, 247, 247);
            idArr[i]      = addStyledField(panel, colX[0], y, colW[0], rowBg);
            nameArr[i]    = addStyledField(panel, colX[1], y, colW[1], rowBg);
            weekArr[i][0] = addStyledField(panel, colX[2], y, colW[2], rowBg);
            weekArr[i][1] = addStyledField(panel, colX[3], y, colW[3], rowBg);
            weekArr[i][2] = addStyledField(panel, colX[4], y, colW[4], rowBg);
            weekArr[i][3] = addStyledField(panel, colX[5], y, colW[5], rowBg);
            weekArr[i][4] = addStyledField(panel, colX[6], y, colW[6], rowBg);
        }

        JSeparator footerLine = new JSeparator();
        footerLine.setBounds(10, 440, 790, 1);
        footerLine.setForeground(new Color(192, 0, 11, 80));
        panel.add(footerLine);

        int[] btnX    = { 55, 195, 335, 475 };
        JButton[] btns  = { submit, update, delete, search };
        String[]  labels = { "Submit", "Update", "Delete", "Search" };

        for (int b = 0; b < btns.length; b++) {
            btns[b].setBounds(btnX[b], 452, 120, 36);
            btns[b].setText(labels[b]);
            styleActionButton(btns[b], b == 0);
            panel.add(btns[b]);
            btns[b].addActionListener(this);
        }

        return panel;
    }

    private JTextField addStyledField(JPanel panel, int x, int y, int w, Color bg) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, 28);
        tf.setFont(new Font("Arial", Font.PLAIN, 11));
        tf.setBackground(bg);
        tf.setForeground(Color.decode("#1a1a1a"));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 200, 200), 1),
            BorderFactory.createEmptyBorder(2, 4, 2, 4)
        ));
        panel.add(tf);
        return tf;
    }

    private JPanel createOOPPanel() {
        btnSubmit = new JButton(); btnUpdate = new JButton();
        btnDelete = new JButton(); btnSearch = new JButton();
        return buildSubjectPanel("OOP Attendance", oopID, oopName, oopWeek, btnSubmit, btnUpdate, btnDelete, btnSearch);
    }

    private JPanel createIntegPanel() {
        btnSubmit2 = new JButton(); btnUpdate2 = new JButton();
        btnDelete2 = new JButton(); btnSearch2 = new JButton();
        return buildSubjectPanel("Integrative Attendance", integID, integName, integWeek, btnSubmit2, btnUpdate2, btnDelete2, btnSearch2);
    }

    private JPanel createCompPanel() {
        btnSubmit3 = new JButton(); btnUpdate3 = new JButton();
        btnDelete3 = new JButton(); btnSearch3 = new JButton();
        return buildSubjectPanel("Computer Programming Attendance", cpID, cpName, cpWeek, btnSubmit3, btnUpdate3, btnDelete3, btnSearch3);
    }

    private JPanel createNetAdPanel() {
        btnSubmit4 = new JButton(); btnUpdate4 = new JButton();
        btnDelete4 = new JButton(); btnSearch4 = new JButton();
        return buildSubjectPanel("Network Administration Attendance", netID, netName, netWeek, btnSubmit4, btnUpdate4, btnDelete4, btnSearch4);
    }

    private JPanel createOSPanel() {
        btnSubmit5 = new JButton(); btnUpdate5 = new JButton();
        btnDelete5 = new JButton(); btnSearch5 = new JButton();
        return buildSubjectPanel("Operating System Attendance", osID, osName, osWeek, btnSubmit5, btnUpdate5, btnDelete5, btnSearch5);
    }

    private boolean studentExists(Connection conn, String tableName, String id) throws SQLException {
        String checkSql = "SELECT 1 FROM " + tableName + " WHERE ID=?";
        try (PreparedStatement checkPst = conn.prepareStatement(checkSql)) {
            checkPst.setString(1, id);
            try (ResultSet rs = checkPst.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnback) {
            setVisible(false);
            new TeacherDashB().setVisible(true);
        } else if (e.getSource() == btnOOP) {
            currentTab = "OOP"; cardLayout.show(pnlMain, "OOP");
        } else if (e.getSource() == btnInteg) {
            currentTab = "INTEG"; cardLayout.show(pnlMain, "INTEG");
        } else if (e.getSource() == btnCP) {
            currentTab = "CP"; cardLayout.show(pnlMain, "CP");
        } else if (e.getSource() == btnNetAd) {
            currentTab = "NET"; cardLayout.show(pnlMain, "NET");
        } else if (e.getSource() == btnOS) {
            currentTab = "OS"; cardLayout.show(pnlMain, "OS");
        } else if (e.getSource() == btnSearch || e.getSource() == btnSearch2 || e.getSource() == btnSearch3 || e.getSource() == btnSearch4 || e.getSource() == btnSearch5) {
            executeDatabaseSearch();
        } else if (e.getSource() == btnDelete || e.getSource() == btnDelete2 || e.getSource() == btnDelete3 || e.getSource() == btnDelete4 || e.getSource() == btnDelete5) {
            String deleteID = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");
            if (deleteID != null && !deleteID.trim().isEmpty()) executeDatabaseDelete(deleteID.trim());
        } else if (e.getSource() == btnSubmit) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "INSERT INTO oopattendance (ID, Name, Week1, Week2, Week3, Week4, Week5) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                int savedRows = 0;
                for (int i = 0; i < 10; i++) {
                    String id = oopID[i].getText().trim(); String name = oopName[i].getText().trim();
                    if (id.isEmpty() && name.isEmpty()) continue;
                    if (studentExists(conn, "oopattendance", id)) continue;
                    pst.setString(1, id); pst.setString(2, name);
                    pst.setString(3, oopWeek[i][0].getText()); pst.setString(4, oopWeek[i][1].getText());
                    pst.setString(5, oopWeek[i][2].getText()); pst.setString(6, oopWeek[i][3].getText());
                    pst.setString(7, oopWeek[i][4].getText());
                    pst.executeUpdate(); savedRows++;
                }
                JOptionPane.showMessageDialog(this, savedRows + " new OOP Attendance saved safely!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "OOP Error: " + ex.getMessage()); }
        } else if (e.getSource() == btnSubmit2) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "INSERT INTO integattendance (ID, Name, Week1, Week2, Week3, Week4, Week5) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                int savedRows = 0;
                for (int i = 0; i < 10; i++) {
                    String id = integID[i].getText().trim(); String name = integName[i].getText().trim();
                    if (id.isEmpty() && name.isEmpty()) continue;
                    if (studentExists(conn, "integattendance", id)) continue;
                    pst.setString(1, id); pst.setString(2, name);
                    pst.setString(3, integWeek[i][0].getText()); pst.setString(4, integWeek[i][1].getText());
                    pst.setString(5, integWeek[i][2].getText()); pst.setString(6, integWeek[i][3].getText());
                    pst.setString(7, integWeek[i][4].getText());
                    pst.executeUpdate(); savedRows++;
                }
                JOptionPane.showMessageDialog(this, savedRows + " new Integrative Attendance saved safely!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Integrative Error: " + ex.getMessage()); }
        } else if (e.getSource() == btnSubmit3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "INSERT INTO cpattendance (ID, Name, Week1, Week2, Week3, Week4, Week5) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                int savedRows = 0;
                for (int i = 0; i < 10; i++) {
                    String id = cpID[i].getText().trim(); String name = cpName[i].getText().trim();
                    if (id.isEmpty() && name.isEmpty()) continue;
                    if (studentExists(conn, "cpattendance", id)) continue;
                    pst.setString(1, id); pst.setString(2, name);
                    pst.setString(3, cpWeek[i][0].getText()); pst.setString(4, cpWeek[i][1].getText());
                    pst.setString(5, cpWeek[i][2].getText()); pst.setString(6, cpWeek[i][3].getText());
                    pst.setString(7, cpWeek[i][4].getText());
                    pst.executeUpdate(); savedRows++;
                }
                JOptionPane.showMessageDialog(this, savedRows + " new CP Attendance saved safely!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "CP Error: " + ex.getMessage()); }
        } else if (e.getSource() == btnSubmit4) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "INSERT INTO netattendance (ID, Name, Week1, Week2, Week3, Week4, Week5) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                int savedRows = 0;
                for (int i = 0; i < 10; i++) {
                    String id = netID[i].getText().trim(); String name = netName[i].getText().trim();
                    if (id.isEmpty() && name.isEmpty()) continue;
                    if (studentExists(conn, "netattendance", id)) continue;
                    pst.setString(1, id); pst.setString(2, name);
                    pst.setString(3, netWeek[i][0].getText()); pst.setString(4, netWeek[i][1].getText());
                    pst.setString(5, netWeek[i][2].getText()); pst.setString(6, netWeek[i][3].getText());
                    pst.setString(7, netWeek[i][4].getText());
                    pst.executeUpdate(); savedRows++;
                }
                JOptionPane.showMessageDialog(this, savedRows + " new Network Attendance saved safely!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Network Error: " + ex.getMessage()); }
        } else if (e.getSource() == btnSubmit5) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "INSERT INTO osattendance (ID, Name, Week1, Week2, Week3, Week4, Week5) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                int savedRows = 0;
                for (int i = 0; i < 10; i++) {
                    String id = osID[i].getText().trim(); String name = osName[i].getText().trim();
                    if (id.isEmpty() && name.isEmpty()) continue;
                    if (studentExists(conn, "osattendance", id)) continue;
                    pst.setString(1, id); pst.setString(2, name);
                    pst.setString(3, osWeek[i][0].getText()); pst.setString(4, osWeek[i][1].getText());
                    pst.setString(5, osWeek[i][2].getText()); pst.setString(6, osWeek[i][3].getText());
                    pst.setString(7, osWeek[i][4].getText());
                    pst.executeUpdate(); savedRows++;
                }
                JOptionPane.showMessageDialog(this, savedRows + " new OS Attendance saved safely!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "OS Error: " + ex.getMessage()); }
        } else if (e.getSource() == btnUpdate) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE oopattendance SET Name=?, Week1=?, Week2=?, Week3=?, Week4=?, Week5=? WHERE ID=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;
                for (int i = 0; i < 10; i++) {
                    if (oopID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, oopName[i].getText()); pst.setString(2, oopWeek[i][0].getText());
                    pst.setString(3, oopWeek[i][1].getText()); pst.setString(4, oopWeek[i][2].getText());
                    pst.setString(5, oopWeek[i][3].getText()); pst.setString(6, oopWeek[i][4].getText());
                    pst.setString(7, oopID[i].getText()); updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        } else if (e.getSource() == btnUpdate2) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE integattendance SET Name=?, Week1=?, Week2=?, Week3=?, Week4=?, Week5=? WHERE ID=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;
                for (int i = 0; i < 10; i++) {
                    if (integID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, integName[i].getText()); pst.setString(2, integWeek[i][0].getText());
                    pst.setString(3, integWeek[i][1].getText()); pst.setString(4, integWeek[i][2].getText());
                    pst.setString(5, integWeek[i][3].getText()); pst.setString(6, integWeek[i][4].getText());
                    pst.setString(7, integID[i].getText()); updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        } else if (e.getSource() == btnUpdate3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE cpattendance SET Name=?, Week1=?, Week2=?, Week3=?, Week4=?, Week5=? WHERE ID=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;
                for (int i = 0; i < 10; i++) {
                    if (cpID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, cpName[i].getText()); pst.setString(2, cpWeek[i][0].getText());
                    pst.setString(3, cpWeek[i][1].getText()); pst.setString(4, cpWeek[i][2].getText());
                    pst.setString(5, cpWeek[i][3].getText()); pst.setString(6, cpWeek[i][4].getText());
                    pst.setString(7, cpID[i].getText()); updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        } else if (e.getSource() == btnUpdate4) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "INSERT INTO netattendance (ID, Name, Week1, Week2, Week3, Week4, Week5) VALUES (?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Name=VALUES(Name), Week1=VALUES(Week1), Week2=VALUES(Week2), Week3=VALUES(Week3), Week4=VALUES(Week4), Week5=VALUES(Week5)";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;
                for (int i = 0; i < 10; i++) {
                    if (netID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, netID[i].getText()); pst.setString(2, netName[i].getText());
                    pst.setString(3, netWeek[i][0].getText()); pst.setString(4, netWeek[i][1].getText());
                    pst.setString(5, netWeek[i][2].getText()); pst.setString(6, netWeek[i][3].getText());
                    pst.setString(7, netWeek[i][4].getText()); updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        } else if (e.getSource() == btnUpdate5) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
                String sql = "UPDATE osattendance SET Name=?, Week1=?, Week2=?, Week3=?, Week4=?, Week5=? WHERE ID=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int updatedCount = 0;
                for (int i = 0; i < 10; i++) {
                    if (osID[i].getText().trim().isEmpty()) continue;
                    pst.setString(1, osName[i].getText()); pst.setString(2, osWeek[i][0].getText());
                    pst.setString(3, osWeek[i][1].getText()); pst.setString(4, osWeek[i][2].getText());
                    pst.setString(5, osWeek[i][3].getText()); pst.setString(6, osWeek[i][4].getText());
                    pst.setString(7, osID[i].getText()); updatedCount += pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, updatedCount + " Record(s) updated successfully!");
                pst.close(); conn.close(); executeDatabaseSearch();
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    private void executeDatabaseSearch() {
        String sql = "";
        if (currentTab.equals("OOP"))        sql = "SELECT * FROM oopattendance LIMIT 10";
        else if (currentTab.equals("INTEG")) sql = "SELECT * FROM integattendance LIMIT 10";
        else if (currentTab.equals("CP"))    sql = "SELECT * FROM cpattendance LIMIT 10";
        else if (currentTab.equals("NET"))   sql = "SELECT * FROM netattendance LIMIT 10";
        else if (currentTab.equals("OS"))    sql = "SELECT * FROM osattendance LIMIT 10";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystemdb", "root", "");
             PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            JTextField[] idArr     = currentTab.equals("OOP") ? oopID   : currentTab.equals("INTEG") ? integID   : currentTab.equals("CP") ? cpID   : currentTab.equals("NET") ? netID   : osID;
            JTextField[] nameArr   = currentTab.equals("OOP") ? oopName : currentTab.equals("INTEG") ? integName : currentTab.equals("CP") ? cpName : currentTab.equals("NET") ? netName : osName;
            JTextField[][] weekArr = currentTab.equals("OOP") ? oopWeek : currentTab.equals("INTEG") ? integWeek : currentTab.equals("CP") ? cpWeek : currentTab.equals("NET") ? netWeek : osWeek;
            for (int r = 0; r < 10; r++) {
                idArr[r].setText(""); nameArr[r].setText("");
                for (int c = 0; c < 5; c++) weekArr[r][c].setText("");
            }
            int rowCounter = 0;
            while (rs.next() && rowCounter < 10) {
                idArr[rowCounter].setText(rs.getString("ID"));
                nameArr[rowCounter].setText(rs.getString("Name"));
                weekArr[rowCounter][0].setText(rs.getString("Week1"));
                weekArr[rowCounter][1].setText(rs.getString("Week2"));
                weekArr[rowCounter][2].setText(rs.getString("Week3"));
                weekArr[rowCounter][3].setText(rs.getString("Week4"));
                weekArr[rowCounter][4].setText(rs.getString("Week5"));
                rowCounter++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Retrieval Error: " + ex.getMessage());
        }
    }

    private void executeDatabaseDelete(String targetID) {
        String sql = "";
        if (currentTab.equals("OOP"))        sql = "DELETE FROM oopattendance WHERE ID = ?";
        else if (currentTab.equals("INTEG")) sql = "DELETE FROM integattendance WHERE ID = ?";
        else if (currentTab.equals("CP"))    sql = "DELETE FROM cpattendance WHERE ID = ?";
        else if (currentTab.equals("NET"))   sql = "DELETE FROM netattendance WHERE ID = ?";
        else if (currentTab.equals("OS"))    sql = "DELETE FROM osattendance WHERE ID = ?";
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
}