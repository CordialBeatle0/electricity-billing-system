import java.sql.Connection;
import java.util.ArrayList;

public class Inquiry implements InquiryROI {
	private String question;
	private String response;
	private int ID;
	private String custCategory;
	private String custName;
	private int custID;
	private String employeeName;
	private String employeeType;
	private String date;

	public Inquiry(String question, int ID, String custCategory, String custName, String date) {
		this.question = question;
		this.ID = ID;
		this.custCategory = custCategory;
		this.custName = custName;
		this.date = date;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getCustCategory() {
		return custCategory;
	}

	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Inquiry{" +
				"question='" + question + '\'' +
				", response='" + response + '\'' +
				", ID=" + ID +
				", custCategory='" + custCategory + '\'' +
				", custName='" + custName + '\'' +
				", custID=" + custID +
				", employeeName='" + employeeName + '\'' +
				", employeeType='" + employeeType + '\'' +
				", date='" + date + '\'' +
				'}';
	}

	@Override
	public Inquiry viewInquiry() {
		//TODO: Add implementation
	}

	@Override
	public void addInquiry() {
		//TODO: Add implementation
		// hat5od el inquiry mn el GUI then add it to the db table inquiry
		DatabaseSingleton db = DatabaseSingleton.getInstance();
		Connection conn = db.getConnection();

		Employee emp = new CustomerService();
		emp.handle(this);
	}

	public void respondToInquiry(String response, String employeeName) {
		//TODO: Add implementation
	}

	public ArrayList<Inquiry> inquiryHistory() {
		//TODO: Add implementation
	}
}
