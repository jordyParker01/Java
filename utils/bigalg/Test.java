package utils.bigalg;

import utils.bigalg.*;
import utils.JordysPrompts;
import utils.menu.*;
public class Test
{
	static FunctionalMenu menu = new FunctionalMenu(
		new Utility[]
		{
			new Utility(Test::testAddition, "Test Addition"),
			new Utility(Test::testMultiplication, "Test Multiplication")
		}
	);
	
	public static void main(String[] args)
	{
		menu.run();
	}

	public static void testAddition()
	{
		while(true)
		{
			BigFraction[] fractions = promptFractions();
			BigFraction sum = BigFraction.addMany(fractions);
			System.out.print("\n\n" + sum.toString() + "\n\n");
			if(!JordysPrompts.promptYesOrNo("Try again?"))
				break;
		}
	}

	public static void testMultiplication()
	{
		while(true)
		{
			BigFraction[] fractions = promptFractions();
			BigFraction product = BigFraction.multiplyMany(fractions);
			System.out.print("\n\n" + product.toString() + "\n\n");
			if(!JordysPrompts.promptYesOrNo("Try Again?"))
				break;
		}
	}

	public static BigFraction[] promptFractions()
	{
		BigFraction[] result = null;
		String[] inputs = JordysPrompts.promptStringArray("\nPlease enter any number of fractions");

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
}