package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private Biodata employeeBiodata;

	private LocalDate dateJoined;

	private boolean isForeigner;

	private enum Grade {
		GRADE_1(3000000),
		GRADE_2(5000000),
		GRADE_3(7000000);

		private final int baseSalary;

		Grade(int baseSalary) {
			this.baseSalary = baseSalary;
		}

		public int getBaseSalary() {
			return baseSalary;
		}
	}

	private enum JenisGender {
		LAKI_LAKI,
		PEREMPUAN
	}

	private JenisGender gender;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private Person spouse;
	private List<Person> children;

	public Employee(Biodata employeeBiodata, LocalDate dateJoined, boolean isForeigner, JenisGender gender) {
		this.employeeBiodata = employeeBiodata;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		children = new LinkedList<>();
	}

	public void setMonthlySalary(Grade grade) {
		int base = grade.getBaseSalary();
		if (isForeigner) {
			monthlySalary = (int) (base * 1.5);
		} else {
			monthlySalary = base;
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void addChild(String childName, String childIdNumber) {
		Person child = new Person();

		child.setName(childName);
		child.setID(childIdNumber);

		children.add(child);
	}

	public boolean isMarried() {
		return spouse != null && spouse.getID() != null && !spouse.getID().isEmpty();
	}

	public int getMonthWorking() {
		LocalDate date = LocalDate.now();
		int monthWorkingInYear;
		if (date.getYear() == dateJoined.getYear()) {
			return monthWorkingInYear = date.getMonthValue() - dateJoined.getMonthValue();
		} else {
			return monthWorkingInYear = 12;
		}
	}

	public int getAnnualIncomeTax() {
		boolean marriedStatus = isMarried();
		int monthWorkingInYear = getMonthWorking();

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				marriedStatus, children.size());
	}

	public Biodata getEmployeeBiodata() {
		return employeeBiodata;
	}

	public void setEmployeeBiodata(Biodata employeeBiodata) {
		this.employeeBiodata = employeeBiodata;
	}

	public LocalDate getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(LocalDate dateJoined) {
		this.dateJoined = dateJoined;
	}

	public JenisGender getGender() {
		return gender;
	}

	public void setGender(JenisGender gender) {
		this.gender = gender;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}
}
