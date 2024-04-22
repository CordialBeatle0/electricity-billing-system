import java.util.ArrayList;

public abstract class Employee {
	private ArrayList<Inquiry> assignedInquiries;
	private int ID;
	private String name;
	private int age;
	private String phoneNumber;
	private char gender;
	private float salary;
	private Account account;

	public Employee nextEmp;

	public Employee(int ID, String name, int age, String phoneNumber, char gender, float salary, Account account) {
		assignedInquiries = new ArrayList<>();
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.salary = salary;
		this.account = account;
	}

	public Employee() {
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
}
