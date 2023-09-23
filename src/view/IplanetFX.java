package view;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import controller.Controller;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import java.awt.*;
import java.awt.event.*;  
import javafx.beans.binding.Bindings;

import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;

//import javafx.scene.layout.StackPane;
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;

import model.Parse;
import model.Scan;
import controller.Controller2;

public class IplanetFX extends Application{

    // Some instance variables
    private GridPane mainGame;
    private GridPane infoDeck;
    private StackPane centerPane;
    
    private Label curDescription = new Label("Description"); // Description for object we hav
    private Label curHeader = new Label("Header"); // Header for what we have
    
    private ArrayList<Label> labelList;
    private Controller2 c;
    
    // Some constants
    private final int SCENE_SIZE_ROW = 715;
    private final int SCENE_SIZE_COL = 810;
    public static final String CharStyle = "/utilities/charCss.css";
    
    

	public static void main(String args[]){

		launch(args);
	}


	@Override
	public void start(Stage stage){
        
        
        
		stage.setTitle("I Planet");
        c = new Controller2(); // Initalizing the controller
        // Initalizing mainGame
        mainGame = new GridPane();
        mainGame.setBackground((new Background( new BackgroundFill(Color.rgb(42, 0, 67), CornerRadii.EMPTY, Insets.EMPTY))));
        mainGame.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;");
        infoDeck = new GridPane();
        curDescription = new Label("Description");
        curDescription.setFont(new Font("Arial", 20));
        curDescription.setStyle("-fx-text-fill: cadetblue");
        curHeader = new Label("Header");
        curHeader.setFont(new Font("Arial", 30));
        curHeader.setStyle("-fx-text-fill: cornflowerblue");
        infoDeck.add(curDescription,2,0);
        infoDeck.add(curHeader,1,0);
        
        
        
        ArrayList<Block> list = c.constructMap(); // Consturcting the map
        
      
        labelList = c.parse(list);
        int col = 0;
        int row = 0;
        int amountCol = c.getAmountCol();
        System.out.println("The columns amount: "+amountCol);
        
        
        // We will have a temp center pane
        centerPane = new StackPane(mainGame);
        centerPane.setStyle("-fx-background-color:cyan");
                    centerPane.setPrefSize(amountCol*50, labelList.size()*50);
        
        


        // Getting the board
        for (int j = 0; j<= labelList.size()-1;j++){
                Label curLabel = labelList.get(j);
            
                curLabel.prefWidthProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
                curLabel.prefHeightProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
            
            
                mainGame.add(labelList.get(j),col,row);
                
                if (col > amountCol){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
        }
       
        
  
        //mainGame.setVgap(0);
        //mainGame.setHgap(0);
        mainGame.setAlignment(Pos.BOTTOM_CENTER);
        
        infoDeck.setAlignment(Pos.BOTTOM_CENTER);
        infoDeck.setBackground(
                new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(2.0), Insets.EMPTY)));
        infoDeck.setVgap(57);
        infoDeck.setHgap(10);
        
        VBox vb = new VBox(infoDeck,centerPane);
        vb.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;");
        vb.setSpacing(0);
        vb.setFillWidth(true);
        //vb.setFillHeight(true);
        //vb.setStyle("-fx-border-radius: 6.0");
        Timeline tl = new Timeline(
          new KeyFrame(
              Duration.millis(1000),
                  event -> {
                      c.moveBlocks(mainGame);
                        }
                  ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        
        
        // We will now try and have something move
        

		
        
        
        
        Scene scene = new Scene(vb,SCENE_SIZE_ROW,SCENE_SIZE_COL);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {

                // Must update entire enviornment
                ArrayList<Block> list = null;
                Button b1 = new Button("");
                b1.getStylesheets().add(CharStyle);
                translateUp(b1, .2, 3, 1, 0, 0);
                b1.setDisable(true);
                
                //Button b2 = new Button("");
                
                if (e.getCode().equals(KeyCode.DOWN)) {
                    System.out.println("Here!");
                    int prevPos = c.getCharPos();
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("s",labelList);
                    // Did move condition
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    System.out.println("The row new should be on: "+(r1));
                    System.out.println("The col new should be on: "+(c1));
                    
               
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    mainGame.getChildren().remove(c.getPrevLabel());
                    
                    
                    // Making an animation for the previous place
                    Node n = prevLabel.getGraphic();
                    translateLeftRight(n, .6, 0, 1, 1, 0);
                    prevLabel.setGraphic(n);
                    
                    
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                    
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    newLabel.setGraphic(b1);
                    
                    mainGame.add(c.getCurLabel(),c1,r1);
                        }else{
                            
                            Label curLabel = c.getCurLabel();
                            curLabel.setGraphic(b1);
                            
                        }
                        
                    
                }
                if (e.getCode().equals(KeyCode.RIGHT)) {
                    System.out.println("Here!");
                    int prevPos = c.getCharPos();
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("d",labelList);
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    //System.out.println("The row new should be on: "+(r1));
                    //System.out.println("The col new should be on: "+(c1));
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    
                    mainGame.getChildren().remove(c.getPrevLabel());
                        
                        // Making an animation for the previous place
                        Node n = prevLabel.getGraphic();
                        translateLeftRight(n, .6, 0, 1, 1, 0);
                        prevLabel.setGraphic(n);
                        
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                    newLabel.setGraphic(b1);
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    mainGame.add(c.getCurLabel(),c1,r1);
                        }// End of did move condition
                    else{
                        Label curLabel = c.getCurLabel();
                        curLabel.setGraphic(b1);
                    }
                    
                }
                if (e.getCode().equals(KeyCode.UP)) {
                    System.out.println("Here!");
                    int prevPos = c.getCharPos();
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("w",labelList);
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    System.out.println("The row new should be on: "+(r1));
                    System.out.println("The col new should be on: "+(c1));
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    
                    mainGame.getChildren().remove(c.getPrevLabel());
                        
                        // Making an animation for the previous place
                        Node n = prevLabel.getGraphic();
                        translateLeftRight(n, .6, 0, 1, 1, 0);
                        prevLabel.setGraphic(n);
                        
                        
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                    
                    newLabel.setGraphic(b1);
                    
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    mainGame.add(newLabel,c1,r1);
                        }else{
                            Label curLabel = c.getCurLabel();
                            curLabel.setGraphic(b1);
                        }
                }
                if (e.getCode().equals(KeyCode.LEFT)) {
                    System.out.println("Here! left");
                    int prevPos = c.getCharPos();
                    if (prevPos != 0){
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("a",labelList);
                    // Did move condition
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    
                    
                    System.out.println("The row new should be on: "+(r1));
                    System.out.println("The col new should be on: "+(c1));
                        
                    if (r2 >= 0 && r1 >= 0 && c1 >= 0 && c2 >= 0){
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    
                    mainGame.getChildren().remove(c.getPrevLabel());
                        
                    // Making an animation for the previous place
                    Node n = prevLabel.getGraphic();
                    translateLeftRight(n, .6, 0, 1, 1, 0);
                    prevLabel.setGraphic(n);
                        
                        
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                
                    newLabel.setGraphic(b1);
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    
                    
                    
                    mainGame.add(c.getCurLabel(),c1,r1);
                        
                        }
                        }else{
                            Label curLabel = c.getCurLabel();
                            curLabel.setGraphic(b1);
                            
                        }
                }}
                if (e.getCode().equals(KeyCode.SPACE)) {
                    //curHeader.setText(c.get)
                    infoDeck = clearButtons(infoDeck);
                    curHeader.setText(c.getKey());
                    String deScript = c.getDescription();
                    if (deScript.equalsIgnoreCase("next level")){
                        
                        System.out.println("Doing the next level!");
                        changeLevel();
                    }else{
                    curDescription.setText(c.getDescription());
                   
                   
                    }
                    
                }
                if(e.getCode().equals(KeyCode.M) ) {
                    
                }
                if (e.getCode().equals(KeyCode.G)) {
                    // Get inventory and grab object
                    infoDeck = clearButtons(infoDeck);
                    Block prevB = c.getPrevBlock();
                    Block curB = c.getCurBlock();
                    
                    // Going to try and place dirt after grabing
                    // Could try to set prevBlock and prevLabel
                    Block b = new Block(".");
                    //Label l = new Label("");
                    Background br = new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY));
                    
                    
                    c.setPrevBlock(b);
                    c.setPrevLabelBackground(br);
                    
                    
                    
                    
                    Label curLabel = c.getCurLabel();
                    //curLabel.setBackground(new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
                    
                    Node n = curLabel.getGraphic();
                    translateLeftRight(n, .6, 0, 1, 1, 0);
                    curLabel.setGraphic(b1);
                    ArrayList<Block> inventory = c.putInInventory(curB,prevB);
                    System.out.println(inventory);
                    curHeader.setText("Inventory: ");
                    curDescription.setText("");
                    ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,stage,infoDeck,curDescription,curHeader);
                    System.out.println("The button list; "+inventoryButtons.toString());
                    for (int i = 0; i<= inventoryButtons.size()-1;i++){
                        infoDeck.add(inventoryButtons.get(i),i+2,0);
                    }
                    // Will display inventory
                    //displayInventory();
                    
                        

                }
                if (e.getCode().equals(KeyCode.I)) {
                    // Get
                    infoDeck = clearButtons(infoDeck);
                    curHeader.setText("Inventory: ");
                    curDescription.setText("");
                    
                    Block curB = c.getCurBlock();
                    ArrayList<Block> inventory = c.getInventory(curB);
                    ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,stage,infoDeck,curDescription,curHeader);
                    for (int i = 0; i<= inventoryButtons.size()-1;i++){
                        infoDeck.add(inventoryButtons.get(i),i+2,0);
                        
                    }
                    
                }
            }
        });
        stage.setScene(scene);
        stage.show();
	}
    
    
    
    public GridPane clearButtons(GridPane gp){
        
        ArrayList<MenuButton> bl = new ArrayList<MenuButton>();
        
        for (Node child: gp.getChildren()){
        if (child != null){
            if (child instanceof MenuButton){
                //gp.getChildren().remove(child);
                // mainGame.getChildren().remove(c.getCurLabel());
                bl.add((MenuButton)child);
            }
        }
            }
        
        for (int i = 0; i<= bl.size()-1;i++){
            gp.getChildren().remove(bl.get(i));
        }
        
        return gp;
    }
    
    public void changeLevel(){
        
        c.changeLevel();
        
        
        ArrayList<Block> list = c.constructMap(); // Consturcting the map
        mainGame.getChildren().clear();
        labelList.clear();
        labelList = c.parse(list);
        int col = 0;
        int row = 0;
        int amountCol = c.getAmountCol();
        System.out.println("The columns amount: "+amountCol);
        
        
        // We will have a temp center pane
        centerPane.getChildren().clear();
        centerPane.getChildren().add(mainGame);
        centerPane.setStyle("-fx-background-color:cyan");
                    centerPane.setPrefSize(amountCol*50, labelList.size()*50);
        
        


        // Getting the board
        for (int j = 0; j<= labelList.size()-1;j++){
                Label curLabel = labelList.get(j);
            
                curLabel.prefWidthProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
                curLabel.prefHeightProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
            
            
                mainGame.add(labelList.get(j),col,row);
                
                if (col > amountCol){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
        }
        
    }
    
    // Translate up to emulate movement
    public void translateUp(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        Path path = new Path();
        path.getElements().add(new MoveTo(up, down));
        path.getElements().add(new CubicCurveTo(ogDown + 20, ogDown + 5, ogUp + 10, ogUp + 18, ogDown + 5, ogUp + 18));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }
    
    public void translateLeftRight(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        
        //int x = n.getX();
        Path path = new Path();
        
        
        
        MoveTo moveTo = new MoveTo();
        moveTo.setX(10.0);
        moveTo.setY(10.0);
    
    
        ArcTo arcTo = new ArcTo();
        arcTo.setX(13.0);
        arcTo.setY(15.0);
        arcTo.setRadiusX(3.0);
        arcTo.setRadiusY(1.0);
        arcTo.setLargeArcFlag(true);
        
        path.getElements().add(moveTo);
        path.getElements().add(arcTo);
      
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }
    
    
    
    
    
    
    // FOR GRABBING AND SHOOTING REALLY GOOD!!
    /*
     
     public void translateLeftRight(Node n, double dur, double up, double down, double ogDown, double ogUp) {
         
         
         Path path = new Path();
         
         MoveTo moveTo = new MoveTo();
         moveTo.setX(0.0f);
         moveTo.setY(50.0f);
         path.getElements().add(moveTo);
     
         path.getElements().add(new QuadCurveTo(25.0f,0.0f,50.0f,50.0f));
       
         PathTransition pathTransition = new PathTransition();
         pathTransition.setDuration(Duration.seconds(dur));
         pathTransition.setNode(n);
         pathTransition.setPath(path);
         pathTransition.play();
     }
     
     
     */
    // FOR SHOOTING!!
    /*public void translateLeftRight(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        Path path = new Path();
        path.getElements().add(new MoveTo(0.0f, 0.0f));
        path.getElements().add(new HLineTo(80.0f));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }*/

}





