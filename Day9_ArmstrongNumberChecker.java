import java.util.Scanner;

public class Day9_ArmstrongNumberChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔢 Armstrong Number Checker");
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        int originalNumber = number;
        int digits = String.valueOf(number).length();
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }

        if (sum == originalNumber) {
            System.out.println("✅ " + originalNumber + " is an Armstrong number.");
        } else {
            System.out.println("❌ " + originalNumber + " is NOT an Armstrong number.");
        }

        scanner.close();
    }
}
