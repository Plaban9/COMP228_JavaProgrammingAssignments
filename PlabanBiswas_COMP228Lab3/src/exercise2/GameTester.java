package exercise2;

public abstract class GameTester
{
    private String name;
    private boolean employmentStatus;

    public GameTester(String name, boolean employmentStatus)
    {
        this.name = name;
        this.employmentStatus = employmentStatus;
    }

    //region Getters
    public String getName()
    {
        return name;
    }

    public boolean getEmploymentStatus()
    {
        return employmentStatus;
    }
    //endregion

    //region Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmploymentStatus(boolean employmentStatus)
    {
        this.employmentStatus = employmentStatus;
    }
    //endregion

    public abstract int getSalary();

    public abstract void display();
}
