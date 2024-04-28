import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerService extends Employee {
    public CustomerService() {
    }

	public CustomerService(int ID, String name, int age, String address, String phoneNumber, char gender, float salary, Account account) {
		super(ID, name, age,address, phoneNumber, gender, salary, account);
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
                if (custCategory.equals("Individual")) {
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
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
