

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

	public float calculateUsage() {
		// sets the usage by subtracting the current - previous readings
		// returns the usage
		setUsage(currentReading - previousReading);
		return usage;
	}

	

	public float viewUsage() {
		return usage = calculateUsage();
	}

	public float setTimeInterval() {
		//TODO: Add implementation
	}
}