package View;

import Model.MyModel;
import Server.Server;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.MyMazeGenerator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Server.Configurations;
public class Main extends Application {
    //public static MyViewController myViewController;


    @Override
    public void start(Stage primaryStage) throws Exception{

        Configurations config = Configurations.getInstance();
        config.setProperty(4, "MyMazeGenerator", "DepthFirstSearch");

        //
/*
        MyModel model = new MyModel();
        model.startServers();

        MyViewModel viewModel = new MyViewModel(model);
        model.addObserver(viewModel);

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("MyView.fxml"));
        //FXMLLoader.load((getClass().getResource("MyView.fxml")));
        loader1.load();
        MyViewController view1 = loader1.getController();
        view1.setViewModel(viewModel);*/

        //
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OpeningScreen.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Maze Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
