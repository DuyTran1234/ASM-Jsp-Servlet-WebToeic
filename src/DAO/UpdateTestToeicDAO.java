package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DB.Connect;

public class UpdateTestToeicDAO {
	public static void deleteTestToeic(int id) {
		String sql = "delete from test_toeic where id = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
