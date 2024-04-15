import java.util.Scanner;

public class JoeMama
{
	public static String firstName, lastName;
	public static Scanner inputDevice = new Scanner(System.in); //inputDevice is declared to store values from the keyboard in a buffer.
	
	public static byte joeMamaCount = 0;
	public static String[] possibleJoeMamaResponses = 
	{
	"Very funny.", 
	"Goodness! It's even funnier the second time!", 
	"We've got a real jokester over here. I just showed this to Glen from bookkeeping and he had to go home early on account of his sore ribs.",
	"OK seriously, this is super fucking funny, but I do need your real first and last name, it's for an official government document so it'd be really good for the both of us if you cooperated.",
	"Obviously your name isn't Joe fucking Mama you piece of shit. First and last name right now I'm not asking again.",
	"I didn't care what your name was anyway. Good luck explaining this to the feds. Kill yourself."};
	
	public static void main(String[] args) 
	{
		promptName();
	}
	
	public static void promptName()
	{
		while(true)
		{
			System.out.print("Please enter your first name >> ");
			firstName = CapitalizeFirstCharacter(inputDevice.nextLine().trim().toLowerCase());
			System.out.print("Please enter your last name >> ");
			lastName = CapitalizeFirstCharacter(inputDevice.nextLine().trim().toLowerCase()); 
			
	    		if(firstName.equals("Joe") && lastName.equals("Mama"))
	    		{
	    	   		System.out.println("");
				System.out.println(possibleJoeMamaResponses[joeMamaCount]);
	    	  		joeMamaCount++;
	    	  	  
	    	  	  	if(joeMamaCount == 6)
	    	    		{
	    	        		break;
	    	    		}
	    		}
	    		else
	    		{
	    			System.out.println("Hello " + firstName + " " + lastName + ", it's a pleasure to meet you.");
	    			break;
	    		}
		}
	}

	public static String CapitalizeFirstCharacter(String input)
	{
		if(input == null)
		{
			return input;
		}
		
		char[] charArray = input.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return new String(charArray);
	}
}