import java.util.ArrayList;

public class Admin extends Employee {
	
	public Admin(int ID, String name, int age, String address, String phoneNumber, char gender, float salary,
			Account account) {
		super(ID, name, age, address, phoneNumber, gender, salary, account);
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
