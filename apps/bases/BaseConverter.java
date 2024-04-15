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
			new Utility(BaseConverter::run_fractionToBase, "Convert a base-10 fraction to a given base"),
			new Utility(BaseConverter::run_stringToFraction, "Convert a place value numeral to a fraction"),
			new Utility(BaseConverter::run_placeValueToBase, "Convert a place value numeral to a given base")
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
			base = JordysPrompts.promptInt("\nPlease enter a base");
			number = JordysPrompts.promptInt("Please enter a number");

			System.out.println(decimalToBase(number, base));
			if(!JordysPrompts.promptYesOrNo("Would you like to try again for another base and number?"))
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
			base = JordysPrompts.promptInt("\nPlease enter a base");
			fraction = Fraction.promptFraction("Please enter a fraction");

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
			base = JordysPrompts.promptInt("\nPlease enter a base");
			str = JordysPrompts.promptString("Please enter the place value notation of the desired number");
			
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
			base = JordysPrompts.promptInt("\nPlease enter a base");
			str = JordysPrompts.promptString("\nPlease enter the place value notation of the desired number");

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