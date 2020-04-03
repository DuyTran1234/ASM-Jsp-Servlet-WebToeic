package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.TestToeic;
import DB.Connect;

public class GetTestToeicDAO {
	public static List<String> getListSearchNameTestToeic(HttpServletRequest request, String name) {
		List<String> listName = new ArrayList<>();
		String sql = "SELECT distinct testToeicName FROM web_toeic.test_toeic where testToeicName like '%" + name + "%'";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				listName.add(result.getString("testToeicName"));
			}
		}
		catch(SQLException e) {
			request.setAttribute("msgDB", "Lỗi kết nối Database");
		}
		return listName;
	}
	
	public static List<TestToeic> getListTestToeicBasedName(HttpServletRequest request, String testToeicName) {
		List<TestToeic> listTestToeic = new ArrayList<>();
		String sql = "SELECT *  FROM web_toeic.test_toeic where testToeicName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, testToeicName);
			ResultSet result = statement.executeQuery();		
			while(result.next()) {
				TestToeic toeic = new TestToeic();
				toeic.setId(result.getInt("id"));
				toeic.setTestToeicName(result.getString("testToeicName"));
				toeic.setQuestionID(result.getString("questionID"));
				toeic.setQuestionContent(result.getString("questionContent"));
				toeic.setOptionA(result.getString("optionA"));
				toeic.setOptionB(result.getString("optionB"));
				toeic.setOptionC(result.getString("optionC"));
				toeic.setOptionD(result.getString("optionD"));
				toeic.setResult(result.getString("result"));
				toeic.setDate(result.getString("date"));
				toeic.setPath(result.getString("path"));
				listTestToeic.add(toeic);
			}
		} catch (SQLException e) {
			request.setAttribute("msgDB", "Lỗi kết nối Database");
		}
		return listTestToeic;
	}
}
