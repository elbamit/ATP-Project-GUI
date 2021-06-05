package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

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

}
