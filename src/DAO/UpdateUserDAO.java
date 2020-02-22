package DAO;

import BEAN.User;
import DB.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
	public static boolean updatePassword(User user, String newPassword, String presentPassword, String confirmPassword, HttpServletRequest request) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,40}");
		Matcher matcher = pattern.matcher(newPassword);
		if(!matcher.matches()) {
			request.setAttribute("msgUpdatePassword", "Mật khẩu phải từ 6-40 ký tự, chỉ chứa chữ hoa, thường hoặc chữ số");
			return false;
		}
		if(!user.getPassword().equals(presentPassword)) {
			request.setAttribute("msgUpdatePassword", "Sai mật khẩu hiện tại");
			return false;
		}
		else if(!newPassword.equals(confirmPassword)) {
			request.setAttribute("msgUpdatePassword", "Mật khẩu xác nhận chưa trùng khớp");
			return false;
		}
		else if(user.getPassword().equals(newPassword)) {
			request.setAttribute("msgUpdatePassword", "Mật khẩu mới trùng với mật khẩu hiện tại");
			return false;
		}
		try {
			String sql = "update user set password = ? where userName = ?";
			PreparedStatement statement = Connect.connectDB().prepareStatement(sql);
			statement.setString(1, newPassword);
			statement.setString(2, user.getUserName());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			request.setAttribute("msgUpdatePassword", "Lỗi kết nối database thay đổi mật khẩu");
			return false;
		}
		request.setAttribute("msgUpdatePassword", "Cập nhật mật khẩu thành công");
		return true;
	}
}
