/** Savings account: cannot be withdrawn below a fixed minimum balance. */
public class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 100.0;

    public SavingsAccount(String accountNumber, String owner, double balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (balance - amount < MIN_BALANCE) return false;
        balance -= amount;
        return true;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
