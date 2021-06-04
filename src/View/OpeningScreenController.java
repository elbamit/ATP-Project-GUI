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

public class OpeningScreenController extends ASceneChanger{



    public void Start_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "GameOptions.fxml");
    }
}
