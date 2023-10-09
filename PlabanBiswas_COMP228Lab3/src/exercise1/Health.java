package exercise1;

import javax.swing.JOptionPane;

public class Health extends Insurance
{
    public Health()
    {
        super("Health");
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
