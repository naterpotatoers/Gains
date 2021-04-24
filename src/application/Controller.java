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
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.net.URL;
import java.util.ArrayList;
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
    private TextField difficultyLevelLabel2;
    @FXML
    private DatePicker myDatePicker;
    @FXML
    private TextArea lastWorkoutTextArea;
    

    private Stage stage;
    private Scene scene;
    private Parent root;
//    private WorkoutController workout;
    private WorkoutController workout = new WorkoutController();
    private ArrayList<WeightTraining> weightExercises = new ArrayList<WeightTraining>();
    private ArrayList<CardioTraining> cardioExercises = new ArrayList<CardioTraining>();
    private String username = "nate";
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	displayLastWorkout();
	}

    //display last workout
    public void displayLastWorkout()
    {
    	if(lastWorkoutTextArea != null)
    	{    
//    		workout = new WorkoutController();
        	weightExercises = workout.getAllWeightWorkouts();
        	cardioExercises = workout.getAllCardioWorkouts();
    		// Need to make it look pretty, might want to look into using CSS
    		lastWorkoutTextArea.setText("Weight Workout:\n" + weightExercises.get(0).toString() + "\n\nCardio Workout:\n" + cardioExercises.get(0).toString());
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

    // Grab and save user's input from Add Weight Training page
    public void submitAndSaveWeightTraining(ActionEvent e) throws IOException
    {
        String exerciseName = exerciseNameLabel.getText();
        String numberOfReps = numberOfRepsLabel.getText();
        String numberOfSets = numberOfSetsLabel.getText();
        String difficultyLevel = difficultyLevelLabel.getText();
        String avgSetDuration = avgSetDurationLabel.getText();
        String amountOfWeight = amountOfWeightLabel.getText();
        LocalDate localDate = myDatePicker.getValue();
        // Convert data types
        int sets = Integer.parseInt(numberOfSets);
        int reps = Integer.parseInt(numberOfReps);
        int weight = Integer.parseInt(amountOfWeight);
        Date date = java.sql.Date.valueOf(localDate);
        
        WeightTraining entry = new WeightTraining(username, exerciseName, difficultyLevel, avgSetDuration, date, weight, sets, reps);
        workout.addWeightExercise(entry);
        
        //Swaps back to the home page after hitting the submit button
        switchToPage(e, "Homepage.fxml");
    }
    
    // Grab user's input from Add Cardio page
    public void submitAndSaveCardio(ActionEvent e) throws IOException
    {
        String exerciseName = exerciseNameLabel.getText();
        String difficultyLevel = difficultyLevelLabel.getText();
        String duration = durationLabel.getText();
        LocalDate localDate = myDatePicker.getValue();
        // Convert data types
        Date date = java.sql.Date.valueOf(localDate);
        
        CardioTraining entry = new CardioTraining(username, exerciseName, difficultyLevel, duration, date);
        workout.addCardioExercise(entry);
        
        //Swaps back to the home page after hitting the submit button
        switchToPage(e, "Homepage.fxml");
    }
    
    private void switchToPage(ActionEvent event, String targetPage) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource(targetPage));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void numberFormatter(KeyEvent event) {
    	numberOfSetsLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	numberOfSetsLabel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	
    	avgSetDurationLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	avgSetDurationLabel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	
    	amountOfWeightLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	amountOfWeightLabel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	
    	numberOfRepsLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	numberOfRepsLabel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	
    	difficultyLevelLabel.setTextFormatter(new TextFormatter<>(this::filter));
    	
    }
    
    public void numberFormatter2(KeyEvent event) { 
    	durationLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	durationLabel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    	difficultyLevelLabel2.setTextFormatter(new TextFormatter<>(this::filter));
    }
    
    private TextFormatter.Change filter(TextFormatter.Change change) {
        if (!change.getControlNewText().matches("([1-9]|1[0-0])")) {
            change.setText("");
        }
        return change;
    }
}