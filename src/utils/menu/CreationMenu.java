package utils.menu;

import java.util.*;
import java.lang.reflect.Array;
import utils.prompts.*;
import utils.menu.*;
public class CreationMenu<T extends Creatable> extends FunctionalMenu
{
	private ArrayList<T> items = new ArrayList<>();
	private Class<T> clazz;

	Utility[] defaultUtilities = new Utility[]
	{
		new Utility(this::createItem, "Create a new instance"),
		new Utility(this::removeItem, "Delete an existing instance"),
		new Utility(this::displayAllItems, "View all existing instances")
	};
	
	/*
		CONSTRUCTORS
	*/

	public CreationMenu(Class<T> clazz)
	{
		super();
		this.clazz = clazz;
		utilities = defaultUtilities;
	}

	@SuppressWarnings("unchecked")
	public CreationMenu(T[] items)
	{
		super();
		clazz = (Class<T>)items[0].getClass();
		utilities = defaultUtilities;

		this.items.addAll(Arrays.asList(items));
	}

	@SuppressWarnings("unchecked")
	public CreationMenu(T[] items, Utility... utils)
	{
		super();
		clazz = (Class<T>)items[0].getClass();
		List<Utility> list = new ArrayList<>(Arrays.asList(defaultUtilities));
		list.addAll(Arrays.asList(utils));
		utilities = list.toArray(new Utility[list.size()]);
	}

	/*
		INSTANCE METHODS
	*/

	public void createItem()
	{
		do
		{
			try
			{
				T item = clazz.getDeclaredConstructor().newInstance();
				item.prompt();
				items.add(item);
				displayItem(item, false);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error creating item.");
			}
		}
		while(ConsolePrompts.promptYesOrNo("\n\nCreate another instance?"));
	}

	public void removeItem()
	{
		String[] names = names(items.toArray());

		SelectionMenu itemMenu = new SelectionMenu
		(
			"Below are listed all instances created so far",
			"Enter the number beside an instance to permanently delete it or enter 0 to go back.",
			"Go Back",
			names
		);

		int index = itemMenu.run() - 1;
		if(index != -1)
		{
			Menu.clearConsole();
			System.out.println("\n\n" + items.get(index).toString() + " has been deleted.\n");
			items.remove(index);
			Menu.pause();
		}
	}

	@SuppressWarnings("unchecked")
	public void displayAllItems()
	{
		ObjectMenu<T> itemMenu = new ObjectMenu<>
		(
			"Below are listed all instances created so far",
			"Enter the number beside an instance to view its stats or enter 0 to go back.",
			"Go Back",
			items.toArray((T[])Array.newInstance(clazz, items.size())),
			item -> displayItem(item, true)
		);
		
		itemMenu.run();
	}

	public void displayItem(T item, boolean pause)
	{
		Menu.clearConsole();
		System.out.print(item.displayState());
		if(pause) Menu.pause();
	}
}