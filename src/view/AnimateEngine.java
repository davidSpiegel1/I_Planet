// A class for animating 
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
import javafx.scene.shape.*;

//import javafx.scene.layout.StackPane;
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;
import model.Enemies;

import model.Parse;
import model.Scan;
import controller.Controller2;


public class AnimateEngine {
    
    
    // Constructor for the animation engine
    public AnimateEngine(){
        System.out.println("Starting the animation engine");
    }
    
    
    public void hitAnimation(Node n){
        FadeTransition ft = new FadeTransition(Duration.millis(3000), n);
        ft.setFromValue(0.5);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        //ft.setAutoReverse(true);
        ft.setOnFinished((e) -> {
            n.setStyle("-fx-background-color: #C7C7C7;");
            

                         });
        ft.play();
    }
    public void deadAnimation(Node n){
        RotateTransition rt = new RotateTransition(Duration.millis(300), n);
        rt.setByAngle(90);
        rt.setCycleCount(1);
        rt.setAutoReverse(true);
        rt.play();
        
    }
    
    
    public void grabAnimation(Node n){
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(60);
        rt.setToAngle(0);
        
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
        parallelTransition.setCycleCount(1);
        parallelTransition.play();

    }
    
    // An animation that makes the character drink
    
    public void drinkAnimation(Node n){
        Button b = (Button)n;
        // Will be mouth of animal
        Ellipse el = new Ellipse();
        //el.focusTraversable(false);
        //el.setManaged(false);
        el.setCenterX(7);
        el.setCenterY(7);
        el.setRadiusX(5.0f);
        el.setRadiusY(3.0f);
        
        
        //el.setLayoutX(20);
        el.setTranslateX(-10);
        el.setTranslateY(-4);
        el.setFill(Color.rgb(74,74,74));
        
        // Scale transition
        ScaleTransition st3 = new ScaleTransition(Duration.millis(900),el);
        st3.setFromX(1.3f);
        st3.setFromY(1);
        st3.setToX(0);
        st3.setToY(0);
        st3.setCycleCount(1);
        
        b.setGraphic(el);
        
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(-60);
        rt.setToAngle(0);
        
      
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,st3);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
        
        
    }
    
    public Node translateEat(Node n){
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(-60);
        rt.setToAngle(0);
        
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
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        return n;

    }
    
    
    
    // Translate up to emulate movement
    public void translateUp2(Node n, double dur, double up, double down, double ogDown, double ogUp) {
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
    
    public void shootNode(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        
        
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
    
    public void shootNode2(Node n, double dur) {
        Path path = new Path();
        path.getElements().add(new MoveTo(0.0f, 0.0f));
        path.getElements().add(new HLineTo(80.0f));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }
    
    public Node translateUp(Node n,double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutY(y-scaler);
      
        double x2 = n.getLayoutY();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public Node translateDown(Node n,double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
        // Used to be: 32
        n.setLayoutY(y+scaler);
      
        double x2 = n.getLayoutY();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public Node translateLeft(Node n,double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutX(x-scaler);
      
        double x2 = n.getLayoutX();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public Node translateRight(Node n, double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutX(x+scaler);
      
        double x2 = n.getLayoutX();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public void moveNatural(Node n){
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        //path.getElements().add(new CubicCurveTo(5,0,3,1,2,1));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(20);
        qt.setY(15);
        qt.setControlX(15);
        qt.setControlY(20);
        
        
        
        path.getElements().add(qt);
        //path.getElements().add(st);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(250));
        //pathT.setDuration(Duration.millis(250));
        pathT.setPath(path);
        pathT.setNode(n);
        
        // Would be infefinite if breathing or something
        // pathT.setCycleCount(Timeline.INDEFINITE);
        pathT.setCycleCount(1);
        //pathT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathT.setAutoReverse(true);
   
        
        ScaleTransition st = new ScaleTransition(Duration.millis(250),n);
        st.setFromX(.01);
        st.setFromY(1);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(300),n);
        st2.setFromX(1.3f);
        st2.setFromY(1);
        st2.setToX(1);
        st2.setToY(1);
        st2.setCycleCount(1);
        
  
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathT,st2);
        //st2.play();
        
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        //st2.play();
        
        
    }
    
    
    
    
    
}
