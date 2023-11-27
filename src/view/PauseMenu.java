// This will hopefully be the pause menu 
// (Although, I don't really want it to pause and ruin the excitement)

package view;

import java.util.ArrayList;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
import model.Person;
import model.Enemies;
import model.Animal;
import model.Gabriel;
import model.MovableBlock;
import model.Spider;
import model.Edog;
import javafx.beans.binding.Bindings;
import javafx.scene.shape.Circle;
import javafx.scene.input.Dragboard;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DragEvent;
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
    private Button newObject;
    private String o1;
    private String o2;
    ArrayList<MenuButton> mb;
    private Scan sc;
    private Parse p;
    private Button currentGraphic;
    private ArrayList<Block> newBlocks;
    // The constructor
    
    public static final String woodStyle = "/utilities/skins/woodCss.css";
    
    
    public PauseMenu(){
        
        st = new Stage();
        st.initStyle(StageStyle.TRANSPARENT);
        newBlocks = new ArrayList<Block>();
        //st.setOpacity(0.5);
    }
    
    
    public void setPane(Character user, ScrollPane map1,MusicPlay mp, ArrayList<MenuButton> inventoryButtons, Parse p, Scan sc){
        
        this.p = p;
        this.sc = sc;
        
        mb = inventoryButtons;
        
        pane = new GridPane();
        
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setPrefSize(width,height-20);
        pane.setMinSize(width,height-20);
       // pane.setMaxSize(width,height/2);
        ColumnConstraints leftCol = new ColumnConstraints();
                leftCol.setHalignment(HPos.RIGHT);
                leftCol.setHgrow(Priority.NEVER);

        ColumnConstraints rightCol = new ColumnConstraints();
                rightCol.setHgrow(Priority.NEVER);
                rightCol.setHalignment(HPos.LEFT);
                //rightCol.setHgrow(Priority.NEVER);

        pane.getColumnConstraints().addAll(leftCol, rightCol);
        
        
        //I removed the vbox the map scroll pane was being put in because it seemed redundant
        //All the coded is still there, so if there was a reason for this I missed, you can uncomment and rename bp back to map
        //ScrollPane map = map1;
        ScrollPane bp = map1;
        
        //Setting the style on both the scrollpane and vbox caused a double border, so I removed one
        //map.setStyle("-fx-background-color: #484646;"
        //             +"-fx-border-radius: 10.0;"
        //             +"-fx-border-width: 3.0;"
        //             +"-fx-border-color: GREY;"
        //             +"-fx-opacity: 0.85;");
        //BorderPane bp = new BorderPane();
       // bp.setCenter(map);
        //VBox bp = new VBox(map);
        
    
        //bp.setAlignment(Pos.CENTER);
        
        //bp.getChildren().add(map);
        //map.setContent(bp);
        bp.setStyle("-fx-background-color: #484646;"
                    +"-fx-border-radius: 5.0;"
                    +"-fx-border-width: 4.0;"
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
        
        
        VBox inventory = constructInventory();
        HBox crafting = getCraftModule();
        
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

        pane.add(inventory,1,1);
        // Building out crafting method with new HBox
        
       
        pane.add(crafting,0,2,2,1);
      

        pane.getColumnConstraints().addAll(leftCol, rightCol);
        pane.setBackground(Background.EMPTY);
    }
    
    
    public HBox getCraftModule(){
        HBox craftModule = new HBox(10);
        
        
        // The drags
        VBox drags = new VBox(5);
        Button dragOne = new Button("Object 1");
        dragOne.setMaxWidth(100.0);
        dragOne.setMinWidth(50.0);
        dragOne.setMaxHeight(100.0);
        dragOne.setMinHeight(50.0);
        dragOne.setStyle("-fx-background-color: #515151;"
                            +"-fx-text-fill: white; -fx-font-size: 15;"
                            +"-fx-font-family: Courier New;"
                         +"-fx-border-radius: 4.0;"
                         +"-fx-border-width: 3.0;"
                         +"-fx-border-color: GREY;"
                            +"-fx-font-weight: bold;");
        
        
        Button dragTwo = new Button("Object 2");
        dragTwo.setMaxWidth(100.0);
        dragTwo.setMinWidth(50.0);
        dragTwo.setMaxHeight(100.0);
        dragTwo.setMinHeight(50.0);
        dragTwo.setStyle("-fx-background-color: #515151;"
                            +"-fx-text-fill: white; -fx-font-size: 15;"
                            +"-fx-font-family: Courier New;"
                         +"-fx-border-radius: 4.0;"
                         +"-fx-border-width: 3.0;"
                         +"-fx-border-color: GREY;"
                            +"-fx-font-weight: bold;");
        drags.getChildren().addAll(dragOne,dragTwo);
        
        // The craft button
        Button craftMe = new Button("Craft me!");
        craftMe.setStyle("-fx-background-color: #515151;"
                            +"-fx-text-fill: white; -fx-font-size: 15;"
                            +"-fx-font-family: Courier New;"
                         +"-fx-border-radius: 4.0;"
                         +"-fx-border-width: 3.0;"
                         +"-fx-border-color: GREY;"
                            +"-fx-font-weight: bold;");
        craftMe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (o1 != null && o2 != null){
                    newObject.setGraphic(craftButton(o1,o2));
                    for (int p =0; p<= mb.size()-1;p++){
                   
                        if (mb.get(p).getText().equals(o1)){
                            int num = Integer.parseInt(((Button)mb.get(p).getGraphic()).getText());
                            num--;
                            //Button b9 = ((Button)mb.get(p).getGraphic());
                            //b9.setText(String.valueOf(num));
                            ((Button)mb.get(p).getGraphic()).setText(String.valueOf(num));
                            
                        }
                        
                        if (mb.get(p).getText().equals(o2) ){
                            int num = Integer.parseInt(((Button)mb.get(p).getGraphic()).getText());
                            num--;
                            //Button b9 = ((Button)mb.get(p).getGraphic());
                            //b9.setText(String.valueOf(num));
                            ((Button)mb.get(p).getGraphic()).setText(String.valueOf(num));
                            
                        }
                    }
                }
                
            }
        });
        craftMe.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                craftMe.setStyle("-fx-background-color: #515151;"
                                  +"-fx-text-fill: white; -fx-font-size: 15;"
                                  +"-fx-font-family: Courier New;"
                               +"-fx-border-radius: 4.0;"
                               +"-fx-border-width: 3.0;"
                               +"-fx-border-color: GREY;"
                                  +"-fx-font-weight: bold;");
            }
            
        });// For exiting the button
        craftMe.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                craftMe.setStyle("-fx-background-color: #333333;"
                                 +"-fx-text-fill: white; -fx-font-size: 15;"
                                 +"-fx-font-family: Courier New;"
                              +"-fx-border-radius: 4.0;"
                              +"-fx-border-width: 3.0;"
                              +"-fx-border-color: GREY;"
                                 +"-fx-font-weight: bold;");
            }
        });
        
        
        
        // The image of the new crafted object
        newObject = new Button();
        newObject.setStyle("-fx-background-color: #515151;"
                            +"-fx-text-fill: #515151; -fx-font-size: 15;"
                            +"-fx-font-family: Courier New;"+
                            "-fx-font-weight: bold;"
                           +"-fx-opacity: 0.65;");
      
        newObject.setMaxWidth(400.0);
        newObject.setMinWidth(350.0);
        newObject.setMaxHeight(100.0);
        newObject.setMinHeight(90.0);
        
        
        dragOne.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                //crafting.setText("HERE!!");
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    dragOne.setText(db.getString());
                    o1 = db.getString();
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
        
        dragTwo.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                //crafting.setText("HERE!!");
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    dragTwo.setText(db.getString());
                    o2 = db.getString();
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
        craftModule.getChildren().addAll(drags,newObject,craftMe);
        craftModule.setStyle("-fx-background-color: #484646;"
                     +"-fx-border-radius: 4.0;"
                     +"-fx-border-width: 1.0;"
                     +"-fx-border-color: GREY;"
                     +"-fx-opacity: 0.85;");
        
        //craftModule.setPrefSize(width,height/14);
        //craftModule.setMinSize(width,height/14);
        //craftModule.setMinHeight(height/4);
        //craftModule.setMaxHeight(height/2);
        //craftModule.setBackground(Background.EMPTY);
        
        craftModule.setAlignment(Pos.CENTER);
        return craftModule;
        
    }
    
    
    public Button craftButton(String typeOne, String typeTwo){
        currentGraphic = new Button(typeOne);
        if (typeOne.equals("t") && typeTwo.equals("t")){
            //b1.getStylesheets().add(woodStyle);
            currentGraphic.getStylesheets().add(p.buttonBuilder("#").getStylesheets().get(0));
            currentGraphic.setText("#");
        }
        else if (typeOne.equals("#") && typeTwo.equals("#")){
            currentGraphic.getStylesheets().add(p.buttonBuilder("$").getStylesheets().get(0));
            currentGraphic.setText("$");
        }
        else if (typeOne.equals("]") && typeTwo.equals("]")){
            currentGraphic.getStylesheets().add(p.buttonBuilder(",").getStylesheets().get(0));
            currentGraphic.setText(",");
        }
        
        currentGraphic.setDisable(false);
        currentGraphic.setOnDragDetected((MouseEvent event) -> {
            /* drag was detected, start drag-and-drop gesture */
            System.out.println("onDragDetected");
            //System.out.println(source.getId());
            
            /* allow any transfer mode */
            Dragboard db = currentGraphic.startDragAndDrop(TransferMode.ANY);

            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(currentGraphic.getText());
            db.setContent(content);

            event.consume();
        });
        
        return currentGraphic;
        
    }
    
    
    public ArrayList<MenuButton> updateMenuButtons(){
        return mb;
    }
    
    public VBox constructInventory(){
        
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
                
                blankButton.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override public void handle(DragEvent event) {
                        //crafting.setText("HERE!!");
                        Dragboard db = event.getDragboard();
                        System.out.println("The string: "+db.getString() );
                        
                        if (db.hasString() && currentGraphic != null) {
                            //dragOne.setText(db.getString());
                            //o1 = db.getString();
                            //blankButton.setText(currentGraphic.getText());
                            //blankButton.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
                            newBlocks.add(sc.findBlock(currentGraphic.getText()));
                            blankButton.setGraphic(currentGraphic);
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                            currentGraphic = null;
                        }
                        event.consume();
                    }
                });
                
                
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
    
    public ArrayList<Block> getNewBlocks(){
        return newBlocks;
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
        
       
        
        HBox hbox = getDash();
       
        
        VBox root = new VBox(1);
        root.setAlignment(Pos.CENTER);
        root.setBackground(Background.EMPTY);
        VBox.setVgrow(pane, Priority.ALWAYS);
        //VBox.setVgrow(closeButton, Priority.NEVER);
        
        //pane.setHgap(10);
        //pane.setVgap(10);
        //pane.setPadding(new Insets(10,10,10,10));
        root.setPrefSize(width,height);
        root.setMinSize(width,height);
        
        
        root.getChildren().addAll(hbox,pane);
        scene = new Scene(root,width,height);
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
    
    public HBox getDash(){
        MenuBar file = new MenuBar();
        file.setId("file");
        Button closeButton = new Button("x");
        closeButton.setStyle("-fx-background-color: #C13B5F;"
                     +"-fx-border-radius: 50.0;"
                     +"-fx-border-width: 1.0;"
                     +"-fx-border-color: GREY;"
                     +"-fx-opacity: 1.0;"
                    +"-fx-text-fill: #C13B5F;");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                st.close();
            }
        });
        closeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                closeButton.setStyle("-fx-background-color: #C13B5F;"
                             +"-fx-border-radius: 50.0;"
                             +"-fx-border-width: 1.0;"
                             +"-fx-border-color: GREY;"
                             +"-fx-opacity: 1.0;"
                            +"-fx-text-fill: #C13B5F;");
            }
            
        });// For exiting the button
        closeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                closeButton.setStyle("-fx-background-color: #D14F72;"
                                    +"-fx-border-radius: 50.0;"
                                    +"-fx-border-width: 1.0;"
                                    +"-fx-border-color: GREY;"
                                    +"-fx-opacity: 1.0;"
                                     +"-fx-text-fill: #52091C;"
                                     +"-fx-font-weight: bold;"
                                     +"-fx-font-size: 9;");
            }
        });
        closeButton.setShape(new Circle(100));
        closeButton.setMaxWidth(17.0);
        closeButton.setMinWidth(15.0);
        closeButton.setMaxHeight(17.0);
        closeButton.setMinHeight(15.0);
        HBox hbox = new HBox(10);
        //hbox.setHgap(10);
        //hbox.setVgap(10);
        hbox.setPadding(new Insets(30,10,10,10));
        hbox.getChildren().addAll(closeButton);
        hbox.setStyle("-fx-background-color: #484646;"
                     +"-fx-border-radius: 4.0;"
                     +"-fx-border-width: 1.0;"
                     +"-fx-border-color: GREY;"
                     +"-fx-opacity: 1.0;");
        hbox.setPrefSize(width,height/14);
        hbox.setMinSize(width,height/14);
        hbox.setBackground(Background.EMPTY);
        hbox.setAlignment(Pos.CENTER_LEFT);
        
        return hbox;
        
    }
    
    public boolean isShowing(){
        
        return st.isShowing();
    }
    
    public void setHide(){
        st.close();
        
    }
    
    
}


