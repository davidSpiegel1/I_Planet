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

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;

import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;

import javafx.scene.input.MouseEvent;

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
    private Stage mainStage;
    
    // Menu Items
    private MenuButton statButton;
    private MenuItem statChar;
    private MenuItem statMap;
    private MenuItem statInventory;
    
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
        
        mainStage = stage;
        
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
        
        statButton = constructStats();
        
        statButton.setId("important");
        
        infoDeck.add(curDescription,2,0);
        infoDeck.add(curHeader,1,0);
        infoDeck.add(statButton,3,0);
        
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
    
        Timeline tl = new Timeline(
          new KeyFrame(
              Duration.millis(1000),
                  event -> {
                      
                      c.moveBlocks(mainGame);
                      
                      if (c.getPrevBlock().getKey().equals("W")){
                          
                          Label l1 = labelList.get(0);
                          Node n = l1.getGraphic();
                          //l1.setStyle("-fx-background-color: black;");
                          // Manipulate node
                          n.setStyle("-fx-background-color: #E9927E;");
                          FadeTransition ft = new FadeTransition(Duration.millis(3000), n);
                          ft.setFromValue(0.5);
                          ft.setToValue(1.0);
                          ft.setCycleCount(1);
                          //ft.setAutoReverse(true);
                          ft.setOnFinished((e) -> {
                              n.setStyle("-fx-background-color: #C7C7C7;");
                              

                                           });
                          ft.play();
                          
                          
                          
                          
                          Character c1 = (Character)c.getCurBlock();
                          c1.decrementLife();
                          c.setCurBlock(c1);
            
                          // Later stuff
                          displayCharInfo();
                      }
                      if (((Character)c.getCurBlock()).getLife() == 0){
                          System.out.println("Game Over!!!");
                          
                          Label l1 = labelList.get(0);
                          Node n = l1.getGraphic();
                          n.setStyle("-fx-background-color: #E9927E;");
                          
                          RotateTransition rt = new RotateTransition(Duration.millis(300), n);
                          rt.setByAngle(90);
                          rt.setCycleCount(1);
                          rt.setAutoReverse(true);
                          
                          rt.setOnFinished((e) -> {
                                               
                              displayGameOver(stage);

                                           });
                          rt.play();
                              
                
                      
                          }
                      
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
                    // Gonna have a move char3 that we will try and use
                    String move = "s";
                    labelList = c.moveChar3(move,labelList); // Move char 3 will take a string for move and the list of blocks
                    int curPos = c.getCharPos();
                    translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    
                    
                    
                    /*System.out.println("Here!");
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
                            
                        }*/
                        
                    
                }
                if (e.getCode().equals(KeyCode.RIGHT)) {
                    
                    String move = "d";
                    labelList = c.moveChar3(move,labelList);
                    int curPos = c.getCharPos();
                    translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    /*System.out.println("Here!");
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
                    }*/
                    
                }
                if (e.getCode().equals(KeyCode.UP)) {
                    
                    String move = "w";
                    labelList = c.moveChar3(move,labelList);
                    int curPos = c.getCharPos();
                    translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    /*System.out.println("Here!");
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
                        }*/
                }
                if (e.getCode().equals(KeyCode.LEFT)) {
                    
                    
                    String move = "a";
                    labelList = c.moveChar3(move,labelList);
                    int curPos = c.getCharPos();
                    translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    /*System.out.println("Here! left");
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
                }*/
                     
                     }
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

                    Background br = new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY));
                    
                    
                    c.setPrevBlock(b);
                    c.setPrevLabelBackground(br);
                    
                    int curPos = c.getCharPos();
                    Label curLabel = labelList.get(curPos);
                    
                    //Node n = curLabel.getGraphic();
                    Node n = labelList.get(0).getGraphic();
                    grabAnimation(n);
                    //translateLeftRight(n, .6, 0, 1, 1, 0);
                
                    curLabel.setGraphic(null);
                    ArrayList<Block> inventory = c.putInInventory(curB,prevB);
                    System.out.println(inventory);
                    
                    // Doing display stuff
                    curHeader.setText("Inventory: ");
                    curDescription.setText("");
                    ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,infoDeck,curDescription,curHeader);
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
                    ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,infoDeck,curDescription,curHeader);
                    for (int i = 0; i<= inventoryButtons.size()-1;i++){
                        infoDeck.add(inventoryButtons.get(i),i+2,0);
                        
                    }
                    
                }
                if (e.getCode().equals(KeyCode.C)){
                    // For the character info!!
                    System.out.println("Characer question requested: ");
                    displayCharInfo();
                    
                    
                    
                    
                }
            }
        });
        stage.setScene(scene);
        stage.show();
	}
    
    public void grabAnimation(Node n){
        
        
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(60);
        rt.setToAngle(0);
        //rt.setCycleCount(2);
        //rt.setAutoReverse(true);
        
       
     
        
        ScaleTransition st = new ScaleTransition(Duration.millis(290),n);
        st.setFromX(.01);
        st.setFromY(1);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(200),n);
        st2.setFromX(1.3f);
        st2.setFromY(1);
        st2.setToX(1);
        st2.setToY(1);
        st2.setCycleCount(1);
        
  
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st2,rt);
        //st2.play();
        
        parallelTransition.setCycleCount(1);
        /*parallelTransition.setOnFinished((e) -> {
                             
            //RotateTransition r3 = new RotateTransition(Duration.millis(50), n);
            r3.setByAngle(-90);
            r3.setCycleCount(1);
            rt.setCycleCount(2);
            rt.setAutoReverse(true);

            });*/

        parallelTransition.play();
        //st2.play();
        
        
        
    }
    
    
    
    /*
     Something to help with the end of the game!!
     Should return the 'controller' as a new controller with nothing there
     FIX!! Make prettier!!
     
     
     */
    public Controller displayGameOver(Stage stage1) {
        stage1 = new Stage();
        mainStage = stage1;
        Label l2 = new Label("You Lost :(");
        Button b1 = new Button("Play again?");


        l2.setFont(new Font("ariel", 50));
        l2.setStyle(
                "-fx-border-color: GREY;" +
                    "-fx-border-radius: 10.0;" +
                    "-fx-border-width: 5;" +
                    "-fx-text-fill: red;"+
                    "-fx-background-color: grey;");

        l2.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(6.0), Insets.EMPTY)));

        // l2.setMaxWidth(Double.MAX_VALUE);
        l2.setAlignment(Pos.BASELINE_LEFT);

        b1.setFont(new Font("ariel", 50));
        b1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 10.0;" + "-fx-border-width: 5;"
                + "-fx-text-fill: green");
        b1.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(6.0), Insets.EMPTY)));
        b1.setAlignment(Pos.BASELINE_RIGHT);
        // What happends if you start the game over
        b1.setOnAction((e) -> {
            
            mainStage.close();

        });

        VBox vbox2 = new VBox(3, l2, b1);
        vbox2.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), CornerRadii.EMPTY, Insets.EMPTY)));
        vbox2.setAlignment(Pos.CENTER);
        Scene sc1 = new Scene(vbox2, SCENE_SIZE_ROW, SCENE_SIZE_COL);

        stage1.setScene(sc1);
        stage1.setTitle("Game Over");
        stage1.show();
        
        return null; // WIll return the controller value
    }
    
    public GridPane clearButtons(GridPane gp){
        
        ArrayList<MenuButton> bl = new ArrayList<MenuButton>();
        
        for (Node child: gp.getChildren()){
        if (child != null){
            if (child instanceof MenuButton){
                
            
                //gp.getChildren().remove(child);
                // mainGame.getChildren().remove(c.getCurLabel());
            
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
    
    // To construct the proper stats for the game
    public MenuButton constructStats(){
        
        // The status of everything button
        statButton = new MenuButton("=");
        statButton.setAlignment(Pos.BASELINE_RIGHT);
      
        statButton.setFocusTraversable(false);
        statButton.setStyle("-fx-background-color: #2A0043;"+
                "-fx-text-fill: cadetblue;" +
                "-fx-font-family: Courier New;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");
    
        statButton.getStylesheets().add("/model/myButton.css");
        
        // Setting the map, character, and inventory options
        statMap = new MenuItem("Map");
        statMap.setId("important");
    
        statMap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Map pressed!");
               	displayMap(); 
            }
        });
        
        
        statChar = new MenuItem("Char info");
        statChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Char info pressed!");
                displayCharInfo();
                
            }
        });
        statInventory = new MenuItem("Inventory");
        statInventory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                
                System.out.println("Inventory pressed!");
                infoDeck = clearButtons(infoDeck);
                curHeader.setText("Inventory: ");
                curDescription.setText("");
                
                Block curB = c.getCurBlock();
                ArrayList<Block> inventory = c.getInventory(curB);
                ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,infoDeck,curDescription,curHeader);
                for (int i = 0; i<= inventoryButtons.size()-1;i++){
                    infoDeck.add(inventoryButtons.get(i),i+2,0);
                    
                }
                
            }
        });
        
        
        
        statButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                statButton.setStyle("-fx-background-color: #2A0043;"+
                        "-fx-text-fill: cadetblue;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;");
            }
            
        });// For exiting the button
                                    
                                    
        statButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                statButton.setStyle("-fx-background-color: cadetblue;"+
                        "-fx-text-fill: cornflowerblue;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;");
            }
        });
        
        // Adding options to statButton
        statButton.getItems().add(statMap);
        statButton.getItems().add(statChar);
        statButton.getItems().add(statInventory);
        
        return statButton;
        
        
    }

    public void displayMap()
    {
	MapGUI2 mapInfo = new MapGUI2();
	Stage mapWindow = new Stage();

	System.out.println("Current Character Position: " + c.getCharPos());
	mapInfo.start(mapWindow, c.getCharPos(), c.getCurrentLevel());
	//mapInfo.start(this.stage1);
    }
    
    
    public void displayCharInfo(){
        infoDeck = clearButtons(infoDeck); //
        curHeader.setText(" ");
        String charLife = "";
        Character ci = (Character)c.getCurBlock();
        String charName = ci.getName();
        for (int p = 0; p<= 9;p++){
            if (p <= ci.getLife()-1){
                charLife += ".";
            }else{
                charLife += "_";
            }
        }
        curHeader.setText(charName);
        curDescription.setText(charLife);
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





