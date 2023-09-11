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
		}else if(k.equalsIgnoreCase("t")) {
			description = "Tree. Look at this picture!";
		}else if (k.equalsIgnoreCase("O")){
			description = "Next level";
		}else if (k.equalsIgnoreCase("K")) {
			description = "A Knife. Can kill enemies with.";
		}else if (k.equalsIgnoreCase("B")) {
			description = "Back to previous level..";
		}else if (k.equalsIgnoreCase("X")) {
			description = "Gun. Kills lots of enemies.";
		}
		
		
		else{
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
    
    public void setDescription(String description){
        
        this.description = description;
    }
    
    @Override
    public String toString(){
        String str = "";
        str+= key;
       
        return str;
    }

}
