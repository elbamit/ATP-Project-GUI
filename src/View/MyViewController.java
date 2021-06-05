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

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MyViewController extends ASceneChanger implements Initializable, Observer,IView{
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
