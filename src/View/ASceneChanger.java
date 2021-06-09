package View;

import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class ASceneChanger {
    public void change_scene(ActionEvent actionEvent, String fxml_name) throws IOException {
        //Gets the stage that we are inside right now
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        //Loads the Game options scene into the stage
        Scene new_scene = new Scene(FXMLLoader.load(getClass().getResource(fxml_name)));
        thisStage.setScene(new_scene);
//        thisStage.sizeToScene();
        thisStage.setWidth(900);
        thisStage.setHeight(500);

        thisStage.show();
    }


    public void change_scene_stage(Stage stage, String fxml_name) throws IOException {
        //Gets the stage that we are inside right now
        Stage thisStage = stage;

        //Loads the Game options scene into the stage
        Scene new_scene = new Scene(FXMLLoader.load(getClass().getResource(fxml_name)));
        thisStage.setScene(new_scene);
        thisStage.sizeToScene();
        thisStage.show();
    }

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

        Scene new_scene = new Scene(x);
        thisStage.setScene(new_scene);

        thisStage.setWidth(900);
        thisStage.setHeight(500);

        IController mv = loader.getController();
        mv.setResizeEvent(thisStage);

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
        mv.setResizeEvent(thisStage);

        thisStage.show();
    }


    public void change_scene_MyView(ActionEvent actionEvent) throws IOException {
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

        MyViewController mv = loader.getController();
        mv.setResizeEvent(thisStage);
        mv.setvisible();

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
        mv.setResizeEvent(thisStage);
        mv.setInvisible();

        thisStage.show();
    }

    public void change_scene_custom_game(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.setTitle("Custom Game");
        //Loads the Game options scene into the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Generate_Custom_Game.fxml"));
        Parent x = loader.load();


        Scene new_scene = new Scene(x);

        thisStage.setScene(new_scene);

        thisStage.setWidth(900);
        thisStage.setHeight(500);

        Generate_Custom_GameController mv =(Generate_Custom_GameController) loader.getController();
        mv.setResizeEvent(thisStage);

        thisStage.show();
    }

    public void change_scene_game_options(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.setTitle("Game Options");
        //Loads the Game options scene into the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOptions.fxml"));
        Parent x = loader.load();


        Scene new_scene = new Scene(x);

        thisStage.setScene(new_scene);

        thisStage.setWidth(900);
        thisStage.setHeight(500);

        GameOptionsController mv =(GameOptionsController) loader.getController();
        mv.setResizeEvent(thisStage);

        thisStage.show();
    }

//    public void close_app(Node node){
//        if (){
//            MyModel
//        }
//
//        Stage stage = (Stage) node.getScene().getWindow();
//        stage.close();
//    }

//    public void new_stage_help() throws IOException {
//        try {
//            Stage stage = new Stage();
//            stage.setTitle("Help");
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpScreen.fxml"));
//            Parent x = loader.load();
//
//            Scene scene = new Scene(x);
//            stage.setScene(scene);
//
//            stage.setWidth(600);
//            stage.setHeight(400);
//            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
//
//            HelpScreenController mv = (HelpScreenController) loader.getController();
//            mv.setResizeEvent(stage);
//
//            stage.show();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

}
