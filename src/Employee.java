import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Employee {
	private ArrayList<Inquiry> assignedInquiries;
	private int ID;
	private String name;
	private int age;
	private String address;
	private String phoneNumber;
	private char gender;
	private float salary;
	private Account account;

	public Employee() {
	}
        
	public Employee(int ID, String name, int age, String address, String phoneNumber, char gender, float salary, Account account) {
		assignedInquiries = new ArrayList<>();
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.salary = salary;
		this.account = account;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void addInquiry(Inquiry inquiry) {
		assignedInquiries.add(inquiry);
	}

	public void removeInquiry(Inquiry inquiry) {
		assignedInquiries.remove(inquiry);
	}

	public void setHandler(Employee employee) {
		//TODO: Add implementation
	}

	public abstract void handle(Inquiry inquiry);

	public void assignEmployee(Inquiry inquiry) {
		//TODO: Add implementation
	}
	
	/**
	 *
	 * @param condition if empty, selects all customers in the database <br>
	 *                     otherwise should be the exact statement written after a WHERE clause
	 * @return ArrayList of all employees retrieved from the query
	 */
	public static ArrayList<Employee> getEmployeesFromDB(String condition) {
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM employee WHERE " + condition);
			while (result.next()) {
				int sqlID = result.getInt("id");
				String employeeType = result.getString("employeeType");
				String sqlName = result.getString("name");
				int sqlAge = result.getInt("age");
				String sqlPhoneNumber = result.getString("phoneNumber");
				char sqlGender = result.getString("gender").charAt(0);
				float sqlSalary = result.getFloat("salary");
				
				// account
				int accountID_INT = result.getInt("account_id");
				String accountID_String = Integer.toString(accountID_INT);
				Account sqlAccount = Account.getAccountFromDB(accountID_String);
				
				Employee employee;
				
				String sqlAssignedLocation = null;
				if (employeeType.equals("Technician")) {
					sqlAssignedLocation = result.getString("technicianAssignedLocation");
					employee = new CustomerService(sqlID, sqlName, sqlAge, sqlPhoneNumber, sqlGender, sqlSalary, sqlAccount);
				} else if (employeeType.equals("Admin")) {
					employee = new Admin(sqlID, sqlName, sqlAge, sqlPhoneNumber, sqlGender, sqlSalary, sqlAccount);
				} else {
					employee = new CustomerService(sqlID, sqlName, sqlAge, sqlPhoneNumber, sqlGender, sqlSalary, sqlAccount);
				}
				employees.add(employee);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving customer from database");
		}
		return employees;
	}
}
