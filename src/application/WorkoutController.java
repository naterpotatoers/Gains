package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkoutController {
	private String host = "cs151-gains-db.cjsjim1qtdlq.us-west-2.rds.amazonaws.com/";
	private String dbName = "gains";
	private String dbUsername = "root";
	private String dbPassword = "Leanahtan523509";
	private MySQLDatabase database;
	
	WorkoutController(){
		System.out.print("Connecting to " + this.host + " MySQL database... ");
		database = new MySQLDatabase(host, dbName, dbUsername, dbPassword);
		System.out.println("Connected!");
	}
	
	public ArrayList<WeightTraining> getAllWeightWorkouts() {
		String dbTable = "workout";
		System.out.println("Fetching data from " + dbTable +" table...");
		String sql = "SELECT * FROM " + dbTable + " WHERE username = 'nate' ORDER BY workoutDate DESC";
		ArrayList<WeightTraining>weightExercises = database.queryWeightTable(sql);
		return weightExercises;
	}
	
	public ArrayList<CardioTraining> getAllCardioWorkouts() {
		String dbTable = "cardio";
		System.out.println("Fetching data from " + dbTable +" table...");
		String sql = "SELECT * FROM " + dbTable + " WHERE username = 'nate' ORDER BY workoutDate DESC";
		ArrayList<CardioTraining>cardioExercises = database.queryCardioTable(sql);
		return cardioExercises;
	}
	
	public void addWeightExercise(WeightTraining exercise) {
		System.out.print("Adding weights exercise to database... ");
		String sql = "INSERT INTO workout (username, workoutName, difficulty, duration, workoutDate, weight, sets, reps) VALUES (?,?,?,?,?,?,?,?)";
		try (PreparedStatement statement = database.connectDatabase().prepareStatement(sql)) {
			statement.setString(1, exercise.getUsername());
			statement.setString(2, exercise.getWorkoutName());
			statement.setString(3, exercise.getDifficulty());
			statement.setString(4, exercise.getDuration());
			statement.setDate(5, exercise.getWorkoutDate());
			statement.setInt(6, exercise.getWeight());
			statement.setInt(7, exercise.getSets());
			statement.setInt(8, exercise.getReps());
			statement.executeUpdate();
		} catch (SQLException e) {
			database.sqlExceptionError(e);
		}
		System.out.println("Success!");
	}
	
	public void addCardioExercise(CardioTraining exercise) {
		System.out.print("Adding cardio exercise to database... ");
		String sql = "INSERT INTO cardio (username, workoutName, difficulty, duration, workoutDate) VALUES (?,?,?,?,?)";
		try (PreparedStatement statement = database.connectDatabase().prepareStatement(sql)) {
			statement.setString(1, exercise.getUsername());
			statement.setString(2, exercise.getWorkoutName());
			statement.setString(3, exercise.getDifficulty());
			statement.setString(4, exercise.getDuration());
			statement.setDate(5, exercise.getWorkoutDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			database.sqlExceptionError(e);
		}
		System.out.println("Success!");
	}
}
