package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import DB.Connect;

public class CheckTestToeicNameDAO {
	public static boolean checkTestToeicName(String testToeicName, HttpServletRequest request) {
		String sql = "SELECT testToeicName FROM web_toeic.test_toeic where testToeicName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, testToeicName);
			ResultSet result = statement.executeQuery();
			return result.isBeforeFirst();
		}
		catch(SQLException e) {
			request.setAttribute("msgDB", "Lỗi kết nối database");
		}
		return true;
	}
}
