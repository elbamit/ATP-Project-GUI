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
//        Stage stage =(Stage) root.getScene().getWindow();
//        new_stage(stage,"Properties.fxml", "Properties screen");
        ActionEvent a = (ActionEvent)actionEvent.getSource();

        //Stage stage =(Stage) root.getScene().getWindow();
        change_scene(a,"Properties.fxml");
//TODO make it open a new stage with properties
    }
    public void help_click(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setTitle("Help");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("HelpScreen.fxml").openStream());
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //change_scene(actionEvent,"HelpScreen.fxml");

    }

    public void about_click(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setTitle("About");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("AboutScreen.fxml").openStream());
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //change_scene(actionEvent,"HelpScreen.fxml");

    }
}
