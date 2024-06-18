/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 1
Purpose: Application File
*******************************************************************************/
package labs;

import java.util.ArrayList;
import java.io.*;
import labs.*;
import utils.prompts.ConsolePrompts;
import utils.menu.*;
import utils.Serializer;
public class SportDriver
{
	private static ArrayList<Sport> teams = new ArrayList<Sport>();
	private static Alphabetizer alphabetizer = new Alphabetizer();

	private static String defaultSavePath = "labs\\default_teams.ser";
	private static String userSavePath = "labs\\user_teams.ser";

	static FunctionalMenu applicationMenu = new FunctionalMenu
	(
		new Utility[]
		{
			new Utility(SportDriver::createTeam, "Create a New Team"),
			new Utility(SportDriver::displayAllTeams, "View Teams"),
			new Utility(SportDriver::removeTeam, "Delete a Team")
		}
	);
	
	public static void main(String[] args)
	{
		load();
		applicationMenu.run();
		Serializer.serialize(teams, userSavePath);
	}

	/*
		APPLICATION METHODS
	*/

	public static void createTeam()
	{
		do
		{
			Sport team = new Sport();
			team.prompt();
			teams.add(team);
			teams.sort(alphabetizer);
			Serializer.serialize(teams, userSavePath);
			displayTeamStats(team, false);
		}
		while(ConsolePrompts.promptYesOrNo("\n\nCreate another team?"));
	}

	public static void displayTeamStats(Sport team, boolean pause)
	{
		Menu.clearConsole();
		/*
		System.out.print("\n\n");
		System.out.println("Name: " + team.getName());
		System.out.println("\nPlayers: " + team.getPlayers());
		System.out.println("Wins: " + team.getWins());
		System.out.println("Losses: " + team.getLosses());
		System.out.printf("\nPercentage of Games Won: %.2f%%\n\n", team.calculatePercentWon());
		*/
		System.out.print(team.displayState());
		if(pause) Menu.pause();
	}

	public static void displayAllTeams()
	{
		ObjectMenu<Sport> teamMenu = new ObjectMenu<>
		(
			"Below are listed all teams created so far",
			"Enter the number beside a team to view its stats or enter 0 to go back.",
			"Go Back",
			teams.toArray(new Sport[teams.size()]),
			team -> displayTeamStats(team, true)
		);
		
		teamMenu.run();
	}

	public static void removeTeam()
	{
		String[] names = new String[teams.size()];

		for(int i = 0; i < teams.size(); i++)
		{
			names[i] = teams.get(i).toString();
		}

		SelectionMenu teamMenu = new SelectionMenu
		(
			"Below are listed all teams created so far",
			"Enter the number beside a team to permanently delete it or enter 0 to go back.",
			"Go Back",
			names
		);
		int teamToRemove = teamMenu.run() - 1;
		if(teamToRemove != -1)
		{
			Menu.clearConsole();
			System.out.println("\n\n" + teams.get(teamToRemove) + " has been deleted.\n");
			Serializer.serialize(teams, userSavePath);
			teams.remove(teamToRemove);
			Menu.pause();
		}
	}

	/*
		PRIVATE METHODS
	*/

	private static void load()
	{
		try
		{
			teams = Serializer.deserialize(userSavePath);
		}
		catch(IOException | ClassNotFoundException e)
		{
			Menu.clearConsole();
			System.out.println("Unable to load saved user data.");
			e.printStackTrace();
			Menu.pause();
			teams.add(new Sport("Dallas Cowboys", 52, 5, 3));
			teams.add(new Sport("San Francisco 49ers", 53, 5, 2));
			teams.add(new Sport("Kansas City Chiefs", 52, 3, 2));
			teams.add(new Sport("Green Bay Packers", 53, 4, 1));
			teams.add(new Sport("Tampa Bay Buccaneers", 53, 2, 0));
			teams.add(new Sport("Miami Dolphins", 52, 2, 3));
			teams.add(new Sport("New York Giants", 53, 4, 1));
			teams.add(new Sport("Cincinnati Bengals", 53, 0, 3));
			teams.add(new Sport("Pittsburgh Steelers", 52, 6, 2));
			teams.add(new Sport("New England Patriots", 53, 6, 5));
			teams.sort(alphabetizer);
		}
	}
}