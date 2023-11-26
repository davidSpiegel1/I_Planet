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
import view.AnimateEngine;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;



public class mainPage{
    private Stage st;
    private static String backgroundColor = "black";
    private static String startColor = "#484646";
    private static String creditColor = "#484646";
    private static String textColor = "white";
    private Button b1;
    private AnimateEngine ag;
    
    private String fontColor = "white";
    private String backColor = "green";
    
    
 
    
    public void show(){
        st.show();
    }
    public Button getStartButton(){
        Button startButton = new Button("Start game");
        b1 = new Button("Start Game");
        b1.setStyle("-fx-background-color: "+startColor+"; "+"-fx-font-family: Courier New; " + "-fx-font-weight: bold; " +
                                "-fx-font-size: 15; "+
                                "-fx-text-fill: "+fontColor+";");
        
        b1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                String backColor = "";
                String fontColor = "";
                String[] styles = b1.getStyle().split(";");
                for (String style : styles) {
                    if (style.contains("-fx-background-color")) {
                        backColor = style.split(":")[1]; // the color of the button
                        System.out.println("The Back color: " + backColor);
               
                    }
                    if (style.contains("-fx-text-fill:")) {
                        fontColor = style.split(":")[1]; // the color of the button
                        System.out.println("The Font color: " + fontColor);
                    }
                }
                b1.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");}});
        
        b1.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                // Setting color to this
                String backColor = "";
                String fontColor = "";
                String[] styles = b1.getStyle().split(";");
                for (String style : styles) {
                    if (style.contains("-fx-background-color")) {
                        backColor = style.split(":")[1]; // the color of the button
                        System.out.println("The color: " + backColor);
                    }
                    if (style.contains("-fx-text-fill:")) {
                        fontColor = style.split(":")[1]; // the color of the button
                        System.out.println("The color: " + fontColor);
                    }
                }

                b1.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");
            }
        });
        
        
        
        
        
        b1.setAlignment(Pos.BASELINE_RIGHT);
        return b1;
    }
    
    public Button getCreditButton(){
        Button creditButton = new Button("Credits..");
        creditButton.setStyle("-fx-background-color: "+creditColor+"; "+"-fx-font-family: Courier New; " + "-fx-font-weight: bold; " +
            "-fx-font-size: 15; "+
            "-fx-text-fill: "+fontColor+";");
        
        
        
        creditButton.setAlignment(Pos.BASELINE_RIGHT);
        
        
        creditButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                String backColor = "";
                String fontColor = "";
                String[] styles = creditButton.getStyle().split(";");
                for (String style : styles) {
                    if (style.contains("-fx-background-color")) {
                        backColor = style.split(":")[1]; // the color of the button
                        System.out.println("The Back color: " + backColor);
               
                    }
                    if (style.contains("-fx-text-fill:")) {
                        fontColor = style.split(":")[1]; // the color of the button
                        System.out.println("The Font color: " + fontColor);
                    }
                }
                creditButton.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");}});
        
        creditButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                // Setting color to this
                String backColor = "";
                String fontColor = "";
                String[] styles = creditButton.getStyle().split(";");
                for (String style : styles) {
                    if (style.contains("-fx-background-color")) {
                        backColor = style.split(":")[1]; // the color of the button
                        System.out.println("The color: " + backColor);
                    }
                    if (style.contains("-fx-text-fill:")) {
                        fontColor = style.split(":")[1]; // the color of the button
                        System.out.println("The color: " + fontColor);
                    }
                }

                creditButton.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");
            }
        });
        
        
        return creditButton;
    }
    public Label getTitleLabel(){
        Label l1 = new Label("I Planet");
        
        l1.setStyle("-fx-border-radius: 3.0;"+
                              "-fx-border-width: 0.0;"+
                    "-fx-font-size: 50;" +
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"+
                              "-fx-font-weight: bold;"+
                              "-fx-font-family: Courier New;"
                              );
        return l1;
        
    }
    
    
    
    public Stage getStage(){
        return st;
    }
    
    
    public VBox getImage(){
        ag = new AnimateEngine();
        Button guy = new Button();
        guy.getStylesheets().add("/utilities/skins/charCss.css");
        VBox background = ag.getCoverImage(guy);
       
        VBox image = new VBox();
       
        image.getChildren().addAll(background);
        return image;
        
    }

    
    
    
}
