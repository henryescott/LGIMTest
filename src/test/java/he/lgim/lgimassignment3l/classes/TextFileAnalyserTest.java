package he.lgim.lgimassignment3l.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TextFileAnalyserTest {
	TextFileAnalyser tfa;

	// Constructor Tests
	@Test
	public void testTextFileAnalyserFailure() {
		try {
			tfa = new TextFileAnalyser("\\src\\test\\resources\\", "image.png");
			fail("Didn't throw exception.");
		} 
		catch (IllegalArgumentException e) {
			// Test passed
		}
	}

	@Test
	public void testTextFileAnalyserSuccess() {
		try {
			tfa = new TextFileAnalyser("src\\test\\resources\\", "test.txt");
		} 
		catch (IllegalArgumentException e) {
			fail("Shouldn't have thrown exception: " + e);
		}
	}

	// Read File
	@Test
	public void testReadFileFailure() {
		try {
			tfa = new TextFileAnalyser("\\src\\test\\resources\\", "somefilethatdoesntexist.txt");
			tfa.readFile("\\s+");
			fail("Didn't throw exception.");
		} 
		catch (FileNotFoundException e) {
			// Test passed
		}
	}

	@Nested
	class StatsicticsTests {
		TextFileAnalyser tfa;

		@BeforeEach
		@Test
		public void testReadFileSuccess() {
			tfa = new TextFileAnalyser("src\\test\\resources\\", "test.txt");
			try {
				tfa.readFile("\\s+");
				tfa.calculateStatistics();
			} 
			catch (FileNotFoundException e) {
				fail("Shouldn't have thrown exception: " + e);
			}
		}

		// Word count
		@Test
		public void testGetWordCount() {
			assertEquals(tfa.getWordCount(), 10);
		}

		// Line count
		@Test
		public void testGetLineCount() {
			assertEquals(tfa.getLineCount(), 4);
		}

		// Avg letters
		@Test
		public void testGetAvgLetters() {
			// Letters: 49 / Words: 10
			assertEquals(tfa.getAvgLetters().toString(), "4.9");
		}

		// Most common letter
		@Test
		public void testGetMostCommonLetter() {
			List<Character> correctList = new ArrayList<Character>();
			correctList.add("e".charAt(0));
			assertEquals(tfa.getMostCommonLetter(), correctList);
		}
	}
}
