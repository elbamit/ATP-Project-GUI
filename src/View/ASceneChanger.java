package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class ASceneChanger {
    public void change_scene(ActionEvent actionEvent, String fxml_name) throws IOException {
        //Gets the stage that we are inside right now
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        //Loads the Game options scene into the stage
        Scene new_scene = new Scene(FXMLLoader.load(getClass().getResource(fxml_name)));
        thisStage.setScene(new_scene);
        thisStage.sizeToScene();
        thisStage.show();
    }
}
