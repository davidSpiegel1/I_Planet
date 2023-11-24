// A class for the devil (Extended from enemy)
package model;
import java.util.*;


public class Alyosha extends Devil{
    private String description;
    private int purpose;
    private ArrayList<Block> levelArr;
    private int amountCol;
    private int pos;
    private ArrayList<String> moveList;
    private int newLife;
    
    private boolean isAngry;
    
    
    private Story st;// For story
    
    
    public Alyosha(int pos, String key, int amountCol){
        super(pos,key,amountCol);
        this.description = "Alyosha. Brother of Ivan.";
        this.purpose = 0;

        this.amountCol = amountCol;
        this.pos = pos;
        this.newLife = 150;
        this.moveList = new ArrayList<String>();
        
        Story sl = new Story("utilities/story/AlyoshaStory.txt");
        st = sl.getTree();
        isAngry = true; // Is angry will be true. But he will not hurt ivan
    }
    
    
    
    public String getComment(){
        return st.getValue();
    }

    public String getOptionOne(){
        return st.getResponse1();
    }

    public String getOptionTwo(){
        return st.getResponse2();
    }

    public void goLeft(){
        if (st.getLeft() != null){
        st = st.getLeft();
        }
    }
    public void goRight(){
        if (st.getRight() != null){
        st = st.getRight();
        }
    }
    
    public boolean getIsAngry(){
        return this.isAngry;
    }

    @Override
    public int getLife(){
        return this.newLife;
    }
    
    
    
    @Override
    public void decrementLife(){
        this.isAngry = false;
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
