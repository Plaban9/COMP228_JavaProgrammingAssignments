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
                System.out.println("Amount withdrawn: " + amount + ", Balance: " + balance);
            }
            else
            {
                System.out.println("Insufficient Balance to withdraw. Amount to withdraw: " + amount);
            }

            checkBalance();
    }

    public synchronized void checkBalance()
    {
        System.out.println("Current Balance: " + balance);
    }

    @Override
    public String toString()
    {
        return "Account\n{" +
                "balance: " + balance +
                '}';
    }
}
