import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer implements Observer {
	private ArrayList<String> notifications;
	private ArrayList<Bill> billHistory;
	private ArrayList<InquiryROI> inquiryHistory;
	private int ID;
	private String name;
	private String address;
	private String phoneNumber;
	private int cardNumber;
	private boolean isTimeToPay;
	private Category category;
	private MeterReader meterReader;
	private float outstandingFees;
	private Account account;
	private Payment paymentType;
	private Subscription subscription;

	// TODO: Add constructor(s) and add these lines in all constructors
	// notifications = new ArrayList<>();
	// billHistory = new ArrayList<>();
	// inquiryHistory = new ArrayList<>();
	
	
	public Customer(int ID, String name, String address, String phoneNumber, int cardNumber, boolean isTimeToPay, Category category, MeterReader meterReader, float outstandingFees, Account account, Subscription subscription) {
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.isTimeToPay = isTimeToPay;
		this.category = category;
		this.meterReader = meterReader;
		this.outstandingFees = outstandingFees;
		this.account = account;
		this.subscription = subscription;
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

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public boolean isTimeToPay() {
		return isTimeToPay;
	}

	public void setTimeToPay(boolean timeToPay) {
		isTimeToPay = timeToPay;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public MeterReader getMeterReader() {
		return meterReader;
	}

	public void setMeterReader(MeterReader meterReader) {
		this.meterReader = meterReader;
	}

	public float getOutstandingFees() {
		return outstandingFees;
	}

	public void setOutstandingFees(float outstandingFees) {
		this.outstandingFees = outstandingFees;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Payment getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Payment paymentType) {
		this.paymentType = paymentType;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public void addNotification(String message) {
		notifications.add(message);
	}

	public void addBill(Bill bill) {
		billHistory.add(bill);
	}

	public void addInquiry(Inquiry inquiry) {
		inquiryHistory.add(inquiry);
	}

	@Override
	public void updateObserver(String message) {
		// probably just add the message to the notifications
		notifications.add(message);
	}
	
	/**
	 *
	 * @param condition if empty, selects all customers in the database <br>
	 *                     otherwise should be the exact statement written after a WHERE clause
	 * @return ArrayList of all customers retrieved from the query
	 */
	public static ArrayList<Customer> getCustomersFromDB(String condition) {
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result;
			// if condition is empty get all customers
			if (condition.isEmpty()){
				result = statement.executeQuery("SELECT * FROM customer");
			}
			// if condition exists
			else {
				result = statement.executeQuery("SELECT * FROM customer WHERE " + condition);
			}
			while (result.next()) {
				int sqlID = result.getInt("id");
				String sqlName = result.getString("name");
				String sqlAddress = result.getString("address");
				String sqlPhoneNumber = result.getString("phone");
				int sqlCardNumber = result.getInt("cardNumber");
				boolean sqlIsTimeToPay = result.getBoolean("isTimeToPay");
				
				// category
				String category = result.getString("custCategory");
				Category sqlCategory = switch (category) {
					case "Individual" -> new Individual();
					case "Company" -> new Company();
					case "Factory" -> new Factory();
					default -> null;
				};
				
				// meter reader
				int meterReaderID_INT = result.getInt("meterReader_id");
				String meterReaderID_String = Integer.toString(meterReaderID_INT);
				MeterReader sqlMeterReader = MeterReader.getMeterReaderFromDB(meterReaderID_String);
				
				float sqlOutstandingFees = result.getFloat("outstandingFees");
				
				// account
				int accountID_INT = result.getInt("account_id");
				String accountID_String = Integer.toString(accountID_INT);
				Account sqlAccount = Account.getAccountFromDB(accountID_String);
				
				// subscription
				boolean subscriptionStatus_Bool = result.getBoolean("subscriptionStatus");
				Subscription sqlSubscription = new Subscription(subscriptionStatus_Bool);
				
				customers.add(new Customer(sqlID, sqlName, sqlAddress, sqlPhoneNumber, sqlCardNumber, sqlIsTimeToPay, sqlCategory, sqlMeterReader, sqlOutstandingFees, sqlAccount, sqlSubscription));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving customer from database");
		}
		return customers;
	}
}
