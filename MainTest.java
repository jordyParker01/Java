import apps.PNG.PrimeNumberGenerator;
import apps.bases.BaseConverter;
import utils.menu.*;
public class MainTest
{
	static FunctionalMenu menu = new FunctionalMenu(
		"Hello, this application allows the user to navigate between different programs.",
		"Please select the next program:",
		new Utility[]
		{
			new Utility(() -> PrimeNumberGenerator.main(new String[0]), "Run Prime Number Generator"),
			new Utility(() -> BaseConverter.main(new String[0]), "Run Base Converter")
		}
	);

	public static void main(String[] args)
	{
		menu.run();
	}
}