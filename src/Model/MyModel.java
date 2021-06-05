package Model;

import Client.Client;
import IO.MyDecompressorInputStream;
import Server.Server;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import Client.IClientStrategy;
import javafx.scene.control.Alert;

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
        this.mazeGeneratorServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        this.mazeSolverServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
    }

    public void startServers(){
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
        notifyObservers(this.maze);
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

    //Function that restarts the game by putting the player back to its start position
    public void restart_Game(){
        setPlayerPosition(this.getMaze().getStartPosition());
    }

}
