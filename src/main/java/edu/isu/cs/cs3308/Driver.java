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
	 * Will ask for a sentence to be entered to be used in the spell checker
	 * Input research from here:
	 * https://www.techrepublic.com/article/handling-terminal-input-and-output-in-java/
	 *
	 * @return The string that was entered
	 */
	private static String getText() {
		String sentence = "";
		System.out.println("Enter a sentence to check:");

		try {
			BufferedReader inputText = new BufferedReader(new InputStreamReader(System.in));
			sentence = inputText.readLine();
		} catch (IOException ex) {
			System.err.println(ex);
		}

		return sentence;
	}

	/**
	 * Runs the spell checker program by getting the input string
	 * Comparing it to the lexicon and outputting what was misspelled
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String sentence = getText();
		System.out.println(sentence);
		Spelling spell = new Spelling();
//		spell.check(sentence);
	}
}
