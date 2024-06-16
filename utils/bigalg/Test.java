package utils.bigalg;

import java.util.Arrays;
import utils.bigalg.*;
import utils.ConsolePrompts;
import utils.menu.*;
public class Test
{
	static FunctionalMenu menu = new FunctionalMenu(
		new Utility[]
		{
			new Utility(Test::testAddition, "Test Addition"),
			new Utility(Test::testMultiplication, "Test Multiplication"),
			new Utility(Test::testSort, "Test Sorting")
		}
	);
	
	public static void main(String[] args)
	{
		menu.run();
	}

	public static void testAddition()
	{
		do
		{
			BigFraction[] fractions = promptFractions();
			BigFraction sum = BigFraction.addMany(fractions);
			System.out.print("\n\n" + sum.toString() + "\n\n");
		}
		while(ConsolePrompts.promptYesOrNo("Try again?"));
	}

	public static void testMultiplication()
	{
		do
		{
			BigFraction[] fractions = promptFractions();
			BigFraction product = BigFraction.multiplyMany(fractions);
			System.out.print("\n\n" + product.toString() + "\n\n");
		}
		while(ConsolePrompts.promptYesOrNo("Try again?"));
	}

	public static void testSort()
	{
		do
		{
			BigFraction[] fractions = promptFractions();
			Arrays.sort(fractions);
			displayFractions(fractions);
		}
		while(ConsolePrompts.promptYesOrNo("Try again?"));
	}

	public static BigFraction[] promptFractions()
	{
		BigFraction[] result = null;
		String[] inputs = ConsolePrompts.promptStringArray("\nPlease enter any number of fractions");

		while(true)
		{
			result = new BigFraction[inputs.length];
			try
			{
				for(int i = 0; i < result.length; i++)
				{
					result[i] = BigFraction.parseFraction(inputs[i]);
				}

				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Invalid input >> ");
			}
		}

		return result;
	}

	public static void displayFractions(BigFraction[] fractions)
	{
		for(int i = 1; i < fractions.length; i++)
		{
			System.out.println(i + ": " + fractions[i].toString());
		}
	}
}