package model;

public class Block {
	private final String key;
	private String description;
	// Constructor for the key
	public Block(String key) {
		this.key = key;
		findDescription(key);

	}
	private void findDescription(String k) {
		if (k.equalsIgnoreCase("G")) {
			description = "Grass. Has no practical use.";
		}
		else if (k.equalsIgnoreCase(".")) {
			description = "A stone. Has no practical use.";
		}else if (k.equalsIgnoreCase("W")) {
			description = "Water. Has no practical use.";
		}else {
			description = "Nothing.";
		}
		
		
	}
	// getting the key
	public String getKey() {
		return key;
	}
	
	// Getting the description
	public String getDescription() {
		return description;
	}

}
