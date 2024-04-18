import java.util.ArrayList;
import java.util.Date;

public class Bill implements Publisher {
	private static ArrayList<Observer> observers;
	private int ID;
	private float totalAmount;
	private Date date;
	private String custName;
	private String custAddress;
	private Date dueDate;

	public Bill(int ID, float totalAmount, Date date, String custName, String custAddress, Date dueDate) {
		this.ID = ID;
		this.totalAmount = totalAmount;
		this.date = date;
		this.custName = custName;
		this.custAddress = custAddress;
		this.dueDate = dueDate;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
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
		//TODO: Add implementation
	}

	@Override
	public boolean checkDueDate() {
		//TODO: Add implementation
	}

	public void calculateBill(float amount, Customer customer) {
		//TODO: Add implementation
	}

	public ArrayList<Bill> viewBillingHistory() {
		//TODO: Add implementation
	}

	public ArrayList<Bill> viewAllCustomerBills() {
		//TODO: Add implementation
	}
}
