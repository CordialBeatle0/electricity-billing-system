public class Request {
	private int ID;
	private int custID;
	private String custName;
	private String requestType;
	private String location;
	private String date;

	public Request(int ID, int custID, String custName, String requestType, String location, String date) {
		this.ID = ID;
		this.custID = custID;
		this.custName = custName;
		this.requestType = requestType;
		this.location = location;
		this.date = date;
	}

	public int getID() {
		return ID;
	}

	public int getCustID() {
		return custID;
	}

	public String getCustName() {
		return custName;
	}

	public String getRequestType() {
		return requestType;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}

	public void addRequest() {
		//TODO: Add implementation
	}

	public Request viewRequest() {
		//TODO: Add implementation
	}

	public void requestHomeService() {
		//TODO: Add implementation
	}
}
