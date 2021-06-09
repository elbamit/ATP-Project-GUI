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
import java.io.FileNotFoundException;

public class MazeDisplayer extends Canvas {
    private Maze maze;
    private Solution solution;
    private Position last_position;
    private Position player_position = new Position(0,0);
    private double zooming_value = 0;
    private boolean isInvisible = false;


    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty(); //Right
    StringProperty imageFileNamePlayerLeft = new SimpleStringProperty();
    StringProperty imageFileNamePlayerUp = new SimpleStringProperty();
    StringProperty imageFileNamePlayerDown = new SimpleStringProperty();
    StringProperty imageFileNameGoal = new SimpleStringProperty();
    StringProperty imageFileNameSolution = new SimpleStringProperty();

    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    public String getImageFileNamePlayerLeft() {
        return imageFileNamePlayerLeft.get();
    }
    public String getImageFileNamePlayerUp() {
        return imageFileNamePlayerUp.get();
    }
    public String getImageFileNamePlayerDown() {
        return imageFileNamePlayerDown.get();
    }


    public String getImageFileNameGoal() { return imageFileNameGoal.get(); }
    public String getImageFileNameSolution() {
        return imageFileNameSolution.get();
    }


    public String imageFileNameWallProperty() {
        return imageFileNameWall.get();
    }

    public String imageFileNameGoalProperty() {
        return imageFileNameGoal.get();
    }


    public String imageFileNamePlayerProperty() {
        return imageFileNamePlayer.get();
    }

    public String imageFileNamePlayerLeftProperty() {
        return imageFileNamePlayerLeft.get();
    }

    public String imageFileNamePlayerUpProperty() {
        return imageFileNamePlayerUp.get();
    }
    public String imageFileNamePlayerDownProperty() {
        return imageFileNamePlayerDown.get();
    }

    public String imageFileNameSolutionProperty() {
        return imageFileNameSolution.get();
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

    public void setImageFileNamePlayerLeft(String imageFileNamePlayerLeft) {
        this.imageFileNamePlayerLeft.set(imageFileNamePlayerLeft);
    }

    public void setImageFileNamePlayerUp(String imageFileNamePlayerUp) {
        this.imageFileNamePlayerUp.set(imageFileNamePlayerUp);
    }

    public void setImageFileNamePlayerDown(String imageFileNamePlayerDown) {
        this.imageFileNamePlayerDown.set(imageFileNamePlayerDown);
    }

    public void setImageFileNameSolution(String imageFileNameSolution) {
        this.imageFileNameSolution.set(imageFileNameSolution);
    }






    public void setInvisible(){
        this.isInvisible = true;
    }

    public void setVisible(){
        this.isInvisible = false;
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
        if (this.maze != null){
            this.player_position = this.maze.getStartPosition();
            this.last_position = this.maze.getStartPosition();
        }
        ResetZoom();
        draw();
    }
    public void drawMaze(Maze maze,Position player_position){
        this.maze = maze;
        this.last_position = this.player_position;
        this.player_position = player_position;
        draw();
    }

    public void drawMaze(Maze maze, Position position, Solution solution) {
        this.maze = maze;
        this.last_position = this.player_position;
        this.player_position = position;
        this.solution = solution;
        draw();
    }


    @Override
    public double prefWidth(double v) {
        return getWidth();
    }

    @Override
    public double prefHeight(double v) {
        return getHeight();
    }

    private void draw() {
        if(this.maze != null){

            double cellHeight = getCellHeight();
            double cellWidth = getCellWidth();
//            System.out.println(this.getHeight());
//            System.out.println(this.getWidth());

            GraphicsContext gc = getGraphicsContext2D();

            //Clears the entire canvas
            double h = getHeight()*4;
            double w = getWidth()*4;
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
        Image SolImage = null;
        try {
            SolImage = new Image(new FileInputStream(getImageFileNameSolution()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        gc.setFill(Color.YELLOW);
        for (int i = 0; i < this.solution.getSolutionPath().size() - 1; i++){
            MazeState curr_state = (MazeState)this.solution.getSolutionPath().get(i);
            int curr_row = curr_state.getPos().getRowIndex();
            int curr_col = curr_state.getPos().getColumnIndex();

            double x = curr_col * cellWidth;
            double y = curr_row * cellHeight;
//            gc.fillRect(x,y,cellWidth,cellHeight);
            gc.drawImage(SolImage, x, y, cellWidth, cellHeight);
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
                    else if (this.isInvisible){
                        gc.setFill(Color.TRANSPARENT);
                        gc.fillRect(x,y,cellWidth, cellHeight);
                    }
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

        Image playerImageright = null;
        Image playerImageleft = null;
        Image playerImageup = null;
        Image playerImagedown = null;
        try {
            playerImageright = new Image(new FileInputStream(getImageFileNamePlayer()));
            playerImageleft = new Image(new FileInputStream(getImageFileNamePlayerLeft()));
            playerImageup = new Image(new FileInputStream(getImageFileNamePlayerUp()));
            playerImagedown = new Image(new FileInputStream(getImageFileNamePlayerDown()));
        } catch (Exception e) {
            System.out.println("There is no player image file");
        }
        if(playerImageright == null){
            graphicsContext.fillRect(x, y, cellWidth, cellHeight);
        }
        else if (last_position.getRowIndex() < this.player_position.getRowIndex()){
            graphicsContext.drawImage(playerImagedown, x, y, cellWidth, cellHeight);
        }
        else if (last_position.getColumnIndex() < this.player_position.getColumnIndex()){
            graphicsContext.drawImage(playerImageright, x, y, cellWidth, cellHeight);
        }
        else if (last_position.getRowIndex() > this.player_position.getRowIndex()){
            graphicsContext.drawImage(playerImageup, x, y, cellWidth, cellHeight);
        }
        else if(last_position.getColumnIndex() > this.player_position.getColumnIndex()){
            graphicsContext.drawImage(playerImageleft, x, y, cellWidth, cellHeight);
        }
        else{
            graphicsContext.drawImage(playerImageright, x, y, cellWidth, cellHeight);
        }

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
//        draw();
    }

    @Override
    public boolean isResizable() {
        return true;
    }
}


