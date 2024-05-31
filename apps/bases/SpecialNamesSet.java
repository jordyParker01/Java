package apps.bases;

import java.util.*;
import java.io.*;
import utils.menu.*;
public class SpecialNamesSet
{
	static TreeMap<Integer, String> userNames = new TreeMap<>();
	static String filePath;

	public SpecialNamesSet(String filePath)
	{
		this.filePath = filePath;
		load(filePath);
	}

	public static void save(String filePath)
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

	public static void load(String filePath)
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
			load("apps\\bases\\special_names_saves\\default.txt");
		}
	}
}