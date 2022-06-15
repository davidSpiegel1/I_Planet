package view;

import java.io.File;
import utilities.*;
// Needed imports
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import controller.Controller;
import model.*;
import model.Character;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * 
 * @author davidspiegel
 * 
 * Description: The IPlanet GUI version
 *
 */


public class IplanetGui extends Application{
	
	// Instance variables
	// We know we will need a label array
	private Label [][] labelArray;
	private GridPane pane;
	// Maybe a pane for when we want to see information + 
	private GridPane infoDeck;
	private Label curDescription;
	private Label curHeader;
	
	
	// We also know we will need a controller
	private Controller c;
	private Environment env;
	private int size_row;
	private int size_col;
	private int currentRow;
	private int currentCol;
	
	// Maybe some constants
	private final String TITLE_GAME = "I Planet";
	// 715
	private final int SCENE_SIZE_ROW = 715;
	private final int SCENE_SIZE_COL = 810;
	private final int ROW_CONSTRAINT = 24;
	private final int COL_CONSTRAINT = 42;
	
	public static final String MainStyle = "utilities/myCss.css";
	public static final String CharStyle = "utilities/charCss.css";
	public static final String GrassStyle = "utilities/grassCss.css";
	public static final String AshStyle = "utilities/ashPerson.css";
	public static final String JackStyle = "utilities/jackPerson.css";
	
	
	// Within our start method, we will start the program for IPlanet.
	@Override
	public void start(Stage stage) throws Exception {
		
		// We must have a character
		Character chacracter = new Character(0,0,"No NAME FOR NOW");
		this.c = new Controller(chacracter);
		env = c.getEnv();
		
		// Descriptions and headers for new pane
		curDescription = new Label("NOnull");
		curHeader = new Label("NOull");
		
		// Needing the sizes to be able to change
		size_row = env.getMap().size();
		size_col = env.getMap().get(0).size();
		
		// Working on just getting something going
		labelArray = new Label[size_row][size_col];
		pane = new GridPane();
		infoDeck = new GridPane();
		// PUT IN MODULE!! EVENTRALLY!!
		// Adding the description and header to info deck
		curDescription.setAlignment(Pos.BOTTOM_RIGHT);
		curDescription.setMaxWidth(Double.MAX_VALUE);
		//curDescription.setMinHeight(650);
		curDescription.setFont(new Font("Arial",20));
		curDescription.setStyle("-fx-text-fill: cadetblue");
		curHeader.setAlignment(Pos.BOTTOM_LEFT);
		curHeader.setMaxWidth(Double.MAX_VALUE);
		//curHeader.setMinHeight(650);
		curHeader.setFont(new Font("Arial",30));
		curHeader.setStyle("-fx-text-fill: cornflowerblue");
		/*grabItem = new Button("Collect block");
		grabItem.setStyle("-fx-text-fill: cornflowerblue");
		grabItem.setFont(new Font("Arial",30));*/
		
		//infoDeck.add(grabItem,3,0);
		infoDeck.add(curDescription,2,0);
		infoDeck.add(curHeader,1,0);
		
		
		// Filling up the label array
		for (int i = 0; i<= size_row-1;i++) {
			for (int j = 0; j<= size_col-1;j++) {
				//labelArray[i][j] = new Label(((Block) env.getMap().get(i).get(j)).getKey());
				labelArray[i][j] = new Label();
				// If the block is grass
				getProperColor(labelArray[i][j],((Block) env.getMap().get(i).get(j)).getKey());
				
				// Changing the look of the label
				labelArray[i][j].setFont(new Font("Arial",30));
				labelArray[i][j].setMaxWidth(Double.MAX_VALUE);
				labelArray[i][j].setAlignment(Pos.CENTER);
				labelArray[i][j].setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;");
				pane.add(labelArray[i][j], i, j);
				// Adding a new row constraint
				RowConstraints rCon = new RowConstraints();
				//rCon.setValignment(VPos.CENTER);
				rCon.setPercentHeight(100/size_row);
				rCon.setVgrow(Priority.NEVER);
				//rCon.setFillHeight(true);
				ColumnConstraints cCon = new ColumnConstraints();
				//cCon.setValignment(VPos.CENTER);
				cCon.setPercentWidth(100/size_col);
				cCon.setHgrow(Priority.NEVER);
				//cCon.setFillWidth(true);
				pane.getColumnConstraints().add(new ColumnConstraints(COL_CONSTRAINT));
				pane.getRowConstraints().add(rCon);
			}
		}
		infoDeck.setAlignment(Pos.BOTTOM_CENTER);
		infoDeck.setBackground(
				new Background(new BackgroundFill(Color.rgb(42, 0, 67), CornerRadii.EMPTY, Insets.EMPTY)));
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setVgap(10);
		pane.setAlignment(Pos.TOP_CENTER);
		
	
		
		
		
		//infoDeck.setAlignment(Pos.BOTTOM_CENTER);
		infoDeck.setVgap(57);
		infoDeck.setHgap(10);
		
		
		
		
		// Using vbox
		VBox vb = new VBox(infoDeck,pane);
		
		vb.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;");
		vb.setSpacing(0);
		
		
		vb.setFillWidth(true);
		
		vb.setStyle("-fx-border-radius: 6.0");
	
		// Setting the scene
		Scene scene = new Scene(vb,SCENE_SIZE_ROW,SCENE_SIZE_COL);
		//scene.getStylesheets().add(MainStyle);
		// Needing a handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent e) {
				
				// Must update entire enviornment 
				
				if (e.getCode().equals(KeyCode.DOWN)) {
					c.moveCharacter("D");
					updateLabelsEvery(0,-1);
					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.RIGHT)) {
					c.moveCharacter("S");
					updateLabelsEvery(-1,0);
					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.UP)) {
					c.moveCharacter("A");
					updateLabelsEvery(0,1);
					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.LEFT)) {
					c.moveCharacter("W");
					updateLabelsEvery(1,0);
					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.SPACE)) {
					Block b = c.getCurrentBlock();
					displayInteraction(b);
				}
				if (e.getCode().equals(KeyCode.G)) {
					Block b = c.getCurrentBlock();
					c.putInBag(b);
				}
				if (e.getCode().equals(KeyCode.I)) {
					displayInventory(c.getInventory());
				}
				
			}

			
		});
		
		stage.setScene(scene);
		stage.setTitle(TITLE_GAME);
		stage.show();
		
		
	}
	
	private void displayInventory(ArrayList<Block> inventory) {
		String in = "[";
		for (int i = 0; i<= inventory.size()-1;i++) {
			if (i != inventory.size()-1) {
			in += inventory.get(i).getKey()+",";
			}else {
				in += inventory.get(i).getKey()+"]";
			}
		}
		this.curDescription.setText(in);
		this.curHeader.setText("Current Inventory");
		
	}
	private void displayInteraction(Block b) {
		/*Stage interactionPageStage = new Stage();
		Label l1 = new Label(b.getKey());
		Label l2 = new Label(b.getDescription());
		VBox vb = new VBox(1,l1,l2);
		
		Scene newScene = new Scene(vb,SCENE_SIZE_ROW,300);
		
		interactionPageStage.setScene(newScene);
		interactionPageStage.setTitle("Interaction");
		interactionPageStage.show();*/
		
		this.curDescription.setText(b.getDescription()+" Press g to grab");
		this.curHeader.setText(b.getKey());
		if (b.getKey().equals("O")) {
			c.changeLevel();
			updateLabels();
		}
		
		
	}
	private void setTextToDisplay(Environment env) {
		this.env = env;
		for (int i = 0; i<= size_row-1;i++) {
			for (int j = 0; j<= size_col-1;j++) {
				//labelArray[i][j].setText(((Block) env.getMap().get(i).get(j)).getKey());
				getProperColor(labelArray[i][j],((Block) env.getMap().get(i).get(j)).getKey());
			}
		}
	}
	private void updateLabels() {
		ArrayList<ArrayList>newGrid = c.getEnvMap();
		for (int i = 0; i<= size_row-1;i++) {
			for (int j = 0; j<= size_col-1;j++) {
				if (labelArray[i][j] != null) {
				//labelArray[i][j].setText(((Block) newGrid.get(i).get(j)).getKey());
				
				}
			}
		}
	}
	private void updateLabelsEvery(int row,int col) {
		ArrayList<ArrayList>newGrid = c.getEnvMap();
		for (int i = 0; i<= size_row-1;i++) {
			for (int j = 0; j<= size_col-1;j++) {
				if (labelArray[i][j] != null) {
					
					if (((Block) newGrid.get(i).get(j)).getKey().equals("E")) {
						
						
						int newRow = row+i;
						int newCol = col+j;
						// While the index is not allowed
						if (!(newRow> size_row-1 || newRow < 0) && !(newCol> size_col-1 || newCol <0)) {
							c.moveEnemy(i, j, newRow, newCol);
							// Must update everything (i think)
							labelArray[i][j].setBackground(
									new Background(new BackgroundFill(Color.rgb(113, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
							//labelArray[newRow][newCol].setText(((Block) newGrid.get(newRow).get(newCol)).getKey());
						}
						
					}
		
				
				}
			}
		}
	}
	private void getProperColor(Label label, String key) {
		// If the block is grass (Big)
		if (key.equals("G")){
			label.setGraphic(null);
		label.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 110, 28), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		// If the block is grass (small)
		else if(key.equals("g")){
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(109, 255, 77), CornerRadii.EMPTY, Insets.EMPTY)));
			}
		else if (key.equals("E")) {
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(113, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
			
		}
		
		else if (key.equals("C")) {
			//label.setGraphic(buttonBuilder("C"));
			getProperColor(label,c.getCurrentBlock().getKey());
			label.setGraphic(buttonBuilder("C"));
			
			
		}
		else if (key.equals("_") || key.equals("|")) {
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(88,103,110), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else if (key.equals("W")) {
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0,89,160), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else if (key.equals("s")) {
			label.setGraphic(buttonBuilder("s"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(244,227,169), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else if (key.equals("t")) {
			
			// Or maybe we can build an object with a node
			
			label.setGraphic(buttonBuilder("t"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0, 110, 28), CornerRadii.EMPTY, Insets.EMPTY)));
			
		}
		else if (key.equals("O")) {
			
			// Or maybe we can build an object with a node
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(20, 100, 140), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else if (key.equals("P")) {
			label.setGraphic(buttonBuilder("P"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(20, 100, 140), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
		// If the block is dirt
		else {
		label.setGraphic(null);
		label.setBackground(
				new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		//label.setDisable(true);
	}



// How to make your own shape in javaFX
public Button buttonBuilder(String type) {
	Button b1 = new Button(type);
	if (type.equals("t")) {
		//SVGPath path = new SVGPath();
		
		b1.getStylesheets().add(MainStyle);
	}
	else if (type.equals("C")) {
		b1.getStylesheets().add(CharStyle);
	}
	else if(type.equals("G")) {
		b1.getStylesheets().add(GrassStyle);
	}
	else if (type.equals("P")) {
		b1.getStylesheets().add(AshStyle);
	}
	else if (type.equals("s")) {
		b1.getStylesheets().add(JackStyle);
	}
	b1.setText("");

	b1.setBackground(
			new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
	b1.setDisable(true);
	return b1;
}
}


