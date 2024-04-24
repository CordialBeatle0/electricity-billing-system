
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

    public ArrayList<Request> viewRequest() {
        //TODO: DATABASE retrieve all TAKE CODE FROM AMIR
        return null;
    }

    public void requestHomeService() {
        Technician tech = new Technician();
        tech.assignTechnician(this);
    }

    public void addRequesttoDB(){
        try{
            Connection connection= DatabaseSingleton.getInstance().getConnection();
            Statement statement=  connection.createStatement();
            statement.executeUpdate("INSERT INTO request(custID, custName, requestType, location, date) values("+ custID+",'"+ custName +"','"+ requestType +"','"+ location+"','"+ date +"')");
                }
                    catch(Exception e){
                    // TODO: write exception logic
                    }
                            
    }
}
