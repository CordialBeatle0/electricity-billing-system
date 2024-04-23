import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Account {
	private int ID;
	private String username;
	private String password;

	public Account(int ID, String username, String password) {
		this.ID = ID;
		this.username = username;
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Account{" +
				"ID=" + ID +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
	
	public void updateAccount(String username, String password) {
		//TODO: Add implementation
	}

	public static Customer custLogin(String username, String password) {
		ArrayList<Customer> customers = Customer.getCustomersFromDB("");
		for (Customer customer : customers) {
			Account account = customer.getAccount();
			if (account.username.equals(username) && account.password.equals(password)) {
				return customer;
			}
		}
		return null;
	}

	public static Employee empLogin(String username, String password) {
		ArrayList<Employee> employees = Employee.getEmployeesFromDB("");
		for (Employee employee : employees) {
			Account account = employee.getAccount();
			if (account.username.equals(username) && account.password.equals(password)) {
				return employee;
			}
		}
		return null;
	}
	
	public static Account getAccountFromDB(String id) {
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM account WHERE id = " + id);
			int sqlID = result.getInt("id");
			String sqlUsername = result.getString("username");
			String sqlPassword = result.getString("password");
			
			return new Account(sqlID, sqlUsername, sqlPassword);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving account from database");
		}
		return null;
	}
}
