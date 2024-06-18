package labs;

import labs.*;
import utils.menu.*;
public class NewSportDriver
{
	private static CreationMenu<Sport> applicationMenu = new CreationMenu<>(Sport.class);
	
	public static void main(String[] args)
	{
		applicationMenu.run();
	}
}