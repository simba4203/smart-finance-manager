
import java.util.ArrayList;

public class User {

    private String name;
    private int pin;
    private double balance;

    private ArrayList<Transaction> history;

    public User(String name, int pin) {
        this.name = name;
        this.pin = pin;
        this.balance = 0.0;
        this.history = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add(new Transaction("Deposit", amount, "Money deposited"));
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        history.add(new Transaction("Withdraw", amount, "Money withdrawn"));
        return true;
    }

    public void addTransaction(Transaction t) {
        history.add(t);
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction t : history) {
            t.showTransaction();
        }
    }
}

