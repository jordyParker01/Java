package utils.menu;

/*******************************************************************************
A SelectionMenu, when run, does not execute any procedure.
Rather, it simply returns the index of the selected option to the client method.
*******************************************************************************/
import utils.ConsolePrompts;
public class SelectionMenu extends Menu
{
	private String[] options;

	/*
		CONSTRUCTORS
	*/

	public SelectionMenu(String g, String p, String e, String[] ops)
	{
		super(g, p, e);
		options = ops;
	}

	public SelectionMenu(String g, String p, String[] ops)
	{
		super(g, p, "Go back");
		options = ops;
	}

	public SelectionMenu(String g, String[] ops)
	{
		super(g, "Please select the desired option", "Go back");
		options = ops;
	}

	public SelectionMenu(String[] ops)
	{
		super("Selection Menu", "Please select the desired option:", "Go back");
		options = ops;
	}
	
	/*
		INSTANCE METHODS
	*/

	public int run()
	{
		clearConsole();
		greet();
		int result = ConsolePrompts.promptInt(display(), "Invalid input", new Number[]{{0, options.length}});
		clearConsole();
		return result;
	}

	@Override
	String display()
	{
		String result = getProcede();

		for(int i = 1; i <= options.length; i++)
		{
			result += "\n" + i + ": " + options[i - 1];
		}
		result += "\n0: " + getExit() + "\n\n";
		return result;
	}
}