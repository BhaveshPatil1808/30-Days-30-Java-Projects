import java.util.Scanner;

class Employee {
    private String name;
    private String designation;
    private double basicSalary;
    private double overtimeHours;
    private double overtimeRate;

    public Employee(String name, String designation, double basicSalary, double overtimeHours, double overtimeRate) {
        this.name = name;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
    }

    public double calculateGrossSalary() {
        return basicSalary + (overtimeHours * overtimeRate);
    }

    public double calculateDeductions() {
        // Assume 10% PF deduction
        return basicSalary * 0.10;
    }

    public double calculateNetSalary() {
        return calculateGrossSalary() - calculateDeductions();
    }

    public void displayPayroll() {
        System.out.println("\n💼 Employee Payroll");
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("Basic Salary: ₹" + basicSalary);
        System.out.println("Overtime Pay: ₹" + (overtimeHours * overtimeRate));
        System.out.println("Deductions (PF): ₹" + calculateDeductions());
        System.out.println("Gross Salary: ₹" + calculateGrossSalary());
        System.out.println("Net Salary: ₹" + calculateNetSalary());
    }
}

public class Day13_EmployeePayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("💼 Employee Payroll System");
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter basic salary: ₹");
        double basicSalary = scanner.nextDouble();

        System.out.print("Enter overtime hours: ");
        double overtimeHours = scanner.nextDouble();

        System.out.print("Enter overtime rate per hour: ₹");
        double overtimeRate = scanner.nextDouble();

        Employee emp = new Employee(name, designation, basicSalary, overtimeHours, overtimeRate);
        emp.displayPayroll();

        scanner.close();
    }
}
