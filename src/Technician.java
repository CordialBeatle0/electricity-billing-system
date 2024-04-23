
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Technician extends Employee {
    private static int maxCapacity;
    private String assignedLocation;
    

    public Technician() {
    }

    public Technician(int ID, String name, int age,String address, String phoneNumber, char gender, float salary, Account account, String assignedLocation) {
        super(ID, name, age,address, phoneNumber, gender, salary, account);
        this.assignedLocation = assignedLocation;
    }

    public static int getMaxCapacity() {
        return maxCapacity;
    }

    public static void setMaxCapacity(int maxCapacity) {
        Technician.maxCapacity = maxCapacity;
    }

    public String getAssignedLocation() {
        return assignedLocation;
    }

    public void setAssignedLocation(String assignedLocation) {
        this.assignedLocation = assignedLocation;
    }

    public void assignTechnician(Request request) {
        //TODO: DATABASE get the techniician for this location from the database instead of for - if (1st one)
        for (Technician tech : technicians) {
            //if the technician's assigned location is the same as the request location
            if (tech.assignedLocation.equals(request.getLocation())) {
                //if the technician can take more requests
                if (tech.requestedServices.size() < maxCapacity) {
                    //TODO: DATABASE add request to DB of the technician instead
                    request.addRequesttoDB();
                    //TODO: JOptionPane to confirm
                    JOptionPane.showMessageDialog(null, "Your request has been made successfully");
                } //if technician can not take any more requests, end the process
                else {
                    //TODO: JOptionPane to customer
                    JOptionPane.showMessageDialog(null, "No available technicians");
                }
            }
        }
    }

    @Override
    public void handle(Inquiry inquiry) {
        //TODO: Add implementation
    }

    public void confirmCashPayment(Request request) {
        //TODO: Add implementation
    }
}
