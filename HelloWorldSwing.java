import javax.swing.*;
public class HelloWorldSwing
{
	public static void main(String[] args)
	{
		JFrame helloFrame = new JFrame("Hello World");
		helloFrame.setSize(250, 100);
		helloFrame.setVisible(true);
		helloFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel greeting = new JLabel("月火水木金上日");
		helloFrame.add(greeting);
	}
}