package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import BEAN.Exercise;
import DB.Connect;

public class GetReadingExerciseDAO {
	public static List<Exercise> getListExercise() {
		List<Exercise> list = new ArrayList<>();
		String sql = "select * from reading_exercise order by exerciseName";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Exercise exercise = new Exercise();
				exercise.setExerciseID(result.getInt("exerciseID"));
				exercise.setExerciseName(result.getString("exerciseName"));
				exercise.setQuestionID(result.getString("questionID"));
				exercise.setQuestionContent(result.getString("questionContent"));
				exercise.setOptionA(result.getString("optionA"));
				exercise.setOptionB(result.getString("optionB"));
				exercise.setOptionC(result.getString("optionC"));
				exercise.setOptionD(result.getString("optionD"));
				exercise.setResult(result.getString("result"));
				exercise.setDate(result.getString("date"));
				list.add(exercise);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getValueJSON() {
		List<Exercise> list = new ArrayList<>();
		list = getListExercise();
		String valueJSON = new Gson().toJson(list);
		return valueJSON;
	}
	public static String getNameJSON() {
		List<String> listName = new ArrayList<>();
		String sql = "select exerciseName from reading_exercise order by exerciseName ASC";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				listName.add(result.getString("exerciseName"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		String nameJSON = new Gson().toJson(listName);
		return nameJSON;
	}
}
