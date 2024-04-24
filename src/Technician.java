import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Technician extends Employee {
    private static int maxCapacity;
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
        //TODO: DATABASE get the techniician for this location from the database instead of for - if (1st one)
        Technician technician = (Technician)Employee.getEmployeesFromDB("employeeType = 'Technician' AND technicianAssignedLocation = " + request.getLocation()).get(0);
        
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            // TODO: add where condition after request status gets added to database
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM request WHERE id = " + request.getID());
            int numberOfRequests = result.getInt(0);
            if (numberOfRequests < maxCapacity) {
                //TODO: DATABASE add request to DB of the technician instead
                request.addRequesttoDB();
                //TODO: JOptionPane to confirm
                JOptionPane.showMessageDialog(null, "Your request has been made successfully");
            } //if technician can not take any more requests, end the process
            else {
                //TODO: JOptionPane to customer
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
                    nextEmp.handle(inquiry);
                }
            } else {
                System.out.println("Customer with ID " + custID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void confirmCashPayment(Request request, float payedAmount) {
        int custID = request.getCustID();
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM customer WHERE id = " + custID;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                float outstandingFees = rs.getFloat("outstandingFees");
                if (outstandingFees == 0.0) {
                    JOptionPane.showMessageDialog(null, "Customer with ID "
                            + custID + " has no outstanding fees.");
                } else {
                    // Update the customer's outstanding fees to 0
                    String update = "UPDATE customer SET outstandingFees = "
                            + (outstandingFees - payedAmount) + " WHERE id = " + custID;
                    stmt.executeUpdate(update);
                    String delete = "DELETE FROM request WHERE id = " + request.getID();
                    stmt.executeUpdate(delete);
                    String isTimeToPayFalse = "UPDATE customer SET isTimeToPay = FALSE WHERE id = " + custID;
                    stmt.executeUpdate(isTimeToPayFalse);
                    JOptionPane.showMessageDialog(null, "Customer with ID "
                            + custID + " has paid "
                            + payedAmount + " remaining fees: " + (outstandingFees - payedAmount) + "$. ");
                }
            } else {
                System.out.println("Customer with ID " + custID + " not found.");
                JOptionPane.showMessageDialog(null, "Customer with ID " + custID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
