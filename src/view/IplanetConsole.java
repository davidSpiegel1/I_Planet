package view;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Controller;
import model.*;
import model.Character;

/**
 * 
 * @author davidspiegel
 * 
 * Description: Our I Planet program for console
 *
 */
public class IplanetConsole {

	public static void main(String[] args) {
		// Displaying the welcome
		displayWelcome();
		
		// Playing the game
		playGame();
		
		// Ending goodbye
		displayGoodBye();
		
		
	}
	
	// Displaying the welcome
	public static void displayWelcome() {
		System.out.println("Welcome to I Planet!!");
		
	}
	
	// Displaying game
	public static void playGame() {
		String userName = getString("Please enter your name");
		Character character = new Character(0,0,userName);
		Controller c = new Controller(character);
		String userChoice = "";
		// While game is not over we will be playing this game
		while (!c.isGameOver()) {
			userChoice = getString("");
			interpreteCommand(userChoice,c);
			displayMap(c.getEnvMap());
			
		}
		
	}

	// Need a module to interprete block
	public static void interpreteCommand(String command, Controller c) {

		if (command.equalsIgnoreCase("A") || command.equalsIgnoreCase("D") || command.equalsIgnoreCase("W")
				|| command.equalsIgnoreCase("S")) {
			c.moveCharacter(command);
		}
		if (command.equalsIgnoreCase("X")) {
			ArrayList<Block> arr = c.getInventory();
			displayInventory(arr);
		}
		if (command.equalsIgnoreCase("Q")){
			
		}

	}
				
	// Needing a module to display inventory
	public static void displayInventory(ArrayList<Block> arr) {
		System.out.println("*** Current Inventory ***");
		for (int i = 0; i<= arr.size()-1;i++) {
			System.out.println(arr.get(i));
		}
		
		String answer = getString("close?");
		
	}
	
	// displaying map of world!!
	public static void displayMap(ArrayList<ArrayList>map) {
		for (int i = 0; i<= map.size()-1;i++) {
			for (int j = 0; j<= map.get(i).size()-1;j++) {
				
				System.out.print(((Block) map.get(i).get(j)).getKey());
			}
			System.out.println("");
			}
		
	}
	
	// Creating a get String function
	public static String getString(String msg) {
		System.out.println(msg);
		
		Scanner sn = new Scanner(System.in);
		String userChoice = sn.nextLine();
		
		return userChoice;
	}
	
	// Creating a display goodbye
	public static void displayGoodBye() {
		System.out.println("Goodbye!");
	}

}
