package exercise2;

import java.security.SecureRandom;

public class Lotto
{
    private final int[] lottoNumbers;

    public Lotto()
    {
        lottoNumbers = new int[3];
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < lottoNumbers.length; i++)
        {
            lottoNumbers[i] = secureRandom.nextInt(0, 10);
        }
    }

    public int[] getLottoNumbers()
    {
        return lottoNumbers;
    }

    public int getLottoSum()
    {
        int sum = 0;

        for (int lottoNumber : lottoNumbers)
        {
            sum += lottoNumber;
        }

        return sum;
    }
}
