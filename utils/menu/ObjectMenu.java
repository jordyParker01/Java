package utils.menu;

/*
An ObjectMenu, when run, executes a given instance method 
*/
import utils.menu.*;
public class ObjectMenu<T> extends FunctionalMenu
{
	Procedure instanceMethod;
	T[] instances;

	/*
		CONSTRUCTORS
	*/

	/*
		17.04.2024 temporary start. Will come back to this later.
	*/
	public ObjectMenu(String g, String p, String e, T[] objs, Procedure method)
	{
		super(g, p, e, new Utility[objs.length]);
		if(!objs.getClass().getComponentType().isAssignableFrom(method.getClass()))
			throw new IllegalArgumentException("Invalid method reference. The class of the given instances and the class of the method reference must be equal.");
		instances = objs;
		instanceMethod = method;
		for(int i = 0; i < instances.length; i++)
		{
			utilities[i] = new Utility(instances[i]::method, objs[i].toString());
		}
	}
}