package model;
import java.util.*;


// Modification of Enemy class
public class Enemies extends MovableBlock{
    
    private String description;
    private int purpose;
    private ArrayList<Block> levelArr;
    private int amountCol;
    private int pos;
    private ArrayList<String> moveList;
    
    public Enemies(int pos, String key, int amountCol){
        super(pos,key,amountCol);
        this.description = "None";
        this.purpose = 0;

        this.amountCol = amountCol;
        this.pos = pos;
        this.moveList = new ArrayList<String>();
    }
    
    
    public void setLevelArr(ArrayList<Block> levelArr){
        this.levelArr = levelArr;
    }
    
    
    // The idea is to use a variation of Dikstras algorithm to find the
    // Fastest way to 'purpose' coordinate
    
    public int generateNewPos(){
        // If going up gets closer to 'purpose', then we go up
        if (this.pos-this.amountCol-2 >= 0){
           //if(!this.levelArr.get(this.pos-this.amountCol-2).getKey().equals("_") && !this.levelArr.get(this.pos-this.amountCol-2).getKey().equals("|")){
                int possUp = this.pos-this.amountCol-2;
                    // If the possible up value has less distance
                    System.out.println("The possUp diff: "+Math.abs(possUp-this.purpose));
                    if (Math.abs(possUp-this.purpose) <= Math.abs(this.pos-this.purpose)){
                            this.pos = possUp;
                            this.moveList.add("w"); // Making the move to up
                    }
              //  }
        }
        
        // If going down gets closer to 'purpose', then we go down
        if (this.pos+this.amountCol+2 <= this.levelArr.size()-1){
          //  if (!this.levelArr.get(this.pos+this.amountCol+2).getKey().equals("_") && !this.levelArr.get(this.pos+this.amountCol+2).getKey().equals("|")){
                int possDown = this.pos+this.amountCol+2;
                // If possible down value has less distance
                if (Math.abs(possDown-this.purpose) <= Math.abs(this.pos-this.purpose)){
                        this.pos = possDown;
                        this.moveList.add("s"); // Making the move to down
                }
                
             //   }
        }
        
        // If going left gets closer to 'purpose', then we go left
        if (this.pos-1 >= 0){
               // if (!this.levelArr.get(this.pos-1).getKey().equals("_") && !this.levelArr.get(this.pos-1).getKey().equals("|")){
                    int possLeft = this.pos-1;
                    // If possible left value has less distance
                    if (Math.abs(possLeft-this.purpose) <= Math.abs(this.pos-this.purpose)){
                        this.pos = possLeft;
                        this.moveList.add("a"); // Making the move to left
            
                    }
                  //  }
            }
        
        // I going right gets closer to 'purpose', then we go right
        if (this.pos+1 <= this.levelArr.size()-1){
            //if (!this.levelArr.get(this.pos+1).getKey().equals("_") && !this.levelArr.get(this.pos+1).getKey().equals("|") && ((this.pos+1)%(this.amountCol)) != 0){
                int possRight = this.pos+1;
                // If possible left value has less distance
                if (Math.abs(possRight-this.purpose) <= Math.abs(this.pos-this.purpose)){
                    this.pos = possRight;
                    this.moveList.add("d"); // Making the move to left
                }
            //}
        }
        return this.pos; // Returning the modified position
        
    }// End of generate new pos
    
    // A getter to recieve the list of moves
    public ArrayList<String> getMoves(){
        return this.moveList;
    }
    
    public void clearMoves(){
        this.moveList.clear();
    }
    
    
    // We have to update the characters position every time
    public void updatePurpose(int purpose){
        this.purpose = purpose;
    }
    
    
    
    @Override
    public String getDescription(){
        return "An Enemy!!";
    }
    
}// End of the Enemies classs
