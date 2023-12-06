public class Student
{
    private final String studentID;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String province;
    private final String postalCode;


    public Student(String studentID, String firstName, String lastName, String address, String city, String province, String postalCode)
    {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    //region GETTERS
    public String getStudentID()
    {
        return studentID;
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

    public String getCity()
    {
        return city;
    }

    public String getProvince()
    {
        return province;
    }

    public String getPostalCode()
    {
        return postalCode;
    }
    //endregion

    //region UTILITY

    @Override
    public String toString()
    {
        return "Student{" +
                "studentID:'" + studentID + '\'' + ", " +
                "firstName:'" + firstName + '\'' + ", " +
                "lastName:'" + lastName + '\'' + ", " +
                "address:'" + address + '\'' + ", " +
                "city:'" + city + '\'' + ", " +
                "province:'" + province + '\'' + ", " +
                "postalCode:'" + postalCode + '\'' +
                '}';
    }

    //endregion
}
