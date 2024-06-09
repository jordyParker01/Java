package horses;

import java.util.ArrayList;
import utils.ConsolePrompts;
import utils.menu.*;
import horses.*;
public class DemoHorses
{
	static ArrayList<RaceHorse> raceHorses = new ArrayList<>();
	static ArrayList<RaceHorse> playerHorses = new ArrayList<>();

	static FunctionalMenu applicationMenu = new FunctionalMenu
	(
		new Utility[]
		{
			new Utility(DemoHorses::createRaceHorse, "Create new race horse"),
			new Utility(DemoHorses::displayAllRaceHorses, "Display all race horses"),
			new Utility(DemoHorses::displayAllPlayerHorses, "Display all player horses"),
			new Utility(DemoHorses::raceHorse, "Race a horse")
		}
	);

	public static void main(String[] args)
	{
		raceHorses.add(new RaceHorse("Friday", "black", 2007, 20));
		raceHorses.add(new RaceHorse("weed horse", "green", 420, 2));
		raceHorses.add(new RaceHorse("Nuclear Shitstain", "rainbows sprinkles", 4065, Integer.MAX_VALUE - 100));
		raceHorses.add(new RaceHorse(" vagina", "bubblegum pink", 69, 11));
		raceHorses.add(new RaceHorse("Herobrine", "invisible", 2009, 1_000_000));
		raceHorses.add(new RaceHorse("megatron", "badass red and black flames", 1993, 651));
		raceHorses.add(new RaceHorse("cumshot", "cum colored", 2015, -19));
		raceHorses.add(new RaceHorse("Richard Nixon", "white", 1913, 3));
		raceHorses.add(new RaceHorse("Gay Buttsex", "hot pink and lavendar", 1969, 493));

		applicationMenu.run();
	}
	
	private static void createRaceHorse()
	{
		while(true)
		{
			String name = ConsolePrompts.promptString("\nPlease enter the horse's name");
			String color = ConsolePrompts.promptString("\nPlease enter the horse's color");
			int year = ConsolePrompts.promptInt("\nPlease enter the Horse's year of birth");
			int racesRun = ConsolePrompts.promptInt("\nPlease enter the number of races run by this horse");
			
			RaceHorse raceHorse = new RaceHorse(name, color, year, racesRun);
			raceHorses.add(raceHorse);
			playerHorses.add(raceHorse);
			System.out.println("\n" + name + ", a " + color + " racehorse run in " + racesRun + " races and born in " + year + " has been added.");
			if(!ConsolePrompts.promptYesOrNo("Would you like to create another racehorse?"))
				break;
		}
	}
	
	private static void displayAllRaceHorses()
	{
		ObjectMenu<Horse> displayMenu = new ObjectMenu<>(
			"Below are listed all racehorses that have been created",
			"Select a horse to view its stats or enter zero to go back", 
			"Go back",
			raceHorses.toArray(new RaceHorse[raceHorses.size()]),
			horse -> horse.display()
		);

		displayMenu.run();
	}
	
	private static void displayAllPlayerHorses()
	{
		ObjectMenu<Horse> displayMenu = new ObjectMenu<>(
			"Below are listed all racehorses that have been created",
			"Select a horse to view its stats or enter zero to go back", 
			"Go back",
			playerHorses.toArray(new RaceHorse[playerHorses.size()]),
			horse -> horse.display()
		);

		displayMenu.run();
	}

	private static void raceHorse()
	{
		ObjectMenu<RaceHorse> displayMenu = new ObjectMenu<>(
			"Below are listed all racehorses that have been created",
			"Select a horse to race or enter zero to go back", 
			"Go back",
			raceHorses.toArray(new RaceHorse[raceHorses.size()]),
			horse -> horse.race()
		);

		displayMenu.run();
	}
}