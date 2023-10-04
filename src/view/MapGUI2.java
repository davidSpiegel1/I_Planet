// Kyle !!
//package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Label;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;


public class MapGUI2 extends Application
//public class MapGUI2
{
	public static GridPane loadFiles(String levelFile)
	{
		GridPane level = new GridPane();
		try
		{
			System.out.println("One");
			FileInputStream file = new FileInputStream("../utilities/" + levelFile);

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
						case '_':
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
		level.setMinSize(800, 800);
		level.setPadding(new Insets(10, 10, 10, 10));
		level.setVgap(5);
		level.setHgap(5);
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

		//level one	
		GridPane levelOne = loadFiles("levelOne.txt");
		GridPane levelTwo = loadFiles("levelTwo.txt");
		GridPane levelThree = loadFiles("levelThree.txt");
		GridPane levelFour = loadFiles("levelFour.txt");
		GridPane levelFive = loadFiles("levelFive.txt");
		GridPane levelSix = loadFiles("levelSix.txt");
		GridPane levelSeven = loadFiles("levelSeven.txt");
		GridPane levelEight = loadFiles("levelEight.txt");
	
		
		//Holds overall layout
		VBox mapLayout = new VBox(2);
		ScrollPane scroll = new ScrollPane(mapLayout);

		//Populate vbox
		mapLayout.getChildren().add(levelOne);
		mapLayout.getChildren().add(levelTwo);
		mapLayout.getChildren().add(levelThree);
		mapLayout.getChildren().add(levelFour);
		mapLayout.getChildren().add(levelFive);
		mapLayout.getChildren().add(levelSix);
		mapLayout.getChildren().add(levelSeven);
		mapLayout.getChildren().add(levelEight);
	
		primaryStage.setScene(new Scene(scroll));
		primaryStage.show();
	}
}
