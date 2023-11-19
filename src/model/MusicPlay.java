// A media player for the game
//
//
//
package model;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javafx.application.Application;
import javafx.application.HostServices;
import java.util.*;
import javafx.stage.Stage;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


public class MusicPlay extends Application{
    
    private Media ac;
    private MediaPlayer player;
    
    private ArrayList<String> songs;
    private int index = 0;
    private double volume = 0.01;
    private Label songTitle;
    private String fontColor = "white";
    private String creditColor = "#484646";
    
    
    
    public MusicPlay(){
        
       songs = populateSongs(songs);
        
        
    }
    
    public ArrayList<String> populateSongs(ArrayList<String> s){
        s = new ArrayList<String>();
        s.add("Theme.m4a");
        s.add("End.m4a");
        s.add("Calm.m4a");
        return s;
    }
    
    public void displaySounds(){
        
        Stage st = new Stage();
        
        st.setTitle("Sounds Settings");
        
        //HBox hb = new HBox();
        //Button lower = new Button("<");
        Slider sl = new Slider(0.0,1.0,volume);
        sl.setStyle("-fx-border-radius: 3.0;"+
                     "-fx-border-width: 0.0;"+
           "-fx-font-size: 20;" +
                     "-fx-text-fill: white;"+
                     "-fx-border-color: GREY;"+
                     "-fx-font-weight: bold;"+
                     "-fx-font-family: Courier New;"
                     );
        sl.setValueChanging(true);
        sl.setBlockIncrement(0.1f);
        
        //Button higher = new Button(">");
        
        //hb.getChildren().addAll(sl);
        sl.valueChangingProperty().addListener(
                            (obs,wasChanging,isChanging)->{
                               if (!isChanging){
                                   player.setVolume(sl.getValue());
                                   volume = sl.getValue();
                               }
                            
                            });
        
        Label soundChange = new Label("Volume: ");
        soundChange.setStyle("-fx-border-radius: 3.0;"+
                              "-fx-border-width: 0.0;"+
                    "-fx-font-size: 20;" +
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"+
                              "-fx-font-weight: bold;"+
                              "-fx-font-family: Courier New;"
                              );
        
        
        
        // For the song change
        Label songChange = new Label("Song: ");
        songChange.setStyle("-fx-border-radius: 3.0;"+
                              "-fx-border-width: 0.0;"+
                    "-fx-font-size: 20;" +
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"+
                              "-fx-font-weight: bold;"+
                              "-fx-font-family: Courier New;"
                              );
        
        HBox hb2 = new HBox(10);
        
        // Lower song button
        Button lowerSong = new Button("<");
        lowerSong.setStyle("-fx-background-color: "+creditColor+"; "+"-fx-font-family: Courier New; " + "-fx-font-weight: bold; " +
                           "-fx-font-size: 15; "+
                           "-fx-text-fill: "+fontColor+";");
        songTitle = new Label(songs.get(index));
        songTitle.setStyle("-fx-border-radius: 3.0;"+
                           "-fx-border-width: 3.0;"+
                 "-fx-font-size: 15;" +
                           "-fx-text-fill: white;"+
                           "-fx-border-color: GREY;"+
                           "-fx-font-weight: bold;"+
                           "-fx-font-family: Courier New;"
                           );
        lowerSong.setOnMouseEntered(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                String backColor = "";
                String fontColor = "";
                String[] styles = lowerSong.getStyle().split(";");
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
                lowerSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");}});
        lowerSong.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                // Setting color to this
                String backColor = "";
                String fontColor = "";
                String[] styles = lowerSong.getStyle().split(";");
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

                lowerSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");
            }
        });
        
        // Next song button
        Button higherSong = new Button(">");
        higherSong.setStyle("-fx-background-color: "+creditColor+"; "+"-fx-font-family: Courier New; " + "-fx-font-weight: bold; " +
                            "-fx-font-size: 15; "+
                            "-fx-text-fill: "+fontColor+";");
        
        higherSong.setOnMouseEntered(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                String backColor = "";
                String fontColor = "";
                String[] styles = higherSong.getStyle().split(";");
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
                higherSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");}});
        
        higherSong.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                // Setting color to this
                String backColor = "";
                String fontColor = "";
                String[] styles = higherSong.getStyle().split(";");
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

                higherSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");
            }
        });
        
        higherSong.setOnAction((e)->{
           System.out.println("Next song!");
            this.changeSong();
            if (player != null){
                player.stop();
                songTitle.setText(songs.get(index));
            }
            
            this.play();
        });
        
        hb2.getChildren().addAll(lowerSong,songTitle,higherSong);
        
        VBox vb = new VBox(40);
        vb.getChildren().addAll(soundChange,sl,songChange,hb2);
        vb.setStyle("-fx-background-color: #484646");
        
        st.setScene(new Scene(vb,170,270));
        st.show();
        
        
    }
    
    // Just to get the graphic needed
    
    public VBox displaySoundsVB(){
        Slider sl = new Slider(0.0,1.0,volume);
        sl.setStyle("-fx-border-radius: 3.0;"+
                     "-fx-border-width: 0.0;"+
           "-fx-font-size: 20;" +
                     "-fx-text-fill: white;"+
                     "-fx-border-color: GREY;"+
                     "-fx-font-weight: bold;"+
                     "-fx-font-family: Courier New;"
                     );
        sl.setValueChanging(true);
        sl.setBlockIncrement(0.1f);
        
        //Button higher = new Button(">");
        
        //hb.getChildren().addAll(sl);
        sl.valueChangingProperty().addListener(
                            (obs,wasChanging,isChanging)->{
                               if (!isChanging){
                                   player.setVolume(sl.getValue());
                                   volume = sl.getValue();
                               }
                            
                            });
        
        Label soundChange = new Label("Volume: ");
        soundChange.setStyle("-fx-border-radius: 3.0;"+
                              "-fx-border-width: 0.0;"+
                    "-fx-font-size: 20;" +
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"+
                              "-fx-font-weight: bold;"+
                              "-fx-font-family: Courier New;"
                              );
        
        
        
        // For the song change
        Label songChange = new Label("Song: ");
        songChange.setStyle("-fx-border-radius: 3.0;"+
                              "-fx-border-width: 0.0;"+
                    "-fx-font-size: 20;" +
                              "-fx-text-fill: white;"+
                              "-fx-border-color: GREY;"+
                              "-fx-font-weight: bold;"+
                              "-fx-font-family: Courier New;"
                              );
        
        HBox hb2 = new HBox(10);
        
        // Lower song button
        Button lowerSong = new Button("<");
        lowerSong.setStyle("-fx-background-color: "+creditColor+"; "+"-fx-font-family: Courier New; " + "-fx-font-weight: bold; " +
                           "-fx-font-size: 15; "+
                           "-fx-text-fill: "+fontColor+";");
        songTitle = new Label(songs.get(index));
        songTitle.setStyle("-fx-border-radius: 3.0;"+
                           "-fx-border-width: 3.0;"+
                 "-fx-font-size: 15;" +
                           "-fx-text-fill: white;"+
                           "-fx-border-color: GREY;"+
                           "-fx-font-weight: bold;"+
                           "-fx-font-family: Courier New;"
                           );
        lowerSong.setOnMouseEntered(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                String backColor = "";
                String fontColor = "";
                String[] styles = lowerSong.getStyle().split(";");
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
                lowerSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");}});
        lowerSong.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                // Setting color to this
                String backColor = "";
                String fontColor = "";
                String[] styles = lowerSong.getStyle().split(";");
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

                lowerSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");
            }
        });
        
        // Next song button
        Button higherSong = new Button(">");
        higherSong.setStyle("-fx-background-color: "+creditColor+"; "+"-fx-font-family: Courier New; " + "-fx-font-weight: bold; " +
                            "-fx-font-size: 15; "+
                            "-fx-text-fill: "+fontColor+";");
        
        higherSong.setOnMouseEntered(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                String backColor = "";
                String fontColor = "";
                String[] styles = higherSong.getStyle().split(";");
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
                higherSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");}});
        
        higherSong.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                // Setting color to this
                String backColor = "";
                String fontColor = "";
                String[] styles = higherSong.getStyle().split(";");
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

                higherSong.setStyle("-fx-text-fill:" + backColor + ";" +
                        "-fx-background-color:" + fontColor + ";" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;" +
                        "-fx-mark-color: " + fontColor + ";");
            }
        });
        
        higherSong.setOnAction((e)->{
           System.out.println("Next song!");
            this.changeSong();
            if (player != null){
                player.stop();
                songTitle.setText(songs.get(index));
            }
            
            this.play();
        });
        
        hb2.getChildren().addAll(lowerSong,songTitle,higherSong);
        
        VBox vb = new VBox(40);
        vb.getChildren().addAll(soundChange,sl,songChange,hb2);
        vb.setStyle("-fx-background-color: #484646");
        return vb;
    }
    
    
    public void play(){
        
        ac = new Media(getHostServices().getDocumentBase() + "/utilities/sounds/"+songs.get(index));
        player = new MediaPlayer(ac);
        player.setVolume(volume);
        player.setAutoPlay(true);
        
    }
    public void changeSong(){
        if (index <= songs.size()-2){
            
        index++;
            
        }else{
            index=0;
        }
    }
    
    @Override
    public void start(Stage st){
        
        
    }
    
  
    
    
    
    
}
