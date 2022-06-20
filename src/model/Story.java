package model;

/**
 * 
 * @author davidspiegel
 * 
 * Description: This class will be used to tell the story of the game
 *
 */
public class Story extends Block{

	// A constructor to set the level
	int currentLevel;
	String discription;
	String keys;
	public Story(int row, int col) {
		super("s");
		currentLevel = 1;
		discription = "";
		keys = "";
		System.err.println(row+"AND"+col);
		findKey(row,col);
		
	
	}
	
	
	public void setCurrentStoryBeat(int level) {
		currentLevel = level;
	}
	
	// A method to allow for the description
	@Override
	public String getDescription() {
		return this.discription;
	}
	public void findKey(int row, int col) {
		if (row == 15 && col == 7) {
			keys = "l1";
		}
		
		
		if (row == 15 && col == 6) {
			keys = "l3";
		}
		if (row == 8 && col == 15) {
			keys = "l2";
		}
		if (row == 16 && col == 7) {
			keys = "l4";
		}
	}
	public void interact() {
		
		if (keys.equals("l1")) {
			if (currentLevel == 2) {
				discription = "Hello. My name is Jack ColterStevens. Welcome to I-Planet!";
			}
			else if (currentLevel == 4) {
				discription = "I Will be helping you on your missions.";
			}
			else if (currentLevel == 6) {
				discription = "Currently, you are near a military base...";
				
			}
			else if (currentLevel == 8) {
				discription = "Well? What are you waiting for?";
				currentLevel = 1;
			}
			
		}
		else if (keys.equals("l2")) {
			if (currentLevel == 2) {
				discription = "So, you made it to the outside of the compund. ";
			}
			else if (currentLevel == 4) {
				discription = "We have been hearing reports of the enemy...";
			}
			else if (currentLevel == 6) {
				discription = "There is a small town outside of I-Istanbul, the enemy is holding hostages there...";
			}
			
			else if (currentLevel == 8) {
				discription = "We have to save a representive of the Parliament. The chancellor...";
			}
		}
		
		else if (keys.equals("l3")) {
			if (currentLevel == 2) {
				discription = "Welcome to the forrests of I-Istanbul. We are looking the hostages.";
			}
			else if (currentLevel == 4) {
				discription = "Remember, we are HERE for the chancellor.";
			}
			else if (currentLevel == 6) {
				discription = "Do what you can for the rest.";
			}
		}
		else if (keys.equals("l4")) {
			if (currentLevel == 2) {
				discription = "Okay, so what?.";
			}
			else if (currentLevel == 4) {
				discription = "Remember, we are HERE for the chancellor. Keep an eye out.";
			}
		}
		
		else {
			discription = "Who are you?";
		}
		System.out.println("Current Level"+currentLevel);
		currentLevel++;
		
		
		
	}
	
	

}
