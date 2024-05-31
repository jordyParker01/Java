package apps.bases;

import java.util.*;
import java.io.*;
import utils.menu.*;
public class SpecialNamesSet
{
	TreeMap<Integer, String> userNames = new TreeMap<>();
	String filePath;

	/*
		CONSTRUCTORS
	*/

	public SpecialNamesSet(String filePath)
	{
		this.filePath = filePath;
		load();
	}

	/*
		CLASS METHODS
	*/

	public void save()
	{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
		{
			int index = 1;
			for(Map.Entry<Integer, String> entry : userNames.entrySet())
			{
				writer.write(entry.getKey() + ": " + entry.getValue());
				index++;
				if(index != userNames.size())
					writer.newLine();
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.out.println("Failure to save.");
			Menu.pause();
		}
	}

	public void load()
	{
		userNames.clear();
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
		{
			String[] values;

			while((values = reader.readLine().split(":")) != null)
			{
				userNames.put(Integer.parseInt(values[0].trim()), values[1].trim());
			}
		}
		catch(Exception e)
		{
			try
			{
				filePath = "apps\\bases\\special_names_saves\\default.txt";
				load();
			}
			catch(Exception f)
			{
				System.out.println(f.getMessage());
				System.out.println("\nDefault special names save not found. Program will continue as normal without special names.");
				Menu.pause();
			}
		}
	}
}