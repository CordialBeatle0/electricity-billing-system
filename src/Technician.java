import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Technician extends Employee {
    private static int maxCapacity = 10;
    private String assignedLocation;

    public Technician() {
    }

    public Technician(int ID, String name, int age, String address, String phoneNumber, char gender, float salary, Account account, String assignedLocation) {
        super(ID, name, age, address, phoneNumber, gender, salary, account);
        this.assignedLocation = assignedLocation;
    }

    public static int getMaxCapacity() {
        return maxCapacity;
    }

    public static void setMaxCapacity(int maxCapacity) {
        Technician.maxCapacity = maxCapacity;
    }

    public String getAssignedLocation() {
        return assignedLocation;
    }

    public void setAssignedLocation(String assignedLocation) {
        this.assignedLocation = assignedLocation;
    }

    public void assignTechnician(Request request) {
        Technician technician = (Technician) Employee.getEmployeesFromDB("employeeType = 'Technician' AND " +
                "technicianAssignedLocation = '" + request.getLocation() + "'").get(0);
        
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM request WHERE location = '" + technician.assignedLocation + "'");
            result.next();
            int numberOfRequests = result.getInt(1);
            if (numberOfRequests < maxCapacity) {
                request.addRequestToDB();
                JOptionPane.showMessageDialog(null, "Your request has been made successfully");
            } //if technician can not take any more requests, end the process
            else {
                JOptionPane.showMessageDialog(null, "No available technicians");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving request from database");
        }
    }

    @Override
    public void handle(Inquiry inquiry) {
        int custID = inquiry.getCustID();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM customer WHERE id = " + custID;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String custCategory = rs.getString("custCategory");
                if (custCategory.equals("Factory")) {
                    this.assignEmployee(inquiry);
                } else {
                    // pass to the next in chain
                    ResultSet result = stmt.executeQuery("SELECT nextEmp FROM employee WHERE id = " + getID());
                    if (!result.next()) {
                        JOptionPane.showMessageDialog(null, "The chain has not been set yet");
                    }
                    
                    int nextEmployeeHandle = result.getInt(1);
                    nextEmp = Employee.getEmployeesFromDB(nextEmployeeHandle + "").get(0);
                    nextEmp.handle(inquiry);
                }
            } else {
                System.out.println("Customer with ID " + custID + " not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving customer from database");
        }
    }
}
