import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Tian Low ttl2132
 * This class creates or gets an existing .cmp file and manipulates its content, 
 * including inserting, deleting, or changing lines, as well as printing its contents.
 * This class executes the methods called by KeyChoice for a .cmp file. 
 */

public class CmpFile extends FileType {
	// First, I changed the all of the variable names from "file" to "code,"
	// because the name makes more sense.
	private File file;
	private ArrayList<String> codeLines;
	private int lineNumber;
	private String codeName;
	private int autoFileNumber;
	private Dictionary myDictionary;
	private BufferedReader bReader = null;
	private FileReader fReader = null;

	public CmpFile() {
		codeName = "";
		lineNumber = 0;
		codeLines = new ArrayList<String>();
		myDictionary = new Dictionary();
	}

	/**
	 * @return temp A String that encompasses the entire file
	 */
	public String getFullCode() {

		String temp = "";
		for (String each : codeLines) {
			temp += each + "\r\n";
		}
		return temp;
	}

	/**
	 * @param fileN
	 *            The name of the file the user inputs This method gets the file
	 *            from the directory if it exists. The only thing I had to
	 *            change in this method was the String ".txt" to ".cmp" and add
	 *            the words to the Dictionary if a file already exists.
	 */
	public void getFile(String fileN) {
		file = new File(System.getProperty("user.dir"));
		String origLine;
		if (fileN.isEmpty())
			codeName = "untitled" + ".cmp";
		else
			codeName = fileN + ".cmp";
		try {
			fReader = new FileReader(codeName);
			bReader = new BufferedReader(fReader);
			String origDict = "";
			origDict = bReader.readLine();
			myDictionary.addWordsToDict(origDict);
			while ((origLine = bReader.readLine()) != null) {
				codeLines.add(origLine);
			}
		} catch (FileNotFoundException error) {

		} catch (IOException error) {
			error.printStackTrace();
		} finally {
			try {
				if (bReader != null)
					bReader.close();
				if (fReader != null)
					fReader.close();
			} catch (IOException error) {
				error.printStackTrace();
			}
		}

	}

	public void printFile() {
		// This method differs from the txt method, because the entire file must
		// be decoded first before being printed.
		String decodedFile = myDictionary.decodeFile(codeLines);
		System.out.println(decodedFile);
	}

	/**
	 * @param line A line that must be inserted into the file. 
	 * This method differs from the txt method, because the words from the input
	 * have to be entered into the dictionary and encoded into the line that 
	 * will actually be inserted.
	 */

	public void insertLine(String line) {

		String message;
		message = line.replace("\n", "");
		myDictionary.addWordsToDict(message);
		message = myDictionary.encodeLine(message);
		codeLines.add(message);
		lineNumber++;
	}

	/**
	 * Writes file to actual file
	 */
	public void writeToFile() {
		// Some of the code in this method was based off of code in the
		// following site:
		// http://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
		BufferedWriter bWriter = null;
		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter(codeName, false);
			bWriter = new BufferedWriter(fWriter);
			if (myDictionary.getDictionaryWords().isEmpty())
				bWriter.write(myDictionary.getDictionaryWords() + "\r\n" + getFullCode());
			else
				bWriter.write(myDictionary.getDictionaryWords() + "\r\n" + getFullCode());
		} catch (IOException error) {
			error.printStackTrace();
		} finally {
			try {
				if (bWriter != null)
					bWriter.close();
				if (fWriter != null)
					fWriter.close();
			} catch (IOException error) {
				error.printStackTrace();
			}
		}
	}

	public void printCurrentLine() {
		// This method differs from the txt method, because the code line must
		// be decoded first.
		if (lineNumber == 0) {
			goToTopLine();
		}
		String currentCodeLine = myDictionary.decodeLine(codeLines.get(lineNumber - 1));
		System.out.println(currentCodeLine);
	}

	public void goToTopLine() {
		lineNumber = 1;
	}

	public void goDownOneLine() {
		if (lineNumber < codeLines.size())
			lineNumber++;
	}

	public void deleteLine() {
		if (codeLines.size() == 0) {
			System.out.println("No line! Cannot delete.");
		} else if (lineNumber == codeLines.size()) {
			codeLines.remove(lineNumber - 1);
			lineNumber--;
		} else
			codeLines.remove(lineNumber - 1);
	}

	/**
	 * @param newLine
	 */
	public void replaceLine(String newLine) {
		newLine = newLine + "\r\n";
		myDictionary.addWordsToDict(newLine);
		if (lineNumber == 0) {
			codeLines.set(lineNumber, myDictionary.encodeLine(newLine));
			lineNumber++;
		} else
			codeLines.set(lineNumber - 1, myDictionary.encodeLine(newLine));
	}

	public void setFile() {
		writeToFile();
		System.out.println(codeName + " written.");
	}

	/**
	 * @return name
	 */

	public String getName() {
		// The .cmp suffix is removed.
		String name = codeName.substring(0, codeName.length() - 4);
		return name;
	}

	/**
	 * @return myDictionary
	 */
	public Dictionary getDictionary() {
		return myDictionary;
	}

	/**
	 * @param myName
	 */
	public void setName(String myName) {
		codeName = myName;
	}

	/**
	 * @param indexWord A combination of both the index in the line and the new word to be inserted, separated by a comma.
	 * To remove a word, simply enter one space into the console after the comma.
	 */
	public void changeWord(String indexWord) {

		String[] temp = indexWord.split(",");
		int index = Integer.valueOf(temp[0]);
		String word = temp[1];
		myDictionary.addWordToDict(word);
		word = myDictionary.encodeLine(word);
		// At this point, word is turned into the code number.
		if (lineNumber == 0)
			temp = codeLines.get(lineNumber).split(" ");
		else
			temp = codeLines.get(lineNumber - 1).split(" ");
		temp[index] = word;
		String newLine = "";
		for (String each : temp) {
			newLine += each + " ";
		}
		newLine = newLine.trim();
		newLine = myDictionary.decodeLine(newLine);
		replaceLine(newLine);
	}
}
