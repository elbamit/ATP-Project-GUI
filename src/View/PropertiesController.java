package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class PropertiesController extends ASceneChanger implements Initializable{
    @FXML
    public Label Thread_Num_Past;
    public ComboBox Generating_Algorithm_Box;
    public ComboBox Searching_Algorithm_Box;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Generating_Algorithm_Box.show();
    }
}
