package labs;

import java.util.Random;
import utils.ConsolePrompts;
public class Mod1Lab1
{
	public static void main(String[] args)
	{
		Random random = new Random();

		String name;
		int lower, higher;
		
		System.out.println("\n\nHi, I will generate a random number between two user-generated bounds, but first,");
		name = ConsolePrompts.promptString("what is your name?");
		System.out.print("Hello, " + name + ".");

		do
		{
			lower = ConsolePrompts.promptInt("What would you like the lower bound of your randomly generated number to be?");
			higher = ConsolePrompts.promptInt("Wonderful, and the higher bound?", new String[][]{{String.valueOf(lower), "n"}}, "The higher bound should be a greater number than the lower bound");

			System.out.println("Okay, " + name + ",");
			System.out.println("Your random number is: " + random.nextInt(lower, higher));
		}
		while(ConsolePrompts.promptRestart("Would you like to see another random number?"));
	}
}