package View;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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


    }

//    public void setResizeEvent(Stage stage) {
//
//        how_to_play_title.wrappingWidthProperty().bind(stage.widthProperty());
//        how_to_create_title.wrappingWidthProperty().bind(stage.widthProperty());
//        how_to_save_title.wrappingWidthProperty().bind(stage.widthProperty());
//        how_to_load_title.wrappingWidthProperty().bind(stage.widthProperty());
//        how_to_settings_title.wrappingWidthProperty().bind(stage.widthProperty());
//        how_to_report_title.wrappingWidthProperty().bind(stage.widthProperty());
//        Help_title.wrappingWidthProperty().bind(stage.widthProperty());

//        image_left.setFitHeight(pane_img_left.getPrefHeight());
//        image_right.setFitHeight(pane_img_right.getPrefHeight());
//
//        anchor_pane.heightProperty().addListener((observable, oldValue, newValue) -> {
//            pane.setPrefHeight(stage.getHeight());
//            pane_img_left.setPrefHeight(stage.getHeight() * ((double) 88/500));
//            pane_img_right.setPrefHeight(stage.getHeight() * ((double) 88/500));


//            how_to_play_txt.setPrefHeight(stage.getHeight() * ((double) 79/500));

//            easy_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            easy_game.setLayoutY(stage.getHeight() * ((double) 70/500) );
//
//            medium_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            medium_game.setLayoutY(stage.getHeight() * ((double) 70/500));
//
//            hard_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            hard_game.setLayoutY(stage.getHeight() * ((double) 70/500));
//
//            custom_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            custom_game.setLayoutY(stage.getHeight() * ((double) 195/500));
//
//            wide_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            wide_game.setLayoutY(stage.getHeight() * ((double) 195/500));
//
//            invisible_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            invisible_game.setLayoutY(stage.getHeight() * ((double) 195/500));
//
//            load_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
//            load_game.setLayoutY(stage.getHeight() * ((double) 320/500));
//
//            menu_bar.setPrefHeight(menu_bar.getPrefHeight());

//        });
//
//        anchor_pane.widthProperty().addListener((observable, oldValue, newValue) -> {
//            pane.setPrefWidth(stage.getWidth());
//
//            pane_img_left.setPrefWidth(stage.getWidth() * ((double) 139/900));
//            pane_img_left.setPrefWidth(stage.getWidth() * ((double) 192/900));
//            easy_game.setPrefWidth(stage.getWidth() * ((double) 155/900));
//            easy_game.setLayoutX(stage.getWidth() * ((double) 80/900));
//
//            medium_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
//            medium_game.setLayoutX(stage.getWidth() * ((double) 380/900));
//
//            hard_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
//            hard_game.setLayoutX(stage.getWidth() * ((double) 680/900));
//
//            custom_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
//            custom_game.setLayoutX(stage.getWidth() * ((double) 80/900));
//
//            wide_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
//            wide_game.setLayoutX(stage.getWidth() * ((double) 380/900));
//
//            invisible_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
//            invisible_game.setLayoutX(stage.getWidth() * ((double) 680/900));
//
//            load_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
//            load_game.setLayoutX(stage.getWidth() * ((double) 380/900));
//
//            menu_bar.setPrefWidth(stage.getWidth());
//        });
//    }


}
