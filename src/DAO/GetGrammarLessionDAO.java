package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import BEAN.Lession;
import DB.Connect;

public class GetGrammarLessionDAO {
	public static List<Lession> getLession(List<Lession> list) {
		String sql = "select * from grammar_lession order by date ASC";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Lession lession = new Lession();
				lession.setLessionName(result.getString("lessionName"));
				lession.setContent(result.getString("content"));
				lession.setDateToday(result.getString("date"));
				list.add(lession);
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getLession() {
		String sql = "select * from grammar_lession order by date ASC";
		List<Lession> list = new ArrayList<>();
		String listJSON = "";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Lession lession = new Lession();
				lession.setLessionName(result.getString("lessionName"));
				lession.setContent(result.getString("content"));
				lession.setDateToday(result.getString("date"));
				list.add(lession);
			}
			listJSON = new Gson().toJson(list);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return listJSON;
	}
}
