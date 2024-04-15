package utils.settings;

import utils.menu.*;
import utils.JordysPrompts;
public class Setting
{
	private String name;
	private String[] options;
	private int currentOption;

	//CONSTRUCTORS
	public Setting(String n, int op, String... descriptions)
	{
		if(descriptions.length > 1)
			options = descriptions;
		else
			throw new IllegalArgumentException("Number of options must be greater than one");
		currentOption = op;
		name = n;
	}

	//ACCESSOR METHODS
	public String[] getOptions()
	{
		return options;
	}

	public int getCurrentOption()
	{
		return currentOption;
	}

	public String getName()
	{
		return name;
	}

	//INSTATNCE METHODS
	public String displayOptions()
	{
		String result = "\n\nBelow are listed the options for this setting. Please select a new option.";
		
		for(int i = 1; i < options.length; i++)
		{
			result += "\n"+ i + ": " + options[i];
		}
		result += "\n0: Go back\n\n";
		return result;
	}

	public void changeSetting()
	{
		Menu.clearConsole();
		int result = JordysPrompts.promptInt(displayOptions(), new String[][]{{"0", String.valueOf(options.length)}}, "Invalid input");

		if(result != 0)
			currentOption = result;
		Menu.clearConsole();
	}
}