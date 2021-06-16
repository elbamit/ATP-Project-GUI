package View;

import Model.MyModel;
import Server.Server;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.MyMazeGenerator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Server.Configurations;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Configurations config = Configurations.getInstance();
        config.setProperty(4, "MyMazeGenerator", "DepthFirstSearch");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("OpeningScreen.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Maze Game");
        Scene new_scene = new Scene(root, 900, 500);
        primaryStage.setScene(new_scene);

        OpeningScreenController mv =(OpeningScreenController) loader.getController();

        mv.setResizeEvent1(new_scene);
        SetStageCloseEvent(primaryStage);
        primaryStage.show();
    }

    public static void SetStageCloseEvent(Stage stage){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Are you sure you want to leave?");
                Optional<ButtonType> res = a.showAndWait();
                if (res.get() == ButtonType.OK){

                }else {
                    windowEvent.consume();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
