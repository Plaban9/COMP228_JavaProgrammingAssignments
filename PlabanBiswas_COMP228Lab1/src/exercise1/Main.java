package exercise1;

import java.util.Scanner;

// Driver Class
public class Main
{
    public static void main(String[] args)
    {
        // Create the driver class that would create 1 Singer (singer1) object with the help of the no argument constructor
        Singer singer1 = new Singer();

        // Display the default values of the instance variables of this object singer1
        System.out.println(" ------- Singer when initialized with default constructor ------- ");
        singer1.printAllValues();

        // Set the values of each instance variables with the help of setters
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ------- Enter details for singer ------- ");
        System.out.print("Enter ID: ");
        String input = scanner.nextLine();
        singer1.setId(input);

        System.out.print("Enter Name: ");
        input = scanner.nextLine();
        singer1.setName(input);

        System.out.print("Enter Address: ");
        input = scanner.nextLine();
        singer1.setAddress(input);

        System.out.print("Enter Date of Birth: ");
        input = scanner.nextLine();
        singer1.setBirthDate(input);

        System.out.print("Enter number of albums published: ");
        int integerInput = scanner.nextInt();
        singer1.setCountAlbumsPublished(integerInput);

        // Display the values
        System.out.println("\n ------- Singer when values set with setters ------- ");
        singer1.printAllValues();
    }
}