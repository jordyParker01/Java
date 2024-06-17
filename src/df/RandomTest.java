package df;

import java.util.Random;
import utils.ConsolePrompts;

public class RandomTest
{
    static Random random = new Random();
    static int range;
    static String response;
    static boolean active = true;

    public static void main(String[] args)
    {
        while(active)
        {
            range = ConsolePrompts.promptInt(
                "\nI will roll a die for you. How many sides would you like this die to have?",
                "I only have dice with 4 sides at the fewest and 20 sides at the greatest",
                new Number[]{4, 20}
            );

            System.out.println("I rolled " + (random.nextInt(range) + 1) + ".\n");
            active = ConsolePrompts.promptRestart("Would you like to try again with another die?");
        }
    }
}