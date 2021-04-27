package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TextArea lastWeightTrainingTextArea;
    @FXML
    private TextArea lastCardioTextArea;
    @FXML 
    private TextArea WorkoutTipTextArea;
    @FXML
    private TextField difficultyLevelLabel2;
    @FXML
    private TableView<CardioTraining> cardioHistoryTable;
    @FXML
    private TableColumn<CardioTraining, Date> cardioHistoryDateColumn;
    @FXML
    private TableColumn<CardioTraining, String> cardioHistoryDurationColumn;
    @FXML
    private TableColumn<CardioTraining, String> cardioHistoryExerciseNameColumn;
    @FXML
    private TableColumn<CardioTraining, String> cardioHistoryDifficultyColumn;
    @FXML
    private TableView<WeightTraining> weightHistoryTable;
    @FXML
    private TableColumn<WeightTraining, Date> weightHistoryDateColumn;
    @FXML
    private TableColumn<WeightTraining, Date> weightHistoryExerciseNameColumn;
    @FXML
    private TableColumn<WeightTraining, String> weightHistoryDurationColumn;
    @FXML
    private TableColumn<WeightTraining, String> weightHistoryWeightColumn;
    @FXML
    private TableColumn<WeightTraining, Date> weightHistorySetsColumn;
    @FXML
    private TableColumn<WeightTraining, Date> weightHistoryRepsColumn;
    @FXML
    private TableColumn<WeightTraining, Date> weightHistoryDifficultyColumn;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private WorkoutController workout = new WorkoutController();
    private ArrayList<WeightTraining> weightExercises = new ArrayList<WeightTraining>();
    private ArrayList<CardioTraining> cardioExercises = new ArrayList<CardioTraining>();
    private String username = "nate";
    
    /** This function is called on every page load. Used for setting the text from database. */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	displayLastWeightTraining();
    	displayLastCardio();
    	displayCardioHistory();
    	displayWeightHistory();
    	displayTip();
	}
    
    /** Populates WeightHistory.fxml page with weight data */
    public void displayWeightHistory() {
        if (weightHistoryTable != null){
            weightExercises = workout.getAllWeightWorkouts();
            ObservableList<WeightTraining> weightWorkouts = FXCollections.observableArrayList(weightExercises);
            weightHistoryDateColumn.setCellValueFactory(new PropertyValueFactory<>("workoutDate"));
            weightHistoryExerciseNameColumn.setCellValueFactory(new PropertyValueFactory<>("workoutName"));
            weightHistoryDurationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
            weightHistoryWeightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
            weightHistorySetsColumn.setCellValueFactory(new PropertyValueFactory<>("sets"));
            weightHistoryRepsColumn.setCellValueFactory(new PropertyValueFactory<>("reps"));
            weightHistoryDifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
            weightHistoryTable.setItems(weightWorkouts);
        }
	}
    
	/** Populates CardioHistory.fxml page with cardio data */
	public void displayCardioHistory(){
        if (cardioHistoryTable != null){
            cardioExercises = workout.getAllCardioWorkouts();
            ObservableList<CardioTraining> cardioWorkouts = FXCollections.observableArrayList(cardioExercises);
            cardioHistoryDateColumn.setCellValueFactory(new PropertyValueFactory<>("workoutDate"));
            cardioHistoryDurationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
            cardioHistoryExerciseNameColumn.setCellValueFactory(new PropertyValueFactory<>("workoutName"));
            cardioHistoryDifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
            cardioHistoryTable.setItems(cardioWorkouts);
        }
    }

    /** Display last weight training history */
    public void displayLastWeightTraining()
    {
    	if(lastWeightTrainingTextArea != null)
    	{    
        	weightExercises = workout.getAllWeightWorkouts();
        	lastWeightTrainingTextArea.setText(weightExercises.get(0).toString());
    	}
    }
    
    /** Display last cardio history */
    public void displayLastCardio()
    {
    	if(lastCardioTextArea != null)
    	{    
        	cardioExercises = workout.getAllCardioWorkouts();
        	lastCardioTextArea.setText(cardioExercises.get(0).toString());
    	}
    }
    
    /** Display a random workout tip */
    public void displayTip()
    {
    	if(WorkoutTipTextArea != null)
    	{
    		WorkoutTipTextArea.setText(WorkoutTips.getTip());
    	}
    }
    
    /**
     * Helper function for switching between fxml pages 
     * @param targetPage name of fxml page. Ex. AddCardio.fxml
     */
    private void switchToPage(ActionEvent event, String targetPage) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource(targetPage));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Page switching handlers
    public void switchToHomepage(ActionEvent event) throws IOException
    {
    	switchToPage(event, "Homepage.fxml");
    }
    
    public void switchToWorkoutTypeSelect(ActionEvent event) throws IOException
    {
    	switchToPage(event, "WorkoutTypeSelect.fxml");
    }
    
    public void switchAddToWeightTraining(ActionEvent event) throws IOException
    {
    	switchToPage(event, "AddWeightTraining.fxml");
    }
    
    public void switchToAddCardio(ActionEvent event) throws IOException
    {
    	switchToPage(event, "AddCardio.fxml");
    }
    
    public void switchToHistoryTypeSelect(ActionEvent event) throws IOException
    {
    	switchToPage(event, "HistoryTypeSelect.fxml");
    }
    
    public void switchToCardioHistory(ActionEvent event) throws IOException
    {
    	switchToPage(event, "CardioHistory.fxml");
    }
    
    public void switchToWeightHistory(ActionEvent event) throws IOException
    {
    	switchToPage(event, "WeightHistory.fxml");
    }
    

    /** Grabs user's input from AddWeightTraining page and saves it to database */
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
        
        // Swaps back to the home page after hitting the submit button
        switchToPage(e, "Homepage.fxml"); // TODO: change this to use switchToHomepage()
    }
    
    /** Grabs user's input from AddCardio page and saves it to database */
    public void submitAndSaveCardio(ActionEvent e) throws IOException
    {
        String exerciseName = exerciseNameLabel.getText();
        String difficultyLevel = difficultyLevelLabel2.getText();
        String duration = durationLabel.getText();
        LocalDate localDate = myDatePicker.getValue();
        // Convert data types
        Date date = java.sql.Date.valueOf(localDate);
        
        CardioTraining entry = new CardioTraining(username, exerciseName, difficultyLevel, duration, date);
        workout.addCardioExercise(entry);
        
        //Swaps back to the home page after hitting the submit button
        switchToPage(e, "Homepage.fxml"); // TODO: change this to use switchToHomepage()
    }
    
    
    /** Validates user input such that only numbers can be entered into text fields */
    public void numberFormatter(KeyEvent event) {
    	// TODO: Make helper function to reduce repeated code such that we only need to pass in fx:id
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

    /** Validates user input such that only numbers can be entered into text fields */
    public void numberFormatter2(KeyEvent event) { 
    	// TODO: Not sure why there are two separate formatter handlers, might need to refactor code
    	durationLabel.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (!newValue.matches("\\d*")) {
    			durationLabel.setText(newValue.replaceAll("[^\\d]", ""));
    		}
    	});
    	difficultyLevelLabel2.setTextFormatter(new TextFormatter<>(this::filter));
    }
    
    /** Deletes bad user input? */
    private TextFormatter.Change filter(TextFormatter.Change change) {
    	// TODO: Not sure why we would want to reset text field? Seems like poor user experience
    	if (!change.getControlNewText().matches("([1-9]|1[0-0])")) {
    		change.setText("");
    	}
    	return change;
    }
}