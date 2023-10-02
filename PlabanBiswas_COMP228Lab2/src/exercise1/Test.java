package exercise1;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Test
{
    private final int numQuestions;
    private int numCorrectAnswers;

    private String userName;

    public Test()
    {
        this.numQuestions = 5;
    }

    public Test(int numQuestions)
    {
        this.numQuestions = numQuestions;
    }

    public void inputAnswer()
    {
        userName = JOptionPane.showInputDialog(null, QuestionBank.INTRODUCTORY_INSTRUCTIONS_STATEMENT + "\n\nNumber of Questions: " + numQuestions + "\n\nEnter Name: ", "JAVA Test", JOptionPane.INFORMATION_MESSAGE);

        simulateQuestion();
    }

    public void simulateQuestion()
    {
        ArrayList<Question> questions = QuestionBank.getQuestions(true);

        // Re-Initialize correct answers count to 0.
        numCorrectAnswers = 0;
        int i = 0;

        do
        {
            String userAnswer = JOptionPane.showInputDialog(null, questions.get(i).getQuestion(), "Question " + (i + 1), JOptionPane.QUESTION_MESSAGE);

            if (validateUserAnswer(userAnswer))
            {
                boolean isCorrectAnswer = checkAnswer(userAnswer, questions.get(i).getAnswerKey());

                String generatedMessage = generateMessage(isCorrectAnswer);
                JOptionPane.showMessageDialog(null, generatedMessage, "Alert for Question " + (i + 1), isCorrectAnswer ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

                i++;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Input!!", "Alert for Question " + (i + 1), JOptionPane.ERROR_MESSAGE);
            }
        }
        while (i < numQuestions);

        displayResultStatistics();
    }

    private boolean checkAnswer(String userAnswer, String answerKey)
    {
        return userAnswer != null && userAnswer.equals(answerKey);
    }

    private String generateMessage(boolean isCorrectAnswer)
    {
        String message;
        SecureRandom random = new SecureRandom();
        int randomMessageIndex = random.nextInt(0, 4);

        if (isCorrectAnswer)
        {
            message = getCorrectMessage(randomMessageIndex);
        }
        else
        {
            message = getIncorrectMessage(randomMessageIndex);
        }

        return message;
    }

    // ("Excellent!", ”Good!”, ”Keep up the good work!”, or “Nice work!”).
    private String getCorrectMessage(int index)
    {
        numCorrectAnswers++;

        switch (index)
        {
            case 0:
                return "Excellent!";

            case 1:
                return "Good!";

            case 2:
                return "Keep up the good work!";

            case 3:
            default:
                return "Nice work!!";
        }
    }

    //  (“No. Please try again”, “Wrong. Try once more”, “Don't give up!”, “No. Keep trying”).
    private String getIncorrectMessage(int index)
    {
        switch (index)
        {
            case 0:
                return "No. Please try again";

            case 1:
                return "Wrong. Try once more!";

            case 2:
                return "Don't give up";

            case 3:
            default:
                return "No. Keep trying.";
        }
    }

    private boolean validateUserAnswer(String userAnswer)
    {
        if (userAnswer != null)
        {
            switch (userAnswer)
            {
                case "a":
                case "b":
                case "c":
                case "d":
                case "A":
                case "B":
                case "C":
                case "D":
                case "":
                    return true;
            }
        }

        return false;
    }

    private void displayResultStatistics()
    {
        JOptionPane.showMessageDialog(null, getResultText(), "Results of the test", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getResultText()
    {
        return """
                Student Name: %s
                
                ===== Result =====
                Correct Answers: %d
                Incorrect Answers: %d
                Percentage Correct Answers: %.2f
                """.formatted(userName, numCorrectAnswers, numQuestions - numCorrectAnswers, (float) numCorrectAnswers / numQuestions * 100f);
    }
}
