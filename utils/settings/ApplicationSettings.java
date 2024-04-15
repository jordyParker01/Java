package utils.settings;

import java.util.ArrayList;
import utils.menu.*;
import utils.settings.Setting;
public class ApplicationSettings
{
	private Setting[] settings;
	Menu settingsMenu;

	//CONSTRUCTORS
	public ApplicationSettings(Setting... s)
	{
		settings = s;

		ArrayList<Utility> utilList = new ArrayList<Utility>();
		for(Setting setting : settings)
		{
			utilList.add(new Utility(setting::changeSetting, setting.getName()));
		}
		Utility[] utilities = utilList.toArray(new Utility[utilList.size()]);

		settingsMenu = new Menu("Below are listed the application settings", "Please select one to update or press 0 to save and quit", "Save and Quit", utilities);
	}

	//ACCESSOR METHODS
	public Setting[] getSettings()
	{
		return settings;
	}

	//INSTANCE METHODS
	public void viewSettings()
	{
		settingsMenu.run();
	}
}