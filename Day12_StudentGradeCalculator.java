import java.util.Scanner;

class Student {
    private String name;
    private double[] marks;

    public Student(String name, int subjects) {
        this.name = name;
        marks = new double[subjects];
    }

    public void enterMarks(Scanner scanner) {
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextDouble();
        }
    }

    public double calculateAverage() {
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum / marks.length;
    }

    public String calculateGrade() {
        double average = calculateAverage();
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }

    public void displayResult() {
        System.out.println("\n📄 Student: " + name);
        System.out.println("Average Marks: " + calculateAverage());
        System.out.println("Grade: " + calculateGrade());
    }
}

public class Day12_StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎓 Student Grade Calculator");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of subjects: ");
        int subjects = scanner.nextInt();

        Student student = new Student(name, subjects);

        student.enterMarks(scanner);
        student.displayResult();

        scanner.close();
    }
}
