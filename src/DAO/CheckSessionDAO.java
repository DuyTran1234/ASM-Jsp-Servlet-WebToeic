package DAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import BEAN.User;

public class CheckSessionDAO {
	public static User checkSession(HttpServletRequest request) {
		User user = new User();
		HttpSession session = request.getSession(false);
		user = (User)session.getAttribute("sessionUser");
		return user;
	}
}
