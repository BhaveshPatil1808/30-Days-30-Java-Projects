import java.util.Scanner;

class ATM {
    private double balance;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited: ₹" + amount);
            checkBalance();
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn: ₹" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
        } else {
            System.out.println("❌ Invalid withdrawal amount!");
        }
    }
}

public class Day22_ATMSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(5000); // initial balance

        int choice;
        do {
            System.out.println("\n===== 🏦 ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("🚪 Exiting ATM. Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
