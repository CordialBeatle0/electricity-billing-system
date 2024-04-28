import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
    private LocalDateTime date;

    public Inquiry() {
    }

    public Inquiry(String question, int ID, String custCategory, String custName, int custID, LocalDateTime date) {
        this.question = question;
        this.ID = ID;
        this.custCategory = custCategory;
        this.custName = custName;
        this.custID = custID;
        this.date = date;
    }

    public Inquiry(String question, String custCategory, String custName, int custID, LocalDateTime date) {
        this.question = question;
        this.custCategory = custCategory;
        this.custName = custName;
        this.custID = custID;
        this.date = date;
    }

    public Inquiry(String question, int ID, String custCategory, String custName, LocalDateTime date) {
        this.question = question;
        this.ID = ID;
        this.custCategory = custCategory;
        this.custName = custName;
        this.date = date;
    }

    public Inquiry(String question, String custCategory, String custName, int custID, String employeeName, String employeeType, LocalDateTime date) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public boolean respondToInquiry(int employeeID, int inquiryID) {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE inquiry SET response = '" + response + "', employee_id = " + employeeID
                    + " WHERE id = " + inquiryID);
            statement.executeUpdate("INSERT INTO notification (message, customer_id) VALUES ('An employee has " +
                    "responded to your inquiry', " + getCustID() + ");");
            JOptionPane.showMessageDialog(null, "Response submitted");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating inquiry in database");
        }
        return false;
    }

    @Override
    public ArrayList<Inquiry> viewInquiriesByID(int custID, int employeeID) {
        ArrayList<Inquiry> inquiries = new ArrayList<>();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT inquiry.id, customer_id, question, inquiry.custCategory, name, date  FROM inquiry " +
                    "JOIN electricity_billing_db.customer c on c.id = inquiry.customer_id WHERE employee_id = " + employeeID +
                    " AND response IS null AND customer_id = " + custID;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Inquiry inquiry = new Inquiry(rs.getString(3), rs.getInt(1), rs.getString(4), rs.getString(5),
                        rs.getInt(2), rs.getTimestamp(6).toLocalDateTime());
                inquiries.add(inquiry);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving inquiry from database");
        }
        return inquiries;
    }

    // this method is used to view all inquiries in the database by the Admin only
    static public ArrayList<Inquiry> viewInquiries(int employeeID) {
        ArrayList<Inquiry> inquiries = new ArrayList<>();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT inquiry.id, customer_id, question, inquiry.custCategory, name, date  FROM inquiry " +
                    "JOIN electricity_billing_db.customer c on c.id = inquiry.customer_id WHERE employee_id = " + employeeID +
                    " AND response IS null";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Inquiry inquiry = new Inquiry(rs.getString(3), rs.getInt(1), rs.getString(4),
                        rs.getString(5), rs.getInt(2), rs.getTimestamp(6).toLocalDateTime());
                inquiries.add(inquiry);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving inquiry from database");
        }
        return inquiries;
    }

    @Override
    public void addInquiry(int customerID) {
        // hat5od el inquiry mn el GUI then add it to the db table inquiry
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            ResultSet generatedKeys;
            // Create the SQL query using values from the Inquiry object
            String query = "INSERT INTO inquiry (date, question, response, custCategory, employee_id, customer_id) " +
                    "VALUES ('" + date + "', '" + question + "', " + null + ", '" +
                    custCategory + "', " + null + ", " + customerID + ")";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println("Inquiry added successfully to the database.");
            JOptionPane.showMessageDialog(null, "Successfully submitted");

            generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            ID = generatedKeys.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding inquiry to database");
        }

        // you give the inquiry to the Employee to handle it
        String sql = "employeeType = 'CustomerService'";
        Employee emp = Employee.getEmployeesFromDB(sql).get(0);
        emp.handle(this);
    }
}
