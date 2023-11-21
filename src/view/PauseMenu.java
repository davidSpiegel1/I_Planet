// This will hopefully be the pause menu 
// (Although, I don't really want it to pause and ruin the excitement)

package view;

import java.util.ArrayList;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import view.AnimateEngine;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javafx.scene.layout.Background;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;
import model.Enemies;
import model.Animal;
import model.Gabriel;
import model.MovableBlock;
import model.Spider;
import model.Edog;
import javafx.beans.binding.Bindings;

import model.Parse;
import model.Scan;
import model.MusicPlay;

import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;


public class PauseMenu{
    
    private Stage st;
    private GridPane pane;
    private Scene scene;
    private int height;
    private int width;
    // The constructor
    public PauseMenu(){
        
        st = new Stage();
        st.initStyle(StageStyle.TRANSPARENT);
        //st.setOpacity(0.5);
    }
    
    
    public void setPane(Character user, ScrollPane map1,MusicPlay mp, ArrayList<MenuButton> inventoryButtons){
        
        pane = new GridPane();
        
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setPrefSize(width,height);
        pane.setMinSize(width,height);
       // pane.setMaxSize(width,height/2);
        ColumnConstraints leftCol = new ColumnConstraints();
                leftCol.setHalignment(HPos.RIGHT);
                leftCol.setHgrow(Priority.NEVER);

        ColumnConstraints rightCol = new ColumnConstraints();
                rightCol.setHgrow(Priority.NEVER);
                rightCol.setHalignment(HPos.LEFT);
                //rightCol.setHgrow(Priority.NEVER);

        pane.getColumnConstraints().addAll(leftCol, rightCol);
        
        
        
        ScrollPane map = map1;
        /*map.setStyle("-fx-background-color: #484646;"
                     +"-fx-border-radius: 4.0;"
                     +"-fx-border-width: 3.0;"
                     +"-fx-border-color: GREY;"
                     +"-fx-opacity: 0.85;");*/
        //BorderPane bp = new BorderPane();
       // bp.setCenter(map);
        VBox bp = new VBox(map);
        
    
        bp.setAlignment(Pos.CENTER);
        
        //bp.getChildren().add(map);
        //map.setContent(bp);
        bp.setStyle("-fx-background-color: #484646;"
                    +"-fx-border-radius: 4.0;"
                    +"-fx-border-width: 3.0;"
                    +"-fx-border-color: GREY;"
                    +"-fx-opacity: 0.85;");
        VBox charInfo = this.userInfo(user.getName(),user.getLife());
        //Button charInfo = new Button("Char Info");
        VBox sounds = mp.displaySoundsVB();
        sounds.setStyle("-fx-background-color: #484646;"
                        +"-fx-border-radius: 4.0;"
                        +"-fx-border-width: 3.0;"
                        +"-fx-border-color: GREY;"
                        +"-fx-opacity: 0.85;");
        
        
        VBox inventory = constructInventory(inventoryButtons);
        Button crafting = new Button("Crafting");
        
        //charInfo.setOpacity(1);
        /*charInfo.setStyle("-fx-border-radius: 3.0;"+
                          "-fx-border-width: 0.0;"+
                "-fx-font-size: 50;" +
                          "-fx-text-fill: white;"+
                          "-fx-border-color: GREY;"+
                          "-fx-font-weight: bold;"+
                          "-fx-font-family: Courier New;"
                          );*/
        //charInfo.getStylesheets().add("/model/myButton2.css");
        pane.add(bp,0,0);
        bp.prefWidthProperty().bind(Bindings.min(pane.widthProperty().divide(3),
                                                                    pane.heightProperty().divide(5/3)));
        bp.prefHeightProperty().bind(Bindings.min(pane.widthProperty().divide(3),
                                                                    pane.heightProperty().divide(5/3)));
        GridPane.setHalignment(bp,HPos.LEFT);
        pane.add(charInfo,1,0);
        charInfo.prefWidthProperty().bind(Bindings.min(pane.widthProperty().divide(3),
                                                                    pane.heightProperty().divide(5/3)));
        charInfo.prefHeightProperty().bind(Bindings.min(pane.widthProperty().divide(3),
                                                                    pane.heightProperty().divide(5/3)));
        pane.add(sounds,0,1);
        sounds.prefWidthProperty().bind(Bindings.min(pane.widthProperty().divide(3),
                                                                    pane.heightProperty().divide(5/3)));
        sounds.prefHeightProperty().bind(Bindings.min(pane.widthProperty().divide(3),
                                                                    pane.heightProperty().divide(5/3)));
        
        /*sounds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Sounds Pressed!");
                   mp.displaySounds();
            }
        });*/
        //HBox inventory = constructInventory(inventoryButtons);
        pane.add(inventory,1,1);
        pane.add(crafting,0,2);
      

        pane.getColumnConstraints().addAll(leftCol, rightCol);
        pane.setBackground(Background.EMPTY);
    }
    
    public VBox constructInventory(ArrayList<MenuButton> mb){
        
        VBox vb = new VBox(15);
        
        HBox hb = new HBox(10);
        // Setting size of inventory to be 6
        for (int i = 0; i<= 5;i++){
            if (i <= mb.size()-1){
                //mb.get(i).setStyle("-fx-font-size: 15;"
                  //                 +"-fx-font-family: Courier New;"+
                    //               "-fx-font-weight: bold;");
                hb.getChildren().add(mb.get(i));
            }else{
                Button blankButton = new Button(".");
                blankButton.setStyle("-fx-background-color: #515151;"
                                     +"-fx-text-fill: #515151; -fx-font-size: 15;"
                                     +"-fx-font-family: Courier New;"+
                                     "-fx-font-weight: bold;");
                
                hb.getChildren().add(blankButton);
            }
        }
        
        Label title = new Label("Inventory: ");
        title.setStyle("-fx-text-fill: cadetblue; -fx-font-size: 30;"
                             +"-fx-font-family: Courier New;"+
                             "-fx-font-weight: bold;");
        vb.getChildren().addAll(title,hb);
        
        vb.setStyle("-fx-background-color: #484646;"
                     +"-fx-border-radius: 4.0;"
                     +"-fx-border-width: 3.0;"
                     +"-fx-border-color: GREY;"
                     +"-fx-opacity: 0.85;");
        return vb;
    }
    
    
    public VBox userInfo(String userName, int userLife){
        
        VBox hb = new VBox();
        HBox lifeBar = new HBox();
        for (int i = 0; i<= 9; i++){
            String deadAlive = "";
            String color1 = "";
            
            if (i <= userLife-1){
                deadAlive = ".";
                color1 = "cadetblue";
            }else{
                deadAlive = "_";
                color1 = "cadetblue";
            }
            
            Label ul = new Label(deadAlive);
            ul.setStyle("-fx-text-fill: "+color1+"; -fx-font-size: 30;"+
                        "-fx-font-family: Courier New;"+
                        "-fx-font-weight: bold;" );
        
            lifeBar.getChildren().add(ul);
        }
        Button user = new Button();
        user.getStylesheets().add("/utilities/skins/charCss2.css");
        
        Label userName1 = new Label(userName);
        userName1.setStyle("-fx-text-fill: cadetblue; -fx-font-size: 30;"
                           +"-fx-font-family: Courier New;"+
                           "-fx-font-weight: bold;");
        hb.getChildren().addAll(userName1,user,lifeBar);
        
        hb.setStyle("-fx-background-color: #484646;"
                    +"-fx-border-radius: 4.0;"
                    +"-fx-border-width: 3.0;"
                    +"-fx-border-color: GREY;"
                    +"-fx-opacity: 0.85;");
        
        return hb;
        
        
        
        
    }
    
    public Stage getStage(int width,int height){
        
        //st = new Stage();
        st.setTitle("Pause Menu");
        this.width = width;
        this.height = height;
        
        
        scene = new Scene(pane,width,height);
        scene.setFill(null);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode().equals(KeyCode.P)) {
                        st.close();
                    }
                }
            
                              
                              
                            }  );

        scene.setFill(Color.rgb(0,26,0,0.5));
        st.setScene(scene);
        
        
        return st;
        
        
        
        
    }
    
    public boolean isShowing(){
        
        return st.isShowing();
    }
    
    public void setHide(){
        st.close();
        
    }
    
    
}


