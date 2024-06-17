package df;

import utils.ConsolePrompts;
import utils.NumberNamer;

public class NumberNamerTest
{
	public static void main(String[] args)
	{
		nameNumber();
	}

	static void nameNumber()
	{
		do
		{
			String number = ConsolePrompts.promptString("\nEnter a number and I will tell you its name.");
			System.out.print("\n\t" + NumberNamer.nameNumber(number) + "\n\n");
		}
		while(ConsolePrompts.promptRestart("Would you like to try again with another number?"));
	}

	static void nameMagnitude()
	{
		do
		{
			int number = ConsolePrompts.promptInt("\nEnter a number and I will construct its magnitude name.");
			System.out.print("\n\t" + NumberNamer.magnitudeName(number) + "\n\n");
		}
		while(ConsolePrompts.promptRestart("Would you like to try again with another number?"));
	}
}