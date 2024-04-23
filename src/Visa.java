
import javax.swing.JOptionPane;


public class Visa implements Payment {

    public Visa() {
    }

    @Override
    public void makePayment(Customer customer, float amount) {
        //TODO: Check card info logic

        //update the outstanding fees of the customer
        customer.setOutstandingFees(customer.getOutstandingFees() - amount);
        //TODO: DATABASE update outstanding fees for customer
        //TODO: JOptionPane to confirm payment
        JOptionPane.showMessageDialog(null, "Your Payment has been made successfully");
    }
}
