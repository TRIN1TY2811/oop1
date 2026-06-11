package schoolsystem3.StudentsData;

import schoolsystem3.Homepage;
import schoolsystem3.StudentInsideData.Grades.StudentGrades;
import schoolsystem3.StudentInsideData.Attendance.StudentAttendance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentDashB extends JFrame implements ActionListener {

    private JButton btnAttendance, btnGrades, btnStudentInfo, btnBack;

    public StudentDashB() {

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
        JLabel lblMenu = new JLabel("NAVIGATION", SwingConstants.CENTER);
        lblMenu.setBounds(10, 90, 165, 24);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 10));
        lblMenu.setForeground(new Color(255, 180, 180));
        pnlSideBar.add(lblMenu);

        // Sidebar nav buttons
        btnStudentInfo = createSideButton("Student Info", 122);
        btnGrades      = createSideButton("Grades",       168);
        btnAttendance  = createSideButton("Attendance",   214);

        pnlSideBar.add(btnStudentInfo);
        pnlSideBar.add(btnGrades);
        pnlSideBar.add(btnAttendance);

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

        // Main content
        JPanel pnlMain = new JPanel(null);
        pnlMain.setBounds(185, 0, 815, 720);
        pnlMain.setOpaque(false);
        background.add(pnlMain);

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
        headerBar.setBounds(0, 0, 815, 58);
        headerBar.setOpaque(false);
        pnlMain.add(headerBar);

        JLabel lblTitle = new JLabel("Student Dashboard", SwingConstants.CENTER);
        lblTitle.setBounds(0, 0, 815, 58);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        headerBar.add(lblTitle);

        // Welcome card
        JPanel cardWelcome = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(0, 0, getWidth(), 6);
            }
        };
        cardWelcome.setBounds(40, 90, 735, 110);
        cardWelcome.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 200), 1));
        pnlMain.add(cardWelcome);

        JLabel lblWelcome = new JLabel("Welcome, Student!");
        lblWelcome.setBounds(24, 22, 400, 30);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setForeground(Color.decode("#C0000B"));
        cardWelcome.add(lblWelcome);

        JLabel lblSub = new JLabel("View your student info, check your grades, and track your attendance.");
        lblSub.setBounds(24, 54, 680, 20);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(new Color(100, 100, 100));
        cardWelcome.add(lblSub);

        // Section divider
        JLabel lblSection = new JLabel("QUICK ACCESS");
        lblSection.setBounds(40, 222, 200, 20);
        lblSection.setFont(new Font("Arial", Font.BOLD, 11));
        lblSection.setForeground(new Color(150, 150, 150));
        pnlMain.add(lblSection);

        JSeparator secDiv = new JSeparator();
        secDiv.setBounds(40, 244, 735, 1);
        secDiv.setForeground(new Color(192, 0, 11, 40));
        pnlMain.add(secDiv);

        // Feature cards
        buildFeatureCard(pnlMain,  40, 258, "Student Info",
                "View your personal student record.", "\uD83D\uDC64");
        buildFeatureCard(pnlMain, 295, 258, "Grades",
                "Check your quiz, exam, and final grades.", "\uD83D\uDCCA");
        buildFeatureCard(pnlMain, 550, 258, "Attendance",
                "Track your weekly attendance record.", "\uD83D\uDDD3");

        // Back button
        btnBack = new JButton("← Back");
        styleButton(btnBack, false);
        btnBack.setBounds(655, 640, 120, 36);
        pnlMain.add(btnBack);

        // Action listeners
        btnStudentInfo.addActionListener(this);
        btnGrades.addActionListener(this);
        btnAttendance.addActionListener(this);
        btnBack.addActionListener(this);
    }

    // Feature card builder
    private void buildFeatureCard(JPanel parent, int x, int y,
                                   String title, String desc, String icon) {
        JPanel card = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
            }
        };
        card.setBounds(x, y, 230, 175);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 200), 1));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        parent.add(card);

        // Icon circle
        JPanel iconCircle = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(192, 0, 11, 20));
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        iconCircle.setBounds(85, 18, 60, 60);
        iconCircle.setOpaque(false);
        card.add(iconCircle);

        JLabel lblIcon = new JLabel(icon, SwingConstants.CENTER);
        lblIcon.setBounds(85, 18, 60, 60);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        card.add(lblIcon);

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setBounds(0, 90, 230, 24);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(Color.decode("#C0000B"));
        card.add(lblTitle);

        JLabel lblDesc = new JLabel("<html><center>" + desc + "</center></html>",
                SwingConstants.CENTER);
        lblDesc.setBounds(14, 116, 202, 36);
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 11));
        lblDesc.setForeground(new Color(120, 120, 120));
        card.add(lblDesc);

        // Hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(Color.decode("#C0000B"), 2));
                lblTitle.setForeground(Color.decode("#A80009"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 200), 1));
                lblTitle.setForeground(Color.decode("#C0000B"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                if (title.equals("Student Info")) {
                    new StudentViewInfo().setVisible(true);
                } else if (title.equals("Grades")) {
                    new StudentGrades().setVisible(true);
                } else if (title.equals("Attendance")) {
                    new StudentAttendance().setVisible(true);
                }
            }
        });
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

    // Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == btnStudentInfo) {
            new StudentViewInfo().setVisible(true);
        } else if (e.getSource() == btnGrades) {
            new StudentGrades().setVisible(true);
        } else if (e.getSource() == btnAttendance) {
            new StudentAttendance().setVisible(true);
        } else if (e.getSource() == btnBack) {
            new Homepage().setVisible(true);
        }
    }
}