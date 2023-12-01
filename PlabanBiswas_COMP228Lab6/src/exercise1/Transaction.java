package exercise1;

import java.util.Random;

public class Transaction implements Runnable
{
    private final int transactionId;
    private final int amount;
    private final Account account;
    private final TransactionType transactionType;

    private static int transactionCount = 0;

    private final static Random random = new Random();

    public Transaction(int amount, Account account, TransactionType transactionType)
    {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        transactionCount++;
        this.transactionId = transactionCount;
    }

    @Override
    public synchronized void run()
    {
        synchronized (account)
        {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("|                              TRANSACTION                               |");
            System.out.println("--------------------------------------------------------------------------");

            boolean isWriteOperation = (transactionType == TransactionType.DEPOSIT || transactionType == TransactionType.WITHDRAW);
            System.out.println("Performing transaction with ID: " + transactionId);
            System.out.println("Request for " + transactionType.name() + (isWriteOperation ? ", amount: " + amount : ""));
            System.out.println("Processing.... ");

            try
            {
                var sleepTime = random.nextInt(1000, 5000);
                System.out.println("Please wait for " + (sleepTime / 1000f) + " second(s).");
                Thread.sleep(sleepTime);
            }
            catch (Exception e)
            {
                System.out.println("Thread  interrupted.");
            }

            switch (transactionType)
            {
                case DEPOSIT:
                    account.deposit(amount);
                    break;
                case WITHDRAW:
                    account.withdraw(amount);
                    break;
                case CHECK_BALANCE:
                    account.checkBalance();
                    break;
            }

            System.out.println("--------------------------------------------------------------------------\n");
        }
    }

    @Override
    public String toString()
    {
        return "Transaction\n{" +
                "Transaction ID: " + transactionId +
                ", Amount:  " + amount +
                ", Transaction Type:" + transactionType +
                ", Account: " + account.toString() +
                '}';
    }
}
