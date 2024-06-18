/************************************************************
Contains only default methods. Implementing class
should override either parse() or prompt().
Although it is not indended, overriding both is also allowed.
************************************************************/
package utils.prompts;

import java.util.Scanner;
public interface Promptable
{
	/*
		Parse input and assign value to `this`.
		For gathering all field values from a single prompt and input.
	*/
	default void parse(String input)
	{
	}

	/*
		Prompt input and assign value to `this`.
		For prompting field values one at a time.
	*/
	default void prompt()
	{
	}

	/*
		Prompt input and assign value to `this`.
		Should only be used if parse() is overridden in implementing class,
		otherwise use prompt().
	*/
	default void prompt(String prompt)
	{
		Scanner scanner = new Scanner(System.in);
		String input;

		System.out.print(prompt + " >> ");

		while(true)
		{
			input = scanner.nextLine().trim();

			try
			{
				this.parse(input);
				break;
			}
			catch(Exception e)
			{
				System.out.print("Invalid input >> ");
			}
		}
	}
}