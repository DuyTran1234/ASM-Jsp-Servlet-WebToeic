package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import BEAN.Lession;
import DB.Connect;

public class UpdateLessionGrammarDAO {
	public static boolean updateLessionGrammar(Lession lession, String lessionNameOld) {
		String sql = "update grammar_lession set lessionName = ? , content = ? where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lession.getLessionName());
			statement.setString(2, lession.getContent());
			statement.setString(3, lessionNameOld);
			statement.executeUpdate();
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public static boolean updateLessionGrammar(String lessionNameNew, String lessionNameOld) {
		String sql = "update grammar_lession set lessionName = ? where lessionName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, lessionNameNew);
			statement.setString(2, lessionNameOld);
			statement.executeUpdate();
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}







