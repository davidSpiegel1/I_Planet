package model;

public class Person extends Block{

	// Basically the current level and description
	private int currentRow;
	private int currentCol;
	private String intro;
	private String characterName;
	private int currentInteraction;
	public Person(int row,int col) {
		super("P");
		System.out.println(row);
		System.out.println(col);
		characterName = " ";
		findDescription(row,col);
	}
	private void findDescription(int row, int col) {
		if (row == 4 && col == 14) {
			
			characterName = "Ashcroft";
		}
		else if (row == 10 && col == 3) {
			intro = "Have you heard of the IPF?";
		}
		else {
			intro = "What are you looking at?";
		}
	}
	
	// A method that allows for interaction
	public String interact() {
		String description = "";
		// Doing ashcroft as a character
		if (characterName.equals("Ashcroft")) {
			if (currentInteraction == 1) {
				intro = "My name is Ashcroft. General in IPDF!";
			}
			else if (currentInteraction == 2) {
				
				intro = "What is the IPDF? Well, it is the I-Planetary Defence force of course!";
			}
			else if (currentInteraction == 3) {
				intro = "I joined it a couple years ago. I love it!";
			}
		}
		currentInteraction++;
	return description;
}
	
	@Override
	public String getDescription() {
		return this.intro;
	}
	

}
