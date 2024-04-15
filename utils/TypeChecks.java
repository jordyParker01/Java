package utils;

public class TypeChecks
{
	public static boolean isByte(String str)
	{
		try
		{
			Byte.parseByte(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isShort(String str)
	{
		try
		{
			Short.parseShort(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isInt(String str)
	{
		try
		{
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isLong(String str)
	{
		try
		{
			Long.parseLong(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isFloat(String str)
	{
		try
		{
			Float.parseFloat(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isDouble(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isChar(String str)
	{
		return str !=null && str.length() == 1;
	}

	public static boolean isBoolean(String str)
	{
		return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
	}

	//converts a 32-bit floating point value to a 32-bit signed integer only if no data is lost in the conversion.
	public static Number FloatToIntLossless(float floatValue)
	{
		if(floatValue == (int)floatValue)
		{
			return (int)floatValue;
		}
		else
		{
			return floatValue;
		}
	}

	//converts a 64-bit floating point value to a 32-bit signed integer only if no data is lost in the conversion.
	public static Number DoubleToIntLossless(double doubleValue)
	{
		if(doubleValue == (int)doubleValue)
		{
			return (int)doubleValue;
		}
		else
		{
			return doubleValue;
		}
	}
}