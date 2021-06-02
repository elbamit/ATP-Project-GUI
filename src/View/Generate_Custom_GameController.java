package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class Generate_Custom_GameController {

    public TextField textField_mazeRows;
    public TextField textField_mazeColumns;


    public void Generate_Maze_Custom_Click(ActionEvent actionEvent) {

        try{
            if (!(isNumeric(textField_mazeRows.getText()) && isNumeric(textField_mazeColumns.getText()))){
                throw new Exception();
            }
            //generate func (checks alone if size is good)
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect input - row and column sizes should be numbers between 3-1000");
            textField_mazeRows.clear();
            textField_mazeColumns.clear();
            alert.show();
        }


    }

    public void Back_to_game_options_click(ActionEvent actionEvent) throws IOException {
        //Gets the stage that we are inside right now
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        //Loads the Game options scene into the stage
        Scene Game_options_scene = new Scene(FXMLLoader.load(getClass().getResource("GameOptions.fxml")));
        thisStage.setScene(Game_options_scene);
        thisStage.sizeToScene();
        thisStage.show();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
