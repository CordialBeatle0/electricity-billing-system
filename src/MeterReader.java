import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
	
	public void getCurrentReadingFromTimePassed(int customerID) {
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT date FROM bill WHERE id IN (SELECT MAX(id) FROM bill " +
					"WHERE customer_id = " + customerID + ")");
			result.next();
			LocalDateTime previousDate = result.getTimestamp(1).toLocalDateTime();
			
			// calculates the difference in time between the last date of the bill and the current date
			long timeInSeconds = ChronoUnit.SECONDS.between(previousDate, LocalDateTime.now());
			float newCurrentReading = (timeInSeconds / 100f) + getCurrentReading();
			
			String sql = "UPDATE meterreader SET currentReading = " + newCurrentReading +
					", previousReading = " + currentReading + " WHERE id = " + getID();
			statement.executeUpdate(sql);
			
			previousReading = currentReading;
			currentReading = newCurrentReading;
		} catch (Exception e) {
			System.out.println("Error in getCurrentReadingFromTimePassed function");
		}
	}
	
	public float calculateUsage(int customerID) {
		// sets the usage by subtracting the current - previous readings
		// returns the usage
		getCurrentReadingFromTimePassed(customerID);
		setUsage(currentReading - previousReading);
		return usage;
	}
	
	public float viewUsage(int customerID) {
		return usage = calculateUsage(customerID);
	}
	
	public static MeterReader getMeterReaderFromDB(int id) {
		try {
			Connection connection = DatabaseSingleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM meterreader WHERE id = " + id);
			if (result.next()) {
				
				int sqlID = result.getInt("id");
				float sqlUsage = result.getFloat("meterUsage");
				float sqlPreviousReading = result.getFloat("previousReading");
				float sqlCurrentReading = result.getFloat("currentReading");
				
				return new MeterReader(sqlID, sqlUsage, sqlPreviousReading, sqlCurrentReading);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error retrieving meter reader from database in meter reader class");
		}
		return null;
	}
}