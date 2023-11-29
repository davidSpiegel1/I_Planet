// New Controller class
package controller;
import java.util.*;
import java.io.*;
import model.Block;
import model.Character;



//import model.Environment;
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
import javafx.scene.input.MouseEvent;
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
import model.Enemies;
import model.Animal;
import model.Gabriel;
import model.Devil;
import model.Edog;
import model.Alyosha;
import model.SaveEngine;
import view.AnimateEngine;

import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.animation.Timeline;
//import javafx.scene.shape.*;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.Region;

//import javafx.scene.TextInputDialog;

// The class of the controller
public class Controller2{
    
    private int currentLevel;
    private int currentStoryBeat;
    private boolean isGameOver;
    private int charPos;
    private int amountCol;
    private int ogCharPos;
    private Tooltip globalTool;
    private Block prevBlock = null;
    private Gabriel gabrielBlock = null;
    private Devil devilBlock = null;
    private boolean didMove = false;// An instance variable to check if the person moved or not
    private boolean inRoom = false;
    private boolean newGame = true;
    
    private ArrayList<Block>levelArr; // list of blocks for the level we are on
    
    
    private ArrayList<String>levelNames; // List of level names
    private ArrayList<String>levelR;
    
    private Scan sc; // Scanner object
    private Parse p; // Parse object
    
    // Save engine
    private SaveEngine saveEngine;
    // A Label for current label
    private Label curLabel;
    private Label prevLabel;
    
    // Labels for description and header
    private Label curHeader;
    private Label curDescription;
    
    private AnimateEngine ag; // Animate Engine instance variable
    private GridPane gp2;
    private MenuButton statButton;
    
    // Maybe have a boolean for each task
    private boolean taskOne = false;
    private boolean taskTwo = false;
    private boolean taskThree = false;
    private int taskNum = 1;
    private ArrayList<String>currentTask;
    
    
    public Controller2(){
        // Us initializing everything
        this.currentLevel = 0; // The current level
        this.charPos = 0;
        this.ogCharPos = 0;
        this.prevBlock = null;
        this.amountCol = 20;
        this.isGameOver = false;
        this.levelNames = new ArrayList<String>();
        
        this.levelR = new ArrayList<String>();
        p = new Parse(); // parser object intiated
    
        this.levelNames = this.populateLevelNames(this.levelNames);
        this.currentTask = this.populateCurrentTask();
        
        // Initalizing the current label and the previous label
        this.curLabel = null;
        this.prevLabel = null;
        this.saveEngine = new SaveEngine();
        
        
    }
    
    public Parse getParser(){
        return p;
    }

    public Scan getScanner(){
        return sc;
    }
    public ArrayList<String> populateCurrentTask(){
        ArrayList<String> curT = new ArrayList<String>();
        
        curT.add("Must defeat the devil. Then you can escape.");
        curT.add("Must fight jesus, idk..");
        
        return curT;
    }
    
    public String getCurrentTask(){
        return currentTask.get(taskNum-1);
    }


    
    //Retrieve where character is
    public int getCurrentLevel()
    {
	return this.currentLevel;
    }

    
    public void setAnimateEngine(AnimateEngine ag){
        this.ag = ag;
        this.p.setAnimateEngine(ag);

    }
    
    
   public ArrayList<String> getLevelNames(){
       return null;
   }
    
    public ArrayList<String> populateLevelNames(ArrayList<String> arr){
        /*arr.add("model/testFile.txt");
        arr.add("utilities/levels/1.txt");
        arr.add("utilities/levels/2.txt");
        arr.add("utilities/levels/3.txt");
        arr.add("utilities/levels/4.txt");
        arr.add("utilities/levels/5.txt");
        arr.add("utilities/levels/6.txt");
        arr.add("utilities/levels/7.txt");
        arr.add("utilities/levels/8.txt");
        arr.add("utilities/levels/9.txt");
        arr.add("utilities/levels/10.txt");
        arr.add("utilities/levels/11.txt");
        arr.add("utilities/levels/12.txt");*/
        arr.add("utilities/levels/13.txt");
        arr.add("utilities/levels/14.txt");
        arr.add("utilities/levels/15.txt");
        arr.add("utilities/levels/16.txt");
        arr.add("utilities/levels/17.txt");
        arr.add("utilities/levels/18.txt");
        
        
        /*this.levelR.add("..");
        this.levelR.add("utilities/rooms/roomOneA.txt");
        this.levelR.add("utilities/rooms/roomTwoA.txt");
        this.levelR.add("utilities/rooms/roomThreeA.txt");
        this.levelR.add("utilities/rooms/roomFourA.txt");
        this.levelR.add("utilities/rooms/roomFiveA.txt");
        this.levelR.add("utilities/rooms/roomSixA.txt");
        this.levelR.add("utilities/rooms/roomSevenA.txt");
        this.levelR.add("utilities/rooms/roomEightA.txt");
        this.levelR.add("utilities/rooms/roomNineA.txt");
        this.levelR.add("utilities/rooms/roomTenA.txt");
        this.levelR.add("utilities/rooms/roomElevenA.txt");
        this.levelR.add("utilities/rooms/roomTwelveA.txt");*/
        this.levelR.add("utilities/rooms/roomThirteenA.txt");
        this.levelR.add("utilities/rooms/roomFourteenA.txt");
        this.levelR.add("utilities/rooms/roomFifteenA.txt");
        this.levelR.add("utilities/rooms/roomSixteenA.txt");
        this.levelR.add("utilities/rooms/roomSeventeenA.txt");
        this.levelR.add("utilities/rooms/roomEighteenA.txt");
        
        
        
     
        
        return arr;
        
    }
    
    public int getAmountCol(){
        return this.amountCol;
    }
    public ArrayList<Block> constructMap(){
        String levelName = "";
       /* if (newGame){
        levelName = this.levelNames.get(this.currentLevel); // Getting our level name
        }else{*/
            newGame = false;
            levelName = newPath(this.levelNames.get(this.currentLevel));
        //}
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
    public String newPath(String levelName){
        String newPath = "./utilities/userData/"+levelName.split("/")[2];
        File file = new File(newPath);
        if (!file.exists()){
            newPath = levelName;
        }
        return newPath;
    }
    
    
    public ArrayList<Block> constructMap2(){
     
        String levelName = this.levelR.get(this.currentLevel); // Getting our level name
     
 
        System.out.println("The level name: "+levelName);
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
    
    public void setCurBlock(Block b){
        this.levelArr.set(this.charPos,b);
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
    
    public void setCharPos(int pos){
        this.charPos = pos;
    }
    
    public ArrayList<Block> getBlockList(){
        return this.levelArr;
    }
    
    public void changeLevel(){
        
        saveEngine.saveData(this.levelNames.get(this.currentLevel),this.levelArr);
        
        this.inRoom = false;
        if (this.currentLevel <= this.levelNames.size()-1){
        this.currentLevel++;
        }else{
            this.currentLevel = 0;
        }
        updateCharacter();
        this.charPos = this.sc.getCharPosition();
        
    }
    
    public void changeToRoom(){
        this.inRoom = true;
        updateCharacter();
        this.charPos = this.sc.getCharPosition();
    }
    public boolean inRoom(){
        return this.inRoom;
    }
    
    public void changeLevel(int valz){
        
        saveEngine.saveData(this.levelNames.get(this.currentLevel),this.levelArr);
        
        if (this.currentLevel+valz <= this.levelNames.size()-1 && this.currentLevel+valz >= 0){
        this.currentLevel= currentLevel+valz;
        }else{
            this.currentLevel = 0;
        }
        updateCharacter();
        this.charPos = this.sc.getCharPosition();
        
    }
    
    
    public boolean isSaved(){
        return saveEngine.isDir();
    }
    
    public void setNewGame(boolean newGame){
        if (newGame == true){
            saveEngine.clearDirectory();
        }
        this.newGame = newGame;
        
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
    public ArrayList<MenuButton> parseInventory(ArrayList<Block> inventory,GridPane infoDeck, Label curDescription, Label curHeader){
        this.curDescription = curDescription;
        this.curHeader = curHeader;
        ArrayList<MenuButton> l = p.parseInventory(inventory,infoDeck,curDescription,curHeader);
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
    public void updateCharacter(Character c){
        Character newC = c;
        this.levelArr.set(this.charPos,newC);
        this.sc.setCharacter(newC);
        this.p.setChar(newC);
        
    }
    
    
    public void decrementFound(ArrayList<Label> labelList){
        ArrayList<MovableBlock> mb = this.p.getMovableBlocks();
        ArrayList<Label> ml = this.p.getMovableLabels();
        
        // Lopping through movable objects to find one close
        for (int i = 0; i<= mb.size()-1;i++){
            System.out.println("The object: "+mb.get(i).getKey()+", The life: "+mb.get(i).getLife());
            System.out.println("The object i: "+mb.get(i).getPos()+", The char i: "+this.charPos);
            if (mb.get(i).getPos()==this.charPos){
                System.out.println("The object: "+mb.get(i).getKey()+", The life: "+mb.get(i).getLife());
                mb.get(i).decrementLife();
                ag.hitAnimation(ml.get(i).getGraphic());
                if (mb.get(i).getLife() <= 0){
                    ag.deadAnimation(ml.get(i).getGraphic());
                    // Okay, so here is where we will try and construct a flower goming out
                    int cur = mb.get(i).getPos();
                    Block newBlock = null;
                    Button newLabel = null;
                    if (mb.get(i).getLife() == 0){
                        if (mb.get(i) instanceof Enemies){
                            newBlock = sc.findBlock("f");
                            newLabel = p.buttonBuilder("f");
                        }else{
                            newBlock = sc.findBlock("]");
                            newLabel = p.buttonBuilder("]");
                        }
                        //levelArr.set(cur,flowerBlock);
                        this.prevBlock = newBlock;
                        //this.prevLabel.setGraphic(newLabel);
                        labelList.get(cur).setGraphic(newLabel);
                        //((Button)ml.get(i).getGraphic()).setGraphic(flowerLabel);
                        }
                }
            }
            
        }
        
    }
    
    public void determineTalk(){
        ArrayList<MovableBlock> mb = this.p.getMovableBlocks();
        ArrayList<Label> ml = this.p.getMovableLabels();
        
        // Lopping through movable objects to find one close
        for (int i = 0; i<= mb.size()-1;i++){
            
            // For Gabriel
            if (mb.get(i).getPos()==this.charPos && mb.get(i) instanceof Gabriel){
                System.out.println("The object: "+mb.get(i).getKey()+", The life: "+mb.get(i).getLife());
                System.out.println("Talking to gabriel!!");
                //mb.get(i).decrementLife();
                
                gabrielBlock = (Gabriel)mb.get(i);
                Button b9 = (Button)ml.get(i).getGraphic();
                //Text t = new Text();
                //t.setText(gabrielBlock.getComment()+"\n"+gabrielBlock.getOptionOne()+" | "+gabrielBlock.getOptionTwo());
                
                globalTool = new Tooltip("");
                VBox hb = new VBox();
                Label comment = new Label(gabrielBlock.getComment().strip());
                Label response1 = new Label(gabrielBlock.getOptionOne().strip());
                Label response2 = new Label(gabrielBlock.getOptionTwo().strip());
                comment.setStyle("-fx-text-fill: red;");
                response1.setStyle("-fx-text-fill: blue;");
                response2.setStyle("-fx-text-fill: blue;");
                
                response1.setMinWidth(Region.USE_PREF_SIZE);
                response2.setMinWidth(Region.USE_PREF_SIZE);
                comment.setMinWidth(Region.USE_PREF_SIZE);
                hb.getChildren().addAll(comment,response1,response2);
                
                globalTool.setAutoHide(false);
                globalTool.setGraphic(hb);
                //tp.show(b9,b9.getLayoutX(),b9.getLayoutY());
                globalTool.setMinWidth(Region.USE_PREF_SIZE);
                globalTool.setMinHeight(Region.USE_PREF_SIZE);
                //globalTool.setMaxWidth((gabrielBlock.getComment().length()*12)+200);
                //globalTool.setMinHeight(gabrielBlock.getComment().length()*7);
                //globalTool.setMaxHeight(gabrielBlock.getComment().length()*12);
                globalTool.setStyle("-fx-font: normal bold 20 Langdon; "
                    + "-fx-base: #BDB4D0; "
                    + "-fx-text-fill: white;");
                
           
                    System.out.println(b9.getTooltip());
                globalTool.setOnShowing(s->{
                        Bounds bounds = b9.localToScreen(b9.getBoundsInLocal());
                    globalTool.setX(bounds.getMaxX());
                    globalTool.setY(bounds.getMinY());
                        
                    });
                    
                globalTool.show(b9,b9.getLayoutX(),b9.getLayoutY());
                b9.setDisable(false);
                b9.setFocusTraversable(true);
                b9.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        
              
                        if (e.getCode().equals(KeyCode.LEFT)) {
                            
                            gabrielBlock.goLeft();
                            //globalTool.setText(gabrielBlock.getComment()+"\n"+gabrielBlock.getOptionOne()+" | "+gabrielBlock.getOptionTwo());
                            VBox hb = new VBox();
                            Label comment = new Label(gabrielBlock.getComment().strip());
                            Label response1 = new Label(gabrielBlock.getOptionOne().strip());
                            Label response2 = new Label(gabrielBlock.getOptionTwo().strip());
                            
                            comment.setStyle("-fx-text-fill: red;");
                            response1.setStyle("-fx-text-fill: blue;");
                            response2.setStyle("-fx-text-fill: blue;");
                            
                            response1.setMinWidth(Region.USE_PREF_SIZE);
                            response2.setMinWidth(Region.USE_PREF_SIZE);
                            comment.setMinWidth(Region.USE_PREF_SIZE);
                            hb.getChildren().addAll(comment,response1,response2);
                            globalTool.setGraphic(hb);
                            globalTool.setMinWidth(Region.USE_PREF_SIZE);
                            globalTool.setMinHeight(Region.USE_PREF_SIZE);
                            
                       
                        }
                        else if (e.getCode().equals(KeyCode.RIGHT)){
                            gabrielBlock.goRight();
                            
                            
                            //globalTool.setText(gabrielBlock.getComment()+"\n"+gabrielBlock.getOptionOne()+" | "+gabrielBlock.getOptionTwo());
                            VBox hb = new VBox();
                            System.out.println("The comment: "+gabrielBlock.getComment());
                            Label comment = new Label(gabrielBlock.getComment().strip());
                            Label response1 = new Label(gabrielBlock.getOptionOne().strip());
                            Label response2 = new Label(gabrielBlock.getOptionTwo().strip());
                            
                            comment.setStyle("-fx-text-fill: red;");
                            response1.setStyle("-fx-text-fill: blue;");
                            response2.setStyle("-fx-text-fill: blue;");
                            
                            response1.setMinWidth(Region.USE_PREF_SIZE);
                            response2.setMinWidth(Region.USE_PREF_SIZE);
                            comment.setMinWidth(Region.USE_PREF_SIZE);
                            hb.getChildren().addAll(comment,response1,response2);
                            globalTool.setGraphic(hb);
                            globalTool.setMinWidth(Region.USE_PREF_SIZE);
                            globalTool.setMinHeight(Region.USE_PREF_SIZE);
                        }
                        else{
                            System.out.println("Something!!");
                            b9.setDisable(true);
                            b9.setFocusTraversable(true);
                            b9.setTooltip(null);
                            globalTool.setOpacity(0.0);
                
                            //globalTool.setGraphic(null);
                        }
                    }
                });
                
                    ag.hitAnimation(ml.get(i).getGraphic());
                    
            
   
                
                
                
            }else if (mb.get(i).getPos()==this.charPos && mb.get(i) instanceof Devil){
                System.out.println("The object: "+mb.get(i).getKey()+", The life: "+mb.get(i).getLife());
                System.out.println("Talking to Devil!!");
                //mb.get(i).decrementLife();
                
                devilBlock = (Devil)mb.get(i);
                Button b9 = (Button)ml.get(i).getGraphic();
                //Text t = new Text();
                //t.setText(gabrielBlock.getComment()+"\n"+gabrielBlock.getOptionOne()+" | "+gabrielBlock.getOptionTwo());
                
                globalTool = new Tooltip("");
                VBox hb = new VBox();
                Label comment = new Label(devilBlock.getComment().strip());
                Label response1 = new Label(devilBlock.getOptionOne().strip());
                Label response2 = new Label(devilBlock.getOptionTwo().strip());
                comment.setStyle("-fx-text-fill: red;");
                response1.setStyle("-fx-text-fill: blue;");
                response2.setStyle("-fx-text-fill: blue;");
                
                response1.setMinWidth(Region.USE_PREF_SIZE);
                response2.setMinWidth(Region.USE_PREF_SIZE);
                comment.setMinWidth(Region.USE_PREF_SIZE);
                hb.getChildren().addAll(comment,response1,response2);
                
                globalTool.setAutoHide(false);
                globalTool.setGraphic(hb);
                globalTool.setMinWidth(Region.USE_PREF_SIZE);
                globalTool.setMinHeight(Region.USE_PREF_SIZE);
                globalTool.setStyle("-fx-font: normal bold 20 Langdon; "
                    + "-fx-base: #BDB4D0; "
                    + "-fx-text-fill: white;");
                
           
                    System.out.println(b9.getTooltip());
                globalTool.setOnShowing(s->{
                        Bounds bounds = b9.localToScreen(b9.getBoundsInLocal());
                    globalTool.setX(bounds.getMaxX());
                    globalTool.setY(bounds.getMinY());
                        
                    });
                    
                globalTool.show(b9,b9.getLayoutX(),b9.getLayoutY());
                b9.setDisable(false);
                b9.setFocusTraversable(true);
                b9.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        
              
                        if (e.getCode().equals(KeyCode.LEFT)) {
                            
                            devilBlock.goLeft();
                            //globalTool.setText(gabrielBlock.getComment()+"\n"+gabrielBlock.getOptionOne()+" | "+gabrielBlock.getOptionTwo());
                            VBox hb = new VBox();
                            Label comment = new Label(devilBlock.getComment().strip());
                            Label response1 = new Label(devilBlock.getOptionOne().strip());
                            Label response2 = new Label(devilBlock.getOptionTwo().strip());
                            
                            comment.setStyle("-fx-text-fill: red;");
                            response1.setStyle("-fx-text-fill: blue;");
                            response2.setStyle("-fx-text-fill: blue;");
                            
                            response1.setMinWidth(Region.USE_PREF_SIZE);
                            response2.setMinWidth(Region.USE_PREF_SIZE);
                            comment.setMinWidth(Region.USE_PREF_SIZE);
                            hb.getChildren().addAll(comment,response1,response2);
                            globalTool.setGraphic(hb);
                            globalTool.setMinWidth(Region.USE_PREF_SIZE);
                            globalTool.setMinHeight(Region.USE_PREF_SIZE);
                            
                       
                        }
                        else if (e.getCode().equals(KeyCode.RIGHT)){
                            devilBlock.goRight();
                            //globalTool.setText(gabrielBlock.getComment()+"\n"+gabrielBlock.getOptionOne()+" | "+gabrielBlock.getOptionTwo());
                            VBox hb = new VBox();
                            System.out.println("The comment: "+devilBlock.getComment());
                            Label comment = new Label(devilBlock.getComment().strip());
                            Label response1 = new Label(devilBlock.getOptionOne().strip());
                            Label response2 = new Label(devilBlock.getOptionTwo().strip());
                            
                            comment.setStyle("-fx-text-fill: red;");
                            response1.setStyle("-fx-text-fill: blue;");
                            response2.setStyle("-fx-text-fill: blue;");
                            
                            response1.setMinWidth(Region.USE_PREF_SIZE);
                            response2.setMinWidth(Region.USE_PREF_SIZE);
                            comment.setMinWidth(Region.USE_PREF_SIZE);
                            hb.getChildren().addAll(comment,response1,response2);
                            globalTool.setGraphic(hb);
                            globalTool.setMinWidth(Region.USE_PREF_SIZE);
                            globalTool.setMinHeight(Region.USE_PREF_SIZE);
                        }
                        else{
                            System.out.println("Something!!");
                            b9.setDisable(true);
                            b9.setFocusTraversable(true);
                            b9.setTooltip(null);
                            globalTool.setOpacity(0.0);
                        }
                    }
                });
                    ag.hitAnimation(ml.get(i).getGraphic());
            }
        }
        
    }
    
    // A method to move the blocks that are movable
    public void moveBlocks(GridPane gp, ArrayList<Label> labelList,GridPane gp2, MenuButton statButton){
        this.gp2 = gp2;// Setting instance variable to that
        this.statButton = statButton;
        
        if (p.getPlaced()){
            Block b = p.getPlacedBlock();
            String key = b.getKey();
            System.out.println("THE KEY OF THE PLACED BLOCK: "+key);
            //Background background = p.findBackGround(key);
            
            Button graphic = p.buttonBuilder(key);
            labelList.get(this.charPos).setGraphic(graphic);
            this.prevBlock = b;
            p.placeBlockControl(null,false);
            
        }
        
        if (p.getUsedNode() != null){
            System.out.println("Not null!!");
            //this.curLabel.getChildren().add(new Label("h"));
            Button n1 = (Button)labelList.get(0).getGraphic();
            // Trying hbox
            //TwoGraphics tg = new TwoGraphics(n1,n2);
            Button n2 = new Button();
            String p1 = p.getUsedNode();
            System.out.println("What p1 is: "+p1);
      
            
            if (p1.equals("K") || p1.equals("$")){
            
                Character c4 = (Character)this.getCurBlock();
                c4.setStick(true);
                this.setCurBlock(c4);
                
                //n2.getStylesheets().add("/utilities/skins/knifeCss.css");
                n2 = this.p.buttonBuilder(p.getUsedNode());
            }
            else if (p1.equals("f")){
                Character c4 = (Character)this.getCurBlock();
                c4.setStick(false);
                c4.incrementLife();
                this.setCurBlock(c4);
                p.setChar2(c4);
                
                
                
                ArrayList<MenuButton> bl = new ArrayList<MenuButton>();
                
                for (Node child: gp2.getChildren()){
                if (child != null){
                    if (child instanceof MenuButton){
                        
                        if (((MenuButton)child).getId()!= null){
                            System.out.println("The id of child: "+((MenuButton)child).getId());
                            if (!((MenuButton)child).getId().equals(((MenuButton)statButton).getId())){
                                bl.add((MenuButton)child);
                            }else{
                                System.out.println("The id of statButton: "+child.getId());
                                child.setId("important");
                            }
                            
                        }else{
                            System.out.println("The id of child: "+((MenuButton)child).getId());
                            bl.add((MenuButton)child);
                        }
                    }
                  }
                }
                
                for (int i = 0; i<= bl.size()-1;i++){
                    gp2.getChildren().remove(bl.get(i));
                }
                
                String life = "";
                for (int i = 0; i<=9;i++){
                    if (i < c4.getLife()){
                        life += ".";
                    }else{
                        life += "_";
                    }
                }
                
                curHeader.setText(c4.getName());
                curDescription.setText(life);
                
                n2 = null;
            }
            else{
                
                Character c4 = (Character)this.getCurBlock();
                c4.setStick(false);
                this.setCurBlock(c4);
                
                //n2.setText(".");
                n2 = this.p.buttonBuilder(p.getUsedNode());
            }
            if (n2 != null){
                n2.setMinWidth(10);
                n2.setMinHeight(10);
                //n2.setManaged(false);
                n2.setLayoutY(50);
            }
            n1.setGraphic(n2);

            p.setUsedNode(null);
            
        }
        else{
            System.out.println("Null!");
            
        }
        
   
        ArrayList<MovableBlock> mb = this.p.getMovableBlocks();
        ArrayList<Label> ml = this.p.getMovableLabels();
        String direction[] = {"a","s","d","w","e","r","g"};
        Random rand = new Random();
        int upperBound = 7;
        for (int i = 0; i<= mb.size()-1;i++){
            
            // Trying the enemies block
            if (mb.get(i) instanceof Enemies){
                
                boolean canHit = true;
                if (mb.get(i) instanceof Devil){
                        
                        canHit = ((Devil)mb.get(i)).getIsAngry();
                        if (mb.get(i).isDead()){
                            taskOne = true;
                        }
                
                }
                
                
                if (!mb.get(i).isDead() && canHit){
                
                System.out.println("The enemies!!");
                Enemies e1 = (Enemies)mb.get(i);
                e1.setLevelArr(this.sc.getNodeList());
                e1.updatePurpose(this.charPos);// Must update the purpose each time!
                int newPos = e1.generateNewPos();
                //System.out.println("The new position! " +newPos);
                //System.out.println("The character postion: "+this.charPos);
                ArrayList<String> moves = e1.getMoves();
                //System.out.println("The new moveis: "+moves);
              
                Node n = ml.get(i).getGraphic();

                // If moves are not empty, we do this
                if (moves.size() > 0){
                for (int p = 0; p<= moves.size()-1;p++){
                    String curMove = moves.get(p);
                    int curP1 = mb.get(i).getPos();
                    if (curP1 <= this.levelArr.size()-1){
                    n = ag.moveDueToEnv(n,/* Current Block*/ this.levelArr.get(curP1).getKey());
                    }
                    // This is kind of like looking ahead
                    if (curP1+1 <= this.levelArr.size()-1){
                        n = ag.moveDueToEnv(n,/* Current Block*/ this.levelArr.get(curP1+1).getKey());
                    }
                    if (curP1+2 <= this.levelArr.size()-1){
                        n = ag.moveDueToEnv(n,/* Current Block*/ this.levelArr.get(curP1+2).getKey());
                    }
                    if (curP1-1 >= 0){
                        n = ag.moveDueToEnv(n,/* Current Block*/ this.levelArr.get(curP1-1).getKey());
                    }
                    
                    
                    if (curMove.equals("s")){
                    n = ag.translateDown(n,labelList.get(0).getHeight());
                    }
                    else if (curMove.equals("d")){
                        ag.translateBack2(n);
                        n = ag.translateRight(n,labelList.get(0).getWidth());
                    }
                    else if (curMove.equals("w")){

                        n = ag.translateUp(n,labelList.get(0).getHeight());
                    }
                    else if (curMove.equals("a")){
                        ag.translateBack(n);
                        n = ag.translateLeft(n,labelList.get(0).getWidth());
                    }
                }
                }
                // Else, we will make bitting
                else{
                    // If the current position and enemy is same
                    System.out.println("The enemy position: "+e1.getPos()+" The char position: "+this.charPos);
                    if (e1.getPos()==this.charPos){
                        if (!(e1 instanceof Alyosha)){
                        if (!e1.getKey().equals("u")){
                        ag.grabAnimation(n);
                        }else{
                            System.out.println("Spider animation!");
                            ag.translateArmsSpider(n);
                        }
                    
                        // Then, the users life must go down
                        // If it is not Alyosha
                   
                            
                            Character c1 = (Character)this.getCurBlock();
                            c1.setGettingHit(true);
                            this.setCurBlock(c1);
                        }
                    }
                     
                    
                }
      
            
                //Node n = ml.get(i).getGraphic();
                n.setManaged(false);
                ml.get(i).toFront();
                n.toFront();
                labelList.get(0).toFront();
                labelList.get(0).getGraphic().toFront();
                //ml.get(i).toFront();
                ml.get(i).setGraphic(n);
                
                
                e1.clearMoves();
                }
                
                //}// End of is dead condition
            }else{
            
            if (!mb.get(i).isDead()){
            
            System.out.println("Moving Blocks!");
            int prevPos = mb.get(i).getPos();
            //System.out.println("The prevPos: "+prevPos);
            
            int newV2 = mb.get(i).getPos();
            boolean isTouching = (newV2==this.getCharPos());
            String dir = direction[rand.nextInt(upperBound)];
            //System.out.println("The direction: "+dir);
            
            if (dir.equals("d")){
            // Trying to move right
            if (prevPos+1 <= this.levelArr.size()-1){
                if (!this.levelArr.get(prevPos+1).getKey().equals("_") && !this.levelArr.get(prevPos+1).getKey().equals("|") && ((prevPos+1)%(this.amountCol)) != 0){
                    mb.get(i).moveRight();
                    int newPos = mb.get(i).getPos();
            
                    System.out.println("The newPos: "+newPos);
                    
                    Node n = ml.get(i).getGraphic();
                    ag.translateBack(n);
                    n = ag.translateRight(n,labelList.get(0).getWidth());
                    n.setManaged(false);
                    n.toFront();
                    ml.get(i).toFront();
                    ml.get(i).setGraphic(n);
                    labelList.get(0).toFront();
                    labelList.get(0).getGraphic().toFront();
                    dir = "e";
                }
            }
            }
            else if (dir.equals("a")){
                if (prevPos-1 <= this.levelArr.size()-1 && prevPos-1 >= 0){
                    if (!this.levelArr.get(prevPos-1).getKey().equals("_") && !this.levelArr.get(prevPos-1).getKey().equals("|")){
                        mb.get(i).moveLeft();
                        int newPos = mb.get(i).getPos();
               
                       // System.out.println("The newPos: "+newPos);
                
                        Node n = ml.get(i).getGraphic();
                        ag.translateBack2(n);
                        n = ag.translateLeft(n,labelList.get(0).getWidth());
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                        dir = "e";
                        
                    }
                } 
            }
            else if (dir.equals("w")){
                if (prevPos-this.amountCol-2 >=0){
                    
                    if (!this.levelArr.get(prevPos-this.amountCol-2).getKey().equals("_") && !this.levelArr.get(prevPos-this.amountCol-2).getKey().equals("|")){
                        
                        
                    mb.get(i).moveUp(this.amountCol);
                    int newPos = mb.get(i).getPos();
      
                    //System.out.println("The newPos: "+newPos);
                    
                    Node n = ml.get(i).getGraphic();
                    n = ag.translateUp(n,labelList.get(0).getHeight());
                    n.setManaged(false);
                    n.toFront();
                    ml.get(i).toFront();
                    ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                        dir = "e";
                    
                }
                    }
            }
            else if (dir.equals("s")){
                if (prevPos+this.amountCol+2 <= this.levelArr.size()-1){
                    
                    if (!this.levelArr.get(prevPos+this.amountCol+2).getKey().equals("_") && !this.levelArr.get(prevPos+this.amountCol+2).getKey().equals("|")){
                        
                    mb.get(i).moveDown(this.amountCol);
                    int newPos = mb.get(i).getPos();
                    //this.levelArr.set(newPos,mb.get(i));
                    System.out.println("The newPos: "+newPos);
                    
                    Node n = ml.get(i).getGraphic();
                    n = ag.translateDown(n,labelList.get(0).getHeight());
                    n.setManaged(false);
                    n.toFront();
                    ml.get(i).toFront();
                    ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                        dir = "e";
                    
                }
                    }
            }
        if (dir.equals("e")){
                    // 'e' for eating
                    int newV = mb.get(i).getPos();
            
                    //System.out.println("The level arr value for e: "+this.levelArr.get(newV).getKey());
                    String newKey = "";
                   if (newV >= 0 && newV <= this.levelArr.size()-1){
                        newKey = this.levelArr.get(newV).getKey();
                   }else{
                       newKey = ".";
                   }
                    if (newKey.equals("G")){
                        Node n = ml.get(i).getGraphic();
                        n = ag.translateEat(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    }
                    if (newKey.equals("W")){
                        Node n = ml.get(i).getGraphic();
                        ag.drinkAnimation(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    
                    }
                    if (newKey.equals("t")){
                        Node n = ml.get(i).getGraphic();
                        ag.lookUpEat(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    }
                    if (newKey.equalsIgnoreCase("g")){
                        Node n = ml.get(i).getGraphic();
                        ag.drinkAnimation(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    }
                    if (newKey.equals("K")){
                        Node n = ml.get(i).getGraphic();
                        ag.moveNatural(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    
                    }
                    if (newKey.equals("E")){
                        Node n = ml.get(i).getGraphic();
                        ag.moveNatural(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    
                    }
                    
                }else if (dir.equals("r")){
                    Node n = ml.get(i).getGraphic();
                    ag.moveNormalEye(n);
                    n.setManaged(false);
                    n.toFront();
                    ml.get(i).toFront();
                    ml.get(i).setGraphic(n);
                    labelList.get(0).toFront();
                    labelList.get(0).getGraphic().toFront();
                
                //ag.translateBack(n);
                }else if (dir.equals("g")){
                    // Maybe have just have 'g' be the 'I don't like you' movement
                    
                
                
                }
                if (isTouching){
                        System.out.println("IS touching!!");
                        Node n = ml.get(i).getGraphic();
                        ag.moveUpset(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    }else{
                   
                        Node n = ml.get(i).getGraphic();
                        ag.moveNormalEye(n);
                        n.setManaged(false);
                        n.toFront();
                        ml.get(i).toFront();
                        ml.get(i).setGraphic(n);
                        labelList.get(0).toFront();
                        labelList.get(0).getGraphic().toFront();
                    }
                }// End of is Dead condition
            }// End of enemy condition
        }
        this.p.setMovableBlocks(mb);
        this.p.setMovableLabels(ml);
    }
    
    
    // This will be the third iteration of moveChar
    public ArrayList<Label> moveChar3(String move, ArrayList<Label> labelArr){
        Node n = null;
        
        if (move.equals("s")){
            System.out.println("Testing down button!");
            if (this.charPos+this.amountCol+2 <= this.levelArr.size()-1){
                // Condition of barriers
                if (!this.levelArr.get(this.charPos+this.amountCol+2).getKey().equals("_") && !this.levelArr.get(this.charPos+this.amountCol+2).getKey().equals("|")){
                     
                    // Translating the graphic
                    n = labelArr.get(this.ogCharPos).getGraphic();
                    n = ag.translateDown(n,labelArr.get(0).getHeight());
                    ag.moveNatural(n);
                    n.setManaged(false);
                    n.toFront();
                    labelArr.get(this.ogCharPos).toFront();
                    labelArr.get(this.ogCharPos).setGraphic(n);
                    // Same as before but we will see
                    Block c = this.levelArr.get(this.charPos);
                    this.levelArr.set(this.charPos,this.prevBlock);
                    //this.curLabel = labelArr.get(this.charPos);
                    //labelArr.set(this.charPos,this.prevLabel);
                    this.charPos+= this.amountCol+2;
                    this.prevBlock = this.levelArr.get(this.charPos);
                    this.levelArr.set(this.charPos,c);
                    //this.prevLabel = labelArr.get(this.charPos);
                    //labelArr.set(this.charPos,this.curLabel);
                    this.didMove = true;
                    }
            }
        }
        else if (move.equals("d")){
            System.out.println("Testing right button!");
            if (this.charPos+1 <= this.levelArr.size()-1){
                // The condition for barriers
                if (!this.levelArr.get(this.charPos+1).getKey().equals("_") && !this.levelArr.get(this.charPos+1).getKey().equals("|")){
                    
                    if (!this.levelArr.get(this.charPos+1).getKey().equals("-") || ((Character)this.levelArr.get(this.charPos)).isJumping()){
                    n = labelArr.get(this.ogCharPos).getGraphic();
                    n = ag.translateRight(n,labelArr.get(0).getWidth());
                    ag.moveNatural(n);
                    n.setManaged(false);
                    n.toFront();
                    labelArr.get(this.ogCharPos).toFront();
                    labelArr.get(this.ogCharPos).setGraphic(n);
                    
                    Block c = this.levelArr.get(this.charPos);
                    this.levelArr.set(this.charPos,this.prevBlock);
                
                    //this.curLabel = labelArr.get(this.charPos);
                    //labelArr.set(this.charPos,this.prevLabel);
                
           
            
                    this.charPos++; // Incrementing the character position..
                    this.prevBlock = this.levelArr.get(this.charPos);
                    this.levelArr.set(this.charPos,c);
                
                    //this.prevLabel = labelArr.get(this.charPos);
                    //labelArr.set(this.charPos,this.curLabel);
                    this.didMove = true;
                        
                        }
                    
                }// End of condition of barriers
            }
        }
        else if (move.equals("w")){
            System.out.println("Testing up button!");
            if (this.charPos-this.amountCol-2 >=0){
                // Condition of barriers
                if (!this.levelArr.get(this.charPos-this.amountCol-2).getKey().equals("_") && !this.levelArr.get(this.charPos-this.amountCol-2).getKey().equals("|")){
                    System.out.println("Got to w with;"+(this.charPos-this.amountCol-2));
                
                n = labelArr.get(this.ogCharPos).getGraphic();
                n = ag.translateUp(n,labelArr.get(0).getHeight());
                ag.moveNatural(n);
                n.setManaged(false);
                n.toFront();
                labelArr.get(this.ogCharPos).toFront();
                labelArr.get(this.ogCharPos).setGraphic(n);
                    
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                
                //this.curLabel = labelArr.get(this.charPos);
                //labelArr.set(this.charPos,this.prevLabel);
                
                
                this.charPos-= this.amountCol+2;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
                
                
                //this.prevLabel = labelArr.get(this.charPos);
                //labelArr.set(this.charPos,this.curLabel);
                this.didMove = true;
                    
                    }// End of the condition of barriers
            }
            
        }
        else if (move.equals("a")){
            if (this.charPos-1 <= this.levelArr.size()-1 && this.charPos-1 >= 0){
                // Condition of barriers
                if (!this.levelArr.get(this.charPos-1).getKey().equals("_") && !this.levelArr.get(this.charPos-1).getKey().equals("|")){
                
                n = labelArr.get(this.ogCharPos).getGraphic();
                n = ag.translateLeft(n,labelArr.get(0).getWidth());
                ag.moveNatural(n);
                n.setManaged(false);
                n.toFront();
                labelArr.get(this.ogCharPos).toFront();
                labelArr.get(this.ogCharPos).setGraphic(n);
                    
                Block c = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,this.prevBlock);
                // Now just do the same for the label arr
                //this.curLabel = labelArr.get(this.charPos);
                //labelArr.set(this.charPos,this.prevLabel);
                
                
                this.charPos--;
                this.prevBlock = this.levelArr.get(this.charPos);
                this.levelArr.set(this.charPos,c);
                
                //this.prevLabel = labelArr.get(this.charPos);
                //labelArr.set(this.charPos,this.curLabel);
                
                this.didMove = true;
                }// End of condition of barriers
            }
        }
        return labelArr;
        
    }
    
    
    // Checking the task by number
    public boolean checkTask(){
        boolean isDone = false;
        if (this.taskNum == 1){
            isDone = this.taskOne;
        }else if (this.taskNum == 2){
            isDone = this.taskTwo;
        }else if (this.taskNum == 3){
            isDone = this.taskThree;
        }
        return isDone;
    }
    
    public void incrementTask(){
        this.taskNum++;
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


