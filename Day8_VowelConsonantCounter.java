import java.util.Scanner;

public class Day8_VowelConsonantCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔤 Vowel & Consonant Counter");
        System.out.print("Enter a word or sentence: ");
        String input = scanner.nextLine();

        int vowels = 0;
        int consonants = 0;

        input = input.toLowerCase();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("✅ Vowels: " + vowels);
        System.out.println("✅ Consonants: " + consonants);

        scanner.close();
    }
}
