import java.util.List;
import java.util.Scanner;

/** Entry point: console UI loop for the Secure Banking Application. */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println(" Welcome to Secure Banking Application");
        System.out.println("=====================================");

        while (true) {
            if (bank.getCurrentUser() == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n1. Login\n2. Register\n3. Exit");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": login(); break;
            case "2": register(); break;
            case "3": System.out.println("Goodbye!"); System.exit(0); break;
            default: System.out.println("Invalid option.");
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String u = scanner.nextLine().trim();
        System.out.print("Password: ");
        String p = scanner.nextLine().trim();
        if (bank.login(u, p)) {
            System.out.println("Login successful. Welcome, " + u + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register() {
        System.out.print("Choose a username: ");
        String u = scanner.nextLine().trim();
        System.out.print("Choose a password: ");
        String p = scanner.nextLine().trim();
        if (bank.register(u, p)) {
            System.out.println("Registration successful. You can now log in.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- Main Menu (" + bank.getCurrentUser().getUsername() + ") ---");
        System.out.println("1. Create Account\n2. View My Accounts\n3. Deposit\n4. Withdraw" +
                "\n5. Transaction History\n6. Logout");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": createAccount(); break;
            case "2": viewAccounts(); break;
            case "3": deposit(); break;
            case "4": withdraw(); break;
            case "5": viewHistory(); break;
            case "6": bank.logout(); System.out.println("Logged out."); break;
            default: System.out.println("Invalid option.");
        }
    }

    private static void createAccount() {
        System.out.print("Account type (SAVINGS/CHECKING): ");
        String type = scanner.nextLine().trim().toUpperCase();
        System.out.print("Initial deposit: ");
        double amt = readDouble();
        BankAccount acc = bank.createAccount(type, amt);
        System.out.println("Account created: " + acc.getAccountNumber() + " (" + acc.getAccountType() + ")");
    }

    private static void viewAccounts() {
        List<BankAccount> accs = bank.getAccountsForCurrentUser();
        if (accs.isEmpty()) {
            System.out.println("You have no accounts yet.");
            return;
        }
        for (BankAccount a : accs) {
            System.out.printf("%s | %s | Balance: %.2f%n", a.getAccountNumber(), a.getAccountType(), a.getBalance());
        }
    }

    private static void deposit() {
        System.out.print("Account number: ");
        String acc = scanner.nextLine().trim();
        System.out.print("Amount: ");
        double amt = readDouble();
        if (bank.deposit(acc, amt)) {
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed. Check the account number.");
        }
    }

    private static void withdraw() {
        System.out.print("Account number: ");
        String acc = scanner.nextLine().trim();
        System.out.print("Amount: ");
        double amt = readDouble();
        if (bank.withdraw(acc, amt)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed. Check the account number, balance, or overdraft limit.");
        }
    }

    private static void viewHistory() {
        System.out.print("Account number: ");
        String acc = scanner.nextLine().trim();
        List<Transaction> list = bank.getTransactionsForAccount(acc);
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction t : list) System.out.println(t);
    }

    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
