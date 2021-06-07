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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;




public class OpeningScreenController extends ASceneChanger{


    @FXML
    private Pane root;


    public void Start_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "GameOptions.fxml");
    }


    public void Properties_click(ActionEvent actionEvent) throws IOException {
    new_stage("Properties.fxml", "Properties");

    }


    public void help_click(ActionEvent actionEvent) throws IOException {
        new_stage("HelpScreen.fxml", "Help");

    }

    public void about_click(ActionEvent actionEvent) throws IOException {
        new_stage("AboutScreen.fxml", "About");


    }
}
