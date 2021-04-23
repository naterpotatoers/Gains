package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author Anthony Tran, Andrilalaina Obeda Velonjatovo, Benjamin Seang, Nathanael Garza			
 *
 * Controller class for the program
 *
 */


public class Controller implements Initializable {
    @FXML
    private TextField exerciseNameLabel;
    @FXML
    private TextField numberOfSetsLabel;
    @FXML
    private TextField avgSetDurationLabel;
    @FXML
    private TextField durationLabel;
    @FXML
    private TextField numberOfRepsLabel;
    @FXML
    private TextField amountOfWeightLabel;
    @FXML
    private TextField difficultyLevelLabel;
    @FXML
    private DatePicker myDatePicker;
    @FXML
    private TextArea lastWorkoutTextArea;
    @FXML 
    private TextArea WorkoutTipTextArea;
    

    private Stage stage;
    private Scene scene;
    private Parent root;
    private WorkoutController workout = new WorkoutController();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    	displayLastWorkout();
    	displayTip();

	}

    //display last workout
    public void displayLastWorkout()
    {
    	if(lastWorkoutTextArea != null)
    	{
    		lastWorkoutTextArea.setText("Hello");
    	}
    }
    
    //display a random tip
    public void displayTip()
    {
    	if(WorkoutTipTextArea != null)
    	{
    		WorkoutTipTextArea.setText(WorkoutTips.getTip());
    	}
    }

    // Switch to Homepage 
    public void switchToHomepage(ActionEvent event) throws IOException
    {
    	switchToPage(event, "Homepage.fxml");
    }
    
    // Switch to Workout Type Select 
    public void switchToWorkoutTypeSelect(ActionEvent event) throws IOException
    {
    	switchToPage(event, "WorkoutTypeSelect.fxml");
    }
    
   // Switch to AddWeightTraining 
    public void switchAddToWeightTraining(ActionEvent event) throws IOException
    {
    	switchToPage(event, "AddWeightTraining.fxml");
    }
    
    // Switch to Cardio 
    public void switchToAddCardio(ActionEvent event) throws IOException
    {
    	switchToPage(event, "AddCardio.fxml");
    }
    
    public void saveWeightExercise(ActionEvent e) {

    }
    
    public void saveCardioExercise(ActionEvent e) {
    	
    }

    // Grab and save user's input from Add Weight Training page
    public void submitAndSaveWeightTraining(ActionEvent e)
    {
        String exerciseName = exerciseNameLabel.getText();
        String numberOfReps = numberOfRepsLabel.getText();
        String numberOfSets = numberOfSetsLabel.getText();
        String difficultyLevel = difficultyLevelLabel.getText();
        String avgSetDuration = avgSetDurationLabel.getText();
        String amountOfWeight = amountOfWeightLabel.getText();
        String date = myDatePicker.getValue().toString(); // LocalDate or String
        int sets = Integer.parseInt(numberOfSets);
        int reps = Integer.parseInt(numberOfReps);
        int weight = Integer.parseInt(amountOfWeight);
        Date dateDate = new Date(2021-04-22); // Need it to be SQL Date
        WeightTraining entry = new WeightTraining("nate", exerciseName, difficultyLevel, avgSetDuration, dateDate, weight, sets, reps);
        workout.addWeightsExercise(entry);
        // // Build a string that is being saved to the save file
        // String data =  	date
        // 				+ " " + exerciseName
        // 				+ " " + numberOfSets
        // 				+ " " + numberOfReps
        // 				+ " " + avgSetDuration
        // 				+ " " + difficultyLevel;
        // // Save user's input
        // utility.saveWorkoutToFile(data);
    }
    
    // Grab user's input from Add Cardio page
    public void submitAndSaveCardio(ActionEvent e)
    {
        String exerciseName = exerciseNameLabel.getText();
        String difficultyLevel = difficultyLevelLabel.getText();
        String duration = durationLabel.getText();
        String date = myDatePicker.getValue().toString();

        // Build a string that is being saved to the save file
        String data =  	date
        				+ " " + exerciseName
        				+ " " + duration
        				+ " " + difficultyLevel;
        
        // Save user's input to a txt file
        utility.saveWorkoutToFile(data);
    }
    
    private void switchToPage(ActionEvent event, String targetPage) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource(targetPage));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}