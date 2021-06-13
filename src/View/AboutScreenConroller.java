package View;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutScreenConroller implements Initializable {
    public javafx.scene.image.ImageView about_image;
    public javafx.scene.image.ImageView about_image_2;
    public TextFlow text_flow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            InputStream stream = new FileInputStream("./resources/images/prince.jpg");
            Image image = new Image(stream);
            about_image.setImage(image);
            InputStream stream_2 = new FileInputStream("./resources/images/melech.jpeg");
            Image image_2 = new Image(stream_2);
            about_image_2.setImage(image_2);
        }
        catch (Exception e){
        }

        Text text_to_add = new Text("Hi, we are Omri and Amit, two SISE students from BGU University. We really hope that you enjoy our game! We spent month working to give YOU the best experience possible! So have fun and don't forget to rate us 5-Stars!");
        text_to_add.setStyle("-fx-font: 15 tahoma;");

        ObservableList<Node> lst = text_flow.getChildren();
        lst.add(text_to_add);

    }
}
