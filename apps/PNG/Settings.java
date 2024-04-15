package apps.PNG;

import java.io.*;

public class Settings
{
	//-1 shows numbered list
	//1 shows exponential representation of repeated factors
	int primeFactorizationSettings;

	//-1 shows numbered list
	//1 shows paired factors
	int listAllFactorsSettings;

	//-1 shows prime factorization when isPrime() returns false in run_isPrime()
	//1 shows list of all factors when isPrime() returns false in run_isPrime()
	int compositeNumbersSettings;

	public Settings(int p, int f, int c)
	{
		primeFactorizationSettings = p;
		listAllFactorsSettings = f;
		compositeNumbersSettings = c;
	}

	public Settings()
	{
		primeFactorizationSettings = 1;
		listAllFactorsSettings = 1;
		compositeNumbersSettings = 1;
	}

	public String primeFactorizationDescription()
	{
		if(primeFactorizationSettings == -1)
			return "Displays as numbered list";
		else
			return "Displays in exponential form";
	}

	public String listAllFactorsDescription()
	{
		if(listAllFactorsSettings == -1)
			return "Displays as numbered list";
		else
			return "Displays in factor pairs";
	}

	public String compositeNumbersDescription()
	{
		if(compositeNumbersSettings == -1)
			return "Displays prime factorization";
		else
			return "Displays all factors";
	}

	public void saveSettings(String filePath)
	{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
		{
			String settingsString = String.format("%d %d %d", primeFactorizationSettings, listAllFactorsSettings, compositeNumbersSettings);
			writer.write(settingsString);
		}
		catch(IOException e)
		{
			//handle exception
		}
	}

	public void loadSettings(String filePath)
	{
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
		{
			String settingsString = reader.readLine();
			if(settingsString != null)
			{
				String[] settingsArray = settingsString.split(" ");
				if(settingsArray.length == 3)
				{
					primeFactorizationSettings = Integer.parseInt(settingsArray[0]);
					listAllFactorsSettings = Integer.parseInt(settingsArray[1]);
					compositeNumbersSettings = Integer.parseInt(settingsArray[2]);
				}
				else 
				{
					primeFactorizationSettings = 1;
					listAllFactorsSettings = 1;
					compositeNumbersSettings = 1;
				}
			}
		}
		catch(IOException | NumberFormatException e)
		{
			primeFactorizationSettings = 1;
			listAllFactorsSettings = 1;
			compositeNumbersSettings = 1;
		}
	}
}