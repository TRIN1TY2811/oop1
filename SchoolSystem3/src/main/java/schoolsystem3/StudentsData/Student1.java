package schoolsystem3.StudentsData;
import schoolsystem3.Homepage;
import schoolsystem3.StudentInsideData.Grades.Student1Grades;
import schoolsystem3.StudentInsideData.Attendance.Student1Attendance;
import schoolsystem3.StudentsData.StudentViewInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Student1 extends JFrame implements ActionListener {
    private JButton btnAttendance, btnGrades, btnStudentInfo, btnBack;
    private JLabel lblTitle;
    private JPanel pnlPanel;

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
        pnlPanel.setBounds(120, 60, 560, 560);
        pnlPanel.setBackground(new Color(255, 255, 255, 180));
        background.add(pnlPanel);

        // ================= TITLE =================
        lblTitle = new JLabel("Student Dashboard", SwingConstants.CENTER);
        lblTitle.setBounds(0, 30, 560, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pnlPanel.add(lblTitle);

        // ================= BUTTON SETTINGS =================
        int btnWidth = 220;
        int btnHeight = 45;
        int btnX = (560 - btnWidth) / 2; // centers button in the 560-wide panel
        int startY = 120;
        int gap = 80;

        // ================= STUDENT INFO BUTTON =================
        btnStudentInfo = new JButton("Student Info");
        btnStudentInfo.setBounds(btnX, startY, btnWidth, btnHeight);
        btnStudentInfo.setFont(new Font("Arial", Font.BOLD, 18));
        pnlPanel.add(btnStudentInfo);

        // ================= GRADES BUTTON =================
        btnGrades = new JButton("Grades");
        btnGrades.setBounds(btnX, startY + gap, btnWidth, btnHeight);
        btnGrades.setFont(new Font("Arial", Font.BOLD, 18));
        pnlPanel.add(btnGrades);

        // ================= ATTENDANCE BUTTON =================
        btnAttendance = new JButton("Attendance");
        btnAttendance.setBounds(btnX, startY + gap * 2, btnWidth, btnHeight);
        btnAttendance.setFont(new Font("Arial", Font.BOLD, 18));
        pnlPanel.add(btnAttendance);

        // ================= BACK BUTTON =================
        btnBack = new JButton("Back");
        btnBack.setBounds(650, 670, 100, 35);
        background.add(btnBack);

        // ================= ACTION LISTENERS =================
        btnStudentInfo.addActionListener(this);
        btnGrades.addActionListener(this);
        btnAttendance.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == btnStudentInfo) {
            new StudentViewInfo().setVisible(true);
        }
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