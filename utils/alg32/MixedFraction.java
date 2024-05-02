package utils.alg32;

import utils.alg32.*;
import java.util.Scanner;

public class MixedFraction
{
	private int wholePart;
	private Fraction fractionalPart;

	/*
		CONSTRUCTORS
	*/

	public MixedFraction()
	{
		fractionalPart = new Fraction();
		wholeNumber = 0;
	}

	public MixedFraction(Fraction f)
	{
		fractionalPart = new Fraction(f.getDenominator() % f.getNumerator(), f.getDenominator());
		wholeNumber = f.getNumerator() / f.getDenominator();
	}

	public MixedFraction(int w)
	{
		fractionalPart = new Fraction();
		wholeNumber = w;
	}

	public MixedFraction(int n, int d)
	{
		fractionalPart = new Fraction(d % n, d);
		wholeNumber = n / d;
	}

	public MixedFraction(int w, int n, int d) throws IntegerOverflowException
	{
		fractionalPart = new Fraction(d % n, d);
		wholeNumber = IntegerOverflowManager.add(w, n / d);
	}

	public MixedFraction(int w, Fraction f) throws IntegerOverflowException
	{
		super(f.getDenominator() % f.getNumerator(), f.getDenominator());
		wholeNumber = IntegerOverflowManager.add(w, f.getNumerator() / f.getDenominator());
	}

	/*
		ACCESSOR METHODS
	*/

	public void setWholePart(int w)
	{
		wholeNumber = w;
	}

	public void setFractionalPart(Fraction f)
	{
		fractionalPart = f;
	}

	public int getWholePart()
	{
		return wholeNumber;
	}

	public Fraction getFractionalPart()
	{
		return fractionalPart;
	}

	/*
		CLASS METHODS
	*/

	public static MixedFraction promptMixedFraction(String prompt)
	{
		Scanner scanner = new Scanner(System.in);
		MixedFraction result = new MixedFraction();
		String input;

		System.out.print(prompt + " >> ");

		while(true)
		{
			input = scanner.nextLine().trim();

			try
			{
				result = parseMixedFraction(prompt);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Invalid input >> ");
			}
		}
	}

	public static MixedFraction parseMixedFraction(String input) throws NumberFormatException
	{
		int wholePart;
		Fraction fractionalPart;
		MixedFraction result;

		String[] values;

		values = input.split(" ");
		if(input.length > 2)
			throw new NumberFormatException("Incorrect number of elements found while parsing mixed fraction");
		else if(input.length == 1)
		{
			wholePart = Integer.parseInt(values[0].trim());
			fractionalPart = new Fraction();
			result = new MixedFraction(wholePart, fractionalPart);
		}
		else
		{
			wholePart = Integer.parseInt(values[0].trim());
			fractionalPart = Fraction.parseFraction(values[1].trim());
			result = new MixedFraction(wholePart, fractionalPart);
		}

		return result;
	}

	public static MixedFraction parseMixedFraction(int input)
	{
		return new MixedFraction(input);
	}
}