package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class GameOptionsController extends ASceneChanger {
    @FXML
    private Pane root;

    public void Custom_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "Generate_Custom_Game.fxml");
    }

    public void Easy_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 10;
        change_scene_MyView(actionEvent);

    }

    public void Medium_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 25;
        MyViewController.col = 25;
        change_scene_MyView(actionEvent);
    }

    public void Hard_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 40;
        MyViewController.col = 40;
        change_scene_MyView(actionEvent);
    }

    public void Wide_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 40;
        change_scene_MyView(actionEvent);
    }

    public void Load_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        change_scene_MyView(actionEvent);
    }

    //TODO maybe deal with trying to cancel a load game
    public void Load_Game_From_Menu_Bar_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) root.getScene().getWindow();
        change_scene_MyView(actionEvent);

    }


    public void Properties_Click(ActionEvent actionEvent) throws IOException {
        new_stage("Properties.fxml", "Properties");
    }

    public void About_Click(ActionEvent actionEvent) throws IOException {
        new_stage("AboutScreen.fxml", "About");

    }

    public void Help_Click(ActionEvent actionEvent) throws IOException {
        new_stage("HelpScreen.fxml", "Help");
    }
}
