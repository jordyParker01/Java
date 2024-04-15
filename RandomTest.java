import java.util.Random;
import utils.JordysPrompts;

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
            range = JordysPrompts.promptInt("\nI will roll a die for you. How many sides would you like this die to have?",
            new String[][]{{"4", "20"}},
            "I only have dice with 4 sides at the fewest and 20 sides at the greatest");

            System.out.println("I rolled " + (random.nextInt(range) + 1) + ".\n");
            active = JordysPrompts.promptRestart("Would you like to try again with another die?");
        }
    }
}