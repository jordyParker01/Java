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

	public Menu(String g, String p, String e)
	{
		greeting = "\n\n" + g;
		procede = "\n\n" + p + "\n";
		exit = e;
	}

	public Menu(String g, String p)
	{
		this(g, p, "Exit program");
	}

	public Menu(String g)
	{
		this(g, "Please select the next procedure:", "Exit program");
	}

	/*
		ACCESSOR METHODS
	*/

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
		MUTATOR METHODS
	*/

	public void setGreeting(String g)
	{
		greeting = "\n\n" + g;
	}

	public void setProcede(String p)
	{
		procede = "\n\n" + p + "\n";
	}

	public void setExit(String e)
	{
		exit = e;
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