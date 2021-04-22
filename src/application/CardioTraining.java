package application;

import java.sql.Date;

public class CardioTraining extends Exercise {
	
	public CardioTraining(String username, String workoutName, String difficulty,
			int duration, Date workoutDate) {
		setUsername(username);
		setWorkoutName(workoutName);
		setDifficulty(difficulty);
		setDuration(duration);
		setWorkoutDate(workoutDate);
	}
	
	public CardioTraining(int id, String username, String workoutName, String difficulty, 
			int duration, Date workoutDate) {
		setId(id);
		setUsername(username);
		setWorkoutName(workoutName);
		setDifficulty(difficulty);
		setDuration(duration);
		setWorkoutDate(workoutDate);
	}
	
	@Override
	public String toString() {
		String data = 
				"username: " + getUsername() + 
				", workoutName: " + getWorkoutName() + 
				", difficulty: " + getDifficulty() + 
				", duration: " + getDuration() + 
				", workoutDate: " + getWorkoutDate();
		// Adds id value if it exists
		if(getId() != 0) {
			return 	"id: " + getId() + ", " + data;
		}
		return data;
	}

}
