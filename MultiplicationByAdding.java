/******************************************************************************
Programmer: Jordy Parker
Date: 14/04/2024
Lab 12
Instructor: Dr. Rafael Azuaje
College: Northwest Vista College
*******************************************************************************/
import utils.JordysPrompts;
public class MultiplicationByAdding
{
	public static void main(String[] args)
	{
		int a = JordysPrompts.promptInt("\nPlease enter an integer");
		int b = JordysPrompts.promptInt("Please enter another integer");

		System.out.println("\n" + multiply(a, b));
	}

	private static int multiply(int a, int b)
	{
		int result;
		boolean sign;

		/*
		Evaluates sign of the resultant product: true = positive, false = negative.
		I almost forgot about the ternary operator, I found an oportunity to use it here
		in addition to the XOR operator. Very elegant, was several lines of code at first.

		I chose this implementation to ensure the control variable, a, would never be
		less than 1 during product evaluation, as that would result in infinite recursion.
		*/
		sign = a < 0 ^ b < 0 ? false : true;

		a = Math.abs(a);
		b = Math.abs(b);

		if(a == 0 || b == 0)
			result = 0;
		else if(sign)
		{
			if(a == 1)
				result = b;
			else
			{
				result = multiply(a - 1, b) + b;
			}
		}
		else
		{
			if(a == 1)
				result = -b;
			else
			{
				result = multiply(a - 1, -b) - b;
			}
		}

		//Not a single multiplication operator is used in the implementation of this method!
		return result;
	}
}