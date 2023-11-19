// Kyle !!
package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Label;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import javafx.scene.input.ZoomEvent;
import javafx.scene.text.TextAlignment;


//public class MapGUI2 extends Application
public class MapGUI2
{
    
    
	public static GridPane loadFiles(String levelFile, int pos, int currentLevel)
	{
		GridPane level = new GridPane();
		//Java yells at me if I don't have a try catch statment
		try
		{
			File file = new File("utilities/levels/" + levelFile);
			
			//Make sure file exists before reading
			if(file.exists())
			{
				FileInputStream fileStream = new FileInputStream("utilities/levels/" + levelFile);

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
		level.setMinSize(400, 400);
		level.setPadding(new Insets(10, 10, 10, 10));
		level.setVgap(2);
		level.setHgap(2);
		return level;
	}



	public static void main(String args[]){
		
		//initMap(args);
		//launch(args);
	}
	

	//public void start(Stage stage1)
	public void start(Stage primaryStage, int pos, int currentLevel)
	{
		primaryStage.setTitle("I Planet Map");

		//level one
		GridPane levelOne = loadFiles("levelOne.txt", pos, currentLevel);
		GridPane levelTwo = loadFiles("levelTwo.txt", pos, currentLevel);
		GridPane levelThree = loadFiles("levelThree.txt", pos, currentLevel);
		GridPane levelFour = loadFiles("levelFour.txt", pos, currentLevel);
		GridPane levelFive = loadFiles("levelFive.txt", pos, currentLevel);
		GridPane levelSix = loadFiles("levelSix.txt", pos, currentLevel);
		GridPane levelSeven = loadFiles("levelSeven.txt", pos, currentLevel);
		GridPane levelEight = loadFiles("levelEight.txt", pos, currentLevel);
	
		
		//Holds overall layout
		VBox mapLayout = new VBox(8);
		ScrollPane scroll = new ScrollPane(mapLayout);
		scroll.setPannable(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setFitToWidth(true);
		
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
	
		primaryStage.setScene(new Scene(scroll, 610, 400));
		primaryStage.show();
	}
    
    public ScrollPane getMap(int pos, int currentLevel){
        /*GridPane levelOne = loadFiles("levelOne.txt", pos, currentLevel);
        GridPane levelTwo = loadFiles("levelTwo.txt", pos, currentLevel);
        GridPane levelThree = loadFiles("levelThree.txt", pos, currentLevel);
        GridPane levelFour = loadFiles("levelFour.txt", pos, currentLevel);
        GridPane levelFive = loadFiles("levelFive.txt", pos, currentLevel);
        GridPane levelSix = loadFiles("levelSix.txt", pos, currentLevel);*/
        GridPane levelSeven = loadFiles("levelSeven.txt", pos, currentLevel);
        GridPane levelEight = loadFiles("levelEight.txt", pos, currentLevel);
    
        
        //Holds overall layout
        VBox mapLayout = new VBox(8);
        ScrollPane scroll = new ScrollPane(mapLayout);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setFitToWidth(true);
        
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
        /*mapLayout.getChildren().add(levelOne);
        mapLayout.getChildren().add(levelTwo);
        mapLayout.getChildren().add(levelThree);
        mapLayout.getChildren().add(levelFour);
        mapLayout.getChildren().add(levelFive);
        mapLayout.getChildren().add(levelSix);*/
        mapLayout.getChildren().add(levelSeven);
        mapLayout.getChildren().add(levelEight);
        return scroll;
    }

}
