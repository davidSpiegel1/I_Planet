package utilities;

// Importing needed classes
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Environment;
import model.Character;
import model.Block;
import controller.Controller;

/**
 * 
 * @author David Spiegel
 * 
 * Description: The tests for the data within the I_Planet back end. 
 * The goal is to get 100% coverage with most classes in model and controller.
 *
 */
class I_PlanetTests {

	@Test
	void TestBlockKey() {
		// First, we create the new blocks
		Block b1 = new Block("K");
		Block b2 = new Block("p");
		// Then, we will find if the key is correct
		assertEquals("K",b1.getKey());
		assertEquals("p",b2.getKey());
		
	}
	
	@Test
	void TestBlockDescription() {
		// First, we create the new blocks
		Block b1 = new Block("K");
		Block b2 = new Block("B");
		Block b3 = new Block("G");
		Block b4 = new Block(".");
		Block b5 = new Block("W");
		Block b6 = new Block("t");
		Block b7 = new Block("O");
		Block b8 = new Block("X");
		// Then, we check if the description is correct
		assertEquals("A Knife. Can kill enemies with.",b1.getDescription());
		assertEquals("Back to previous level..",b2.getDescription());	
		assertEquals("Grass. Has no practical use.",b3.getDescription());
		assertEquals("A stone. Has no practical use.",b4.getDescription());
		assertEquals("Water. Has no practical use.",b5.getDescription());
		assertEquals("Tree. Look at this picture!",b6.getDescription());	
		assertEquals("Next level",b7.getDescription());
		assertEquals("Gun. Kills lots of enemies.",b8.getDescription());
	}
	
	@Test
	void TestEmptyDescription() {
		// First, we create the new blocks
		Block b1 = new Block("hi");
		Block b2 = new Block("hello");
		// Then, we check if the description is nothing
		assertEquals("Nothing.",b1.getDescription());
		assertEquals("Nothing.",b2.getDescription());	
	}
	
	@Test
	void TestCharacterRowAndCol() {
		Character c = new Character(0,0,"Man");
		assertEquals(0,c.getRow());
		assertEquals(0,c.getCol());
		// Now we will move the character and see if it changes
		c.moveDown();
		assertEquals(1,c.getCol());
		c.moveRight();
		assertEquals(1,c.getRow());
		c.moveUp();
		assertEquals(0,c.getCol());
		// Now we will try to set the col and row
		c.setCol(2);
		c.setRow(2);
		assertEquals(2,c.getCol());
		assertEquals(2,c.getRow());
		
	}
	
	@Test
	void TestCharacterGetName() {
		Character c = new Character(0,0,"Man");
		Character c2 = new Character(0,0,"Man2");
		// Now we check the name
		assertEquals("Man",c.getName());
		assertEquals("Man2",c2.getName());
		
	}

}
