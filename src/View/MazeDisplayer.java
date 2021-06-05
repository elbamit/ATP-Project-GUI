package View;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;

public class MazeDisplayer extends Canvas {
    private Maze maze;
    private Solution solution;
    private Position player_position = new Position(0,0);

    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();

    public String imageFileNameWallProperty() {
        return imageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    public String imageFileNamePlayerProperty() {
        return imageFileNamePlayer.get();
    }

    public void setImageFileNamePlayer(String imageFileNamePlayer) {
        this.imageFileNamePlayer.set(imageFileNamePlayer);
    }

    public void setPlayer_position(Position player_position) {
        this.player_position = player_position;
    }

    public Solution getSolution() {
        return solution;
    }

    public Position getPlayer_position() {
        return player_position;
    }

    public void drawMaze(Maze maze){
        this.maze = maze;
        this.player_position = this.maze.getStartPosition();
        draw();
    }
    public void drawMaze(Maze maze,Position player_position){
        this.maze = maze;
        this.player_position = player_position;
        draw();
    }


    private void draw(){
        if(this.maze != null){
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            int maze_rows = this.maze.getMaze_matrix().length;
            int maze_cols = this.maze.getMaze_matrix()[0].length;

            double cellHeight = canvasHeight / maze_rows;
            double cellWidth = canvasWidth / maze_cols;

            GraphicsContext gc = getGraphicsContext2D();

            //Clears the entire canvas
            gc.clearRect(0,0,canvasHeight,canvasWidth);

            draw_Maze_Walls(gc, cellHeight, cellWidth);
            if(this.solution != null){
                drawSolution(gc,cellHeight,cellWidth);
            }
            drawPlayer(gc, cellHeight, cellWidth);
        }
    }

    //TODO
    private void drawSolution(GraphicsContext gc, double cellHeight, double cellWidth) {

    }

    private void draw_Maze_Walls(GraphicsContext gc, double cellHeight, double cellWidth) {
        gc.setFill(Color.RED);

        Image wallImage = null;
        try{
            wallImage = new Image(new FileInputStream(getImageFileNameWall()));
        } catch (Exception e) {
            System.out.println("There is no wall image file");
        }

        for (int i = 0; i < this.maze.getMaze_matrix().length; i++) {
            for (int j = 0; j < maze.getMaze_matrix()[0].length; j++) {
                if(maze.getMaze_matrix()[i][j] == 1){
                    //if it is a wall:
                    double x = j * cellWidth;
                    double y = i * cellHeight;
                    if(wallImage == null)
                        gc.fillRect(x, y, cellWidth, cellHeight);
                    else
                        gc.drawImage(wallImage, x, y, cellWidth, cellHeight);
                }
            }
        }
    }

    private void drawPlayer(GraphicsContext graphicsContext, double cellHeight, double cellWidth) {
        double x = this.player_position.getColumnIndex() * cellWidth;
        double y = this.player_position.getRowIndex() * cellHeight;

        graphicsContext.setFill(Color.RED);

        Image playerImage = null;
        try {
            playerImage = new Image(new FileInputStream(getImageFileNamePlayer()));
        } catch (Exception e) {
            System.out.println("There is no player image file");
        }
        if(playerImage == null)
            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
        else
            graphicsContext.drawImage(playerImage, x, y, cellWidth, cellHeight);
    }

}
