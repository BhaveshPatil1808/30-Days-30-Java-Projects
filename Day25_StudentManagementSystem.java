import java.util.ArrayList;
import java.util.Scanner;

class Student3 {
    private int id;
    private String name;
    private String course;

    public Student3(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Course: " + course;
    }
}

public class Day25_StudentManagementSystem {
    private static ArrayList<Student3> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== 🎓 Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("👋 Exiting Student System...");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        students.add(new Student3(id, name, course));
        System.out.println("✅ Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found.");
            return;
        }
        System.out.println("\n📋 Student List:");
        for (Student3 s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();
        for (Student3 s : students) {
            if (s.getId() == id) {
                System.out.println("✅ Student Found: " + s);
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        for (Student3 s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("🗑️ Student deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }
}
