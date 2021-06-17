package View;

import Model.MyModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public abstract class ASceneChanger {
//    MediaPlayer themeMediaPlayer = null; //TODO remove

    public void new_stage(String fxml_name, String stage_name) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setTitle(stage_name);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource(fxml_name).openStream());
            Scene scene = new Scene(root, 620, 420);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void change_scene(ActionEvent actionEvent, String title, String fxml_path) throws IOException {

        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.setTitle(title);

        //Loads the Game options scene into the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml_path));
        Parent x = loader.load();

        Scene new_scene = new Scene(x, 900, 500);
        thisStage.setScene(new_scene);


        thisStage.setWidth(900);
        thisStage.setHeight(500);

        IController mv = loader.getController();
        mv.setResizeEvent(new_scene);
        mv.stopMaccabiSound();
        SetStageCloseEvent(thisStage);
        thisStage.show();
    }

    public void change_scene_menu(Stage stage, String title, String fxml_path) throws IOException {
        Stage thisStage = stage;
        thisStage.setTitle(title);

        //Loads the Game options scene into the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml_path));
        Parent x = loader.load();

        Scene new_scene = new Scene(x);
        thisStage.setScene(new_scene);

        thisStage.setWidth(900);
        thisStage.setHeight(500);

        IController mv = loader.getController();
        mv.setResizeEvent(new_scene);
        mv.stopMaccabiSound();
        SetStageCloseEvent(thisStage);
        thisStage.show();
    }



    public void change_scene_MyView_Invisible(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.setTitle("Maze");

        //Loads the Game options scene into the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent x = loader.load();


        Scene new_scene = new Scene(x);

        thisStage.setScene(new_scene);

        thisStage.setWidth(900);
        thisStage.setHeight(500);

        MyViewController mv =(MyViewController) loader.getController();
        mv.setInvisible();
        mv.stopMaccabiSound();
        mv.setResizeEvent(new_scene);
        SetStageCloseEvent(thisStage);
        thisStage.show();
    }

    public void change_scene_MyView_Maccabi(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.setTitle("Maze");
        //Loads the Game options scene into the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent x = loader.load();


        Scene new_scene = new Scene(x);

        thisStage.setScene(new_scene);

        thisStage.setWidth(900);
        thisStage.setHeight(500);

        MyViewController mv =(MyViewController) loader.getController();
        mv.setMaccabi();
        mv.playMaccabiSound();
        mv.setResizeEvent(new_scene);
        SetStageCloseEvent(thisStage);
        thisStage.show();
    }

    public static void SetStageCloseEvent(Stage stage){

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Are you sure you want to leave?");
                Optional<ButtonType> res = a.showAndWait();
                if (res.get() == ButtonType.OK){
                    if (MyModel.serverworking){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyView.fxml"));
                        try {
                            MyViewController.Leaving_game();
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        MyViewController mv = loader.getController();
                        mv.viewModel.stopServers();
                        Platform.exit();
                        System.exit(0);
                    }
                }else {
                    windowEvent.consume();
                }
            }
        });
    }

    public static void exit_from_menu(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Are you sure you want to leave?");
        Optional<ButtonType> res = a.showAndWait();
        if (res.get() == ButtonType.OK){
            if (MyModel.serverworking){
                FXMLLoader loader = new FXMLLoader(ASceneChanger.class.getResource("MyView.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MyViewController mv = loader.getController();
                mv.viewModel.stopServers();
            }
            Platform.exit();
            System.exit(0);

        }

    }

}
