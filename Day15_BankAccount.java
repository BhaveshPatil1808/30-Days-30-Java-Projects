import java.util.Scanner;

class BankAccount1 {
    private String accountHolder;
    private double balance;

    public BankAccount1(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ ₹" + amount + " deposited successfully.");
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("❌ Invalid withdrawal amount or insufficient funds!");
        }
    }

    public void displayBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }
}

public class Day15_BankAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ₹");
        double balance = scanner.nextDouble();

        BankAccount1 account = new BankAccount1(name, balance);

        while (true) {
            System.out.println("\n=== Banking Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    account.deposit(scanner.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    account.withdraw(scanner.nextDouble());
                    break;
                case 3:
                    account.displayBalance();
                    break;
                case 4:
                    System.out.println("👋 Thank you for using our banking system!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}
