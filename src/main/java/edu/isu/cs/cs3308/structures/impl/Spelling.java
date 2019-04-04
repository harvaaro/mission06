package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.SpellChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Spellchecker implementation that compares words in a sentence to a lexicon of words
 * and will output a list of words that have been mispelled
 *
 * @author Aaron Harvey
 */
public class Spelling implements SpellChecker {

	private List<String> suggestList = new ArrayList<>();
	private SetHash<String> lexicon = new SetHash<>();
//	private char[] changes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'".toCharArray();
	private char[] changes = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
		'p','q','r','s','t','u','v','w','x','y','z',0x0027};

	public Spelling() {
		saveLexcion(true);
	}

	/**
	 * Checks the spelling of the given string.
	 *
	 * @param s The string to check the spelling of
	 * @return A list of alternatives, if the list is length 1
	 * containing the same value as s, then the provided word
	 * was correctly spelled. Else it was not.
	 */
	@Override
	public List<String> check(String s) {
		s = s.toLowerCase();
		suggestList.clear();
		suggestList.add(s);

		if (lexicon.contains(s)) return suggestList;

		processWord(s);
//		if (suggestList.size() == 1) {
//			suggestList = new ArrayList<>();
//			saveLexcion(true);
//			s = s.toLowerCase();
//			processWord(s);
//		}
		removeDuplicates();

		if (suggestList.size() == 1) suggestList.add("No suggestions");
		return suggestList;
	}

	/**
	 * Saves the dictionary of word in its current form or lowercase
	 *
	 * @param asLower True to make all words lowercase, False for original casing
	 */
	private void saveLexcion(boolean asLower) {
		try {
			FileReader file = new FileReader("data/en-US.dic");
			BufferedReader buffer = new BufferedReader(file);
			for (String line; (line = buffer.readLine()) != null; ) {
				if (asLower) {
					line = line.toLowerCase();
				}
				lexicon.add(line);
			}
			buffer.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Remove duplicate suggestions from the processing list
	 */
	private void removeDuplicates() {
		for (int i = 0; i < suggestList.size(); i++) {
			for (int c = i+1; c < suggestList.size(); c++) {
				if (suggestList.get(i).equals(suggestList.get(c))) {
					suggestList.remove(c);
				}
			}
		}
	}

	/**
	 * See if the lexicon has the word
	 *
	 * @param word the word to search for
	 */
	private void compareToLexicon(String word) {
		if (lexicon.contains(word)) {
			suggestList.add(word);
		}
	}

	/**
	 * Process the word in four ways by checking:
	 * Swapping adjacent characters
	 * Deleting a character
	 * Inserting a character
	 * Replacing a character
	 * Results will be added to the suggestion list
	 *
	 * @param word The word to check against the lexicon
	 */
	private void processWord(String word) {
		for (int i = 0; i < word.length() - 1; i++) {
			StringBuilder verify = new StringBuilder(word);

			verify.insert(i, word.charAt(i + 1));
			verify.deleteCharAt(i + 2);
			compareToLexicon(verify.toString());

			verify = new StringBuilder(word);
			verify.deleteCharAt(i);
			compareToLexicon(verify.toString());

			for (char c : changes) {
				verify = new StringBuilder(word);
				verify.insert(i, c);
				compareToLexicon(verify.toString());

				verify = new StringBuilder(word);
				verify.setCharAt(i, c);
				compareToLexicon(verify.toString());
			}
		}
	}
}
