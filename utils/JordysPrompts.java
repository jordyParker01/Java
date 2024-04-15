/******************************************************************************
Programmer: Jordy Parker

Contains static methods for requesting data from the console, handling exceptions
when data is of the incorrect type, and limiting legal numeric types to a certain
range or set of ranges.
*******************************************************************************/
package utils;
import java.util.Scanner;
public class JordysPrompts
{
	static Scanner scanner = new Scanner(System.in);

	/*
	To be called at the end of a while loop prompting the user
	if they would like to run the loop's logic again. Returns
	boolean value to be assigned to the condition of the while
	loop.
	*/
	public static boolean promptRestart(String prompt)
	{
		String response;
		boolean result;
		
		System.out.print(prompt + " (y/n)>> ");

		while(true)
		{
			response = scanner.nextLine().trim().toLowerCase();

			if(response.equals("y"))
			{
				result = true;
				System.out.print("\n\n");
				break;
			}
			else if(response.equals("n"))
			{
				result = false;
				System.out.print("\nOK, Goodbye!\n\n");
				break;
			}
			else
			{
				System.out.print("Please enter a valid response. (y/n) >> ");
			}
		}

		return result;
	}

	public static boolean promptRestart(String prompt, String exitMessage)
	{
		String response;
		boolean result;
		
		System.out.print(prompt + " (y/n)>> ");

		while(true)
		{
			response = scanner.nextLine().trim().toLowerCase();

			if(response.equals("y"))
			{
				result = true;
				System.out.print("\n\n");
				break;
			}
			else if(response.equals("n"))
			{
				result = false;
				System.out.print("\n" + exitMessage + "\n\n");
				break;
			}
			else
			{
				System.out.print("Please enter a valid response. (y/n) >> ");
			}
		}

		return result;
	}

	/*
	Prompts and returns a boolean.
	*/
	public static boolean promptYesOrNo(String prompt)
	{
		String response;
		boolean isYes;
	
		System.out.print(prompt + " (y/n)>> ");
		
		while(true)
		{
			response = scanner.nextLine().trim().toLowerCase();
			
			if(response.equals("y"))
			{
				isYes = true;
				break;
			}
			else if(response.equals("n"))
			{
				isYes = false;
				break;
			}
			else
			{
				System.out.print("Please enter a valid response. (y/n) >> ");
			}
		}
		
		return isYes;
	}

	/*
	Prompts and returns an 8-bit signed integer.
	*/
	public static byte promptByte(String prompt)
	{
		String response;
		byte result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result= Byte.parseByte(response);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 8-bit signed integer >> ");
			}
		}
		
		return result;
	}

	public static byte promptByte(String prompt, String[][]ranges, String conditionErrorMessage)
	{
		String response;
		byte result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Byte.parseByte(response);
				
				if(condition(result, ranges))
				{
					break;
				}
				else
				{
					System.out.print(conditionErrorMessage + " >> ");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 8-bit signed integer >> ");
			}
		}
		
		return result;
	}

	/*
	Prompts and returns a 16-bit signed integer.
	*/
	public static short promptShort(String prompt)
	{
		String response;
		short result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Short.parseShort(response);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 16-bit signed integer >> ");
			}
		}
		
		return result;
	}

	public static short promptShort(String prompt, String[][]ranges, String conditionErrorMessage)
	{
		String response;
		short result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Short.parseShort(response);
				
				if(condition(result, ranges))
				{
					break;
				}
				else
				{
					System.out.print(conditionErrorMessage + " >> ");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 16-bit signed integer >> ");
			}
		}
		
		return result;
	}

	/*
	Prompts and returns a 32-bit signed integer.
	*/
	public static int promptInt(String prompt)
	{
		String response;
		int result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Integer.parseInt(response);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 32-bit signed integer >> ");
			}
		}
		
		return result;
	}

	public static int promptInt(String prompt, String[][]ranges, String conditionErrorMessage)
	{
		String response;
		int result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Integer.parseInt(response);
				
				if(condition(result, ranges))
				{
					break;
				}
				else
				{
					System.out.print(conditionErrorMessage + " >> ");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 32-bit signed integer >> ");
			}
		}
		
		return result;
	}

	/*
	Prompts and returns a 64-bit signed integer.
	*/
	public static long promptLong(String prompt)
	{
		String response;
		long result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Long.parseLong(response);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 64-bit signed integer >> ");
			}
		}
		
		return result;
	}

	public static long promptLong(String prompt, String[][]ranges, String conditionErrorMessage)
	{
		String response;
		long result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Long.parseLong(response);
				
				if(condition(result, ranges))
				{
					break;
				}
				else
				{
					System.out.print(conditionErrorMessage + " >> ");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 64-bit signed integer >> ");
			}
		}
		
		return result;
	}

	/*
	Prompts and returns a 32-bit floating point number.
	*/
	public static float promptFloat(String prompt)
	{
		String response;
		float result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Float.parseFloat(response);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 32-bit floating point number >> ");
			}
		}
		
		return result;
	}

	public static float promptFloat(String prompt, String[][]ranges, String conditionErrorMessage)
	{
		String response;
		float result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Float.parseFloat(response);
				
				if(condition(result, ranges))
				{
					break;
				}
				else
				{
					System.out.print(conditionErrorMessage + " >> ");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 32-bit floating point number >> ");
			}
		}
		
		return result;
	}

	/*
	Prompts and returns a 64-bit floating point number.
	*/
	public static double promptDouble(String prompt)
	{
		String response;
		double result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Double.parseDouble(response);
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 64-bit floating point number >> ");
			}
		}
		
		return result;
	}

	public static double promptDouble(String prompt, String[][]ranges, String conditionErrorMessage)
	{
		String response;
		double result;
		System.out.print(prompt + " >> ");
		
		while(true)
		{
			response = scanner.nextLine().trim();
			
			try
			{
				result = Double.parseDouble(response);
				
				if(condition(result, ranges))
				{
					break;
				}
				else
				{
					System.out.print(conditionErrorMessage + " >> ");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.print("Please enter a valid 64-bit floating point number >> ");
			}
		}
		
		return result;
	}
	
	/*
	Prompts and returns a character of integral data type.
	*/
	public static char promptChar(String prompt)
	{
		String response;
		char result;
		System.out.print(prompt + " >> ");

		while(true)
		{
			response = scanner.nextLine().trim();
			
			if(response.length() != 1)
			{
				System.out.print("Please enter a single character >> ");
			}
			else
			{
				result = response.charAt(0);
				break;
			}
		}

		return result;
	}

	public static String promptString(String prompt)
	{
		System.out.print(prompt + " >> ");
		return scanner.nextLine();
	}

	/*
	Prompts and returns several values formatted as an
	array of strings, useful for prompting several fields
	of data on a single console line. Parsing and exception
	handling must be handled by the method or class calling
	promptStringArray().

	I've used this only once before and found the need
	for exception handling on the front end tedious and
	frustrating, and entirely counterproductive to the
	goal I was trying to achieve by writing this package;
	to handle all exceptions and input errors automatically.

	I've read about hash maps recently and may return to this
	method in the future when I understand them better, as
	I believe they may improve the usability of this method.
	*/
	public static String[] promptStringArray(String prompt)
	{
		String[] result;
		System.out.print(prompt + " >> ");
		result = scanner.nextLine().split(",\\s*");
		return result;
	}

	public static String[] promptStringArray(String prompt, int arrayLength)
	{
		String[] result;
		System.out.print(prompt + " >> ");

		while(true)
		{
			result = scanner.nextLine().split(",\\s*");

			if(result.length != arrayLength)
			{
				System.out.print("Please enter the correct number of items >> ");
			}
			else
			{
				break;
			}
		}
		
		return result;
	}

	/*
	Not accessible outside of the JordysPrompts class. Takes a 
	two-dimensional array as an argument with each row representing
	a legal range of a given numeric data type. The method returns
	a boolean value representing whether a given numeric type falls
	within any of the legal ranges.

	Used on the back end for the overloaded versions of all the numeric
	prompt methods to verify that a user's input falls within a given
	legal range or set of ranges.

	The character 'n' is to be used in either the first or second column
	(never both) to respresent a range that is unbounded in either the
	negative or positive direction respecitively.

	Classes calling an overloaded numeric prompt method must pass in
	a correctly formatted array, otherwise a NumberFormatException will be
	thrown at runtime.

	Example of a correctly formatted array:
	new String[][]{{"n", ""3.14"}, {"100", "200"}}

	Represents a range between 100 and 200 (inclusive) or less than/equal to 3.14.

	Could possibly be improved with hash maps?
	Generics?
	*/
	private static boolean condition(Number numberInput, String[][] ranges)
	{
		boolean result = false;
		
		double doubleInput = numberInput.doubleValue();
		
		for(int i = 0; i < ranges.length; i++)
		{
			if(ranges[i][0].equals("n"))
			{
				result |= doubleInput <= Double.parseDouble(ranges[i][1]);
			}
			else if(ranges[i][1].equals("n"))
			{
				result |= doubleInput >= Double.parseDouble(ranges[i][0]);
			}
			else
			{
				result |= doubleInput >= Double.parseDouble(ranges[i][0]) && doubleInput <= Double.parseDouble(ranges[i][1]);
			}
		}
		
		return result;
	}
}