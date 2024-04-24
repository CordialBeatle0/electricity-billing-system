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

    public Technician(int ID, String name, int age,String address, String phoneNumber, char gender, float salary, Account account, String assignedLocation) {
        super(ID, name, age,address, phoneNumber, gender, salary, account);
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
        for (Technician tech : technicians) {
            //if the technician's assigned location is the same as the request location
            if (tech.assignedLocation.equals(request.getLocation())) {
                //if the technician can take more requests
                if (tech.requestedServices.size() < maxCapacity) {
                    //TODO: DATABASE add request to DB of the technician instead
                    request.addRequesttoDB();
                    //TODO: JOptionPane to confirm
                    JOptionPane.showMessageDialog(null, "Your request has been made successfully");
                } //if technician can not take any more requests, end the process
                else {
                    //TODO: JOptionPane to customer
                    JOptionPane.showMessageDialog(null, "No available technicians");
                }
            }
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
