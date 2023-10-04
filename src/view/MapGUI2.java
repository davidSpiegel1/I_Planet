// Kyle !!
//package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Label;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;


public class MapGUI2 extends Application
//public class MapGUI2
{
	public static void loadFiles()
	{
		try
		{
			FileInputStream file = new FileInputStream("../utilities/levelOne.txt");

			int ch;
			while((ch = file.read()) != -1)
			{
				System.out.print((char)ch);
				if(ch == '\n')
				{
					System.out.print("EOL");
				}
			}
			file.close();
		}
		catch(IOException e)
		{
			System.out.print("error");
			e.printStackTrace();
		}
	}


	public static void initMap(String args[])
	{
		launch(args);
		loadFiles();
	}


	public static void main(String args[]){
		
		initMap(args);
		//launch(args);
	}
	

	//Override inherited behaviour?
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Hello World");
		Button btn = new Button();
		btn.setText("Say: 'Hello World!'");
		btn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Hello World!");	
			}
		});

		//StackPane root = new StackPane();
		//root.getChildren().add(btn);
		Label cellLabel = new Label();
		GridPane levelOne = new GridPane();
		levelOne.setMinSize(400, 200);
		levelOne.setPadding(new Insets(10, 10, 10, 10));
		levelOne.setVgap(5);
		levelOne.setHgap(5);
		levelOne.setAlignment(Pos.CENTER);
		levelOne.add(cellLabel, 0, 0);
		cellLabel.setMinWidth(10);
		cellLabel.setMinHeight(10);
		
		cellLabel.setStyle("-fx-background-color: blue;");

		primaryStage.setScene(new Scene(levelOne));
		primaryStage.show();
	}
}
