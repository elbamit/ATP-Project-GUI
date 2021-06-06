package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class Generate_Custom_GameController extends ASceneChanger{

    @FXML
    private TextField textField_mazeRows;
    @FXML
    private TextField textField_mazeColumns;


    public void Generate_Maze_Custom_Click(ActionEvent actionEvent) throws IOException {

        try{

            //Checks input
            if (!(isNumeric(textField_mazeRows.getText()) && isNumeric(textField_mazeColumns.getText())) || Integer.parseInt(textField_mazeRows.getText()) < 3 || Integer.parseInt(textField_mazeColumns.getText()) < 3 || Integer.parseInt(textField_mazeRows.getText()) > 1000 || Integer.parseInt(textField_mazeColumns.getText()) > 1000){
                throw new Exception();
            }

            MyViewController.row = Integer.parseInt(textField_mazeRows.getText());
            MyViewController.col = Integer.parseInt(textField_mazeColumns.getText());
            change_scene(actionEvent, "MyView.fxml");

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect input - sizes should be numbers between 3-1000");
            textField_mazeRows.clear();
            textField_mazeColumns.clear();
            alert.show();
        }

    }

    public void Back_to_game_options_click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "GameOptions.fxml");
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void Load_Game_From_Menu_Bar_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) textField_mazeRows.getScene().getWindow();
        change_scene_stage(stage, "MyView.fxml");
    }

    public void Back_to_game_options_From_Menu_click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) textField_mazeRows.getScene().getWindow();
        change_scene_stage(stage, "GameOptions.fxml");
    }
}
