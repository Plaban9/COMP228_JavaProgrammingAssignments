package exercise2;

import java.util.Scanner;

// Driver Class
// Note: Input Validation is not done in this class
public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        String employmentStatus;

        GameTester gameTester1;
        GameTester gameTester2;
        GameTester gameTester3;

        // Game Tester - 1
        displayEnterDetails(1);
        name = getName(scanner);
        employmentStatus = getEmploymentStatus(scanner);
        gameTester1 = createTester(name, employmentStatus, scanner);

        // Game Tester - 2
        displayEnterDetails(2);
        name = getName(scanner);
        employmentStatus = getEmploymentStatus(scanner);
        gameTester2 = createTester(name, employmentStatus, scanner);

        // Game Tester - 3
        displayEnterDetails(3);
        name = getName(scanner);
        employmentStatus = getEmploymentStatus(scanner);
        gameTester3 = createTester(name, employmentStatus, scanner);


        displayPrintDetails(1);
        gameTester1.display();
        displayPrintDetails(2);
        gameTester2.display();
        displayPrintDetails(3);
        gameTester3.display();
    }

    private static GameTester createTester(String name, String employmentStatus, Scanner scanner)
    {
        GameTester gameTester;

        switch (employmentStatus)
        {
            case "Part-Time":
                gameTester = new PartTimeGameTester(name);
                ((PartTimeGameTester) gameTester).setNumHrs(getHours(scanner));
                break;

            case "Full-Time":
            default:
                gameTester = new FullTimeGameTester(name);
                break;
        }

        return gameTester;
    }

    private static void displayEnterDetails(int gameTesterIndex)
    {
        System.out.println("\n\n---------------------------------------------");
        System.out.println("         Enter Tester - " + gameTesterIndex + " Details         ");
        System.out.println("---------------------------------------------");
    }

    private static void displayPrintDetails(int gameTesterIndex)
    {
        System.out.println("\n\n---------------------------------------------");
        System.out.println("        Tester - " + gameTesterIndex + " Details         ");
        System.out.println("---------------------------------------------");
    }

    private static String getName(Scanner scanner)
    {
        System.out.print("Enter Name: ");
        return scanner.nextLine();
    }

    private static String getEmploymentStatus(Scanner scanner)
    {
        System.out.print("Enter Employment Status (Part-Time/Full-Time): ");
        return scanner.nextLine();
    }

    private static int getHours(Scanner scanner)
    {
        System.out.print("Enter hours: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
