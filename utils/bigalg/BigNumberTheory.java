package utils.bigalg;

import java.math.BigInteger;
public class BigNumberTheory
{
	//Euclid's Algorithm for finding the least common divisor of two integers
	public static BigInteger GCD(BigInteger a, BigInteger b)
	{
		BigInteger greater;
		BigInteger lesser;
		BigInteger remainder = BigInteger.ONE;

		BigInteger result = BigInteger.ONE;

		if(a.compareTo(b) == 1) //a > b
		{
			greater = a.abs();
			lesser = b.abs();
		}
		else
		{
			greater = b;
			lesser = a;
		}

		if(lesser.compareTo(BigInteger.ZERO) == 0) //lesser == 0
			result = greater;
		else
			while(remainder.compareTo(BigInteger.ZERO) != 0) //remainder != 0
			{
				remainder = greater.remainder(lesser);

				if(remainder.compareTo(BigInteger.ZERO) == 0) //remainder == 0
					result = lesser;
				else
				{
					greater = lesser;
					lesser = remainder;
				}
			}

		return result;
	}

	public static BigInteger LCM(BigInteger a, BigInteger b)
	{
		return (a.multiply(b).divide(GCD(a, b))).abs();
	}
}