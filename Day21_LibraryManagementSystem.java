import java.util.ArrayList;
import java.util.Scanner;

class Book2 {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book2(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // by default available
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("✅ Book borrowed successfully!");
        } else {
            System.out.println("❌ Book is already borrowed!");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("✅ Book returned successfully!");
        } else {
            System.out.println("❌ Book was not borrowed!");
        }
    }

    public void displayBook() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Status: " + (isAvailable ? "Available" : "Borrowed"));
    }
}

public class Day21_LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book2> library = new ArrayList<>();

        int choice;
        do {
            System.out.println("\n===== 📚 Library Management Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    library.add(new Book2(id, title, author));
                    System.out.println("✅ Book added successfully!");
                    break;

                case 2:
                    if (library.isEmpty()) {
                        System.out.println("📭 No books in library!");
                    } else {
                        System.out.println("\n--- Library Books ---");
                        for (Book2 b : library) {
                            b.displayBook();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = sc.nextInt();
                    boolean borrowed = false;
                    for (Book2 b : library) {
                        if (b.getId() == borrowId) {
                            b.borrowBook();
                            borrowed = true;
                            break;
                        }
                    }
                    if (!borrowed) System.out.println("❌ Book not found!");
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    boolean returned = false;
                    for (Book2 b : library) {
                        if (b.getId() == returnId) {
                            b.returnBook();
                            returned = true;
                            break;
                        }
                    }
                    if (!returned) System.out.println("❌ Book not found!");
                    break;

                case 5:
                    System.out.println("🚪 Exiting Library System...");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
