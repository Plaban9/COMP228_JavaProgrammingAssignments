package exercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

// Driver Class
public class Main
{
    private static String insuranceType = null;
    private static double insuranceAmount = -1;
    private static ArrayList<Insurance> insurances = new ArrayList<>();

    public static void main(String[] args)
    {
        displayInsurancePanel();
    }

    private static void displayInsurancePanel()
    {
        JFrame insuranceFrame = new JFrame("Insurance Portal");
        insuranceFrame.setPreferredSize(new Dimension(600, 400));

        insuranceFrame.setLayout(new GridLayout(4, 1)); // For button not covering whole area
        JLabel insuranceIntroduction = new JLabel("******* Welcome to the Insurance Portal!! *******", JLabel.CENTER);
        insuranceFrame.add(insuranceIntroduction);


        // Insurance Type Query
        // Radio Button for Insurance
        JPanel insuranceTypePanel = new JPanel();
        insuranceTypePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        insuranceTypePanel.setLayout(new GridLayout(3, 1, 5, 5));

        JLabel insuranceTypeLabel = new JLabel("Pick an insurance type: ", JLabel.LEFT);
        insuranceTypePanel.add(insuranceTypeLabel);

        JRadioButton lifeInsuranceRadioButton = new JRadioButton("Life Insurance", true);
        JRadioButton healthInsuranceRadioButton = new JRadioButton("Health Insurance", false);

        ButtonGroup insuranceTypeButtonGroup = new ButtonGroup();
        insuranceTypeButtonGroup.add(lifeInsuranceRadioButton);
        insuranceTypeButtonGroup.add(healthInsuranceRadioButton);

        insuranceTypePanel.add(lifeInsuranceRadioButton);
        insuranceTypePanel.add(healthInsuranceRadioButton);

        insuranceFrame.getContentPane().add(insuranceTypePanel);


        // Insurance Amount Query
        JPanel insuranceAmountPanel = new JPanel();
        insuranceAmountPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        insuranceAmountPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel insuranceAmountLabel = new JLabel("Enter Insurance Cost: ", JLabel.LEFT);
        insuranceAmountPanel.add(insuranceAmountLabel);

        JTextField insuranceAmountField = new JTextField();
        insuranceAmountField.setColumns(20);
        insuranceAmountPanel.add(insuranceAmountField);

        insuranceFrame.getContentPane().add(insuranceAmountPanel);

        // Submit Button
        JPanel submitPanel = new JPanel();
        submitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("SUBMIT");

        submitPanel.add(submitButton);

        insuranceFrame.add(submitPanel);

        insuranceFrame.pack();
        insuranceFrame.setVisible(true);

        submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String radioButtonSelection = null;

                for (Enumeration<AbstractButton> buttons = insuranceTypeButtonGroup.getElements(); buttons.hasMoreElements(); )
                {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected())
                    {
                        radioButtonSelection = button.getText();
                    }
                }

                String amount = insuranceAmountField.getText();

                if (validateInput(radioButtonSelection, amount))
                {
                    insuranceFrame.setVisible(false);

                    insurances.add(createInsuranceObject(insuranceType, insuranceAmount));
                    askUserForRetry();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Enter proper details!!", "Insurance Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private static void askUserForRetry()
    {
        String enterAnotherEntry = JOptionPane.showInputDialog(null, "Do you want to enter another insurance entry? (Yes/No)", "Insurance Entry", JOptionPane.QUESTION_MESSAGE);

        if (validateRetry(enterAnotherEntry))
        {
            if (shouldRetry(enterAnotherEntry))
            {
                displayInsurancePanel();
            }
            else
            {
                displayInsurances();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Enter proper input!!", "Insurance Error", JOptionPane.ERROR_MESSAGE);
            askUserForRetry();
        }
    }

    private static void displayInsurances()
    {
        for (Insurance insurance : insurances)
        {
            insurance.display();
        }
    }

    private static boolean validateRetry(String entryString)
    {
        if (entryString != null && !entryString.isEmpty())
        {
            switch (entryString)
            {
                case "Y":
                case "y":
                case "YES":
                case "Yes":
                case "yes":
                case "N":
                case "n":
                case "NO":
                case "No":
                case "no":
                    return true;
            }
        }

        return false;
    }

    private static boolean shouldRetry(String entryString)
    {
        if (entryString != null && !entryString.isEmpty())
        {
            switch (entryString)
            {
                case "Y":
                case "y":
                case "YES":
                case "Yes":
                case "yes":
                    return true;
            }
        }

        return false;
    }

    private static Insurance createInsuranceObject(String insuranceType, double insuranceAmount)
    {
        Insurance insurance;

        switch (insuranceType)
        {

            case "Health Insurance":
                insurance = new Health();
                break;

            case "Life Insurance":
            default:
                insurance = new Life();
                break;
        }

        insurance.setInsuranceCost(insuranceAmount);

        return insurance;
    }

    private static boolean validateInput(String type, String amount)
    {
        insuranceType = type;

        try
        {
            if (amount != null && !amount.isEmpty())
            {
                insuranceAmount = Double.parseDouble(amount);
                return true;
            }
        }
        catch (Exception ignored)
        {

        }

        insuranceType = null;
        insuranceAmount = -1;

        return false;
    }
}