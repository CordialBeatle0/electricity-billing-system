import java.util.ArrayList;

public class Inquiry implements InquiryROI {
	private String question;
	private String response;
	private int ID;
	private String custCategory;
	private String custName;
	private String employeeName;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public Inquiry viewInquiry() {
		//TODO: Add implementation
	}

	@Override
	public void addInquiry() {
		//TODO: Add implementation
	}

	public void respondToInquiry(String response, String employeeName) {
		//TODO: Add implementation
	}

	public ArrayList<Inquiry> inquiryHistory() {
		//TODO: Add implementation
	}
}
