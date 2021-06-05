package View;

import Model.MyModel;
import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MyViewController implements Initializable, Observer,IView{
    public MyViewModel viewModel;
    public MazeDisplayer mazeDisplayer = new MazeDisplayer();
    public static int row;
    public static int col;


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

        generateMazeAuto();

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
