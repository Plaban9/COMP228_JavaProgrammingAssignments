package exercise3;

import javax.swing.*;

public class PersonalMortgage extends Mortgage
{
    //Note: Interest Rate is prime rate + additional rate on mortgage type.
    public PersonalMortgage(int mortgageNumber, String customerName, double amount, int term)
    {
        super(mortgageNumber, customerName, amount, PRIME_RATE + 2, term);
    }

    public PersonalMortgage(int mortgageNumber, String customerName, double amount, float interestRate, int term)
    {
        super(mortgageNumber, customerName, amount, interestRate + 2, term);
    }

    @Override
    public void getMortgageInfo()
    {
        String mortgageDetails = "Mortgage Number: " + getMortgageNumber() +
                ", Customer Name: " + getCustomerName() +
                ", Amount: $" + getAmount() +
                ", Interest Rate: " + getInterestRate() + "% " +
                ", Term: " + getTerm() + " year(s)";

        JOptionPane.showMessageDialog(null, mortgageDetails, "Personal Mortgage", JOptionPane.INFORMATION_MESSAGE);
    }
}
