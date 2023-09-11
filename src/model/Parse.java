// The idea here is to make a parse object that builds a list for the gui
package model;

import java.util.ArrayList;
import javafx.util.Duration;
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


public class Parse{
    
    // An instance variable of scanner
    private Scan sc;
    private ArrayList<Label>labelList = null;
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
    
    
    public Parse(){
        labelList = new ArrayList<Label>();
        System.out.println("Parsing started..");
            
        
    }
    public ArrayList<Label> getList(){
        
        return this.labelList;
    }
    public ArrayList<Label> constructGui(ArrayList<Block> arr){
        
        for (int i = 0; i<= arr.size()-1;i++){
            Label l1 = new Label(arr.get(i).getKey());
           
            if (arr.get(i).getKey().equals("C")){
                l1.setText("C");
                //l1.setBackground(findBackGround(prevBlock.getKey()));
            }
       
            l1.setBackground(findBackGround(arr.get(i).getKey()));
            l1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
            l1.setMinHeight(SET_MIN_HEIGHT);
            this.labelList.add(l1);
        }
        
        
        return this.labelList;
    }
    
    public ArrayList<Label> constructGui(ArrayList<Block> arr,Block prevBlock){
       
        for (int i = 0; i<= arr.size()-1;i++){
            Label l1 = new Label();
            l1.setGraphic(null);
            if (arr.get(i).getKey().equals("C")){
                //l1.setText("C");
                l1.setBackground(findBackGround(prevBlock.getKey()));
                l1.setGraphic(buttonBuilder(arr.get(i).getKey()));
            }
            else{
                if (arr.get(i).getKey().equals("t") || arr.get(i).getKey().equalsIgnoreCase("G") || arr.get(i).getKey().equals("_") ||
                    arr.get(i).getKey().equals("|")){
                    l1.setBackground(findBackGround(arr.get(i).getKey()));
                    l1.setGraphic(buttonBuilder(arr.get(i).getKey()));
                }else{
                //l1.setText(arr.get(i).getKey());
                l1.setBackground(findBackGround(arr.get(i).getKey()));
                   // l1.setGraphic(buttonBuilder(arr.get(i).getKey()));
                    }
            }
            //l1.setGraphic(buttonBuilder(arr.get(i).getKey()));
            //l1.setGraphic(buttonBuilder(arr.get(i).getDescription()));
            l1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
            l1.setMaxWidth(Double.MAX_VALUE);
            l1.setAlignment(Pos.CENTER);
            l1.setFont(new Font("Arial", 30));
            l1.setMinHeight(SET_MIN_HEIGHT);
            this.labelList.add(l1);
        }
        
        return this.labelList;
    }
                      
    public Background findBackGround(String key){
            Background b;
            if (key.equals("G")){
              b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
            }
            else if (key.equals("s")){
              b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
            }
            else if (key.equals("X")){
                b = new Background(new BackgroundFill(Color.rgb(137, 110, 77), new CornerRadii(2.0), Insets.EMPTY));
            }
            else if (key.equals("t")){
                b = new Background(new BackgroundFill(Color.rgb(0, 110, 28), new CornerRadii(2.0), Insets.EMPTY));
            }
            else if (key.equals("_") || key.equals("|")){
                b= new Background(new BackgroundFill(Color.rgb(88, 103, 110), new CornerRadii(2.0), Insets.EMPTY));
            }
            else if (key.equals("W")){
                b = new Background(new BackgroundFill(Color.rgb(0, 89, 160), CornerRadii.EMPTY, Insets.EMPTY));
            }
            else{
                b = new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY));
            }
                
        return b;
                
    }
    
    
    public ArrayList<Button> parseInventory(ArrayList<Block> blockArr){
        ArrayList<Button> buttonArr = new ArrayList<Button>();
        // Looping through the
        for (int i = 0; i<= blockArr.size()-1;i++){
            Button b = new Button(blockArr.get(i).getKey());
            b.setBackground(findBackGround(blockArr.get(i).getKey()));
            //b.setBackground(new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
            b.setDisable(true);
            buttonArr.add(b);
            /*b.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    b.setStyle("-fx-background-color:#dae7f3;");
                }
            });

            b.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    b.setStyle("-fx-background-color:transparent;");
                }
            });*/
         
        }
        
        return buttonArr;
    }
    
    public Button buttonBuilder(String type){
        Button b1 = new Button(type);
        b1.setLayoutX(0);
        b1.setLayoutY(0);
        if (type.equals("t")) {
            // SVGPath path = new SVGPath();

            b1.getStylesheets().add(MainStyle);
            //translateBackAndForth(b1, 50, 3, 1, 0, 0);
        }
        // public void translateObject(Node n, double dur, int cyle, int howFar, boolean
        // isPoly) {
        else if (type.equals("C")) {
            b1.getStylesheets().add(CharStyle);
            //translateUp(b1, .2, 3, 1, 0, 0);

        } else if (type.equals("G")) {
            b1.getStylesheets().add(GrassStyle);
        }else if (type.equals("g")){
            b1.getStylesheets().add(smallGrassStyle);
        }else if (type.equals("P")) {
            b1.getStylesheets().add(AshStyle);
        } else if (type.equals("s")) {
            b1.getStylesheets().add(JackStyle);
        } else if (type.equals("E")) {
            b1.getStylesheets().add(BadStyle);
           // translateUp(b1, .2, 3, 1, 0, 0);
        } else if (type.equals("|") || type.equals("_")) {
            b1.getStylesheets().add(BrickStyle);
        } else if (type.equals("K")) {
            b1.getStylesheets().add(KnifeStyle);
        } else if (type.equals("W")) {
            b1.getStylesheets().add(WaterStyle);

        } else if (type.equals(".")) {
            b1.getStylesheets().add(BlockStyle);
        } else if (type.equals("CK")) {
            b1.getStylesheets().add(CharKnifeStyle);
            //this.curHeader.setText("Use G to hit!");
           // translateUp(b1, .2, 3, 1, 0, 0);
        }
         else if (type.equals("X")) {
        b1.getStylesheets().add(GunStyle);
        //translateUp(b1, .2, 3, 1, 0, 0);
        
         }
        b1.setText("");

        b1.setBackground(new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
        b1.setDisable(true);
        return b1;
    }
    
    // Main function to test
    /*public static void main(String args[]){
        
        System.out.println("Testing ...");
        Parse p = new Parse();
        ArrayList<JLabel>rj = p.getList();
        JFrame frame = new JFrame("i planet");
        frame.setSize(600,800);
        frame.setLayout(new GridLayout(3,1));
        
        
        for (int i = 0; i<= rj.size()-1;i++){
            frame.add(rj.get(i));
        }
        frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                 }
              });
        frame.setVisible(true);
        
        
        
    }*/
    
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
    
    
    
}


