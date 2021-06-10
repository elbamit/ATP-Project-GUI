package View;

import Model.MyModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameOptionsController extends ASceneChanger implements IController, Initializable {
    public Pane pane;
    public Button easy_game;
    public Button medium_game;
    public Button hard_game;
    public Button custom_game;
    public Button wide_game;
    public Button invisible_game;
    public Button load_game;
    public VBox menu_bar;
    public javafx.scene.image.ImageView background;


    public void Custom_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "Custom Game", "Generate_Custom_Game.fxml");
    }

    public void Easy_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 10;
        OpeningScreenController.background_music = false;
        change_scene(actionEvent, "Game", "MyView.fxml");

    }

    public void Medium_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 25;
        MyViewController.col = 25;
        OpeningScreenController.background_music = false;
        change_scene(actionEvent, "Game", "MyView.fxml");
    }

    public void Hard_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 40;
        MyViewController.col = 40;
        OpeningScreenController.background_music = false;
        change_scene(actionEvent, "Game", "MyView.fxml");
    }

    public void Wide_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 40;
        OpeningScreenController.background_music = false;
        change_scene(actionEvent, "Game", "MyView.fxml");
    }

    public void Load_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        OpeningScreenController.background_music = false;
        change_scene(actionEvent, "Game", "MyView.fxml");
    }


    public void Load_Game_From_Menu_Bar_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) pane.getScene().getWindow();
        OpeningScreenController.background_music = false;
        change_scene_menu(stage, "Game", "MyView.fxml");

    }

    public void Invisible_Button_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 15;
        MyViewController.col = 15;
        OpeningScreenController.background_music = false;
        change_scene_MyView_Invisible(actionEvent);
    }


    public void Properties_Click(ActionEvent actionEvent) throws IOException {
        new_stage("Properties.fxml", "Properties");
    }

    public void About_Click(ActionEvent actionEvent) throws IOException {
        new_stage("AboutScreen.fxml", "About");

    }

    public void Help_Click(ActionEvent actionEvent) throws IOException {
        new_stage("HelpScreen.fxml", "Help");
    }

    public void close_button_click(ActionEvent actionEvent) {
        exit_from_menu();
    }

    @Override
    public void closeWindow() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Are you sure you want to leave?");
        Optional<ButtonType> res = a.showAndWait();
        if (res.get() == ButtonType.OK){
            if (MyModel.serverworking){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MyView.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MyViewController mv = loader.getController();
                mv.viewModel.stopServers();
            }
            Platform.exit();
        }
    }


    public void setResizeEvent(Stage stage) {

        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            pane.setPrefHeight(stage.getHeight());

            easy_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            easy_game.setLayoutY(stage.getHeight() * ((double) 70/500) );

            medium_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            medium_game.setLayoutY(stage.getHeight() * ((double) 70/500));

            hard_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            hard_game.setLayoutY(stage.getHeight() * ((double) 70/500));

            custom_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            custom_game.setLayoutY(stage.getHeight() * ((double) 195/500));

            wide_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            wide_game.setLayoutY(stage.getHeight() * ((double) 195/500));

            invisible_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            invisible_game.setLayoutY(stage.getHeight() * ((double) 195/500));

            load_game.setPrefHeight(stage.getHeight() * ((double) 68/500));
            load_game.setLayoutY(stage.getHeight() * ((double) 320/500));

            menu_bar.setPrefHeight(menu_bar.getPrefHeight());

        });

        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            pane.setPrefWidth(stage.getWidth());

            easy_game.setPrefWidth(stage.getWidth() * ((double) 155/900));
            easy_game.setLayoutX(stage.getWidth() * ((double) 80/900));

            medium_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
            medium_game.setLayoutX(stage.getWidth() * ((double) 380/900));

            hard_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
            hard_game.setLayoutX(stage.getWidth() * ((double) 680/900));

            custom_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
            custom_game.setLayoutX(stage.getWidth() * ((double) 80/900));

            wide_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
            wide_game.setLayoutX(stage.getWidth() * ((double) 380/900));

            invisible_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
            invisible_game.setLayoutX(stage.getWidth() * ((double) 680/900));

            load_game.setPrefWidth(stage.getWidth() *  ((double) 155/900));
            load_game.setLayoutX(stage.getWidth() * ((double) 380/900));

            menu_bar.setPrefWidth(stage.getWidth());
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image wallpaper = null;
        try {
            wallpaper = new Image(new FileInputStream("./resources/images/wallpaper3.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        background.setImage(wallpaper);
        background.fitHeightProperty().bind(pane.heightProperty());
        background.fitWidthProperty().bind(pane.widthProperty());
    }
}
