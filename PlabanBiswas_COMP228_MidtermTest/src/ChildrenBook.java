public class ChildrenBook extends Book
{
    private final String genre;

    public ChildrenBook(String title, String ISBN, String publisher, double price, int year)
    {
        super(title, ISBN, publisher, year);
        setPrice(price);
        genre = "Children";
    }

    @Override
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String getGenre()
    {
        return genre;
    }
}
