package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;
import DB.Connect;

public class LoginDAO {
	public static User loginAccount(HttpServletRequest request, User user) {
		if(!checkPatternLogin(request, user)) {
			user = null;
			return user;
		}
		try {
			String sql = "select * from user where userName = ? and password = ?";
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				user.setUserType(result.getString("userType"));
				user.setUserTypeID(result.getInt("userTypeID"));
				user.setEmail(result.getString("email"));
				user.setFullName(result.getString("fullName"));
				user.setPhoneNumber(result.getString("phoneNumber"));
				user.setAddress(result.getString("address"));
			}
			else {
				user = null;
				request.setAttribute("msgLogin", "Sai tên đăng nhập hoặc mật khẩu");
				return user;
			}
		}
		catch(SQLException e) {
			request.setAttribute("msgLogin", "Không thể kết nối đến database");
		}
		
		return user;
	}
	public static boolean checkPatternLogin(HttpServletRequest request, User user) {
		Pattern patternUsername = Pattern.compile("[a-zA-z0-9]{6,40}");
		Matcher matcherUsername = patternUsername.matcher(user.getUserName());
		
		Pattern patternPassword = Pattern.compile("[a-zA-Z0-9]{6,40}");
		Matcher matcherPassword = patternPassword.matcher(user.getPassword());
		if(!matcherUsername.matches()) {
			request.setAttribute("msgLogin", "Username từ 6-40 ký tự, chỉ chứa chữ hoa, chữa thường và chữ số");
			return false;
		}
		else if(!matcherPassword.matches()) {
			request.setAttribute("msgLogin", "Password từ 6-40 ký tự, chỉ chứa chữ hoa, chữa thường và chữ số");
			return false;
		}
		return true;
	}
	
}