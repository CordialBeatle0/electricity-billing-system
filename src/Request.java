import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;


public class Request {

    private int ID;
    private int custID;
    private String custName;
    private String requestType;
    private String location;
    private LocalDate date;
    
    public Request(int custID, String custName, String requestType, String location, LocalDate date) {
        this.custID = custID;
        this.custName = custName;
        this.requestType = requestType;
        this.location = location;
        this.date = date;
    }
    
    public Request(int ID, int custID, String custName, String requestType, String location, LocalDate date) {
        this.ID = ID;
        this.custID = custID;
        this.custName = custName;
        this.requestType = requestType;
        this.location = location;
        this.date = date;
    }

    public Request(int ID, int custID, String location, String requestType) {
        this.ID = ID;
        this.custID = custID;
        this.location = location;
        this.requestType = requestType;
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

    public LocalDate getDate() {
        return date;
    }

    public void addRequest() {
        //TODO: Add implementation
    }

    public static ArrayList<Request> viewRequest(Technician tech) {
        //TODO: DATABASE retrieve all TAKE CODE FROM AMIR
        ArrayList<Request> requests = new ArrayList<>();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT request.id, customer_id, location, requestType FROM request, customer WHERE request.customer_id = customer.id AND location = '" + tech.getAssignedLocation() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Request request = new Request(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public void requestHomeService() {
        Technician tech = new Technician();
        tech.assignTechnician(this);
    }

    public void addRequesttoDB() {
        try {
            Connection connection= DatabaseSingleton.getInstance().getConnection();
            Statement statement=  connection.createStatement();
            statement.executeUpdate("INSERT INTO request(custID, custName, requestType, location, date) values(" + custID + ",'" + custName + "','" + requestType + "','" + location + "','" + date + "')");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding request to database");
        }
    }
}
