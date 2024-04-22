import java.util.GregorianCalendar;

public class main {
    public static void main(String[] args) {
        GregorianCalendar currentdate = new GregorianCalendar();
        GregorianCalendar duedate= new GregorianCalendar(2024, 4, 22);
        //System.out.println(currentdate.DAY_OF_MONTH);
        if (duedate == currentdate) {
            System.out.println("true");
        }
    }
}
