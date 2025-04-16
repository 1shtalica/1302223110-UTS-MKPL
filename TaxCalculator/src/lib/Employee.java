package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	private String idNumber;

	private String employeeId;
	private String firstName;
	private String lastName;
	private String address;

	private LocalDate dateJoined;

	private int monthWorkingInYear;

	private boolean isForeigner;

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

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			LocalDate dateJoined, boolean isForeigner, JenisGender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		children = new LinkedList<Person>(children);
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
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

	public int getAnnualIncomeTax() {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.

		boolean marriedStatus = isMarried();
		LocalDate date = LocalDate.now();

		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonthValue();
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				marriedStatus, children.size());
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
