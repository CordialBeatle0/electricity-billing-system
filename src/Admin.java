import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Admin extends Employee {

    public Admin(int ID, String name, int age, String address, String phoneNumber, char gender, float salary,
                 Account account) {
        super(ID, name, age, address, phoneNumber, gender, salary, account);
    }

    public void manageCustStatus(Customer customer) {
        //TODO: Add implementation
    }

    public void manageCustCategory(Customer customer) {
        //TODO: Add implementation
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
                if (custCategory.equals("Company")) {
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
}
