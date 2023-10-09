package exercise3;

import javax.swing.*;

public abstract class Mortgage implements MortgageConstants
{
    private int mortgageNumber;
    private String customerName;
    private double amount;
    private float interestRate; //Note: Interest Rate is prime rate + additional rate on mortgage type.
    private int term;

    public Mortgage(int mortgageNumber, String customerName, double amount, float interestRate, int term)
    {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        setAmount(amount);
        this.interestRate = interestRate;
        setTerm(term);
    }

    public void getMortgageInfo()
    {
        String mortgageDetails = "Mortgage Number: " + mortgageNumber +
                ", Customer Name: " + customerName +
                ", Amount: $" + amount +
                ", Interest Rate: " + interestRate + "% " +
                ", Term: " + term + " year(s)";

        JOptionPane.showMessageDialog(null, mortgageDetails, "Mortgage Details", JOptionPane.INFORMATION_MESSAGE);
    }

    //region getters
    public int getMortgageNumber()
    {
        return mortgageNumber;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public double getAmount()
    {
        return amount;
    }

    public float getInterestRate()
    {
        return interestRate;
    }

    public int getTerm()
    {
        return term;
    }
    //endregion

    //region setters
    public void setMortgageNumber(int mortgageNumber)
    {
        this.mortgageNumber = mortgageNumber;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setAmount(double amount)
    {
        this.amount = Math.min(amount, MAX_AMOUNT);
    }

    public void setInterestRate(float interestRate)
    {
        this.interestRate = interestRate;
    }

    public void setTerm(int term)
    {
        if (term != LONG_TERM && term != MEDIUM_TERM)
        {
            term = SHORT_TERM;
        }

        this.term = term;
    }
    //endregion
}
