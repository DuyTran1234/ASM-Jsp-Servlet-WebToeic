package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.Connect;

public class CreateListeningExerciseDAO {
	public static boolean checkExistsExercise(String exerciseName) {
		String sql = "select exerciseName from listening_exercise where exerciseName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, exerciseName);
			ResultSet result = statement.executeQuery();
			return result.next();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
