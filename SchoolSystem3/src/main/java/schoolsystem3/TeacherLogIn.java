package schoolsystem3;
import schoolsystem3.TeachersFunctions.TeacherDashB;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import schoolsystem3.Homepage;

public class TeacherLogIn extends JFrame implements ActionListener {

    private JButton btnLogin, btnRegister, btnBack;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JLabel lblTitle, lblUser, lblPass;
    private JPanel pnlPanel;
   
    public TeacherLogIn() {

        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // ================= BACKGROUND =================
        ImageIcon img = new ImageIcon("C:\\Users\\admin\\Downloads\\pup1.jpg");
        Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setLayout(null);
        setContentPane(background);

        // ================= PANEL =================
        pnlPanel = new JPanel();
        pnlPanel.setLayout(null);
        pnlPanel.setBounds(250, 120, 300, 450);
        pnlPanel.setBackground(new Color(255, 255, 255, 180));
        background.add(pnlPanel);

        // ================= TITLE =================
        lblTitle = new JLabel("Teacher Login", SwingConstants.CENTER);
        lblTitle.setBounds(40, 40, 220, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.decode("#312E81"));
        pnlPanel.add(lblTitle);

        // ================= USERNAME =================
        lblUser = new JLabel("Teacher ID");
        lblUser.setBounds(40, 110, 220, 20);
        lblUser.setForeground(Color.decode("#312E81"));
        pnlPanel.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(40, 135, 220, 35);
        pnlPanel.add(txtUser);

        // ================= PASSWORD =================
        lblPass = new JLabel("Password");
        lblPass.setBounds(40, 185, 220, 20);
        lblPass.setForeground(Color.decode("#312E81"));
        pnlPanel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(40, 210, 220, 35);
        pnlPanel.add(txtPass);

        // ================= LOGIN BUTTON =================
        btnLogin = new JButton("Login");
        btnLogin.setBounds(40, 280, 220, 40);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        pnlPanel.add(btnLogin);

        // ================= FORGOT PASSWORD BUTTON =================
        btnRegister = new JButton("Forgot Password");
        btnRegister.setBounds(40, 330, 220, 40);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
        pnlPanel.add(btnRegister);

        // ================= BACK BUTTON =================
        btnBack = new JButton("Back");
        btnBack.setBounds(40, 380, 220, 40);
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        pnlPanel.add(btnBack);

        // ================= ACTION LISTENERS =================
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);

        btnLogin.setForeground(Color.decode("#1E1B4B"));
        btnLogin.setBackground(Color.decode("#BEE9FF"));
        btnRegister.setForeground(Color.decode("#1E1B4B"));
        btnRegister.setBackground(Color.decode("#BEE9FF"));
        btnBack.setForeground(Color.decode("#1E1B4B"));
        btnBack.setBackground(Color.decode("#BEE9FF"));

        addHoverEffect(btnLogin);
        addHoverEffect(btnRegister);
        addHoverEffect(btnBack);
    }
    
    // ================= HOVER EFFECT =================
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

    // ================= DATABASE LOGIN CHECK =================
    private boolean checkLogin(String username, String password) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            PreparedStatement pst = conn.prepareStatement(
                "SELECT * FROM users WHERE Username = ? AND Password = ?"
            );

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            return rs.next(); // returns true if a matching record is found

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

    // ================= ACTION PERFORMED =================
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogin) {

            String username = txtUser.getText().trim();
            String password = String.valueOf(txtPass.getPassword()).trim();

            // ===== EMPTY CHECK =====
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter your credentials to proceed",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // ===== DATABASE CHECK =====
            if (checkLogin(username, password)) {
                dispose();
                TeacherDashB tdb = new TeacherDashB();
                tdb.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // ================= FORGOT PASSWORD =================
        else if (e.getSource() == btnRegister) {
            dispose();
            TeacherForgotPassword teachforg = new TeacherForgotPassword();
            teachforg.setVisible(true);
        }

        // ================= BACK =================
        else if (e.getSource() == btnBack) {
            dispose();
            Homepage hp = new Homepage();
            hp.setVisible(true);
        }
    }
}