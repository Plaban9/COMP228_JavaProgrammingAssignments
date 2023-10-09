package exercise1;

// Abstract Super Class
public abstract class Insurance
{
    protected String insuranceType;
    protected double monthlyCost;

    public Insurance(String type)
    {
        insuranceType = type;
    }

    //region Getters
    public String getInsuranceType()
    {
        return insuranceType;
    }

    public double getMonthlyCost()
    {
        return monthlyCost;
    }
    //endregion

    //region Abstract Methods
    public abstract void setInsuranceCost(double monthlyCost);

    public abstract void display();
    //endregion
}
