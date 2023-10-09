package exercise3;

import javax.swing.*;

public class BusinessMortgage extends Mortgage
{
    //Note: Interest Rate is prime rate + additional rate on mortgage type.
    public BusinessMortgage(int mortgageNumber, String customerName, double amount, int term)
    {
        super(mortgageNumber, customerName, amount, PRIME_RATE + 1, term);
    }

    public BusinessMortgage(int mortgageNumber, String customerName, double amount, float interestRate, int term)
    {
        super(mortgageNumber, customerName, amount, interestRate + 1, term);
    }

    @Override
    public void getMortgageInfo()
    {
        String mortgageDetails = "Mortgage Number: " + getMortgageNumber() +
                ", Customer Name: " + getCustomerName() +
                ", Amount: $" + getAmount() +
                ", Interest Rate: " + getInterestRate() + "% " +
                ", Term: " + getTerm() + " year(s)";

        JOptionPane.showMessageDialog(null, mortgageDetails, "Business Mortgage", JOptionPane.INFORMATION_MESSAGE);
    }
}
