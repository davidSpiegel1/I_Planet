package utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

// Importing needed classes

import model.Character;
import model.Environment;
import model.Person;
import model.Block;

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
		c.moveLeft();
		assertEquals(0,c.getRow());
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
	@Test
	void TestInventoryCharacter() {
		Character c = new Character(0,0,"Man");
		// Now checking if the inventory works correctly
		c.addToInventory(new Block("s"));
		c.addToInventory(new Block("t"));
		c.addToInventory(new Block("O"));
		c.removeInventory(0);
		ArrayList<Block> arr = c.getInventory();
		assertEquals("t",arr.get(0).getKey());
		assertEquals("O",arr.get(1).getKey());
		
	}
	@Test
	void TestOverrideMethodsCharacter() {
		Character c = new Character(0,0,"Man");
		// Checking get description to be name and get key to be "C"
		assertEquals("Man",c.getDescription());
		assertEquals("C",c.getKey());
	}
	@Test
	void TestPersonClass() {
		// The default of person
		Person p = new Person(0,0);
		assertEquals("What are you looking at?",p.getDescription());
		// What comes else could come up
		Person p1 = new Person(4,14);
		System.out.println(p1.getDescription());
		System.out.println(p1.interact());
		System.out.println(p1.getDescription());
		System.out.println(p1.interact());
		assertEquals("My name is Ashcroft. General in IPDF!",p1.getDescription());
		System.out.println(p1.interact());
		assertEquals("What is the IPDF? Well, it is the I-Planetary Defence force of course!",p1.getDescription());
		System.out.println(p1.interact());
		assertEquals("I joined it a years ago. I love it!",p1.getDescription());
		System.out.println(p1.interact());
		assertEquals("Of course, some think it is wrong, but when the world caves in and you face hell itself...",p1.getDescription());
		// 
		Person p2 = new Person(11,15);
		System.out.println(p2.getDescription());
		System.out.println(p2.interact());
		System.out.println(p2.getDescription());
		System.out.println(p2.interact());
		assertEquals("Your following the WOLF I see.",p2.getDescription());
		System.out.println(p2.interact());
		assertEquals("Colterstevens, he's the one guiding you? ",p2.getDescription());
		System.out.println(p2.interact());
		assertEquals("Good man. I served under him back in the great war.",p2.getDescription());
		System.out.println(p2.interact());
		assertEquals("Lost a leg. Ha! Do you want to know why they call him the wolf?",p2.getDescription());
		//
		Person p3 = new Person(10,11);
		System.out.println(p3.getDescription());
		System.out.println(p3.interact());
		System.out.println(p3.getDescription());
		System.out.println(p3.interact());
		assertEquals("Your gonna save my child, right?",p3.getDescription());
		System.out.println(p3.interact());
		assertEquals("My child! in the town! Those monsters!? ",p3.getDescription());
		System.out.println(p3.interact());
	}
	
	
	
	
	
	
}
