package controller;

import java.util.ArrayList;

import model.Environment;
import model.Block;
import model.Character;

public class Controller {
	
	private int currentLevel;
	private Environment env;
	private Character c;
	// Basically, we are making a constructor for our controller class that
	//allows for an instance of environment and current level
	public Controller(Character c) {	
		this.currentLevel = 1;
		this.c = c;
		this.env = findEnv();
	}
	// Change level module
	public void changeLevel() {
		currentLevel++;
		this.env = findEnv();
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
		return env;
	}
	
	// Getting inventory
	public ArrayList<Block> getInventory(){
		return c.getInventory();
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
	
	
	// Getting the map
	public ArrayList<ArrayList> getEnvMap() {
		return env.getMap();
	}
	
	// Finding out if the game is over
	public boolean isGameOver() {
		return false;
	}

}
