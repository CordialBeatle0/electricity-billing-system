
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;

public class Subscription {

    private boolean subscriptionStatus;

    public Subscription() {
    }

    public Subscription(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public boolean isSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public boolean subscribeToElectricity(Customer customer) {
        //if customer not already subscribed
        if (!customer.getSubscription().subscriptionStatus) {
            //make customer a subscriber
            customer.setSubscription(new Subscription(true));
            try {
                Connection connection = DatabaseSingleton.getInstance().getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("UPDATE customer set subscriptionStatus= true WHERE id = " + customer.getID());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error updating customer subscription status in database");
            }
            return true;
        } else {
            return false;
        }
    }

    public void renewSubscription(Customer cust, float amount, Payment paymentType) {
        // check if it is time to pay
        if (cust.isTimeToPay()) {
            // If customer is subscribed and has outstanding fees
            if (cust.getSubscription().subscriptionStatus && cust.getOutstandingFees() > 0) {
                // verify that the entered amount is less than the outstanding fees
                if (amount <= cust.getOutstandingFees() && amount >= 0) {
                    // set the method of payment (cash or visa)
                    cust.setPaymentType(paymentType);
                    // call make payment in the interface of strategy
                    paymentType.makePayment(cust, amount);
                } else {
                    JOptionPane.showMessageDialog(null, "Paid Amount Should not Exceed Outstanding Fees");
                }
            } // if customer not subscribed
            else {
                JOptionPane.showMessageDialog(null, "You must subscribe first to use the service");
            }
        } // if it's not time to pay
        else {
            JOptionPane.showMessageDialog(null, "You cannot pay now, wait for a notification when it's time to pay");
        }
    }
}
