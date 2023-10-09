package exercise1;

import javax.swing.*;

public class Life extends Insurance
{
    public Life()
    {
        super("Life");
    }

    @Override
    public void setInsuranceCost(double monthlyCost)
    {
        this.monthlyCost = monthlyCost;
    }

    @Override
    public void display()
    {
        JOptionPane.showMessageDialog(null, "Insurance type: " + getInsuranceType() + "\nMonthly Cost: $" + getMonthlyCost(), "Insurance Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
