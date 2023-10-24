/*
 MovableObject.java
 Basically a class that contains more
 
 
 
 */



package model;

public class MovableBlock extends Block{
    
    private int pos;
    private Block nextBlock;
    private String key;
    private int animalLife;
    private int maxCol;
  
    
    
    public MovableBlock(int pos, String key, int maxCol){
        super(key);
        this.pos = pos;
        this.key = key;
        this.maxCol = maxCol;
        this.animalLife = 5;
        
    }
    
    public boolean isDead(){
        return this.animalLife <= 0;
    }
    
    public int getLife(){
        return this.animalLife;
    }
    public void decrementLife(){
        this.animalLife--;
    }
    public void incrementLife(){
        this.animalLife++;
    }
  
    
    public void moveRight(){
        this.pos++;
    }
    
    public void moveLeft(){
        this.pos--;
    }
    
    public void moveUp(int amountCol){
        this.pos -= amountCol+2;
    }
    
    public void moveDown(int amountCol){
        this.pos += amountCol+2;
    }
    
    public void setPos(int pos){
        this.pos = pos;
    }
    public int getPos(){
        return this.pos;
    }
    

    
    
    @Override
    public String getDescription() {
        return "Movable obj: ";
    }
    
    
    
    
    
    
}
