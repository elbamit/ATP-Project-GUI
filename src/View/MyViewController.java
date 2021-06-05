package View;

import Model.MyModel;
import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.*;
import algorithms.search.Solution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MyViewController extends ASceneChanger implements Initializable, Observer,IView{
    public MyViewModel viewModel;
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
            else if(arg instanceof Solution){
                mazeDisplayer.drawMaze(viewModel.getMaze(), (new Position(viewModel.getPlayerPositionRow(), viewModel.getPlayerPositionCol())), (Solution) arg);

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

            //TODO CHECK WHY THERE IS AN ERROR WHEN STARTING EASY THEN GOING BACK THEN PRESSING EASY AGAIN
            System.out.println("gogogogo");
            MyViewModel viewModel1 = new MyViewModel(model);
            model.addObserver(viewModel1);
            setViewModel(viewModel1);
        }
        if (loaded){

            try {
                Load_Maze_and_display();
            } catch (IOException e) {
            }
        }
        else{
            generateMazeAuto();
        }

    }

    public void SaveGame(ActionEvent actionEvent) {
        this.viewModel.saveGame();
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


    public void SolveMaze(ActionEvent actionEvent) {
        this.viewModel.solveMaze();
        this.mazeDisplayer.requestFocus();
    }


    @Override
    public void displayMaze(Maze maze) {
        mazeDisplayer.drawMaze(maze);
    }


    public void Back_To_Game_Options(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "GameOptions.fxml");
    }

    public void Restart_Game_Click(ActionEvent actionEvent) {
        this.viewModel.restartGame();
        this.mazeDisplayer.deleteSolutionPath();
        this.mazeDisplayer.requestFocus();


    }
}
