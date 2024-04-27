import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Bill implements Publisher {
	private static ArrayList<Observer> observers = new ArrayList<>();
	private int ID;
	private float totalAmount;
	private LocalDateTime date;
	private String custName;
	private String custAddress;
	private static GregorianCalendar dueDate = new GregorianCalendar(2024, 5, 1);
	
	public Bill() {
	}
	
	public Bill(int ID, float totalAmount, LocalDateTime date, String custName, String custAddress) {
		this.ID = ID;
		this.totalAmount = totalAmount;
		this.date = date;
		this.custName = custName;
		this.custAddress = custAddress;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public static GregorianCalendar getDueDate() {
		return dueDate;
	}

	public static void setDueDate(GregorianCalendar dueDate) {
		Bill.dueDate = dueDate;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void sendBillingAlert(String message) {
		ArrayList<Customer> customers = (Customer.getCustomersFromDB("subscriptionStatus = true"));
		for (Customer c : customers) {
			addObserver(c);
		}
		for (Observer observer : observers) {
			observer.updateObserver(message);
		}
	}

	@Override
	public void checkDueDate() {
		GregorianCalendar currentDate = new GregorianCalendar();
		
		// This is a fake scenario for testing renew subscription
		if (true) {
			// call send billing alert that alerts all observers of a pending bill
			sendBillingAlert("You have Pending Fees to pay!");
			// resets the new due date to the one of next month
			dueDate.add((GregorianCalendar.MONTH), 1);
		}
		
		// This is the real scenario
		//		if (dueDate.get((GregorianCalendar.DAY_OF_MONTH)) == currentDate.get((GregorianCalendar.DAY_OF_MONTH))) {
		//			// call send billing alert that alerts all observers of a pending bill
		//			sendBillingAlert("You have Pending Fees to pay!");
		//			// resets the new due date to the one of next month
		//			dueDate.add((GregorianCalendar.MONTH), 1);
		//		}
	}

	public void calculateBill() {
		
		// will carry the result from the database function with all the customers that are subscribed
		ArrayList<Customer> allCustomers = Customer.getCustomersFromDB("subscriptionStatus = true AND custCategory " +
				"IS NOT NULL");
		for (Customer customer : allCustomers) {
			// retrieving the tax calculation per each category
			float customerTax = customer.getCategory().calculateTax();
			// retrieving the usage
			float customerUsage = customer.getMeterReader().calculateUsage(customer.getID());
			// setting the bills total amount
			float billtotalAmount = customerUsage * customerTax;
			// creating the bill in the database
			try{
				Connection connection = DatabaseSingleton.getInstance().getConnection();
				Statement statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO bill(totalAmount, date, customer_id) values(" + billtotalAmount + ", '" + LocalDateTime.now() + "', " + customer.getID() + ")");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error adding bill to database");
			}
			
			// set the outstanding fees for the customer ie: adding to the already pending fees in case customer didnt pay
			customer.setOutstandingFees(billtotalAmount + customer.getOutstandingFees());
			// updating the outstanding fees of the customer
			// creating the bill in the database
			try{
				Connection connection = DatabaseSingleton.getInstance().getConnection();
				Statement statement = connection.createStatement();
				statement.executeUpdate("UPDATE customer set outstandingFees = " + customer.getOutstandingFees() + " WHERE id = " + customer.getID());
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error updating outstanding fees in customer in database");
			}
		}
		// checking if it's the due date to send the billing alert
		checkDueDate();
	}
	
	public static ArrayList<Bill> getBillsFromDB(int id) {
		ArrayList<Bill> bills = new ArrayList<>();
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bill WHERE customer_id = " + id);
			while (result.next()) {
				int sqlID = result.getInt("id");
				float sqlTotalAmount = result.getFloat("totalAmount");
				
				// parses string to LocalDateTime
				LocalDateTime sqlDate = result.getTimestamp("date").toLocalDateTime();
				
				int custID = result.getInt("customer_id");
				Customer customer = Customer.getCustomersFromDB("id = " + custID).get(0);
				String sqlCustName= customer.getName();
				String sqlCustAddress = customer.getAddress();
				
				Bill bill = new Bill(sqlID, sqlTotalAmount, sqlDate, sqlCustName, sqlCustAddress);
				bills.add(bill);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving bill from database");
		}
		return bills;
	}
}
