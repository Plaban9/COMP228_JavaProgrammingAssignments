package exercise1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest
{
    private final ArrayList<Transaction> transactions;

    public AccountTest()
    {
        transactions = new ArrayList<>();
    }

    public void runTest(int balance)
    {
        System.out.println("<======================================== STARTING TEST ========================================>");
        System.out.println("Created account with balance: " + balance);
        Account testAccount = new Account(balance);

        System.out.println("Performing transactions...");

        Transaction transaction1 = new Transaction(1500, testAccount, TransactionType.WITHDRAW);
        Transaction transaction2 = new Transaction(450, testAccount, TransactionType.DEPOSIT);
        Transaction transaction3 = new Transaction(800, testAccount, TransactionType.WITHDRAW);
        Transaction transaction4 = new Transaction(150, testAccount, TransactionType.DEPOSIT);
        Transaction transaction5 = new Transaction(50, testAccount, TransactionType.DEPOSIT);
        Transaction transaction6 = new Transaction(1000, testAccount, TransactionType.WITHDRAW);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);

        try (ExecutorService executorService = Executors.newFixedThreadPool(transactions.size()))
        {
            for (var transaction : transactions)
            {
                executorService.execute(transaction);
            }

            executorService.shutdown();

            while(!executorService.isTerminated())
            {
                // To know that all threads are done executing.
            }

            System.out.println("Performed transactions.");
            System.out.println("<======================================== TEST COMPLETED ========================================>");
        }
        catch (Exception exception)
        {
            System.out.println("Exception while performing thread test. Reason: " + exception.getMessage());
        }
    }
}
