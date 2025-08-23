import java.util.ArrayList;
import java.util.Scanner;

class Book3 {
    private int id;
    private String title;
    private boolean isIssued;

    public Book3(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return id + " - " + title + (isIssued ? " (Issued)" : " (Available)");
    }
}

public class Day24_LibraryManagementSystem {
    private static ArrayList<Book3> books = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-added books
        books.add(new Book3(1, "Java Basics"));
        books.add(new Book3(2, "Data Structures"));
        books.add(new Book3(3, "OOP Concepts"));
        books.add(new Book3(4, "Database Management"));

        int choice;
        do {
            System.out.println("\n===== 📚 Library Management System =====");
            System.out.println("1. View All Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    System.out.println("👋 Exiting Library System...");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void viewBooks() {
        System.out.println("\n📖 Book List:");
        for (Book3 book : books) {
            System.out.println(book);
        }
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        for (Book3 book : books) {
            if (book.getId() == id) {
                if (!book.isIssued()) {
                    book.issueBook();
                    System.out.println("✅ Book issued successfully.");
                } else {
                    System.out.println("⚠️ Book already issued.");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        for (Book3 book : books) {
            if (book.getId() == id) {
                if (book.isIssued()) {
                    book.returnBook();
                    System.out.println("✅ Book returned successfully.");
                } else {
                    System.out.println("⚠️ This book was not issued.");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }
}
