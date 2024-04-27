import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Cash implements Payment {
	public Cash() {
	}

	@Override
	public void makePayment(Customer customer, float amount) {
		Request req = new Request(customer.getID(), customer.getName(),"Collect Cash Payment", customer.getAddress(), LocalDateTime.now());
		//add request to DB
		req.addRequesttoDB();
		req.requestHomeService();
	}
	
	public void confirmCashPayment(Technician technician, Request request, float payedAmount) {
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
			JOptionPane.showMessageDialog(null, "Error editing or retrieving customer from database");
		}
	}
}
