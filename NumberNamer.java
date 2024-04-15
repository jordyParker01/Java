import utils.JordysPrompts;
import utils.NumberNamerUtil;

public class NumberNamer
{
	static boolean active = true;
	static long number;

	public static void main(String[] args)
	{
		while(active)
		{
			number = JordysPrompts.promptLong("\nEnter a number and I will tell you its name.");
			System.out.print("\n\t" + NumberNamerUtil.nameNumber(number) + "\n\n");
			active = JordysPrompts.promptRestart("Would you like to try again with another number?");
		}
	}
}