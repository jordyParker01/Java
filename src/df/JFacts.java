package df;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFacts
{
	public static void main(String[] args)
	{
		JFactsFrame frame = new JFactsFrame();
	}
}

class JFactsFrame extends JFrame implements ActionListener
{
	public static String[] facts = new String[]
	{
		"Martin Van Buren is the only US president whose first language was not English. His first language was Dutch.",
		"Although the Republican Party is called the \"Grand Old Party,\" the Democratic Party is actually older.",
		"Grover Cleveland is the only president to have ever had two non-consecutive terms. He was both the 22nd and 24th president.",
		"Franklin Delano Roosevelt is the only president to have ever been elected more than two terms; he was elected four terms. If he didn't die during his fourth term, he may have been elected more.",
		"Only four presidents have ever been killed by assassination (The two most people forget were Garfield and McKinley).",
		"Gerald Ford is the only president to have not been elected by the American people. He replaced Nixon's elected VP during his second term and succeeded him following his resignation.",
	};
	int index = 0;
	JButton button = new JButton("Next Fact");
	JLabel currentFact = new JLabel(facts[index]);
	FlowLayout flow = new FlowLayout();

	public JFactsFrame()
	{
		super("JFacts");
		setLayout(flow);
		add(button);
		add(currentFact);
		setSize(1400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		index = (index + 1) % 6;
		currentFact.setText(facts[index]);
	}
}