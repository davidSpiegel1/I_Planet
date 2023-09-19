// New Controller class
package controller;
import java.util.*;
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Story;
import javafx.scene.control.MenuButton;
// New imports
import model.Parse;
import model.Scan;
import model.Character;
import model.MovableBlock;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.*;
import javafx.util.Duration;
// The class of the controller
public class Controller2{
    private int currentLevel;
    private int currentStoryBeat;
    private boolean isGameOver;
    private int charPos;
    private int amountCol;
    private Block prevBlock = null;
    
    private boolean didMove = false;// An instance variable to check if the person moved or not
    
    
    private ArrayList<Block>levelArr; // list of blocks for the level we are on
    
    
    private ArrayList<String>levelNames; // List of level names
    
    private Scan sc; // Scanner object
    private Parse p; // Parse object
    
    // A Label for current label
    private Label curLabel;
    private Label prevLabel;
    
    public Controller2(){
        // Us initializing everything
        this.currentLevel = 0; // The current level
        this.charPos = 0;
        this.prevBlock = null;
        this.amountCol = 20;
        this.isGameOver = false;
        this.levelNames = new ArrayList<String>();
        p = new Parse(); // parser object intiated
        
        
        
        this.levelNames = this.populateLevelNames(this.levelNames);
        
        // Initalizing the current label and the previous label
        this.curLabel = null;
        this.prevLabel = null;
        
        
        
    }
   
    
    public ArrayList<String> populateLevelNames(ArrayList<String> arr){
        arr.add("model/testFile.txt");
        arr.add("utilities/levelOne.txt");
        arr.add("utilities/levelTwo.txt");
        arr.add("utilities/levelThree.txt");
        arr.add("utilities/levelFour.txt");
        arr.add("utilities/levelFive.txt");
        arr.add("utilities/levelSix.txt");
        
        return arr;
        
    }
    
    public int getAmountCol(){
        return this.amountCol;
    }
    public ArrayList<Block> constructMap(){
        String levelName = this.levelNames.get(this.currentLevel); // Getting our level name
        this.levelArr = new ArrayList<Block>();
        Character newC = null;
        try{
            if (this.sc != null){
                
                newC = this.sc.getCharacter();
                this.sc = new Scan(levelName);
                this.sc.setCharacter(newC);
                this.levelArr = sc.scan();
                
            }else{
                
            this.sc = new Scan(levelName);
            this.levelArr = sc.scan();
            
            }
        
        } catch(Exception e){
            System.err.println("Error!");
        }
        if (newC == null){
            this.levelArr = this.placeChar(levelArr,null);
        }else{
            this.levelArr = this.placeChar(levelArr,newC);
        }
        return this.levelArr;
        
    }
    
    public ArrayList<Block> userCommand(String userCommand){
        String moveChar = "asdw";
        String interactEnv = "qi";
        if (moveChar.contains(userCommand)){
            this.moveChar(userCommand);
        }
      return this.levelArr;
        
        
    }
    
    // To parse the list to a gui version
    public ArrayList<Label> parse (ArrayList<Block>list){
        if (this.prevBlock == null){
            return p.constructGui(list);
        }else{
            return p.constructGui(list,this.prevBlock);
        }
    }
    
    // Where we will place the user if asked
    public ArrayList<Block> placeChar(ArrayList<Block> levelArr, Character c1){
        Character c;
        if (c1 == null){
            c = new Character();// Making a character block
        }else{
            c = c1;
        }
        this.prevBlock = levelArr.get(this.charPos);
        this.prevLabel = new Label(".");
        levelArr.set(this.charPos,c);
        return levelArr;
    }
    
    // Will have a new moveChar that moves the value to a new label list
    public int getCharPos(){
        return this.charPos;
    }
    
    
    public ArrayList<Block> moveChar(String move){
        if (move.equals("d")){
            if (this.charPos+1 <= this.levelArr.size()-1){
            Block c = this.levelArr.get(this.charPos);
            this.levelArr.set(this.charPos,this.prevBlock);
            this.charPos++; // Incrementing the character position..
            this.prevBlock = this.levelArr.get(this.charPos);
            this.levelArr.set(this.charPos,c);
            }else{
                this.didMove = false;
            }
        }
        else if (move.equals("a")){
            if (this.charPos-1 <= this.levelArr.size()-1){
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                this.charPos--;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
            }
        }
        else if (move.equals("s")){
            if (this.charPos+this.amountCol+2 <= this.levelArr.size()-1){
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                this.charPos+= this.amountCol+2;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
                
            }
        }
        else if (move.equals("w")){
            if (this.charPos-this.amountCol+2 >=0){
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                this.charPos-= this.amountCol+2;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
            }
        }
        
        
     return this.levelArr;
    }
    
    public Label getPrevLabel(){
        return this.prevLabel;
    }
    
    public void setPrevLabelBackground(Background l){
        this.prevLabel.setGraphic(null);
        //this.prevLabel = l;
    }
    
    public Label getCurLabel(){
        return this.curLabel;
    }
    //Block prevB = c.getPrevBlock();
    public Block getPrevBlock(){
        return this.prevBlock;
        
    }
    
    public void setPrevBlock(Block b){
        this.prevBlock =b;
    }
    public Block getCurBlock(){
        return this.levelArr.get(this.charPos);
    }
    
    public ArrayList<Label> moveChar2(String move,ArrayList<Label> labelArr){
        this.didMove = false;
        
        if (move.equals("d")){
            
            if (this.charPos+1 <= this.levelArr.size()-1){
                // The condition for barriers
                if (!this.levelArr.get(this.charPos+1).getKey().equals("_") && !this.levelArr.get(this.charPos+1).getKey().equals("|")){
            Block c = this.levelArr.get(this.charPos);
            this.levelArr.set(this.charPos,this.prevBlock);
                
            this.curLabel = labelArr.get(this.charPos);
            labelArr.set(this.charPos,this.prevLabel);
                
           
            
            this.charPos++; // Incrementing the character position..
            this.prevBlock = this.levelArr.get(this.charPos);
            this.levelArr.set(this.charPos,c);
                
            this.prevLabel = labelArr.get(this.charPos);
            labelArr.set(this.charPos,this.curLabel);
            this.didMove = true;
                    
                    }// End of condition of barriers
            }
        }
        else if (move.equals("a")){
            if (this.charPos-1 <= this.levelArr.size()-1 && this.charPos-1 >= 0){
                // Condition of barriers
                if (!this.levelArr.get(this.charPos-1).getKey().equals("_") && !this.levelArr.get(this.charPos-1).getKey().equals("|")){
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                // Now just do the same for the label arr
                this.curLabel = labelArr.get(this.charPos);
                labelArr.set(this.charPos,this.prevLabel);
                
                
                this.charPos--;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
                
                this.prevLabel = labelArr.get(this.charPos);
                labelArr.set(this.charPos,this.curLabel);
                
                this.didMove = true;
                }// End of condition of barriers
            }
        }
        else if (move.equals("s")){
            if (this.charPos+this.amountCol+2 <= this.levelArr.size()-1){
                // Condition of barriers
                if (!this.levelArr.get(this.charPos+this.amountCol+2).getKey().equals("_") && !this.levelArr.get(this.charPos+this.amountCol+2).getKey().equals("|")){
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                
                this.curLabel = labelArr.get(this.charPos);
                labelArr.set(this.charPos,this.prevLabel);
                
                
                this.charPos+= this.amountCol+2;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
                
                this.prevLabel = labelArr.get(this.charPos);
                labelArr.set(this.charPos,this.curLabel);
                this.didMove = true;
                    
                    }// End of condition of barriers
            }
        }
        else if (move.equals("w")){
            if (this.charPos-this.amountCol-2 >=0){
                // Condition of barriers
                System.out.println("Almost here to w with;"+(this.charPos-this.amountCol-2));
                String key =this.levelArr.get(this.charPos-this.amountCol-2).getKey();
                System.out.println("The key position:"+(key));
                if (!this.levelArr.get(this.charPos-this.amountCol-2).getKey().equals("_") && !this.levelArr.get(this.charPos-this.amountCol-2).getKey().equals("|")){
                    System.out.println("Got to w with;"+(this.charPos-this.amountCol-2));
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                
                this.curLabel = labelArr.get(this.charPos);
                labelArr.set(this.charPos,this.prevLabel);
                
                
                this.charPos-= this.amountCol+2;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
                
                
                this.prevLabel = labelArr.get(this.charPos);
                labelArr.set(this.charPos,this.curLabel);
                this.didMove = true;
                    
                    }// End of the condition of barriers
            }
        }
     return labelArr;
    }
    
    public boolean didMove(){
        return this.didMove;
    }
    
    public ArrayList<Block> getBlockList(){
        return this.levelArr;
    }
    
    public void changeLevel(){
        this.currentLevel++;
        updateCharacter();
        this.charPos = this.sc.getCharPosition();
        
    }
    
    
    // Basically a way to get the neeeded description
    public String getDescription(){
        return this.prevBlock.getDescription();
    }
    
    public String getKey(){
        return this.prevBlock.getKey();
    }
    
    // Starting to place a block into inventory
    public ArrayList<Block> putInInventory(Block character,Block b){
        if (character instanceof Character){
            Character c = (Character)character;
            c.addToInventory(b);
            sc.setCharacter(c);
            return c.getInventory();
        }else{
        return null;
            }
    }
    
    // A method to parse the inventory
    public ArrayList<MenuButton> parseInventory(ArrayList<Block> inventory, Stage primaryStage, GridPane infoDeck, Label curDescription, Label curHeader){
        ArrayList<MenuButton> l = p.parseInventory(inventory,primaryStage,infoDeck,curDescription,curHeader);
        return l;
    }
    
    public ArrayList<Block> getInventory (Block character){
        if (character instanceof Character){
            return ((Character)character).getInventory();
        }else{
            return null;
        }
    }
    
    
    public void updateCharacter(){
        Character newC = sc.getCharacter();
        this.levelArr.set(this.charPos,newC);
        this.sc.setCharacter(newC);
        this.p.setChar(newC);
        
    }
    
    // A method to move the blocks that are movable
    public void moveBlocks(GridPane gp){
        
        ArrayList<MovableBlock> mb = this.p.getMovableBlocks();
        ArrayList<Label> ml = this.p.getMovableLabels();
        for (int i = 0; i<= mb.size()-1;i++){
           
            System.out.println("Moving Blocks!");
            int prevPos = mb.get(i).getPos();
            System.out.println("The prevPos: "+prevPos);
            
            
            // Trying to move right
            if (prevPos+1 <= this.levelArr.size()-1){
                //if (!this.levelArr.get(prevPos+1).getKey().equals("_") && !this.levelArr.get(prevPos+1).getKey().equals("|")){
                    
                    
                    //Block charBlock = this.levelArr.get(prevPos+1);
                    //this.levelArr.set(prevPos,charBlock);
                   
                    mb.get(i).moveRight();
                    int newPos = mb.get(i).getPos();
                    //this.levelArr.set(newPos,mb.get(i));
                    System.out.println("The newPos: "+newPos);
                    
                    Node n = ml.get(i).getGraphic();
                    n = translateRight(n);
                    n.setManaged(false);
                    ml.get(i).setGraphic(n);
            
                    
                    
                    
                    //}
                
                
            }
  
          
            
        }
        this.p.setMovableBlocks(mb);
        this.p.setMovableLabels(ml);
        
    }
    
    
    public Node translateRight(Node n) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
        
        
        //int x = n.getX();
        double toX = (int)x+10.0;
        int toY = (int)y;
        
        //n.setScaleX(2);
        //n.relocate(toX,toY);
        n.setLayoutX(x+10);
      
        
        
        double x2 = n.getLayoutX();
        System.out.println("what th layout is: "+x2);
        
        
        
      
        return n;
    }
    
  
    
    public static class MoveToAbs extends MoveTo {

            public MoveToAbs(Node node) {
                super(node.getLayoutBounds().getWidth() / 2, node.getLayoutBounds().getHeight() / 2);
            }

            public MoveToAbs(Node node, double x, double y) {
                super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2, y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
            }
        }
        
        public static class LineToAbs extends LineTo {

                public LineToAbs(Node node, double x, double y) {
                    super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2, y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
                }

            }

        
    
    
    
    
}


