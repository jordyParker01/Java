/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 1
Purpose: Sport class definition
*******************************************************************************/
package labs;

import java.io.Serializable;
import java.util.Formatter;
import utils.menu.Creatable;
import utils.prompts.ConsolePrompts;
public class Sport implements Serializable, Creatable
{
	private String name;
	private int players;
	private int wins;
	private int losses;

	/*
		CONSTRUCTORS
	*/

	public Sport(String name, int players, int wins, int losses)
	{
		this.name = name;
		this.players = players;
		this.wins = wins;
		this.losses = losses;
	}

	public Sport()
	{
		this("Unnamed Team", 0, 0, 0);
	}

	/*
		ACCESSORS
	*/

	public String getName()
	{
		return name;
	}

	public int getPlayers()
	{
		return players;
	}

	public int getWins()
	{
		return wins;
	}

	public int getLosses()
	{
		return losses;
	}

	/*
		MUTATORS
	*/

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPlayers(int players)
	{
		this.players = players;
	}

	public void setWins(int wins)
	{
		this.wins = wins;
	}

	public void setLosses(int losses)
	{
		this.losses = losses;
	}

	/*
		PUBLIC METHODS
	*/
	
	public double calculatePercentWon()
	{
		return wins * 100.0 / (wins + losses);
	}

	/*
		OVERRIDDEN METHODS
	*/

	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public void prompt()
	{
		name = ConsolePrompts.promptString("\nPlease enter the name of the new team");
		players = ConsolePrompts.promptInt("\nPlease enter the number of players in the team");
		wins = ConsolePrompts.promptInt("\nPlease enter the number of season wins this team has had");
		losses = ConsolePrompts.promptInt("\nPlease enter the number of season losses this team has had");
	}

	@Override
	public String displayState()
	{
		Formatter formatter = new Formatter();
		String result = "\n\nName: " + name;
		result += "\n\nPlayers: " + players;
		result += "\nWins: " + wins;
		result += "\nLosses: " + losses;
		result += formatter.format("\n\nPercentage of Games Won: %.2f%%\n\n", calculatePercentWon());
		return result;
	}
}