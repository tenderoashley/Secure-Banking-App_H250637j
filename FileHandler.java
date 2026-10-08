import java.io.*;
import java.util.*;

/**
 * Handles all reading from and writing to the plain-text data files
 * (users.txt, accounts.txt, transactions.txt). Centralizing file I/O here
 * keeps persistence logic separate from business logic (single responsibility).
 */
public class FileHandler {
    private static final String USERS_FILE = "users.txt";
    private static final String ACCOUNTS_FILE = "accounts.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File f = new File(USERS_FILE);
        if (!f.exists()) return users;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) users.add(User.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) pw.println(u.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public static List<BankAccount> loadAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        File f = new File(ACCOUNTS_FILE);
        if (!f.exists()) return accounts;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",");
                String accNum = p[0], owner = p[1], type = p[2];
                double balance = Double.parseDouble(p[3]);
                if (type.equals("SAVINGS")) {
                    accounts.add(new SavingsAccount(accNum, owner, balance));
                } else {
                    accounts.add(new CheckingAccount(accNum, owner, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
        return accounts;
    }

    public static void saveAccounts(List<BankAccount> accounts) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (BankAccount a : accounts) pw.println(a.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> list = new ArrayList<>();
        File f = new File(TRANSACTIONS_FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) list.add(Transaction.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return list;
    }

    public static void appendTransaction(Transaction t) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            pw.println(t.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}
