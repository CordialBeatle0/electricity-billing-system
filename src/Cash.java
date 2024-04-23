public class Cash implements Payment {
	public Cash() {
	}

	@Override
	public void makePayment(Customer customer, float amount) {
                //create a new Request
                Request req = new Request(1,customer.getID(), customer.getName(),"Cash Payment", customer.getAddress(),java.time.LocalDate.now());
                //TODO: DATABASE add request to DB
                req.requestHomeService();
                
	}
}
