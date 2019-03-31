package edu.isu.cs.cs3308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Driver to test the spell checker
 *
 * @author Aaron Harvey
 */
public class Driver {
	private static String getText() {
		String sentence = "";

		System.out.println("Enter a sentence to check:");

		// https://www.techrepublic.com/article/handling-terminal-input-and-output-in-java/
		try {
			BufferedReader inputText = new BufferedReader(new InputStreamReader(System.in));
			sentence = inputText.readLine();
		} catch (IOException ex) {
			System.err.println(ex);
		}

		return sentence;
	}

	public static void main(String[] args) {
		String sentence = getText();
		System.out.println(sentence);
	}
}
