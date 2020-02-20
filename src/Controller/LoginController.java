package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.LoginDAO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("pword"));
		
		user = LoginDAO.loginAccount(request, user);
		if(user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("./View/Login.jsp");
			rd.forward(request, response);
		}
		else {
			HttpSession session = request.getSession(true);
			session.setAttribute("sessionUser", user);
			RequestDispatcher rd = request.getRequestDispatcher("./View/Home.jsp");
			rd.forward(request, response);
		}
	}

}
