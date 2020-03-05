package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DB.Connect;

public class DeleteLessionGrammarDAO {
	public static boolean deleteLession(String deleteLessionName) {
		String sql = "delete from grammar_lession where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, deleteLessionName);
			statement.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
}
