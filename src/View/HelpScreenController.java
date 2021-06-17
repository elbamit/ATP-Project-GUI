package View;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class HelpScreenController implements Initializable {
    public javafx.scene.image.ImageView image_left;
    public javafx.scene.image.ImageView image_right;


    public Text how_to_play;
    public Text how_to_create;
    public Text how_to_save;
    public Text how_to_load;
    public Text how_to_report;
    public Text how_to_settings;
    public TextFlow how_to_play_txt;
    public TextFlow how_to_create_txt;
    public TextFlow how_to_save_txt;
    public TextFlow how_to_load_txt;
    public TextFlow how_to_report_txt;
    public TextFlow how_to_settings_txt;

    public AnchorPane anchor_pane;
    public Pane pane;
    public Text how_to_save_title;
    public Text how_to_play_title;
    public Text how_to_create_title;
    public Text how_to_settings_title;
    public Text how_to_report_title;
    public Text how_to_load_title;
    public Text Help_title;

    public Pane pane_img_right;
    public Pane pane_img_left;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            InputStream stream = new FileInputStream("./resources/images/help_.jpg");
            Image image = new Image(stream);
            image_left.setImage(image);
            InputStream stream_2 = new FileInputStream("./resources/images/Help_2.jpg");
            Image image_2 = new Image(stream_2);
            image_right.setImage(image_2);
        }
        catch (Exception e){
        }

        how_to_play = new Text("Just click Start Game and choose your desired game mode. Use the arrows or the numpad, or even the mouse to navigate through the maze and find the exit. But don't forget to have fun!");
        ObservableList<Node> lst = how_to_play_txt.getChildren();
        lst.add(how_to_play);

        how_to_create = new Text("You can create different sizes of mazes in the game option menu - or you can customize you own maze by clicking Custom Maze.");
        ObservableList<Node> lst1 = how_to_create_txt.getChildren();
        lst1.add(how_to_create);

        how_to_save = new Text("You can save any maze by clicking on the menu bar -> File -> Save. Just choose a kick ass name for it ;)");
        ObservableList<Node> lst2 = how_to_save_txt.getChildren();
        lst2.add(how_to_save);

        how_to_load = new Text("You can load any maze from the game option screen or from the menu bar -> File -> Load. We're totally fine if you load a picture of you credit card instead ;) ");
        ObservableList<Node> lst3 = how_to_load_txt.getChildren();
        lst3.add(how_to_load);

        how_to_settings = new Text("You are fully able to control the game settings by clicking on Options in the Menu Bar, then on Properties.");
        ObservableList<Node> lst4 = how_to_settings_txt.getChildren();
        lst4.add(how_to_settings);

        how_to_report = new Text("We take your remarks very seriously! You can send us your report to: hahahaYourFunnyIfYouThinkSomeoneWillGiveACrapAboutYou@gmail.com");
        ObservableList<Node> lst5 = how_to_report_txt.getChildren();
        lst5.add(how_to_report);


        Help_title.setFont(Font.font("tahoma", 31));

        how_to_play.setFont(Font.font("tahoma"));
        how_to_play_title.setFont(Font.font("tahoma"));
        how_to_create.setFont(Font.font("tahoma"));
        how_to_create_title.setFont(Font.font("tahoma"));

        how_to_save.setFont(Font.font("tahoma"));
        how_to_save_title.setFont(Font.font("tahoma"));

        how_to_load.setFont(Font.font("tahoma"));
        how_to_load_title.setFont(Font.font("tahoma"));

        how_to_settings.setFont(Font.font("tahoma"));
        how_to_settings_title.setFont(Font.font("tahoma"));

        how_to_report.setFont(Font.font("tahoma"));
        how_to_report_title.setFont(Font.font("tahoma"));

        anchor_pane.setStyle("-fx-background-color: IVORY");
    }
}
