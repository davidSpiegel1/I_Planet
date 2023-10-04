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
	public static GridPane loadFiles()
	{
		GridPane level = new GridPane();
		try
		{
			System.out.println("One");
			FileInputStream file = new FileInputStream("../utilities/levelOne.txt");

			int ch;
			int x = 0;
			int y = 0;
			while((ch = file.read()) != -1)
			{
				Label cellLabel = new Label();
				System.out.println("two");
				if(ch != '\n')
				{
					cellLabel.setMinWidth(50);
					cellLabel.setMinHeight(50);
					System.out.println("three");


					switch(ch)
					{
						case 'G':
							cellLabel.setStyle("-fx-background-color: #994d00;"); //brown
							System.out.println("adding " + (char)ch);
							break;
						case 't':
							cellLabel.setStyle("-fx-background-color: #003300"); //dark green
							System.out.println("adding " + (char)ch);
							break;
						case 'p':
							cellLabel.setStyle("-fx-background-color: #ffff00"); //yellow (temp)
							System.out.println("adding " + (char)ch);
							break;
						case 'g':	
							cellLabel.setStyle("-fx-background-color: #ffff00"); //yellow (temp)
							System.out.println("adding " + (char)ch);
							break;
						case 'O':
							cellLabel.setStyle("-fx-background-color: #ffff00"); //yellow (temp)
							System.out.println("adding " + (char)ch);
							break;
						case 'W':
							cellLabel.setStyle("-fx-background-color: #0000ff"); //blue
							System.out.println("adding " + (char)ch);
							break;
						case '.':
							cellLabel.setStyle("-fx-background-color: #663300"); //brown
							System.out.println("adding " + (char)ch);
							break;
						case '|':
							cellLabel.setStyle("-fx-background-color: #8c8c8c"); //grey
							System.out.println("adding " + (char)ch);
							break;
						default:
							cellLabel.setStyle("-fx-background-color: #ff00ff"); //pink (missing texture)
							System.out.println("Hit default");

					}
					level.add(cellLabel, x, y);
					x++;
				}
				else if(ch == '\n')
				{
					y+=1;
					x = 0;
				}
				System.out.println(x);
				System.out.println(y);

			}
			file.close();
		}
		catch(IOException e)
		{
			System.out.print("error");
			e.printStackTrace();
		}
		return level;
	}


	public static void initMap(String args[])
	{
		launch(args);
		//loadFiles();
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
		GridPane levelOne = loadFiles();
		System.out.println("Load files check");
		
		levelOne.setMinSize(800, 800);
		levelOne.setPadding(new Insets(10, 10, 10, 10));
		levelOne.setVgap(5);
		levelOne.setHgap(5);
		levelOne.setAlignment(Pos.CENTER);
		//levelOne.add(cellLabel, 0, 0);
		//cellLabel.setMinWidth(10);
		//cellLabel.setMinHeight(10);
		
		//cellLabel.setStyle("-fx-background-color: blue;");
	
		primaryStage.setScene(new Scene(levelOne));
		primaryStage.show();
	}
}
