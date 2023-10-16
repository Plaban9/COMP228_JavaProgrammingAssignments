public class ScienceBook extends Book
{
    public ScienceBook(String title, String ISBN, String publisher, double price, int year)
    {
        super(title, ISBN, publisher, year);
        setPrice(price);
        super.setGenre("Science");
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
