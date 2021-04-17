package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * 
 * @author Anthony Tran, Andrilalaina Obeda Velonjatovo, Benjamin Seang, Nathanael Garza			
 *
 * Controller class for the program
 *
 */


public class Controller {
    @FXML
    private TextField exerciseNameLabel;
    @FXML
    private TextField numberOfSetsLabel;
    @FXML
    private TextField avgSetDurationLabel;
    @FXML
    private TextField numberOfRepsLabel;
    @FXML
    private TextField difficultyLevelLabel;
    @FXML
    private DatePicker myDatePicker;
    

    private Stage stage;
    private Scene scene;
    private Parent root;
    
   
    //switch to AddWorkout page 
    public void switchToAddWorkout(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("AddWorkout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //switch to Homepage 
    public void switchToHomepage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //grab user's input from AddWorkout page
    public void grabFormData(ActionEvent e){
        String exerciseName = exerciseNameLabel.getText();
        String numberOfReps = numberOfRepsLabel.getText();
        String numberOfSets = numberOfSetsLabel.getText();
        String difficultyLevel = difficultyLevelLabel.getText();
        String avgSetDuration = avgSetDurationLabel.getText();
        String date = myDatePicker.getValue().toString();

        //build a string that is being saved to the save file
        String data =  	date
        				+ " " + exerciseName
        				+ " " + numberOfSets
        				+ " " + numberOfReps
        				+ " " + avgSetDuration
        				+ " " + difficultyLevel;
        
        //save user's input to a txt file
        utility.saveToFile(data);
    }
    
    
    

}