package utils.bigalg;

import java.math.BigInteger;
import java.util.Scanner;
import utils.bigalg.*;
public class BigFraction implements Comparable<BigFraction>
{
	//INSTANCE FIELDS
	private BigInteger numerator;
	private BigInteger denominator;

	//CLASS CONSTANTS
	static BigFraction ZERO = new BigFraction();
	static BigFraction ONE = new BigFraction(1);
	static BigFraction	NEGATIVE_ONE = new BigFraction(-1);

	/*
		CONSTRUCTORS
	*/

	public BigFraction(int n, int d)
	{
		setNumerator(n);
		setDenominator(d);
		reduce();
	}

	public BigFraction(BigInteger n, BigInteger d)
	{
		setNumerator(n);
		setDenominator(d);
		reduce();
	}

	public BigFraction(BigInteger n, int d)
	{
		setNumerator(n);
		setDenominator(d);
		reduce();
	}

	public BigFraction(int n, BigInteger d)
	{`
		setNumerator(n);
		setDenominator(d);
		reduce();
	}

	public BigFraction(int n)
	{
		this(n, 1);
	}

	public BigFraction(BigInteger n)
	{
		this(n, 1);
	}

	public BigFraction()
	{
		this(0, 1);
	}

	/*
		ACCESSOR METHODS
	*/

	public BigInteger getNumerator()
	{
		return numerator;
	}

	public BigInteger getDenominator()
	{
		return denominator;
	}

	/*
		MUTATOR METHODS
	*/

	private void setNumerator(int numerator)
	{
		this.numerator = BigInteger.valueOf(numerator);
	}

	private void setNumerator(BigInteger numerator)
	{
		this.numerator = numerator;
	}

	private void setDenominator(int denominator) throws NumberFormatException
	{
		if(denominator != 0)
			this.denominator = BigInteger.valueOf(denominator);
		else
			throw new NumberFormatException("Zero Divisor");
	}

	private void setDenominator(BigInteger denominator)
	{
		if(denominator.compareTo(BigInteger.ZERO) != 0) //denominator != 0
			this.denominator = denominator;
		else
			throw new NumberFormatException("Zero Divisor");
	}

	/*
		CLASS METHODS
	*/

	public static BigFraction promptFraction(String prompt)
	{
		Scanner scanner = new Scanner(System.in);
		String input;
		BigFraction result;

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

	public static BigFraction parseFraction(String input) throws NumberFormatException
	{
		BigInteger n = null;
		BigInteger d = null;

		String[] values = input.split("/");
		BigFraction result;

		if(values.length > 2)
			throw new NumberFormatException("Invalid number of elements found while parsing fraction");
		else if(values.length == 1)
		{
			n = new BigInteger(values[0].trim());
			result = new BigFraction(n);
		}
		else
		{
			n = new BigInteger(values[0].trim());
			d = new BigInteger(values[1].trim());
			result = new BigFraction(n, d);
		}

		return result;
	}

	public static BigFraction add(BigFraction a, BigFraction b)
	{
		BigInteger lcm = BigNumberTheory.LCM(a.denominator, b.denominator);
		BigFraction result;

		BigInteger numA = a.numerator.multiply(lcm).divide(a.denominator);
		BigInteger numB = b.numerator.multiply(lcm).divide(b.denominator);

		return new BigFraction(numA.add(numB), lcm);
	}

	public static BigFraction addMany(BigFraction... fractions)
	{
		BigFraction result = ZERO;

		for(BigFraction fraction : fractions)
		{
			result = add(result, fraction);
		}

		return result;
	}

	public static BigFraction multiply(BigFraction a, BigFraction b)
	{
		BigInteger n = a.numerator.multiply(b.numerator);
		BigInteger d = a.denominator.multiply(b.denominator);
		return new BigFraction(n, d);
	}

	public static BigFraction multiplyMany(BigFraction... fractions)
	{
		BigFraction result = ONE;

		for(BigFraction fraction : fractions)
		{
			result = multiply(result, fraction);
		}

		return result;
	}

	public static BigFraction max(BigFraction a, BigFraction b)
	{
		return a.compareTo(b) >= 0 ? a : b;
	}

	public static BigFraction min(BigFraction a, BigFraction b)
	{
		return a.compareTo(b) <= 0 ? a : b;
	}

	/*
		INSTANCE METHODS
		LOSSY TYPE CONVERSIONS
	*/

	public long toLong() throws ArithmeticException
	{
		return numerator.longValueExact() / denominator.longValueExact();
	}

	public int toInt() throws ArithmeticException
	{
		return numerator.intValueExact() / denominator.intValueExact();
	}

	public short toShort() throws ArithmeticException
	{
		return (short)(numerator.shortValueExact() / denominator.shortValueExact());
	}

	public byte toByte() throws ArithmeticException
	{
		return (byte)(numerator.byteValueExact() / denominator.byteValueExact());
	}

	public float toFloat()
	{
		return numerator.floatValue() / denominator.floatValue();
	}

	public double toDouble()
	{
		return numerator.doubleValue() / denominator.doubleValue();
	}

	/*
		INSTANCE METHODS
		LOSSLESS TYPE CONVERSIONS
	*/

	public long toLongExact() throws ArithmeticException
	{
		if(!isInt())
			throw new ArithmeticException("Lossless conversion not possible");
		else
			return numerator.longValueExact() / denominator.longValueExact();
	}

	public int toIntExact() throws ArithmeticException
	{
		if(!isInt())
			throw new ArithmeticException("Lossless conversion not possible");
		else
			return numerator.intValueExact() / denominator.intValueExact();
	}

	public short toShortExact() throws ArithmeticException
	{
		if(!isInt())
			throw new ArithmeticException("Lossless conversion not possible");
		else
			return (short)(numerator.shortValueExact() / denominator.shortValueExact());
	}

	public byte toByteExact() throws ArithmeticException
	{
		if(!isInt())
			throw new ArithmeticException("Lossless conversion not possible");
		else
			return (byte)(numerator.byteValueExact() / denominator.byteValueExact());
	}

	/*
		INSTANCE METHODS
		NUMBER THEORY
	*/

	public BigFraction abs()
	{
		return new BigFraction(numerator.abs(), denominator);
	}

	public BigFraction negative()
	{
		return new BigFraction(numerator.negate(), denominator);
	}

	public BigFraction inverse()
	{
		return new BigFraction(denominator, numerator);
	}

	public int signum()
	{
		return numerator.signum();
	}

	public boolean isInt()
	{
		return numerator.remainder(denominator) != BigInteger.ZERO;
	}

	public boolean isImproper()
	{
		return numerator.compareTo(denominator) == 1 && !isInt();
	}

	/*
		INSTANCE METHODS
		STANDARD OVERRIDES
	*/

	@Override
	public int compareTo(BigFraction other)
	{
		BigInteger lcm = BigNumberTheory.LCM(this.denominator, other.denominator);

		BigInteger numThis = this.numerator.multiply(lcm).divide(this.denominator);
		BigInteger numOther = other.numerator.multiply(lcm).divide(other.denominator);

		return numThis.compareTo(numOther);
	}

	@Override
	public boolean equals(Object x)
	{
		boolean result = false;
		if
		(
			x.getClass().getName() == "BigFraction" &&
			this.numerator.equals(((BigFraction)x).numerator) &&
			this.denominator.equals(((BigFraction)x).denominator)
		)
			result = true;
		return result;
	}

	@Override
	public String toString()
	{
		String result;
		if(!denominator.equals(BigInteger.ONE))
			result = numerator + "/" + denominator;
		else
			result = String.valueOf(this.numerator);
		return result;
	}

	/*
		PRIVATE METHODS
	*/

	private void reduce()
	{
		BigInteger gcd = BigNumberTheory.GCD(numerator, denominator);
		numerator = numerator.divide(gcd);
		denominator = denominator.divide(gcd);

		if(denominator.signum() == -1)
		{
			numerator = numerator.negate();
			denominator = denominator.negate();
		}
	}
}