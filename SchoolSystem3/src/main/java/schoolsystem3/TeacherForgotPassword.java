package schoolsystem3;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TeacherForgotPassword extends JFrame implements ActionListener {

    private JTextField txtUsername;
    private JPasswordField txtNewPass, txtConfirmPass;
    private JButton btnReset, btnBack;
    private JLabel lblTitle;
    private JPanel pnlPanel;

    public TeacherForgotPassword() {

        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel background = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(320, 0, 480, 750);

                int[] xPts = {300, 340, 340, 240};
                int[] yPts = {0,   0,   750, 750};
                g2.setColor(Color.decode("#A80009"));
                g2.fillPolygon(xPts, yPts, 4);

                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillOval(420, 500, 400, 400);
                g2.fillOval(500, -80, 300, 300);
            }
        };
        background.setLayout(null);
        setContentPane(background);

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

        pnlPanel = new JPanel(null);
        pnlPanel.setBounds(320, 0, 480, 750);
        pnlPanel.setOpaque(false);
        background.add(pnlPanel);

        lblTitle = new JLabel("Forgot Password", SwingConstants.CENTER);
        lblTitle.setBounds(60, 140, 360, 45);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        pnlPanel.add(lblTitle);

        JLabel lblSub2 = new JLabel("Reset your account password", SwingConstants.CENTER);
        lblSub2.setBounds(60, 183, 360, 25);
        lblSub2.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSub2.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblSub2);

        JSeparator sep = new JSeparator();
        sep.setBounds(100, 218, 260, 2);
        sep.setForeground(new Color(255, 255, 255, 80));
        pnlPanel.add(sep);

        JLabel lblUsernameField = new JLabel("Username");
        lblUsernameField.setBounds(115, 235, 220, 22);
        lblUsernameField.setFont(new Font("Arial", Font.BOLD, 12));
        lblUsernameField.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblUsernameField);

        txtUsername = new JTextField();
        txtUsername.setBounds(115, 257, 220, 36);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 13));
        txtUsername.setForeground(Color.decode("#1a1a1a"));
        txtUsername.setBackground(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        pnlPanel.add(txtUsername);

        JLabel lblNewPass = new JLabel("New Password");
        lblNewPass.setBounds(115, 305, 220, 22);
        lblNewPass.setFont(new Font("Arial", Font.BOLD, 12));
        lblNewPass.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblNewPass);

        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(115, 327, 220, 36);
        txtNewPass.setFont(new Font("Arial", Font.PLAIN, 13));
        txtNewPass.setForeground(Color.decode("#1a1a1a"));
        txtNewPass.setBackground(Color.WHITE);
        txtNewPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        pnlPanel.add(txtNewPass);

        JLabel lblConfirmPass = new JLabel("Confirm Password");
        lblConfirmPass.setBounds(115, 375, 220, 22);
        lblConfirmPass.setFont(new Font("Arial", Font.BOLD, 12));
        lblConfirmPass.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblConfirmPass);

        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(115, 397, 220, 36);
        txtConfirmPass.setFont(new Font("Arial", Font.PLAIN, 13));
        txtConfirmPass.setForeground(Color.decode("#1a1a1a"));
        txtConfirmPass.setBackground(Color.WHITE);
        txtConfirmPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        pnlPanel.add(txtConfirmPass);

        btnReset = new JButton("Reset Password");
        btnReset.setBounds(115, 460, 220, 44);
        btnReset.setFont(new Font("Arial", Font.BOLD, 15));
        btnReset.setForeground(Color.decode("#C0000B"));
        btnReset.setBackground(Color.WHITE);
        btnReset.setOpaque(true);
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlPanel.add(btnReset);

        btnBack = new JButton("Back");
        btnBack.setBounds(115, 515, 220, 44);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.decode("#C0000B"));
        btnBack.setOpaque(true);
        btnBack.setBorderPainted(true);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlPanel.add(btnBack);

        JLabel lblFooter = new JLabel("© 2025 PUP School System", SwingConstants.CENTER);
        lblFooter.setBounds(60, 700, 360, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 200, 200));
        pnlPanel.add(lblFooter);

        btnReset.addActionListener(this);
        btnBack.addActionListener(this);

        addHoverEffect(btnReset);
        addHoverEffect(btnBack);
    }

    public void addHoverEffect(JButton button) {
        boolean isFilled = button.getText().equals("Reset Password");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Arial", Font.BOLD, 16));
                if (isFilled) {
                    button.setForeground(Color.decode("#A80009"));
                    button.setBackground(new Color(240, 240, 240));
                } else {
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

    private boolean resetPassword(String username, String newPassword) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            PreparedStatement checkPst = conn.prepareStatement(
                "SELECT * FROM users WHERE Username = ?"
            );
            checkPst.setString(1, username);
            ResultSet rs = checkPst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Username not found!",
                    "Not Found",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }

            PreparedStatement updatePst = conn.prepareStatement(
                "UPDATE users SET Password = ? WHERE Username = ?"
            );
            updatePst.setString(1, newPassword);
            updatePst.setString(2, username);

            int rows = updatePst.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Database connection failed: " + ex.getMessage(),
                "Connection Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnReset) {
            String username = txtUsername.getText().trim();
            String pass1    = new String(txtNewPass.getPassword()).trim();
            String pass2    = new String(txtConfirmPass.getPassword()).trim();

            if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (resetPassword(username, pass1)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Password Reset Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
                TeacherLogIn teachLog = new TeacherLogIn();
                teachLog.setVisible(true);
            }

        } else if (e.getSource() == btnBack) {
            dispose();
            TeacherLogIn teachLog = new TeacherLogIn();
            teachLog.setVisible(true);
        }
    }
}