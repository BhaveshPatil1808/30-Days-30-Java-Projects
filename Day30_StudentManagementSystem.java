import java.sql.*;
import java.util.Scanner;

public class Day30_StudentManagementSystem {
    // Database connection details
    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";      // change if needed
    static final String PASS = "root";      // change if needed

    // Method to insert student
    public static void addStudent(String name, int age, String course) {
        String query = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to view all students
    public static void viewStudents() {
        String query = "SELECT * FROM students";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("\n📋 Student Records:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getInt("age") + " | " +
                                   rs.getString("course"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update student
    public static void updateStudent(int id, String name, int age, String course) {
        String query = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Student updated successfully!");
            else System.out.println("⚠️ Student not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete student
    public static void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("🗑️ Student deleted successfully!");
            else System.out.println("⚠️ Student not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== 🎓 Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter course: ");
                    String course = sc.nextLine();
                    addStudent(name, age, course);
                }
                case 2 -> viewStudents();
                case 3 -> {
                    System.out.print("Enter student ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new course: ");
                    String course = sc.nextLine();
                    updateStudent(id, name, age, course);
                }
                case 4 -> {
                    System.out.print("Enter student ID to delete: ");
                    int id = sc.nextInt();
                    deleteStudent(id);
                }
                case 5 -> System.out.println("👋 Exiting Student Management System...");
                default -> System.out.println("⚠️ Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
