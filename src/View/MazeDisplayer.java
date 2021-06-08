package View;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.MazeState;
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
    private double zooming_value = 0;

    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageFileNameGoal = new SimpleStringProperty();

    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    //TODO change the picture
    public String getImageFileNameGoal() { return imageFileNameGoal.get(); }

    public String imageFileNameWallProperty() {
        return imageFileNameWall.get();
    }

    public String imageFileNameGoalProperty() {
        return imageFileNamePlayer.get();
    }

    public String imageFileNamePlayerProperty() {
        return imageFileNamePlayer.get();
    }


    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }

    public void setImageFileNameGoal(String imageFileNameGoal) {
        this.imageFileNameGoal.set(imageFileNameGoal);
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
        ResetZoom();
        draw();
    }
    public void drawMaze(Maze maze,Position player_position){
        this.maze = maze;
        this.player_position = player_position;
        draw();
    }

    public void drawMaze(Maze maze, Position position, Solution solution) {
        this.maze = maze;
        this.player_position = position;
        this.solution = solution;
        draw();
    }


    private void draw(){
        if(this.maze != null){

            double cellHeight = getCellHeight();
            double cellWidth = getCellWidth();

            GraphicsContext gc = getGraphicsContext2D();

            //Clears the entire canvas
            double h = getHeight()+200;
            double w = getWidth()+200;
            gc.clearRect(0,0,h,w);

            draw_Maze_Walls(gc, cellHeight, cellWidth);
            if(this.solution != null){
                drawSolution(gc,cellHeight,cellWidth);
            }
            drawPlayer(gc, cellHeight, cellWidth);

            drawGoal(gc, cellHeight, cellWidth);

        }
    }

    private void drawGoal(GraphicsContext gc, double cellHeight, double cellWidth) {
        double x = this.maze.getGoalPosition().getColumnIndex() * cellWidth;
        double y = this.maze.getGoalPosition().getRowIndex() * cellHeight;

        gc.setFill(Color.BLUE);

        Image GoalImage = null;
        try {
            GoalImage = new Image(new FileInputStream(getImageFileNameGoal()));
        } catch (Exception e) {

        }
        if(GoalImage == null)
            gc.fillRect(x, y, cellWidth, cellHeight);
        else
            gc.drawImage(GoalImage, x, y, cellWidth, cellHeight);
    }




    private void drawSolution(GraphicsContext gc, double cellHeight, double cellWidth) {
        gc.setFill(Color.YELLOW);
        for (int i = 0; i < this.solution.getSolutionPath().size() - 1; i++){
            MazeState curr_state = (MazeState)this.solution.getSolutionPath().get(i);
            int curr_row = curr_state.getPos().getRowIndex();
            int curr_col = curr_state.getPos().getColumnIndex();

            double x = curr_col * cellWidth;
            double y = curr_row * cellHeight;
            gc.fillRect(x,y,cellWidth,cellHeight);
        }
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


    public void deleteSolutionPath() {
        this.solution = null;
        this.draw();
    }

    public double getCellHeight() {
        double canvasHeight = getHeight();
        int maze_rows = this.maze.getMaze_matrix().length;
        double cellHeight = (canvasHeight + this.zooming_value) / maze_rows;
        return cellHeight;
    }

    public double getCellWidth() {
        double canvasWidth = getWidth();
        int maze_cols = this.maze.getMaze_matrix()[0].length;
        double cellWidth = (canvasWidth + this.zooming_value) / maze_cols;
        return cellWidth;
    }

    public void ZoomIn() {
        this.zooming_value+=20;
        draw();
    }

    public void ZoomOut() {
        if (((double)this.getHeight() + this.zooming_value) / this.maze.getMaze_matrix().length >= 1 && ((double) this.getWidth() + this.zooming_value)/this.maze.getMaze_matrix()[0].length >= 1){
            this.zooming_value-=20;
            draw();
        }
    }

    public void ResetZoom(){
        this.zooming_value = 0;
        draw();
    }
}


