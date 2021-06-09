package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;


public class Generate_Custom_GameController extends ASceneChanger implements IController{

    @FXML
    public Pane pane;
    @FXML
    public Text maze_rows_text;
    @FXML
    public Text maze_col_text;
    @FXML
    public Button generate_button;
    @FXML
    public Button back_button;
    @FXML
    public GridPane grid_pane;
    @FXML
    public TextField textField_mazeRows;
    @FXML
    public TextField textField_mazeColumns;
    @FXML
    public VBox menu_bar;


    public void Generate_Maze_Custom_Click(ActionEvent actionEvent) throws IOException {

        try{

            //Checks input
            if (!(isNumeric(textField_mazeRows.getText()) && isNumeric(textField_mazeColumns.getText())) || Integer.parseInt(textField_mazeRows.getText()) < 3 || Integer.parseInt(textField_mazeColumns.getText()) < 3 || Integer.parseInt(textField_mazeRows.getText()) > 1000 || Integer.parseInt(textField_mazeColumns.getText()) > 1000){
                throw new Exception();
            }

            MyViewController.row = Integer.parseInt(textField_mazeRows.getText());
            MyViewController.col = Integer.parseInt(textField_mazeColumns.getText());
            change_scene(actionEvent, "Game", "MyView.fxml");

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect input - sizes should be numbers between 3-1000");
            textField_mazeRows.clear();
            textField_mazeColumns.clear();
            alert.show();
        }

    }

    public void Back_to_game_options_click(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "Game Options", "GameOptions.fxml");
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void Load_Game_From_Menu_Bar_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) textField_mazeRows.getScene().getWindow();
        change_scene_menu(stage, "Game", "MyView.fxml");
    }

    public void Back_to_game_options_From_Menu_click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) textField_mazeRows.getScene().getWindow();
        change_scene_menu(stage, "Game Options","GameOptions.fxml" );
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

    public void close_button_click(ActionEvent actionEvent) {
        exit_from_menu();
    }

    public void setResizeEvent(Stage stage) {
        maze_rows_text.wrappingWidthProperty().bind(stage.widthProperty());
        maze_col_text.wrappingWidthProperty().bind(stage.widthProperty());
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            pane.setPrefHeight(stage.getHeight());

            grid_pane.setPrefHeight(stage.getHeight());
            back_button.setPrefHeight(stage.getHeight() * ((double) 80/500));
            generate_button.setPrefHeight(stage.getHeight() * ((double) 80/500));
            menu_bar.setPrefHeight(menu_bar.getPrefHeight());

        });

        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            pane.setPrefWidth(stage.getWidth());
            grid_pane.setPrefWidth(stage.getWidth());
            textField_mazeRows.setPrefWidth(stage.getWidth()/3);
            textField_mazeColumns.setPrefWidth(stage.getWidth()/3);
            back_button.setPrefWidth(stage.getWidth() * ((double) 250/900));
            generate_button.setPrefWidth(stage.getWidth() * ((double) 250/900));
            menu_bar.setPrefWidth(stage.getWidth());
        });
    }

    @Override
    public void closeWindow() {

    }


}
