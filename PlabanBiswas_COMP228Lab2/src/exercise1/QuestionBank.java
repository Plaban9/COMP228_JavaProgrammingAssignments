package exercise1;

import java.util.ArrayList;
import java.util.Collections;

// Purpose of this class is to store questions and retrieve questions
public class QuestionBank
{
    public static final String INTRODUCTORY_INSTRUCTIONS_STATEMENT = "Hello,\nYou will be asked a set of questions. Marking scheme below. \n\nYou will be awarded 1 point(s) for each correct answer. \nYou will be penalized 0 point(s) for each incorrect answer. \nYou will receive 0 point(s) for each blank answer. \n\nYou will have to enter the character to which the correct answer is mapped to. \n\nYou will be given the percentage of correct answers at the end of the test. \n\nAll the best!!";

    // Text Block
    public static final String QUESTION_1 = """
            A __________ in Java is a collection of instance variables, which describe the properties of class objects, and methods, which describe the behaviors. Java Programming

            a. class
            b. instance
            c. object
            d. variable""";
    public static final String QUESTION_2 = """
            By convention, we use what type of case convention in JAVA?
            a. PascalCase
            b. camelCase
            c. snake_case
            d. MACRO_CASE""";

    public static final String QUESTION_3 = """
            The keyword _________ is used to create an instance of a class.
            a. break
            b. switch
            c. new
            d. return""";

    public static final String QUESTION_4 = """
            The __________ Package contains classes and interfaces that enable programs to input and output data.
            a. java.util
            b. java.net
            c. java.lang
            d. java.io""";

    public static final String QUESTION_5 = """
            What is the default value for an instance variable of the type String?
            a. null
            b. 0
            c. undefined
            d. "\"""";

    public static final String QUESTION_6 = """
            What is String?
            a. a class
            b. a sequence of characters
            c. a non-primitive data type
            d. All of the above""";

    public static ArrayList<Question> getQuestions(boolean isRandom)
    {
        ArrayList<Question> questionArrayList = new ArrayList<>();

        questionArrayList.add(new Question(QuestionBank.QUESTION_1, "a"));
        questionArrayList.add(new Question(QuestionBank.QUESTION_2, "b"));
        questionArrayList.add(new Question(QuestionBank.QUESTION_3, "c"));
        questionArrayList.add(new Question(QuestionBank.QUESTION_4, "d"));
        questionArrayList.add(new Question(QuestionBank.QUESTION_5, "a"));
        questionArrayList.add(new Question(QuestionBank.QUESTION_6, "d"));

        if (isRandom)
        {
            Collections.shuffle(questionArrayList);
        }

        return questionArrayList;
    }
}
