package exercise2;

public class FullTimeGameTester extends GameTester
{
    private static final int FULL_TIME_BASE_SALARY = 3000;

    public FullTimeGameTester(String name)
    {
        super(name, true);
    }

    @Override
    public int getSalary()
    {
        return FULL_TIME_BASE_SALARY;
    }

    @Override
    public void display()
    {
        System.out.println("Tester Name: " + getName());
        System.out.println("Tester Type: Full - Time");
        System.out.println("Tester Salary: $" + getSalary());
    }
}
