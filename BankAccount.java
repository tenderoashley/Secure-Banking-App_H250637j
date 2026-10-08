/**
 * Abstract base class for all account types.
 * Demonstrates encapsulation (private/protected fields with controlled access)
 * and abstraction (withdraw rules differ per subclass).
 */
public abstract class BankAccount {
    protected String accountNumber;
    protected String owner;
    protected double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    /** Each account type enforces its own withdrawal rules. Returns false if not allowed. */
    public abstract boolean withdraw(double amount);

    public abstract String getAccountType();

    /** Serializes this account as a single line for storage in accounts.txt */
    public String toFileString() {
        return accountNumber + "," + owner + "," + getAccountType() + "," + balance;
    }
}
