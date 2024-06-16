/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 3
Purpose: Application file
*******************************************************************************/
package labs;

import java.util.ArrayList;
import utils.menu.*;
import utils.ConsolePrompts;
public class HouseDriver
{
	private static ArrayList<House> houses = new ArrayList<>();

	private static FunctionalMenu applicationMenu = new FunctionalMenu
	(
		new Utility[]
		{
			new Utility(HouseDriver::createHouse, "Create a New House"),
			new Utility(HouseDriver::removeHouse, "Delete an existing House"),
			new Utility(HouseDriver::displayHouses, "View All Houses"),
			new Utility(HouseDriver::changePriceOfHouse, "Change Price of House"),
		}
	);
	
	public static void main(String[] args)
	{
		addDefaults();
		applicationMenu.run();
	}

	/*
		APPLICATION METHODS
	*/

	public static void createHouse()
	{
		do
		{
			String address = ConsolePrompts.promptString("\nPlease enter the address of the new house");
			int bedrooms = ConsolePrompts.promptInt("How many bedrooms does this new house have?");
			int bathrooms = ConsolePrompts.promptInt("How many baths?");
			double price = ConsolePrompts.promptDouble("How much is the asking price?");
			int squareFeet = ConsolePrompts.promptInt("Finally, how many square feet is this unit?");
			Menu.clearConsole();
			House house = new House(address, bedrooms, bathrooms, price, squareFeet);
			houses.add(house);
			displayHouseStats(house, false);
		}
		while(ConsolePrompts.promptYesOrNo("Create another House?"));
	}

	public static void removeHouse()
	{
		String[] names = new String[houses.size()];

		for(int i = 0; i < houses.size(); i++)
		{
			names[i] = houses.get(i).toString();
		}

		SelectionMenu houseMenu = new SelectionMenu
		(
			"Below are listed all houses created so far",
			"Enter the number beside one to permanently delete it or enter 0 to go back",
			"Go Back",
			names
		);
		int house = houseMenu.run() - 1;
		 
		Menu.clearConsole();
		System.out.println("\n\n" + names[house] + " has been deleted.\n");
		houses.remove(house);
		Menu.pause();
	}

	public static void displayHouses()
	{
		ObjectMenu<House> houseMenu = new ObjectMenu<>
		(
			"Below are listed all houses created so far",
			"Enter the number beside one to view its stats or enter 0 to go back",
			"Go Back",
			houses.toArray(new House[houses.size()]),
			house -> displayHouseStats(house, true)
		);
		houseMenu.run();
	}

	public static void changePriceOfHouse()
	{
		ObjectMenu<House> houseMenu = new ObjectMenu<>
		(
			"Below are listed all houses created so far",
			"Enter the number beside one to update its price or enter 0 to go back",
			"Go Back",
			houses.toArray(new House[houses.size()]),
			house -> changePrice(house)
		);
		houseMenu.run();
	}

	/*
		PRIVATE METHODS
	*/

	private static void displayHouseStats(House house, boolean pause)
	{
		System.out.print("\n\n");
		System.out.println("Address: " + house.getAddress());
		System.out.println("Beds: " + house.getBedrooms());
		System.out.println("Baths: " + house.getBathrooms());
		System.out.println("Square Feet: " + house.getSquareFeet());
		System.out.printf("\nCurrent Market Price: $%,.2f\n\n", house.getPrice());
		if(pause) Menu.pause();
	}

	private static void changePrice(House house)
	{
		System.out.print("\n\n");
		System.out.printf("Current price of " + house.toString() + ": $%,.2f.\n\n", house.getPrice());
		double price = ConsolePrompts.promptDouble("Please enter a new price for " + house.toString());

		Menu.clearConsole();
		house.setPrice(price);
		System.out.printf("\n\nThe asking price for " + house.toString() + " has been updated to $%,.2f.\n\n", price);
		Menu.pause();
	}

	private static void addDefaults()
	{
		houses.add(new House("114 Nigel St.", 2, 1, 110000.0, 1500));
		houses.add(new House("109 Harmon Rd.", 3, 2, 270000.0, 3500));
		houses.add(new House("1027 Bartholmew Blv.", 5, 4, 1870000.0, 8400));
		houses.add(new House("101 Betty Sue Dr.", 2, 2, 180000.0, 2000));
		houses.add(new House("204 Rodriguez St.", 2, 1, 98000.0, 1000));
	}
}