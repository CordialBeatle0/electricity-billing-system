import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Request {
    private int ID;
    private int custID;
    private String custName;
    private String requestType;
    private String location;
    private LocalDateTime date;
    
    public Request(int custID, String custName, String requestType, String location, LocalDateTime date) {
        this.custID = custID;
        this.custName = custName;
        this.requestType = requestType;
        this.location = location;
        this.date = date;
    }
    
    public Request(int ID, int custID, String custName, String requestType, String location, LocalDateTime date) {
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
    
    public static ArrayList<Request> viewRequest(Technician tech) {
        ArrayList<Request> requests = new ArrayList<>();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, custName, requestType, location, date, customer_id FROM request WHERE location = '" + tech.getAssignedLocation() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Request request = new Request(rs.getInt(1), rs.getInt(6), rs.getString(2), rs.getString(3), rs.getString(4), LocalDateTime.now());
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
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
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void requestHomeService() {
        Technician tech = new Technician();
        tech.assignTechnician(this);
    }
    
    public void addRequesttoDB() {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO request(customer_id, custName, requestType, location, date) values(" + custID + ",'" + custName + "','" + requestType + "','" + location + "','" + date + "')");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding request to database");
        }
    }
}
