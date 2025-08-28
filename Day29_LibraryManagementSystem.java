import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book4 {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book4(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}

class Library1 {
    private List<Book4> books = new ArrayList<>();

    public void addBook(Book4 book) {
        books.add(book);
        System.out.println("✅ Book added: " + book.getTitle());
    }

    public void showAvailableBooks() {
        System.out.println("\n📚 Available Books:");
        for (Book4 b : books) {
            if (!b.isBorrowed()) {
                System.out.println(b);
            }
        }
    }

    public void borrowBook(int bookId) {
        for (Book4 b : books) {
            if (b.getId() == bookId && !b.isBorrowed()) {
                b.borrowBook();
                System.out.println("📖 You borrowed: " + b.getTitle());
                return;
            }
        }
        System.out.println("❌ Book not available!");
    }

    public void returnBook(int bookId) {
        for (Book4 b : books) {
            if (b.getId() == bookId && b.isBorrowed()) {
                b.returnBook();
                System.out.println("✅ You returned: " + b.getTitle());
                return;
            }
        }
        System.out.println("⚠️ Invalid return request!");
    }
}

public class Day29_LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library1 library = new Library1();

        // Preloaded books
        library.addBook(new Book4(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book4(2, "Java Programming", "James Gosling"));
        library.addBook(new Book4(3, "Clean Code", "Robert C. Martin"));

        int choice;
        do {
            System.out.println("\n===== 📚 Library Menu =====");
            System.out.println("1. Show Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> library.showAvailableBooks();
                case 2 -> {
                    System.out.print("Enter Book ID to borrow: ");
                    int id = sc.nextInt();
                    library.borrowBook(id);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = sc.nextInt();
                    library.returnBook(id);
                }
                case 4 -> System.out.println("👋 Exiting Library. Goodbye!");
                default -> System.out.println("⚠️ Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
