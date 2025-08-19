import java.util.Scanner;

class Employee2 {
    private int id;
    private String name;
    private double salary;

    public Employee2(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void displayEmployee() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: ₹" + salary);
    }

    public void updateSalary(double newSalary) {
        this.salary = newSalary;
        System.out.println("✅ Salary updated for " + name + " to ₹" + newSalary);
    }
}

public class Day19_EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many employees you want to add? ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Employee2[] employees = new Employee2[n];

        // Input employee details
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            employees[i] = new Employee(id, name, salary);
        }

        int choice;
        do {
            System.out.println("\n===== Employee Management Menu =====");
            System.out.println("1. Display All Employees");
            System.out.println("2. Search Employee by ID");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Employee List ---");
                    for (Employee2 emp : employees) {
                        emp.displayEmployee();
                    }
                    break;
                case 2:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    boolean found = false;
                    for (Employee2 emp : employees) {
                        if (emp.getId() == searchId) {
                            emp.displayEmployee();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Employee not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Employee ID to update salary: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    boolean updated = false;
                    for (Employee2 emp : employees) {
                        if (emp.getId() == updateId) {
                            emp.updateSalary(newSalary);
                            updated = true;
                            break;
                        }
                    }
                    if (!updated) {
                        System.out.println("❌ Employee not found!");
                    }
                    break;
                case 4:
                    System.out.println("🚪 Exiting Employee Management System...");
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 4);

        scanner.close();
    }
}
