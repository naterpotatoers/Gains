package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Loading driver...");
			String host = "cs151-gains-db.cjsjim1qtdlq.us-west-2.rds.amazonaws.com/";
			String database = "gains";
			String databaseUsername = "root";
			String databasePassword = "Leanahtan523509";
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + database, databaseUsername, databasePassword)) {
				if (connection != null) {
					System.out.println("Connected to the database!");
					String sql = "Select * from workout";
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery(sql);
					while(result.next()) {
						int id = result.getInt("id"); // primary key
						String username = result.getString("username");
						String workoutName = result.getString("workoutName");
						String difficulty = result.getString("difficulty");
						int duration = result.getInt("duration");
						Date workoutDate = result.getDate("workoutDate");
						int weight = result.getInt("weight");
						int sets = result.getInt("sets");
						int reps = result.getInt("reps");
						
						System.out.printf("id(primary key): %d username: %s workoutName: %s difficulty: %s duration: %d workoutDate: %s weight: %d sets: %d reps: %d \n", 
								id, username, workoutName, difficulty, duration, workoutDate, weight, sets, reps);
					}
					result.close();
					connection.close();
				} else {
					System.out.println("Failed to make connection!");
				}

			} catch (SQLException e) {
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}

			Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
