package View;

import Model.MyModel;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PropertiesController extends ASceneChanger implements Initializable, Observer {

    private MyViewModel viewModel;

    @FXML
    public Text Thread_Num_Past;
    @FXML
    private TextField Thread_Num;
    @FXML
    public ComboBox Generating_Algorithm_Box;
    @FXML
    public Text Gener_algo_past;
    @FXML
    public ComboBox Solving_Algorithm_Box;
    @FXML
    public Text Solving_Algo_Past;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (this.viewModel == null) {
            MyModel model = new MyModel();
            this.viewModel = new MyViewModel(model);
            model.addObserver(viewModel);
            this.viewModel.addObserver(this);

            try {
                this.viewModel.getThreadNum();
                this.viewModel.getSearchingAlgo();
                this.viewModel.getGeneratingAlgo();

                this.Generating_Algorithm_Box.getItems().removeAll(this.Generating_Algorithm_Box.getItems());
                this.Generating_Algorithm_Box.getItems().addAll("Choose Generating Algorithm", "SimpleMaze Generator", "MyMaze Generator");


                this.Solving_Algorithm_Box.getItems().removeAll(this.Solving_Algorithm_Box.getItems());
                this.Solving_Algorithm_Box.getItems().addAll("Choose Solving Algorithm", "BFS - Breadth First Search", "DFS - Depth First Search", "Best First Search");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel){
            if(arg instanceof AMazeGenerator){
                this.Gener_algo_past.setText(arg.getClass().getName().substring(26));

            }
            else if (arg instanceof ASearchingAlgorithm){
                this.Solving_Algo_Past.setText(arg.getClass().getName().substring(18));

            }
            else{
                this.Thread_Num_Past.setText(String.valueOf(arg));
            }
        }
    }


    public void Save_Settings_Click(ActionEvent actionEvent) {
        String thread_number = this.Thread_Num.getText();
        Object gener_algo = this.Generating_Algorithm_Box.getValue();
        Object solving_algo = this.Solving_Algorithm_Box.getValue();

        //Checks if thread number texfield has changed and is correct
        if (!thread_number.trim().isEmpty()) {
            if (Generate_Custom_GameController.isNumeric(thread_number) && Integer.parseInt(thread_number) > 0) {
                this.viewModel.setThreadNum(Integer.parseInt(thread_number));
            }
            else {
                //TODO POP ERROR MESSAGE
            }
        }

        //Check if algorithm combo box has changed
        if(gener_algo != null && !this.Generating_Algorithm_Box.getSelectionModel().isEmpty() && gener_algo.toString() != "Choose Generating Algorithm"){
            switch (gener_algo.toString()){
                case "SimpleMaze Generator" -> this.viewModel.setGeneratingAlgo("SimpleMazeGenerator");
                case "MyMaze Generator" -> this.viewModel.setGeneratingAlgo("MyMazeGenerator");
            }
        }

        //Check if Solving combo box has changed
        if(solving_algo != null && !this.Solving_Algorithm_Box.getSelectionModel().isEmpty() && solving_algo.toString() != "Choose Solving Algorithm" ){
            switch (solving_algo.toString()){
                case "BFS - Breadth First Search" -> this.viewModel.setSolvingAlgo("BreadthFirstSearch");
                case "DFS - Depth First Search" -> this.viewModel.setSolvingAlgo("DepthFirstSearch");
                case "Best First Search" -> this.viewModel.setSolvingAlgo("BestFirstSearch");
            }
        }
    }

    public void Cancel_Button_Click(ActionEvent actionEvent) {
    }
}
