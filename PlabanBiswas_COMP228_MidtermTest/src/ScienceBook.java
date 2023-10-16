public class ScienceBook extends Book
{
    private final String genre;

    public ScienceBook(String title, String ISBN, String publisher, double price, int year)
    {
        super(title, ISBN, publisher, year);
        setPrice(price);
        genre = "Science";
    }

    @Override
    public void setPrice(double price)
    {
        this.price = 0.9 * price; // 10% discount
    }

    @Override
    public String getGenre()
    {
        return genre;
    }
}
