package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//import view.IplanetGui;

/**
 * 
 * @author davidspiegel
 * 
 *         Description: Within our environment class, we will construct a 2D
 *         arrayList of blocks from a map
 *
 */
@SuppressWarnings("deprecation")
public class Environment{

	private ArrayList<ArrayList> map;
	private String fileName;
	private Character c;
	private Block beforeBlock;
	private Block beforeBlockEnemy;

	// A constructor to allow for the creation of a map
	public Environment(Character c, String fileName) throws Exception {
		this.fileName = fileName;
		this.map = createMap();
		this.c = c;
		beforeBlock = inputCharacter();
		beforeBlockEnemy = new Block("E");
	

	}

	// Within this input character method, we will simply be putting the character
	// in the first block
	// But, the before block is needed to find what was previously there
	private Block inputCharacter() {
		Block bb = (Block) map.get(0).get(0);
		map.get(0).set(0, this.c);
		this.c.setCol(0);
		this.c.setRow(0);
		return bb;
	}

	// A helper function to build the map
	private ArrayList<ArrayList> createMap() throws Exception {
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));

		// Declaring a string and array list
		String st;
		ArrayList<ArrayList> arr2 = new ArrayList<ArrayList>();
		int currentRow = 0;
		// While loop to look through the level
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			ArrayList<Block> arr = new ArrayList<Block>();
			// For this line, we will have to make another loop
			for (int i = 0; i <= st.length() - 1; i++) {
				Block bl;
				// Making an enemy within the map
				if (st.substring(i, i + 1).equalsIgnoreCase("E")) {
					bl = new Enemy(currentRow, i);
				} else if (st.substring(i, i + 1).equalsIgnoreCase("S")) {
					bl = new Story(currentRow, i);
				} else if (st.substring(i, i + 1).equalsIgnoreCase("P")) {
					bl = new Person(currentRow, i);
				} else {
					bl = new Block(st.substring(i, i + 1));
				}

				arr.add(bl);

			}
			currentRow++;
			arr2.add(arr);
		}
		return arr2;
	}

	// We want to get the current block
	public Block getCurrentBlock() {
		return beforeBlock;
	}

	// We want to delete a block if needed
	public void deleteBlock(int row, int col) {
		// Put a throws statement here!!!

		map.get(row).set(col, new Block("."));
	
	}

	public void placeBlock(int row, int col, Block b) {

		beforeBlockEnemy = (Block) map.get(row).get(col);
		map.get(row).set(col, b);
		
	}

	public Block getBeforeBlockEnemy() {
		return beforeBlockEnemy;
	}

	// We now want to put the value into inventory and remove it from the map
	public void putInInventory(Block b) {
		this.c.addToInventory(b);
		beforeBlock = new Block(".");
		
	}

	// We want the name from the character
	public String getName() {
		return c.getName();
	}

	// Move right within environment
	public void moveRight() {
		int currentRow;
		int currentCol;
		int prevRow;
		int prevCol;
		prevRow = c.getRow();
		prevCol = c.getCol();
		this.c.moveRight();
		currentRow = c.getRow();
		currentCol = c.getCol();
		if ((currentRow >= 0 && currentRow < map.get(0).size()) && (currentCol >= 0 && currentCol < map.size())) {
			Block currentBlock = (Block) map.get(currentCol).get(currentRow);
			if (!(currentBlock.getKey().equals("_") || currentBlock.getKey().equals("|"))) {
				map.get(prevCol).set(prevRow, beforeBlock);
				beforeBlock = (Block) map.get(currentCol).get(currentRow);
				map.get(currentCol).set(currentRow, c);

			} else {
				this.c.moveLeft();
			}
		} else {
			this.c.moveLeft();
		}


	}

	// Move left within environment
	public void moveLeft() {
		int currentRow;
		int currentCol;
		int prevRow;
		int prevCol;
		prevRow = c.getRow();
		prevCol = c.getCol();
		this.c.moveLeft();
		currentRow = c.getRow();
		currentCol = c.getCol();
		if ((currentRow >= 0 && currentRow < map.get(0).size()) && (currentCol >= 0 && currentCol < map.size())) {
			Block currentBlock = (Block) map.get(currentCol).get(currentRow);
			if (!(currentBlock.getKey().equals("_") || currentBlock.getKey().equals("|"))) {
				map.get(prevCol).set(prevRow, beforeBlock);
				beforeBlock = (Block) map.get(currentCol).get(currentRow);
				map.get(currentCol).set(currentRow, c);

			} else {
				this.c.moveRight();
			}
		} else {
			this.c.moveRight();
		}


	}

	// Move up within environment
	public void moveUp() {
		int currentRow;
		int currentCol;
		int prevRow;
		int prevCol;
		prevRow = c.getRow();
		prevCol = c.getCol();
		this.c.moveUp();
		currentRow = c.getRow();
		currentCol = c.getCol();
		if ((currentRow >= 0 && currentRow < map.get(0).size()) && (currentCol >= 0 && currentCol < map.size())) {
			Block currentBlock = (Block) map.get(currentCol).get(currentRow);
			if (!(currentBlock.getKey().equals("_") || currentBlock.getKey().equals("|"))) {
				map.get(prevCol).set(prevRow, beforeBlock);
				beforeBlock = (Block) map.get(currentCol).get(currentRow);
				map.get(currentCol).set(currentRow, c);

			} else {
				this.c.moveDown();
			}
		} else {
			this.c.moveDown();
		}
	

	}

	// Move down within environment
	public void moveDown() {
		int currentRow;
		int currentCol;
		int prevRow;
		int prevCol;
		prevRow = c.getRow();
		prevCol = c.getCol();
		this.c.moveDown();
		currentRow = c.getRow();
		currentCol = c.getCol();
		if ((currentRow >= 0 && currentRow < map.get(0).size()) && (currentCol >= 0 && currentCol < map.size())) {
			Block currentBlock = (Block) map.get(currentCol).get(currentRow);
			if (!(currentBlock.getKey().equals("_") || currentBlock.getKey().equals("|"))) {
				map.get(prevCol).set(prevRow, beforeBlock);
				beforeBlock = (Block) map.get(currentCol).get(currentRow);
				map.get(currentCol).set(currentRow, c);

			} else {
				this.c.moveUp();
			}
		} else {
			this.c.moveUp();
		}
	

	}

	// How we will get the array list map
	public ArrayList<ArrayList> getMap() {
		return map;
	}

	// How we know when to change levels
	public boolean canChangeLevel() {
		boolean canChange = false;
		if (beforeBlock.getKey().equalsIgnoreCase("O")) {
			canChange = true;
		}
		return canChange;

	}

	

}
