
import java.util.*;
import java.util.logging.FileHandler;

public class main {

    static Scanner sc = new Scanner(System.in);

    // Store all users
    static HashMap<Integer, User> users = Filehandler.loadUsers();

    // Auto account number
    static int accountCounter = 1001;

    public static void main(String[] args) {

        System.out.println("== SMART FINANCE MANAGER ==");

        while (true) {
            showMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Thanks for using our system!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Main Menu
    public static void showMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
    }

    // Create Account
    public static void createAccount() {
        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        int accNo = accountCounter++;

        users.put(accNo, new User(name, pin));

        System.out.println("Account created successfully for " + name);
        System.out.println("Your Account Number: " + accNo);
    }

    // Login
    public static void login() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (users.containsKey(accNo)) {
            User user = users.get(accNo);

            if (user.getPin() == pin) {
                System.out.println("Login Successful! Welcome " + user.getName());
                userMenu(user);
            } else {
                System.out.println("Wrong PIN!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    // User Menu
    public static void userMenu(User user) {

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. View History");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance(user);
                    break;
                case 2:
                    deposit(user);
                    break;
                case 3:
                    withdraw(user);
                    break;
                case 4:
                    transferMoney(user);
                    break;
                case 5:
                    user.showHistory();
                    break;
                case 6:
                    System.out.println("Logged out!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Check Balance
    public static void checkBalance(User user) {
        System.out.println("Your Balance: ₹" + user.getBalance());
    }

    // Deposit
    public static void deposit(User user) {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        user.deposit(amount);

        System.out.println("Deposit successful!");
    }

    // Withdraw
    public static void withdraw(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        boolean success = user.withdraw(amount);

        if (success) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Transfer Money
    public static void transferMoney(User sender) {

        System.out.print("Enter Receiver Account Number: ");
        int receiverAccNo = sc.nextInt();

        if (!users.containsKey(receiverAccNo)) {
            System.out.println("Receiver account not found!");
            return;
        }

        User receiver = users.get(receiverAccNo);

        System.out.println("Receiver Name: " + receiver.getName());

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance!");
            return;
        }

        // Perform transfer
        sender.withdraw(amount);
        receiver.deposit(amount);

        // Add transaction for sender
        sender.addTransaction(
            new Transaction("Transfer", amount, "Sent to " + receiver.getName())
        );

        // Add transaction for receiver
        receiver.addTransaction(
            new Transaction("Received", amount, "Received from " + sender.getName())
        );

        System.out.println("Transfer successful!");
    }
}


