
/**
 * @author Tian Low ttl2132
 * This class prints general messages to the user and checks to make sure a valid file type is entered.
 */
public class ConsolePrinter {
	public ConsolePrinter() {
	}

	public void askName() {
		System.out.println("Enter File name.");
	}

	public void printArrow() {
		System.out.println(">");
	}

	public void printTypeQuestion() {
		System.out.println(
				"What kind of file would you like to make? Enter '.txt' for a .txt file and '.cmp' for a .cmp compressed file.");
	}

	public void printStart() {
		System.out.println("Start editing!");
	}

	public void printConverterQuestion() {
		System.out.println("Would you like to convert your file? Enter 'Y' for yes and 'N' for no.");
	}
	/**
	 * @param decision User input for type of file wanted.
	 * @return whether or not the answer was valid.
	 */
	public boolean fileTypeChoice(String decision) {
		if (decision.equals(".txt")) {
			
			return true;
		}
		else if (decision.equals(".cmp")) {
			return true;
		}
		else {
			System.out.println("Try again!");
			return false;
		}
		
	}
}
