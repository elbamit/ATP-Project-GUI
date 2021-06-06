package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.util.Observer;

public interface IModel {
    void generateMaze(int rows, int cols);
    Maze getMaze();
    void updatePlayerLocation(MovementDirection direction);
    int getPlayerRow();
    int getPlayerCol();
    void setPlayerPosition(Position startPosition);
    void assignObserver(Observer o);
    void solveMaze();
    void setMaze(Maze maze);
    void restart_Game();
    Solution getSolution();

    void Load(Object object_loadedMaze);

    void saveGame();

    void updatePlayerLocation(MouseEvent mouseEvent, double mouseX, double mouseY, double cellHeight, double cellWidth);
}
