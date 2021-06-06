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

    public void new_stage(Stage stage, String fxml_name, String stage_name) throws IOException {
//        Stage new_stage = stage;
//        new_stage.setTitle(stage_name);
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource(fxml_name).openStream());
//        PropertiesController settingController = loader.getController();
//        Scene scene = new Scene(root);
//        new_stage.setScene(scene);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        new_stage.show();
        //TODO make a function that opens a new stage

    }
}
