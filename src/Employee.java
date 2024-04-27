import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Employee {
    private int ID;
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private char gender;
    private float salary;
    private Account account;
    protected Employee nextEmp;

    public Employee(int ID, String name, int age, String address, String phoneNumber, char gender, float salary, Account account) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.salary = salary;
        this.account = account;
    }

    public Employee() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setHandler(Employee employee) {
        nextEmp = employee;
    }

    public abstract void handle(Inquiry inquiry);

    public void assignEmployee(Inquiry inquiry) {
        inquiry.setEmployeeType(this.getClass().getName());
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            // gets all employees of the type assigned (Admin, Technician, CustomerService)
            // and gets the one with the lowest number of assigned inquiries
            ArrayList<Integer> employeeIDs = new ArrayList<>();
            ResultSet result = stmt.executeQuery("SELECT id FROM employee WHERE employeeType = '" + getClass().getName() + "'");
            while (result.next()){
                employeeIDs.add(result.getInt(1));
            }
            
            int lowestValue = 100;
            int lowestValueID = 1; // dummy value
            for (int id : employeeIDs) {
                result = stmt.executeQuery("SELECT COUNT(*) AS 'Number of inquiries' FROM inquiry WHERE employee_id = " + id);
                if (result.next()) {
                    int currentValue = result.getInt(1);
                    if (lowestValue > currentValue) {
                        lowestValueID = id;
                        lowestValue = currentValue;
                    }
                } else {
                    lowestValue = 0;
                    lowestValueID = id;
                }
            }
            String sql = "UPDATE inquiry SET employee_id = " + lowestValueID + " WHERE ID = " + inquiry.getID();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating inquiry in assignEmployee function in employee class");
        }
    }

    /**
     * @param condition if empty, selects all customers in the database <br>
     *                  otherwise should be the exact statement written after a WHERE clause
     * @return ArrayList of all employees retrieved from the query
     */
    public static ArrayList<Employee> getEmployeesFromDB(String condition) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result;
            if (condition.isEmpty()) {
                result = statement.executeQuery("SELECT * FROM employee");
            } else {
                result = statement.executeQuery("SELECT * FROM employee WHERE " + condition);
            }
            while (result.next()) {
                int sqlID = result.getInt(1);
                String employeeType = result.getString(2);
                String sqlName = result.getString(3);
                int sqlAge = result.getInt(4);
                String sqlAddress = result.getString(5);
                String sqlPhoneNumber = result.getString(6);
                char sqlGender = result.getString(7).charAt(0);
                float sqlSalary = result.getFloat(8);

                // account
                int accountID_INT = result.getInt(9);
                Account sqlAccount = Account.getAccountFromDB(accountID_INT);

                Employee employee;

                if (employeeType.equals("Technician")) {
                    String sqlAssignedLocation = result.getString(10);
                    employee = new Technician(sqlID, sqlName, sqlAge, sqlAddress, sqlPhoneNumber, sqlGender, sqlSalary, sqlAccount, sqlAssignedLocation);
                } else if (employeeType.equals("Admin")) {
                    employee = new Admin(sqlID, sqlName, sqlAge, sqlAddress, sqlPhoneNumber, sqlGender, sqlSalary, sqlAccount);
                } else {
                    employee = new CustomerService(sqlID, sqlName, sqlAge, sqlAddress, sqlPhoneNumber, sqlGender, sqlSalary, sqlAccount);
                }
                employees.add(employee);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving employee from database");
        }
        return employees;
    }
}
