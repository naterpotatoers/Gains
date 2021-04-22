package application;

import java.sql.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		utility.getConnection();
//		Date date = new Date(2021-04-22);
//		WeightTraining test = new WeightTraining(10, "nate", "brench press", "medium", 30, date, 25, 3, 12);
//		System.out.println(test);
		launch(args);
	}
}
