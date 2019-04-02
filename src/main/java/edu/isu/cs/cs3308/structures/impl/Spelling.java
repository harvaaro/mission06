package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.SpellChecker;

import java.util.List;

/**
 * Spellchecker implementation that compares words in a sentence to a lexicon of words
 * and will output a list of words that have been mispelled
 *
 * @author Aaron Harvey
 */
public class Spelling implements SpellChecker {
	/**
	 * Checks the spelling of the given string.
	 *
	 * @param s The string to check the spelling of
	 * @return A list of alternatives, if the list is length 1 containing the same value as s, then the provided word was correctly spelled. Else it was not.
	 */
	@Override
	public List<String> check(String s) {
		return null;
	}
}
