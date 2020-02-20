package DAO;

import java.sql.*;

import BEAN.User;
import DB.Connect;

public class SelectUserDAO {
	public static User selectUser(User user) {
		String sql = "select * from user where userName = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, user.getUserName());
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user.setUserID(result.getInt("userID"));
				user.setPassword(result.getString("password"));
				user.setUserType(result.getString("userType"));
				user.setUserTypeID(result.getInt("userTypeID"));
				user.setEmail(result.getString("email"));
				user.setFullName(result.getString("fullName"));
				user.setPhoneNumber(result.getString("phoneNumber"));
				user.setAddress(result.getString("address"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
