import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateAccountTest {

    @Test
    public void testUpdateAccount() {
        // Creating a mock Customer object with outstanding fees
        Account account = new Account(2121, "21amir21", "2121");
        Customer customer = new Customer(2121, "Amir_TEST", "Heliopolis", "01202190488", account);

        account.updateAccount(customer, "amir21", "2121");
        // in case we changed the username only
        Account acc = Account.getAccountFromDB(2121);

        assertTrue(acc.getUsername().equals("amir21"));
        assertTrue(acc.getPassword().equals("2121"));
        assertFalse(acc.getUsername().equals("21amir21"));


    }
}
