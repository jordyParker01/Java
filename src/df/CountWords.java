package defaultPackage;

import utils.ConsolePrompts;
public class CountWords
{
	static String input;
	static boolean active = true;

	public static void main(String[] args)
	{
		while(active)
		{
			input = ConsolePrompts.promptString("\nPlease input a string of text, any string you like");
			System.out.println("The string has " + numberOfWords(input) + " words.");
			active = ConsolePrompts.promptRestart("\nWould you like to try again with a different string?");
		}
	}

	public static int numberOfWords(String s)
	{
		int words = 0;
		boolean lastCharFormedWord = false;

		for(int i = 0; i < s.length(); i++)
		{
			//checks for illegal, non-word-forming characters, increments word count for each new string of legal characters.
			switch(s.charAt(i))
			{
				case '\s':
					lastCharFormedWord = false;
					break;
				case '\t':
					lastCharFormedWord = false;
					break;
				case '\n':
					lastCharFormedWord = false;
					break;
				case '.':
					lastCharFormedWord = false;
					break;
				case ',':
					lastCharFormedWord = false;
					break;
				case '!':
					lastCharFormedWord = false;
					break;
				case '?':
					lastCharFormedWord = false;
					break;
				case ';':
					lastCharFormedWord = false;
					break;
				case ':':
					lastCharFormedWord = false;
					break;
				case '-':
					lastCharFormedWord = false;
					break;
				case '_':
					lastCharFormedWord = false;
					break;
				case '/':
					lastCharFormedWord = false;
					break;
				default:
					/*
					New words are counted at the beginning. Should they be interrupted by an illegal,
					non-word-forming character, the next word isn't counted until the next legal
					word-forming character is encountered.
					*/
					if(lastCharFormedWord == false)
						words++;
					lastCharFormedWord = true;
			}
		}
		return words;
	}
}