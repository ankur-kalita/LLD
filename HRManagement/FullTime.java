package HRManagement;

public class FullTime extends Employee  {
    private double salary;
    private String department;

    public FullTime(String name, EmployeeType employeeType, double salary, String department) {
        super(name, employeeType);
        this.salary = salary;
        this.department = department;
    }

    @Override
    void register() {
        System.out.println("Full-time employee " + name + " registered in " + department + " with a salary of " + salary);
    }

    @Override
    void save() {
        System.out.println("Full-time employee details saved for " + name);
    }
    
}
