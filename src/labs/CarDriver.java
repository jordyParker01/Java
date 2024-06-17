/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 2
Purpose: Application file
*******************************************************************************/
package labs;

import java.util.ArrayList;
import utils.ConsolePrompts;
import utils.menu.*;
public class CarDriver
{
	private static ArrayList<Car> cars = new ArrayList<>();
	private static FunctionalMenu applicationMenu = new FunctionalMenu
	(
		new Utility[]
		{
			new Utility(CarDriver::createCar, "Create a New Car"),
			new Utility(CarDriver::removeCar, "Delete a Car"),
			new Utility(CarDriver::displayAllCars, "View Garage"),
			new Utility(CarDriver::driveCar, "Drive a Car"),
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

	public static void createCar()
	{
		Car car = new Car();
		
		do
		{
			car.setMake(ConsolePrompts.promptString("\nPlease enter the brand of the new car"));
			car.setModel(ConsolePrompts.promptString("\nPlease enter the model of the car"));
			while(true)
			{
				try
				{
					car.setYear(ConsolePrompts.promptInt("\nPlease enter the car's year"));
					break;
				}
				catch(IllegalArgumentException e)
				{
					System.out.print(e.getMessage());
				}
			}
			while(true)
			{
				try
				{
					car.setMileage(ConsolePrompts.promptInt("\nPlease enter the car's current mileage"));
					break;
				}
				catch(IllegalArgumentException e)
				{
					System.out.print(e.getMessage());
				}
			}
			
			cars.add(car);
			Menu.clearConsole();
			displayCarStats(car, false);
		}
		while(ConsolePrompts.promptYesOrNo("Create another car?"));

	}

	public static void removeCar()
	{
		String[] names = new String[cars.size()];

		for(int i = 0; i < cars.size(); i++)
		{
			names[i] = cars.get(i).toString();
		}

		SelectionMenu carMenu = new SelectionMenu
		(
			"Below are displayed all created cars.",
			"Enter the number beside a car to permanently delete it or enter 0 to go back.",
			"Go Back",
			names
		);

		int car = carMenu.run() - 1;
		Menu.clearConsole();
		System.out.println("\n\n" + names[car] + " has been deleted.\n");
		cars.remove(car);
		Menu.pause();
		
	}

	public static void displayAllCars()
	{
		ObjectMenu<Car> carMenu = new ObjectMenu<Car>
		(
			"Below are displayed all created cars.",
			"Enter the number beside a car to view its stats or enter 0 to go back.",
			"Go Back",
			cars.toArray(new Car[cars.size()]),
			car -> displayCarStats(car, true)
		);

		carMenu.run();
	}

	public static void driveCar()
	{
		ObjectMenu<Car> carMenu = new ObjectMenu<Car>
		(
			"Below are displayed all created cars.",
			"Enter the number beside a car to drive it or enter 0 to go back.",
			"Go Back",
			cars.toArray(new Car[cars.size()]),
			car -> drive(car)
		);

		carMenu.run();
	}

	/*
		PRIVATE METHODS
	*/

	private static void drive(Car car)
	{
		double previousMileage = car.getMileage();
		double miles = ConsolePrompts.promptDouble("\nHow many miles will this trip be?");
		car.drive(miles);
		System.out.printf("\nPrevious Odometer Reading: %,.1f\n", previousMileage);
		double mileage = car.getMileage();
		System.out.printf("Current Odometer Reading: %,.1f\n", mileage);
		Menu.pause();
	}

	private static void displayCarStats(Car car, boolean pause)
	{
		System.out.print("\n\n");
		System.out.println("Make: " + car.getMake());
		System.out.println("Model: " + car.getModel());
		System.out.println("Year: " + car.getYear());
		double mileage = car.getMileage();
		System.out.printf("Current Odometer Reading: %,.1f\n\n", mileage);
		if(pause) Menu.pause();
	}

	private static void addDefaults()
	{
		cars.add(new Car("Toyota", "Corolla", 2003, 689514.2));
		cars.add(new Car("Ford", "F-150", 2014, 81459.7));
		cars.add(new Car("Toyota", "Tacoma", 2022, 41257.5));
		cars.add(new Car("Ford", "Taurus", 1997, 158287.1));
		cars.add(new Car("Nissan", "Altima", 2010, 113641.4));
		cars.add(new Car("Honda", "Civic", 2006, 201358.9));
	}
}