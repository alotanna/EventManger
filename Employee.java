package com.example.dsaproject;

public class Employee extends Person {
    private String employeeId;
    private String department;

    // Constructor
    public Employee(String name, int age, String email, String employeeId, String department) {
        super(name, age, email);
        this.employeeId = employeeId;
        this.department = department;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Display employee's details including inherited details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
    }

    // Check if the employee belongs to a specific department
    public boolean isInDepartment(String department) {
        return this.department.equalsIgnoreCase(department);
    }

    // Override toString method
    @Override
    public String toString() {
        return super.toString() + ", EmployeeID=" + employeeId + ", Department=" + department;
    }
}
