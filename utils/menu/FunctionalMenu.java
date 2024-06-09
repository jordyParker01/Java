package utils.menu;

/*************************************************
A FunctionalMenu, when run, executes the currently
selected utility until the user exits the menu.
**********************************************/
import utils.menu.Utility;
import utils.ConsolePrompts;
import utils.NumberNamerUtil;
public class FunctionalMenu extends Menu
{
	protected Utility[] utilities;

	protected int currentUtility;
	private boolean active;

	/*
		CONSTRUCTORS
	*/

	public FunctionalMenu(String g, String p, String e, Utility[] utils)
	{
		super(g, p, e);
		utilities = utils;
	}

	public FunctionalMenu(String g, String p, Utility[] utils)
	{
		super(g, p);
		utilities = utils;
	}

	public FunctionalMenu(String g, Utility[] utils)
	{
		super(g);
		utilities = utils;
	}

	public FunctionalMenu(Utility[] utils)
	{
		super("Hello, this program contains " + NumberNamerUtil.nameNumber(utils.length) + " helpful utilities.");
		utilities = utils;
	}

	/*
		INSTANCE METHODS
	*/

	//Opens menu for navigation.
	public void run()
	{
		clearConsole();
		greet();
		active = true;
		while(active)
		{
			currentUtility = ConsolePrompts.promptInt(display(), new String[][]{{"0", String.valueOf(utilities.length)}}, "Invalid input");

			if(currentUtility != 0)
			{
				clearConsole();
				utilities[currentUtility - 1].execute();
				clearConsole();
			}
			else
			{
				clearConsole();
				active = false;
			}
		}
	}

	@Override
	String display()
	{
		String result = getProcede();

		for(int i = 1; i <= utilities.length; i++)
		{
			result += "\n" + i + ": " + utilities[i - 1].getDescription();
		}
		result += "\n0: " + getExit() + "\n\n";
		return result;
	}
}