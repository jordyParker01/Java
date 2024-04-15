package utils;

import java.text.DecimalFormat;

public class NumberNamerUtil
{	
	static String[] unitsNames = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	static String[] teensNames = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	static String[] tensNames = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninty"};
	static String[] magnitudeNames = {"thousand", "million", "billion", "trillion", "quadrillion", "quintillion"};

	public static String nameNumber(long number)
	{
		long currentNumber = number;
		int magValue;
		int numberMagnitude = magnitude(number);
		String result = "";
		
		//Unique condition for zero...
		if(number == 0)
		{
			return "zero";
		}

		//Breaks number into its composite magnitudes and names them based on the value at each magnitude...
		for(int i = numberMagnitude; i >= -1; i--)
		{
			magValue = magnitudeValue(currentNumber);
			
			if(i != numberMagnitude && magValue != 0)//Add a space for all but the first loop, provided the current magnitude has a value greater than 0...
			{
				result += " ";
			}
			if(magValue != 0)//Do not name magnitudes with no value...
			{
				result += modThousandName(magValue);
			}
			if(i > -1 && magValue != 0)//Assign magnitude name for all magnitudes with a value greater than 0 and for all but the last loop...
			{
				result += " " + magnitudeNames[i];
			}
			
			//Subtract the current magnitude and start the next loop on the next lowest magnitude...
			currentNumber -= (magValue * Math.pow(10, 3 * (i+1)));
		}
		
		return result;
	}

	//Names numbers up to but not including 100.
	static String modHundredName(int n)
	{
		if(n < 10)
		{
			return unitsNames[n - 1];
		}
		else if(n < 20)
		{
			return teensNames[n % 10];
		}
		else
		{
			String result = tensNames[(n / 10) - 2];
			if(n % 10 != 0)
			{
				result += "-" + unitsNames[(n % 10) -1];
			}
		
			return result;
		}
	}

	//Names numbers up to but not including 1,000.
	static String modThousandName(int n)
	{
		if(n < 100)
		{
			return modHundredName(n);
		}
		else
		{
			String result = unitsNames[(n / 100) -1] + " hundred";
			if(n % 100 != 0)
			{
				result += " " + modHundredName(n % 100);
			}
			
			return result;
		}
	}
	
	//Returns the magnitude of a number (thousands >> 0, millions >> 1, billions >> 2, trillions >> 3, etc...)
	public static int magnitude(long number)
	{
		double dNumber = (double)number;
		
		return (int)Math.floor(((Math.log10(dNumber) - 3) / 3));
	}

	//Returns the value of a given number's magnitude (37 thousand >> 37, 961 trillion >> 961, etc...)
	public static int magnitudeValue(long number)
	{
		return (int)(number / Math.pow(10, 3 * magnitude(number) + 3));
	}

	//adds commas to an integer
	public static String formatWithCommas(Number n)
	{
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		return decimalFormat.format(n);
	}
}