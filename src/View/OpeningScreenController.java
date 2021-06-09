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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class OpeningScreenController extends ASceneChanger implements Initializable, IController {

    @FXML
    public Button start_game_button;
    public Menu exit_menu;
    public MenuBar menu_bar;
    @FXML
    private Pane root;

//    public Thread musicThread;
    public static boolean background_music = false;


    public void Start_Game_Click(ActionEvent actionEvent) throws IOException {
//        change_scene_game_options(actionEvent);
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
//        closeWindow();
        exit_from_menu();
    }


    @Override
    public void closeWindow() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Are you sure you want to leave?");
        Optional<ButtonType> res = a.showAndWait();
        if (res.get() == ButtonType.OK){
            Platform.exit();
        }
    }


    public void setResizeEvent(Stage stage) {

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(stage.getHeight());
            start_game_button.setPrefHeight(stage.getHeight()/3.5);
            start_game_button.setLayoutY(stage.getHeight()/3);

        });

        stage.widthProperty().addListener((observable, oldValue, newValue) -> {

            root.setPrefWidth(stage.getWidth());

            start_game_button.setPrefWidth(stage.getWidth()*0.32);
            start_game_button.setLayoutX(stage.getX()/2);

        });
    }



    private void playTheme() {

        //String musicFile = "resources/Sounds/theme.mp3";
        //Media sound = new Media(new File(musicFile).toURI().toString());
        background_music = true;
        Thread musicThread = new Thread(() -> {
            try {
                while (background_music) {
                    String path = this.getClass().getResource("/sounds/Opening_music.mp3").toString();
                    Media sound = new Media(path);
                    MediaPlayer themeMediaPlayer = new MediaPlayer(sound);
                    themeMediaPlayer.play();

                    //Media sound = new Media(new File(musicFile).toURI().toString());

//                    String path = "C:/Users/elbam/OneDrive/Documents/Study/Year2/Semester B/Advanced Topics Programming/ATP-Project-GUI/resources/sounds/Opening_music.mp3";
//                    Media sound = new Media(new File(path).toURI().toString());
//                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
//                    mediaPlayer.play();
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
        playTheme();

    }
}
