package View;

import Model.MyModel;
import ViewModel.MyViewModel;
import com.sun.glass.ui.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;




public class OpeningScreenController extends ASceneChanger{

    @FXML
    public Button start_game_button;
    @FXML
    private Pane root;


    public void Start_Game_Click(ActionEvent actionEvent) throws IOException {
        //Gets the stage that we are inside right now
//        Node node = (Node) actionEvent.getSource();
//        Stage thisStage = (Stage) node.getScene().getWindow();
//
//        //Loads the Game options scene into the stage
//        Scene new_scene = new Scene(FXMLLoader.load(getClass().getResource("GameOptions.fxml")));
//        thisStage.setScene(new_scene);
////        thisStage.sizeToScene();
//        thisStage.setWidth(900);
//        thisStage.setHeight(500);
//
//        thisStage.show();

        change_scene_game_options(actionEvent);
    }


    public void Properties_Click(ActionEvent actionEvent) throws IOException {
        new_stage("Properties.fxml", "Properties");
    }


    public void Help_Click(ActionEvent actionEvent) throws IOException {
        new_stage("HelpScreen.fxml", "Help");
    }

    public void About_Click(ActionEvent actionEvent) throws IOException {
        new_stage("AboutScreen.fxml", "About");
    }

    public void setResizeEvent(Stage stage) {


        stage.heightProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(stage.getHeight());
            start_game_button.setPrefHeight(stage.getHeight()/3.5);
            start_game_button.setLayoutY(stage.getHeight()/3);

        });

        stage.widthProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(stage.getWidth());

            start_game_button.setPrefWidth(stage.getWidth()*0.32);
            start_game_button.setLayoutX(stage.getX()/2);




        });
    }
}
