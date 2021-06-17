package View;


import Model.MyModel;
import ViewModel.MyViewModel;
import com.sun.glass.ui.View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class OpeningScreenController extends ASceneChanger implements Initializable, IController {

    @FXML
    public Button start_game_button;
    public Menu exit_menu;
    public MenuBar menu_bar;
    public VBox menu_bar_box;
    @FXML
    private Pane root;
    public javafx.scene.image.ImageView background;


    public static boolean background_music = false;


    public void Start_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "Game Options", "GameOptions.fxml");
    }


    public void Properties_Click(ActionEvent actionEvent) throws IOException {
        new_stage("Properties.fxml", "Properties");
    }


    public void Help_Click(ActionEvent actionEvent) throws IOException {
        new_stage("HelpScreen.fxml", "Help");

    }

    public void About_Click(ActionEvent actionEvent) throws IOException {
        new_stage("AboutScreen.fxml", "About");
    }


    public void close_button_click(Event actionEvent){
        exit_from_menu();
    }



    @Override
    public void stopMaccabiSound() {
        return;
    }


    public void setResizeEvent(Scene scene) {

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(scene.getHeight());
            start_game_button.setPrefHeight(scene.getHeight()/3.5);
            start_game_button.setLayoutY(scene.getHeight()/3);


        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(scene.getWidth());

            start_game_button.setPrefWidth(scene.getWidth()*0.32);
            start_game_button.setLayoutX(scene.getX()/2);

        });
    }



    private void playTheme() {

        background_music = true;
        Thread musicThread = new Thread(() -> {
            try {
                while (background_music) {
                    String path = this.getClass().getResource("/sounds/Opening_music.mp3").toString();
                    Media sound = new Media(path);
                    MediaPlayer themeMediaPlayer = new MediaPlayer(sound);
                    themeMediaPlayer.play();

                    int time = 70000;
                    while(background_music){
                        Thread.sleep(10);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        musicThread.setDaemon(true);
        musicThread.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image wallpaper = null;
        try{
            wallpaper = new Image(new FileInputStream("./resources/images/wallpaper3.png"));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }


        background.setImage(wallpaper);
        background.fitHeightProperty().bind(root.heightProperty());
        background.fitWidthProperty().bind(root.widthProperty());
        playTheme();

    }

    public void setResizeEvent1(Scene new_scene) {
        new_scene.heightProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefHeight(new_scene.getHeight());
            menu_bar_box.setPrefHeight(30);
            start_game_button.setPrefHeight(new_scene.getHeight()/3.5);
            start_game_button.setLayoutY(new_scene.getHeight()/3);

        });

        new_scene.widthProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(new_scene.getWidth());
            menu_bar_box.setPrefWidth(new_scene.getWidth());
            start_game_button.setPrefWidth(new_scene.getWidth()*0.32);
            start_game_button.setLayoutX(new_scene.getWidth()/3);

        });
    }
}
