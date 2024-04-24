import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public Inquiry() {
    }

    public Inquiry(String question, String custCategory, String custName, int custID, String date) {
        this.question = question;
        this.custCategory = custCategory;
        this.custName = custName;
        this.custID = custID;
        this.date = date;
    }

    public Inquiry(String question, int ID, String custCategory, String custName, String date) {
        this.question = question;
        this.ID = ID;
        this.custCategory = custCategory;
        this.custName = custName;
        this.date = date;
    }

    public Inquiry(String question, String custCategory, String custName, int custID, String employeeName, String employeeType, String date) {
        this.question = question;
        this.custCategory = custCategory;
        this.custName = custName;
        this.custID = custID;
        this.employeeName = employeeName;
        this.employeeType = employeeType;
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
    public ArrayList<Inquiry> viewInquiriesByID(int custID, String empType) {
        ArrayList<Inquiry> inquiries = new ArrayList<>();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        String custCategoryy = "";
        if (empType.equals("CustomerService")) {
            custCategoryy = "Individual";
        } else if (empType.equals("Technician")) {
            custCategoryy = "Factory";
        } else if (empType.equals("Admin")) {
            custCategoryy = "Company";
        }

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, question, custCategory, custName, date  FROM inquiry, Customer WHERE Inquiry(id) = Customer(id) AND custCategory = " + custCategoryy;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Inquiry inquiry = new Inquiry(
                        rs.getString("question"),
                        rs.getString("custCategory"),
                        rs.getString("name"),
                        rs.getInt("id"), // Assuming custID corresponds to the ID of the customer
//                        rs.getString("employeeName"),
//                        rs.getString("employeeType"),
                        rs.getString("date")
                );
                inquiries.add(inquiry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inquiries;
    }

    // this method is used to view all inquiries in the database by the Admin only
    static public ArrayList<Inquiry> viewInquiries(String empType) {
        ArrayList<Inquiry> inquiries = new ArrayList<>();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        String custCategoryy = "";
        if (empType.equals("CustomerService")) {
            custCategoryy = "Individual";
        } else if (empType.equals("Technician")) {
            custCategoryy = "Factory";
        } else if (empType.equals("Admin")) {
            custCategoryy = "Company";
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, question, custCategory, custName, date  FROM inquiry, Customer WHERE custCategory = " + custCategoryy;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Inquiry inquiry = new Inquiry(
                        rs.getString("question"),
                        rs.getString("custCategory"),
                        rs.getString("name"),
                        rs.getInt("id"), // Assuming custID corresponds to the ID of the customer
//                        rs.getString("employeeName"),
//                        rs.getString("employeeType"),
                        rs.getString("date")
                );
                inquiries.add(inquiry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inquiries;
    }


    @Override
    public void addInquiry() {
        // hat5od el inquiry mn el GUI then add it to the db table inquiry
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            // Create the SQL query using values from the Inquiry object
            String query = "INSERT INTO inquiry (question, custCategory, custName, custID, employeeName, employeeType, date) " +
                    "VALUES ('" + this.getQuestion() + "', '" + this.getCustCategory() + "', '" + this.getCustName() + "', " +
                    this.getCustID() + ", '" + this.getEmployeeName() + "', '" + this.getEmployeeType() + "', '" + this.getDate() + "')";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Inquiry added successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // you give the inquiry to the Employee to handle it
        Employee emp = new CustomerService();
        emp.handle(this);
    }

    public void respondToInquiry(String response, String employeeName) {
        //TODO: Add implementation
        //TODO: get employee's inquiry from database and remove the inquiry from their list
    }

    public ArrayList<Inquiry> inquiryHistory() {
        //TODO: Add implementation
    }
}
