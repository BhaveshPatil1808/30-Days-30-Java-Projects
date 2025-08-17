import java.util.Scanner;

// Class representing a Book
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("✅ You have borrowed: " + title);
        } else {
            System.out.println("❌ Book is not available.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("📚 You have returned: " + title);
        } else {
            System.out.println("⚠️ Book was not borrowed.");
        }
    }

    public void displayBook() {
        System.out.println("ID: " + bookId + " | Title: " + title + " | Author: " + author +
                " | Available: " + (isAvailable ? "Yes" : "No"));
    }
}

// Class representing the Library
class Library {
    private Book[] books;
    private int count;

    public Library(int size) {
        books = new Book[size];
        count = 0;
    }

    public void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
            System.out.println("📖 Book added successfully!");
        } else {
            System.out.println("⚠️ Library is full. Cannot add more books.");
        }
    }

    public void showBooks() {
        if (count == 0) {
            System.out.println("⚠️ No books in library.");
        } else {
            System.out.println("\n--- 📚 Library Books ---");
            for (int i = 0; i < count; i++) {
                books[i].displayBook();
            }
        }
    }

    public Book findBookById(int bookId) {
        for (int i = 0; i < count; i++) {
            if (books[i].getBookId() == bookId) {
                return books[i];
            }
        }
        return null;
    }
}

public class Day17_LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library(5); // max 5 books

        // Adding some books initially
        library.addBook(new Book(101, "Java Basics", "James Gosling"));
        library.addBook(new Book(102, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(103, "Clean Code", "Robert C. Martin"));

        int choice;
        do {
            System.out.println("\n--- 📘 Library Menu ---");
            System.out.println("1. Show Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    Book borrowBook = library.findBookById(borrowId);
                    if (borrowBook != null) {
                        borrowBook.borrowBook();
                    } else {
                        System.out.println("❌ Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    Book returnBook = library.findBookById(returnId);
                    if (returnBook != null) {
                        returnBook.returnBook();
                    } else {
                        System.out.println("❌ Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("👋 Exiting Library System...");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
