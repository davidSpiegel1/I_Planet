package model;
import java.util.Random;


/**
 * 
 * @author davidspiegel
 * 
 * Description: This class will be for the enemy. Then enemy will follow a 
 * 				simple algorithm and move every time it is updated.
 *
 */
public class Enemy extends Block{
	
	private int row;
	private int col;
	private int algoMove;
	private final int AMOUNT_OF_POSSIBLE_MOVES = 4;
	public Enemy(int row,int col) {
		super("E");
		this.row = row;
		this.col = col;
		Random rand = new Random();
		algoMove = rand.nextInt(AMOUNT_OF_POSSIBLE_MOVES);
	}
	
	// When we update the enemy, we will follow a simple algorithm
	public void updateEnemy() {
		if (algoMove == 0) {
			moveRight();
		}else if (algoMove == 1) {
			moveDown();
		}else if (algoMove == 2) {
			moveUp();
		}else if (algoMove == 3) {
			moveLeft();
		}
		Random rand = new Random();
		algoMove = rand.nextInt(AMOUNT_OF_POSSIBLE_MOVES);
		
	}
	
	// Need get row and get column
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	// When our character moves right
	public void moveRight() {
		row++;
	}
	
	// When our character moves left
	public void moveLeft() {
		row--;
	}
	// When our character moves up
	public void moveUp() {
		col--;
	}
	// When our character moves down
	public void moveDown() {
		col++;
	}
	
	// Overriding the description of this block
	@Override
	public String getDescription() {
		return "This is an Ememy! Watch out!";
	}


}
