package exercise1;

public class Singer
{
    private String id;
    private String name;
    private String address;
    private String birthDate;
    private int countAlbumsPublished;


    //region Constructors
    public Singer()
    {
    }

    public Singer(String id)
    {
        this.id = id;
    }

    public Singer(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Singer(String id, String name, String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Singer(String id, String name, String address, String birthDate)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }

    public Singer(String id, String name, String address, String birthDate, int albumsPublished)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.countAlbumsPublished = albumsPublished;
    }
    //endregion Constructors

    //region Setters
    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
    }

    public void setCountAlbumsPublished(int countAlbumsPublished)
    {
        this.countAlbumsPublished = countAlbumsPublished;
    }

    // To set all Values
    public void setAllValues(String id, String name, String address, String birthDate, int albumsPublished)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.countAlbumsPublished = albumsPublished;
    }
    //endregion Setters

    //region Getters
    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getBirthDate()
    {
        return birthDate;
    }

    public int getCountAlbumsPublished()
    {
        return countAlbumsPublished;
    }
    //endregion Getters


    public void printAllValues()
    {
        System.out.println("ID: " + id + ", \nName: " + name + ", \nAddress: " + address + ", \nDate of Birth: " + birthDate + ", \nNumber of albums published: " + countAlbumsPublished);
    }

    @Override
    public String toString()
    {
        return "Singer - ID: " + id + ", Name: " + name + ", Address: " + address + ", Date of Birth: " + birthDate + ", Number of albums published: " + countAlbumsPublished;
    }
}
