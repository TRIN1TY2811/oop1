package schoolsystem3;
import schoolsystem3.TeachersFunctions.TeacherDashB;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

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

        pnlPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        pnlPanel.setBounds(320, 0, 480, 750);
        pnlPanel.setOpaque(false);
        background.add(pnlPanel);

        lblTitle = new JLabel("Teacher Login", SwingConstants.CENTER);
        lblTitle.setBounds(60, 160, 360, 45);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        pnlPanel.add(lblTitle);

        JLabel lblSub2 = new JLabel("Sign in to your account", SwingConstants.CENTER);
        lblSub2.setBounds(60, 203, 360, 25);
        lblSub2.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSub2.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblSub2);

        JSeparator sep = new JSeparator();
        sep.setBounds(100, 238, 260, 2);
        sep.setForeground(new Color(255, 255, 255, 80));
        pnlPanel.add(sep);

        lblUser = new JLabel("Teacher ID");
        lblUser.setBounds(115, 258, 220, 22);
        lblUser.setFont(new Font("Arial", Font.BOLD, 12));
        lblUser.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(115, 280, 220, 36);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 13));
        txtUser.setForeground(Color.decode("#1a1a1a"));
        txtUser.setBackground(Color.WHITE);
        txtUser.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        pnlPanel.add(txtUser);

        lblPass = new JLabel("Password");
        lblPass.setBounds(115, 330, 220, 22);
        lblPass.setFont(new Font("Arial", Font.BOLD, 12));
        lblPass.setForeground(new Color(255, 210, 210));
        pnlPanel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(115, 352, 220, 36);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 13));
        txtPass.setForeground(Color.decode("#1a1a1a"));
        txtPass.setBackground(Color.WHITE);
        txtPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        pnlPanel.add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(115, 415, 220, 44);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setForeground(Color.decode("#C0000B"));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlPanel.add(btnLogin);

        btnRegister = new JButton("Forgot Password");
        btnRegister.setBounds(115, 470, 220, 44);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 15));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(Color.decode("#C0000B"));
        btnRegister.setOpaque(true);
        btnRegister.setBorderPainted(true);
        btnRegister.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlPanel.add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(115, 525, 220, 44);
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

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);

        addHoverEffect(btnLogin);
        addHoverEffect(btnRegister);
        addHoverEffect(btnBack);
    }

    public void addHoverEffect(JButton button) {
        boolean isFilled = button.getText().equals("Login");
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
            return rs.next();

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

        if (e.getSource() == btnLogin) {
            String username = txtUser.getText().trim();
            String password = String.valueOf(txtPass.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter your credentials to proceed",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

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
        } else if (e.getSource() == btnRegister) {
            dispose();
            TeacherForgotPassword teachforg = new TeacherForgotPassword();
            teachforg.setVisible(true);
        } else if (e.getSource() == btnBack) {
            dispose();
            Homepage hp = new Homepage();
            hp.setVisible(true);
        }
    }
}