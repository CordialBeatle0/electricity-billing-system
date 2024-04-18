import java.util.ArrayList;

public class Technician extends Employee {
	private static int maxCapacity;
	private String assignedLocation;
	private ArrayList<Request> requestedServices;

	public Technician(int ID, String name, int age, String phoneNumber, char gender, float salary, Account account, String assignedLocation) {
		super(ID, name, age, phoneNumber, gender, salary, account);
		this.assignedLocation = assignedLocation;
		requestedServices = new ArrayList<>();
	}

	public static int getMaxCapacity() {
		return maxCapacity;
	}

	public static void setMaxCapacity(int maxCapacity) {
		Technician.maxCapacity = maxCapacity;
	}

	public String getAssignedLocation() {
		return assignedLocation;
	}

	public void setAssignedLocation(String assignedLocation) {
		this.assignedLocation = assignedLocation;
	}

	public void addRequest(Request request) {
		requestedServices.add(request);
	}

	public void removeRequest(Request request) {
		requestedServices.remove(request);
	}

	public void assignTechnician(Request request) {
		//TODO: Add implementation
	}

	@Override
	public void handle(Inquiry inquiry) {
		//TODO: Add implementation
	}

	public void confirmCashPayment(Request request) {
		//TODO: Add implementation
	}
}
