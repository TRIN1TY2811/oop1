package schoolsystem3.TeachersFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentInfo extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtID, txtName, txtAge, txtGender, txtBirthday;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnBack;

    public StudentInfo() {

        setSize(800, 750);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ================= BACKGROUND =================
        ImageIcon img = new ImageIcon("C:\\Users\\admin\\Downloads\\pup1.jpg");
        Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setLayout(null);
        setContentPane(background);

        // ================= MAIN PANEL =================
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(80, 40, 640, 660);
        panel.setBackground(new Color(255, 255, 255, 180));
        background.add(panel);

        // ================= TITLE =================
        JLabel title = new JLabel("Student List", SwingConstants.CENTER);
        title.setBounds(0, 15, 640, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.decode("#312E81"));
        panel.add(title);

        // ================= TABLE =================
        model = new DefaultTableModel(
            new String[]{"StudentID", "Name", "Age", "Gender", "Birthday"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 70, 580, 250);
        panel.add(scroll);

        // ================= CLICK TO POPULATE FIELDS =================
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtID.setText(model.getValueAt(row, 0).toString());
                    txtName.setText(model.getValueAt(row, 1).toString());
                    txtAge.setText(model.getValueAt(row, 2).toString());
                    txtGender.setText(model.getValueAt(row, 3).toString());
                    txtBirthday.setText(model.getValueAt(row, 4).toString());
                    txtID.setEditable(false);
                }
            }
        });

        // ================= INPUT FIELDS =================
        int labelX = 30, fieldX = 160, fieldW = 180, rowH = 35;
        int startY = 345;

        addLabel(panel, "Student ID:", labelX, startY);
        txtID = addField(panel, fieldX, startY, fieldW);

        addLabel(panel, "Name:", labelX, startY + rowH);
        txtName = addField(panel, fieldX, startY + rowH, fieldW);

        addLabel(panel, "Age:", labelX, startY + rowH * 2);
        txtAge = addField(panel, fieldX, startY + rowH * 2, fieldW);

        addLabel(panel, "Gender:", labelX, startY + rowH * 3);
        txtGender = addField(panel, fieldX, startY + rowH * 3, fieldW);

        addLabel(panel, "Birthday:", labelX, startY + rowH * 4);
        txtBirthday = addField(panel, fieldX, startY + rowH * 4, fieldW);

        JLabel hint = new JLabel("(YYYY-MM-DD)");
        hint.setBounds(fieldX + fieldW + 5, startY + rowH * 4, 110, 30);
        hint.setForeground(Color.GRAY);
        hint.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(hint);

        // ================= BUTTONS =================
        int btnY = startY + rowH * 5 + 15;
        int btnW = 110, btnH = 35;

        btnAdd    = createButton("Add",    30,  btnY, btnW, btnH);
        btnUpdate = createButton("Update", 155, btnY, btnW, btnH);
        btnDelete = createButton("Delete", 280, btnY, btnW, btnH);
        btnClear  = createButton("Clear",  405, btnY, btnW, btnH);
        btnBack   = createButton("Back",   530, btnY, btnW, btnH);

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);
        panel.add(btnBack);

        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> {
            dispose();
            new TeacherDashB().setVisible(true);
        });

        loadData();
    }

    // ================= HELPER: LABEL =================
    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 120, 30);
        lbl.setForeground(Color.decode("#312E81"));
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lbl);
    }

    // ================= HELPER: FIELD =================
    private JTextField addField(JPanel panel, int x, int y, int w) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, 30);
        panel.add(tf);
        return tf;
    }

    // ================= HELPER: BUTTON =================
    private JButton createButton(String text, int x, int y, int w, int h) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(Color.decode("#1E1B4B"));
        btn.setBackground(Color.decode("#BEE9FF"));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(Color.WHITE);
                btn.setBackground(Color.decode("#312E81"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setForeground(Color.decode("#1E1B4B"));
                btn.setBackground(Color.decode("#BEE9FF"));
            }
        });
        return btn;
    }

    // ================= LOAD DATA =================
    private void loadData() {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            PreparedStatement pst = conn.prepareStatement(
                "SELECT * FROM students"
            );
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("StudentID"),
                    rs.getString("Name"),
                    rs.getString("Age"),
                    rs.getString("Gender"),
                    rs.getString("Birthday")
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ================= ADD STUDENT =================
    private void addStudent() {

        if (!validateFields()) return;

        String id       = txtID.getText().trim();
        String name     = txtName.getText().trim();
        String age      = txtAge.getText().trim();
        String gender   = txtGender.getText().trim();
        String birthday = txtBirthday.getText().trim();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            // duplicate ID check
            PreparedStatement check = conn.prepareStatement(
                "SELECT StudentID FROM students WHERE StudentID = ?"
            );
            check.setString(1, id);
            if (check.executeQuery().next()) {
                JOptionPane.showMessageDialog(this,
                    "Student ID already exists!",
                    "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                return;
            }

            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO students (StudentID, Name, Age, Gender, Birthday) VALUES (?, ?, ?, ?, ?)"
            );
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, age);
            pst.setString(4, gender);
            pst.setString(5, birthday);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                "Student added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);

            clearFields();
            loadData();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ================= UPDATE STUDENT =================
    private void updateStudent() {

        if (!validateFields()) return;

        String id       = txtID.getText().trim();
        String name     = txtName.getText().trim();
        String age      = txtAge.getText().trim();
        String gender   = txtGender.getText().trim();
        String birthday = txtBirthday.getText().trim();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            PreparedStatement pst = conn.prepareStatement(
                "UPDATE students SET Name=?, Age=?, Gender=?, Birthday=? WHERE StudentID=?"
            );
            pst.setString(1, name);
            pst.setString(2, age);
            pst.setString(3, gender);
            pst.setString(4, birthday);
            pst.setString(5, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Student updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this,
                    "No student found with that ID!",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ================= DELETE STUDENT =================
    private void deleteStudent() {

        String id = txtID.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select or enter a Student ID to delete!",
                "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete Student ID: " + id + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

            PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM students WHERE StudentID=?"
            );
            pst.setString(1, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Student deleted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this,
                    "No student found with that ID!",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ================= CLEAR FIELDS =================
    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtGender.setText("");
        txtBirthday.setText("");
        txtID.setEditable(true);
        table.clearSelection();
    }

    // ================= VALIDATE FIELDS =================
    private boolean validateFields() {
        if (txtID.getText().trim().isEmpty()
                || txtName.getText().trim().isEmpty()
                || txtAge.getText().trim().isEmpty()
                || txtGender.getText().trim().isEmpty()
                || txtBirthday.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                "Please fill in all fields!",
                "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}