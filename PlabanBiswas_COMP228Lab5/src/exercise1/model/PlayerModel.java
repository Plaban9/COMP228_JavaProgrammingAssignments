package exercise1.model;

public class PlayerModel
{
    private int playerId;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String province;
    private String phoneNumber;

    public PlayerModel(int playerId, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber)
    {
        setPlayerId(playerId);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPostalCode(postalCode);
        setProvince(province);
        setPhoneNumber(phoneNumber);
    }

    //region GETTERS

    public int getPlayerId()
    {
        return playerId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getProvince()
    {
        return province;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    //endregion

    //region SETTERS

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    //endregion

    @Override
    public String toString()
    {

        return "Player ID: " +
                playerId +
                ", First Name: " +
                firstName +
                ", Last Name: " +
                lastName +
                ",  Address: " +
                address +
                ", Postal Code: " +
                postalCode +
                ", Province: " +
                province +
                ", Phone Number: " +
                phoneNumber;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.model.PlayerModel>> " + message);
    }
}
