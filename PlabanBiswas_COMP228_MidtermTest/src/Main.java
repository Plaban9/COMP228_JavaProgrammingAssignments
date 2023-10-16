import javax.swing.*;

//Driver Class
public class Main
{
    public static void main(String[] args)
    {
        processBookPurchases();
    }

    private static void processBookPurchases()
    {
        boolean areInputsValid;
        boolean userCancelled = false;

        JTextField titleField = new JTextField();
        JTextField ISBNField = new JTextField();
        JTextField publisherField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField yearField = new JTextField();

        String[] genreArray = {"Children", "Science" };
        var genreField = new JComboBox(genreArray);

        Object[] message =
                {
                        "Title: ", titleField,
                        "ISBN: ", ISBNField,
                        "Publisher: ", publisherField,
                        "Price: ", priceField,
                        "Year: ", yearField,
                        "Genre: ", genreField
                };

        do
        {
            Icon icon = new ImageIcon(System.getProperty("user.dir") + "/assets/logo/logo_24x24.png");
            int option = JOptionPane.showConfirmDialog(null, message, "Book Portal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

            if (option == JOptionPane.OK_OPTION)
            {
                String title = titleField.getText();
                String ISBN = ISBNField.getText();
                String publisher = publisherField.getText();
                String price = priceField.getText();
                String year = yearField.getText();
                String genre = genreField.getSelectedItem().toString();

                areInputsValid = validateForm(title, ISBN, publisher, price, year, genre);

                if (areInputsValid)
                {
                    Book book = getBook(title, ISBN, publisher, Double.parseDouble(price), Integer.parseInt(year), genre);
                    JOptionPane.showMessageDialog(null, book.toString(), "Book Portal", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Input!!", "Book Portal", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (option == JOptionPane.CANCEL_OPTION)
            {
                areInputsValid = false;
                userCancelled = true;
            }
            else
            {
                areInputsValid = false;
                userCancelled = true;
                JOptionPane.showMessageDialog(null, "Invalid Input!!", "Book Portal", JOptionPane.ERROR_MESSAGE);
            }

        } while(!areInputsValid && !userCancelled);
    }

    private static boolean validateForm(String title, String ISBN, String publisher, String price, String year, String genre)
    {
        if (nullAndEmptyCheck(title) && nullAndEmptyCheck(ISBN) && nullAndEmptyCheck(publisher) && nullAndEmptyCheck(price) && nullAndEmptyCheck(year) && nullAndEmptyCheck(genre))
        {
            try
            {
                return Double.parseDouble(price) >= 0 && Integer.parseInt(year) >= 0;
            }
            catch (Exception ignored)
            {

            }
        }

        return false;
    }

    private static Book getBook(String title, String ISBN, String publisher, double price, int year, String genre)
    {
        switch (genre)
        {
            case "Science":
                return new ScienceBook(title, ISBN, publisher, price, year);

            case "Children":
            default:
                return new ChildrenBook(title, ISBN, publisher, price, year);
        }
    }

    private static boolean nullAndEmptyCheck(String stringToCheck)
    {
        return (stringToCheck != null) && !stringToCheck.isEmpty();
    }

//    private static Object[] getObjects()
//    {
//        JTextField title = new JTextField();
//        JTextField ISBN = new JTextField();
//        JTextField publisher = new JTextField();
//        JTextField price = new JTextField();
//        JTextField year = new JTextField();
//        JTextField genre = new JTextField();
//
//        Object[] message = {
//                "Title: ", title,
//                "ISBN: ", ISBN,
//                "Publisher: ", publisher,
//                "Price: ", price,
//                "Year: ", year,
//                "Genre: ", genre
//        };
//
//        return message;
//    }
}