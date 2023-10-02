package exercise3;

import javax.swing.*;

//Exercise - 3
// Driver Class
public class Main
{
    public static void main(String[] args)
    {
        StaticMethodExercise.display("Overload - 1"); // Only modifies message
        StaticMethodExercise.display("Overload - 2", "Title - 1"); // Modifies both message and title
        StaticMethodExercise.display("Overload - 3", "Title - 2", JOptionPane.QUESTION_MESSAGE); // Modifies message, title and Message Types
    }
}
