package schoolsystem3.TeachersFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import schoolsystem3.TeachersFunctions.TeacherDashB;

public class StudentInfo extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public StudentInfo() {

        setSize(800, 750);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("C:\\Users\\admin\\Downloads\\pup1.jpg");
        Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setLayout(null);
        setContentPane(background);

       
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(120, 60, 560, 520);
        panel.setBackground(new Color(255, 255, 255, 180));
        background.add(panel);

        JLabel title = new JLabel("Student List", SwingConstants.CENTER);
        title.setBounds(120, 20, 320, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.decode("#312E81"));
        panel.add(title);

        
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Birthday");

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 80, 500, 350);
        panel.add(scroll);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(320, 450, 120, 40);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setForeground(Color.decode("#1E1B4B"));
        btnBack.setBackground(Color.decode("#BEE9FF"));
        panel.add(btnBack);

   
        btnBack.addActionListener(e -> {
            dispose();
            new TeacherDashB().setVisible(true);
        });

        // first load
        loadData();
    }

 
    private void loadData() {

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolsystemdb",
                    "root",
                    ""
            );

            String sql = "SELECT * FROM studentinfo";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("birthday")
                });
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}