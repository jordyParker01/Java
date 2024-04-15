package apps.bases;

import java.util.*;
import utils.menu.*;
import utils.JordysPrompts;
import apps.bases.*;
public class BaseConverter
{
	static Menu applicationMenu = new Menu
	(
		new Utility[]
		{
			new Utility(BaseConverter::run_decimalToBase, "Convert a base-10 integer to a given base"),
			new Utility(BaseConverter::run_fractionToBase, "Convert a fraction to a place value numeral given base"),
			new Utility(BaseConverter::run_stringToFraction, "Convert a place value numeral in a given base to a fraction"),
			new Utility(BaseConverter::run_placeValueToBase, "Convert a base-10 place value numeral to a given base")
		}
	);

	public static void main(String[] args)
	{
		applicationMenu.run();
	}

	/**********************************************************************************************************************************
	                                                           APPLICATION METHODS                                                     
	                                                           vvvvvvvvvvvvvvvvvvv                                                     
	**********************************************************************************************************************************/

	private static void run_decimalToBase()
	{
		int base;
		int number;

		while(true)
		{
			Menu.clearConsole();
			System.out.println("\nI will convert a whole number from base-10 to a given base.");
			number = JordysPrompts.promptInt("\nPlease enter the number you'd like to convert");
			base = JordysPrompts.promptInt("Please enter the desired base");

			System.out.println(decimalToBase(number, base));
			if(!JordysPrompts.promptYesOrNo("Would you like to try again for another and number and base?"))
				break;
		}
	}

	private static void run_fractionToBase()
	{
		int base;
		Fraction fraction;

		while(true)
		{
			Menu.clearConsole();
			System.out.println("\nI will convert a fraction to its respective place value representation in a given base.");
			fraction = Fraction.promptFraction("\nPlease enter the fraction you'd like to convert");
			base = JordysPrompts.promptInt("Please enter the desired base");

			System.out.println("\n" + fractionToBase(fraction, base) + "\n");
			if(!JordysPrompts.promptYesOrNo("Would you like to try again for another base and fraction?"))
				break;
		}
	}

	private static void run_stringToFraction()
	{
		int base;
		String str;

		while(true)
		{
			Menu.clearConsole();
			System.out.println("\nI will convert the place value representation of a number in a given base to its reduced fractional representation in base-10.");
			base = JordysPrompts.promptInt("\nPlease enter the base you'd like to convert from");
			str = JordysPrompts.promptString("Please enter the place value notation of the number in the given base");
			
			while(true)
			{
				try
				{
					System.out.println("\n" + stringToFraction(str, base) + "\n");
					break;
				}
				catch(IllegalArgumentException e)
				{
					str = JordysPrompts.promptString("Invalid Input: failed to parse");
				}
			}

			if(!JordysPrompts.promptYesOrNo("Would you like to try again for another base and number?"))
				break;
		}
	}

	private static void run_placeValueToBase()
	{
		int base;
		String str;

		while(true)
		{
			Menu.clearConsole();
			System.out.println("\nI will convert a number from its place value representation in base-10 to its place value representation in another base.");
			str = JordysPrompts.promptString("\nPlease enter the place value notation of the number you'd like to convert");
			base = JordysPrompts.promptInt("Please enter the base you'd like to convert to");

			while(true)
			{
				try
				{
					System.out.println("\n" + placeValueToBase(str, base) + "\n");
					break;
				}
				catch(IllegalArgumentException e)
				{
					str =JordysPrompts.promptString("Invalid input: failed to parse");
				}
			}
			if(!JordysPrompts.promptYesOrNo("Would you like to try again for another base and number?"))
				break;
		}
	}

	/**********************************************************************************************************************************
	                                                                PUBLIC METHODS                                                     
	                                                                vvvvvvvvvvvvvv                                                     
	**********************************************************************************************************************************/

	public static String decimalToBase(int n, int b)
	{
		PlaceValueNotation notation = new PlaceValueNotation(n, 1, b);
		return notation.alphanumeric();
	}
	 
	public static String fractionToBase(Fraction fraction, int b)
	{
		PlaceValueNotation notation = new PlaceValueNotation(fraction.getNumerator(), fraction.getDenominator(), b);
		return notation.alphanumeric();
	}

	public static String stringToFraction(String str, int b) throws IllegalArgumentException
	{
		return PlaceValueNotation.rationalize(str, b).toString();
	}

	public static String placeValueToBase(String str, int b) throws IllegalArgumentException
	{
		return fractionToBase(PlaceValueNotation.rationalize(str, 10), b);
	}
}