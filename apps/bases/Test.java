package apps.bases;

import apps.bases.Fraction;
import utils.JordysPrompts;

public class Test
{
	static Fraction[] fractions;

	public static void main(String[] args)
	{
		while(true)
		{
			while(true)
			{
				String[] input = JordysPrompts.promptStringArray("\nPlease enter some fractions");
				fractions = new Fraction[input.length];

				for(Fraction fraction : fractions)
				{
					fraction = new Fraction();
				}

				try
				{
					int index = 0;
					for(String element : input)
					{
						fractions[index] = Fraction.parseFraction(element);
						index++;
					}
					break;
				}
				catch(NumberFormatException e)
				{
					System.out.print("Invalid input.");
				}
			}

			System.out.println(Fraction.multiplyMany(fractions).toString());
			if(!JordysPrompts.promptRestart("Try again?"))
				break;
		}
	}
}