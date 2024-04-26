import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Cash implements Payment {
	public Cash() {
	}

	@Override
	public void makePayment(Customer customer, float amount) {
		Request req = new Request(customer.getID(), customer.getName(),"Collect Cash Payment", customer.getAddress(), LocalDateTime.now());
		//add request to DB
		req.addRequesttoDB();
		req.requestHomeService();
	}
}
