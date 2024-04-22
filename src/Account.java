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

	public void updateAccount(String username, String password) {
		//TODO: Add implementation
	}

	public static Customer custLogin(String username, String password) {
		ArrayList<Customer> customers;
		//TODO: Get all customer accounts from database and put it in customers
		for (Customer customer : customers) {
			Account account = customer.getAccount();
			if (account.username.equals(username) && account.password.equals(password)) {
				return customer;
			}
		}
		return null;
	}

	public static Employee empLogin(String username, String password) {
		ArrayList<Employee> employees;
		//TODO: Get all customer accounts from database and put it in customers
		for (Employee employee : employees) {
			Account account = employee.getAccount();
			if (account.username.equals(username) && account.password.equals(password)) {
				return employee;
			}
		}
		return null;
	}
}
