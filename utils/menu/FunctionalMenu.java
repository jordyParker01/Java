package utils.menu;

import java.util.function.Consumer;
import utils.menu.Utility;
import utils.JordysPrompts;
import utils.NumberNamerUtil;

public class FunctionalMenu extends Menu
{
	private Utility[] utilities;

	private int currentUtility;
	private boolean active = true;

	/*
		CONSTRUCTORS
	*/

	public FunctionalMenu(Utility[] utils)
	{
		super("");
		utilities = utils;
		this.setGreeting("\n\nHello, this program contains " + NumberNamerUtil.nameNumber(utilities.length) + " helpful utilities.");
	}

	public FunctionalMenu(String g, Utility[] utils)
	{
		super(g);
		utilities = utils;
	}

	public FunctionalMenu(String g, String p, Utility[] utils)
	{
		super(g, p);
		utilities = utils;
	}

	public FunctionalMenu(String g, String p, String e, Utility[] utils)
	{
		super(g, p, e);
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
		while(active)
		{
			currentUtility = JordysPrompts.promptInt(display(), new String[][]{{"0", String.valueOf(utilities.length)}}, "Invalid input");

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

	/*
		PRIVATE METHODS
	*/

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