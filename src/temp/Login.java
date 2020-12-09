package temp;

import javax.swing.*;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

class LoginForm extends JFrame implements ActionListener {

	Container container = getContentPane();
	 
	 JLabel lblNewLabel_1 = new JLabel("<html><h1>Login</h1></html>", SwingConstants.CENTER);
	 JLabel lblNewLabel_2 = new JLabel();
	 JLabel lblNewLabel_3 = new JLabel("<html><h1>USERNAME</h1></html>");
	 JTextField txtuser = new JTextField(); 
	 JLabel label = new JLabel("<html><h1>PASSWORD</h1></html>");
	 JPasswordField txtpass = new JPasswordField();
	 JCheckBox chckbxNewCheckBox = new JCheckBox("<html><h3>SHOW PASSWORD</h3></html>");
	 JButton btnNewButton = new JButton("<html><h1>LOGIN</h1></html>");
	 JButton button = new JButton("<html><h1>RESET</h1></html>");
	 JProgressBar progressBar = new JProgressBar();
	 JLabel lblNewLabel = new JLabel();	
	
LoginForm(){
	
	lblNewLabel_1.setOpaque(true);
	lblNewLabel_1.setBackground(new Color(255, 182, 193));
    lblNewLabel_1.setBounds(485, 32, 237, 64);
    container.add(lblNewLabel_1);
    
    lblNewLabel_2.setOpaque(true);
    lblNewLabel_2.setBackground(new Color(0, 0, 0));
    lblNewLabel_2.setBounds(422, 0, 364, 121);
    container.add(lblNewLabel_2);
	
    lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 24));
    lblNewLabel_3.setOpaque(true);
    lblNewLabel_3.setBackground(new Color(248, 248, 255));
    lblNewLabel_3.setBounds(476, 175, 154, 36);
    container.add(lblNewLabel_3);
    
    txtuser.setBounds(480, 240, 271, 30);
    txtuser.setColumns(10);
    container.add(txtuser);
    
    label.setFont(new Font("Times New Roman", Font.BOLD, 24));
    label.setOpaque(true);
    label.setBackground(new Color(248, 248, 255));
    label.setBounds(476, 316, 154, 36);
    container.add(label);
    
    txtpass.setColumns(10);
    txtpass.setBounds(480, 384, 271, 30);
    container.add(txtpass);
 
    chckbxNewCheckBox.setBackground(new Color(255, 255, 204));
    chckbxNewCheckBox.setBounds(516, 432, 204, 36);
    container.add(chckbxNewCheckBox);
    
    btnNewButton.setOpaque(true);
    btnNewButton.setBackground(new Color(0, 204, 51));
    btnNewButton.setBounds(474, 494, 114, 44);
    container.add(btnNewButton);
    
    button.setOpaque(true);
    button.setBackground(new Color(255, 0, 0));
    button.setBounds(625, 494, 114, 44);
    container.add(button);
    
    progressBar.setBounds(0, 593, 786, 20);
    progressBar.setIndeterminate(true);
    progressBar.setBackground(Color.WHITE);
    progressBar.setForeground(Color.BLACK);
    progressBar.setVisible(false);
    container.add(progressBar);
    
    lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource("/images/Loading....... (2).png")));
	lblNewLabel.setBounds(-814, -37, 1237, 792);
	lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 24));
	container.add(lblNewLabel);
    
    addActionEvent();
}

public void addActionEvent(){

	btnNewButton.addActionListener(this);
    button.addActionListener(this);
    chckbxNewCheckBox.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e){

	  if(e.getSource() == btnNewButton){
          

          String username = txtuser.getText();
          String password = txtpass.getText();
	      
          try {
              Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/diary","root", "mypass");

              PreparedStatement st = (PreparedStatement) ((java.sql.Connection) connection)
                  .prepareStatement("Select name, password from student where name=? and password=?");

              st.setString(1, username);
              st.setString(2, password);
              ResultSet rs = st.executeQuery();
              if (rs.next()) {
            	  dispose();
            	  Homepage hp = new Homepage();
          		hp.getContentPane().setBackground(new Color(255, 240, 245));
          		Image icon1 = Toolkit.getDefaultToolkit().getImage("C:/Users/becca/eclipse-workspace/OOPM_Mini_Project/src/images/android-icon-36x36.png");  
          		hp.setIconImage(icon1);  
          		hp.setTitle("Login Form");
          		hp.getContentPane().setLayout(null);
          		hp.setVisible(true);
          		hp.setBounds(10,10,800,650);
          		hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          		
                  
                  JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
              } else {
                  JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
              }
          } catch (SQLException sqlException) {
              sqlException.printStackTrace();
          }
	      
	        }

    if(e.getSource() == button){

      txtuser.setText("");
      txtpass.setText("");

    }

    if(e.getSource() == chckbxNewCheckBox){

      if(chckbxNewCheckBox.isSelected()){
        txtpass.setEchoChar((char)0);
      }
      else{
        txtpass.setEchoChar('*');
      }
    }
  }
}
public class Login{
	private static JTextField textField;
	private static JTextField textField_1;
	public static void main(String[] args) {
LoginForm lf = new LoginForm();
lf.getContentPane().setBackground(new Color(255, 240, 245));
Image icon1 = Toolkit.getDefaultToolkit().getImage("C:/Users/becca/eclipse-workspace/OOPM_Mini_Project/src/images/android-icon-36x36.png");  
lf.setIconImage(icon1);  
lf.setTitle("Login Form");
lf.getContentPane().setLayout(null);

/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyyy HH:mm:ss");  
LocalDateTime now = LocalDateTime.now();
JLabel lblNewLabel_4 = new JLabel(dtf.format(now));
lblNewLabel_4.setBounds(670, 157, 106, 61);
lf.getContentPane().add(lblNewLabel_4);*/
  


lf.setVisible(true);
lf.setBounds(10,10,800,650);
lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




}
}