package View;

import Model.MyModel;
import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.*;
import algorithms.search.Solution;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.File;

import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MyViewController extends ASceneChanger implements Initializable, Observer,IView, IController{
    MediaPlayer player;
    public MyViewModel viewModel;
    @FXML
    public MazeDisplayer mazeDisplayer = new MazeDisplayer();
    @FXML
    public BorderPane border_pane;
    @FXML
    public Pane button_pane;
    @FXML
    public Button restart_game_button;
    @FXML
    public Button solve_button;
    @FXML
    public Button back_button;
    @FXML
    public VBox menu_bar;
    @FXML
    private Pane MazePane;
    public static int row;
    public static int col;
    public static boolean loaded=false;

    private double MouseX;
    private double MouseY;
    private boolean Drag_started = false;
    MediaPlayer themeMediaPlayer = null;


    public void setViewModel(MyViewModel viewModel) {
        if (this.viewModel == null){
            this.viewModel = viewModel;
            this.viewModel.addObserver(this);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            if (arg instanceof Maze) {
                displayMaze((Maze) arg);
            }
            else if(arg instanceof Position){
                mazeDisplayer.drawMaze(viewModel.getMaze(),(Position)arg);
            }
            else if(arg instanceof Solution){
                mazeDisplayer.drawMaze(viewModel.getMaze(), (new Position(viewModel.getPlayerPositionRow(), viewModel.getPlayerPositionCol())), (Solution) arg);

            }
            else if(arg instanceof String){
                if(((String)arg).equals("Start the party") && themeMediaPlayer==null){
                    //mazeDisplayer.drawMaze(viewModel.getMaze(),viewModel.getMaze().getStartPosition());
                    //displayMaze((Maze) arg);
                    DJ_PlayMusic();
                    StartTheParty();
                    mazeDisplayer.resetSol();
                    viewModel.restartGame();
                }
                else{
                    stopMaccabiSound();
                    StartThePartyMaccabi();
                    mazeDisplayer.resetSol();
                    viewModel.restartGame();
                }
            }
        }

    }

    private void DJ_PlayMusic() {
        Media sound = new Media(this.getClass().getResource("/sounds/Aluf.mp3").toString());
        player = new MediaPlayer(sound);
        player.play();
    }

    private void StartTheParty() {
        try {
            String path = "C:\\Users\\omrim\\Desktop\\הנדסת מערכות מידע\\שנה ב'\\סימסטר ד'\\נושאים מתקדמים בתכנות\\פרוייקט\\ATP-Project-GUI\\resources\\images\\FinalGoal.mp4";
            //Instantiating Media class
            Media media = new Media(new File(System.getProperty ("user.dir") + "\\resources\\images\\FinalGoal.mp4").toURI().toString());
            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            //Instantiating MediaView class
            MediaView mediaView = new MediaView(mediaPlayer);
            //by setting this property to true, the Video will be played
            mediaPlayer.setAutoPlay(true);
            //setting group and scene
            Group root1 = new Group();
            root1.getChildren().add(mediaView);
            Stage stage = new Stage();
            stage.setTitle("you are the best ! ! !");
            Scene scene = new Scene(root1, 900, 420);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                //stage.close();
                stage.setTitle("Goalllllllllllllllllllll");
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = null;
                try {
                    root = fxmlLoader.load(getClass().getResource("WinScreen.fxml").openStream());
                } catch (IOException e) {
                    System.out.println("here");
                }
                Scene scene1 = new Scene(root, 600, 400);
                stage.setScene(scene1);
                //stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                stage.show();

            });
            delay.play();
            }catch (Exception e){}


    }

    private void StartThePartyMaccabi() {
        try {
            //String path = "C:\\Users\\omrim\\Desktop\\הנדסת מערכות מידע\\שנה ב'\\סימסטר ד'\\נושאים מתקדמים בתכנות\\פרוייקט\\ATP-Project-GUI\\resources\\images\\FinalGoal.mp4";
            //Instantiating Media class
            Media media = new Media(new File(System.getProperty ("user.dir") + "\\resources\\images\\partyMaccabi.mp4").toURI().toString());
            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            //Instantiating MediaView class
            MediaView mediaView = new MediaView(mediaPlayer);
            //by setting this property to true, the Video will be played
            mediaPlayer.setAutoPlay(true);
            //setting group and scene
            Group root1 = new Group();
            root1.getChildren().add(mediaView);
            Stage stage = new Stage();
            stage.setTitle("you are the best ! ! !");
            Scene scene = new Scene(root1, 900, 620);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("after 10 years, Maccabi Haifa is the champion again, the championship is back home to Carmel,Championship number 13!");
            a.show();
/*            PauseTransition delay = new PauseTransition(Duration.seconds(160));
            delay.setOnFinished(event -> {
                stage.close();
            });
            delay.play();*/
        }catch (Exception e){}


    }

    public MyViewController returnthis(){
        return this;
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        mazeDisplayer.requestFocus();
    }//מהתרגול


    //Function that does stuff upon loading the MyView fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.viewModel== null){

            MyModel model = new MyModel();
            model.startServers();

            MyViewModel viewModel1 = new MyViewModel(model);
            model.addObserver(viewModel1);
            setViewModel(viewModel1);
        }

        if (loaded){

            try {
                Load_Maze_and_display();
            } catch (IOException e) {
            }
        }
        else{
            MazePane.setPrefHeight(500);
            MazePane.setPrefWidth(900);
//            mazeDisplayer.setHeight(MazePane.getHeight());
//            mazeDisplayer.setWidth(MazePane.getWidth());
//            mazeDisplayer.heightProperty().bind(MazePane.heightProperty());
//            mazeDisplayer.widthProperty().bind(MazePane.widthProperty());
            generateMazeAuto();
        }

    }



    private void Load_Maze_and_display() throws IOException {
        //open window for user to load
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Game","*.atpgame"));
        chooser.setTitle("Choose Maze File");
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            try {
                File loaded_file = new File(file.getPath());
                FileInputStream inputStream = new FileInputStream(loaded_file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Object o = (Object) objectInputStream.readObject();
                //Maze loadedMaze = (Maze) o[0];
                viewModel.Load(o);
                objectInputStream.close();
                inputStream.close();

            } catch (Exception e) {
                Alert a = new Alert((Alert.AlertType.ERROR));
                a.setContentText("Error in loading the maze... Please try again");
                a.show();
                MyViewController.row = 10;
                MyViewController.col = 10;

            }
        }
        else{
            Alert a1 = new Alert((Alert.AlertType.INFORMATION));
            a1.setContentText("Please don't look for bugs, just enjoy our awesome game ;)"); //TODO maybe remove
            a1.show();
            Alert a = new Alert((Alert.AlertType.ERROR));
            a.setContentText("Error in loading the maze... Please try again");
            a.show();
            MyViewController.row = 10;
            MyViewController.col = 10;



        }
        loaded = false;

    }

    private void generateMazeAuto() {
        viewModel.generateMaze(row,col);

    }
    public void KeyPressed(KeyEvent keyEvent) {
        viewModel.movePlayer(keyEvent);
        keyEvent.consume();
    }




    @Override
    public void displayMaze(Maze maze) {
        mazeDisplayer.drawMaze(maze);
    }






    private void Restart_Game(){
        this.viewModel.restartGame();
        this.mazeDisplayer.deleteSolutionPath();
        this.mazeDisplayer.requestFocus();
    }

    public void DragDetected(MouseEvent mouseEvent) {
        this.MouseX = mouseEvent.getX();
        this.MouseY = mouseEvent.getY();
        this.Drag_started = true;
    }

    public void MouseDragged(MouseEvent mouseEvent) {
        if (this.Drag_started && enough_drag(mouseEvent, this.MouseX, this.MouseY)){
            this.viewModel.movePlayer(mouseEvent, this.MouseX, this.MouseY, this.mazeDisplayer.getCellHeight(), this.mazeDisplayer.getCellWidth());
            this.MouseX = mouseEvent.getX();
            this.MouseY = mouseEvent.getY();
        }
    }

    public void MousePressed(MouseEvent mouseEvent) {
        MouseX = mouseEvent.getX();
        MouseY = mouseEvent.getY();
    }

    public void MouseReleased(MouseEvent mouseEvent) {
        this.Drag_started = false;
        this.MouseX = mouseEvent.getX();
        this.MouseY = mouseEvent.getY();
        mouseEvent.consume();
    }

    private boolean enough_drag(MouseEvent mouseEvent, double mouseX, double mouseY){
        double cell_height = this.mazeDisplayer.getCellHeight();
        double cell_width = this.mazeDisplayer.getCellWidth();

        if (Math.abs(mouseX-mouseEvent.getX()) >= cell_width || Math.abs(mouseY-mouseEvent.getY()) >= cell_height){
            return true;
        }

       else {
           return false;
        }
    }

    public void setInvisible(){
        this.mazeDisplayer.setInvisible();
    }
    public void setMaccabi(){this.mazeDisplayer.setMaccabi();}

    public void setvisible(){
        this.mazeDisplayer.setVisible();
    }




    public void MouseScrolled(ScrollEvent scrollEvent) {
        if (scrollEvent.isControlDown()){//holds control simultaneously of scrolling
            if(scrollEvent.getDeltaY() > 0){//zoom in
                this.mazeDisplayer.ZoomIn();
            }
            else {
                this.mazeDisplayer.ZoomOut();
            }
        }
    }

    public void SolveMaze(ActionEvent actionEvent) {
        this.viewModel.solveMaze();
        this.mazeDisplayer.requestFocus();
    }

    public void Back_To_Game_Options(ActionEvent actionEvent) throws IOException {
        change_scene(actionEvent, "Game Options", "GameOptions.fxml");
    }

    public void Restart_Game_Click(ActionEvent actionEvent) {
        Restart_Game();
    }

    public void Properties_Click(ActionEvent actionEvent) throws IOException {
        new_stage("Properties.fxml", "Properties");
    }

    public void Help_Click(ActionEvent actionEvent) throws IOException {
        new_stage("HelpScreen.fxml", "Help");
    }

    public void About_Click(ActionEvent actionEvent) throws IOException {
        new_stage("AboutScreen.fxml", "About");
    }


    public void New_Game_From_Menu_Click(ActionEvent actionEvent) throws IOException{
        Stage stage =(Stage) this.mazeDisplayer.getScene().getWindow();
        change_scene_menu(stage, "Game Options", "GameOptions.fxml");

    }

    public void SaveGame(ActionEvent actionEvent) {
        this.viewModel.saveGame();
    }

    public void Load_From_Menu_Bar_Click(ActionEvent actionEvent) throws IOException {
        MyViewController.loaded=true;
        Stage stage =(Stage) this.mazeDisplayer.getScene().getWindow();
        OpeningScreenController.background_music = false;
        change_scene_menu(stage, "Game", "MyView.fxml");
    }

    public void close_button_click(ActionEvent actionEvent) {
        exit_from_menu();
    }



    @Override
    public void closeWindow() {

    }


    public void playMaccabiSound() {
        if(this.mazeDisplayer.Maccabi){
            String path = this.getClass().getResource("/sounds/maccabiSound.mp3").toString();
            Media sound = new Media(path);
            //MediaPlayer
            themeMediaPlayer = new MediaPlayer(sound);
            themeMediaPlayer.play();

        }
    }
    public void stopMaccabiSound() {
        if(this.themeMediaPlayer !=null){
            themeMediaPlayer.stop();
        }
    }

    public void setResizeEvent(Scene scene) {
//        mazeDisplayer.widthProperty().bind(MazePane.widthProperty());
//        mazeDisplayer.heightProperty().bind(MazePane.heightProperty());

        MazePane.setLayoutX(0);
        MazePane.setLayoutY(0);

        //stage.getScene().
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            MazePane.setPrefHeight(scene.getHeight()*0.84);
            mazeDisplayer.setHeight(MazePane.getHeight());
            button_pane.setPrefHeight(scene.getHeight());

            back_button.setPrefHeight(scene.getHeight()*0.066);
            back_button.setMaxHeight(50);

            solve_button.setPrefHeight(scene.getHeight()*0.066);
            solve_button.setMaxHeight(50);

            restart_game_button.setPrefHeight(scene.getHeight()*0.066);
            restart_game_button.setMaxHeight(50);

            menu_bar.setPrefHeight(menu_bar.getPrefHeight());

            mazeDisplayer.drawMaze(viewModel.getMaze());


        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            MazePane.setPrefWidth(scene.getWidth()*0.666);
            mazeDisplayer.setWidth(MazePane.getWidth());

            button_pane.setPrefWidth(scene.getWidth()/6);

            back_button.setPrefWidth(scene.getWidth()*(0.15));
            back_button.setMaxWidth(260);

            solve_button.setPrefWidth(scene.getWidth()*(0.15));
            solve_button.setMaxWidth(260);

            restart_game_button.setPrefWidth(scene.getWidth()*(0.15));
            restart_game_button.setMaxWidth(260);

            menu_bar.setPrefWidth(scene.getWidth());

            mazeDisplayer.drawMaze(viewModel.getMaze());
        });

        mazeDisplayer.drawMaze(viewModel.getMaze());

    }
}
