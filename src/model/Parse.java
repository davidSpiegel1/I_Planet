// The idea here is to make a parse object that builds a list for the gui
package model;

import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.collections.*;
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
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.scene.Cursor;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import java.awt.Robot;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;

import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.*;

import view.AnimateEngine;
import model.Animal;
import model.Gabriel;

public class Parse {

    // An instance variable of scanner
    private Scan sc;

    private ArrayList<Label> labelList = null;
    // Constants
    private final double SET_MIN_HEIGHT = 36.5;
    public static final String MainStyle = "/utilities/myCss.css";
    public static final String CharStyle = "/utilities/charCss.css";
    public static final String GrassStyle = "/utilities/grassCss.css";
    public static final String smallGrassStyle = "/utilities/smallGrass.css";
    public static final String AshStyle = "/utilities/ashPerson.css";
    public static final String JackStyle = "/utilities/jackPerson.css";
    public static final String BadStyle = "/utilities/badGuys.css";
    public static final String BrickStyle = "/utilities/brickCss.css";
    public static final String KnifeStyle = "/utilities/knifeCss.css";
    public static final String WaterStyle = "/utilities/waterCss.css";
    public static final String BlockStyle = "/utilities/blockCss.css";
    public static final String CharKnifeStyle = "/utilities/charWithKnifeCss.css";
    public static final String GunStyle = "/utilities/gunCss.css";
    public static final String cowStyle = "/utilities/cowCss.css";
    public static final String DoorStyle = "/utilities/doorCss.css";
    public static final String FireStyle = "/utilities/fireCss.css";
    public static final String BlackStyle = "/utilities/blackCss.css";
    public static final String FlowerStyle = "/utilities/flowerCss.css";
    public static final String WebStyle = "/utilities/webCss.css";
    public static final String GabrielStyle = "/utilities/gabrielCss.css";

    // Making a character object
    private Character char1;
    private boolean shouldAdd;
    private MenuButton curB;
    private ArrayList<MenuButton> menuArr;
    private int curI;
    private GridPane curPane;
    private Label curDescription;
    private Label curHeader;
    private ArrayList<MovableBlock> movingBlocks;
    private ArrayList<Label> movingLabels;
    private String usedNode = null;
    private AnimateEngine ag;

    public Parse() {
        labelList = new ArrayList<Label>();

        char1 = new Character();
        System.out.println("Parsing started..");

    }
    
    public void setChar2(Character c){
        char1 = c;
    }

    public ArrayList<Label> getList() {

        return this.labelList;
    }
    public void setAnimateEngine(AnimateEngine ag){
        this.ag = ag;
    }

    public ArrayList<Label> constructGui(ArrayList<Block> arr) {

        for (int i = 0; i <= arr.size() - 1; i++) {
            Label l1 = new Label(arr.get(i).getKey());

            if (arr.get(i).getKey().equals("C")) {
                l1.setText("C");
                // l1.setBackground(findBackGround(prevBlock.getKey()));
            }

            l1.setBackground(findBackGround(arr.get(i).getKey()));
            l1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
            l1.setMinHeight(SET_MIN_HEIGHT);
            this.labelList.add(l1);
        }
        return this.labelList;
    }

    public ArrayList<Label> constructGui(ArrayList<Block> arr, Block prevBlock) {
        
        movingBlocks = new ArrayList<MovableBlock>();// Initializing moving blocks
        movingLabels = new ArrayList<Label>();// For moving labels
        for (int i = 0; i <= arr.size() - 1; i++) {
            Label l1 = new Label();
            l1.setGraphic(null);
            if (arr.get(i).getKey().equals("C")) {
                
                l1.setBackground(findBackGround(prevBlock.getKey()));
                l1.setGraphic(buttonBuilder(arr.get(i).getKey()));
            } else if (arr.get(i) instanceof MovableBlock) {

                if (arr.get(i).getKey().equals("d") || arr.get(i).getKey().equals("p") || arr.get(i).getKey().equals("E") ||
                    arr.get(i).getKey().equals("a") ) {

                    l1.setBackground(findBackGround(arr.get(i).getKey()));
                    l1.setGraphic(buttonBuilder(arr.get(i).getKey()));

                    Timeline tl = new Timeline(
                            new KeyFrame(
                                    Duration.millis(800),
                                    event -> {
                                        
                                        Node n = l1.getGraphic();
                                        ag.translateUp2(n, .2, 3, 1, 0, 0);
                                        l1.setGraphic(n);

                                    }));
                    tl.setCycleCount(Animation.INDEFINITE);
                    tl.play();
                }
                //System.out.println(((Animal) arr.get(i)).getPos());
                movingBlocks.add((MovableBlock) arr.get(i));
                movingLabels.add(l1);

            } else {
                if (arr.get(i).getKey().equals("t") || arr.get(i).getKey().equalsIgnoreCase("G")
                        || arr.get(i).getKey().equals("_") ||
                        arr.get(i).getKey().equals("|") || arr.get(i).getKey().equals("W") || arr.get(i).getKey().equalsIgnoreCase("O") || arr.get(i).getKey().equals("K") || arr.get(i).getKey().equalsIgnoreCase("B") ||
                    arr.get(i).getKey().equalsIgnoreCase("F") ||
                    arr.get(i).getKey().equalsIgnoreCase("w") ||
                    arr.get(i).getKey().equals("a")) {
                    l1.setBackground(findBackGround(arr.get(i).getKey()));
                    l1.setGraphic(buttonBuilder(arr.get(i).getKey()));
                } else {

                    l1.setBackground(findBackGround(arr.get(i).getKey()));
        
                }
            }
      
            l1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
            l1.setMaxWidth(Double.MAX_VALUE);
            l1.setAlignment(Pos.CENTER);
            l1.setFont(new Font("Arial", 30));
            l1.setMinHeight(SET_MIN_HEIGHT);
            this.labelList.add(l1);
        }

        return this.labelList;
    }

    public Background findBackGround(String key) {
        Background b;
        if (key.equals("G")) {
            b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
        } else if (key.equals("s")) {
            b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
        } else if (key.equals("X")) {
            b = new Background(new BackgroundFill(Color.rgb(137, 110, 77), new CornerRadii(2.0), Insets.EMPTY));
        } else if (key.equals("t")) {
            b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
        } else if (key.equals("_") || key.equals("|")) {
            b = new Background(new BackgroundFill(Color.rgb(88, 103, 110), new CornerRadii(2.0), Insets.EMPTY));
        } else if (key.equals("W")) {
            b = new Background(new BackgroundFill(Color.rgb(0, 89, 160), CornerRadii.EMPTY, Insets.EMPTY));
        }
        else if (key.equals("B")){
            b = new Background(new BackgroundFill(Color.rgb(54, 66, 66), CornerRadii.EMPTY, Insets.EMPTY));
        }
        else if (key.equals("b")){
            b = new Background(new BackgroundFill(Color.rgb(64, 66, 66), CornerRadii.EMPTY, Insets.EMPTY));
        }
        else if (key.equals("F") || key.equals("w")){
            b = new Background(new BackgroundFill(Color.rgb(64, 66, 66), CornerRadii.EMPTY, Insets.EMPTY));
        }
        else if (key.equals("f")){
            b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
        }
        else if (key.equals("a")){
            b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
        }
        else {
            b = new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY));
        }
        
        return b;
    }
    
    
    

public ArrayList<MenuButton> parseInventory(ArrayList<Block> blockArr, GridPane infoDeck,
            Label curDescription, Label curHeader) {

        // Setting the character instance variable to the block arraylist inventory
        char1.setInventory(blockArr);
        
        menuArr = new ArrayList<MenuButton>();
        this.curDescription = curDescription;
        this.curHeader = curHeader;

        ArrayList<MenuButton> buttonArr = new ArrayList<MenuButton>();
        // Looping through the
   
        ArrayList<String> foundTypes = new ArrayList<String>(); // List of types found
        
        
        for (int i = 0; i <= blockArr.size() - 1; i++) {
            
            System.out.println("The class name: "+blockArr.get(i).getKey());
            if (!foundTypes.contains(blockArr.get(i).getKey())){
            
            MenuItem m1 = new MenuItem("Use");
            MenuItem m2 = new MenuItem("Remove");
            MenuButton b = new MenuButton(blockArr.get(i).getKey());
            curB = b;
            b.setId(blockArr.get(i).getKey());
            
            menuArr.add(b);
            curI = i;
            // b.setBackground(findBackGround(blockArr.get(i).getKey()));
            String color1 = "";
            String fontCol = "";
       
            if (blockArr.get(i).getKey().equals(".")) {
                color1 = "#D08770";
                fontCol = "#BF616A";
            }
            if (blockArr.get(i).getKey().equalsIgnoreCase("G") || blockArr.get(i).getKey().equals("t")) {
                color1 = "#295f4e";
                fontCol = "#6db193";
            }
            if (blockArr.get(i).getKey().equals("W")) {
                color1 = "#3F72AF";
                fontCol = "#112D4E";

            }
            if (blockArr.get(i).getKey().equals("K")){
                color1 = "#776389";
                fontCol = "#DDC5F2";
            }
            if (blockArr.get(i).getKey().equals("F")){
                    color1 = "#E0813E";
                    fontCol = "#E8A677";
            }
            if (blockArr.get(i).getKey().equals("f")){
                        color1 = "#446B5D";
                        fontCol = "#78C3A8";
            }
            if(blockArr.get(i).getKey().equalsIgnoreCase("B")){
                            color1 = "black";
                            fontCol = "grey";
            }
            if(blockArr.get(i).getKey().equals("w")){
                            color1 = "red";
                            fontCol = "black";
            }
            if(blockArr.get(i).getKey().equals("a")){
                color1 = "red";
                fontCol = "black";
            }
                

            b.setStyle("-fx-text-fill: " + fontCol + ";" +
                    "-fx-background-color: " + color1 + ";" +
                    "-fx-font-family: Courier New;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15;");

            b.getStylesheets().add("/model/myButton.css");

     
            m1.setId(blockArr.get(i).getKey());
            m2.setId(blockArr.get(i).getKey());

            m1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("Use pressed!");
                    setUsedNode(((MenuItem) e.getSource()).getId());
                    if (((MenuItem) e.getSource()).getId().equals("f")){
                        try {
                            for (int i = 0; i <= blockArr.size() - 1; i++) {
                                if (((MenuItem) e.getSource()).getId().equals(blockArr.get(i).getKey())) {
                                    char1.removeInventory(i);
                                    break;
                                }
                            }
                            System.out.println(((MenuItem) e.getSource()).getId());
                            int val = 0;
                            for (int i = 0; i <= menuArr.size() - 1; i++) {
                                if (menuArr.get(i).getId().equals(((MenuItem) e.getSource()).getId())) {
                                    System.out.println("Found the menu Item in menu Array");
                                    menuArr.get(i).setVisible(false);
                                    val = i;
                                    break;
                                }
                            }
                            menuArr.remove(val);
                    }catch (Exception el) {
                        System.err.println("Error! Could not remove inventory!" + el);
                    }
                
                    
                    
                    }
    
                    }

            });
            m2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("Remove pressed!");
                    // Must set the char and update it
                    try {
                        for (int i = 0; i <= blockArr.size() - 1; i++) {
                            if (((MenuItem) e.getSource()).getId().equals(blockArr.get(i).getKey())) {
                                char1.removeInventory(i);
                                break;
                            }
                        }
                        System.out.println(((MenuItem) e.getSource()).getId());
                        int val = 0;
                        for (int i = 0; i <= menuArr.size() - 1; i++) {
                            if (menuArr.get(i).getId().equals(((MenuItem) e.getSource()).getId())) {
                                System.out.println("Found the menu Item in menu Array");
                                menuArr.get(i).setVisible(false);
                                val = i;
                                break;
                            }
                        }
                        menuArr.remove(val);
                    } catch (Exception el) {
                        System.err.println("Error! Could not remove inventory!" + el);
                    }

                    try {

                        MenuItem val = (MenuItem) e.getSource();

                        infoDeck.getChildren().remove(val);

                    } catch (Exception en) {
                        System.err.println("Error! Could not clear info deck" + e);
                    }

                }

            });

            b.getItems().add(m1);
            b.getItems().add(m2);
                
    
            Button num = new Button("1");
          
            num.setStyle("-fx-text-fill: #303841;" +
                        "-fx-background-color: transparent;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 10;" );
            num.setAlignment(Pos.TOP_LEFT);
          
            num.setFocusTraversable(false);
                
            b.setGraphic(num);
            b.setFocusTraversable(false);

            setOnDragDetected(b);

            setOnDragDone(b);

            b.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    Button button = new Button("Some text");
                    // b.add(button);

                    String backColor = "";
                    String fontColor = "";
                    String[] styles = b.getStyle().split(";");
                    for (String style : styles) {
                        if (style.contains("-fx-background-color")) {
                            backColor = style.split(":")[1]; // the color of the button
                            System.out.println("The Back color: " + backColor);
                            // b.setStyle("-fx-background-color:"+color+";");
                        }
                        if (style.contains("-fx-text-fill:")) {
                            fontColor = style.split(":")[1]; // the color of the button
                            System.out.println("The Font color: " + fontColor);
                        }
                    }
                    b.setStyle("-fx-text-fill:" + backColor + ";" +
                            "-fx-background-color:" + fontColor + ";" +
                            "-fx-font-family: Courier New;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 15;" +
                            "-fx-mark-color: " + fontColor + ";");

                    // Gonna try and set up a stage

                    // options.show();
                }
            });

            b.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    // Setting color to this
                    String backColor = "";
                    String fontColor = "";
                    String[] styles = b.getStyle().split(";");
                    for (String style : styles) {
                        if (style.contains("-fx-background-color")) {
                            backColor = style.split(":")[1]; // the color of the button
                            System.out.println("The color: " + backColor);
                            // b.setStyle("-fx-background-color:"+color+";");
                        }
                        if (style.contains("-fx-text-fill:")) {
                            fontColor = style.split(":")[1]; // the color of the button
                            System.out.println("The color: " + fontColor);
                        }
                    }

                    b.setStyle("-fx-text-fill:" + backColor + ";" +
                            "-fx-background-color:" + fontColor + ";" +
                            "-fx-font-family: Courier New;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 15;" +
                            "-fx-mark-color: " + fontColor + ";");
                }
            });

         

                buttonArr.add(b);
                foundTypes.add(blockArr.get(i).getKey());
                
                }else{
                    
                    System.out.println("Already in list!");
                    for (int j = 0; j<= menuArr.size()-1;j++){
                        if (blockArr.get(i).getKey().equals(menuArr.get(j).getText())){
                            try{
                                Button n = (Button)menuArr.get(j).getGraphic();
                                System.out.println("The number: "+n.getText());
                                int nextNum = Integer.parseInt(n.getText())+1;
                                System.out.println("The next number: "+nextNum);
                                n.setText(Integer.toString(nextNum));
                                
                                System.out.println("Found the button for "+ menuArr.get(j).getText());
                                }catch(NumberFormatException et){
                                    System.out.println("Error Parsing integer for incrementing inventory block!! "+et);
                                }
                                
                        }
                    }
                    
                    
                    
                } // Else we say it is already contained
         
     
        }

        return buttonArr;
    }
    
    public void setUsedNode(String n){
        this.usedNode = n;
    }
    
    public String getUsedNode(){
        return this.usedNode;
    }

    public Button buttonBuilder(String type) {
        Button b1 = new Button(type);
        b1.setLayoutX(0);
        b1.setLayoutY(0);
        if (type.equals("t")) {
            // SVGPath path = new SVGPath();

            b1.getStylesheets().add(MainStyle);
            // translateBackAndForth(b1, 50, 3, 1, 0, 0);
        }
        // public void translateObject(Node n, double dur, int cyle, int howFar, boolean
        // isPoly) {
        else if (type.equals("C")) {
            b1.getStylesheets().add(CharStyle);
            // translateUp(b1, .2, 3, 1, 0, 0);

        } else if (type.equals("G")) {
            b1.getStylesheets().add(GrassStyle);
        } else if (type.equals("g")) {
            b1.getStylesheets().add(smallGrassStyle);
        } else if (type.equalsIgnoreCase("P")) {
            b1.getStylesheets().add(AshStyle);
        } else if (type.equals("s")) {
            b1.getStylesheets().add(JackStyle);
        } else if (type.equals("E")) {
            b1.getStylesheets().add(BadStyle);
        
        } else if (type.equalsIgnoreCase("O")){
            b1.getStylesheets().add(DoorStyle);
        }else if (type.equals("|") || type.equals("_")) {
            b1.getStylesheets().add(BrickStyle);
        } else if (type.equals("K")) {
            b1.getStylesheets().add(KnifeStyle);
        } else if (type.equals("W")) {
            b1.getStylesheets().add(WaterStyle);

        } else if (type.equals(".")) {
            b1.getStylesheets().add(BlockStyle);
        } else if (type.equals("CK")) {
            b1.getStylesheets().add(CharKnifeStyle);
            // this.curHeader.setText("Use G to hit!");
            // translateUp(b1, .2, 3, 1, 0, 0);
        } else if (type.equals("X")) {
            b1.getStylesheets().add(GunStyle);
            // translateUp(b1, .2, 3, 1, 0, 0);

        } else if (type.equals("d")) {
            b1.getStylesheets().add(cowStyle);
        }else if (type.equals("B")){
            b1.getStylesheets().add(BlackStyle);
        }
        else if (type.equals("b")){
            b1.getStylesheets().add(BlackStyle);
        }
        else if (type.equals("F")){
            b1.getStylesheets().add(FireStyle);
        }
        else if (type.equals("f")){
            b1.getStylesheets().add(FlowerStyle);
        }
        else if (type.equals("w")){
            b1.getStylesheets().add(WebStyle);
        }
        else if (type.equals("a")){
            b1.getStylesheets().add(GabrielStyle);
        }
        b1.setText("");

        b1.setBackground(new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
        b1.setDisable(true);
        return b1;
    }

    // Main function to test
    /*
     * public static void main(String args[]){
     * 
     * System.out.println("Testing ...");
     * Parse p = new Parse();
     * ArrayList<JLabel>rj = p.getList();
     * JFrame frame = new JFrame("i planet");
     * frame.setSize(600,800);
     * frame.setLayout(new GridLayout(3,1));
     * 
     * 
     * for (int i = 0; i<= rj.size()-1;i++){
     * frame.add(rj.get(i));
     * }
     * frame.addWindowListener(new WindowAdapter() {
     * public void windowClosing(WindowEvent windowEvent){
     * System.exit(0);
     * }
     * });
     * frame.setVisible(true);
     * 
     * 
     * 
     * }
     */

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

    // Translate up to emulate movement
    public void translateBackAndForth(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        Path path = new Path();
        path.getElements().add(new MoveTo(up, down));
        path.getElements().add(new CubicCurveTo(ogDown + 20, ogDown + 5, ogUp + 10, ogUp + 18, ogDown + 5, ogUp + 18));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }

    class Delta {

        double x, y;
    }

    // source events handlers
    public void setOnDragDetected(MenuButton source) {
        source.setOnDragDetected((MouseEvent event) -> {
            /* drag was detected, start drag-and-drop gesture */
            System.out.println("onDragDetected");

            /* allow any transfer mode */
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            db.setContent(content);

            event.consume();
        });
    }

    public void setOnDragDone(MenuButton source) {
        source.setOnDragDone((DragEvent event) -> {
            /* the drag-and-drop gesture ended */
            System.out.println("onDragDone");
            /* if the data was successfully moved, clear it */
            // if (event.getTransferMode() == TransferMode.MOVE) {
            // source.setText("");
            // }

            event.consume();
        });
    }

    // target event handlers
    public void setOnDragOver(StackPane target) {
        target.setOnDragOver((DragEvent event) -> {
            /* data is dragged over the target */
            System.out.println("onDragOver");

            /*
             * accept it only if it is not dragged from the same node
             * and if it has a string data
             */
            if (event.getGestureSource() != target
                    && event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
    }

    public void setOnDragEntered(StackPane target) {
        target.setOnDragEntered((DragEvent event) -> {
            /* the drag-and-drop gesture entered the target */
            System.out.println("onDragEntered");
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target
                    && event.getDragboard().hasString()) {
                target.setStyle("-fx-background-color: green;");
            }

            event.consume();
        });
    }

    public void setOnDragExited(StackPane target) {
        target.setOnDragExited((DragEvent event) -> {
            /* mouse moved away, remove the graphical cues */
            target.setStyle("-fx-background-color: transparent;");

            event.consume();
        });
    }

    public void setOnDragDropped(StackPane target) {
        target.setOnDragDropped((DragEvent event) -> {
            /* data dropped */
            System.out.println("onDragDropped");
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                // target.setText(db.getString());
                Button tempBoat = new Button(db.getString());
                tempBoat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                // target.getChildren().clear();
                // target.getChildren().add(tempBoat);
                success = true;
            }
            /*
             * let the source know whether the string was successfully
             * transferred and used
             */
            event.setDropCompleted(success);

            event.consume();
        });
    }

    public ArrayList<MovableBlock> getMovableBlocks() {

        return this.movingBlocks;
    } // End of getMovableBlocks

    public void setMovableBlocks(ArrayList<MovableBlock> mb) {
        this.movingBlocks = mb;
    }

    public ArrayList<Label> getMovableLabels() {
        return this.movingLabels;
    }

    public void setMovableLabels(ArrayList<Label> ml) {
        this.movingLabels = ml;
    }

    // Character getters and setter methods for updating

    public Character getChar() {
        return this.char1;
    }

    public void setChar(Character c) {
        this.char1 = c;
    }

    public void translateLeftRight(Node n, double dur, double up, double down, double ogDown, double ogUp) {

        // int x = n.getX();
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

}
