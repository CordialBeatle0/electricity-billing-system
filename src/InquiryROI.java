import java.util.ArrayList;

public interface InquiryROI {
    public ArrayList<Inquiry> viewInquiriesByID(int custID);

    public void addInquiry();
}
