
/**
 * @author Tian Low ttl2132
 * Step 1 & 2 Files
 * Files Names			.txt size (bytes)		.cmp size (bytes)
 * Empty File(untitled)			0						2
 * CopyPaste					17						27
 * BigCopyPaste					84						49
 * The trials run files are provided in a folder in the zip file. 
 * This class allows the user to use a simple text editor. The user first enters the file type.
 * This allows the Converter to return some kind of FileType (a .txt or .cmp file).
 * The user then enters a command, possibly followed by a String (a filename, words, or a word),
 * which then allows the KeyChoice class to execute the commands.
 * After the user saves the file, the user has the option to convert it into a different file type.
 */
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		char command = '0';
		boolean isValidAnswer = false;
		ConsolePrinter printer = new ConsolePrinter();
		Scanner myScanner = new Scanner(System.in);
		Converter myConverter = new Converter();
		String input = "";
		String fileType = "";
		printer.printTypeQuestion();
		//while loop keeps asking user for input until valid answer is given.
		while (isValidAnswer == false) {
			fileType = myScanner.next();
			isValidAnswer = printer.fileTypeChoice(fileType);
		}
		FileType file = Converter.getGeneralFile(fileType);
		KeyChoice myKeyChoice = new KeyChoice(file);
		printer.printStart();
		myScanner.nextLine();
		//while loop allows user to keep using text editor
		while (true) {
			printer.printArrow();
			input = myScanner.nextLine();
			command = input.charAt(0);
			if (input.length() > 2)
				input = input.substring(2);
			else
				input = "";
			myKeyChoice.key(command, input);
			if (command == 's')
				break;
		}
		printer.printConverterQuestion();
		isValidAnswer = false;
		//while loop keeps asking user for input until valid answer is given.
		while (isValidAnswer == false) {
			input = myScanner.next();
			isValidAnswer = myConverter.ConverterChoice(input, file);
		}

	}
}
