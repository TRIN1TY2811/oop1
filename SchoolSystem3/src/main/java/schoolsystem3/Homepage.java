package schoolsystem3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import schoolsystem3.StudentsData.Student1;
/**
 *
 * @author admin
 */
public class Homepage extends JFrame implements ActionListener {
    private JButton btnStud, btnTch;
    private JLabel lblHome, lblHome1, lblHome2;
    private JPanel pnlPanel;
    
   public Homepage() {

    setSize(800, 750);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    ImageIcon img = new ImageIcon("C:\\Users\\cubes\\Downloads\\pup.png");

    Image scaled = img.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);

    JLabel background = new JLabel(new ImageIcon(scaled));
    background.setLayout(null);

    setContentPane(background);
    

    pnlPanel = new JPanel();
    pnlPanel.setLayout(null);

    pnlPanel.setBounds(500, 0, 300, 750);

    pnlPanel.setBackground(new Color(255, 255, 255, 170));
  // pnlPanel.setBackground(Color.decode("#ebebeb"));
    background.add(pnlPanel);

    lblHome = new JLabel("Access your academic portal", SwingConstants.CENTER);
    lblHome.setBounds(20, 300, 260, 50);
    lblHome.setFont(new Font("Arial", Font.BOLD, 18));
    lblHome.setForeground(Color.decode("#312E81"));
    
    lblHome2 = new JLabel("Welcome Back", SwingConstants.CENTER);
    lblHome2.setBounds(20, 270, 260, 50);
    lblHome2.setFont(new Font("Arial", Font.BOLD, 26));
    lblHome2.setForeground(Color.decode("#312E81"));
    
    lblHome1 = new JLabel("Hi, PUPian!", SwingConstants.CENTER);
    lblHome1.setBounds(18, 240, 260, 50);
    lblHome1.setFont(new Font("Arial", Font.BOLD, 26));
    lblHome1.setForeground(Color.decode("#312E81"));
    pnlPanel.add(lblHome);
    pnlPanel.add(lblHome1);
    pnlPanel.add(lblHome2);


     btnStud = new JButton("Student");
     btnStud.setBounds(535, 350, 220, 40);   
     btnStud.setFont(new Font("Arial", Font.BOLD, 18));
     btnStud.setForeground(Color.decode("#1E1B4B"));
            btnStud.setBackground(Color.decode("#BEE9FF"));
           btnStud.setOpaque(true);
            btnStud.setBorderPainted(false);
           
    this.add(btnStud);
    

    // TEACHER BUTTON
    btnTch = new JButton("Teacher");
    btnTch.setBounds(535, 400, 220, 40);
    btnTch.setFont(new Font("Arial", Font.BOLD, 18));
    btnTch.setForeground(Color.decode("#1E1B4B"));
            btnTch.setBackground(Color.decode("#BEE9FF"));
           btnTch.setOpaque(true);
            btnTch.setBorderPainted(false);
    this.add(btnTch);
    addHoverEffect(btnTch);
    addHoverEffect(btnStud);
    
    btnStud.addActionListener(this);
    btnTch.addActionListener(this);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if(e.getSource()==btnStud){
            Student1 sc = new Student1();
            sc.setVisible(true);
        }
        else if(e.getSource() == btnTch) {
            TeacherLogIn tch = new TeacherLogIn();
            tch.setVisible(true);
        }
        }
    
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
        
   public void AddHoverEffectsLBL(JLabel label) {

    label.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e) {
            label.setForeground(Color.decode("#3B0CA3"));
           // label.setBackground(Color.decode("#7FA6E6"));
            label.setOpaque(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setForeground(Color.decode("#0d07ba"));
           // label.setBackground(Color.decode("#0d07ba"));
        }
    });
}
   
}


