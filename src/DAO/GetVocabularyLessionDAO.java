package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import BEAN.Lession;
import DB.Connect;

public class GetVocabularyLessionDAO {
	public static List<Lession> getLession(List<Lession> list) {
		String sql = "select * from vocabulary_lession";
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;		
		}		
	}
	public static String getLession() {
		String listJSON = null;
		List<Lession> list = new ArrayList<>();
		list = getLession(list);
		if(list.size() > 0) {	
			listJSON = new Gson().toJson(list);
		}
		return listJSON;
	}
}
