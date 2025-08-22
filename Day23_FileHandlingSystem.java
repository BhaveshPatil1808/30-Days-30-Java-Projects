import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day23_FileHandlingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "sample.txt";

        try {
            // 1️⃣ Create a File
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("📄 File created: " + file.getName());
            } else {
                System.out.println("⚠️ File already exists.");
            }

            // 2️⃣ Write to File
            System.out.print("✍️ Enter text to write into the file: ");
            sc.nextLine(); // consume newline
            String data = sc.nextLine();

            FileWriter writer = new FileWriter(fileName);
            writer.write(data);
            writer.close();
            System.out.println("✅ Successfully wrote to the file.");

            // 3️⃣ Read from File
            System.out.println("\n📖 Reading file content:");
            FileReader reader = new FileReader(fileName);
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("❌ An error occurred.");
            e.printStackTrace();
        }

        sc.close();
    }
}
