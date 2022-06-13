package model;

import java.util.ArrayList;
import java.io.*;

/**
 * 
 * @author davidspiegel
 * 
 * Description: Within our environment class, we will construct
 * 				a 2D arrayList of blocks from a map
 *
 */
public class Environment {
	
	private ArrayList<ArrayList> map;
	private String fileName;
	private Character c;
	private Block beforeBlock;
	// A constructor to allow for the creation of a map
	public Environment(Character c, String fileName) throws Exception {
		this.fileName = fileName;
		this.map = createMap(); 
		this.c = c;
		beforeBlock = inputCharacter();
	
	}
	
	// Within this input character method, we will simply be putting the character in the first block
	// But, the before block is needed to find what was previously there
	private Block inputCharacter() {
		Block bb = (Block) map.get(0).get(0);
		map.get(0).set(0, this.c);
		return bb;
	}

	// A helper function to build the map
	private ArrayList<ArrayList> createMap() throws Exception{
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		// Declaring a string and array list
		String st;
		ArrayList<ArrayList> arr2 = new ArrayList<ArrayList>();
		
		// While loop to look through the level
		while ((st= br.readLine()) != null) {
			System.out.println(st);
			ArrayList<Block>arr = new ArrayList<Block>();
			// For this line, we will have to make another loop
			for (int i = 0; i<= st.length()-1;i++) {
				Block bl = new Block(st.substring(i,i+1));
				
				arr.add(bl);
				
			}
			arr2.add(arr);
		}
		return arr2;
	}
	
	// Move right within environment
	public void moveRight() throws IllegalArgumentException{
		int currentRow;
		int currentCol;
		int prevRow;
		int prevCol;
		prevRow = c.getRow();
		prevCol = c.getCol();
		this.c.moveRight();
		currentRow = c.getRow();
		currentCol = c.getCol();
		if ((currentRow < 0 || currentRow > map.get(0).size()) || (currentCol <0 || currentCol > map.size() )){
			throw new IllegalArgumentException("Err. You have gone out of bounds.");
		}
		map.get(prevCol).set(prevRow, beforeBlock);
		beforeBlock = (Block)map.get(currentCol).get(currentRow);
		map.get(currentCol).set(currentRow, c);
		
	}
	
	// Move left within environment
		public void moveLeft() throws IllegalArgumentException{
			int currentRow;
			int currentCol;
			int prevRow;
			int prevCol;
			prevRow = c.getRow();
			prevCol = c.getCol();
			this.c.moveLeft();
			currentRow = c.getRow();
			currentCol = c.getCol();
			if ((currentRow < 0 || currentRow > map.get(0).size()) || (currentCol <0 || currentCol > map.size() )){
				throw new IllegalArgumentException("Err. You have gone out of bounds.");
			}
			map.get(prevCol).set(prevRow, beforeBlock);
			beforeBlock = (Block)map.get(currentCol).get(currentRow);
			map.get(currentCol).set(currentRow, c);
			
		}
		
		// Move up within environment
				public void moveUp() throws IllegalArgumentException{
					int currentRow;
					int currentCol;
					int prevRow;
					int prevCol;
					prevRow = c.getRow();
					prevCol = c.getCol();
					this.c.moveUp();
					currentRow = c.getRow();
					currentCol = c.getCol();
					if ((currentRow < 0 || currentRow > map.get(0).size()) || (currentCol <0 || currentCol > map.size() )){
						throw new IllegalArgumentException("Err. You have gone out of bounds.");
					}
					map.get(prevCol).set(prevRow, beforeBlock);
					beforeBlock = (Block)map.get(currentCol).get(currentRow);
					map.get(currentCol).set(currentRow, c);
					
				}
				
				// Move down within environment
				public void moveDown() throws IllegalArgumentException{
					int currentRow;
					int currentCol;
					int prevRow;
					int prevCol;
					prevRow = c.getRow();
					prevCol = c.getCol();
					this.c.moveDown();
					currentRow = c.getRow();
					currentCol = c.getCol();
					if ((currentRow < 0 || currentRow > map.get(0).size()) || (currentCol <0 || currentCol > map.size() )){
						throw new IllegalArgumentException("Err. You have gone out of bounds.");
					}
					map.get(prevCol).set(prevRow, beforeBlock);
					beforeBlock = (Block)map.get(currentCol).get(currentRow);
					map.get(currentCol).set(currentRow, c);
					
				}
	
	// How we will get the array list map
	public ArrayList<ArrayList> getMap() {
		return map;
	}
	
	
	
	

}
