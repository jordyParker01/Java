package labs;

import utils.ConsolePrompts;
public class Mod1Lab2
{
	static float miles, gallons, mpg;

	public static void main(String[] args)
	{
		System.out.println("\n\nHi, I will calculate the fuel economy of your car for you.");
		do
		{
			miles = ConsolePrompts.promptFloat("Please enter the number of miles driven during your last trip");
			gallons = ConsolePrompts.promptFloat("Please enter the number of gallons consumed by your vehicle during this trip");

			mpg = miles / gallons;
			System.out.println("\nYour car's fuel economy for your last trip sits at around: " + mpg + " miles per gallon.");
		}
		while(ConsolePrompts.promptRestart("Try again for another trip?"));
	}
}