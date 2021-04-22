package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class utility {
    
	public static void saveWorkoutToFile(String data)
	{
		saveToFile(data, "workouts.txt");
	}
	
    public static void getConnection()
    {
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
    	}
    }
    
	/**
	 * Write data to a .txt file
	 * @param data
	 * @param fileName
	 */
    private static void saveToFile(String data, String fileName){
        try{
            FileWriter fstream = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.append(data + "\n");
            out.close();
        }catch (Exception exception){
            System.err.println("Error: " + exception.getMessage());
        }
    }

}
