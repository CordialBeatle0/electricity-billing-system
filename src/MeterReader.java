import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MeterReader {
	private int ID;
	private float usage;
	private float previousReading;
	private float currentReading;

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
	
	@Override
	public String toString() {
		return "MeterReader{" +
				"ID=" + ID +
				", usage=" + usage +
				", previousReading=" + previousReading +
				", currentReading=" + currentReading +
				'}';
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
	
	public static MeterReader getMeterReaderFromDB(String id) {
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM meterreader WHERE id = " + id);
			
			int sqlID = result.getInt("id");
			float sqlUsage = result.getFloat("meterUsage");
			float sqlPreviousReading = result.getFloat("previousReading");
			float sqlCurrentReading = result.getFloat("currentReading");
			
			return new MeterReader(sqlID, sqlUsage, sqlPreviousReading, sqlCurrentReading);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving meter reader from database");
		}
		return null;
	}
}