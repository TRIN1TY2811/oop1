package schoolsystem3.StudentInsideData.Grades;

import schoolsystem3.StudentsData.Student1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Student1Grades extends JFrame implements ActionListener {

    private JButton btnback, btnOOP, btnInteg, btnCP, btnNetAd, btnOS;
    private JLabel lblatt;

    private JPanel pnlSideBar, pnlMain;
    private CardLayout cardLayout;

    // Colors
    private final Color defaultColor = new Color(230, 230, 230);
    private final Color activeColor = new Color(100, 149, 237);

    public Student1Grades() {

        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        //  SIDEBAR 
        pnlSideBar = new JPanel();
        pnlSideBar.setLayout(null);
        pnlSideBar.setBounds(0, 0, 180, 750);
        pnlSideBar.setBackground(new Color(245, 245, 245));
        add(pnlSideBar);

        JLabel lblMenu = new JLabel("GRADES");
        lblMenu.setBounds(50, 40, 100, 30);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 16));
        pnlSideBar.add(lblMenu);

        btnOOP = createSideButton("OOP", 120);
        btnInteg = createSideButton("Integrative", 170);
        btnCP = createSideButton("Programming", 220);
        btnNetAd = createSideButton("Network", 270);
        btnOS = createSideButton("Operating Sys", 320);

        pnlSideBar.add(btnOOP);
        pnlSideBar.add(btnInteg);
        pnlSideBar.add(btnCP);
        pnlSideBar.add(btnNetAd);
        pnlSideBar.add(btnOS);

        //  MAIN PANEL 
        cardLayout = new CardLayout();

        pnlMain = new JPanel(cardLayout);
        pnlMain.setBounds(200, 0, 800, 500);

        // Add pages
        pnlMain.add(createOOPPanel(), "OOP");
        pnlMain.add(createIntegPanel(), "INTEG");
        pnlMain.add(createCompPanel(), "CP");
        pnlMain.add(createNetAdPanel(), "NET");
        pnlMain.add(createOSPanel(), "OS");

        add(pnlMain);

        //  BACK BUTTON 
        btnback = new JButton("Back");
        btnback.setBounds(650, 620, 100, 30);
        add(btnback);

        //  ACTIONS 
        btnOOP.addActionListener(this);
        btnInteg.addActionListener(this);
        btnCP.addActionListener(this);
        btnNetAd.addActionListener(this);
        btnOS.addActionListener(this);
        btnback.addActionListener(this);

        // Default page
        cardLayout.show(pnlMain, "OOP");
        setActiveButton(btnOOP);
    }
    private JPanel createOOPPanel() {
       

    JPanel panel = new JPanel();

    panel.setLayout(null);
    panel.setBackground(Color.WHITE);

JLabel lblatt = new JLabel("OOP Grades");

    lblatt.setBounds(20, 0, 400, 50);
    lblatt.setFont(new Font("Arial", Font.PLAIN, 24));

    panel.add(lblatt);

    // STUDENTS
    
        addField(panel,"Amodia, James Earl", 20, 80, 120, 30);
        addField(panel,"", 160, 80, 120, 30);
        addField(panel,"", 300, 80, 120, 30);
        addField(panel,"", 440, 80, 120, 30);
        addField(panel,"", 580, 80, 120, 30);

    return panel;
}

    //  CREATE PAGE 
    private JPanel createPage(String Teachers) {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel lbl = new JLabel(Teachers, SwingConstants.CENTER);
        lbl.setBounds(100, 200, 350, 40);
        lbl.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(lbl);

        return panel;
    }

    //  BUTTON STYLE 
    private JButton createSideButton(String text, int y) {

        JButton btn = new JButton(text);

        btn.setBounds(10, y, 160, 35);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setBackground(defaultColor);

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);

        return btn;
    }
    private JPanel createIntegPanel() {

    JPanel panel = new JPanel();

    panel.setLayout(null);
    panel.setBackground(Color.WHITE);

JLabel lblatt = new JLabel("Integ Grades");

    lblatt.setBounds(20, 0, 400, 50);
    lblatt.setFont(new Font("Arial", Font.PLAIN, 24));

    panel.add(lblatt);

    // STUDENTS
    
        addField(panel,"Amodia, James Earl", 20, 80, 120, 30);
        addField(panel,"", 160, 80, 120, 30);
        addField(panel,"", 300, 80, 120, 30);
        addField(panel,"", 440, 80, 120, 30);
        addField(panel,"", 580, 80, 120, 30);

     
    return panel;
        }
    
        private JPanel createOSPanel() {

    JPanel panel = new JPanel();

    panel.setLayout(null);
    panel.setBackground(Color.WHITE);

JLabel lblatt = new JLabel("Operating System Grades");

    lblatt.setBounds(20, 0, 400, 50);
    lblatt.setFont(new Font("Arial", Font.PLAIN, 24));

    panel.add(lblatt);

    // STUDENTS
    
        addField(panel,"Amodia, James Earl", 20, 80, 120, 30);
        addField(panel,"", 160, 80, 120, 30);
        addField(panel,"", 300, 80, 120, 30);
        addField(panel,"", 440, 80, 120, 30);
        addField(panel,"", 580, 80, 120, 30);

     
    return panel;
        }
        
        private JPanel createCompPanel() {

    JPanel panel = new JPanel();

    panel.setLayout(null);
    panel.setBackground(Color.WHITE);

JLabel lblatt = new JLabel("Computer Programming Grades");

    lblatt.setBounds(20, 0, 400, 50);
    lblatt.setFont(new Font("Arial", Font.PLAIN, 24));

    panel.add(lblatt);

    // STUDENTS
    
        addField(panel,"Amodia, James Earl", 20, 80, 120, 30);
        addField(panel,"", 160, 80, 120, 30);
        addField(panel,"", 300, 80, 120, 30);
        addField(panel,"", 440, 80, 120, 30);
        addField(panel,"", 580, 80, 120, 30);
        
        

     
    return panel;
        }
        
        private JPanel createNetAdPanel() {

    JPanel panel = new JPanel();

    panel.setLayout(null);
    panel.setBackground(Color.WHITE);

JLabel lblatt = new JLabel("Network Administration Grades");

    lblatt.setBounds(20, 0, 400, 50);
    lblatt.setFont(new Font("Arial", Font.PLAIN, 24));

    panel.add(lblatt);

    // STUDENTS
    
        addField(panel,"Amodia, James Earl", 20, 80, 120, 30);
        addField(panel,"", 160, 80, 120, 30);
        addField(panel,"", 300, 80, 120, 30);
        addField(panel,"", 440, 80, 120, 30);
        addField(panel,"", 580, 80, 120, 30);

     
    return panel;
        }
        
        

    //  ACTIVE BUTTON 
    private void setActiveButton(JButton activeBtn) {

        JButton[] buttons = {
            btnOOP, btnInteg, btnCP, btnNetAd, btnOS
        };

        for (JButton btn : buttons) {

            btn.setBackground(defaultColor);
            btn.setForeground(Color.BLACK);
        }

        activeBtn.setBackground(activeColor);
        activeBtn.setForeground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnback) {

            setVisible(false);
            new Student1().setVisible(true);
        }

        else if (e.getSource() == btnOOP) {

            cardLayout.show(pnlMain, "OOP");
            setActiveButton(btnOOP);
        }

        else if (e.getSource() == btnInteg) {

            cardLayout.show(pnlMain, "INTEG");
            setActiveButton(btnInteg);
        }

        else if (e.getSource() == btnCP) {

            cardLayout.show(pnlMain, "CP");
            setActiveButton(btnCP);
        }

        else if (e.getSource() == btnNetAd) {

            cardLayout.show(pnlMain, "NET");
            setActiveButton(btnNetAd);
        }

        else if (e.getSource() == btnOS) {

            cardLayout.show(pnlMain, "OS");
            setActiveButton(btnOS);
        }
    }
   private void addField(JPanel panel, String text,int x, int y, int width, int height) {

    JTextField txt = new JTextField(text);

    txt.setBounds(x, y, width, height);

    panel.add(txt);
}
}