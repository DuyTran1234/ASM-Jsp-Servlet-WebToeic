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
		
	}
}
