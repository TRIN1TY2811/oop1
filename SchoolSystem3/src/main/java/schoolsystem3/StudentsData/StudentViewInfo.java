package schoolsystem3.StudentsData;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StudentViewInfo extends JFrame implements ActionListener {

    private JTextField txtSearch;
    private JButton btnSearch, btnBack;

    private JTextField[] infoID       = new JTextField[10];
    private JTextField[] infoName     = new JTextField[10];
    private JTextField[] infoAge      = new JTextField[10];
    private JTextField[] infoGender   = new JTextField[10];
    private JTextField[] infoBirthday = new JTextField[10];

    public StudentViewInfo() {

        setTitle("Student Information");
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
                g2.fillOval(-40, 480, 280, 280);
                g2.fillOval(10, -70, 210, 210);
            }
        };
        pnlSideBar.setBounds(0, 0, 185, 720);
        pnlSideBar.setOpaque(false);
        background.add(pnlSideBar);

        // Logo
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

        // Section label
        JLabel lblSection = new JLabel("STUDENT", SwingConstants.CENTER);
        lblSection.setBounds(10, 90, 165, 24);
        lblSection.setFont(new Font("Arial", Font.BOLD, 10));
        lblSection.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblSection);

        // Active nav item
        JLabel lblNavItem = new JLabel("Student Info", SwingConstants.CENTER);
        lblNavItem.setBounds(18, 122, 150, 36);
        lblNavItem.setFont(new Font("Arial", Font.BOLD, 12));
        lblNavItem.setForeground(Color.WHITE);
        lblNavItem.setOpaque(true);
        lblNavItem.setBackground(new Color(160, 0, 9));
        lblNavItem.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        pnlSideBar.add(lblNavItem);

        // Footer
        JSeparator sideFootDiv = new JSeparator();
        sideFootDiv.setBounds(20, 660, 145, 1);
        sideFootDiv.setForeground(new Color(255, 255, 255, 40));
        pnlSideBar.add(sideFootDiv);

        JLabel lblFooter = new JLabel("© 2025 PUP", SwingConstants.CENTER);
        lblFooter.setBounds(10, 668, 165, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblFooter);

        // Main panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBounds(205, 20, 770, 670);
        mainPanel.setOpaque(false);
        background.add(mainPanel);

        // Header bar
        JPanel headerBar = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerBar.setBounds(0, 0, 770, 55);
        headerBar.setOpaque(false);
        mainPanel.add(headerBar);

        JLabel title = new JLabel("Student Information", SwingConstants.CENTER);
        title.setBounds(0, 0, 770, 55);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        headerBar.add(title);

        // Search bar
        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(0, 74, 60, 30);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 12));
        lblSearch.setForeground(Color.decode("#1a1a1a"));
        mainPanel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(62, 74, 220, 30);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 12));
        txtSearch.setForeground(Color.decode("#1a1a1a"));
        txtSearch.setBackground(Color.WHITE);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 200, 200), 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {}
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (txtSearch.getText().trim().isEmpty()) loadTable();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });
        mainPanel.add(txtSearch);

        btnSearch = new JButton("Search");
        styleButton(btnSearch, true);
        btnSearch.setBounds(292, 74, 110, 30);
        btnSearch.addActionListener(this);
        mainPanel.add(btnSearch);

        // Hint label
        JLabel lblHint = new JLabel("Search by ID or Name");
        lblHint.setBounds(412, 74, 200, 30);
        lblHint.setFont(new Font("Arial", Font.ITALIC, 11));
        lblHint.setForeground(new Color(180, 180, 180));
        mainPanel.add(lblHint);

        JSeparator searchDiv = new JSeparator();
        searchDiv.setBounds(0, 114, 770, 1);
        searchDiv.setForeground(new Color(192, 0, 11, 40));
        mainPanel.add(searchDiv);

        // Column headers
        String[] headers = { "Student ID", "Name", "Age", "Gender", "Birthday" };
        int[]    colX    = {  10,           115,    320,    410,      510       };
        int[]    colW    = {  95,           195,     80,     90,      220       };

        JPanel colHeaderBg = new JPanel(null);
        colHeaderBg.setBounds(0, 120, 770, 30);
        colHeaderBg.setBackground(new Color(255, 240, 240));
        mainPanel.add(colHeaderBg);

        for (int i = 0; i < headers.length; i++) {
            JLabel h = new JLabel(headers[i], SwingConstants.CENTER);
            h.setBounds(colX[i], 120, colW[i], 30);
            h.setFont(new Font("Arial", Font.BOLD, 11));
            h.setForeground(Color.decode("#C0000B"));
            mainPanel.add(h);
        }

        JSeparator headerLine = new JSeparator();
        headerLine.setBounds(0, 151, 770, 1);
        headerLine.setForeground(new Color(192, 0, 11, 80));
        mainPanel.add(headerLine);

        // Data rows
        for (int i = 0; i < 10; i++) {
            int y = 157 + (i * 34);
            Color rowBg = (i % 2 == 0) ? Color.WHITE : new Color(255, 247, 247);
            infoID[i]       = addStyledField(mainPanel, colX[0], y, colW[0], rowBg);
            infoName[i]     = addStyledField(mainPanel, colX[1], y, colW[1], rowBg);
            infoAge[i]      = addStyledField(mainPanel, colX[2], y, colW[2], rowBg);
            infoGender[i]   = addStyledField(mainPanel, colX[3], y, colW[3], rowBg);
            infoBirthday[i] = addStyledField(mainPanel, colX[4], y, colW[4], rowBg);
        }

        JSeparator footerLine = new JSeparator();
        footerLine.setBounds(0, 502, 770, 1);
        footerLine.setForeground(new Color(192, 0, 11, 60));
        mainPanel.add(footerLine);

        // Back button
        btnBack = new JButton("← Back");
        styleButton(btnBack, false);
        btnBack.setBounds(640, 516, 120, 36);
        btnBack.addActionListener(this);
        mainPanel.add(btnBack);

        loadTable();
    }

    // Field helper
    private JTextField addStyledField(JPanel panel, int x, int y, int w, Color bg) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, 28);
        tf.setFont(new Font("Arial", Font.PLAIN, 11));
        tf.setBackground(bg);
        tf.setForeground(Color.decode("#1a1a1a"));
        tf.setEditable(false);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 200, 200), 1),
            BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        panel.add(tf);
        return tf;
    }

    // Button style
    private void styleButton(JButton btn, boolean isPrimary) {
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

    // Load table
    private void loadTable() {

        String key = txtSearch.getText().trim();

        for (int i = 0; i < 10; i++) {
            infoID[i].setText("");
            infoName[i].setText("");
            infoAge[i].setText("");
            infoGender[i].setText("");
            infoBirthday[i].setText("");
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "")) {

            PreparedStatement pst;

            if (key.isEmpty()) {
                pst = conn.prepareStatement("SELECT * FROM studentinfo");
            } else {
                pst = conn.prepareStatement(
                    "SELECT * FROM studentinfo WHERE student_id = ? OR name = ?");
                pst.setString(1, key);
                pst.setString(2, key);
            }

            ResultSet rs = pst.executeQuery();

            int i = 0;
            while (rs.next() && i < 10) {
                infoID[i].setText(rs.getString("student_id"));
                infoName[i].setText(rs.getString("name"));
                infoAge[i].setText(rs.getString("age"));
                infoGender[i].setText(rs.getString("gender"));
                infoBirthday[i].setText(rs.getString("birthday"));
                i++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading student info:\n\n" + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            loadTable();
        } else if (e.getSource() == btnBack) {
            new StudentDashB().setVisible(true);
            dispose();
        }
    }
}