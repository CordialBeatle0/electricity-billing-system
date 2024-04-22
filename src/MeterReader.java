import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MeterReader {
	private int ID;
	private float usage;
	private float previousReading;
	private float currentReading;
	private static Connection connection;

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
		//TODO: Add implementation
	}

	public float viewUsage() {
		//TODO: Add implementation
	}

	public float setTimeInterval() {
		//TODO: Add implementation
	}
	
	public static MeterReader getMeterReaderFromDB(String id) {
		try {
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