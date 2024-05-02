package utils.alg32;

import utils.alg32.*;
public class NumberTheory
{
	/*
		For the purposes of rational arithmetic.
	*/
	
	//Euclid's Algorithm for finding the greatest common divisor of two integers
	public static int GCD(int a, int b)
	{
		int greater;
		int lesser;
		int remainder = 1;
		int result = 1;

		if(a > b)
		{
			greater = a;
			lesser = b;
		}
		else
		{
			greater = b;
			lesser = a;
		}

		if(lesser == 0)
			result = greater;
		else
			while(remainder != 0)
			{
				remainder = greater % lesser;

				if(remainder == 0)
					result = lesser;
				else
				{
					greater = lesser;
					lesser = remainder;
				}
			}

		return result;
	}

	public static int LCM(int a, int b) throws IllegalArgumentException, IntegerOverflowException
	{
		if(a <= 0 || b <= 0)
			throw new IllegalArgumentException("Only positive integer values are expected for the given algorithm.");
		
		int result;
		int gcd = GCD(a, b);

		a /= gcd;
		result = IntegerOverflowManager.multiply(a, b);
		return result;
	}

	/*
		For the purposes of modular arithmetic,
		with the goal of eliminating avoidable
		integer overflow.

		This approach was poorly implemented. Will improve upon later.
	*/

	public static int modIncrement(int n, int m)
	{
		return n + 1 <= m ? n + 1 : 1;
	}

	public static int modDecrement(int n, int m)
	{
		return n - 1 > 0 ? n - 1 : m;
	}

	public static int modAdd(int a, int b, int m)
	{
		int result = a % m;
		if(b >= 0)
			for(int i = 1; i <= b; i++)
				result = modIncrement(result, m);
		else
			for(int i = -1; i >= b; i--)
				result = modDecrement(result, m);
		return result;
	}

	public static int modMultiply(int a, int b, int m)
	{
		int result = 0;

		if(a > 0 && b > 0 || a < 0 && b < 0)
			for(int i = 1; i <= Math.abs(b); i++)
				result = modAdd(result, a, m);
		else if(a != 0 && b != 0)
			for(int i = 1; i <= Math.abs(b); i++)
				result = modAdd(result, -a, m);
		return result;
	}
}