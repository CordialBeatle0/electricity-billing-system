import java.util.ArrayList;

public interface InquiryROI {
    public ArrayList<Inquiry> viewInquiriesByID(int custID, int employeeID);
    
    public void addInquiry(int customerID);
}
