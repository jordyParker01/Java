package df;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
public class DateTest
{
	public static void main(String[] args)
	{
		try
		{
			PrintStream out = new PrintStream(System.out, true, "UTF-8");
			out.println("月火水木金上日");
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}