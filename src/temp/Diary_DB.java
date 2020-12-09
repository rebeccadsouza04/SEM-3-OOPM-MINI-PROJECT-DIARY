package temp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date; //to be able to use DATE as a data type

public class Diary_DB {
	
	private static final String URL = "jdbc:mysql://localhost:3306/diary";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mypass";

	private Connection connection = null;
	private PreparedStatement selectAllEntries = null;
	private PreparedStatement insertEntry = null;
	
	public Diary_DB()
	{
		try
		{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database
			selectAllEntries = connection.prepareStatement("SELECT * FROM diary"); // Prepare the select query that gets all entries from the database or Diary table
			
			
			insertEntry = connection.prepareStatement("INSERT INTO diary VALUES (?,?,?)");

		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public ArrayList<Diary_Entries> getAllEntries()
	{
		ArrayList<Diary_Entries> results = null;
		ResultSet resultSet = null;
		
		try
		{
			resultSet = selectAllEntries.executeQuery(); 
			results = new ArrayList<Diary_Entries>();
		
			while(resultSet.next()) 
			{
			
				results.add(new Diary_Entries(
				resultSet.getDate("Current_day"), 
				resultSet.getString("Title"), 
				resultSet.getString("Entry"))); 
			}
		} 
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
		
		return results;
	} 
	public void addDiary_Entries(Date current_day, String title, String entry)
	{
		try
		{
			
			insertEntry.setDate(1, current_day);
			insertEntry.setString(2, title);
			insertEntry.setString(3, entry);
			
			
			int result = insertEntry.executeUpdate(); 
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}	
	}

}