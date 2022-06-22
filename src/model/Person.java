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
		else if (row == 11 && col == 15) {
			characterName = "Rasputin";
		}
		else if (row == 10 && col == 11) {
			characterName = "Help";
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
				intro = "I joined it a years ago. I love it!";
			}
			else if (currentInteraction == 3) {
				intro = "Of course, some think it is wrong, but when things get terrible, when the world caves in and you face hell itself...";
			}
			
		}
		if (characterName.equals("Rasputin")) {
			if (currentInteraction == 1) {
				intro = "Your following the WOLF I see.";
			}
			else if (currentInteraction == 2) {
				
				intro = "Colterstevens, he's the one guiding you? ";
			}
			else if (currentInteraction == 3) {
				intro = "Good man. I served under him back in the great war.";
			}
			else if (currentInteraction == 4) {
				intro = "Lost a leg. Ha! Do you want to know why they call him the wolf?";
			}
		}
		if (characterName.equals("Help")) {
			if (currentInteraction == 1) {
				intro = "Your gonna save my child, right?";
			}
			else if (currentInteraction == 2) {
				
				intro = "My child! in the town! Those monsters!? ";
			}
			else if (currentInteraction == 3) {
				intro = "You will?! Thank god!";
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
