package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOptionsController {

    public void Custom_Game_Click(ActionEvent actionEvent) throws IOException {

        //Gets the stage that we are inside right now
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        //Loads the Game options scene into the stage
        Scene Game_Maze_scene = new Scene(FXMLLoader.load(getClass().getResource("Generate_Custom_Game.fxml")));
        thisStage.setScene(Game_Maze_scene);
        thisStage.sizeToScene();
        thisStage.show();
    }
}
