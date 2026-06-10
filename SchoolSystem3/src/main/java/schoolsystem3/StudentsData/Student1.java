package schoolsystem3.StudentsData;

import schoolsystem3.Homepage;
import schoolsystem3.StudentInsideData.Grades.Student1Grades;
import schoolsystem3.StudentInsideData.Attendance.Student1Attendance;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Student1 extends JFrame implements ActionListener {

    private JButton btnAttendance, btnGrades, btnBack, btnProfile;

    private JLabel lblTitle;
    private JLabel lblPersonalTitle;

    // PERSONAL INFO LABELS
    private JLabel lblName;
    private JLabel lblBirthdate;
    private JLabel lblCourse;
    private JLabel lblGender;
    private JLabel lblYear;

    private JPanel pnlPanel, pnlPersonal;

    public Student1() {

        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // ================= BACKGROUND =================
        ImageIcon img = new ImageIcon("C:\\Users\\admin\\Downloads\\pup1.jpg");

        Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setLayout(null);

        setContentPane(background);

        // ================= MAIN GLASS PANEL =================
        pnlPanel = new JPanel();
        pnlPanel.setLayout(null);
        pnlPanel.setBounds(120, 60, 560, 500);
        pnlPanel.setBackground(new Color(255, 255, 255, 180));

        background.add(pnlPanel);

        // ================= TITLE =================
        lblTitle = new JLabel("Student Dashboard", SwingConstants.CENTER);
        lblTitle.setBounds(0, 20, 560, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        pnlPanel.add(lblTitle);

        // ================= GRADES BUTTON =================
        btnGrades = new JButton("Grades");
        btnGrades.setBounds(170, 100, 220, 40);
        btnGrades.setFont(new Font("Arial", Font.BOLD, 18));

        pnlPanel.add(btnGrades);

        // ================= ATTENDANCE BUTTON =================
        btnAttendance = new JButton("Attendance");
        btnAttendance.setBounds(170, 200, 220, 40);
        btnAttendance.setFont(new Font("Arial", Font.BOLD, 18));

        pnlPanel.add(btnAttendance);
        
        // ================= PROFILE BUTTON =================
        btnProfile = new JButton("Student");
        btnProfile.setBounds(170, 300, 220, 40);
        btnProfile.setFont(new Font("Arial", Font.BOLD, 18));

        pnlPanel.add(btnProfile);

  
        
        // ================= BACK BUTTON =================
        btnBack = new JButton("Back");
        btnBack.setBounds(650, 620, 100, 30);

        background.add(btnBack);

        // ================= ACTION LISTENERS =================
        btnGrades.addActionListener(this);
        btnAttendance.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        dispose();

        if (e.getSource() == btnGrades) {

            new Student1Grades().setVisible(true);
        }

        if (e.getSource() == btnAttendance) {

            new Student1Attendance().setVisible(true);
        }

        if (e.getSource() == btnBack) {

            new Homepage().setVisible(true);
        }
    }
}