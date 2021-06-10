package View;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class WinScreenController extends ASceneChanger implements Initializable{
    public MediaPlayer player;
    public Text left_text;
    public Text right_text;
    public TextFlow left_text_txt;
    public TextFlow right_text_txt;
    public javafx.scene.image.ImageView image_right;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            InputStream stream = new FileInputStream("./resources/images/num1.jpg");
            Image image = new Image(stream);
            image_right.setImage(image);
        }
        catch (Exception e){
        }
        left_text = new Text("OMG ! Who would believe ?! You finished in first place! Call Mom right now !");
        ObservableList<Node> lst = left_text_txt.getChildren();
        lst.add(left_text);
        Media sound = new Media(this.getClass().getResource("/sounds/final_music.mp3").toString());
        player = new MediaPlayer(sound);
        player.play();
    }

    public void Back_To_Game_Options(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
        //change_scene(actionEvent, "Game Options", "GameOptions.fxml");
    }

    public void Donate_Click(ActionEvent actionEvent) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("We appreciate it, Please contact our secretary at the following email address: elbamit@hotmail.com , Thanks ;-)");
        a.setHeight(420);
        a.show();
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
        //change_scene(actionEvent, "Game Options", "GameOptions.fxml");

    }


}
