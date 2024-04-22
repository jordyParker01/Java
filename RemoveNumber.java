/******************************************************************************
Programmer: Jordy Parker
Date: 21/04/2024
Lab 13
Instructor: Dr. Rafael Azuaje
College: Northwest Vista College
*******************************************************************************/
import java.util.ArrayList;
import java.util.Iterator;
import utils.JordysPrompts;
public class RemoveNumber
{
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args)
	{
		//Populate list
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(11);
		list.add(13);
		list.add(17);
		list.add(19);
		list.add(23);
		list.add(29);
		displayList();
		removeElement(JordysPrompts.promptInt("\nPlease enter the number you'd like to remove"));
	}

	private static void displayList()
	{
		System.out.println();
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());
	}

	private static void removeElement(Integer n)
	{
		list.remove(n);
		displayList();
	}
}