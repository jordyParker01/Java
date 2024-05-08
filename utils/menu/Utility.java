package utils.menu;

import utils.menu.Procedure;
//Utility is a class which contains a Procedure (functional interface) and a description of the Procedure.
public class Utility
{
	//Fields
	private Procedure procedure;
	private String description;

	//Constructor
	public Utility(Procedure p, String d)
	{
		procedure = p;
		description = d;
	}

	//Accessor Methods
	public Procedure getProcedure()
	{
		return procedure;
	}

	public String getDescription()
	{
		return description;
	}

	public void setProcedure(Procedure p)
	{
		procedure = p;
	}

	public void setDescription(String d)
	{
		description = d;
	}

	//Execute Methods
	public void execute()
	{
		procedure.execute();
	}
}