package utils.alg32;

import java.util.Scanner;
import utils.alg32.*;
public class Fraction
{
	private int numerator;
	private int denominator;

	public static final Fraction INFINITESSIMAL = new Fraction(1, Integer.MAX_VALUE);

	/*
		CONSTRUCTORS
	*/

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

	public Fraction(int n, int d) throws NumberFormatException, IntegerOverflowException
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


	/*
		ACCESSOR METHODS
	*/

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


	/*
		CLASS METHODS
	*/

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
		int numA = IntegerOverflowManager.multiply(a.numerator, multA);
		int numB = IntegerOverflowManager.multiply(b.numerator, multB);

		result = new Fraction(IntegerOverflowManager.add(numA, numB), lcm);

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
	public static Fraction multiply(Fraction a, Fraction b) throws IntegerOverflowException
	{
		int n;
		int d;
		Fraction result;

		n = IntegerOverflowManager.multiply(a.numerator, b.numerator);
		d = IntegerOverflowManager.multiply(a.denominator, b.denominator);

		result = new Fraction(n, d);
		return result;
	}

	public static Fraction multiplyMany(Fraction... fractions) throws IntegerOverflowException
	{
		Fraction result = new Fraction(1);

		for(Fraction fraction : fractions)
		{
			result = multiply(result, fraction);
		}

		return result;
	}


	/*
		INSTANCE METHODS
	*/

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

	public Fraction reciprocal() throws IntegerOverflowException
	{
		int n = this.denominator;
		int d = this.numerator;

		Fraction result = new Fraction(n, d);
		return result;
	}

	public Fraction negative() throws IntegerOverflowException
	{
		int n = IntegerOverflowManager.negate(this.numerator);
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

	public void increaseBy(Fraction fraction) throws IntegerOverflowException
	{
		int lcm = findLCM(this.denominator, fraction.denominator);

		int numA = lcm / this.denominator;
		int numB = lcm / fraction.denominator;

		this.numerator = IntegerOverflowManager.add(numA, numB);
		this.denominator = lcm;
		this.reduce();
	}

	public void increaseBy(int i) throws IntegerOverflowException
	{
		this.numerator = IntegerOverflowManager.add(this.numerator, IntegerOverflowManager.multiply(i, this.denominator));
		this.reduce();
	}

	public void decreaseBy(Fraction fraction) throws IntegerOverflowException
	{
		int lcm = findLCM(this.denominator, fraction.denominator);

		int numA = lcm / this.denominator;
		int numB = lcm / fraction.denominator;

		this.numerator = IntegerOverflowManager.add(numA, -numB);
		this.denominator = lcm;
		this.reduce();
	}

	public void decreaseBy(int i) throws IntegerOverflowException
	{
		this.numerator = IntegerOverflowManager.add(this.numerator, IntegerOverflowManager.multiply(-i, this.denominator));
	}

	public void multiplyBy(Fraction fraction) throws NumberFormatException, IntegerOverflowException
	{
		this.numerator = IntegerOverflowManager.multiply(this.numerator, fraction.getNumerator());
		this.setDenominator(IntegerOverflowManager.multiply(this.denominator, fraction.getDenominator()));
		this.reduce();
	}

	public void multiplyBy(int i) throws IntegerOverflowException
	{
		this.numerator = IntegerOverflowManager.multiply(this.numerator, i);
		this.reduce();
	}
	
	public void divideBy(Fraction fraction) throws NumberFormatException, IntegerOverflowException
	{
		this.numerator = IntegerOverflowManager.multiply(this.numerator, fraction.getDenominator());
		this.setDenominator(IntegerOverflowManager.multiply(this.denominator, fraction.getNumerator()));
		this.reduce();
	}

	public void divideBy(int i) throws NumberFormatException, IntegerOverflowException
	{
		this.setDenominator(IntegerOverflowManager.multiply(this.denominator, i));
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

	/*
		PRIVATE METHODS
	*/

	private void reduce() throws IntegerOverflowException
	{
		int gcd = findGCD(this.numerator, this.denominator);
		this.numerator /= gcd;
		this.denominator /= gcd;

		if(this.denominator < 0)
		{
			this.numerator = IntegerOverflowManager.negate(this.numerator);
			this.denominator = IntegerOverflowManager.negate(this.denominator);
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

	private static int findLCM(int a, int b) throws IllegalArgumentException, IntegerOverflowException
	{
		if(a <= 0 || b <= 0)
			throw new IllegalArgumentException("Only positive integer values are expected for the given algorithm.");
		
		int result;
		int gcd = findGCD(a, b);

		a /= gcd;
		result = IntegerOverflowManager.multiply(a, b);
		return result;
	}
}