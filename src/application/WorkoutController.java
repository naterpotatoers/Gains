package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutController {
	private String host = "cs151-gains-db.cjsjim1qtdlq.us-west-2.rds.amazonaws.com/";
	private String dbName = "gains";
	private String dbUsername = "root";
	private String dbPassword = "Leanahtan523509";
	private String user = "nate";
	private MySQLDatabase database;
	
	WorkoutController(){
		database = new MySQLDatabase(host, dbName, dbUsername, dbPassword);
	}

	public ResultSet getWorkouts(String dbTable) {
		System.out.println("Fetching" + dbTable +" workouts...");
		String sql = "SELECT * FROM " + dbTable + " WHERE username = 'user' ORDER BY workoutDate DESC";
		return database.queryStatement(sql);
	}
	
	public ResultSet getRecentWorkout(String dbTable) {
		System.out.println("Fetching most recent workouts...");
		String sql = "SELECT * FROM " + dbTable + " WHERE username = 'user' ORDER BY workoutDate DESC limit 2";
		return database.queryStatement(sql);
	}
	
	public void addCardioExercise(CardioTraining exercise) {
		System.out.println("Adding cardio exercise to database...");
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
	}
	
	public void addWeightsExercise(WeightTraining exercise) {
		System.out.println("Adding weights exercise to database...");
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
	}
}
