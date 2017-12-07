import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Tian Low ttl2132
 * This abstract class allows the code to be organized in a logical manner
 * and for a factory method to be used.
 */
public abstract class FileType {
	public String getFullCode() {
		return null;
	}
	public void getFile(String fileN) {
	}
	public Dictionary getDictionary () {
		return null;
	}
	public void printFile() {
	}

	public void insertLine(String fileN) {
	}

	public void writeToFile() {
	}

	public void printCurrentLine() {
	}

	public void goToTopLine() {
	}

	public void goDownOneLine() {
	}

	public void deleteLine() {
	}

	public void replaceLine(String newLine) {
	}

	public void setFile() {
	}
	
	public String getName () {
		return null;
	}
	public void setName (String myName) {
	}
	public void changeWord (String wordAndIndex) {
	}
}
