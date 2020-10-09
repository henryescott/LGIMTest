package he.lgim.lgimassignment3l.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import he.lgim.lgimassignment3l.classes.TextFileAnalyser;
import he.lgim.lgimassignment3l.util.Input;

public class MainMenuController {
	/* -------------- Attributes --------------- */
	private Input in = new Input();
	private Boolean inputValid;
	private TextFileAnalyser textFile;

	/* -------------- Constructor -------------- */
	public MainMenuController() {
		setInputValid(true);
	}
	
	/* ---------------- Methods ---------------- */
	private void setInputValid(Boolean inputValid) {
		this.inputValid = inputValid;
	}

	// Main menu
	public void mainMenu() {
		switch (in.startMenu(inputValid)) {
		// Read file
		case "1":
			loadFile(in.getFilePath(), in.getFileToLoad());
			break;
		// Exit
		case "2":
			System.exit(0);
			break;
		// Handle invalid input	
		default:
			setInputValid(false);
			break; 
		}
	}

	private void loadFile(String filepath, String filename) {
		try {
			textFile = new TextFileAnalyser(filepath, filename);
			// For the moment we can just hard code the delimiter, but as an extension we may want to ask the user
			textFile.readFile("\\s+");
			System.out.println("\nFile loaded successfully: " + filename);
			textFile.calculateStatistics();
			printStats(textFile);
		}
		// The file isn't .txt format (thrown by the TextFile constructor)
		catch(IllegalArgumentException e) {
			System.out.println("\nThe file must be .txt format.\n");
		}
		// The file is unable to be found
		catch (FileNotFoundException e){
			System.out.println("\nUnable to locate file: " + filename + "\n");
		}
		// Any other issues that may occur
		catch(Exception e) {
			System.out.println("\nProblem reading file: " + filename + "\n");
		}
		finally {
			setInputValid(true);
		}
	}
	
	private void printStats(TextFileAnalyser textFile) {
		System.out.println("\nStatistics");
		System.out.println("------------------------------------");
		// Word count
		System.out.println("Word count: " + textFile.getWordCount());
		// Line count
		System.out.println("Line count: " + textFile.getLineCount());
		// Avg letters
		System.out.println("Average letters per word: " + textFile.getAvgLetters());
		// Most common char
		List<Character> mostCommonLetters = textFile.getMostCommonLetter(); 
		System.out.print(
				"Most common letter(s): " + 
				mostCommonLetters
				.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(", ")) + "\n\n"
		);
	}
}
