// Spider 
package model;
import java.util.*;


public class Spider extends Enemies{
    private String description;
    private int purpose;
    private ArrayList<Block> levelArr;
    private int amountCol;
    private int pos;
    private ArrayList<String> moveList;
    private int newLfe;
    public Spider(int pos, String key, int amountCol){
        super(pos,key,amountCol);
        this.description = "A spider!";
        this.purpose = 0;

        this.amountCol = amountCol;
        this.pos = pos;
        this.newLife = 10;
        this.moveList = new ArrayList<String>();
    }
    
    
    @Override
    public int getLife(){
        return this.newLife;
    }
    
    
    @Override
    public String getDescription(){
        return this.description;
    }
    

}
