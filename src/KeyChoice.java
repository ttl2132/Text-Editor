
/**
 * @author Tian Low ttl2132
 * This class determines how the file will be manipulated based on user input.
 */
public class KeyChoice {
	private FileType myFileType;
/**
 * @param myFT
 */
	public KeyChoice (FileType myFT) {
		myFileType = myFT;
	}
/**
 * @param input A character that contains the command for what the editor does
 * @param myLine A String that may be manipulated by the editor
 * This method edits the file based on the input of the user.
 */
	public void key(char input, String myLine) {
		switch (input) {
		case 'g':
			myFileType.getFile(myLine);
			break;
		case 'i':
			myFileType.insertLine(myLine);
			break;
		case 'p':
			myFileType.printFile();
			break;
		case 'c':
			myFileType.printCurrentLine();
			break;
		case 't':
			myFileType.goToTopLine();
			break;
		case 'v':
			myFileType.goDownOneLine();
			break;
		case 'd':
			myFileType.deleteLine();
			break;
		case 'r':
			myFileType.replaceLine(myLine);
			break;
		case 's':
			myFileType.setFile();
			break;
		case 'w':
			myFileType.changeWord(myLine);
		}
	}
}
