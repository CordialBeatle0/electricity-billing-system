import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SubscriptionRenewalTest {

    @Test
    public void testRenewSubscription() {
        // Creating a mock Customer object with outstanding fees
        Customer customer = new Customer();
        Subscription subscription = new Subscription();
        subscription.setSubscriptionStatus(true);
        customer.setSubscription(subscription);
        customer.setOutstandingFees(100.0f); // Assuming outstanding fees are $100
        customer.setTimeToPay(true);
        // Testing with a positive amount greater than or equal to outstanding fees
        subscription.renewSubscription(customer, 100.0f, new Visa());
        assertEquals(0.0f, customer.getOutstandingFees()); // Outstanding fees should reduce to $50

        customer.setTimeToPay(true);
        customer.setOutstandingFees(100.0f); // Resetting outstanding fees to $100
        // Testing with a positive amount less than outstanding fees
        subscription.renewSubscription(customer, 50.0f, new Visa());
        assertEquals(50, customer.getOutstandingFees()); // Outstanding fees should remain $50


        customer.setTimeToPay(true);
        customer.setOutstandingFees(100.0f); // Resetting outstanding fees to $100
        // Testing with a negative amount
        subscription.renewSubscription(customer, -50.0f, new Visa());
        assertEquals(100.0f, customer.getOutstandingFees()); // Outstanding fees should remain $50

    }
}
