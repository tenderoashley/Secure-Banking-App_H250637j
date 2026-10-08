/** Checking account: allows a small overdraft. */
public class CheckingAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 200.0;

    public CheckingAccount(String accountNumber, String owner, double balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (balance - amount < -OVERDRAFT_LIMIT) return false;
        balance -= amount;
        return true;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }
}
