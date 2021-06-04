package View;

import ViewModel.MyViewModel;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MyViewController implements Initializable, Observer {
    public MyViewModel viewModel;
    public MazeDisplayer mazeDisplayer = new MazeDisplayer();

    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //TODO erase
    public void generateMaze(ActionEvent actionEvent) {

    }

    //TODO erase
    public void solveMaze(ActionEvent actionEvent) {
        AMazeGenerator gener = new MyMazeGenerator();
        Maze m = null;
        try {
            m = gener.generate(10,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mazeDisplayer.drawMaze(m);
    }
}
