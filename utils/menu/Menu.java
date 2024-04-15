package utils.menu;

import java.util.Scanner;
import java.util.function.Consumer;
import utils.menu.Utility;
import utils.JordysPrompts;
import utils.NumberNamerUtil;

public class Menu
{
	private Utility[] utilities;
	private String greeting;
	private String procede;
	private String exit;

	private int currentUtility;
	private boolean active = true;

	//Constructors
	public Menu(Utility[] utils)
	{
		utilities = utils;
		greeting = "\n\nHello, this program contains " + NumberNamerUtil.nameNumber(utilities.length) + " helpful utilities.";
		procede = "\n\nPlease select the next procedure";
		exit = "Exit program";
	}

	public Menu(String g, Utility[] utils)
	{
		utilities = utils;
		greeting = "\n\n" + g;
		procede = "\n\nPlease select the next procedure";
		exit = "Exit program";
	}

	public Menu(String g, String p, Utility[] utils)
	{
		utilities = utils;
		greeting = "\n\n" + g;
		procede = "\n\n" + p;
		exit = "Exit program";
	}

	public Menu(String g, String p, String e, Utility[] utils)
	{
		
		utilities = utils;
		greeting = "\n\n" + g;
		procede = "\n\n" + p;
		exit = e;
	}

	//Open menu navigation
	public void run()
	{
		clearConsole();
		greet();
		while(active)
		{
			currentUtility = JordysPrompts.promptInt(displayUtilities(), new String[][]{{"0", String.valueOf(utilities.length)}}, "Invalid input");

			if(currentUtility != 0)
			{
				clearConsole();
				utilities[currentUtility - 1].execute();
				clearConsole();
			}
			else
			{
				active = false;
			}
		}
	}

	public static void clearConsole()
	{
		try
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void pause()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Press Enter to continue >> ");
		String dump = scanner.nextLine();
	}

	//Private Methods
	private void greet()
	{
		System.out.println(greeting);
	}

	private String displayUtilities()
	{
		String result = procede;

		for(int i = 1; i <= utilities.length; i++)
		{
			result += "\n" + i + ": " + utilities[i - 1].getDescription();
		}
		result += "\n0: " + exit + "\n\n";
		return result;
	}
}