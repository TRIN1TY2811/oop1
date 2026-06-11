package schoolsystem3.StudentInsideData.Grades;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import schoolsystem3.StudentsData.StudentDashB;

public class StudentGrades extends JFrame implements ActionListener {

    // Search & nav
    private JTextField txtSearch;
    private JButton btnSearch, btnBack;
    private JButton btnOOP, btnInteg, btnCP, btnNet, btnOS;
    private CardLayout cardLayout;
    private JPanel pnlMain;
    private String currentTab = "OOP";

    // OOP fields
    private JTextField[] oopID         = new JTextField[10];
    private JTextField[] oopName       = new JTextField[10];
    private JTextField[] oopQuiz       = new JTextField[10];
    private JTextField[] oopProject    = new JTextField[10];
    private JTextField[] oopExam       = new JTextField[10];
    private JTextField[] oopFinalGrade = new JTextField[10];

    // INTEG fields
    private JTextField[] integID         = new JTextField[10];
    private JTextField[] integName       = new JTextField[10];
    private JTextField[] integQuiz       = new JTextField[10];
    private JTextField[] integProject    = new JTextField[10];
    private JTextField[] integExam       = new JTextField[10];
    private JTextField[] integFinalGrade = new JTextField[10];

    // CP fields
    private JTextField[] cpID         = new JTextField[10];
    private JTextField[] cpName       = new JTextField[10];
    private JTextField[] cpQuiz       = new JTextField[10];
    private JTextField[] cpProject    = new JTextField[10];
    private JTextField[] cpExam       = new JTextField[10];
    private JTextField[] cpFinalGrade = new JTextField[10];

    // NET fields
    private JTextField[] netID         = new JTextField[10];
    private JTextField[] netName       = new JTextField[10];
    private JTextField[] netQuiz       = new JTextField[10];
    private JTextField[] netProject    = new JTextField[10];
    private JTextField[] netExam       = new JTextField[10];
    private JTextField[] netFinalGrade = new JTextField[10];

    // OS fields
    private JTextField[] osID         = new JTextField[10];
    private JTextField[] osName       = new JTextField[10];
    private JTextField[] osQuiz       = new JTextField[10];
    private JTextField[] osProject    = new JTextField[10];
    private JTextField[] osExam       = new JTextField[10];
    private JTextField[] osFinalGrade = new JTextField[10];

    // Layout constants
    // Frame 1000x720, title bar ~30px => usable content pane ~690px
    // pnlMain: x=185, y=0, w=815, h=560
    // Bottom bar: y=565, h=36 => bottom edge 601 — well within 690
    // Sidebar: h=690
    private static final int PANEL_H  = 560;
    private static final int BOTTOM_Y = 565;

    public StudentGrades() {

        setTitle("Student Grades Viewer");
        setSize(1000, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background
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
        JPanel pnlSideBar = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(255, 255, 255, 18));
                g2.fillOval(-40, 400, 280, 280);
                g2.fillOval(10, -70, 210, 210);
            }
        };
        pnlSideBar.setBounds(0, 0, 185, 690);
        pnlSideBar.setOpaque(false);
        background.add(pnlSideBar);

        // Sidebar logo
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

        // Sidebar subject buttons
        btnOOP   = createSideButton("OOP",           122);
        btnInteg = createSideButton("Integrative",   168);
        btnCP    = createSideButton("Programming",   214);
        btnNet   = createSideButton("Network",       260);
        btnOS    = createSideButton("Operating Sys", 306);

        pnlSideBar.add(btnOOP);
        pnlSideBar.add(btnInteg);
        pnlSideBar.add(btnCP);
        pnlSideBar.add(btnNet);
        pnlSideBar.add(btnOS);

        // Sidebar footer
        JSeparator sideFootDiv = new JSeparator();
        sideFootDiv.setBounds(20, 608, 145, 1);
        sideFootDiv.setForeground(new Color(255, 255, 255, 40));
        pnlSideBar.add(sideFootDiv);

        JLabel lblFooter = new JLabel("© 2025 PUP", SwingConstants.CENTER);
        lblFooter.setBounds(10, 616, 165, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblFooter);

        // Card panel
        cardLayout = new CardLayout();
        pnlMain = new JPanel(cardLayout);
        pnlMain.setBounds(185, 0, 815, PANEL_H);
        pnlMain.setOpaque(false);
        background.add(pnlMain);

        pnlMain.add(buildSubjectPanel("OOP Grades",                    oopID,   oopName,   oopQuiz,   oopExam,   oopProject,   oopFinalGrade),   "OOP");
        pnlMain.add(buildSubjectPanel("Integrative Grades",            integID, integName, integQuiz, integExam, integProject, integFinalGrade), "INTEG");
        pnlMain.add(buildSubjectPanel("Computer Programming Grades",   cpID,    cpName,    cpQuiz,    cpExam,    cpProject,    cpFinalGrade),    "CP");
        pnlMain.add(buildSubjectPanel("Network Administration Grades", netID,   netName,   netQuiz,   netExam,   netProject,   netFinalGrade),   "NET");
        pnlMain.add(buildSubjectPanel("Operating System Grades",       osID,    osName,    osQuiz,    osExam,    osProject,    osFinalGrade),    "OS");

        // Bottom bar: search + back
        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(200, BOTTOM_Y + 3, 60, 30);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 12));
        lblSearch.setForeground(Color.decode("#C0000B"));
        background.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(262, BOTTOM_Y, 200, 36);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 12));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#C0000B"), 1),
            BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {}
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (txtSearch.getText().trim().isEmpty()) searchAll();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });
        background.add(txtSearch);

        btnSearch = new JButton("Search");
        styleActionButton(btnSearch, true);
        btnSearch.setBounds(472, BOTTOM_Y, 110, 36);
        background.add(btnSearch);

        btnBack = new JButton("← Back");
        styleActionButton(btnBack, false);
        btnBack.setBounds(760, BOTTOM_Y, 120, 36);
        background.add(btnBack);

        // Listeners
        btnOOP.addActionListener(this);
        btnInteg.addActionListener(this);
        btnCP.addActionListener(this);
        btnNet.addActionListener(this);
        btnOS.addActionListener(this);
        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);

        cardLayout.show(pnlMain, "OOP");
        setVisible(true);
        searchAll();
    }

    // Subject panel builder
    private JPanel buildSubjectPanel(String titleText,
                                     JTextField[] idArr,   JTextField[] nameArr,
                                     JTextField[] quizArr, JTextField[] examArr,
                                     JTextField[] projArr, JTextField[] finalArr) {
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
        String[] colLabels = {"Student ID", "Name",  "Quiz", "Exam", "Project", "Final Grade"};
        int[]    colX      = { 15,            110,     230,    318,    406,        494 };
        int[]    colW      = { 90,             110,     83,     83,     83,         90  };

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

        // Data rows — all read-only
        // 10 rows x 44px = 440px; start y=95; last row bottom = 95 + 9*44 + 28 = 519 -> fits in PANEL_H=560
        for (int i = 0; i < 10; i++) {
            int y = 95 + (i * 44);
            Color rowBg = (i % 2 == 0) ? Color.WHITE : new Color(255, 247, 247);
            idArr[i]    = addReadOnlyField(panel, colX[0], y, colW[0], rowBg);
            nameArr[i]  = addReadOnlyField(panel, colX[1], y, colW[1], rowBg);
            quizArr[i]  = addReadOnlyField(panel, colX[2], y, colW[2], rowBg);
            examArr[i]  = addReadOnlyField(panel, colX[3], y, colW[3], rowBg);
            projArr[i]  = addReadOnlyField(panel, colX[4], y, colW[4], rowBg);
            finalArr[i] = addReadOnlyField(panel, colX[5], y, colW[5], new Color(245, 245, 245));
        }

        // Footer separator: below last row (95 + 9*44 + 28 = 519), place at 524
        JSeparator footerLine = new JSeparator();
        footerLine.setBounds(10, 524, 790, 1);
        footerLine.setForeground(new Color(192, 0, 11, 80));
        panel.add(footerLine);

        return panel;
    }

    // Read-only field
    private JTextField addReadOnlyField(JPanel panel, int x, int y, int w, Color bg) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, 28);
        tf.setFont(new Font("Arial", Font.PLAIN, 11));
        tf.setBackground(bg);
        tf.setForeground(Color.decode("#1a1a1a"));
        tf.setEditable(false);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 200, 200), 1),
            BorderFactory.createEmptyBorder(2, 4, 2, 4)
        ));
        panel.add(tf);
        return tf;
    }

    // Sidebar button
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

    // Action button style
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
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (isPrimary) {
                    btn.setBackground(Color.decode("#C0000B"));
                } else {
                    btn.setBackground(Color.WHITE);
                    btn.setForeground(Color.decode("#C0000B"));
                }
            }
        });
    }

    // Load all tables
    private void searchAll() {
        loadTable("oopgrades",   oopID,   oopName,   oopQuiz,   oopProject,   oopExam,   oopFinalGrade);
        loadTable("integgrades", integID, integName, integQuiz, integProject, integExam, integFinalGrade);
        loadTable("cpgrades",    cpID,    cpName,    cpQuiz,    cpProject,    cpExam,    cpFinalGrade);
        loadTable("netgrades",   netID,   netName,   netQuiz,   netProject,   netExam,   netFinalGrade);
        loadTable("osgrades",    osID,    osName,    osQuiz,    osProject,    osExam,    osFinalGrade);
    }

    // Table loader
    private void loadTable(String table,
                           JTextField[] id,      JTextField[] name,
                           JTextField[] quiz,    JTextField[] project,
                           JTextField[] exam,    JTextField[] finalGrade) {
        String key = txtSearch.getText().trim();

        for (int i = 0; i < 10; i++) {
            id[i].setText(""); name[i].setText("");
            quiz[i].setText(""); project[i].setText("");
            exam[i].setText(""); finalGrade[i].setText("");
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "")) {

            PreparedStatement pst;
            if (key.isEmpty()) {
                pst = conn.prepareStatement("SELECT * FROM " + table + " LIMIT 10");
            } else {
                pst = conn.prepareStatement(
                    "SELECT * FROM " + table + " WHERE student_id=? OR name=?");
                pst.setString(1, key);
                pst.setString(2, key);
            }

            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next() && i < 10) {
                id[i].setText(rs.getString("student_id"));
                name[i].setText(rs.getString("name"));
                quiz[i].setText(rs.getString("quiz"));
                project[i].setText(rs.getString("project"));
                exam[i].setText(rs.getString("exam"));
                finalGrade[i].setText(rs.getString("final_grade"));
                i++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading: " + table + "\n\n" + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if      (e.getSource() == btnSearch) { searchAll(); }
        else if (e.getSource() == btnOOP)    { currentTab = "OOP";   cardLayout.show(pnlMain, "OOP"); }
        else if (e.getSource() == btnInteg)  { currentTab = "INTEG"; cardLayout.show(pnlMain, "INTEG"); }
        else if (e.getSource() == btnCP)     { currentTab = "CP";    cardLayout.show(pnlMain, "CP"); }
        else if (e.getSource() == btnNet)    { currentTab = "NET";   cardLayout.show(pnlMain, "NET"); }
        else if (e.getSource() == btnOS)     { currentTab = "OS";    cardLayout.show(pnlMain, "OS"); }
        else if (e.getSource() == btnBack)   {
            new StudentDashB().setVisible(true);
            dispose();
        }
    }
}