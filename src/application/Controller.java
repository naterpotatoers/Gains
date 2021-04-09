package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    @FXML
    private TextField exerciseNameLabel;
    @FXML
    private TextField numberOfRepsLabel;
    @FXML
    private TextField numberOfSetsLabel;
    @FXML
    private TextField difficultyLevelLabel;
    @FXML
    private TextField avgSetDurationLabel;
    @FXML
    private TextField dateLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    //
    public void switchToAddWorkout(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("AddWorkout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomepage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void grabFormData(ActionEvent e){
        String exerciseName = exerciseNameLabel.getText();
        String numberOfReps = numberOfRepsLabel.getText();
        String numberOfSets = numberOfSetsLabel.getText();
        String difficultyLevel = difficultyLevelLabel.getText();
        String avgSetDuration = avgSetDurationLabel.getText();
        String date = dateLabel.getText();

        String data = exerciseName + numberOfReps + numberOfSets + difficultyLevel + avgSetDuration + date;

        System.out.println(exerciseName);
        System.out.println(numberOfReps);
        System.out.println(numberOfSets);
        System.out.println(difficultyLevel);
        System.out.println(avgSetDuration);
        System.out.println(date);

        saveToFile(data);

    }

    private void saveToFile(String data){
        try{
            // Create file
            FileWriter fstream = new FileWriter("out.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);

            out.append(data + "\n");


            //Close the output stream
            out.close();
        }catch (Exception exception){//Catch exception if any
            System.err.println("Error: " + exception.getMessage());
        }
    }

}
