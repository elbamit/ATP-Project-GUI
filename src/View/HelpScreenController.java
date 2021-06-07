package View;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class HelpScreenController implements Initializable {
    public javafx.scene.image.ImageView Help_image;
    public javafx.scene.image.ImageView Help_image_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            InputStream stream = new FileInputStream("./resources/images/help_.jpg");
            Image image = new Image(stream);
            Help_image.setImage(image);
            InputStream stream_2 = new FileInputStream("./resources/images/Help_2.jpg");
            Image image_2 = new Image(stream_2);
            Help_image_2.setImage(image_2);
        }
        catch (Exception e){
        }

    }

}
