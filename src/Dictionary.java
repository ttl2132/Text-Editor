import java.util.ArrayList;

/**
 * @author Tian Low ttl2132
 * This class stores the meaning of "encoded", or compressed, words.
 * It also is able to translate between the Strings of numbers 
 * and the actual words inserted by the user.
 */
public class Dictionary {
	private ArrayList<String> dict;

	public Dictionary() {
		dict = new ArrayList<String>();
	}

	/**
	 * @param wordsFromLIne
	 */
	public void addWordsToDict(String wordsFromLine) {
		// This method adds a whole String of words into the dictionary.
		String[] separateWords = wordsFromLine.split(" ");
		for (String wordInLine : separateWords) {
			addWordToDict(wordInLine);
		}
	}

	/**
	 * @param wordInLine
	 */
	public void addWordToDict(String wordInLine) {
		// This method adds a single word to the dictionary if it's not already
		// there.
		if (!dict.contains(wordInLine))
			dict.add(wordInLine);
	}

	/**
	 * @param wordsFromLine
	 * @return codeHolder A String that contains the compressed line.
	 */
	public String encodeLine(String wordsFromLine) {
		// This method compresses the input String into the String of numbers.
		String[] separateWords = wordsFromLine.split(" ");
		String codeHolder = "";
		for (String wordInLine : separateWords) {
			codeHolder += (dict.indexOf(wordInLine) + " ");
		}
		return codeHolder;
	}

	/**
	 * @param codeFromLine
	 * @return wordHolder A String that contains the original line input by the
	 *         user.
	 */
	public String decodeLine(String codeFromLine) {
		// This method decompresses the String of numbers into the original
		// input String.
		String[] separateWords = codeFromLine.split(" ");
		String wordHolder = "";
		String currentWord;
		int index;
		for (String num : separateWords) {
			if (!num.isEmpty() && dict.size()>0) {
				index = Integer.parseInt(num);
				currentWord = dict.get(index);
				wordHolder += (currentWord + " ");
			}
		}
		return wordHolder;
	}

	/**
	 * @return allWords A String that contains all of the words in the
	 *         dictionary.
	 */
	public String getDictionaryWords() {
		String allWords = "";
		for (String each : dict) {
			allWords += each.trim() + " ";
		}
		return allWords;
	}

	/**
	 * @param myCodeLines An ArrayList that contains all of the lines of code
	 * @return lineHolder A String that contains all of the decoded lines.
	 * Interprets the numbers in the cmp file and translates them into
	 * words.
	 */
	public String decodeFile(ArrayList<String> myCodeLines) {
		String lineHolder = "";
		for (String code : myCodeLines) {
			lineHolder += decodeLine(code).trim() + "\r\n";
		}
		return lineHolder;
	}
}
