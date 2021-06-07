package Model;

import Client.Client;
import IO.MyDecompressorInputStream;
import Server.Server;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.Solution;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import Server.Configurations;
import Client.IClientStrategy;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class MyModel extends Observable implements IModel {
    private Maze maze;
    private Solution solution;
    private Position player_Position;
    private Server mazeGeneratorServer;
    private Server mazeSolverServer;

    public MyModel() {
        this.maze = null;
        this.solution = null;
        this.player_Position = null;
        this.mazeGeneratorServer = null;
        this.mazeSolverServer = null;
    }

    public void startServers(){
        this.mazeGeneratorServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        this.mazeSolverServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        this.mazeGeneratorServer.start();
        this.mazeSolverServer.start();
    }

    public void stopServers(){
        this.mazeGeneratorServer.stop();
        this.mazeSolverServer.stop();
    }

    @Override
    public void generateMaze(int rows, int cols) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{rows, cols};
                        toServer.writeObject(mazeDimensions);

                        //send maze dimensions to server
                        toServer.flush();
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[rows * cols + 12 /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes


                        Maze new_maze = new Maze(decompressedMaze);

                        //Sets the this.maze to the be new_maze and notify all
                        setMaze(new_maze);

                        //Updates this.Position to be the new_maze start position
                        setPlayerPosition(new_maze.getStartPosition());

                    } catch (Exception e) {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("");
                    }
                }
            });
            client.communicateWithServer();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerPosition(Position startPosition) {
        this.player_Position = startPosition;
        setChanged();
        notifyObservers(startPosition);
    }

    private void setPlayerPosition(int row, int col) {
        setPlayerPosition(new Position(row, col));
    }

    public void setMaze(Maze maze){
        this.maze = maze;
        this.solution = null;
        setChanged();
        notifyObservers(maze);

    }

    @Override
    public Maze getMaze() {
        return this.maze;
    }

    @Override
    public void updatePlayerLocation(MovementDirection direction) {
        switch (direction) {
            case UP -> {
                if (getPlayerRow() > 0 && getMaze().getMaze_matrix()[(getPlayerRow() - 1)][getPlayerCol()] != 1)
                    setPlayerPosition(getPlayerRow()-1, getPlayerCol());
            }
            case DOWN -> {
                if (getPlayerRow() < getMaze().getMaze_matrix().length - 1 && getMaze().getMaze_matrix()[(getPlayerRow() + 1)][getPlayerCol()] != 1)
                    setPlayerPosition(getPlayerRow()+1, getPlayerCol());
            }
            case LEFT -> {
                if (getPlayerCol() > 0 && getMaze().getMaze_matrix()[(getPlayerRow())][getPlayerCol() - 1] != 1)
                    setPlayerPosition(getPlayerRow(), getPlayerCol() - 1);
            }
            case RIGHT -> {
                if (getPlayerCol() < getMaze().getMaze_matrix()[0].length - 1 && getMaze().getMaze_matrix()[getPlayerRow()][getPlayerCol() + 1] != 1)
                    setPlayerPosition(getPlayerRow(), getPlayerCol() + 1);
            }

            case UP_RIGHT -> {
                if (getPlayerRow() > 0 && getPlayerCol() < getMaze().getMaze_matrix()[0].length - 1 &&  getMaze().getMaze_matrix()[(getPlayerRow() - 1)][getPlayerCol() + 1] != 1)
                    setPlayerPosition(getPlayerRow()-1, getPlayerCol() + 1);
            }

            case DOWN_RIGHT -> {
                if (getPlayerRow() < getMaze().getMaze_matrix().length - 1 && getPlayerCol() < getMaze().getMaze_matrix()[0].length - 1 && getMaze().getMaze_matrix()[(getPlayerRow() + 1)][getPlayerCol() + 1] != 1)
                    setPlayerPosition(getPlayerRow()+1, getPlayerCol()+1);
            }

            case DOWN_LEFT -> {
                if (getPlayerRow() < getMaze().getMaze_matrix().length - 1 && getPlayerCol() > 0 && getMaze().getMaze_matrix()[(getPlayerRow() + 1)][getPlayerCol() - 1] != 1)
                    setPlayerPosition(getPlayerRow()+1, getPlayerCol()-1);
            }

            case UP_LEFT -> {
                if (getPlayerRow() > 0 && getPlayerCol() > 0 && getMaze().getMaze_matrix()[(getPlayerRow() - 1)][getPlayerCol() - 1] != 1)
                    setPlayerPosition(getPlayerRow()-1, getPlayerCol()-1);
            }
        }
    }

    @Override
    public void updatePlayerLocation(MouseEvent mouseEvent, double mouseX, double mouseY, double cellHeight, double cellWidth) {
        MovementDirection direction;
        if(this.maze != null){

            //Checks DOWN
            if(mouseEvent.getY() > mouseY && Math.abs(mouseEvent.getY() - mouseY) >= cellHeight){

                //DOWN-RIGHT
                if (mouseEvent.getX() > mouseX && Math.abs(mouseEvent.getX() - mouseX) >= cellWidth){
                    direction = MovementDirection.DOWN_RIGHT;
                    updatePlayerLocation(direction);
                    return;
                }

                //DOWN-LEFT
                else if (mouseEvent.getX() < mouseX && Math.abs(mouseEvent.getX() - mouseX) >= cellWidth){
                    direction = MovementDirection.DOWN_LEFT;
                    updatePlayerLocation(direction);
                    return;
                }

                //DOWN
                else{
                    direction = MovementDirection.DOWN;
                    updatePlayerLocation(direction);
                    return;
                }
            }

            //Checks UP
            else if(mouseEvent.getY() < mouseY && Math.abs(mouseEvent.getY() - mouseY) >= cellHeight){

                //UP-RIGHT
                if (mouseEvent.getX() > mouseX && Math.abs(mouseEvent.getX() - mouseX) >= cellWidth){
                    direction = MovementDirection.UP_RIGHT;
                    updatePlayerLocation(direction);
                    return;
                }

                //UP-LEFT
                else if (mouseEvent.getX() < mouseX && Math.abs(mouseEvent.getX() - mouseX) >= cellWidth){
                    direction = MovementDirection.UP_LEFT;
                    updatePlayerLocation(direction);
                    return;
                }

                //UP
                else{
                    direction = MovementDirection.UP;
                    updatePlayerLocation(direction);
                    return;
                }
            }

            //Checks RIGHT/LEFT
            else{
                if(mouseEvent.getX() > mouseX && Math.abs(mouseEvent.getX() - mouseX) >= cellWidth){
                    direction = MovementDirection.RIGHT;
                    updatePlayerLocation(direction);
                    return;
                }
                else if(mouseEvent.getX() < mouseX && Math.abs(mouseEvent.getX() - mouseX) >= cellWidth){
                    direction = MovementDirection.LEFT;
                    updatePlayerLocation(direction);
                    return;
                }
            }
        }
    }

    @Override
    public void getThreadNum() {
        Configurations config = Configurations.getInstance();
        int size = config.getThreadPoolSize();
        setChanged();
        notifyObservers(size);

    }

    @Override
    public void getGeneratingAlgo() throws CloneNotSupportedException {
        Configurations config = Configurations.getInstance();
        AMazeGenerator gener_algo = config.getmazeGeneratingAlgorithm();

        setChanged();
        notifyObservers(gener_algo);
    }

    @Override
    public void getSearchingAlgo() throws CloneNotSupportedException {
        Configurations config = Configurations.getInstance();
        ASearchingAlgorithm search_algo = config.getmazeSearchingAlgorithm();
        setChanged();
        notifyObservers(search_algo);
    }

    @Override
    public void setThreadNum(int num) {
        Configurations config = Configurations.getInstance();
        config.setThreadPoolSize(num);
        setChanged();
        notifyObservers(num);
    }

    @Override
    public void setGeneratingAlgo(String gener_algo_name) {
        Configurations config = Configurations.getInstance();
        config.setMazeGeneratingAlgorithm(gener_algo_name);

        setChanged();
        try {
            notifyObservers(config.getmazeGeneratingAlgorithm());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSolvingAlgo(String solving_algo_name) {
        Configurations config = Configurations.getInstance();
        config.setMazeSearchingAlgorithm(solving_algo_name);

        setChanged();
        try {
            notifyObservers(config.getmazeSearchingAlgorithm());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPlayerRow() {
        return this.player_Position.getRowIndex();
    }

    @Override
    public int getPlayerCol() {
        return this.player_Position.getColumnIndex();
    }

    @Override
    public void assignObserver(Observer o) {
        this.addObserver(o);
    }

    @Override
    public void solveMaze() {

        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401,new
                    IClientStrategy(){
                        @Override
                        public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                                toServer.flush();


                                toServer.writeObject(getMaze()); //send maze to server
                                toServer.flush();

                                //read generated maze (compressed with MyCompressor) from server
                                Solution mazeSolution = (Solution) fromServer.readObject();

                                //updates this.solution to be mazeSolution
                                setSolution(mazeSolution);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void setSolution(Solution mazeSolution) {
        this.solution = mazeSolution;
        setChanged();
        notifyObservers(mazeSolution);
    }

    @Override
    public Solution getSolution() {
        return this.solution;
    }

    @Override
    public void Load(Object object_loadedMaze) {
        Maze loadedMaze = new Maze((byte[]) object_loadedMaze);
        this.player_Position = loadedMaze.getStartPosition();
        setMaze(loadedMaze);
    }

    @Override
    public void saveGame() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Game","*.atpgame"));

        File file = chooser.showSaveDialog(null);
        if(file != null){
            try{
                File file_to_save = new File(file.getPath());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_to_save));
                Object o = this.getMaze().toByteArray();
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




    //Function that restarts the game by putting the player back to its start position
    public void restart_Game(){
        setPlayerPosition(this.getMaze().getStartPosition());
    }

}
