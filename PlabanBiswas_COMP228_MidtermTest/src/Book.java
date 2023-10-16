public abstract class Book
{
    private String title;
    private String ISBN;
    private String publisher;
    protected double price;
    private int year;

    //region Constructors

    public Book(String title, String ISBN, String publisher, int year)
    {
        this.title = title;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.price = price;
        this.year = year;
    }

    //endregion

    //region Abstract Methods
    public abstract void setPrice(double price);

    public abstract String getGenre();
    //endregion

    //region GETTERS and SETTERS
    //region GETTERS
    public String getTitle()
    {
        return title;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public double getPrice()
    {
        return price;
    }

    public int getYear()
    {
        return year;
    }
    //endregion

    //region SETTERS
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
    //endregion
    //endregion

    @Override
    public String toString()
    {
        String returnString = "Title: " + title + ", ISBN:  " + ISBN + ", Publisher: " + publisher + ", Price: " + price + ", Year: " + year;
        return "Book Details -  {" + returnString + "}";
    }
}
