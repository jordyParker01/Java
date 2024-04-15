package apps.bases;

import java.util.Scanner;
import apps.bases.IntegerOverflowException;
public class Fraction
{
	private int numerator;
	private int denominator;

	public static final Fraction INFINITESSIMAL = new Fraction(1, Integer.MAX_VALUE);

	//CONSTRUCTORS

	public Fraction()
	{
		numerator = 0;
		denominator = 1;
	}
	
	public Fraction(int n)
	{
		numerator = n;
		denominator = 1;
	}

	public Fraction(int n, int d) throws NumberFormatException
	{
		if(d != 0)
		{
			numerator = n;
			denominator = d;
			reduce();
		}
		else
			throw new NumberFormatException("Zero divisor in denominator constructor.");
	}


	//ACCESS METHODS

	public int getNumerator()
	{
		return this.numerator;
	}

	public int getDenominator()
	{
		return this.denominator;
	}

	public void setNumerator(int n)
	{
		this.numerator = n;
	}

	public void setDenominator(int d) throws NumberFormatException
	{
		if(d > 0)
			this.denominator = d;
		else if(d < 0)
		{
			this.denominator = d;
			this.reduce();
		}
		else
			throw new NumberFormatException("Zero divisor in denominator assignment.");
	}


	//CLASS METHODS

	public static Fraction promptFraction(String prompt)
	{
		Scanner scanner = new Scanner(System.in);
		String input;
		Fraction result = new Fraction();

		System.out.print(prompt + " >> ");

		while(true)
		{
			input = scanner.nextLine().trim();
			
			try
			{
				result = parseFraction(input);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Invalid input >> ");
			}
		}

		return result;
	}

	public static Fraction parseFraction(String input) throws NumberFormatException
	{
		int n;
		int d;
		String[] values;
		Fraction result;

		values = input.split("/");
		if(values.length > 2)
			throw new NumberFormatException("Incorrect number of elements found while parsing fraction");
		else if(values.length == 1)
		{
			n = Integer.parseInt(values[0].trim());
			result = new Fraction(n);
		}
		else
		{
			n = Integer.parseInt(values[0].trim());
			d = Integer.parseInt(values[1].trim());
			result = new Fraction(n, d);
		}

		return result;
	}

	public static Fraction parseFraction(int input)
	{
		return new Fraction(input);
	}

	public static Fraction add(Fraction a, Fraction b) throws IntegerOverflowException
	{
		Fraction result;

		int lcm = findLCM(a.denominator, b.denominator);

		int multA = (lcm / a.denominator);
		int multB = (lcm / b.denominator);

		if
		(
			(a.numerator > 0 && a.numerator > Integer.MAX_VALUE / multA) ||
			(a.numerator < 0 && a.numerator < Integer.MIN_VALUE / multA) ||
			(b.numerator > 0 && b.numerator > Integer.MAX_VALUE / multB) ||
			(b.numerator < 0 && b.numerator < Integer.MIN_VALUE / multB)
		)
			throw new IntegerOverflowException("Numerator too large to be stored in 32-bit signed integer.");

		int numA = a.numerator * multA;
		int numB = b.numerator * multB;

		result = new Fraction(numA + numB, lcm);
		/*
		catch(IntegerOverflowException e)
		{
			System.out.println(e);
			result = a;
		}
		*/

		return result;
	}

	public static Fraction addMany(Fraction... fractions) throws IntegerOverflowException
	{
		Fraction result = new Fraction();

		for(Fraction fraction : fractions)
		{
			result = add(result, fraction);
		}

		return result;
	}

	/*
	PREVENTABLE INTEGER OVERFLOW: by taking responsiblity for reducing the denominator first before
	passing values to the constructor, integer overflow could possibly be prevented...I think.

	vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	*/
	public static Fraction multiply(Fraction a, Fraction b)
	{
		int n;
		int d;
		Fraction result;

		n = a.numerator * b.numerator;
		d = a.denominator * b.denominator;

		result = new Fraction(n, d);
		return result;
	}

	public static Fraction multiplyMany(Fraction... fractions)
	{
		Fraction result = new Fraction(1);

		for(Fraction fraction : fractions)
		{
			result = multiply(result, fraction);
		}

		return result;
	}


	//INSTANCE METHODS

	public float toFloat()
	{
		return (float)this.numerator / (float)this.denominator;
	}

	public double toDouble()
	{
		return (double)this.numerator / (double)this.denominator;
	}

	public int toInt()
	{
		return this.numerator / this.denominator;
	}

	public Fraction reciprocal()
	{
		int n = this.denominator;
		int d = this.numerator;

		Fraction result = new Fraction(n, d);
		return result;
	}

	public Fraction negative()
	{
		int n = this.numerator * -1;
		int d = this.denominator;

		Fraction result = new Fraction(n, d);
		return result;
	}

	@Override
	public String toString()
	{
		String result;
		if(this.denominator != 1)
			result = this.numerator + "/" + this.denominator;
		else
			result = String.valueOf(this.numerator);
		return result;
	}

	public String mixedNumber()
	{
		String result;
		Fraction remainder;
		int leadingValue;

		leadingValue = this.numerator / this.denominator;
		remainder = new Fraction(this.numerator % this.denominator, this.denominator);

		result = leadingValue + " " + remainder.toString();
		return result;
	}

	public void increaseBy(Fraction fraction)
	{
		int lcm = findLCM(this.denominator, fraction.denominator);

		int numA = lcm / this.denominator;
		int numB = lcm / fraction.denominator;

		this.numerator = numA + numB;
		this.denominator = lcm;
		this.reduce();
	}

	public void increaseBy(int i)
	{
		this.numerator += i * this.denominator;
		this.reduce();
	}

	public void decreaseBy(Fraction fraction)
	{
		int lcm = findLCM(this.denominator, fraction.denominator);

		int numA = lcm / this.denominator;
		int numB = lcm / fraction.denominator;

		this.numerator = numA - numB;
		this.denominator = lcm;
		this.reduce();
	}

	public void decreaseBy(int i)
	{
		this.numerator -= i * this.denominator;
	}

	public void multiplyBy(Fraction fraction) throws NumberFormatException
	{
		this.numerator *= fraction.getNumerator();
		this.setDenominator(this.denominator * fraction.getDenominator());
		this.reduce();
	}

	public void multiplyBy(int i)
	{
		this.numerator *= i;
		this.reduce();
	}
	
	public void divideBy(Fraction fraction) throws NumberFormatException
	{
		this.numerator *= fraction.getDenominator();
		this.setDenominator(this.denominator * fraction.getNumerator());
		this.reduce();
	}

	public void divideBy(int i) throws NumberFormatException
	{
		this.setDenominator(this.denominator * i);
		this.reduce();
	}

	public Fraction compareTo(Fraction fraction)
	{
		return add(fraction, this.negative());
	}

	public Fraction compareTo(int i)
	{
		return add(new Fraction(i), this.negative());
	}

	//PRIVATE METHODS

	private void reduce()
	{
		int gcd = findGCD(this.numerator, this.denominator);
		this.numerator /= gcd;
		this.denominator /= gcd;

		if(this.denominator == Integer.MIN_VALUE)
			throw new IntegerOverflowException("Denominator too large to be stored in 32-bit signed integer.");
		else if(this.denominator < 0)
		{
			this.numerator *= -1;
			this.denominator *= -1;
		}
	}

	//Euclid's Algorithm for finding the greatest common divisor of two integers
	private static int findGCD(int a, int b)
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

	private static int findLCM(int a, int b) throws IntegerOverflowException
	{
		if(a <= 0 || b <= 0)
			throw new IllegalArgumentException("Only positive integer values are expected for the given algorithm.");
		
		int result;
		int gcd = findGCD(a, b);

		a /= gcd;

		if(a > Integer.MAX_VALUE / b)
			throw new IntegerOverflowException("Denominator too large to be stored in 32-bit signed integer.");
		else
			result = (a * b);
		return result;
	}
}