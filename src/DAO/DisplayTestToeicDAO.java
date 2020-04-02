package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.TestToeic;
import DB.Connect;

public class DisplayTestToeicDAO {
	public static List<TestToeic> getList(HttpServletRequest request) {
		List<TestToeic> list = null;
		String sql = "SELECT * FROM web_toeic.test_toeic";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if(result.isBeforeFirst()) {
				list = new ArrayList<>();
			}
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
				list.add(toeic);
			}
		}
		catch(SQLException e) {
			request.setAttribute("msgDB", "Lỗi kết nối database");
		}
		return list;
	}
}
