package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String HOST = "localhost:3306";
	private final String DATABASE = "projectbad";
	private final String URL = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet res;
	private static Connect instance;
	
	public Connect() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	 public static Connect getInstance() {
	        if (instance == null) {
	            return new Connect();
	        }
	        return instance;
	 }
	
	 public ResultSet execQuery(String query) {
	        try {
	            preparedStatement = connection.prepareStatement(query);
	            return preparedStatement.executeQuery();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Error executing query: " + e.getMessage());
	        }
	        return null;
	    }
	 
	public void execUpdate(String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error executing update: " + e.getMessage());
        }
    }
	 
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return connection;
	}
	public void insertUserData(String userId, String username, String password, String phoneNumber,String email, String role) {
        String insertQuery = "INSERT INTO user (UserId, Username, Password, PhoneNumber, Email, Role) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, role);
     

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error executing update: " + e.getMessage());
        }
    }
	
}
