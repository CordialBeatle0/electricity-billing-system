import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Bill implements Publisher {
	private static ArrayList<Observer> observers;
	private int ID;
	private float totalAmount;
	private Date date;
	private String custName;
	private String custAddress;
	private static GregorianCalendar dueDate = new GregorianCalendar(2024, 5, 1);
	
	public Bill(int ID, float totalAmount, Date date, String custName, String custAddress) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		this.dueDate = dueDate;
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
		for (Observer myobeserveres : observers) {
			myobeserveres.updateObserver(message);
		}
	}

	@Override
	public boolean checkDueDate() {
		GregorianCalendar currentDate = new GregorianCalendar();
		
		if (dueDate.get((GregorianCalendar.DAY_OF_MONTH)) == currentDate.get((GregorianCalendar.DAY_OF_MONTH))) {
			// resets the new due date to the one of next month
			dueDate.add((GregorianCalendar.MONTH), 1);;
			// call send billing alert that alerts all observers of a pending bill
			sendBillingAlert("You have Pending Fees to pay!");
			return true;
		}
		else
			return false;
	}

	public void calculateBill(float amount, Customer customer) {
		//TODO: still needs implemetation 
		// check the logic for finding the category from observers arraylist
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select custCategory from Customer where subscriptionStatus = true");
			
        if (rs.next()) {
			// Retrieve the value from the result set
			
            
        }
			
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
	}

	public ArrayList<Bill> viewBillingHistory() {
		//TODO: Add implementation
	}

	public ArrayList<Bill> viewAllCustomerBills() {
		//TODO: Add implementation
	}
	
	public static ArrayList<Bill> getBillsFromDB(String id) {
		ArrayList<Bill> bills = new ArrayList<>();
		try {
			connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bill WHERE customer_id = " + id);
			while (result.next()) {
				int sqlID = result.getInt("id");
				float sqlTotalAmount = result.getFloat("totalAmount");
				
				// translates string to long and from long to date (built in)
				String date_String = result.getString("date");
				long date_long = Long.getLong(date_String);
				Date sqlDate = new Date(date_long);
				
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
