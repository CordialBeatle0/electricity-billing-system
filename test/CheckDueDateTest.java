import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class CheckDueDateTest {
    @Test
    public void testCheckDueDate() {
        // Creating a mock Customer object with outstanding fees
        Bill bill = new Bill();
        GregorianCalendar billDate = Bill.getDueDate();

        bill.checkDueDate();


        // Testing if the bill date is not the same as the current date it will not send billing alert
        GregorianCalendar currentDate = new GregorianCalendar();
        assertFalse(currentDate.get(GregorianCalendar.DAY_OF_MONTH) == billDate.get(GregorianCalendar.DAY_OF_MONTH));
    }
}
