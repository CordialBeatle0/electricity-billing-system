import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Account {
    private int ID;
    private String username;
    private String password;

    public Account(int ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void updateAccount(String username, String password) {
        //TODO: Add implementation
    }

    public static Customer custLogin(String username, String password) {
        Customer customer;
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Abll el select");
            ResultSet result = statement.executeQuery("SELECT customer.id, name, phone, address, account_id FROM customer JOIN electricity_billing_db.account a on a.id = customer.account_id WHERE username = '" + username + "' AND password = '" + password + "'");
            System.out.println("B3ddd el select");
            result.next();
            int sqlID = result.getInt(1);
            String sqlName = result.getString(2);
            String sqlPhone = result.getString(3);
            String sqlAddress = result.getString(4);
            int accountID_INT = result.getInt(5);
            System.out.println("B3ddd el select 5");
            Account sqlAccount = Account.getAccountFromDB(accountID_INT);
            System.out.println("B3ddd el select accounttt");
            customer = new Customer(sqlID, sqlName, sqlAddress, sqlPhone, false, 0, sqlAccount, new Subscription(false));
            return customer;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in custLogin function in customer");
        }
        return null;
    }

    public static Employee empLogin(String username, String password) {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employee JOIN electricity_billing_db.account a on a.id = employee.account_id WHERE username = '" + username + "' AND password = '" + password + "'");
            result.next();
            int sqlID = result.getInt(1);
            String sqlName = result.getString(3);
            int sqlAge = result.getInt(4);
            String sqlAddress = result.getString(5);
            String sqlPhone = result.getString(6);
            char sqlGender = result.getString(7).charAt(0);
            float sqlSalary = result.getFloat(8);

            int accountID_INT = result.getInt(9);
            Account sqlAccount = Account.getAccountFromDB(accountID_INT);

            Employee employee = new Admin(sqlID, sqlName, sqlAge, sqlAddress, sqlPhone, sqlGender, sqlSalary, sqlAccount);
            return employee;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in empLogin function in account");
        }
        return null;
    }

    public static Account getAccountFromDB(int id) {
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM account WHERE id = " + id);
            result.next();
            int sqlID = result.getInt("id");
            String sqlUsername = result.getString("username");
            String sqlPassword = result.getString("password");

            return new Account(sqlID, sqlUsername, sqlPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving account from database");
        }
        return null;
    }
}
