package apps.bases;

public class IntegerOverflowException extends RuntimeException
{
	public IntegerOverflowException(String message)
	{
		super(message);
	}
}