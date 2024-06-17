package df;

import utils.ConsolePrompts;

public class MyRNG
{
	static boolean active = true;

	static float dividend;
	static float divisor;

	static float currentSeed;

	public static void main(String args [])
	{
		while(active)
		{
			dividend = ConsolePrompts.promptFloat("\nPlease enter a dividend.");
			divisor = ConsolePrompts.promptFloat("\nPlease enter a divisor");
			
			while(true)
			{
				System.out.print("\n\t" + random(currentSeed, divisor) + "\n");

				if(ConsolePrompts.promptYesOrNo("\nSee the next value?"))
				{
					currentSeed =  random(currentSeed - (currentSeed * divisor), divisor);y
					dividend = currentSeed * 10;
				}
				else
				{
					active = ConsolePrompts.promptRestart("\nTry again?");
					break;
				}
			}
		}
		
	}
	
	public static int random(float dividend, float divisor)
	{
		//Decimag describes the scale of a number, as in scientific notation: #.## * 10^decimag.
		int decimag = (int)Math.log(dividend);
			
		int result;

		while(true)
		{
			//Decimag Value describes the value at a given magnitude: decimagValue * 10^#
			int decimagValue = (int)(dividend / Math.pow(10, decimag));
			
			if((int)(decimagValue / divisor) != 0)
			{
				result = (int)(decimagValue / divisor);
				break;
			}
			else
			{
				decimag--;
			}
		}

		return result;
	}
}