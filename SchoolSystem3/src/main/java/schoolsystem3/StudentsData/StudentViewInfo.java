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
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        //  SEARCH BAR 
        JLabel lbl = new JLabel("Enter Student ID or Name:");
        lbl.setBounds(200, 20, 200, 30);
        add(lbl);

        txtSearch = new JTextField();
        txtSearch.setBounds(380, 20, 200, 30);
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {}
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (txtSearch.getText().trim().isEmpty()) loadTable();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(600, 20, 100, 30);
        btnSearch.addActionListener(this);
        add(btnSearch);

        //  SIDE PANEL 
        JPanel side = new JPanel();
        side.setLayout(null);
        side.setBounds(0, 0, 180, 700);
        side.setBackground(Color.LIGHT_GRAY);

        JLabel sideTitle = new JLabel("Student Info", SwingConstants.CENTER);
        sideTitle.setBounds(10, 30, 160, 30);
        sideTitle.setFont(new Font("Arial", Font.BOLD, 14));
        side.add(sideTitle);

        add(side);

        //  MAIN PANEL 
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBounds(200, 80, 760, 550);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        //  TITLE 
        JLabel title = new JLabel("Student Information", SwingConstants.CENTER);
        title.setBounds(0, 10, 760, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(title);

        //  HEADERS 
        String[] headers = { "ID", "Name", "Age", "Gender", "Birthday" };
        int[]    xPos    = {  20,   120,    310,    400,      500       };
        int[]    widths  = {  80,   180,     80,     90,      200       };

        for (int i = 0; i < headers.length; i++) {
            JLabel h = new JLabel(headers[i]);
            h.setBounds(xPos[i], 60, widths[i], 25);
            h.setFont(new Font("Arial", Font.BOLD, 12));
            mainPanel.add(h);
        }

        //  ROWS 
        for (int i = 0; i < 10; i++) {
            int y = 100 + (i * 35);
            infoID[i]       = createField(mainPanel, xPos[0], y, widths[0]);
            infoName[i]     = createField(mainPanel, xPos[1], y, widths[1]);
            infoAge[i]      = createField(mainPanel, xPos[2], y, widths[2]);
            infoGender[i]   = createField(mainPanel, xPos[3], y, widths[3]);
            infoBirthday[i] = createField(mainPanel, xPos[4], y, widths[4]);
        }

        //  BACK BUTTON 
        btnBack = new JButton("Back");
        btnBack.setBounds(820, 610, 150, 40);
        btnBack.addActionListener(this);
        add(btnBack);

        setVisible(true);

        // Auto-load all data on startup
        loadTable();
    }

    //  FIELD HELPER 

    private JTextField createField(JPanel panel, int x, int y, int width) {
        JTextField t = new JTextField();
        t.setBounds(x, y, width, 25);
        t.setEditable(false);
        panel.add(t);
        return t;
    }

    //  LOAD TABLE 

    private void loadTable() {

        String key = txtSearch.getText().trim();

        // Clear all rows first
        for (int i = 0; i < 10; i++) {
            infoID[i].setText("");
            infoName[i].setText("");
            infoAge[i].setText("");
            infoGender[i].setText("");
            infoBirthday[i].setText("");
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolsystemdb",
                "root",
                "")) {

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
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //  ACTIONS 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            loadTable();
        } else if (e.getSource() == btnBack) {
            new Student1().setVisible(true);
            dispose();
        }
    }
}