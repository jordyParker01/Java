package utils.menu;

/*
An ObjectMenu, when run, executes a given instance method 
*/
import utils.menu.*;
import java.util.function.Consumer;
public class ObjectMenu<T> extends FunctionalMenu
{
	T[] instances;

	/*
		CONSTRUCTORS
	*/

	/*
		17.04.2024 temporary start. Will come back to this later.
	*/
	public ObjectMenu(String g, String p, String e, T[] objs, Consumer<T> method)
	{
		super(g, p, e, new Utility[objs.length]);
		//if(!objs.getClass().getComponentType().isAssignableFrom(method.getClass())) throw new IllegalArgumentException("Invalid method reference. The class of the given instances and the class of the method reference must be equal.");
		instances = objs;
		for(int i = 0; i < instances.length; i++)
		{
			int temp = i;
			utilities[i] = new Utility(() -> method.accept(instances[temp]), objs[i].toString());
		}
	}
}