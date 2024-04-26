public class Cash implements Payment {
	public Cash() {
	}

	@Override
	public void makePayment(Customer customer, float amount) {
		//create a new Request
		Request req = new Request(customer.getID(), customer.getName(),"Collect Cash Payment", customer.getAddress(), java.time.LocalDate.now());
		//add request to DB
		req.addRequesttoDB();
		req.requestHomeService();
	}
}
