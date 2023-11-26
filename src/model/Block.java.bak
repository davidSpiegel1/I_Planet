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
		}else if (k.equals("W")) {
			description = "Water. Has no practical use.";
		}else if(k.equalsIgnoreCase("t")) {
			description = "Tree. Look at this picture!";
		}else if (k.equals("O")){
			description = "Next level";
		}else if (k.equalsIgnoreCase("K")) {
			description = "A Knife. Can kill enemies with.";
		}else if (k.equalsIgnoreCase("B")) {
			description = "Back stone..";
		}else if (k.equalsIgnoreCase("X")) {
			description = "Gun. Kills lots of enemies.";
		}else if (k.equalsIgnoreCase("d")){
            description ="Animal. Should be moving.";
        }else if (k.equals("F")){
            description ="Fire!!";
        }else if (k.equals("f")){
            description ="A flower. What you can eat.";
        }else if (k.equals("w")){
            description ="A web. stay away";
        }
        else if (k.equals("#")){
	    description = "Wood!";
        }
        else if (k.equals("$")){
        description = "A wooden weapon";
        }
        else if (k.equals("^")){
        description = "A palm tree";
        }
        else if (k.equals("<")){
        description = "A snowy tree";
        }
        else if (k.equals("*")){
        description = "Snow!";
        }
        else if (k.equals(",")){
        description = "A jacket!";
        }
        else if (k.equals("[")){
        description = "A stone";
        }
        else if (k.equals("]")){
        description = "Wool. Can make clothes";
        }
        

        else if (k.equals("o")){
            description = "next levelz";
        }
        else if (k.equals(">")){
            description = "Gate. Must complete task.";
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
