package Oops.HRManagement;

public class Main {
    public static void main(String[] args) {
        Employee fullTimeEmployee = new FullTime("Alice", EmployeeType.FULL_TIME, 60000, "Engineering");
        fullTimeEmployee.register();
        ((Save) fullTimeEmployee).save();

        Employee intern = new Intern("Bob", EmployeeType.INTERN, "XYZ University", "3 months");
        intern.register();
        ((Save) intern).save();

        System.out.println("Employee Type: " + fullTimeEmployee.employeeType);
        System.out.println("Employee Type: " + intern.employeeType);
    }
}
