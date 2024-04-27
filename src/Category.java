import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

public abstract class Category {
    public Category() {
    }

    public void categorizeCustomer(Customer customer) {
        customer.setCategory(this);
        ResultSet generatedKeys = null;
        try {

            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE customer SET custCategory = '" + this.getClass().getName() + "' WHERE id = " + customer.getID());
            String qu = "INSERT INTO meterreader (meterUsage, previousReading, currentReading) VALUES (0, 0, 0) ";
            statement.executeUpdate(qu, Statement.RETURN_GENERATED_KEYS);
            generatedKeys = statement.getGeneratedKeys();
            int meterReaderID = -1;
            if (generatedKeys.next()) {
                meterReaderID = generatedKeys.getInt(1);
            }
            statement.executeUpdate("UPDATE customer SET meterReader_id =  " + meterReaderID + " WHERE id = " + customer.getID());
            
            statement.executeUpdate("INSERT INTO bill (totalAmount, date, customer_id) VALUE (0, '" + LocalDateTime.now() +
                    "', " + customer.getID() + ")");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating customer in database");
        }
    }

    public abstract float calculateTax();
}