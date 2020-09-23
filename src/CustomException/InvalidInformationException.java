package CustomException;

public class InvalidInformationException extends Exception {


	public InvalidInformationException () {
		super();
		
	}
	
	public String message() {
		
		return "Please complete the fields completely";
	}
}
