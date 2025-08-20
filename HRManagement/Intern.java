package HRManagement;

public class Intern extends Employee {
    private String collegeName;
    private String internshipDuration;

    public Intern(String name, EmployeeType employeeType, String collegeName, String internshipDuration) {
        super(name, employeeType);
        this.collegeName = collegeName;
        this.internshipDuration = internshipDuration;
    }

    @Override
    void register() {
        System.out.println("Intern " + name + " registered from " + collegeName + " for " + internshipDuration);
    }

    @Override
    void save() {
        System.out.println("Intern details saved for " + name);
    }
    
}
