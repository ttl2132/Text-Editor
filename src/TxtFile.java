
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * @author Tian Low ttl2132
 * This class creates or gets an existing .txt file and manipulates its content, 
 * including inserting, deleting, or changing lines, as well as printing its contents.
 * It executes the methods called by KeyChoice for a .txt file. 
 */
public class TxtFile extends FileType {
	private File file;
	private ArrayList<String> fileLines;
	private int lineNumber;
	private String fileName;
	private int autoFileNumber;
	private BufferedReader bReader = null;
	private FileReader fReader = null;

	public TxtFile() {
		fileName = "";
		lineNumber = 0;
		fileLines = new ArrayList<String>();
	}

	/**
	 * @param fileN The name of the file the user inputs 
	 * This method gets the file from the directory if it exists. 
	 * The only thing I had to change in this method was the String ".txt" to ".cmp" 
	 * and add the words to the Dictionary if a file already exists.
	 */
	public void getFile(String fileN) {
		file = new File(System.getProperty("user.dir"));
		String origLine;
		if (fileN.isEmpty()) {
			fileName = "untitled" + ".txt";
			autoFileNumber++;
		} else
			fileName = fileN + ".txt";
		try {
			fReader = new FileReader(fileName);
			bReader = new BufferedReader(fReader);
			while ((origLine = bReader.readLine()) != null) {
				insertLine(origLine);
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

	/**
	 * @return temp A String that encompasses the entire file
	 */
	public String getFullFile() {
		String temp = "";
		for (String each : fileLines) {
			temp += each;
		}
		return temp;
	}

	public void printFile() {
		System.out.println(getFullFile());
	}

	/**
	 * @param A line that must be inserted into the file. 
	 * This method differs from the txt method, because the words from the input have to
	 * be entered into the dictionary and encoded into the line that will actually be inserted.
	 */
	public void insertLine(String line) {
		String message;
		message = line + "\r\n";
		fileLines.add(message);
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
			fWriter = new FileWriter(fileName, false);
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(getFullFile());
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
		if (lineNumber == 0)
			goToTopLine();
		System.out.println(fileLines.get(lineNumber - 1));
	}

	public void goToTopLine() {
		lineNumber = 1;
	}

	public void goDownOneLine() {
		if (lineNumber < fileLines.size())
			lineNumber++;
	}

	public void deleteLine() {
		if (fileLines.size() == 0) {
			System.out.println("No line! Cannot delete.");
		} else if (lineNumber == fileLines.size()) {
			fileLines.remove(lineNumber - 1);
			lineNumber--;
		} else
			fileLines.remove(lineNumber - 1);
	}

	/**
	 * @param newLine
	 */
	public void replaceLine(String newLine) {
		newLine = newLine + "\r\n";
		fileLines.set(lineNumber - 1, newLine);
	}

	public void setFile() {
		writeToFile();
		System.out.println(fileName + " written.");
	}

	/**
	 * @return name A String that contains the name without the file extension.
	 */
	public String getName() {
		// The .txt suffix is removed.
		String name = fileName.substring(0, fileName.length() - 4);
		return name;
	}

	public void setName(String myName) {
		fileName = myName;
	}

	/**
	 * @param indexWord A combination of both the index in the line and the new word 
	 * 					to be inserted, separated by a comma.
	 * To remove a word, simply enter one space into the console after the comma.
	 */
	public void changeWord(String indexWord) {
		// To remove a word, simply enter one space into the console after the
		// comma.
		String[] temp = indexWord.split(",");
		int index = Integer.valueOf(temp[0]);
		String word = temp[1];
		if (lineNumber == 0)
			temp = fileLines.get(lineNumber).split(" ");
		else
			temp = fileLines.get(lineNumber - 1).split(" ");
		temp[index] = word;
		String newLine = "";
		for (String each : temp) {
			newLine += each + " ";
		}
		newLine = newLine.trim();
		replaceLine(newLine);
	}
}
