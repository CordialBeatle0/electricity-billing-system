
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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
	private static GregorianCalendar dueDate= new GregorianCalendar(2024, 5, 1);


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

	public void calculateBill(float amount) {
		// will carry the result from the databse function with all the customers that are subscribed
		ArrayList<Customer> allCustomers = Customer.getCustomersFromDB("subscriptionStatus = true");
		for (Customer customer : allCustomers) {
			// retriving the tax calculatio per each category
			float customerTax = customer.getCategory().calculateTax();
			// retriving the usage
			float customerUsage= customer.getMeterReader().calculateUsage();
			// setting the bills total amount
			setTotalAmount(customerUsage * customerTax);
			// set the outstanding fees for the customer ie: adding to the already pending fees incase customer didnt pay
			customer.setOutstandingFees(totalAmount+customer.getOutstandingFees());
		}
		// checking if its the due date to send the billing alert
		checkDueDate();
	 
	}

	public ArrayList<Bill> viewBillingHistory(int custID) {
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
			// TODO: write exception GUI joptionpayne
		}
		return allCustomerHistory;
	}

	public ArrayList<Bill> viewAllCustomerBills() {
		//TODO: Add implementation
	}
}
