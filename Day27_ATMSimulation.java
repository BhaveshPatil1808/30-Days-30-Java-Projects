import java.util.Scanner;

class ATM1 {
    private double balance;

    public ATM1(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Successfully deposited ₹" + amount);
        } else {
            System.out.println("⚠️ Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("⚠️ Enter a valid withdrawal amount!");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance! Available: ₹" + balance);
        } else {
            balance -= amount;
            System.out.println("💸 Successfully withdrawn ₹" + amount);
        }
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }
}

public class Day27_ATMSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM1 atm = new ATM1(1000.0); // initial balance ₹1000

        int choice;
        do {
            System.out.println("\n===== 🏧 ATM Menu =====");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    atm.deposit(depositAmount);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    atm.withdraw(withdrawAmount);
                }
                case 3 -> atm.checkBalance();
                case 4 -> System.out.println("👋 Thank you for using ATM. Goodbye!");
                default -> System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
