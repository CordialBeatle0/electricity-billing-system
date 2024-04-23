import java.util.ArrayList;

public class Customer implements Observer {
	private ArrayList<String> notifications;
	private ArrayList<Bill> billHistory;
	private ArrayList<InquiryROI> inquiryHistory;
	private int ID;
	private String name;
	private String address;
	private String phoneNumber;
	private int cardNumber; //TODO: remove mee
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
	

}
