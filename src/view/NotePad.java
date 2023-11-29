// Will be a notepad where you can write on the block you carry
// This will go away I think
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
import model.Notes;

import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;



public class NotePad {
    private Stage st;
    private Notes n;
    private static int WIDTH = 300;
    private static int HEIGHT = 400;
    
    public NotePad(Notes n){
        
        st = new Stage();
        //st.initStyle(StageStyle.TRANSPARENT);
        //newBlocks = new ArrayList<Block>();
        //st.setOpacity(0.5);
        this.n = n;
    }
    
    public Stage getStage(){
        
        VBox pane = new VBox(4);
        
        //Label note = new Label(n.getNote());
        
        TextArea text = new TextArea(n.getNote()+"\n\n");
        text.setStyle("-fx-text-fill: cadetblue; -fx-font-size: 20;"
                       +"-fx-font-family: Courier New;"+
                       "-fx-font-weight: bold;");
        //text.setAlignment(Pos.TOP_LEFT);
        text.setWrapText(true);
        
        HBox options = new HBox();
        Button commit = new Button("Commit");
        commit.setStyle("-fx-text-fill: cadetblue; -fx-font-size: 15;"
                        +"-fx-font-family: Courier New;"+
                        "-fx-font-weight: bold;");
        
        Button clear = new Button("Clear");
        clear.setStyle("-fx-text-fill: cadetblue; -fx-font-size: 15;"
                       +"-fx-font-family: Courier New;"+
                       "-fx-font-weight: bold;");
        
        options.getChildren().addAll(commit,clear);
        pane.getChildren().addAll(text,options);
        
        pane.setPrefSize(WIDTH,HEIGHT);
        pane.setMinSize(WIDTH,HEIGHT);
        Scene scene = new Scene(pane,WIDTH,HEIGHT);
        st.setScene(scene);
        return st;
    }
    
    
    
}
