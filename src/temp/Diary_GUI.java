package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit; 

public class Diary_GUI extends JFrame  {
	
	final static int MAX_QTY = 100; 

	private Diary_DB diary_DB;
	private ArrayList<Diary_Entries> allEntries;
	private Diary_Entries currentEntries;
	
	static JTable tableDiary; 
	static JButton btnAddEntry; 
	private JButton btnDeleteEntry;
	private JCheckBox checkBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public Diary_GUI(){
		super("My Diary");

		diary_DB = new Diary_DB();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0,0,862,497); 
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 186, 572, 155);
		getContentPane().add(scrollPane);
		
		
		tableDiary = new JTable();
		tableDiary.setBackground(new Color(255, 240, 245));
		scrollPane.setViewportView(tableDiary);
		tableDiary.setRowSelectionAllowed(true);
		tableDiary.setModel(new DefaultTableModel(
			new Object [MAX_QTY][3],  
			new String[] {"Date", "Title", "Entry"}
		));
		
		tableDiary.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		checkBox = new JCheckBox("New check box");
		scrollPane.setColumnHeaderView(checkBox);
		
		btnAddEntry = new JButton("Add Entry");
		btnAddEntry.setBackground(new Color(255, 255, 204));
		btnAddEntry.setIcon(new ImageIcon(Diary_GUI.class.getResource("/images/android-icon-36x36 plus.png")));
		btnAddEntry.setBounds(208, 134, 158, 41);
		getContentPane().add(btnAddEntry);
		
		populateTable();
		
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddEntry.addActionListener(commandHandler);
		
		btnDeleteEntry = new JButton("Delete Entry");
		btnDeleteEntry.setBackground(new Color(255, 215, 0));
		btnDeleteEntry.setBounds(517, 134, 140, 34);
		getContentPane().add(btnDeleteEntry);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Diary_GUI.class.getResource("/images/android-icon-72x72.png")));
		lblNewLabel.setBounds(404, 34, 72, 78);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBackground(new Color(102, 255, 153));
		btnNewButton.setBounds(386, 371, 101, 55);
		getContentPane().add(btnNewButton);
		 
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Diary_GUI.class.getResource("/images/xt (15).png")));
		lblNewLabel_1.setBounds(0, 0, 848, 460);
		getContentPane().add(lblNewLabel_1);
		
		tableDiary.addMouseListener(new java.awt.event.MouseAdapter() { //For Selecting and Reading Cell Entry Information

			public void mouseClicked(java.awt.event.MouseEvent e) {

				int row=tableDiary.rowAtPoint(e.getPoint());

				int col= tableDiary.columnAtPoint(e.getPoint());
				
				JOptionPane.showMessageDialog(null, "Info:  " + "" + tableDiary.getValueAt(row,col).toString());
			}

        });
				
		
		btnDeleteEntry.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel myPanel = new JPanel();
				int result = JOptionPane.showConfirmDialog(null, myPanel, "You want to delete this entry?", JOptionPane.OK_CANCEL_OPTION);
				
				if (result == JOptionPane.OK_OPTION) {
					try {
					
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary", "root", "mypass");
						int row = tableDiary.getSelectedRow();
						String value = (tableDiary.getModel().getValueAt(row,0).toString());
						String delete_query = "DELETE FROM diary WHERE current_day = ('" + value + "')";
						PreparedStatement ps = conn.prepareStatement(delete_query);
						ps.executeUpdate(); //updates in database
						DefaultTableModel model = (DefaultTableModel)tableDiary.getModel();
						model.removeRow(row); //updates in GUI table
						
						
					}
					catch (SQLException sqlException)
					{
						sqlException.printStackTrace();
					}


				}
				
			}
		});
		
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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

				
			}});
	}
	

	private void populateTable(){
		
		allEntries = diary_DB.getAllEntries();
		
		for (int row=0; row<allEntries.size(); row++){
			
			currentEntries= allEntries.get(row);
			
			tableDiary.setValueAt(currentEntries.getCurrent_day(), row, 0);  
			tableDiary.setValueAt(currentEntries.getTitle(), row, 1);  
			tableDiary.setValueAt(currentEntries.getEntry(), row, 2); // Setting values to be added in columns to same row
		}
		
	
	}

	private class MyEventHandler implements ActionListener {
		public void actionPerformed (ActionEvent myEntry) {
			if (myEntry.getSource() == btnAddEntry){
				if (allEntries.size() < MAX_QTY){ // If current amount of Entries in the database is smaller than MAX_QTY entry is added
					getNewEntryFromUser();
					populateTable();
				}
				else{
					JOptionPane.showMessageDialog(null, "Your Diary is full and the entry can not be saved", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
		}
	}
	
	private void getNewEntryFromUser() {
		dispose();
		
		JLabel l = new JLabel();
		
		JTextField current_date = new JTextField(10);
	    JTextField title = new JTextField(10);
	    JTextArea entry = new JTextArea(10,30);
	    // Code added here
 
	    JPanel myPanel = new JPanel();
	    
	    myPanel.add(new JLabel("Date"));
	    myPanel.add(current_date);
	    
	    myPanel.add(new JLabel("Title"));
	    myPanel.add(title);

	    myPanel.add(new JLabel("Write Entry"));
	    myPanel.add(entry);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Write a new Entry in your Diary", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {
			
	    	diary_DB.addDiary_Entries(Date.valueOf(current_date.getText()), title.getText(), entry.getText());  
	    	Diary_GUI frame = new Diary_GUI();
			frame.setVisible(true);
	    }
	    else {
	    	Diary_GUI frame = new Diary_GUI();
			frame.setVisible(true);
	    }
	}

	private static void showWellcomeDialog() {
		JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Welcome to your Diary! Press OK to Continue"));
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Diary", JOptionPane.DEFAULT_OPTION);

	}
	
	
	public static void main(String[] args) { 
		showWellcomeDialog();
		Diary_GUI frame = new Diary_GUI();
		frame.setVisible(true);
		Image icon1 = Toolkit.getDefaultToolkit().getImage("C:/Users/becca/eclipse-workspace/OOPM_Mini_Project/src/images/android-icon-36x36.png");  
		frame.setIconImage(icon1);  
		frame.setTitle("Diary");
	}


}