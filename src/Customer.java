import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer implements Observer {
    private int ID;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean isTimeToPay;
    private Category category;
    private MeterReader meterReader;
    private float outstandingFees;
    private Account account;
    private Payment paymentType;
    private Subscription subscription;

    public Customer(int ID, String name, String address, String phoneNumber, boolean isTimeToPay, Category category, MeterReader meterReader, float outstandingFees, Account account, Subscription subscription) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isTimeToPay = isTimeToPay;
        this.category = category;
        this.meterReader = meterReader;
        this.outstandingFees = outstandingFees;
        this.account = account;
        this.subscription = subscription;
    }

    public Customer(int ID, String name, String address, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int ID, String name, String address, String phoneNumber, Account account) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    public Customer(int ID, String name, String address, String phoneNumber, boolean isTimeToPay, float outstandingFees, Account account, Subscription subscription) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isTimeToPay = isTimeToPay;
        this.outstandingFees = outstandingFees;
        this.account = account;
        this.subscription = subscription;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isTimeToPay() {
        return isTimeToPay;
    }

    public void setTimeToPay(boolean timeToPay) {
        isTimeToPay = timeToPay;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MeterReader getMeterReader() {
        return meterReader;
    }

    public void setMeterReader(MeterReader meterReader) {
        this.meterReader = meterReader;
    }

    public float getOutstandingFees() {
        return outstandingFees;
    }

    public void setOutstandingFees(float outstandingFees) {
        this.outstandingFees = outstandingFees;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Payment getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Payment paymentType) {
        this.paymentType = paymentType;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isTimeToPay=" + isTimeToPay +
                ", category=" + category +
                ", meterReader=" + meterReader +
                ", outstandingFees=" + outstandingFees +
                ", account=" + account +
                ", paymentType=" + paymentType +
                ", subscription=" + subscription +
                '}';
    }

    @Override
    public void updateObserver(String message) {
        addNotificationToDB(message);
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String isTimeToPayTrue = "UPDATE customer SET isTimeToPay = TRUE WHERE id = " + ID;
            statement.executeQuery(isTimeToPayTrue);
        } catch (Exception e) {
            //TOD: write exception joptionpayne
        }
    }

    public void addNotificationToDB(String message) {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO notification (message, customer_id) VALUES ('" + message + "', " + ID + ");");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding notification to database");
        }
    }

    public void removeNotificationFromDB(int id) {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM notification WHERE id = " + id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error removing notification from database");
        }
    }

    /**
     * @param condition if empty, selects all customers in the database <br>
     *                  otherwise should be the exact statement written after a WHERE clause
     * @return ArrayList of all customers retrieved from the query
     */
    public static ArrayList<Customer> getCustomersFromDB(String condition) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result;
            // if condition is empty get all customers
            if (condition.isEmpty()) {
                result = statement.executeQuery("SELECT * FROM customer");
            }
            // if condition exists
            else {
                result = statement.executeQuery("SELECT * FROM customer WHERE " + condition);
            }
            while (result.next()) {
                int sqlID = result.getInt("id");
                String sqlName = result.getString("name");
                String sqlAddress = result.getString("address");
                String sqlPhoneNumber = result.getString("phone");
                boolean sqlIsTimeToPay = result.getBoolean("isTimeToPay");
                
                Category sqlCategory = null;
                try {
                    String category = result.getString("custCategory");
                    sqlCategory = switch (category) {
                        case "Individual" -> new Individual();
                        case "Company" -> new Company();
                        case "Factory" -> new Factory();
                        default -> null;
                    };
                } catch (NullPointerException e) {
                    System.out.println("Customer does not have a category while logging in");
                }
                
                MeterReader sqlMeterReader = null;
                try {
                    int meterReaderID_INT = result.getInt("meterReader_id");
                    sqlMeterReader = MeterReader.getMeterReaderFromDB(meterReaderID_INT);
                } catch (NullPointerException e) {
                    System.out.println("Customer does not have a meter reader while logging in");
                }

                float sqlOutstandingFees = result.getFloat("outstandingFees");

                // account
                int accountID_INT = result.getInt("account_id");
                Account sqlAccount = Account.getAccountFromDB(accountID_INT);

                // subscription
                boolean subscriptionStatus_Bool = result.getBoolean("subscriptionStatus");
                Subscription sqlSubscription = new Subscription(subscriptionStatus_Bool);

                customers.add(new Customer(sqlID, sqlName, sqlAddress, sqlPhoneNumber, sqlIsTimeToPay, sqlCategory, sqlMeterReader, sqlOutstandingFees, sqlAccount, sqlSubscription));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving customer from database");
        }
        return customers;
    }

    public static boolean doesCustomerExist(int id) {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT name FROM customer WHERE " + id);
            if (result.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving customer from database");
        }
        return false;
    }
}
