package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class GameOptionsController extends ASceneChanger {

    public void Custom_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "Generate_Custom_Game.fxml");
    }

    public void Easy_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 10;
        change_scene(actionEvent, "MyView.fxml");
    }

    //TODO MAKE ALL THE BUTTONS CLICKS (FOR ALL OPTIONS)


}
