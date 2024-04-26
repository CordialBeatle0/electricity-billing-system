import java.util.ArrayList;

public interface InquiryROI {
    public ArrayList<Inquiry> viewInquiriesByID(int custID, String empType);

    public void addInquiry(int customerID);
}
