import java.util.ArrayList;

/**
 * @author Tian Low ttl2132
 * This class converts between .txt and .cmp files.
 */
public class Converter {
	private String [] itemLines;
	private Dictionary myDictionary;
	private String fileName;
	private String codeString="";
	public Converter () {
		itemLines = null;
		myDictionary = new Dictionary();
		fileName = "";
	}
	/**
	 * @param decision User input for if a conversion is needed. 
	 * @param myFT
	 * @return whether or not the answer was valid.
	 */
	public boolean ConverterChoice(String decision, FileType myFT) {
		if (decision.equals("Y")) {
			convertFile(myFT);
			return true;
		}
		else if (decision.equals("N")) {
			return true;
		}
		else {
			System.out.println("Try again!");
			return false;
		}
		
	}
	/**
	 * @param myFileType
	 * @return either a TxtFile or CmpFile, depending on what the original file was.
	 */
	public static FileType getGeneralFile(String myFileType) {
		if (myFileType.equals(".txt")) {
			return new TxtFile();
		} else {
			return new CmpFile();
		}
	}
	/**
	 * @param myFT
	 */
	public void convertFile(FileType myFT) {
		fileName = myFT.getName();
		if (myFT instanceof TxtFile) {
			CmpFile newFile = new CmpFile();
			newFile.setName(fileName);
			newFile.getFile(fileName);
			transferDictAndLines(myFT);
			insertOrigLines(newFile);
			newFile.setFile();
		} else {
			TxtFile newFile = new TxtFile();
			newFile.setName(fileName);
			newFile.getFile(fileName);
			transferDictAndLines(myFT);
			insertOrigLines(newFile);
			newFile.setFile();
		}
	}
	/**
	 * @param myFT
	 */
	public void transferDictAndLines (FileType myFT) {
		if (myFT instanceof TxtFile) {
			myDictionary.addWordsToDict(((TxtFile) myFT).getFullFile());
			itemLines = ((TxtFile) myFT).getFullFile().split("\r\n");
		} else {
			myDictionary = myFT.getDictionary();
			itemLines = ((CmpFile)myFT).getFullCode().split("\r\n");
			ArrayList<String> tempLineHolder = new ArrayList<String>();
			tempLineHolder = arrayToAL(itemLines);
			codeString = myDictionary.decodeFile(tempLineHolder);
			itemLines = codeString.split("\r\n");
		}
	}
	/**
	 * @param lines
	 * @return wordAL An ArrayList that contains the same information as the array.
	 */
	public ArrayList<String> arrayToAL(String[] lines) {
		ArrayList<String> wordAL = new ArrayList<String> ();
		for (String line: lines) {
			wordAL.add(line);
		}
		return wordAL;
	}
	/**
	 * @param myNewFile
	 */
 	public void insertOrigLines (FileType myNewFile) {

		for (String each: itemLines) {
			myNewFile.insertLine(each);
		}
	}
}
