package exercise3;

import javax.swing.*;

// Driver Class
public class ProcessMortgage
{
    public static void main(String[] args)
    {
        Mortgage[] mortgages = new Mortgage[3];

        float interestRate = getInterestRate();

        for (int i = 0; i < mortgages.length; i++)
        {
            processMortgages(interestRate, mortgages, i);
        }

        for (Mortgage mortgage : mortgages)
        {
            mortgage.getMortgageInfo();
        }
    }


    public static void processMortgages(float interest, Mortgage[] mortgages, int index)
    {
        if (mortgages.length <= index)
        {
            return;
        }

        String[] mortgageType = {"Business", "Personal"};
        Object userMortgageType = JOptionPane.showInputDialog(null, "Select Mortgage Type: ", "Mortgage Portal", JOptionPane.PLAIN_MESSAGE, null, mortgageType, "Business");

        mortgages[index] = getMortgage(userMortgageType.toString(), interest);
    }

    public static Mortgage getMortgage(String mortgageType, float interestRate)
    {
        Mortgage mortgage;
        boolean areInputsValid;

        JTextField mortgageNumber = new JTextField();
        JTextField customerName = new JTextField();
        JTextField amount = new JTextField();
        JTextField term = new JTextField();

        Object[] message = {
                "Mortgage Number: ", mortgageNumber,
                "Customer Name: ", customerName,
                "Amount: ", amount,
                "Term: ", term
        };

        do
        {
            int option = JOptionPane.showConfirmDialog(null, message, "Mortgage Portal", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION)
            {
                areInputsValid = validateMortgageData(mortgageNumber.getText(), customerName.getText(), amount.getText(), term.getText());
            }
            else
            {
                areInputsValid = false;
                JOptionPane.showMessageDialog(null, "Invalid Input!!", "Mortgage Portal", JOptionPane.ERROR_MESSAGE);
            }

        }while (!areInputsValid);

        switch (mortgageType)
        {
            case "Business":
                mortgage = new BusinessMortgage(Integer.parseInt(mortgageNumber.getText()), customerName.getText(), Double.parseDouble(amount.getText()), interestRate, Integer.parseInt(term.getText()));
                break;

            case "Personal":
            default:
                mortgage = new PersonalMortgage(Integer.parseInt(mortgageNumber.getText()), customerName.getText(), Double.parseDouble(amount.getText()), interestRate, Integer.parseInt(term.getText()));
                break;
        }

        return mortgage;
    }

    private static boolean validateMortgageData(String mortgageNumber, String customerName, String amount, String term)
    {
        if (nullAndEmptyCheck(customerName))
        {
            try
            {
                return Integer.parseInt(mortgageNumber) > 0 && Double.parseDouble(amount) > 0 && Integer.parseInt(term) > 0;
            }
            catch (Exception ignored)
            {

            }
        }

        return false;
    }

    public static float getInterestRate()
    {
        float interestRate = 0f;
        boolean isInterestRateValid;

        do
        {
            String userInterestRate = JOptionPane.showInputDialog(null, "Enter Interest Rate: ", "Mortgage Portal", JOptionPane.PLAIN_MESSAGE);

            isInterestRateValid = validateFloatData(userInterestRate);

            if (isInterestRateValid)
            {
                interestRate = Float.parseFloat(userInterestRate);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Input!!", "Mortgage Portal", JOptionPane.ERROR_MESSAGE);
            }

        }while (!isInterestRateValid);

        return interestRate;
    }

    private static boolean validateFloatData(String userInterestRate)
    {
        if (nullAndEmptyCheck(userInterestRate))
        {
            try
            {
                return Float.parseFloat(userInterestRate) > 0f;
            }
            catch (Exception ignored)
            {

            }
        }

        return false;
    }

    private static boolean nullAndEmptyCheck(String stringToCheck)
    {
        return (stringToCheck != null) && !stringToCheck.isEmpty();
    }
}
