// Trying to implement 3D block in javafx
//
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.PerspectiveCamera;
import java.io.IOException;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
//import org.scenicview.ScenicView;

public class Block3D extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-10);

        FXMLLoader loader = new FXMLLoader(Block3D.class.getResource("/suzzane.fxml"));
        loader.load();
        MeshView mesh = (MeshView) loader.getRoot();
        Group group3d = new Group(camera, mesh);
        Button b1 = new Button("Here");
        b1.setGraphic(group3d);
        HBox hb = new HBox();
        hb.getChildren().add(b1);
        Scene scene = new Scene(hb, 640, 480, true, SceneAntialiasing.BALANCED);
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.setTitle("suzzane import");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
