package HRManagement;

public abstract class Employee {
    String name;
    EmployeeType employeeType;
    
    Employee(String name, EmployeeType employeeType) {
        this.name = name;
        this.employeeType = employeeType;
    }
    abstract void register();
    abstract void save();
}
