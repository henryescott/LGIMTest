package he.lgim.lgimassignment3l.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextFileAnalyser {
	/* -------------- Attributes --------------- */
	private File file;
	private List<String> lines;
	private List<String> words;
	private Map<Character, Integer> letterCounts;

	/* -------------- Constructor -------------- */
	public TextFileAnalyser(String filepath, String filename) {
		// Make sure the file is .txt format
		if (filename.matches(".+[.]txt")) {
			file = new File(filepath, filename); 
			lines = new ArrayList<String>();
			words = new ArrayList<String>();
			letterCounts = new HashMap<Character, Integer>();
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/* ---------------- Methods ---------------- */
	// whitespace delimited word count
	public int getWordCount() {
		return words.size();
	}

	// line count
	public int getLineCount() {
		return lines.size();
	}

	// average number of letters per word (to one decimal place)
	// Note: rounding to 1dp is done in the menu controller in the printStats method
	public BigDecimal getAvgLetters() {
		int totalLetters = letterCounts
				.values()
				.stream()
				.mapToInt(count -> count)
				.sum();
		BigDecimal avgLetters = new BigDecimal((float)totalLetters / getWordCount());
		return avgLetters.setScale(1, BigDecimal.ROUND_HALF_EVEN); 
	}

	// most common letter
	public List<Character> getMostCommonLetter() {
		// Returns a list because there could be multiple with same # of occurrences
		int maxValue = Collections.max(letterCounts.values());
		List<Character> mostCommonLetters = letterCounts
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue() == maxValue)
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
		return mostCommonLetters;
	}

	// Methods
	public void calculateStatistics() {
		analyseLetters();
		// Any future added analysis methods would also be called here i.e
		// any pre-processing required to calculate the stats 
	}

	public void readFile(String delimiter) throws FileNotFoundException {
		Scanner inFile = new Scanner(file); 
		while(inFile.hasNextLine()) { 
			String line = inFile.nextLine();
			lines.add(line);
			words.addAll(Arrays.asList(line.split(delimiter)));
		}
		inFile.close();
	}

	private void analyseLetters() {
		for(String word : words) {
			// For the purpose of the stats requested, we only care about letters
			for(int i = 0; i < word.replaceAll("[^a-zA-Z]", "").length(); i++) {
				// Convert to lower case - assumption is we don't distinguish between upper and lower case
				char letter = Character.toLowerCase(word.charAt(i));
				letterCounts.put(letter, letterCounts.getOrDefault(letter, 0) + 1);
			}
		}
	}

	/*
	 *  Can add further methods to calculate the lines/words read from the text file as required.
	 *  Because they're stored in ArrayLists we won't need to re-read the text file for this -
	 *  it'll be there ready to use in further methods. 
	 */
}
