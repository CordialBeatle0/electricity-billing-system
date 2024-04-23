import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

public abstract class Category {
	public Category() {
	}

	public void categorizeCustomer(Customer customer) {
		customer.setCategory(this);
		
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE customer SET custCategory = " + this.getClass().getName() + " WHERE id = " + customer.getID());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error updating customer in database");
		}
	}

	public abstract float calculateTax();
}