package view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Block;
import model.MapModel;

public class MapGUI {
	
	public ArrayList<Label>labelArray;
	public HBox pane;
	public ScrollPane sp;
	protected Label map;
	


	public void displayOption(Label map,int curRow,int curCol,boolean isMapShowing,double x, double y) throws Exception {
		Stage badPop1 = new Stage();
		this.map = map;
		int endingHeight = 80;
				
				labelArray = new ArrayList<Label>();
				pane = new HBox();
			
				// First, we will make a string to get the map model
				String str = "";
				int j1 = 0;
				MapModel m = new MapModel(curRow,curCol);
				ArrayList<ArrayList>arr = m.getMapList();
				for(int i = 0; i<=arr.size()-1;i++) {
					for (int j = 0; j<= arr.get(i).size()-1;j++) {
						Label l1 = createLabelArray((ArrayList)arr.get(i).get(j));
						l1.setStyle("-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
						l1.setMinSize(20, 50);
						
						pane.getChildren().add(l1);
						
					}
					
					
				
					
				}
				
				
				pane.setPadding(Insets.EMPTY);
				sp = new ScrollPane(pane);
				Scene sc1 = new Scene(sp, 400, 350);

				// Curr x and y: 305,77
				badPop1.setX(x + 350);
				badPop1.setY(y + 77);
				
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
				badPop1.setTitle("Map");
				badPop1.show();
				
				// When we click on the map we will close
				EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
					

					

					private Label map1 = map;

					@Override
					public void handle(KeyEvent e) {
						if (e.getCode().equals(KeyCode.M)) {
							map1.setStyle("-fx-text-fill: grey");
							badPop1.close();
						}
						
					}};
					badPop1.addEventHandler(KeyEvent.KEY_PRESSED,handler);
					
				
				
				
				// Not Working, I Think
				if (!badPop1.isFocused()) {
					
					isMapShowing = false;
					badPop1.close();
				}
				
				
				badPop1.setOnCloseRequest((e)->{
					
					badPop1.close();
				});


				
		
		
		
	}

	private Label createLabelArray(ArrayList arrayList) {
		String str = "";
		Label l = new Label();
		GridPane gp = new GridPane();
		for (int j = 0;j<=arrayList.size()-1;j++ ) {
				//str +=  ((Block) arrayList.get(j)).getKey();
				//str+= "\n";
				Label l1 = new Label(((Block) arrayList.get(j)).getKey());
				if (((Block) arrayList.get(j)).getKey().equals("C")) {
				l1.setStyle("-fx-font-size: 30.0;"+"-fx-base: LightGrey;"+"-fx-text-fill: rgba(137, 110, 77);"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				
				}
				else if (((Block) arrayList.get(j)).getKey().equals("W")) {
					l1.setStyle("-fx-font-size: 30.0;"+"-fx-base: LightGrey;"+"-fx-text-fill: rgba(20, 100, 140);"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: rgba(0, 100, 21);" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				else if (((Block) arrayList.get(j)).getKey().equals("t")) {
					l1.setStyle("-fx-font-size: 30.0;"+"-fx-text-fill: rgba(34, 122, 14);"+"-fx-background-color: DarkGreen;"+"-fx-border-color: rgba(0, 100, 21);" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				else if (((Block) arrayList.get(j)).getKey().equals("G")) {
					l1.setStyle("-fx-font-size: 30.0;"+"-fx-text-fill: rgba(0, 110, 28);"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: rgba(0, 100, 21);" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				else if (((Block) arrayList.get(j)).getKey().equals("|") ||((Block) arrayList.get(j)).getKey().equals("_") ) {
					l1.setStyle("-fx-font-size: 30.0;"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: rgba(0, 100, 21);" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				else if (((Block) arrayList.get(j)).getKey().equals("E")) {
					l1.setStyle("-fx-font-size: 30.0;"+"-fx-text-fill: rgba(99, 66, 17);"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: rgba(0, 100, 21);" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				else if (((Block) arrayList.get(j)).getKey().equals(".")) {
					l1.setStyle("-fx-font-size: 30.0;"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				else {
				l1.setStyle("-fx-font-size: 30.0;"+"-fx-text-fill: rgba(137, 110, 77);"+"-fx-background-color: rgba(0, 100, 21);"+"-fx-border-color: GREY;" + "-fx-border-radius: 6.0;" + "-fx-border-width: .3;");
				}
				l1.setMinWidth(40);
				l1.setMinSize(5, 20);
				gp.add(l1, 0, j);
				gp.setAlignment(Pos.TOP_CENTER);
				gp.setVgap(2);
				
				
				
			}
		
		l.setGraphic(gp);
		l.setContentDisplay(ContentDisplay.RIGHT);
		
		return l;
	}


}
