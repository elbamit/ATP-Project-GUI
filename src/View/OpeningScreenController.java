package View;

import com.sun.glass.ui.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class OpeningScreenController {



    public void Start_Game_Click(ActionEvent actionEvent) throws IOException {

        //Gets the stage that we are inside right now
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        //Loads the Game options scene into the stage
        Scene Game_options_scene = new Scene(FXMLLoader.load(getClass().getResource("GameOptions.fxml")));
        thisStage.setScene(Game_options_scene);
        thisStage.sizeToScene();
        thisStage.show();

    }
}
