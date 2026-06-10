package schoolsystem3;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import schoolsystem3.TeacherLogIn;

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

        // BACKGROUND
        ImageIcon img = new ImageIcon("C:\\Users\\admin\\Downloads\\pup1.jpg");
        Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setLayout(null);
        setContentPane(background);

        // PANEL
        pnlPanel = new JPanel();
        pnlPanel.setLayout(null);
        pnlPanel.setBounds(230, 150, 340, 420);
        pnlPanel.setBackground(new Color(255, 255, 255, 180));
        background.add(pnlPanel);

        // TITLE
        lblTitle = new JLabel("Forgot Password", SwingConstants.CENTER);
        lblTitle.setBounds(40, 20, 260, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.decode("#312E81"));
        pnlPanel.add(lblTitle);

        // USERNAME
        addLabel("Username", 80);
        txtUsername = addField(105);

        // NEW PASSWORD
        addLabel("New Password", 150);
        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(60, 175, 220, 30);
        pnlPanel.add(txtNewPass);

        // CONFIRM PASSWORD
        addLabel("Confirm Password", 220);
        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(60, 245, 220, 30);
        pnlPanel.add(txtConfirmPass);

        // RESET BUTTON
        btnReset = new JButton("Reset Password");
        btnReset.setBounds(60, 310, 220, 40);
        btnReset.setFont(new Font("Arial", Font.BOLD, 14));
        pnlPanel.add(btnReset);

        // BACK BUTTON
        btnBack = new JButton("Back");
        btnBack.setBounds(60, 360, 220, 35);
        pnlPanel.add(btnBack);

        btnReset.addActionListener(this);
        btnBack.addActionListener(this);

        btnReset.setForeground(Color.decode("#1E1B4B"));
        btnReset.setBackground(Color.decode("#BEE9FF"));
        btnBack.setForeground(Color.decode("#1E1B4B"));
        btnBack.setBackground(Color.decode("#BEE9FF"));

        addHoverEffect(btnReset);
        addHoverEffect(btnBack);
    }

    private void addLabel(String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(60, y, 220, 20);
        lbl.setForeground(Color.decode("#312E81"));
        pnlPanel.add(lbl);
    }

    private JTextField addField(int y) {
        JTextField tf = new JTextField();
        tf.setBounds(60, y, 220, 30);
        pnlPanel.add(tf);
        return tf;
    }

    //  DATABASE RESET 
    private boolean resetPassword(String username, String newPassword) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            // Check if Username exists
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

            // Update the password
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

            //  EMPTY CHECK 
            if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            //  PASSWORD MATCH CHECK 
            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            //  DATABASE UPDATE 
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
        }

        else if (e.getSource() == btnBack) {
            dispose();
            TeacherLogIn teachLog = new TeacherLogIn();
            teachLog.setVisible(true);
        }
    }

    public void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Arial", Font.BOLD, 21));
                button.setForeground(Color.decode("#FFFFFF"));
                button.setBackground(Color.decode("#312E81"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Arial", Font.BOLD, 18));
                button.setForeground(Color.decode("#1E1B4B"));
                button.setBackground(Color.decode("#BEE9FF"));
            }
        });
    }
}