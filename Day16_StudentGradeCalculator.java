import java.util.Scanner;

class Student1 {
    private String name;
    private int[] marks;
    private int total;
    private double average;
    private char grade;

    public Student1(String name, int subjectCount) {
        this.name = name;
        this.marks = new int[subjectCount];
    }

    public void enterMarks(Scanner scanner) {
        total = 0;
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
            total += marks[i];
        }
        calculateAverage();
        calculateGrade();
    }

    private void calculateAverage() {
        average = (double) total / marks.length;
    }

    private void calculateGrade() {
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 60) {
            grade = 'C';
        } else if (average >= 40) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    public void displayResult() {
        System.out.println("\n📘 Student Name: " + name);
        System.out.println("📊 Total Marks: " + total);
        System.out.println("📈 Average: " + average);
        System.out.println("🏆 Grade: " + grade);
    }
}

public class Day16_StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Number of Subjects: ");
        int subjects = scanner.nextInt();

        Student1 student = new Student1(name, subjects);
        student.enterMarks(scanner);
        student.displayResult();

        scanner.close();
    }
}
