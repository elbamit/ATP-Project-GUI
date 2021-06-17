package ViewModel;

import Model.IModel;
import Model.MovementDirection;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer {
    private IModel model;

    public MyViewModel(IModel model) {
        this.model = model;
        this.model.assignObserver(this);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    public void generateMaze(int rows, int cols){
        this.model.generateMaze(rows,cols);
    }

    public Maze getMaze(){
        return this.model.getMaze();
    }

    public int getPlayerPositionRow(){
        return this.model.getPlayerRow();
    }

    public int getPlayerPositionCol(){
        return this.model.getPlayerCol();
    }

    public void solveMaze(){
        this.model.solveMaze();
    }

    public Solution getSolution(){
        return this.model.getSolution();
    }



    //Translates the key event into a MovementDirection enum object, then sends it to the updatePLayerLocation func of Model
    public void movePlayer(KeyEvent keyevent){
        MovementDirection direction;
        switch (keyevent.getCode()){
            case NUMPAD8 -> direction = MovementDirection.UP;
            case NUMPAD2 -> direction = MovementDirection.DOWN;
            case NUMPAD4 -> direction = MovementDirection.LEFT;
            case NUMPAD6 -> direction = MovementDirection.RIGHT;
            case NUMPAD1 -> direction = MovementDirection.DOWN_LEFT;
            case NUMPAD7 -> direction = MovementDirection.UP_LEFT;
            case NUMPAD9 -> direction = MovementDirection.UP_RIGHT;
            case NUMPAD3 -> direction = MovementDirection.DOWN_RIGHT;
            case UP -> direction = MovementDirection.UP;
            case DOWN -> direction = MovementDirection.DOWN;
            case LEFT -> direction = MovementDirection.LEFT;
            case RIGHT -> direction = MovementDirection.RIGHT;
            default -> {
                // no need to move the player...
                return;
            }
        }
        model.updatePlayerLocation(direction);
    }

    public void movePlayer(MouseEvent mouseEvent, double mouseX, double mouseY, double cellHeight, double cellWidth){
        this.model.updatePlayerLocation(mouseEvent, mouseX, mouseY, cellHeight, cellWidth);
    }

    public void Load(Object object_loadedMaze) {
        model.Load(object_loadedMaze);
    }

    public void setPlayerPosition(Position pos){
        this.model.setPlayerPosition(pos);
    }

    public void restartGame() {
        this.model.restart_Game();
    }

    public void saveGame() {
        this.model.saveGame();
    }

    public void getThreadNum() {
        this.model.getThreadNum();
    }

    public void getGeneratingAlgo() throws CloneNotSupportedException {
        this.model.getGeneratingAlgo();
    }

    public void getSearchingAlgo() throws CloneNotSupportedException {
        this.model.getSearchingAlgo();
    }

    public void setThreadNum(int num) {
        this.model.setThreadNum(num);
    }

    public void setGeneratingAlgo(String gener_algo_name) {
        this.model.setGeneratingAlgo(gener_algo_name);
    }

    public void setSolvingAlgo(String solving_algo_name) {
        this.model.setSolvingAlgo(solving_algo_name);
    }

    public void stopServers(){
        this.model.stopServers();
    }




}
