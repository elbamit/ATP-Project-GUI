package View;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutScreenConroller implements Initializable {
    public javafx.scene.image.ImageView about_image;
    public javafx.scene.image.ImageView about_image_2;
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
    }
}
