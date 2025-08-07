import java.util.Scanner;

public class Day4_PalindromeChecker {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔁 Palindrome Checker");
        System.out.print("Enter a word or number: ");
        String input = scanner.nextLine();

        String original = input.toLowerCase();
        String reversed = "";

        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }

        if (original.equals(reversed)) {
            System.out.println("✅ It's a palindrome!");
        } else {
            System.out.println("❌ Not a palindrome.");
        }

        scanner.close();
    }

}
