import utils.JordysPrompts;
import utils.TypeChecks;
import utils.NumberNamerUtil;

public class Exponents
{
	static String[] possibleRetorts = 
	{
		",\nwhich is about how many bitches you've been getting",
		",\nwhich is about how many friends you still have after high school",
		",\nwhich is about how many other guys your wife is hanging out with",
		",\nwhich is about how high your IQ is",
		",\nwhich is about how many men your mother sleeps with",
		",\nwhich is about how many pounds your mother weighs",
	};
	static String customRetort;
	
	static String[] inputArray;

	static int base;
	static int exponent;
	
	static boolean active = true;
		
	public static void main(String[] args)
	{
		
		//JordysPrompts methods handle console prompts and exceptions 

		while(active)
		{
			inputArray = JordysPrompts.promptStringArray("\nPlease enter an integer base and an integer exponent. Separate your inputs using commas.", 2);

			try
			{			
				base = Integer.parseInt(inputArray[0]);
				exponent = Integer.parseInt(inputArray[1]);
			}
			catch(NumberFormatException e)
			{
				if(TypeChecks.isInt(inputArray[0]) && TypeChecks.isInt(inputArray[1]))
				{
					inputArray = JordysPrompts.promptStringArray("\nBoth items invalid; please enter inputs as 32-bit signed integers.", 2);
				}
				else if(!TypeChecks.isInt(inputArray[1]))
				{
					exponent = JordysPrompts.promptInt("\nInvalid exponent; please enter a valid 32-bit signed integer.");
				}
				else
				{
					exponent = Integer.parseInt(inputArray[1]);
					base = JordysPrompts.promptInt("\nInvalid base; please enter a valid 32-bit signed integer.");
				}
			}
			long power = (long)power(base, exponent);
			
			if(power == 0)
			{
				customRetort = possibleRetorts[0];
			}
			else if(power < 5)
			{
				customRetort = possibleRetorts[1];
			}
			else if(power < 35)
			{
				customRetort = possibleRetorts[2];
			}
			else if(power < 75)
			{
				customRetort = possibleRetorts[3];
			}
			else if(power < 10000)
			{
				customRetort = possibleRetorts[4];
			}
			else
			{
				customRetort = possibleRetorts[5];
			}
			
			System.out.print("\n\t" + base + "^" + exponent + " = " + NumberNamerUtil.formatWithCommas(power) + "\nThat's " + NumberNamerUtil.nameNumber(power) + customRetort + ".\n\n");

			active = JordysPrompts.promptRestart("Would you like to try again for another base and exponent?");
		}
	}
	
	public static float power(float base, int exponent)
	{
		float result = 1;		

		for(int i = 1; i <= exponent; i++)
		{
			result *= base;
		}
		
		return result;

	}
}