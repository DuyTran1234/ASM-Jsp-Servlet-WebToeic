package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import DB.Connect;

public class DeleteListeningExerciseDAO {
	public static void deleteListeningExerciseBasedId(int exerciseId, HttpServletRequest request) {
		String sql = "delete from listening_exercise where exerciseID = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setInt(1, exerciseId);
			statement.executeUpdate();
			request.setAttribute("msgDatabaseDelete", "Xo� th�nh c�ng");
		}
		catch(SQLException e) {
			request.setAttribute("msgDatabaseDelete", "Xo� kh�ng th�nh c�ng");
		}
	}
	public static void deleteAllListeningExercise(String exerciseName, HttpServletRequest request) {
		String sql = "delete from listening_exercise where exerciseName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, exerciseName);
			statement.executeUpdate();
			request.setAttribute("msgDatabaseDelete", "Xo� th�nh c�ng");
		}
		catch(SQLException e) {
			request.setAttribute("msgDatabaseDelete", "Xo� kh�ng th�nh c�ng");
		}
	}
}
