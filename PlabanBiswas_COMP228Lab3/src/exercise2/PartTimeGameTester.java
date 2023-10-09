package exercise2;

public class PartTimeGameTester extends GameTester
{
    private static final int PART_TIME_HOURLY_WAGE = 20;

    private int numHrs;

    public PartTimeGameTester(String name)
    {
        super(name, false);
    }

    public void setNumHrs(int numHrs)
    {
        this.numHrs = numHrs;
    }

    @Override
    public int getSalary()
    {
        return PART_TIME_HOURLY_WAGE * numHrs;
    }

    @Override
    public void display()
    {
        System.out.println("Tester Name: " + getName());
        System.out.println("Tester Type: Part - Time");
        System.out.println("Tester Salary: $" + getSalary());
    }
}
