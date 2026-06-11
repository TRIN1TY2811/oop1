package schoolsystem3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import schoolsystem3.StudentsData.StudentDashB;

public class Homepage extends JFrame implements ActionListener {
    private JButton btnStud, btnTch;
    private JLabel lblHome, lblHome1, lblHome2;
    private JPanel pnlPanel;

    public Homepage() {

        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // BACKGROUND 
        JPanel background = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // White left side
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Red right side
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(320, 0, 480, 750);

                // Diagonal trim between panels
                int[] xPts = {300, 340, 340, 240};
                int[] yPts = {0,   0,   750, 750};
                g2.setColor(Color.decode("#A80009"));
                g2.fillPolygon(xPts, yPts, 4);

                // Subtle white circles on red side
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillOval(420, 500, 400, 400);
                g2.fillOval(500, -80, 300, 300);
            }
        };
        background.setLayout(null);
        setContentPane(background);

     
        ImageIcon rawLogo = new ImageIcon("C:\\Users\\amodi\\OneDrive\\Documents\\NetBeansProjects\\SchoolSystem\\StudentSystem\\oop1\\SchoolSystem3\\LogoPUP.png");
        Image scaledLogo = rawLogo.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaledLogo));
        lblLogo.setBounds(90, 120, 140, 140);
        background.add(lblLogo);

      
        JLabel lblSchool = new JLabel("Polytechnic University", SwingConstants.CENTER);
        lblSchool.setBounds(10, 275, 300, 35);
        lblSchool.setFont(new Font("Arial", Font.BOLD, 20));
        lblSchool.setForeground(Color.decode("#C0000B"));
        background.add(lblSchool);

        JLabel lblSchool2 = new JLabel("of the Philippines", SwingConstants.CENTER);
        lblSchool2.setBounds(10, 308, 300, 35);
        lblSchool2.setFont(new Font("Arial", Font.BOLD, 20));
        lblSchool2.setForeground(Color.decode("#1a1a1a"));
        background.add(lblSchool2);

        JLabel lblDivider = new JLabel("─────────────────", SwingConstants.CENTER);
        lblDivider.setBounds(10, 343, 300, 25);
        lblDivider.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDivider.setForeground(new Color(192, 0, 11, 100));
        background.add(lblDivider);

        JLabel lblSub = new JLabel("Student Information System", SwingConstants.CENTER);
        lblSub.setBounds(10, 366, 300, 25);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSub.setForeground(Color.decode("#888888"));
        background.add(lblSub);

       
        pnlPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(0, 0, 0, 0));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        pnlPanel.setBounds(320, 0, 480, 750);
        pnlPanel.setOpaque(false);
        background.add(pnlPanel);

        
        lblHome1 = new JLabel("Hi, PUPian!", SwingConstants.CENTER);
        lblHome1.setBounds(60, 200, 360, 40);
        lblHome1.setFont(new Font("Arial", Font.BOLD, 28));
        lblHome1.setForeground(Color.WHITE);
        pnlPanel.add(lblHome1);

        lblHome2 = new JLabel("Welcome Back", SwingConstants.CENTER);
        lblHome2.setBounds(60, 238, 360, 40);
        lblHome2.setFont(new Font("Arial", Font.BOLD, 28));
        lblHome2.setForeground(Color.WHITE);
        pnlPanel.add(lblHome2);

        lblHome = new JLabel("Access your academic portal", SwingConstants.CENTER);
        lblHome.setBounds(60, 278, 360, 28);
        lblHome.setFont(new Font("Arial", Font.PLAIN, 13));
        lblHome.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblHome);

       
        JSeparator sep = new JSeparator();
        sep.setBounds(100, 318, 260, 2);
        sep.setForeground(new Color(255, 255, 255, 80));
        pnlPanel.add(sep);

      
        btnStud = new JButton("Student");
        btnStud.setBounds(115, 360, 210, 44);
        btnStud.setFont(new Font("Arial", Font.BOLD, 15));
        btnStud.setForeground(Color.decode("#C0000B"));
        btnStud.setBackground(Color.WHITE);
        btnStud.setOpaque(true);
        btnStud.setBorderPainted(false);
        btnStud.setFocusPainted(false);
        btnStud.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPanel.add(btnStud);

       
        btnTch = new JButton("Teacher");
        btnTch.setBounds(115, 420, 210, 44);
        btnTch.setFont(new Font("Arial", Font.BOLD, 15));
        btnTch.setForeground(Color.WHITE);
        btnTch.setBackground(Color.decode("#C0000B"));
        btnTch.setOpaque(true);
        btnTch.setBorderPainted(true);
        btnTch.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnTch.setFocusPainted(false);
        btnTch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPanel.add(btnTch);

       
        JLabel lblFooter = new JLabel("© 2025 PUP School System", SwingConstants.CENTER);
        lblFooter.setBounds(60, 700, 360, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 200, 200));
        pnlPanel.add(lblFooter);

        addHoverEffect(btnTch);
        addHoverEffect(btnStud);

        btnStud.addActionListener(this);
        btnTch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == btnStud) {
            StudentDashB sc = new StudentDashB();
            sc.setVisible(true);
        } else if (e.getSource() == btnTch) {
            TeacherLogIn tch = new TeacherLogIn();
            tch.setVisible(true);
        }
    }

    public void addHoverEffect(JButton button) {
        boolean isFilled = button.getText().equals("Student");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Arial", Font.BOLD, 16));
                if (isFilled) {
                    // Student: white bg -> light gray on hover
                    button.setForeground(Color.decode("#A80009"));
                    button.setBackground(new Color(240, 240, 240));
                } else {
                    // Teacher: outline -> solid white fill on hover
                    button.setForeground(Color.decode("#C0000B"));
                    button.setBackground(Color.WHITE);
                    button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Arial", Font.BOLD, 15));
                if (isFilled) {
                    button.setForeground(Color.decode("#C0000B"));
                    button.setBackground(Color.WHITE);
                } else {
                    button.setForeground(Color.WHITE);
                    button.setBackground(Color.decode("#C0000B"));
                    button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                }
            }
        });
    }

    public void AddHoverEffectsLBL(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.decode("#3B0CA3"));
                label.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.decode("#0d07ba"));
            }
        });
    }
}