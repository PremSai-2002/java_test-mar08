package java_test;

abstract class BankAccount {

    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount + ", New Balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }
}

interface Transaction {

    void transfer(BankAccount toAccount, double amount);
}

class SavingsAccount extends BankAccount implements Transaction {

    private static final double MIN_BALANCE = 500;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount + ", New Balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal denied! Minimum balance of ₹" + MIN_BALANCE + " must be maintained.");
        }
    }

    @Override
    public void transfer(BankAccount toAccount, double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            toAccount.deposit(amount);
            System.out.println("Transferred: ₹" + amount + " to Account: " + toAccount.accountNumber);
        } else {
            System.out.println("Transfer failed! Minimum balance of ₹" + MIN_BALANCE + " must be maintained.");
        }
    }
}

class CurrentAccount extends BankAccount implements Transaction {

    private static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount + ", New Balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal denied! Overdraft limit of ₹" + OVERDRAFT_LIMIT + " exceeded.");
        }
    }

    @Override
    public void transfer(BankAccount toAccount, double amount) {
        if (balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            toAccount.deposit(amount);
            System.out.println("Transferred: ₹" + amount + " to Account: " + toAccount.accountNumber);
        } else {
            System.out.println("Transfer failed! Overdraft limit exceeded.");
        }
    }
}

public class bank {

    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("SA123", 2000);
        CurrentAccount current = new CurrentAccount("CA456", 1000);

        savings.deposit(500);
        savings.withdraw(1800);
        savings.withdraw(500);

        current.withdraw(3000);
        current.withdraw(7000);

        savings.transfer(current, 1000);
        current.transfer(savings, 2000);
    }
}
