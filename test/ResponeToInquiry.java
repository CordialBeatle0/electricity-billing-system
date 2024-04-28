import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ResponeToInquiry {
    @Test
    public void testResponseToInquiry() {
        // Creating a mock Customer object with outstanding fees
        Inquiry inquiry = new Inquiry();
        inquiry.setID(2121);
        Employee employee = new CustomerService();
        employee.setID(2121);

        inquiry.setResponse("The issue has been resolved, you can check your account now.");
        inquiry.respondToInquiry(employee.getID(), inquiry.getID());

        String answerResponse = "";

        Connection conn = DatabaseSingleton.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT response FROM inquiry WHERE id = " + inquiry.getID();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                answerResponse = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals("The issue has been resolved, you can check your account now.", answerResponse);
    }
}
