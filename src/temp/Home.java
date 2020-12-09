package temp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

class Homepage extends JFrame implements ActionListener{
	
	Container container = getContentPane();
	JLabel lblNewLabel = new JLabel("New label");
	JLabel lblNewLabel_1 = new JLabel("New label");
	JLabel lblNewLabel_3 = new JLabel("<html><h1>DIARY</h1></html>");
	JButton btnNewButton = new JButton();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyyy HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();
    JLabel textField = new JLabel(dtf.format(now));
	JLabel lblNewLabel_2 = new JLabel("New label");
	JLabel lblNewLabel_4 = new JLabel("New label");
	JButton button = new JButton();
	JButton button_1 = new JButton();
		
	Homepage(){		
		lblNewLabel_3.setIcon(new ImageIcon(Home.class.getResource("/images/android-icon-36x36.png")));
		lblNewLabel_3.setBackground(new Color(255, 255, 204));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBounds(315, 11, 149, 70);
		container.add(lblNewLabel_3);
		
		textField.setOpaque(true);
		textField.setBackground(new Color(255, 255, 204));
		textField.setBounds(637, 235, 120, 51);
		container.add(textField);
		
		
		lblNewLabel_2.setIcon(new ImageIcon(Home.class.getResource("/images/xt (11).png")));
		lblNewLabel_2.setBounds(602, 216, 184, 301);
		container.add(lblNewLabel_2);
		
		button_1.setBackground(new Color(204, 153, 102));
		button_1.setIcon(new ImageIcon(Home.class.getResource("/images/android-icon-36x36 plus.png")));
		button_1.setBounds(69, 127, 60, 56);
		container.add(button_1);
		
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/images/xt (4).png")));
		lblNewLabel.setBounds(58, 127, 333, 229);
		container.add(lblNewLabel);
		
		lblNewLabel_1.setIcon(new ImageIcon(Home.class.getResource("/images/xt (8).png")));
		lblNewLabel_1.setBounds(36, 367, 273, 223);
		container.add(lblNewLabel_1);
				
		lblNewLabel_4.setIcon(new ImageIcon(Home.class.getResource("/images/Untitled design (2).png")));
		lblNewLabel_4.setBounds(-827, 0, 1630, 624);
		lblNewLabel_4.setBackground(new Color(204, 204, 153));
		container.add(lblNewLabel_4);
		
		addActionEvent();
		
	}
	
	public void addActionEvent(){

	
	    button_1.addActionListener((ActionListener) this);
	    
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == button_1){
			 dispose();
			 Diary_GUI frame = new Diary_GUI();
				frame.setVisible(true);
		 }
		
	}
}

class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}

public class Home {
	
	public static void main(String[] args) {
		
		Homepage hp = new Homepage();
		hp.getContentPane().setBackground(new Color(255, 240, 245));
		Image icon1 = Toolkit.getDefaultToolkit().getImage("C:/Users/becca/eclipse-workspace/OOPM_Mini_Project/src/images/android-icon-36x36.png");  
		hp.setIconImage(icon1);  
		hp.setTitle("Login Form");
		hp.getContentPane().setLayout(null);
		hp.setVisible(true);
		hp.setBounds(10,10,800,650);
		hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	
	}
}
