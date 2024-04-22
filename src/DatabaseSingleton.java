import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
	// Static instance variable to hold the single instance of the class
	private static DatabaseSingleton instance;

	// Database connection variables
	private Connection connection;
	private final String url = "jdbc:mysql://localhost:3306/electricity_billing_db"; // change the dbName to ours
	private final String username = "username"; // Change to your MySQL username
	private final String password = "password"; // Change to your MySQL password

	// Private constructor to prevent instantiation
	private DatabaseSingleton() {
		try {
			// create database connection
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace(); // momkn ne8yr dyy low 3ayzen
		}
	}

	// Static method to get the single instance of the class
	// This method is synchronized to prevent multiple threads from creating multiple instances
	public static synchronized DatabaseSingleton getInstance() {
		if (instance == null) {
			instance = new DatabaseSingleton();
		}
		return instance;
	}

	// Method to get database connection
	public Connection getConnection() {
		return connection;
	}
}