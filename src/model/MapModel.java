package model;

import java.util.ArrayList;

import controller.Controller;

public class MapModel {
	
	// Instance variables
	private ArrayList<ArrayList>mapList;
	private int row;
	private int col;
	

	public MapModel(int row,int col) throws Exception {
		
		this.row = row;
		this.col = col;
		// We are going to have an array list that has everything in it
		mapList = new ArrayList<ArrayList>();
		mapList = populateMapList();
	}
	
	// Populating the map list
	public ArrayList<ArrayList> populateMapList() throws Exception{
		// Using the environment object to get lots of maps
			Character c = new Character(0,0,"C");
			Environment env = new Environment(c,"/Users/davidspiegel/git/I_Planet/src/utilities/levelOne.txt");
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelOne.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelTwo.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelThree.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				try {
					
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelFour.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelFive.txt");
					env.placeBlock(row,col,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelSix.txt");
					env.placeBlock(row,col,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			

				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelSeven.txt");
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/levelEight.txt");
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
		return mapList;
			
		
	}
	
	// Getting the list of maps
	public ArrayList<ArrayList> getMapList(){
		
	
		return mapList;
	}
	/*public static void main(String args[]) throws Exception {
		MapModel m = new MapModel();
		ArrayList<ArrayList>arr = m.getMapList();
		for (int i = 0; i<= arr.size()-1;i++) {
				
			System.out.println(arr.get(i));
		}
		
	}*/
	
	
	
	
}
