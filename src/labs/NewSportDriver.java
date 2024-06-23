package labs;

import labs.*;
import utils.creator.*;
public class NewSportDriver
{
	public static void main(String[] args)
	{
		CreationMenu<Sport> applicationMenu = new CreationMenu<>(Sport.class, "labs\\sport_saves");
		applicationMenu.setClassName("team");
		applicationMenu.setComparator(new Alphabetizer<Sport>());
		applicationMenu.run();
	}
}