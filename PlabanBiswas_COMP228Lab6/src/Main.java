import exercise1.AccountTest;

public class Main
{
    private static final int LOW_BALANCE = 600; // For High Miss cases
    private static final int ADEQUATE_BALANCE = 1500; // For medium Miss cases
    private static final int HIGH_BALANCE = 3000; // For No miss cases

    public static void main(String[] args)
    {
        AccountTest accountTest = new AccountTest();
        accountTest.runTest(LOW_BALANCE);
    }
}