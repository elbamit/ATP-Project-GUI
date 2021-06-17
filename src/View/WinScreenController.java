package View;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


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
    public Button play_again_button;
    public Button donate_button;
    public AnchorPane anchorepane;


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

        anchorepane.setStyle("-fx-background-color: IVORY");

        play_again_button.setStyle("-fx-text-fill: DimGrey");
        play_again_button.setStyle("-fx-font-family: 'Arial Narrow'");
        play_again_button.setStyle("-fx-font-size: 1.3em");
        play_again_button.setStyle("-fx-font-weight: bold");
        play_again_button.setStyle("-fx-background-color: linear-gradient(#FF6347, #D2B48C)");
        play_again_button.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.6), 5, 0.0, 0, 1)");play_again_button.setStyle("-fx-text-fill: DimGrey");

        donate_button.setStyle("-fx-font-family: 'Arial Narrow'");
        donate_button.setStyle("-fx-font-size: 1.3em");
        donate_button.setStyle("-fx-font-weight: bold");
        donate_button.setStyle("-fx-background-color: linear-gradient(#FF6347, #D2B48C)");
        donate_button.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.6), 5, 0.0, 0, 1)");






    }

    public void Back_To_Game_Options(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    public void Donate_Click(ActionEvent actionEvent) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("We appreciate it, Tfo Tfo Tfo We are fine, but you can find a wonderful charity that can donate at the following site: https://www.israelgives.org/ , Thanks ;-)");
        a.setHeight(420);
        a.show();
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();

    }


}
