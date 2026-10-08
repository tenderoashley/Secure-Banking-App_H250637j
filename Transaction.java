import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents a single deposit or withdrawal event, used for transaction history. */
public class Transaction {
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String accountNumber;
    private String type;
    private double amount;
    private String timestamp;

    public Transaction(String accountNumber, String type, double amount, String timestamp) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Transaction(String accountNumber, String type, double amount) {
        this(accountNumber, type, amount, LocalDateTime.now().format(FORMAT));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String toFileString() {
        return accountNumber + "," + type + "," + amount + "," + timestamp;
    }

    public static Transaction fromFileString(String line) {
        String[] p = line.split(",", 4);
        return new Transaction(p[0], p[1], Double.parseDouble(p[2]), p[3]);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-10s %10.2f  (Acc: %s)", timestamp, type, amount, accountNumber);
    }
}
