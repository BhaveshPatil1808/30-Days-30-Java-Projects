import java.util.Scanner;

class Student2 {
    private int rollNo;
    private String name;
    private int[] marks;
    private int total;
    private double average;
    private char grade;

    public Student2(int rollNo, String name, int subjects) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = new int[subjects];
    }

    public void enterMarks(Scanner sc) {
        total = 0;
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
            total += marks[i];
        }
        calculateResult();
    }

    private void calculateResult() {
        average = (double) total / marks.length;

        if (average >= 90) grade = 'A';
        else if (average >= 75) grade = 'B';
        else if (average >= 60) grade = 'C';
        else if (average >= 40) grade = 'D';
        else grade = 'F';
    }

    public void displayResult() {
        System.out.println("\n--- Result ---");
        System.out.println("Roll No: " + rollNo + ", Name: " + name);
        System.out.print("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println("\nTotal: " + total + ", Average: " + average + ", Grade: " + grade);
    }

    public int getRollNo() {
        return rollNo;
    }
}

public class Day20_StudentResultManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many students? ");
        int n = sc.nextInt();
        System.out.print("How many subjects? ");
        int subjects = sc.nextInt();

        Student2[] students = new Student2[n];

        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Name: ");
            String name = sc.nextLine();

            students[i] = new Student2(roll, name, subjects);
            students[i].enterMarks(sc);
        }

        int choice;
        do {
            System.out.println("\n===== Student Result Menu =====");
            System.out.println("1. Display All Results");
            System.out.println("2. Search Student by Roll No");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Student2 s : students) {
                        s.displayResult();
                    }
                    break;
                case 2:
                    System.out.print("Enter Roll No to search: ");
                    int roll = sc.nextInt();
                    boolean found = false;
                    for (Student2 s : students) {
                        if (s.getRollNo() == roll) {
                            s.displayResult();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Student not found!");
                    }
                    break;
                case 3:
                    System.out.println("🚪 Exiting Student Result Management...");
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 3);

        sc.close();
    }
}
