//
//The evil dog...
package model;
import java.util.*;


public class Edog extends Enemies{
    private String description;
    private int purpose;
    private ArrayList<Block> levelArr;
    private int amountCol;
    private int pos;
    private ArrayList<String> moveList;
    private int newLife;
    public Edog(int pos, String key, int amountCol){
        super(pos,key,amountCol);
        this.description = "An evil dog!";
        this.purpose = 0;

        this.amountCol = amountCol;
        this.pos = pos;
        this.newLife = 50;
        this.moveList = new ArrayList<String>();
    }
    
    
    @Override
    public int getLife(){
        return this.newLife;
    }
    
    @Override
    public void decrementLife(){
        this.newLife--;
    }
    
    @Override
    public void incrementLife(){
        this.newLife++;
    }
    
    
    @Override
    public String getDescription(){
        return this.description;
    }
    
    @Override
    public boolean isDead(){
        return this.newLife<=0;
    }
    

}
