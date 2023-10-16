public class ChildrenBook extends Book
{
    public ChildrenBook(String title, String ISBN, String publisher, double price, int year)
    {
        super(title, ISBN, publisher, year);
        setPrice(price);
        super.setGenre("Children");
    }

    @Override
    public void setPrice(double price)
    {
        this.price = price; // Fixed, same as Customer supplied
    }

    @Override
    public String getGenre()
    {
        return genre;
    }
}
