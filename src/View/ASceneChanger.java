package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
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


    public void change_scene_stage(Stage stage, String fxml_name) throws IOException {
        //Gets the stage that we are inside right now
        Stage thisStage = stage;

        //Loads the Game options scene into the stage
        Scene new_scene = new Scene(FXMLLoader.load(getClass().getResource(fxml_name)));
        thisStage.setScene(new_scene);
        thisStage.sizeToScene();
        thisStage.show();
    }

    public void new_stage(String fxml_name, String stage_name) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setTitle(stage_name);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource(fxml_name).openStream());
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //TODO make a function that opens a new stage

    }
}
