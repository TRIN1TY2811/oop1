package schoolsystem3.TeachersFunctions;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import schoolsystem3.TeacherLogIn;
import schoolsystem3.TeachersFunctions.TeacherGrades;

public class TeacherDashB extends JFrame implements ActionListener {

    private JButton btnAttendance, btnGrades, btnStudentInfo, btnBack, btnAddStudent, btnSearchStudent, btnUpdateStudent, btnDeleteStudent;
    private JLabel lblTitle, lblPersonalTitle;
    private JPanel pnlPanel, pnlPersonal;

    public TeacherDashB() {

        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // BACKGROUND
        ImageIcon img = new ImageIcon("C:\\Users\\admin\\Downloads\\pup1.jpg");
        Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setLayout(null);
        setContentPane(background);

        // MAIN PANEL
        pnlPanel = new JPanel();
        pnlPanel.setLayout(null);
        pnlPanel.setBounds(120, 60, 560, 420);
        pnlPanel.setBackground(new Color(255, 255, 255, 180));
        background.add(pnlPanel);

        // TITLE
        lblTitle = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        lblTitle.setBounds(120, 20, 320, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.decode("#312E81"));
        pnlPanel.add(lblTitle);

        // GRADES BUTTON
        btnGrades = new JButton("Grades");
        btnGrades.setBounds(70, 100, 220, 40);
        btnGrades.setFont(new Font("Arial", Font.BOLD, 18));
        btnGrades.setForeground(Color.decode("#1E1B4B"));
        btnGrades.setBackground(Color.decode("#BEE9FF"));
        pnlPanel.add(btnGrades);

        // ATTENDANCE BUTTON
        btnAttendance = new JButton("Attendance");
        btnAttendance.setBounds(310, 100, 220, 40);
        btnAttendance.setFont(new Font("Arial", Font.BOLD, 18));
        btnAttendance.setForeground(Color.decode("#1E1B4B"));
        btnAttendance.setBackground(Color.decode("#BEE9FF"));
        pnlPanel.add(btnAttendance);

        // STUDENT INFO BUTTON
        btnStudentInfo = new JButton("Student Info");
        btnStudentInfo.setBounds(190, 170, 180, 40);
        btnStudentInfo.setFont(new Font("Arial", Font.BOLD, 18));
        btnStudentInfo.setForeground(Color.decode("#1E1B4B"));
        btnStudentInfo.setBackground(Color.decode("#BEE9FF"));
        pnlPanel.add(btnStudentInfo);

        // PERSONAL PANEL
        pnlPersonal = new JPanel();
        pnlPersonal.setLayout(null);
        pnlPersonal.setBounds(70, 285, 420, 100);
        pnlPersonal.setBackground(new Color(240, 240, 240, 180));
        pnlPanel.add(pnlPersonal);

        // BACK BUTTON
        btnBack = new JButton("Back");
        btnBack.setBounds(650, 620, 100, 30);
        btnBack.setForeground(Color.decode("#1E1B4B"));
        btnBack.setBackground(Color.decode("#BEE9FF"));
        background.add(btnBack);

        // ACTION LISTENERS
        btnGrades.addActionListener(this);
        btnAttendance.addActionListener(this);
        btnStudentInfo.addActionListener(this);
        btnBack.addActionListener(this);

        // HOVER EFFECTS
        addHoverEffect(btnGrades);
        addHoverEffect(btnAttendance);
        addHoverEffect(btnStudentInfo);
        addHoverEffect(btnBack);
    }
    private void addStudent() {

    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField age = new JTextField();
    JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female"});
    JTextField birthday = new JTextField();

    JPanel panel = new JPanel(new GridLayout(5,2));
    panel.add(new JLabel("ID")); panel.add(id);
    panel.add(new JLabel("Name")); panel.add(name);
    panel.add(new JLabel("Age")); panel.add(age);
    panel.add(new JLabel("Gender")); panel.add(gender);
    panel.add(new JLabel("Birthday")); panel.add(birthday);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Student",
            JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {

    try {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                ""
        );

        String studentId = id.getText().trim();
        String studentName = name.getText().trim();
        String studentAge = age.getText().trim();
        String studentGender = gender.getSelectedItem().toString();
        String studentBirthday = birthday.getText().trim();

        // INSERT STUDENT INFO
        String sql = "INSERT INTO studentinfo (student_id, name, age, gender, birthday) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, studentId);
        pst.setString(2, studentName);
        pst.setInt(3, Integer.parseInt(studentAge));
        pst.setString(4, studentGender);
        pst.setString(5, studentBirthday);

        pst.executeUpdate();

        // INSERT ATTENDANCE ROWS
        String[] tables = {
                "oopattendance",
                "integattendance",
                "cpattendance",
                "netattendance",
                "osattendance"
        };

        for (String table : tables) {

            String attendanceSql =
                    "INSERT INTO " + table +
                    " (ID, Week1, Week2, Week3, Week4, Week5) VALUES (?, '', '', '', '', '')";

            PreparedStatement attPst = conn.prepareStatement(attendanceSql);

            attPst.setString(1, studentId);  // ✅ FIXED HERE

            attPst.executeUpdate();
            attPst.close();
        }

        JOptionPane.showMessageDialog(this, "Student Added!");

        pst.close();
        conn.close();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}
    }
    private void searchStudent() {

    String id = JOptionPane.showInputDialog(this, "Enter Student ID:");

    if (id == null || id.trim().isEmpty()) return;

    try {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                ""
        );

        String sql = "SELECT * FROM studentinfo WHERE student_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            JOptionPane.showMessageDialog(this,
                    "ID: " + rs.getString("student_id") +
                    "\nName: " + rs.getString("name") +
                    "\nAge: " + rs.getInt("age") +
                    "\nGender: " + rs.getString("gender") +
                    "\nBirthday: " + rs.getString("birthday")
            );

        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
    private void updateStudent() {

    String id = JOptionPane.showInputDialog(this, "Enter Student ID to update:");
    if (id == null || id.trim().isEmpty()) return;

    JTextField name = new JTextField();
    JTextField age = new JTextField();
    JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female"});
    JTextField birthday = new JTextField();

    JPanel panel = new JPanel(new GridLayout(4,2));
    panel.add(new JLabel("Name")); panel.add(name);
    panel.add(new JLabel("Age")); panel.add(age);
    panel.add(new JLabel("Gender")); panel.add(gender);
    panel.add(new JLabel("Birthday")); panel.add(birthday);

    int result = JOptionPane.showConfirmDialog(this, panel, "Update Student",
            JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolsystemdb",
                    "root",
                    ""
            );

            String sql =
                    "UPDATE studentinfo SET name=?, age=?, gender=?, birthday=? WHERE student_id=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, name.getText().trim());
            pst.setInt(2, Integer.parseInt(age.getText().trim()));
            pst.setString(3, gender.getSelectedItem().toString());
            pst.setString(4, birthday.getText().trim());
            pst.setString(5, id);

            int rows = pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    rows > 0 ? "Updated Successfully!" : "Student not found!");

            pst.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    private void deleteStudent() {

    String id = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");
    if (id == null || id.trim().isEmpty()) return;

    try {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                ""
        );

        String sql = "DELETE FROM studentinfo WHERE student_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, id);

        int rows = pst.executeUpdate();

        JOptionPane.showMessageDialog(this,
                rows > 0 ? "Deleted Successfully!" : "Student not found!");

        pst.close();
        conn.close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
    private boolean isValidAge(String ageText) {

    try {
        int age = Integer.parseInt(ageText);
        return age >= 1 && age <= 120;
    } catch (Exception e) {
        return false;
    }
}

private boolean isValidBirthday(String bday) {
    return bday.matches("\\d{4}-\\d{2}-\\d{2}");
}

private boolean isEmpty(String... fields) {
    for (String f : fields) {
        if (f == null || f.trim().isEmpty()) {
            return true;
        }
    }
    return false;
}

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAttendance) {

            dispose();
            JamesEarlAttendance sma = new JamesEarlAttendance();
            sma.setVisible(true);

        } else if (e.getSource() == btnGrades) {

            dispose();
            TeacherGrades tg = new TeacherGrades();
            tg.setVisible(true);

        } else if (e.getSource() == btnStudentInfo) {

    Object[] options = {"Add", "Search", "Update", "Delete", "Cancel", "View"};

    int choice = JOptionPane.showOptionDialog(
            this,
            "Choose an action:",
            "Student Management",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
    );

    // =========================
    // 1. ADD STUDENT (INSERT)
    // =========================
    if (choice == 0) {

    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField age = new JTextField();
    JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female"});
    JTextField birthday = new JTextField();

    JPanel panel = new JPanel(new GridLayout(5,2));
    panel.add(new JLabel("ID")); panel.add(id);
    panel.add(new JLabel("Name")); panel.add(name);
    panel.add(new JLabel("Age")); panel.add(age);
    panel.add(new JLabel("Gender")); panel.add(gender);
    panel.add(new JLabel("Birthday (YYYY-MM-DD)")); panel.add(birthday);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Student",
            JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {

        String sid = id.getText();
        String sname = name.getText();
        String sage = age.getText();
        String sbday = birthday.getText();

        if (isEmpty(sid, sname, sage, sbday)) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (!isValidAge(sage)) {
            JOptionPane.showMessageDialog(this, "Invalid age (1–120 only)!");
            return;
        }

        if (!isValidBirthday(sbday)) {
            JOptionPane.showMessageDialog(this, "Birthday must be YYYY-MM-DD!");
            return;
        }

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolsystemdb",
                    "root",
                    ""
            );

            String sql = "INSERT INTO studentinfo VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, sid);
            pst.setString(2, sname);
            pst.setInt(3, Integer.parseInt(sage));
            pst.setString(4, gender.getSelectedItem().toString());
            pst.setString(5, sbday);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");

            pst.close();
            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
    else if(choice == 5){
        StudentInfo si = new StudentInfo();
        si.setVisible(true);
        dispose();
    }

 
    else if (choice == 1) {
            String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolsystemdb",
                    "root",
                    ""
            );

            String sql = "SELECT * FROM studentinfo WHERE student_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(this,
                        "ID: " + rs.getString("student_id") +
                        "\nName: " + rs.getString("name") +
                        "\nAge: " + rs.getInt("age") +
                        "\nGender: " + rs.getString("gender") +
                        "\nBirthday: " + rs.getString("birthday")
                );

            } else {
                JOptionPane.showMessageDialog(this, "Student not found!");
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
             JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage()
             );
        }
    }

    // =========================
    // 3. UPDATE STUDENT
    // =========================
   else if (choice == 2) {

    String id = JOptionPane.showInputDialog(this, "Enter Student ID to update:");
    if (id == null || id.trim().isEmpty()) return;

    try {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                ""
        );

        // ✅ STEP 1: CHECK IF STUDENT EXISTS FIRST
        String checkSql = "SELECT * FROM studentinfo WHERE student_id=?";
        PreparedStatement checkPst = conn.prepareStatement(checkSql);
        checkPst.setString(1, id);

        ResultSet rs = checkPst.executeQuery();

        if (!rs.next()) {
            JOptionPane.showMessageDialog(this, "Student ID not found!");
            rs.close();
            checkPst.close();
            conn.close();
            return;
        }

        rs.close();
        checkPst.close();

        // ✅ STEP 2: SHOW UPDATE FORM
        JTextField name = new JTextField(rs.getString("name"));
        JTextField age = new JTextField(String.valueOf(rs.getInt("age")));
        JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female"});
        gender.setSelectedItem(rs.getString("gender"));
        JTextField birthday = new JTextField(rs.getString("birthday"));

        JPanel panel = new JPanel(new GridLayout(4,2));
        panel.add(new JLabel("Name")); panel.add(name);
        panel.add(new JLabel("Age")); panel.add(age);
        panel.add(new JLabel("Gender")); panel.add(gender);
        panel.add(new JLabel("Birthday")); panel.add(birthday);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Student",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String sql = "UPDATE studentinfo SET name=?, age=?, gender=?, birthday=? WHERE student_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, name.getText().trim());
            pst.setInt(2, Integer.parseInt(age.getText().trim()));
            pst.setString(3, gender.getSelectedItem().toString());
            pst.setString(4, birthday.getText().trim());
            pst.setString(5, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }

            pst.close();
        }

        conn.close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    // =========================
    // 4. DELETE STUDENT
    // =========================
    else if (choice == 3) {

        String id = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");
        if (id == null || id.trim().isEmpty()) return;

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolsystemdb",
                    "root",
                    ""
            );

            String sql = "DELETE FROM studentinfo WHERE student_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);

            int rows = pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    rows > 0 ? "Deleted Successfully!" : "Student not found!");

            pst.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        } else if (e.getSource() == btnBack) {

            dispose();
            TeacherLogIn tl = new TeacherLogIn();
            tl.setVisible(true);
        }
      }

    public void addHoverEffect(JButton button) {

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setBackground(Color.decode("#312E81"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.decode("#1E1B4B"));
                button.setBackground(Color.decode("#BEE9FF"));
            }
        });
    }
}