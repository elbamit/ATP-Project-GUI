package View;

import Model.MyModel;
import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MyViewController extends ASceneChanger implements Initializable, Observer,IView{
    public MyViewModel viewModel;
    @FXML
    private BorderPane b;
    public MazeDisplayer mazeDisplayer = new MazeDisplayer();
    public static int row;
    public static int col;
    public static boolean loaded=false;




    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            if (arg instanceof Maze) {
                displayMaze((Maze) arg);
            }
            else if(arg instanceof Position){
                mazeDisplayer.drawMaze(viewModel.getMaze(),(Position)arg);
            }
        }

    }


    public void mouseClicked(MouseEvent mouseEvent) {
        mazeDisplayer.requestFocus();
    }//מהתרגול


    //Function that does stuff upon loading the MyView fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.viewModel == null){
            MyModel model = new MyModel();
            model.startServers();

            MyViewModel viewModel1 = new MyViewModel(model);
            model.addObserver(viewModel1);
            setViewModel(viewModel1);
        }
        if (loaded){

            //mazeDisplayer.draw_empty();
            try {
                Load_Maze_and_display();
            } catch (IOException e) {
            }
        }
        generateMazeAuto();

    }

    public void SaveGame(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Game","*.atpgame"));
        //chooser.setTitle("Choose maze name");
        File file = chooser.showSaveDialog(null);
        if(file != null){
            try{
                File file_to_save = new File(file.getPath());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_to_save));
                Object o = (Object)viewModel.getMaze();
                objectOutputStream.writeObject(o);
                objectOutputStream.flush();
                objectOutputStream.close();

            }
            catch(Exception e){
                Alert a = new Alert((Alert.AlertType.ERROR));
                a.setContentText("Sorry... titkasher le aba ");
                a.show();
            }
        }
        else{
            Alert a = new Alert((Alert.AlertType.ERROR));
            a.setContentText("Sorry... titkasher le aba ");
            a.show();
        }
    }

    private void Load_Maze_and_display() throws IOException {
        //open window for user to load
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Game","*.atpgame"));
        chooser.setTitle("Choose Maze File");
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            try {
                File loaded_file = new File(file.getPath());
                FileInputStream inputStream = new FileInputStream(loaded_file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Object o = (Object) objectInputStream.readObject();
                //Maze loadedMaze = (Maze) o[0];
                viewModel.Load(o);
                objectInputStream.close();
                inputStream.close();

            } catch (Exception e) {
                Alert a = new Alert((Alert.AlertType.ERROR));
                a.setContentText("No maze to Load ! Ya ben zone");
                a.show();
                MyViewController.row = 10;
                MyViewController.col = 10;

            }
        }
        else{
            Alert a = new Alert((Alert.AlertType.ERROR));
            a.setContentText("No maze to Load ! ya Sharmota");
            a.show();
            MyViewController.row = 10;
            MyViewController.col = 10;

        }
        loaded = false;

    }


    private void generateMazeAuto() {
        viewModel.generateMaze(row,col);
    }
    public void KeyPressed(KeyEvent keyEvent) {
        viewModel.movePlayer(keyEvent);
        keyEvent.consume();
    }

    public void generateNewMaze(ActionEvent actionEvent) {
        viewModel.generateMaze(row,col);
    }

    //TODO
    public void solveMaze(ActionEvent actionEvent) {

    }


    @Override
    public void displayMaze(Maze maze) {
        mazeDisplayer.drawMaze(maze);
    }
}
