import java.util.ArrayList;

public class Admin extends Employee {
	private ArrayList<Customer> uncategorizedCustomers;

	public Admin(int ID, String name, int age, String phoneNumber, char gender, float salary, Account account) {
		super(ID, name, age, phoneNumber, gender, salary, account);
		uncategorizedCustomers = new ArrayList<>();
	}

	

	public void removeUncategorizedCustomer(Customer customer) {
		//TODO: Add implementation
	}

	public void manageCustStatus(Customer customer) {
		//TODO: Add implementation
	}

	public void manageCustCategory(Customer customer) {
		//TODO: Add implementation
	}

	@Override
	public void handle(Inquiry inquiry) {
		//TODO: Add implementation
	}
}
