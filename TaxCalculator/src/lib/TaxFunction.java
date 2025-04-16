package lib;

public class TaxFunction {

	private static final int PTKP = 54000000;
	private static final int Married = 4500000;
	private static final int Child = 4500000;
	private static final double TaxRate = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		int tax = 0;

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}

		if (isMarried) {
			tax = (int) Math.round(TaxRate * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible
					- (PTKP + Married + (numberOfChildren * Child))));
		} else {
			tax = (int) Math.round(
					TaxRate * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - PTKP));
		}

		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}
	}
}
