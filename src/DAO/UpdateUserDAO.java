package DAO;

import BEAN.User;
import DB.*;
import java.sql.*;

public class UpdateUserDAO {
	public static boolean updateUser(User user) {
		try {
			String sql = "update user set fullName = ?, phoneNumber = ?, address = ? where userName = ?";
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getPhoneNumber());
			statement.setString(3, user.getAddress());
			statement.setString(4, user.getUserName());
			statement.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
