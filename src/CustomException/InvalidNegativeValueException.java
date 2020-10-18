package CustomException;

public class InvalidNegativeValueException extends Exception{
	

	public InvalidNegativeValueException() {
		super();
	}
	

	public String message() {
		
		return "Please enter non-negative values ";
	}
}
