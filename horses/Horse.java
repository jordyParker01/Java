package horses;

import utils.menu.Menu;
import utils.JordysPrompts;
public class Horse
{
	private String name;
	private String color;
	private int birthYear;

	//CONSTRUCTORS
	public Horse()
	{
		name = "";
		color = "";
		birthYear = 0;
	}

	public Horse(String n)
	{
		name = n;
		color = "";
		birthYear = 0;
	}

	public Horse(String n, String c)
	{
		name = n;
		color = c;
		birthYear = 0;
	}

	public Horse(String n, String c, int y)
	{
		name = n;
		color = c;
		birthYear = y;
	}

	//ACCESSOR METHODS
	public void setName(String n)
	{
		name = n;
	}

	public void setColor(String c)
	{
		color = c;
	}

	public void setBirthYear(int y)
	{
		birthYear = y;
	}

	public String getName()
	{
		return name;
	}

	public String getColor()
	{
		return color;
	}

	public int getBirthYear()
	{
		return birthYear;
	}

	//INSTANCE METHODS
	public void display()
	{
		String list = "\nName: " + name;
		list += "\nColor: " + color;
		list += "\nBirth Year: " + birthYear;

		System.out.println(list);
		System.out.println();
		Menu.pause();
	}

	@Override
	public String toString()
	{
		return name;
	}
}