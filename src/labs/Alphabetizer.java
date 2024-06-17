/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 1
Purpose: Implements Comparator to alphabetize teams by name.
*******************************************************************************/
package labs;

import java.util.Comparator;
public class Alphabetizer implements Comparator<Sport>
{
	@Override
	public int compare(Sport team1, Sport team2)
	{
		return team1.getName().compareTo(team2.getName());
	}
}