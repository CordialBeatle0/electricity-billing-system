import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
		for (Observer myobeserveres : observers) {
			myobeserveres.updateObserver(message);
		}
	}

	@Override
	public void checkDueDate() {
		GregorianCalendar currentDate = new GregorianCalendar();
	
		if (dueDate.get((GregorianCalendar.DAY_OF_MONTH)) == currentDate.get((GregorianCalendar.DAY_OF_MONTH))) {
			// call send billing alert that alerts all observers of a pending bill
			sendBillingAlert("You have Pending Fees to pay!");
			// resets the new due date to the one of next month
			dueDate.add((GregorianCalendar.MONTH), 1);;
		}
	}

	public static void calculateBill() {

		// will carry the result from the databse function with all the customers that are subscribed
		ArrayList<Customer> allCustomers = Customer.getCustomersFromDB("subscriptionStatus = true");
		for (Customer customer : allCustomers) {
			// retriving the tax calculatio per each category
			float customerTax = customer.getCategory().calculateTax();
			// retriving the usage
			float customerUsage= customer.getMeterReader().calculateUsage();
			// setting the bills total amount
			float billtotalAmount = customerUsage * customerTax;
			// creating the bill in the database
			try{
				Connection connection = DatabaseSingleton.getInstance().getConnection();
				Statement statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO bill(totalAmount, date, customer_id) values("+billtotalAmount+",'"+LocalDate.now()+"',"+customer.getID()+")");
			}
			catch (Exception e) {
				// TODO: write joptionpayne
                                
			}

			// set the outstanding fees for the customer ie: adding to the already pending fees incase customer didnt pay
			customer.setOutstandingFees(billtotalAmount + customer.getOutstandingFees());
			// updating the outstanding fees of the custmer
			// creating the bill in the database
			try{
				Connection connection = DatabaseSingleton.getInstance().getConnection();
				Statement statement = connection.createStatement();
				statement.executeUpdate("UPDATE customer set outstandingFees="+ customer.getOutstandingFees()+" WHERE id=' "+customer.getID()+"'");
				
			}
			catch (Exception e) {
				// TODO: write joptionpayne
			}


			
		}
		// checking if its the due date to send the billing alert
		checkDueDate();
	 
	}

	public ArrayList<Bill> viewBillingHistory() {
		ArrayList<Bill> allCustomerHistory = new ArrayList<>();
		try{
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bill WHERE customer_id = " + custID);
			while (result.next()) {
				int sqlcustID = result.getInt("customer_id");
				int sqlbillId = result.getInt("id");
				float sqltotalamount = result.getFloat("totalAmount");
				//TODO : STEAL LONG CONVERSION
				String sqldate = result.getString("date");
				Customer mycustomer= Customer.getCustomersFromDB(custID).get(0);
				String sqlcustAdress = mycustomer.getAddress();
				Bill retrivalbill = new Bill(sqlbillId, sqltotalamount, date, sqldate, sqlcustAdress);
				allCustomerHistory.add(retrivalbill);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving bill from database");
		}
		return allCustomerHistory;
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
