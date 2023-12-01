package exercise1;

public class Account
{
    private int balance;

    public Account(int balance)
    {
        this.balance = balance;
    }

    public synchronized void deposit(int amount)
    {
            balance += amount;

            System.out.println("Amount deposited: " + amount);
            checkBalance();
    }

    public synchronized void withdraw(int amount)
    {
            if (balance >= amount)
            {
                balance -= amount;
                System.out.println("Amount withdrawn: " + amount);
            }
            else
            {
                System.out.println("Insufficient balance to withdraw. Amount to withdraw: " + amount);
            }

            checkBalance();
    }

    public synchronized void checkBalance()
    {
        System.out.println("Current balance: " + balance);
    }

    @Override
    public String toString()
    {
        return "Account\n{" +
                "balance: " + balance +
                '}';
    }
}
