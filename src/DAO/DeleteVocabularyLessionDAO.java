package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DB.Connect;

public class DeleteVocabularyLessionDAO {
	public static boolean deleteLession(String lessionName) {
		String sql = "delete from vocabulary_lession where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lessionName);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
