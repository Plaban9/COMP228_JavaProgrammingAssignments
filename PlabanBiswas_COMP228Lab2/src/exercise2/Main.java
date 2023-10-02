package exercise2;

import javax.swing.*;

// Exercise - 2
public class Main
{
    public static void main(String[] args)
    {
        int currentChance = 0;
        int numChances = 5;
        int userInputInNumbers;

        do
        {
            String userInput = JOptionPane.showInputDialog(null, "Enter a number between 3 and 27: ", "Lotto Chance: " + (currentChance + 1) + "/" + numChances, JOptionPane.INFORMATION_MESSAGE);

            if (validateInput(userInput))
            {
                userInputInNumbers = Integer.parseInt(userInput);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "INVALID INPUT!!\nTry again!!", "Lotto Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            Lotto lotto = new Lotto();

            if (lotto.getLottoSum() == userInputInNumbers)
            {
                JOptionPane.showMessageDialog(null, "Congratulations!!\nYou Won!!", "Lotto Result", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                switch (currentChance)
                {

                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        JOptionPane.showMessageDialog(null, "Your choice (" + userInputInNumbers + ") didn't match the Lotto (" + lotto.getLottoSum() + ") \n\n Chances Remaining: " + (numChances - (currentChance + 1)), "Lotto Information", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "You Have exhausted your chances\n\nComputer Wins!!", "Lotto Result", JOptionPane.INFORMATION_MESSAGE);
                        break;

                }
            }

            currentChance++;

        } while (currentChance < numChances);
    }

    // Validation of User Input
    private static boolean validateInput(String input)
    {
        if (input != null && !input.isEmpty())
        {
            try
            {
                int integerInput = Integer.parseInt(input);
                return (integerInput >= 3 && integerInput <= 27);
            }
            catch (Exception ignored)
            {

            }
        }

        return false;
    }
}
