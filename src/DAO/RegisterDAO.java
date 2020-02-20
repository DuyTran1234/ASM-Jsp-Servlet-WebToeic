package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;
import DB.Connect;

public class RegisterDAO {
	public static boolean insertUserDAO(HttpServletRequest request, User user, String confirmPassword) {
		if(!user.getPassword().equals(confirmPassword)) {
			request.setAttribute("msgRegister", "Password và Confirm Password chưa trùng nhau");
			return false;
		}
		if(!checkPatternRegister(request, user)) {
			return false;
		}
		if(checkUsernameExists(request, user)) {
			return false;
		}
		if(checkEmailExists(request, user)) {
			return false;
		}
	
		try {
			String sql = "insert into user(userName,password,email) values(?,?,?)";
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			request.setAttribute("msgRegister", "Lỗi kết nối đến Database1");
		}
		return false;
	}
	
	public static boolean checkPatternRegister(HttpServletRequest request, User user) {
		Pattern patternUsername = Pattern.compile("[a-zA-Z0-9]{6,40}");
		Matcher matcherUsername = patternUsername.matcher(user.getUserName());		
		
		Pattern patternPassword = Pattern.compile("[a-zA-Z0-9]{6,40}");
		Matcher matcherPassword = patternPassword.matcher(user.getPassword());
		
		Pattern patternEmail = Pattern.compile(".+@.{4,150}");
		Matcher matcherEmail = patternEmail.matcher(user.getEmail());
		
		if(!matcherUsername.matches()) {
			request.setAttribute("msgRegister", "Username từ 6-40 ký tự, chỉ chứa chữ thường, chữ hoa và chữ số");
			return false;
		}
		else if(!matcherPassword.matches()) {
			request.setAttribute("msgRegister", "Password từ 6-40 ký tự, chỉ chứa chữ thường, chữ hoa và chữ số");
			return false;
		}
		else if(!matcherEmail.matches()) {
			request.setAttribute("msgRegister", "Email không đúng định dạng");
			return false;
		}
		return true;
	}
	
	public static boolean checkUsernameExists(HttpServletRequest request, User user) {
		try {
			String sql = "select userName from user where userName = ?";
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1,user.getUserName());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				request.setAttribute("msgRegister", "Username đã tồn tại");
				return true;
			}
		}
		catch(SQLException e) {
			request.setAttribute("msgRegister", "Lỗi kết nối đến Database2");
		}
		return false;
	}
	
	public static boolean checkEmailExists(HttpServletRequest request, User user) {
		String sql = "select email from user where email = ?";
		try {
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1,user.getEmail());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				request.setAttribute("msgRegister", "Email đăng ký đã tồn tại");
				return true;
			}
		}
		catch(SQLException e) {
			request.setAttribute("msgRegister", "Lỗi kết nối đến Database3");
		}
		return false;
	}
}


