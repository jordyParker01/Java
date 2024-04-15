package apps.PNG;

import utils.JordysPrompts;
import utils.menu.*;
import java.util.*;
public class PrimeNumberGenerator
{
	static Settings settings = new Settings();

	static Menu applicationMenu = new Menu
	(
		new Utility[]
		{
			new Utility(PrimeNumberGenerator::run_primes, "Generate the first n primes"),
			new Utility(PrimeNumberGenerator::run_primesUpTo, "Generate all the primes up to n"),
			new Utility(PrimeNumberGenerator::run_primeFactors, "Generate the prime factorization of a given number"),
			new Utility(PrimeNumberGenerator::run_factors, "Generate all the factors of a given number"),
			new Utility(PrimeNumberGenerator::run_isPrime, "Check if a given number is prime"),
			new Utility(PrimeNumberGenerator::run_changeSettings, "Change setttings")
		}
	);

	//Application
	public static void main(String[] args)
	{
		settings.loadSettings("apps\\PNG\\settings.txt");
		applicationMenu.run();
	}
	
	/******************************************************************************************************************************************************
	                                                                  APPLICATION METHODS                                                                  
	                                                                  vvvvvvvvvvvvvvvvvvv                                                                  
	******************************************************************************************************************************************************/

	private static void run_primes()
	{
		while(true)
		{
			Menu.clearConsole();
			int n = JordysPrompts.promptInt("\nI will generate the first n primes. What shall n be?",
			new String[][]{{"1", "n"}},
			"Please enter a value greater than 0 for n.");
			ArrayList<Integer> primes = primes(n);
			int index = 1;
			System.out.println();
			displayList(primes);
			if(!JordysPrompts.promptYesOrNo("\nDo you want to try again for a different value of n?"))
				break;
		}
	}

	private static void run_primesUpTo()
	{
		while(true)
		{
			Menu.clearConsole();
			int n = JordysPrompts.promptInt("\nI will generate all the primes up to n. What shall n be?",
			new String[][]{{"1", "n"}},
			"Please enter a value greater than 0 for n.");
			ArrayList<Integer> primes = primesUpTo(n);
			int index = 1;
			System.out.println();
			displayList(primes);
			if(!JordysPrompts.promptYesOrNo("\nDo you want to try again for a different value of n?"))
				break;
		}
	}

	private static void run_primeFactors()
	{
		while(true)
		{
			Menu.clearConsole();
			int n = JordysPrompts.promptInt("\nI will find the prime factors for n. What shall n be?",
			new String[][]{{"1", "n"}},
			"Please enter a value greater than 0 for n.");

			if(settings.primeFactorizationSettings == -1)
			{
				ArrayList<Integer> factors = primeFactorsList(n);
				displayList(factors);
			}
			else
			{
				Map<Integer, Integer> factorsMap = primeFactorsHash(n);
				displayPrimeFactors(factorsMap);
			}
			if(!JordysPrompts.promptYesOrNo("\nDo you want to try again for a different value of n?"))
				break;
		}
	}

	private static void run_factors()
	{
		while(true)
		{
			Menu.clearConsole();
			int n = JordysPrompts.promptInt("\nI will find all the factors of n. What shall n be?",
			new String[][]{{"1", "n"}},
			"Please enter a value greater than 0 for n.");

			if(settings.listAllFactorsSettings == -1)
			{
				ArrayList<Integer> factors = factorsList(n);
				displayList(factors);
			}
			else
			{
				Map<Integer, Integer> factorsMap = factorsHash(n);
				displayFactors(factorsMap);
			}
			if(!JordysPrompts.promptYesOrNo("\nDo you want to try again for a different value of n?"))
				break;
		}
	}

	private static void run_isPrime()
	{
		while(true)
		{
			Menu.clearConsole();
			int n = JordysPrompts.promptInt("\nI will check if a given number, n, is prime. What shall n be?",
			new String[][]{{"1", "n"}},
			"Please enter a value greater than 0 for n.");
			boolean isPrime = isPrime(n);

			System.out.println();
			if(isPrime)
				System.out.println(n + " is indeed prime.");
			else
			{
				System.out.println(n + " is not prime, it is composite.");

				if(settings.compositeNumbersSettings == -1)
				{
					if(JordysPrompts.promptYesOrNo("Would you like to see the prime factorization of " + n + "?"))
					{
						if(settings.primeFactorizationSettings == -1)
							displayList(primeFactorsList(n));
						else
							displayPrimeFactors(primeFactorsHash(n));
					}
				}
				else
				{
					if(JordysPrompts.promptYesOrNo("Would you like to see the complete list of factors for " + n + "?"))
					{
						if(settings.listAllFactorsSettings == -1)
							displayList(factorsList(n));
						else
							displayFactors(factorsHash(n));
					}
				}
			}
			if(!JordysPrompts.promptYesOrNo("\nDo you want to try again for a different value of n?"))
				break;
		}
	}

	private static void run_changeSettings()
	{
		Menu.clearConsole();
		int settingToChange;
		int updates = 0;
		boolean active = true;
		while(active)
		{
			settingToChange = JordysPrompts.promptInt(
			displaySettings(updates),
			new String[][]{{"0", "3"}},
			"Invalid input"
			);

			updates++;

			active = changeSettings(settingToChange);
			settings.saveSettings("apps\\PNG\\settings.txt");
		}
	}

	private static boolean changeSettings(int i)
	{
		boolean active = true;

		switch(i)
		{
			case 1:
				settings.primeFactorizationSettings *= -1;
				break;
			case 2:
				settings.listAllFactorsSettings *= -1;
				break;
			case 3:
				settings.compositeNumbersSettings *= -1;
				break;
			case 0:
				active = false;
		}

		return active;
	}

	private static String displaySettings(int updates)
	{
		String result = "\nBelow are listed your ";

		if(updates == 0)
			result += "current ";
		else
			result += "updated ";

		result += "settings.\nSelect ";
		if(updates > 0)
			result += "another "; 
		result += "one to change or save and quit if you are satisfied with the ";
		
		if(updates == 0)
			result += "current ";
		else
			result += "new ";

		result += "settings.\n\n";
		result += "1: PRIME FACTORIZATION >> " + settings.primeFactorizationDescription() + "\n";
		result += "2: LIST OF ALL FACTORS >> " + settings.listAllFactorsDescription() + "\n";
		result += "3: COMPOSITE NUMBERS   >> " + settings.compositeNumbersDescription() + "\n\n";
		result += "0: Save and Quit\n\n";
		return result;
	}
	
	/******************************************************************************************************************************************************
	                                                                    DISPLAY METHODS                                                                    
	                                                                    vvvvvvvvvvvvvvv                                                                    
	******************************************************************************************************************************************************/

	private static void displayPrimeFactors(Map<Integer, Integer> factorsMap)
	{
		System.out.println();
		for(Map.Entry<Integer, Integer> entry : factorsMap.entrySet())
		{
			System.out.print(entry.getKey());
			if(entry.getValue() != 1)
				System.out.println("^" + entry.getValue());
			else
				System.out.println();
		}
	}

	private static void displayFactors(Map<Integer, Integer> factorsMap)
	{
		System.out.println();
		for(Map.Entry<Integer, Integer> entry : factorsMap.entrySet())
		{
			System.out.println(entry.getKey() + " * " + entry.getValue());
		}
	}

	private static void displayList(ArrayList<Integer> list)
	{
		int index = 1;
		System.out.println();
		for(int item: list)
		{
			System.out.println(index + ": " + item);
			index++;
		}
	}

	/******************************************************************************************************************************************************
	                                                                    PUBLIC METHODS                                                                     
	                                                                    vvvvvvvvvvvvvv                                                                     
	******************************************************************************************************************************************************/

	public static ArrayList<Integer> primes(int n)
	{
		ArrayList<Integer> primes = new ArrayList<>();
		primes.add(2);
		int currentNumber = 3;
		while(primes.size() < n)
		{
			boolean isPrime = true;
			for(int prime : primes)
			{
				if(currentNumber % prime == 0)
				{
					isPrime = false;
					break;
				}
			}
			if(isPrime)
			{
				primes.add(currentNumber);
			}
			currentNumber++;
		}

		return primes;
	}

	public static ArrayList<Integer> primesUpTo(int n)
	{
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		int currentNumber = 2;
		while(currentNumber <= n)
		{
			boolean isPrime = true;
			for(int prime : primes)
			{
				if(currentNumber % prime == 0)
				{
					isPrime = false;
					break;
				}
			}
			if(isPrime)
			{
				primes.add(currentNumber);
			}
				currentNumber++;
		}

		return primes;
	}

	public static Map<Integer, Integer> primeFactorsHash(int n)
	{
		ArrayList<Integer> primesUpToN = primesUpTo(n);
		Map<Integer, Integer> primeFactors = new HashMap<>();
		for(int prime : primesUpToN)
		{
			while(n % prime == 0)
			{
				primeFactors.put(prime, primeFactors.getOrDefault(prime, 0) +1);
				n /= prime;
			}
		}

		return primeFactors;
	}
	
	public static ArrayList<Integer> primeFactorsList(int n)
	{
		ArrayList<Integer> primesUpToN = primesUpTo(n);
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		for(int prime : primesUpToN)
		{
			while(n % prime == 0)
			{
				primeFactors.add(prime);
				n /= prime;
			}
		}

		return primeFactors;
	}

	public static Map<Integer, Integer> factorsHash(int n)
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();
		Map<Integer, Integer> factorMap = new HashMap<>();
		for(int i = 1; i <= n; i++)
		{
			if(factors.contains(i))
			{
				break;
			}
			else if(n % i == 0)
			{
				factorMap.put(i, n / i);
				factors.add(i);
				factors.add(n / i);
			}
		}

		return factorMap;
	}

	public static ArrayList<Integer> factorsList(int n)
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();

		for(int i = 1; i <= n; i++)
		{
			if(n % i == 0)
				factors.add(i);
		}

		return factors;
	}

	public static boolean isPrime(int n)
	{
		ArrayList<Integer> primes = primesUpTo(n);
		if(primes.contains(n))
			return true;
		else
			return false;
	}
}