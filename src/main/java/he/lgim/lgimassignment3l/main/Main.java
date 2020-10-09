package he.lgim.lgimassignment3l.main;

import he.lgim.lgimassignment3l.controller.MainMenuController;

public class Main {
	public static void main(String[] args) {
    	MainMenuController menuController = new MainMenuController();
    	// Main program loop
    	while(true) {
    		menuController.mainMenu();
    	}
	}
}
