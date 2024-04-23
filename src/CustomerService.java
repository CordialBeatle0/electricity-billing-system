public class CustomerService extends Employee {
	public CustomerService(int ID, String name, int age, String address, String phoneNumber, char gender, float salary, Account account) {
		super(ID, name, age,address, phoneNumber, gender, salary, account);
	}

	@Override
	public void handle(Inquiry inquiry) {
		//TODO: Add implementation
	}
}
