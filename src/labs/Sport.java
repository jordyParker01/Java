/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 1
Purpose: Sport class definition
*******************************************************************************/
package labs;

import java.io.Serializable;
public class Sport implements Serializable
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

	@Override
	public String toString()
	{
		return name;
	}
}