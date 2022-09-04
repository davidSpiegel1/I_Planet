package view;

// Needed imports
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import model.Block;
import model.Character;
import model.Environment;

/**
 * 
 * @author davidspiegel
 * 
 *         Description: The IPlanet GUI version
 *
 */

@SuppressWarnings("deprecation")
public class IplanetGui extends Application{

	// Instance variables
	// We know we will need a label array
	private Label[][] labelArray;
	private GridPane pane;

	// We are going to create an arrayList
	ArrayList<Button> inList;
	// Maybe a pane for when we want to see information +
	private GridPane infoDeck;
	private Label curDescription;
	private Label curHeader;
	private Label map;
	
	// Some buttons that can change
	private Button b1;
	private Button b2;
	// We also know we will need a controller
	private Controller c;
	private Environment env;
	private int size_row;
	private int size_col;
	private int currentRow;
	private int currentCol;
	private int currentInventory;
	private boolean weaponUsed = false;
	private boolean isInventoryShowing = false;
	private boolean isMapShowing = false;
	// Need a block for using elements
	private Block useBlock;
	private String oldColor = ".";

	// Maybe some constants
	private final String TITLE_GAME = "I Planet";
	// 715
	private final int SCENE_SIZE_ROW = 715;
	private final int SCENE_SIZE_COL = 810;
	private final int ROW_CONSTRAINT = 24;
	private final int COL_CONSTRAINT = 42;
	private final double SET_MIN_HEIGHT = 36.5;
	

	public static final String MainStyle = "utilities/myCss.css";
	public static final String CharStyle = "utilities/charCss.css";
	public static final String GrassStyle = "utilities/grassCss.css";
	public static final String AshStyle = "utilities/ashPerson.css";
	public static final String JackStyle = "utilities/jackPerson.css";
	public static final String BadStyle = "utilities/badGuys.css";
	public static final String BrickStyle = "utilities/brickCss.css";
	public static final String KnifeStyle = "utilities/knifeCss.css";
	public static final String WaterStyle = "utilities/waterCss.css";
	public static final String BlockStyle = "utilities/blockCss.css";
	public static final String CharKnifeStyle = "utilities/charWithKnifeCss.css";
	public static final String GunStyle = "utilities/gunCss.css";
	

	// Within our start method, we will start the program for IPlanet.
	@Override
	public void start(Stage stage) throws Exception {
		
		// BUTTONS (May delete!!)
		b1 = new Button("");
		b2 = new Button("");

		// We must have a character
		Character chacracter = new Character(0, 0, "No NAME FOR NOW");
		this.c = new Controller(chacracter);
		env = c.getEnv();
	
		currentInventory = 3;
		// Descriptions and headers for new pane
		curDescription = new Label("NOnull");
		curHeader = new Label("NOull");
		map = new Label("                 Map");
		map.setStyle("-fx-text-fill: grey");
		
		/// Making a map label with a button in it
		//map = makeMap(map);
		
		inList = new ArrayList<Button>();
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
		// curDescription.setMinHeight(650);
		curDescription.setFont(new Font("Arial", 20));
		curDescription.setStyle("-fx-text-fill: cadetblue");
		curHeader.setAlignment(Pos.BOTTOM_LEFT);
		curHeader.setMaxWidth(Double.MAX_VALUE);
		// curHeader.setMinHeight(650);
		curHeader.setFont(new Font("Arial", 30));
		curHeader.setStyle("-fx-text-fill: cornflowerblue");
		/*
		 * grabItem = new Button("Collect block");
		 * grabItem.setStyle("-fx-text-fill: cornflowerblue"); grabItem.setFont(new
		 * Font("Arial",30));
		 */

		infoDeck.add(map,3,0);
		infoDeck.add(curDescription, 2, 0);
		infoDeck.add(curHeader, 1, 0);

		// Filling up the label array
		for (int i = 0; i <= size_row - 1; i++) {
			for (int j = 0; j <= size_col - 1; j++) {
				// labelArray[i][j] = new Label(((Block) env.getMap().get(i).get(j)).getKey());
				labelArray[i][j] = new Label();
				// If the block is grass

				getProperColor(labelArray[i][j], ((Block) env.getMap().get(i).get(j)).getKey());

				// Changing the look of the label
				labelArray[i][j].setFont(new Font("Arial", 30));
				labelArray[i][j].setMaxWidth(Double.MAX_VALUE);
				labelArray[i][j].setAlignment(Pos.CENTER);
				// labelArray[i][j].setStyle(null);
				labelArray[i][j]
						.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				pane.add(labelArray[i][j], i, j);
				// getProperColor(labelArray[i][j],((Block)
				// env.getMap().get(i).get(j)).getKey());
				// Adding a new row constraint
				RowConstraints rCon = new RowConstraints();
				// rCon.setValignment(VPos.CENTER);
				rCon.setPercentHeight(100 / size_row);
				rCon.setVgrow(Priority.NEVER);
				// rCon.setFillHeight(true);
				ColumnConstraints cCon = new ColumnConstraints();
				// cCon.setValignment(VPos.CENTER);
				cCon.setPercentWidth(100 / size_col);
				cCon.setHgrow(Priority.NEVER);
				// cCon.setFillWidth(true);
				pane.getColumnConstraints().add(new ColumnConstraints(COL_CONSTRAINT));
				pane.getRowConstraints().add(rCon);
			}
		}
		infoDeck.setAlignment(Pos.BOTTOM_CENTER);
		infoDeck.setBackground(
				new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(7.0), Insets.EMPTY)));
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setVgap(10);
		pane.setAlignment(Pos.TOP_CENTER);

		// infoDeck.setAlignment(Pos.BOTTOM_CENTER);
		infoDeck.setVgap(57);
		infoDeck.setHgap(10);

		// Using vbox
		VBox vb = new VBox(infoDeck, pane);

		vb.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;");
		vb.setSpacing(0);

		vb.setFillWidth(true);

		vb.setStyle("-fx-border-radius: 6.0");

		// Setting the scene
		Scene scene = new Scene(vb, SCENE_SIZE_ROW, SCENE_SIZE_COL);
		// scene.getStylesheets().add(MainStyle);
		// Needing a handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {

				// Must update entire enviornment

				if (e.getCode().equals(KeyCode.DOWN)) {
					c.moveCharacter("D");

					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.RIGHT)) {
					c.moveCharacter("S");

					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.UP)) {
					c.moveCharacter("A");
					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.LEFT)) {
					c.moveCharacter("W");
					setTextToDisplay(c.getEnv());
				}
				if (e.getCode().equals(KeyCode.SPACE)) {
					Block b = c.getCurrentBlock();
					deleteCurrentInventory();
					displayInteraction(b);
					isInventoryShowing = false;
					setTextToDisplay(c.getEnv());
				}
				if(e.getCode().equals(KeyCode.M) ) {
					try {
						map = makeMap();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getCode().equals(KeyCode.G)) {
					if (!weaponUsed) {

						Block b = c.getCurrentBlock();
						Button b1 = new Button(b.getKey());
						b1.setFocusTraversable(false);
						b1.setOnAction((ev) -> {
							displayOption(inList.size() - 1, b1.getLayoutX(), b1.getLayoutY());
						});
						// b1.setDisable(true);
						// infoDeck.add(b1,currentInventory,0);
						inList.add(b1);
						c.putInBag(b);
						currentInventory++;
						if (isInventoryShowing) {
						deleteCurrentInventory();
						createInventory(c.getInventory());
						displayInventory(c.getInventory());
						}
						
					} else {
						System.out.println(c.getCharRow()-3);
						System.out.println(c.getCharCol()+3);
						c.hit(c.getCharCol()+1,c.getCharRow()-1);
						

					}
					setTextToDisplay(c.getEnv());
					

				}
				if (e.getCode().equals(KeyCode.I)) {
					deleteCurrentInventory();
					createInventory(c.getInventory());
					displayInventory(c.getInventory());
					isInventoryShowing = true;
					

				}

				updateLabelsEvery(c.getCharRow(), c.getCharCol());
			
				if (c.isGameOver()) {
					c = displayGameOver();
					setTextToDisplay(c.getEnv());
				}

			}

		});
	

		stage.setScene(scene);
		stage.setTitle(TITLE_GAME);
		stage.show();
		stage.setOnCloseRequest((e)->{
			
			stage.close();
		});
		

	}

	private Label makeMap() throws Exception {
		map.setStyle("-fx-text-fill: cornflowerblue");
		
		displayMap(isMapShowing,map.getLayoutX(),map.getLayoutY());
		
		
		
		
		
		return map;
	}

	private void displayMap (boolean isMapShowing,double layoutX, double layoutY) throws Exception {
		MapGUI map = new MapGUI();
		map.displayOption(this.map,c.getCharRow(),c.getCharCol(),isMapShowing,layoutX,layoutY);
		//map2.setStyle("-fx-text-fill: grey");
		
		
	}

	// Display option, for when we want to use an object
	public void displayOption(int curIndex, double x, double y) {
		Stage badPop1 = new Stage();
		int endingHeight = 20;

		b1.setText("Remove");
		if (weaponUsed) {
			b2.setText("Disable All");
		} else {
			b2.setText("Use");
		}
		b2.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-padding: 10;" + "-fx-"
				+ "-fx-border-width: .3;" + "-fx-text-fill: red");
		b1.setStyle(
				"-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;" + "-fx-text-fill: red");
		VBox vbox2;
		// If you can use the item use will be there
		if (c.seeIfCanBeUsed(inList.get(curIndex).getText())) {
			vbox2 = new VBox(3, b2, b1);
		} else {
			vbox2 = new VBox(3, b1);
		}

		vbox2.setBackground(
				new Background(new BackgroundFill(Color.rgb(91, 188, 66), CornerRadii.EMPTY, Insets.EMPTY)));

		Scene sc1 = new Scene(vbox2, 60, 30);

		// Curr x and y: 305,77
		badPop1.setX(x + 305);
		badPop1.setY(y + 77);
		badPop1.setTitle("Game Over");
		badPop1.setResizable(false);

		Timer animTimer = new Timer();
		animTimer.scheduleAtFixedRate(new TimerTask() {
			int in = 0;
			@Override
			public void run() {
				if (in < endingHeight) {
					badPop1.setHeight(badPop1.getHeight() + 3);
				} else {
					this.cancel();
				}
				in++;
			}
		}, 80, 2);

		badPop1.setScene(sc1);
		badPop1.show();

		b2.setOnAction((e) -> {
			if (!weaponUsed) {
				if (c.seeIfCanBeUsed(inList.get(curIndex).getText())) {
					weaponUsed = true;
				}
			} else {
				if (c.seeIfCanBeUsed(inList.get(curIndex).getText())) {
					weaponUsed = false;
				}

			}
			vbox2.getChildren().clear();

			badPop1.close();

		});
		b1.setOnAction((e) -> {
			c.deleteElementFromInventory(curIndex);
			inList.remove(curIndex);
			currentInventory--;

			deleteCurrentInventory();
			createInventory(c.getInventory());
			displayInventory(c.getInventory());

			vbox2.getChildren().clear();
			badPop1.close();

		});

	
		badPop1.setOnCloseRequest((e)->{
			vbox2.getChildren().clear();
			badPop1.close();
		});
		

	}

	// May delete
	public void translateObject(Node n, double dur, int cyle, int howFar, boolean isPoly) {
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.millis(dur));
		translate.setNode(n);
		;
		translate.setToX(howFar);

		translate.setCycleCount(cyle);
		translate.setAutoReverse(true);
		translate.play();
	}

	public Controller displayGameOver() {
		Stage badPop1 = new Stage();
		Label l2 = new Label("You Lost :(");
		Button b1 = new Button("Play again?");
		// Needed for next round
		Character chacracter = new Character(0, 0, "No NAME FOR NOW");
		this.c = new Controller(chacracter);
		currentInventory = 0;
		inList.clear();
		env = c.getEnv();
		
		weaponUsed = false;

		l2.setFont(new Font("ariel", 50));
		l2.setStyle(
				"-fx-border-color: GREY;" + "-fx-border-radius: 10.0;" + "-fx-border-width: 5;" + "-fx-text-fill: red");

		l2.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(6.0), Insets.EMPTY)));

		// l2.setMaxWidth(Double.MAX_VALUE);
		l2.setAlignment(Pos.BASELINE_LEFT);

		b1.setFont(new Font("ariel", 50));
		b1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 10.0;" + "-fx-border-width: 5;"
				+ "-fx-text-fill: green");
		b1.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(6.0), Insets.EMPTY)));
		b1.setAlignment(Pos.BASELINE_RIGHT);
		// What happends if you start the game over
		b1.setOnAction((e) -> {
			
			badPop1.close();

		});

		VBox vbox2 = new VBox(3, l2, b1);
		vbox2.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), CornerRadii.EMPTY, Insets.EMPTY)));
		vbox2.setAlignment(Pos.CENTER);
		Scene sc1 = new Scene(vbox2, SCENE_SIZE_ROW, SCENE_SIZE_COL);

		badPop1.setScene(sc1);
		badPop1.setTitle("Game Over");
		badPop1.show();
		
		return c;
	}

	private void createInventory(ArrayList<Block> inventory) {
		int curVal = 0;
		for (int i = 3; i <= this.currentInventory - 1; i++) {
			infoDeck.add(inList.get(curVal), i, 0);
			curVal++;

		}

	}
	private void displayInventory(ArrayList<Block> inventory) {

		this.curDescription.setText("");
		this.curHeader.setText("Current Inventory:");

	}

	private void deleteCurrentInventory() {

		infoDeck.getChildren().clear();
		infoDeck.add(map, 3, 0);
		infoDeck.add(curHeader, 2, 0);
		infoDeck.add(curDescription, 1, 0);

		infoDeck.setGridLinesVisible(true);

	}

	private void displayInteraction(Block b) {
		/*
		 * Stage interactionPageStage = new Stage(); Label l1 = new Label(b.getKey());
		 * Label l2 = new Label(b.getDescription()); VBox vb = new VBox(1,l1,l2);
		 * 
		 * Scene newScene = new Scene(vb,SCENE_SIZE_ROW,300);
		 * 
		 * interactionPageStage.setScene(newScene);
		 * interactionPageStage.setTitle("Interaction"); interactionPageStage.show();
		 */

		this.curDescription.setText(b.getDescription() + " Press g to grab");
		this.curHeader.setText(b.getKey());
		if (b.getKey().equals("O")) {
			c.changeLevel();
			updateLabels();
		}
		if (b.getKey().equals("B")) {
			c.changeLevelBack();
			updateLabels();
		}

	}

	private void setTextToDisplay(Environment env) {
		this.env = env;
		for (int i = 0; i <= size_row - 1; i++) {
			for (int j = 0; j <= size_col - 1; j++) {
				getProperColor(labelArray[i][j], ((Block) env.getMap().get(i).get(j)).getKey());
			}
		}
	}

	private void updateLabels() {
		ArrayList<ArrayList> newGrid = c.getEnvMap();
		for (int i = 0; i <= size_row - 1; i++) {
			for (int j = 0; j <= size_col - 1; j++) {
				if (labelArray[i][j] != null) {
					// labelArray[i][j].setText(((Block) newGrid.get(i).get(j)).getKey());

				}
			}
		}
	}

	private void updateLabelsEvery(int row, int col) {
		ArrayList<ArrayList> newGrid = c.getEnvMap();
		for (int i = 0; i <= size_row - 1; i++) {
			for (int j = 0; j <= size_col - 1; j++) {
				if (labelArray[i][j] != null) {

					if (((Block) newGrid.get(i).get(j)).getKey().equals("E")) {

						int newRow = i;
						int newCol = j;
						// While the index is not allowed
						if (newRow > row) {
							newRow--;
						} else if (newRow < row) {
							newRow++;
						}
						if (newCol > col) {
							newCol--;
						} else if (newCol < col) {
							newCol++;
						}
						if (!(newRow > size_row - 1 || newRow < 0) && !(newCol > size_col - 1 || newCol < 0)) {
							if (!((Block) c.getEnvMap().get(newRow).get(newCol)).getKey().equals("_")
									&& !((Block) c.getEnvMap().get(newRow).get(newCol)).getKey().equals("|")
									&& !((Block) c.getEnvMap().get(newRow).get(newCol)).getKey().equals("C")) {

								oldColor = ((Block) c.getEnvMap().get(newRow).get(newCol)).getKey();
								System.out.println(oldColor + " OLDCOLOR");
								c.moveEnemy(i, j, newRow, newCol);

							}
						}

					}

					// Where we make everything move

				}
			}
		}
	}

	private void getProperColor(Label label, String key) {
		// If the block is grass (Big)
		if (key.equals("G")) {
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0, 110, 28), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		// If the block is grass (small)
		else if (key.equals("g")) {
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(109, 255, 77), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (key.equals("E")) {
			if (!oldColor.equals("E")) {
				getProperColor(label, oldColor);
			}
			label.setGraphic(buttonBuilder("E"));
			// label.setBackground(
			// new Background(new BackgroundFill(Color.rgb(113, 0, 0), CornerRadii.EMPTY,
			// Insets.EMPTY)));

		}

		else if (key.equals("C")) {
			if (weaponUsed) {
				getProperColor(label, c.getCurrentBlock().getKey());
				label.setGraphic(buttonBuilder("CK"));
			} else {
				getProperColor(label, c.getCurrentBlock().getKey());
				label.setGraphic(buttonBuilder("C"));
			}

		} else if (key.equals("_") || key.equals("|")) {
			label.setGraphic(buttonBuilder("|"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(88, 103, 110), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (key.equals("W")) {
			label.setGraphic(buttonBuilder("W"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0, 89, 160), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (key.equals("s")) {
			label.setGraphic(buttonBuilder("s"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0, 110, 28), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (key.equals("t")) {

			// Or maybe we can build an object with a node

			label.setGraphic(buttonBuilder("t"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0, 110, 28), CornerRadii.EMPTY, Insets.EMPTY)));

		} else if (key.equals("O")) {

			// Or maybe we can build an object with a node
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(20, 100, 140), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (key.equals("B")) {

			// Or maybe we can build an object with a node
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(20, 100, 140), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (key.equals("K")) {

			// Or maybe we can build an object with a node
			label.setGraphic(buttonBuilder("K"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
		}

		else if (key.equals("P")) {
			label.setGraphic(buttonBuilder("P"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(0, 110, 28), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else if (key.equals("X")) {
			label.setGraphic(buttonBuilder("X"));
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		/*
		 * else if (key.equals(".")) { label.setGraphic(buttonBuilder("."));
		 * label.setBackground( new Background(new BackgroundFill(Color.rgb(137, 110,
		 * 77), CornerRadii.EMPTY, Insets.EMPTY))); }
		 */

		// If the block is dirt
		else {
			label.setGraphic(null);
			label.setBackground(
					new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		label.setMinHeight(SET_MIN_HEIGHT);
		;
	}

// How to make your own shape in javaFX
	public Button buttonBuilder(String type) {
		Button b1 = new Button(type);
		if (type.equals("t")) {
			// SVGPath path = new SVGPath();

			b1.getStylesheets().add(MainStyle);
		}
		// public void translateObject(Node n, double dur, int cyle, int howFar, boolean
		// isPoly) {
		else if (type.equals("C")) {
			b1.getStylesheets().add(CharStyle);
			translateUp(b1, .2, 3, 1, 0, 0);

		} else if (type.equals("G")) {
			b1.getStylesheets().add(GrassStyle);
		} else if (type.equals("P")) {
			b1.getStylesheets().add(AshStyle);
		} else if (type.equals("s")) {
			b1.getStylesheets().add(JackStyle);
		} else if (type.equals("E")) {
			b1.getStylesheets().add(BadStyle);
			translateUp(b1, .2, 3, 1, 0, 0);
		} else if (type.equals("|") || type.equals("_")) {
			b1.getStylesheets().add(BrickStyle);
		} else if (type.equals("K")) {
			b1.getStylesheets().add(KnifeStyle);
		} else if (type.equals("W")) {
			b1.getStylesheets().add(WaterStyle);

		} else if (type.equals(".")) {
			b1.getStylesheets().add(BlockStyle);
		} else if (type.equals("CK")) {
			b1.getStylesheets().add(CharKnifeStyle);
			//this.curHeader.setText("Use G to hit!");
			translateUp(b1, .2, 3, 1, 0, 0);
		}
		 else if (type.equals("X")) {
		b1.getStylesheets().add(GunStyle);
		//translateUp(b1, .2, 3, 1, 0, 0);
		
		 }
		b1.setText("");

		b1.setBackground(new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY)));
		b1.setDisable(true);
		return b1;
	}

	public void displayContinousAnimation(Node n, double dur, double up, double down, double ogDown, double ogUp) {
		Path path = new Path();
		path.getElements().add(new MoveTo(up, down));
		path.getElements().add(new CubicCurveTo(ogDown + 20, ogDown + 5, ogUp + 10, ogUp + 18, ogDown + 5, ogUp + 18));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(dur));
		pathTransition.setNode(n);
		pathTransition.setPath(path);
		pathTransition.play();
	}

	public void translateUp(Node n, double dur, double up, double down, double ogDown, double ogUp) {
		Path path = new Path();
		path.getElements().add(new MoveTo(up, down));
		path.getElements().add(new CubicCurveTo(ogDown + 20, ogDown + 5, ogUp + 10, ogUp + 18, ogDown + 5, ogUp + 18));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(dur));
		pathTransition.setNode(n);
		pathTransition.setPath(path);
		pathTransition.play();
	}



}


