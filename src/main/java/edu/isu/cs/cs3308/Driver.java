package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.Spelling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Driver to test the spell checker
 *
 * @author Aaron Harvey
 */
public class Driver {

	/**
	 * Runs the spell checker program by getting the input string
	 * Comparing it to the lexicon and outputting what was misspelled
	 *
	 * @param args arguments
	 */
	public static void main(String[] args) {
		while(true) {
			checkSpelling(getText());
		}
	}

	/**
	 * Will ask for a sentence to be entered to be used in the spell checker
	 * Input research from here:
	 * https://www.techrepublic.com/article/handling-terminal-input-and-output-in-java/
	 *
	 * @return The string array of words
	 */
	private static String[] getText() {
		String sentence = "";
		System.out.println("\r\nEnter a sentence to check:");

		try {
			BufferedReader inputText = new BufferedReader(new InputStreamReader(System.in));
			sentence = inputText.readLine();
		} catch (IOException ex) {
			System.err.println(ex);
		}

		return sentence.replaceAll("[^\\w\\'\\ ]","").split("\\s+");
	}

	/**
	 * Checks the words against the lexicon and prompts any misspellings
	 *
	 * @param words Word array to check
	 */
	private static void checkSpelling(String[] words) {
		Spelling spell = new Spelling();
		boolean noMistakes = true;
		int numMistakes = 1;

		for (String word : words) {
			List<String> results = spell.check(word);

			if (results.size() > 1) {
				StringBuilder str = new StringBuilder();
				for (int i = 1; i < results.size(); i++) {
					if (i < 6) str.append(results.get(i) + ", ");
				}
				String options = str.substring(0, str.length() - 2);

				if (numMistakes == 1) {
					System.out.println("\r\nMisspelled Words:");
				}
				System.out.printf("%3d. %s: [%s]\r\n", numMistakes, results.get(0), options);

				noMistakes = false;
				numMistakes++;
			}
		}

		if (noMistakes) System.out.println("\r\nNo misspellings!");
	}
}
