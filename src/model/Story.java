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
	public Story(String key) {
		super(key);
		currentLevel = 1;
		discription = "";
	}
	// How to change the level
	public void changeLevel() {
		currentLevel++;
	}
	
	public void setCurrentStoryBeat(int level) {
		currentLevel = level;
	}
	
	// A method to allow for the description
	@Override
	public String getDescription() {
		discription = findDescription(currentLevel);
		return this.discription;
	}
	
	// A helper function to find the description we need
	private String findDescription(int cl) {
		if (cl == 1) {
			return "You are at your house. We must go to the desert!";
		}
		if (cl == 2) {
			return "Whats this? A church?";
		}
		else {
			return "";
		}
		
	}

}
