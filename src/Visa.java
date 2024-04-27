
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;

public class Visa implements Payment {

    public Visa() {
    }

    @Override
    public void makePayment(Customer customer, float amount) {
        //update the outstanding fees of the customer
        customer.setOutstandingFees(customer.getOutstandingFees() - amount);
        customer.setTimeToPay(false);
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE customer set outstandingFees = " + customer.getOutstandingFees() + " WHERE id = " + customer.getID());
            statement.executeUpdate("UPDATE customer set isTimeToPay = FALSE WHERE id = " + customer.getID());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating customer outstanding fees in database");
        }
        JOptionPane.showMessageDialog(null, "Your Payment has been made successfully");
    }
}
