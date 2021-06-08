package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

public class GameOptionsController extends ASceneChanger {
    public Pane pane;
    public Button easy_game;
    public Button medium_game;
    public Button hard_game;
    public Button custom_game;
    public Button wide_game;
    public Button invisible_game;
    public Button load_game;
    public VBox menu_bar;


    public void Custom_Game_Click(ActionEvent actionEvent) throws IOException {
        change_scene_custom_game(actionEvent);
    }

    public void Easy_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 10;
        change_scene_MyView(actionEvent);

    }

    public void Medium_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 25;
        MyViewController.col = 25;
        change_scene_MyView(actionEvent);
    }

    public void Hard_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 40;
        MyViewController.col = 40;
        change_scene_MyView(actionEvent);
    }

    public void Wide_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.row = 10;
        MyViewController.col = 40;
        change_scene_MyView(actionEvent);
    }

    public void Load_Game_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        change_scene_MyView(actionEvent);
    }

    //TODO maybe deal with trying to cancel a load game
    public void Load_Game_From_Menu_Bar_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) pane.getScene().getWindow();
        change_scene_MyView(actionEvent);

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
}
