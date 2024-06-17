package sortingAlgorithms;

import utils.JordysPrompts;

public class BubbleSort
{
	static String[] stringArray;
	static int[] intArray;
	static boolean active = true;

	public static void main(String[] args)
	{
		while(active)
		{
			while(true)
			{
				stringArray = JordysPrompts.promptStringArray("\nPlease enter a list of integers separated by commas");

				intArray = new int[stringArray.length];
				int index = 0;
				boolean parseSuccessful = true;

				for(String element : stringArray)
				{
					try
					{
						intArray[index] = Integer.parseInt(element);
					}
					catch(NumberFormatException e)
					{
						System.out.println("invalid input: Failed to parse list.");
						parseSuccessful = false;
						break;
					}

					index++;
				}

				if(parseSuccessful)
					break;
			}
			
			bubbleSort(intArray);

			System.out.println();
			int index = 0;
			for(int n : intArray)
			{
				if(index != intArray.length - 1)
					System.out.print(n + ", ");
				else
					System.out.print(n + "\n\n");
					index++;
			}

			active = JordysPrompts.promptRestart("Would you like to try again?");
		}


	}

	private static void swap(int[] values, int a, int b)
	{
		if(values[a] > values[b])
		{
			int temp = values[a];
			 values[a] = values[b];
			values[b] = temp;
		}
	}

	private static void bubbleSort(int[] values)
	{
		for(int i = values.length - 1; i > 0; i--)
		{
			for(int j = 0; j < i; j++)
			{
				swap(values, j, j + 1);
			}
		}
	}
}