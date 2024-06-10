/********************************
	Name: Jordy Parker
	Date: 09.06.2024
	Module: 1
	Lab: 3
	Purpose: Application File
********************************/
package labs;

import utils.ConsolePrompts;
public class Mod1Lab3
{
	static final int MONTHS_IN_YEAR = 12;
	static double principle, annualRate;
	static int period; //in years

	public static void main(String[] args)
	{
		System.out.println("\n\nHi, I will calculate your monthly mortgage payment for you.");
		do
		{
			principle = ConsolePrompts.promptDouble("Please enter the loan amount for this mortgage", new String[][]{{"0", "n"}}, "Only positive values allowed for loan amount");
			annualRate = ConsolePrompts.promptDouble("Please enter the annual interest rate for this loan", new String[][]{{"0", "n"}}, "Only positive values allowed for interest rate");
			period = ConsolePrompts.promptInt("Please enter the period of this loan in years", new String[][]{{"0", "n"}}, "Only positive values allowed for period");
			System.out.printf("Your monthly payment will be $%.2f\n", CalculateMonthlyPayment());
		}
		while(ConsolePrompts.promptRestart("Try again with different values?"));
	}

	private static double CalculateMonthlyPayment()
	{
		int payments = period * MONTHS_IN_YEAR;
		double monthlyRate = annualRate / MONTHS_IN_YEAR;
		double percentIncrease = Math.pow((1 + monthlyRate), payments);

		return principle * monthlyRate * percentIncrease / (percentIncrease - 1);
	}
}