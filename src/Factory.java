public class Factory extends Category {
	public Factory() {
	}

	@Override
	public float calculateTax() {
		// this returns a tax value for the factories
		// 10 base electric consumption 
		// 51% tax imposed on base consumption  
		return (10 * 0.51f);
	}
}
