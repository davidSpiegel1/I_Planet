// New Java main page
//
//
//
// (Hopefully with animation to start..)
package view;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.geometry.Pos;

public class mainPage{
    private Stage st;
    private static String backgroundColor = "black";
    private static String startColor = "red";
    private static String creditColor = "green";
    private static String textColor = "white";
    private Button b1;
    
    
    
 
    
    public void show(){
        st.show();
    }
    public Button getStartButton(){
        Button startButton = new Button("Start game");
        b1 = new Button("start game");
        b1.setStyle("-fx-background-color: "+startColor+";"+
        "-fx-border-radius: 3.0;"+
        "-fx-border-width: 3;"+
        "-fx-text-fill: white;"+
        "-fx-border-color: GREY;"
        );
        b1.setAlignment(Pos.BASELINE_RIGHT);
        return b1;
    }
    
    public Button getCreditButton(){
        Button creditButton = new Button("Credits..");
        creditButton.setStyle("-fx-background-color: "+creditColor+";"+
                              "-fx-border-radius: 3.0;"+
                              "-fx-border-width: 3;"+
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"
                              );
        
        creditButton.setAlignment(Pos.BASELINE_RIGHT);
        
        return creditButton;
    }
    public Label getTitleLabel(){
        Label l1 = new Label("I Planet");
        l1.setStyle("-fx-border-radius: 3.0;"+
                              "-fx-border-width: 3;"+
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"
                              );
        return l1;
        
    }
    
    
    
    public Stage getStage(){
        return st;
    }

    
    
    
}
