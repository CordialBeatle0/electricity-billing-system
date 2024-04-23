
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;

public class Subscription {

    private boolean subscriptionStatus;

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
        if (customer.getSubscription().subscriptionStatus == false) {
            //make customer a subscriber
            customer.setSubscription(new Subscription(true));
            //TODO: DATABASE update subscription of customer in DB
            try{
                Connection connection = DatabaseSingleton.getInstance().getConnection();
                Statement statement= connection.createStatement();
                statement.executeUpdate("'UPDATE customer set subscriptionStatus= true");
            }
            catch(Exception e){
                // TODO: exception layout needs to be added joptionpayne
            }
            // TODO: Category.addUncategorizedCust(customer); this should be removed 
            //as we will add all the customers in the table and admin will filter to get false status to set the category
            return true;
        } else {
            return false;
        }
    }

    public void renewSubscription(Customer cust, float amount, Payment paymentType) {
        //check if it is time to pay
        if (cust.isTimeToPay()) {
            //TODO: How to check card info
            //If customer is subscribed and has outstanding fees
            if (cust.getSubscription().subscriptionStatus == true && cust.getOutstandingFees() > 0) {
                //verify that the eneterd amount is less than the outsanding fees
                if (amount <= cust.getOutstandingFees()) {
                    //set the method of payment (cash or visa)
                    cust.setPaymentType(paymentType);
                    //call make payment in the interface of strategy
                    paymentType.makePayment(cust, amount);
                } else {
                    //TODO: JOptionPane that enetered amount should be less than outstanding
                    JOptionPane.showMessageDialog(null, "Paid Amount Should not Exceed Outstanding Fees");

                }
            } //if customer not subscribed
            else {
                //TODO: JOptionPane fel GUI
                JOptionPane.showMessageDialog(null, "You must subscribe first to use the service");
            }
        } //if its not time to pay
        else {
            JOptionPane.showMessageDialog(null, "You cannot pay now, wait for a notification when it's time to pay");
        }

    }
}
