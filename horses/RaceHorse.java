package horses;

import java.util.Random;
import utils.JordysPrompts;
import utils.menu.Menu;
public class RaceHorse extends Horse
{
	private int racesRun;

	//CONSTRUCTORS
	RaceHorse()
	{
		super();
	}

	RaceHorse(String name)
	{
		super(name);
	}

	RaceHorse(String name, String color)
	{
		super(name, color);
	}

	RaceHorse(String name, String color, int year)
	{
		super(name, color, year);
	}

	RaceHorse(String name, String color, int year, int r)
	{
		super(name, color, year);
		racesRun = r;
	}

	//ACCESSOR METHODS
	public void setRacesRun(int r)
	{
		racesRun = r;
	}

	public int getRacesRun()
	{
		return racesRun;
	}

	//INSTANCE METHODS
	public void display()
	{
		String list = "\nName: " + getName();
		list += "\nColor: " + getColor();
		list += "\nBirth Year: " + getBirthYear();
		list += "\nRaces Run: " + racesRun;

		System.out.println(list);
		System.out.println();
		Menu.pause();
	}

	public void race()
	{
		Random random = new Random();
		float score = random.nextFloat() * racesRun;

		if(score > 10f)
			System.out.println("\nHooray! " + getName() + " won the race!");
		else
			System.out.println("\nWhat a shame! " + getName() + " didn't win this time");

		racesRun++;
		Menu.pause();
	}
}