public class Individual extends Category {
	public Individual() {
	}

	@Override
	public float calculateTax() {
		
		//this returns a tax value for individuals
		// 10 base electric consumption 
		// 10.5% tax imposed on base consumption 
		return (10 * 0.105f);
	}
}