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
    //private static MyViewController instance = null;
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
                mazeDisplayer.drawMaze((Maze) arg);
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
//        Alert a = new Alert(Alert.AlertType.ERROR);
//        a.setContentText("ballalala");
//        a.show();
        if (this.viewModel == null){
            MyModel model = new MyModel();
            model.startServers();

            MyViewModel viewModel1 = new MyViewModel(model);
            model.addObserver(viewModel1);
            setViewModel(viewModel1);
/*            this.viewModel.generateMaze(row, col);
            this.mazeDisplayer.drawMaze(this.viewModel.getMaze());*/
        }
        generateMazeAuto();
        //drawMazeAuto();

    }



    private void drawMazeAuto() {
        mazeDisplayer.drawMaze(viewModel.getMaze());
    }

    private void generateMazeAuto() {
        viewModel.generateMaze(row,col);
    }
    public void KeyPressed(KeyEvent keyEvent) {
        viewModel.movePlayer(keyEvent);
        keyEvent.consume();
    }

    //TODO erase
    public void generateMaze(ActionEvent actionEvent) {
        viewModel.generateMaze(row,col);
    }

    //TODO erase
    public void solveMaze(ActionEvent actionEvent) {
        AMazeGenerator gener = new SimpleMazeGenerator();
        Maze m = null;
        try {
            m = gener.generate(10,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mazeDisplayer.drawMaze(m);
    }


    @Override
    public void displayMaze(Maze maze) {

    }
}
