// Kyle !!
package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Label;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import javafx.scene.input.ZoomEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.TextAlignment;
import controller.Controller2;


//public class MapGUI2 extends Application
public class MapGUI2
{
	private static Controller2 character;

	//Im pretty sure java just passes objects by reference, so this shouldnt really matter
	public MapGUI2(Controller2 c) {
		character = c;
	}

	public static GridPane loadFiles(String levelFile)
	{
		int currentLevel = character.getCurrentLevel();
		int pos = character.getCharPos();

		GridPane level = new GridPane();
		//Java yells at me if I don't have a try catch statment
		try
		{
			File file = new File("utilities/levels/" + levelFile);
			
			//Make sure file exists before reading
			if(file.exists())
			{
				//FileInputStream fileStream = new FileInputStream("utilities/levels/" + levelFile);
				FileInputStream fileStream = new FileInputStream(file);

				int ch;
				int x = 0;
				int y = 0;
				int charPos = 0;
				int playerLevel = 1;
				System.out.println("Current Level: " + currentLevel);
		
				//Iterate over every character until end of file
				while((ch = fileStream.read()) != -1)
				{
					//Labels are used to chage the background of the gridpane
					Label cellLabel = new Label();

					//Keep track of the players current level, so their icon can be placed in the
					//proper level in the map
					if(playerLevel == currentLevel && pos == charPos)
					{
						cellLabel.setText("X");
						cellLabel.setTextAlignment(TextAlignment.CENTER);
					}

					if(ch != '\n')
					{
						cellLabel.setMinWidth(25);
						cellLabel.setMinHeight(25);

						//These colors are currently just a guess of that they are supposed to be
						//Some map items may be missing or miscolored
						switch(ch)
						{
							case 'G':
								cellLabel.setStyle("-fx-background-color: #00b300;"); //Light green
								//System.out.println("adding " + (char)ch);
								break;
							case 't':
								cellLabel.setStyle("-fx-background-color: #006600"); //dark green
								//System.out.println("adding " + (char)ch);
								break;
							case 'p':
								cellLabel.setStyle("-fx-background-color: #ffff00"); //yellow (temp)
								//System.out.println("adding " + (char)ch);
								break;
							case 'g':	
								cellLabel.setStyle("-fx-background-color: #663300"); //brown
								//System.out.println("adding " + (char)ch);
								break;
							case 'O':
								cellLabel.setStyle("-fx-background-color: #ffff00"); //yellow (temp)
								//System.out.println("adding " + (char)ch);
								break;
							case 'W':
								cellLabel.setStyle("-fx-background-color: #0000ff"); //blue
								//System.out.println("adding " + (char)ch);
								break;
							case '.':
								cellLabel.setStyle("-fx-background-color: #663300"); //brown
								//System.out.println("adding " + (char)ch);
								break;
							case '_':
							case '|':
								cellLabel.setStyle("-fx-background-color: #8c8c8c"); //grey
								//System.out.println("adding " + (char)ch);
								break;
							default:
								cellLabel.setStyle("-fx-background-color: #ff00ff"); //pink (missing texture)
								//System.out.println("Hit default");

						}
						level.add(cellLabel, x, y);
						x++;
						pos++;
					}
					//Start a new row when reaching the end of a line
					else if(ch == '\n')
					{
						y+=1;
						x = 0;
					}

				}

				playerLevel++;
				fileStream.close();
			}
			else
			{
				System.out.println("Error: " + levelFile + " not Found!");
			}
		}
		catch(IOException e)
		{
			System.out.print("error");
			e.printStackTrace();
		}

		int calcSize = (22 * 25) + ((22-2) * 2);
		level.setMinSize(300, 300);
		level.setVgap(2);
		level.setHgap(2);
		return level;
	}
	

	//Load the map the player is currently on
	public static ScrollPane loadCurrentLevel()
	{
		int curLevel = character.getCurrentLevel() + 1;
		String fileName = curLevel + ".txt";
		
		System.out.println("[INFO] getting level " + fileName);

		//Get read file into gridpane
		GridPane map = loadFiles(fileName);
        
		ScrollPane mapLevel = new ScrollPane();
		mapLevel.setContent(map);
		
		mapLevel.setPannable(true);
        mapLevel.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapLevel.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      
		map.setMinHeight(mapLevel.getWidth());
		map.setMinWidth(mapLevel.getHeight());
		map.setAlignment(Pos.CENTER);

		//Handle zoom via mouse wheel
		mapLevel.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
			@Override public void handle(ScrollEvent event) {
				System.out.println("Scroll event detected");
               
				double detectDirection = event.getDeltaY();
				double curMapWidth = map.getWidth() * map.getScaleX();

				if(detectDirection > 0) {
					map.setScaleX(map.getScaleX() + .1); 
					map.setScaleY(map.getScaleY() + .1);
					System.out.println("Zoom in");
				}
				else if(detectDirection < 0) {

					double propScale = (map.getScaleX() - .1) * map.getWidth();

					//While map is larger than scrollpane view port, scale down
					if(propScale > mapLevel.getWidth()){
						map.setScaleX(map.getScaleX() - .1); 
						map.setScaleY(map.getScaleY() - .1);
						System.out.println("Zoom out");
					}
					//When next scale event would cause map to be smaller than viewport
					//Set map to be same size as view port
					else {
						//Calculate what factor map needs to be scaled by
						//to match width of view port
						double testWidth = -1 * ((mapLevel.getWidth() / map.getWidth()) - map.getScaleX());
						map.setScaleX(map.getScaleX() - testWidth);
						map.setScaleY(map.getScaleY() - testWidth);

					}
						
				}
				
				event.consume();
			}
		});

		//Touch event
		/*
		mapLevel.setOnZoom(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                mapLevel.setScaleX(mapLevel.getScaleX() * event.getZoomFactor());
                mapLevel.setScaleY(mapLevel.getScaleY() * event.getZoomFactor());
                System.out.println("Rectangle: Zoom event" +
                ", inertia: " + event.isInertia() +
                ", direct: " + event.isDirect());
         
                event.consume();
            }
        });
		/*

        mapLevel.setOnZoomStarted(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                //inc(scroll);
                System.out.println("Rectangle: Zoom event started");
                event.consume();
            }
        });

        mapLevel.setOnZoomFinished(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                //dec(scroll);
                System.out.println("Rectangle: Zoom event finished");
                event.consume();
            }
		});
		*/

		return mapLevel;

	}


	public static void main(String args[]){
		
		//initMap(args);
		//launch(args);
	}
	

	//public void start(Stage stage1)
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("I Planet Map");

		//level one
		GridPane levelOne = loadFiles("1.txt");
		GridPane levelTwo = loadFiles("2.txt");
		GridPane levelThree = loadFiles("3.txt");
		GridPane levelFour = loadFiles("4.txt");
		GridPane levelFive = loadFiles("5.txt");
		GridPane levelSix = loadFiles("6.txt");
		GridPane levelSeven = loadFiles("7.txt");
		GridPane levelEight = loadFiles("8.txt");
	
		
		//Holds overall layout
		VBox mapLayout = new VBox(8);
		ScrollPane scroll = new ScrollPane(mapLayout);
		scroll.setPannable(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setPrefHeight(400);
		
		//I can't test this in a vm, so I don't know if it works
	
		scroll.setOnZoom(new EventHandler<ZoomEvent>() {
			@Override public void handle(ZoomEvent event) {
			    scroll.setScaleX(scroll.getScaleX() * event.getZoomFactor());
			    scroll.setScaleY(scroll.getScaleY() * event.getZoomFactor());
			    System.out.println("Rectangle: Zoom event" +
				", inertia: " + event.isInertia() + 
				", direct: " + event.isDirect());
		 
			    event.consume();
			}
		});

		scroll.setOnZoomStarted(new EventHandler<ZoomEvent>() {
			@Override public void handle(ZoomEvent event) {
			    //inc(scroll);
			    System.out.println("Rectangle: Zoom event started");
			    event.consume();
			}
		});

		scroll.setOnZoomFinished(new EventHandler<ZoomEvent>() {
			@Override public void handle(ZoomEvent event) {
			    //dec(scroll);
			    System.out.println("Rectangle: Zoom event finished");
			    event.consume();
			}
		});


		//Populate vbox
		mapLayout.getChildren().add(levelOne);
		mapLayout.getChildren().add(levelTwo);
		mapLayout.getChildren().add(levelThree);
		mapLayout.getChildren().add(levelFour);
		mapLayout.getChildren().add(levelFive);
		mapLayout.getChildren().add(levelSix);
		mapLayout.getChildren().add(levelSeven);
		mapLayout.getChildren().add(levelEight);
	
		int calcSize = (22 * 25) + ((22-2) * 2);
		//primaryStage.setScene(new Scene(scroll, calcSize, 400));
		primaryStage.setScene(new Scene(scroll));
		primaryStage.show();
	}
/* 
	//Load all maps when character opens map menu
    public ScrollPane getMap(int pos, int currentLevel){
        GridPane levelOne = loadFiles("1.txt", pos, currentLevel);
        GridPane levelTwo = loadFiles("2.txt", pos, currentLevel);
        GridPane levelThree = loadFiles("3.txt", pos, currentLevel);
        GridPane levelFour = loadFiles("4.txt", pos, currentLevel);
        GridPane levelFive = loadFiles("5.txt", pos, currentLevel);
        GridPane levelSix = loadFiles("6.txt", pos, currentLevel);
        GridPane levelSeven = loadFiles("7.txt");
        GridPane levelEight = loadFiles("8.txt");
    
        
        //Holds overall layout
        VBox mapLayout = new VBox(8);
        ScrollPane scroll = new ScrollPane(mapLayout);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        
        //I can't test this in a vm, so I don't know if it works
    
        scroll.setOnZoom(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                scroll.setScaleX(scroll.getScaleX() * event.getZoomFactor());
                scroll.setScaleY(scroll.getScaleY() * event.getZoomFactor());
                System.out.println("Rectangle: Zoom event" +
                ", inertia: " + event.isInertia() +
                ", direct: " + event.isDirect());
         
                event.consume();
            }
        });

        scroll.setOnZoomStarted(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                //inc(scroll);
                System.out.println("Rectangle: Zoom event started");
                event.consume();
            }
        });

        scroll.setOnZoomFinished(new EventHandler<ZoomEvent>() {
            @Override public void handle(ZoomEvent event) {
                //dec(scroll);
                System.out.println("Rectangle: Zoom event finished");
                event.consume();
            }
        });


        //Populate vbox
        mapLayout.getChildren().add(levelOne);
        mapLayout.getChildren().add(levelTwo);
        mapLayout.getChildren().add(levelThree);
        mapLayout.getChildren().add(levelFour);
        mapLayout.getChildren().add(levelFive);
        mapLayout.getChildren().add(levelSix);
        mapLayout.getChildren().add(levelSeven);
        mapLayout.getChildren().add(levelEight);
        return scroll;
    }
*/
}
