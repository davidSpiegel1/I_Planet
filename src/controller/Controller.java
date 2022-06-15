package controller;

import java.util.ArrayList;

import model.Environment;
import model.Person;
import model.Story;
import model.Block;
import model.Character;
import model.Enemy;

public class Controller {
	
	private int currentLevel;
	private int currentStoryBeat;
	private Environment env;
	private Character c;
	// Basically, we are making a constructor for our controller class that
	//allows for an instance of environment and current level
	public Controller(Character c) {	
		this.currentLevel = 1;
		this.currentStoryBeat = 1;
		this.c = c;
		this.env = findEnv();
	}
	// Change level module
	public void changeLevel() {
		if (env.canChangeLevel()) {
		currentLevel++;
		this.env = findEnv();
		}
	}
	
	// Letting the character put something within their inventory
	public void putInBag(Block b) {
		/*if (b instanceof Story) {
			((Story)b).setCurrentStoryBeat(currentStoryBeat);
			currentStoryBeat++;
		}*/
		env.putInInventory(b);
		
	}
	
	// If we want the new level map, we will need to find wich level it is
	// Only use this method WHEN Starting new level
	private Environment findEnv(){
		if (currentLevel == 1) {
			try {
				env = new Environment(c,"levelOne.txt");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (currentLevel == 2) {
			try {
				env = new Environment(c,"levelTwo.txt");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (currentLevel == 3) {
			try {
				env = new Environment(c,"levelThree.txt");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (currentLevel == 4) {
			try {
				env = new Environment(c,"levelFour.txt");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (currentLevel == 5) {
			try {
				env = new Environment(c,"levelFive.txt");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return env;
	}
	
	// Getting inventory
	public ArrayList<Block> getInventory(){
		return c.getInventory();
	}
	
	// Getting the current block
	public Block getCurrentBlock() {
		Block b = env.getCurrentBlock();
		
		// For the story 
		if (b instanceof Story) {
			((Story)b).interact();
		}
		if (b instanceof Person) {
			((Person)b).interact();
		}
		return b;
	}
	
	
	// Moving a character
	public void moveCharacter(String move) {
		if (move.equalsIgnoreCase("D")) {
			env.moveRight();
		}
		if (move.equalsIgnoreCase("S")) {
			env.moveDown();
		}
		if (move.equalsIgnoreCase("A")) {
			env.moveLeft();
		}
		if (move.equalsIgnoreCase("W")) {
			env.moveUp();
		}
	}
	
	// Getting the needed environment
	public Environment getEnv() {
		return this.env;
	}
	// Need a module to move an enemy
	public void moveEnemy(int prevRow,int prevCol,int newRow,int newCol) {
		env.deleteBlock(prevRow, prevCol);
		env.placeBlock(newRow, newCol, new Enemy(newRow,newCol));
		env.placeBlock(prevRow, prevCol, env.getBeforeBlockEnemy());
	}
	
	
	// Getting the map
	public ArrayList<ArrayList> getEnvMap() {
		return env.getMap();
	}
	
	// Getting the name of character
	public String getName() {
		return env.getName();
	}
	
	public boolean isGameOver() {
		return false;
	}

}