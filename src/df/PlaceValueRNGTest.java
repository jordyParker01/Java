package df;

import apps.bases.*;
import utils.JordysPrompts;
import utils.menu.*;
import utils.alg32.*;
import java.util.HashMap;
import java.util.Map;

public class PlaceValueRNGTest
{
	static boolean active = true;
	static Fraction fraction;
	static int base;

	public static void main(String[] args)
	{
		while(active)
		{
			Menu.clearConsole();
			System.out.println("\n");
			base = ConsolePrompts.promptInt("Please enter a base");
			fraction = Fraction.promptFraction("Please enter a fraction");
			displayPlaceValue(fraction, base);
			active = ConsolePrompts.promptRestart("Try again with another base and fraction?");
			Menu.clearConsole();
		}
	}

	public static void displayPlaceValue(Fraction f, int b)
	{
		System.out.println();
		PlaceValueNotation pvn = new PlaceValueNotation(f.getNumerator(), f.getDenominator(), b);
		System.out.println("Place Value Notation: " + pvn.alphanumeric());
		System.out.println("Length of Repeating Sequence: " + calculateModulation(pvn) + "\n");
		displayHistogram(pvn);
		System.out.println();
	}

	public static int calculateModulation(PlaceValueNotation pvn)
	{
		int ior = pvn.getIndexOfRepetition();
		int base = pvn.getBase();
		if(ior == -1)
			return 0;
		else
			return pvn.getFractionalDigits().length - ior;
	}

	public static void displayHistogram(PlaceValueNotation pvn)
	{
		int[] digits = pvn.getFractionalDigits();
		int ior = pvn.getIndexOfRepetition();
		int mod = calculateModulation(pvn);

		if(ior != -1)
		{
			HashMap<Integer, Integer> histogram = new HashMap<>();

			//Generate empty hashmap with correct number of entries
			for(int i = 0; i < base; i++)
				histogram.put(i, 0);
			//Tally number of instances of each given digit
			for(int i = ior; i < digits.length; i++)
				histogram.put(digits[i], histogram.get(digits[i]) + 1);
			//Calculate mean and display histogram
			for(int i = 0; i < base; i++)
			{
				System.out.println(i + ": " + histogram.get(i));
			}
		}
	}

	/*
	This really needs to be placed in its own statistics package
	*/
	private static int mode(int[] data)
	{
		HashMap<Integer, Integer> map = new HashMap<>();

		//Tally frequency of each entry in set
		for(int entry : data)
		{
			map.put(entry, map.getOrDefault(entry, 0) + 1);
		}
		//Find highest frequency of any entry.
		int max = 0;
		for(Map.Entry<Integer, Integer> entry : map.entrySet())
		{
			if(entry.getValue() > max)
				max = entry.getValue();
		}

		return max;
	}
}