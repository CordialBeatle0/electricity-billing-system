public class Company extends Category {
	public Company() {
	}

	@Override
	public float calculateTax() {
		// this returns a tax value for the companies
		// 10 base electric consumption 
		// 41% tax imposed on base consumption 
		return (10 * 0.41f);
	}
}
