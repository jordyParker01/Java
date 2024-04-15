import utils.JordysPrompts;
public class SwitchTest
{
	public boolean resultOfQuery = false;

	public static void main(String[] args)
	{
		System.out.println(isWeekday(JordysPrompts.promptChar("\nPlease enter a day of the week (MTWRFSU)")));
		System.out.println(isWeekday2(JordysPrompts.promptChar("\nPlease enter a day of the week (MTWRFSU)")));
	}
	
	public static boolean isWeekday(char day)
	{
	       boolean result = false;
	       switch(day)
	       {
	           case 'M', 'T', 'W', 'R', 'F' -> result = true;
	           case 'S', 'U' -> result = false;
			   default -> throw new IllegalArgumentException("Invalid day: " + day);
	       }
	   return result;
	}

	public static boolean isWeekday2(char day)
	{
		return switch(day)
		{
	           case 'M', 'T', 'W', 'R', 'F' -> true;
	           case 'S', 'U' -> false;
			   default -> throw new IllegalArgumentException("Invalid day: " + day);
		};
	}
}
