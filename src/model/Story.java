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
		findKey(row,col);
		System.err.println(row);
		System.err.println(col);
	
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
		if (row == 0 && col == 11) {
			keys = "l1";
		}
		if (row == 0 && col == 3) {
			keys = "l2";
		}
	}
	public void interact() {
		if (keys.equals("l1")) {
			if (currentLevel == 2) {
				discription = "Hello. My name is Jack ColterStevens. Welcome to I-Planet!";
			}
			else if (currentLevel == 3) {
				discription = "I Will be helping you on your missions. Please, ask me anything.";
			}
			else if (currentLevel == 4) {
				discription = "Good Luck.";
				currentLevel = 0;
			}
			
		}
		else if (keys.equals("l2")) {
			if (currentLevel == 1) {
				discription = "Hello. My name is Jack ColterStevens. Welcome to I-Planet!";
			}
		}
		else {
			discription = "Who are you?";
		}
		currentLevel++;
		
		
		
	}
	
	

}
