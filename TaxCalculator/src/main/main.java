package main;

import lib.Biodata;
import lib.Employee;
import lib.Person;

import java.time.LocalDate;

public class main {
    public static void main(String[] args) {

        Biodata biodata = new Biodata();
        biodata.setEmployeeId("emp01");
        biodata.setFirstName("Rudi");
        biodata.setLastName("Hartono");
        biodata.setAddress("123456789");

        Employee emp = new Employee(
                biodata,
                LocalDate.of(2023, 5, 10),
                false,
                Employee.JenisGender.LAKI_LAKI);

        emp.setMonthlySalary(Employee.Grade.GRADE_2);
        emp.setAdditionalIncome(1000000);
        emp.setAnnualDeductible(2000000);

        Person spouse = new Person();
        spouse.setName("Siti Aminah");
        spouse.setID("987654321");
        emp.setSpouse(spouse);

        emp.addChild("Andi", "001");
        emp.addChild("Budi", "002");

        int tax = emp.getAnnualIncomeTax();
        System.out.println("Pajak tahunan: Rp" + tax);
    }
}
