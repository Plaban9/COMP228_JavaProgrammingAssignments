package exercise1;

// Class for question object - contains Text Questions with Options + the answer Key
public class Question
{
    private final String question;
    private final String answerKey;

    public Question(String question, String answerKey)
    {
        this.question = question;
        this.answerKey = answerKey;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswerKey()
    {
        return answerKey;
    }

    @Override
    public String toString()
    {
        return "Question: " + question + "\nAnswer Key: " + answerKey;
    }
}
