package exercise3;

import javax.swing.*;

public class StaticMethodExercise
{
    public static void display(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void display(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void display(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
