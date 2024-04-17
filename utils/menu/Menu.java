package utils.menu;

import java.util.Scanner;
public abstract class Menu
{
	private String greeting;
	private String procede;
	private String exit;

	/*
		CONSTRUCTORS
	*/
	public Menu(String g)
	{
		greeting = "\n\n" + g;
		procede = "\n\nPlease select the next procedure:";
		exit = "Exit program";
	}

	public Menu(String g, String p)
	{
		greeting = "\n\n" + g;
		procede = "\n\n" + p;
		exit = "Exit program";
	}

	public Menu(String g, String p, String e)
	{
		greeting = "\n\n" + g;
		procede = "\n\n" + p;
		exit = e;
	}

	/*
		ACCESSOR METHODS
	*/

	public void setGreeting(String g)
	{
		greeting = g;
	}

	public void setProcede(String p)
	{
		procede = p;
	}

	public void setExit(String e)
	{
		exit = e;
	}

	public String getGreeting()
	{
		return greeting;
	}

	public String getProcede()
	{
		return procede;
	}

	public String getExit()
	{
		return exit;
	}

	/*
		CLASS METHODS
	*/

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

	/*
		INSTANCE METHODS
	*/

	public void greet()
	{
		System.out.println(getGreeting());
	}

	/*
		ABSTRACT METHODS
	*/

	abstract String display();
}