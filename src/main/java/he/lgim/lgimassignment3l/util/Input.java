package he.lgim.lgimassignment3l.util;

import java.util.Scanner;

public class Input {
	/* -------------- Attributes --------------- */
	private Scanner in;

	/* -------------- Constructor -------------- */
	public Input() {
		in = new Scanner(System.in);
	}

	/* ---------------- Methods ---------------- */
	public String startMenu(Boolean inputValid) {
		if (inputValid) {
			System.out.println("Please select one of the following options:");
		} 
		else {
			System.out.println("\nPlease select a valid menu option:");
		}
		System.out.println("1) Load a text file");
		System.out.println("2) Exit");
		return in.nextLine().trim();
	}

	public String getFilePath() {
		System.out.println("\nPlease enter the path of the file you'd like to load (e.g. C:\\Users\\henry\\Documents):");
		return in.nextLine().trim();
	}

	public String getFileToLoad() {
		System.out.println("\nPlease enter the name of the file you'd like to load (e.g. textfile.txt):");
		return in.nextLine().trim();
	}
}