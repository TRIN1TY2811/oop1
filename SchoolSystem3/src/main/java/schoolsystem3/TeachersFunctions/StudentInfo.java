package schoolsystem3.TeachersFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentInfo extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtID, txtName, txtAge, txtBirthday;
    private JComboBox<String> cmbGender;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnBack;

    public StudentInfo() {

        setSize(1000, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel background = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(Color.decode("#C0000B"));
                g2.fillRect(0, 0, 185, getHeight());
                g2.setColor(new Color(255, 255, 255, 18));
                g2.fillOval(-40, 480, 280, 280);
                g2.fillOval(10, -70, 210, 210);
                g2.setColor(new Color(192, 0, 11, 12));
                g2.fillRect(185, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(null);
        setContentPane(background);

        JLabel lblSystem = new JLabel("PUP", SwingConstants.CENTER);
        lblSystem.setBounds(10, 28, 165, 30);
        lblSystem.setFont(new Font("Arial", Font.BOLD, 22));
        lblSystem.setForeground(Color.WHITE);
        background.add(lblSystem);

        JLabel lblSubSys = new JLabel("School System", SwingConstants.CENTER);
        lblSubSys.setBounds(10, 52, 165, 20);
        lblSubSys.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSubSys.setForeground(new Color(255, 210, 210));
        background.add(lblSubSys);

        JSeparator sideDiv = new JSeparator();
        sideDiv.setBounds(20, 80, 145, 1);
        sideDiv.setForeground(new Color(255, 255, 255, 60));
        background.add(sideDiv);

        JLabel lblSection = new JLabel("MANAGEMENT", SwingConstants.CENTER);
        lblSection.setBounds(10, 90, 165, 24);
        lblSection.setFont(new Font("Arial", Font.BOLD, 10));
        lblSection.setForeground(new Color(255, 180, 180));
        background.add(lblSection);

        JLabel lblStudentInfo = new JLabel("Student Info", SwingConstants.CENTER);
        lblStudentInfo.setBounds(18, 122, 150, 36);
        lblStudentInfo.setFont(new Font("Arial", Font.BOLD, 12));
        lblStudentInfo.setForeground(Color.WHITE);
        lblStudentInfo.setOpaque(true);
        lblStudentInfo.setBackground(new Color(160, 0, 9));
        lblStudentInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        background.add(lblStudentInfo);

        JSeparator sideFootDiv = new JSeparator();
        sideFootDiv.setBounds(20, 660, 145, 1);
        sideFootDiv.setForeground(new Color(255, 255, 255, 40));
        background.add(sideFootDiv);

        JLabel lblFooter = new JLabel("© 2025 PUP", SwingConstants.CENTER);
        lblFooter.setBounds(10, 668, 165, 20);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFooter.setForeground(new Color(255, 180, 180));
        background.add(lblFooter);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBounds(205, 20, 770, 670);
        mainPanel.setOpaque(false);
        background.add(mainPanel);

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

        JLabel title = new JLabel("Student List", SwingConstants.CENTER);
        title.setBounds(0, 0, 770, 55);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        headerBar.add(title);

        model = new DefaultTableModel(
            new String[]{"student_id", "name", "age", "gender", "birthday"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(28);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(192, 0, 11, 60));
        table.setSelectionForeground(Color.decode("#1a1a1a"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                setHorizontalAlignment(SwingConstants.CENTER);
                if (!isSelected) {
                    setBackground(row % 2 == 0 ? Color.WHITE : new Color(255, 247, 247));
                    setForeground(Color.decode("#1a1a1a"));
                }
                setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));
                return this;
            }
        };
        for (int c = 0; c < table.getColumnCount(); c++) {
            table.getColumnModel().getColumn(c).setCellRenderer(centerRenderer);
        }

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12));
        tableHeader.setForeground(Color.decode("#C0000B"));
        tableHeader.setBackground(new Color(255, 240, 240));
        tableHeader.setPreferredSize(new Dimension(0, 32));
        tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(192, 0, 11, 80)));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0, 65, 770, 240);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 200), 1));
        scroll.getViewport().setBackground(Color.WHITE);
        mainPanel.add(scroll);

        // ── Clicking a row populates the form fields including the combo box ──
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtID.setText(model.getValueAt(row, 0).toString());
                    txtName.setText(model.getValueAt(row, 1).toString());
                    txtAge.setText(model.getValueAt(row, 2).toString());
                    cmbGender.setSelectedItem(model.getValueAt(row, 3).toString());
                    txtBirthday.setText(model.getValueAt(row, 4).toString());
                    txtID.setEditable(false);
                }
            }
        });

        JSeparator divider = new JSeparator();
        divider.setBounds(0, 318, 770, 1);
        divider.setForeground(new Color(192, 0, 11, 60));
        mainPanel.add(divider);

        JLabel formTitle = new JLabel("Student Details");
        formTitle.setBounds(0, 326, 300, 24);
        formTitle.setFont(new Font("Arial", Font.BOLD, 13));
        formTitle.setForeground(Color.decode("#C0000B"));
        mainPanel.add(formTitle);

        int labelX = 0, fieldX = 130, fieldW = 200, rowH = 38;
        int startY = 358;

        addLabel(mainPanel, "Student ID:", labelX, startY);
        txtID = addField(mainPanel, fieldX, startY, fieldW);

        addLabel(mainPanel, "Name:", labelX, startY + rowH);
        txtName = addField(mainPanel, fieldX, startY + rowH, fieldW);

        addLabel(mainPanel, "Age:", labelX, startY + rowH * 2);
        txtAge = addField(mainPanel, fieldX, startY + rowH * 2, fieldW);

        // ── Gender dropdown ──
        addLabel(mainPanel, "Gender:", labelX, startY + rowH * 3);
        cmbGender = new JComboBox<>(new String[]{"Male", "Female"});
        cmbGender.setBounds(fieldX, startY + rowH * 3, fieldW, 30);
        cmbGender.setFont(new Font("Arial", Font.PLAIN, 12));
        cmbGender.setForeground(Color.decode("#1a1a1a"));
        cmbGender.setBackground(Color.WHITE);
        cmbGender.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 200), 1));
        cmbGender.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(cmbGender);

        addLabel(mainPanel, "Birthday:", labelX, startY + rowH * 4);
        txtBirthday = addField(mainPanel, fieldX, startY + rowH * 4, fieldW);

        JLabel hint = new JLabel("(YYYY-MM-DD)");
        hint.setBounds(fieldX + fieldW + 8, startY + rowH * 4, 110, 30);
        hint.setForeground(new Color(180, 180, 180));
        hint.setFont(new Font("Arial", Font.ITALIC, 11));
        mainPanel.add(hint);

        JSeparator divider2 = new JSeparator();
        divider2.setBounds(0, startY + rowH * 5 + 8, 770, 1);
        divider2.setForeground(new Color(192, 0, 11, 60));
        mainPanel.add(divider2);

        int btnY = startY + rowH * 5 + 18;
        int btnW = 120, btnH = 38;
        int[] btnXPositions = {0, 130, 260, 390, 520};
        String[] btnLabels = {"Add", "Update", "Delete", "Clear", "Back"};
        JButton[] buttons = new JButton[5];

        for (int i = 0; i < 5; i++) {
            buttons[i] = new JButton(btnLabels[i]);
            buttons[i].setBounds(btnXPositions[i], btnY, btnW, btnH);
            styleActionButton(buttons[i], i == 0);
            mainPanel.add(buttons[i]);
        }

        btnAdd    = buttons[0];
        btnUpdate = buttons[1];
        btnDelete = buttons[2];
        btnClear  = buttons[3];
        btnBack   = buttons[4];

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

    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 125, 30);
        lbl.setForeground(Color.decode("#1a1a1a"));
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(lbl);
    }

    private JTextField addField(JPanel panel, int x, int y, int w) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, 30);
        tf.setFont(new Font("Arial", Font.PLAIN, 12));
        tf.setForeground(Color.decode("#1a1a1a"));
        tf.setBackground(Color.WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 200, 200), 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        panel.add(tf);
        return tf;
    }

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

    private void loadData() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "")) {
            PreparedStatement pst = conn.prepareStatement(
                "SELECT student_id, name, age, gender, birthday FROM studentinfo");
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("age"),
                    rs.getString("gender"),
                    rs.getString("birthday")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void addStudent() {
        if (!validateFields()) return;
        String id       = txtID.getText().trim();
        String name     = txtName.getText().trim();
        String age      = txtAge.getText().trim();
        String gender   = cmbGender.getSelectedItem().toString();
        String birthday = txtBirthday.getText().trim();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "")) {
            PreparedStatement check = conn.prepareStatement(
                "SELECT student_id FROM studentinfo WHERE student_id = ?");
            check.setString(1, id);
            if (check.executeQuery().next()) {
                JOptionPane.showMessageDialog(this, "Student ID already exists!",
                    "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                return;
            }
            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO studentinfo (student_id, name, age, gender, birthday) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, age);
            pst.setString(4, gender);
            pst.setString(5, birthday);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void updateStudent() {
        if (!validateFields()) return;
        String id       = txtID.getText().trim();
        String name     = txtName.getText().trim();
        String age      = txtAge.getText().trim();
        String gender   = cmbGender.getSelectedItem().toString();
        String birthday = txtBirthday.getText().trim();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "")) {
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE studentinfo SET name=?, age=?, gender=?, birthday=? WHERE student_id=?");
            pst.setString(1, name);
            pst.setString(2, age);
            pst.setString(3, gender);
            pst.setString(4, birthday);
            pst.setString(5, id);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that ID!",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

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
                "jdbc:mysql://localhost:3306/schoolsystemdb", "root", "")) {
            PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM studentinfo WHERE student_id=?");
            pst.setString(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that ID!",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        cmbGender.setSelectedIndex(0);
        txtBirthday.setText("");
        txtID.setEditable(true);
        table.clearSelection();
    }

    private boolean validateFields() {
        if (txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()
                || txtAge.getText().trim().isEmpty() || txtBirthday.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!",
                "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}