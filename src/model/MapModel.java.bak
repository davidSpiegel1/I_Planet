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
			Environment env = new Environment(c,"/Users/davidspiegel/git/I_Planet/src/utilities/1.txt");
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/1.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/2.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/3.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				try {
					
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/4.txt");
					env.placeBlock(col,row,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/5.txt");
					env.placeBlock(row,col,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/6.txt");
					env.placeBlock(row,col,new Character(0,0,"C"));
					env.deleteBlock(0, 0);
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			

				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/7.txt");
					mapList.add(env.getMap());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
				try {
					env = new Environment(c, "/Users/davidspiegel/git/I_Planet/src/utilities/8.txt");
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
