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
		
		
		if (row == 8 && col == 15) {
			keys = "l2";
		}
		if (row == 15 && col == 6) {
			keys = "l3";
		}
		if (row == 14 && col == 10) {
			keys = "l4";
		}
		if (row == 15 && col == 9) {
			keys = "l5";
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
				discription = "Darnit solder! On your feet!";
			}
			else if (currentLevel == 4) {
				discription = "The chancellor should be through here!";
			}
			else if (currentLevel == 6) {
				discription = "A child? No, we are getting the chancellor!";
			}
		}
		else if (keys.equals("l5")) {
			if (currentLevel == 2) {
				discription = "Fine! We will take both. We gotta get out of here!";
			}
			else if (currentLevel == 4) {
				discription = "I'm glade you save the child. Let's move!";
			}
			
		}
		else if (keys.equals("l6")) {
			if (currentLevel == 2) {
				discription = "Leave me! I can find my own way!";
			}
			else if (currentLevel == 4) {
				discription = "Your a good fighter. Maybe a better wolf than I..";
			}
			else if (currentLevel == 8) {
				discription = "Now go! I'll find you guys!";
			}
			
		}
		else if (keys.equals("l7")) {
			if (currentLevel == 2) {
				discription = "Name's John. Soldier, where is jack?";
			}
			else if (currentLevel == 4) {
				discription = "Where is the WOLF? ";
			}
			else if (currentLevel == 8) {
				discription = "I'll never leave him behind he is my friend!";
			}
			
		}
		
		else {
			discription = "Who are you?";
		}
		System.out.println("Current Level"+currentLevel);
		currentLevel++;
		
		
		
	}
	
	

}
