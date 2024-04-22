import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.spi.DirStateFactory.Result;

public class MeterReader {
	private int ID;
	private float usage;
	private float previousReading;
	private float currentReading;
	private Connection con;

	public MeterReader(int ID, float usage, float previousReading, float currentReading) {
		this.ID = ID;
		this.usage = usage;
		this.previousReading = previousReading;
		this.currentReading = currentReading;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public float getUsage() {
		return usage;
	}

	public void setUsage(float usage) {
		this.usage = usage;
	}

	public float getPreviousReading() {
		return previousReading;
	}

	public void setPreviousReading(float previousReading) {
		this.previousReading = previousReading;
	}

	public float getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(float currentReading) {
		this.currentReading = currentReading;
	}

	public float calculateUsage() {
		// sets the usage by subtracting the current - previous readings
		// returns the usage
		setUsage(currentReading - previousReading);
		return usage;
	}

	public float viewUsage() {
		//DataBase version
		 try {
            Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select meterUsage from MeterReader");
			
        if (rs.next()) {
            // Retrieve the value from the result set
            usage = rs.getFloat("meterUsage");
        }
			
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
		return usage;
	}

	public float setTimeInterval() {
		//TODO: Add implementation
	}
}